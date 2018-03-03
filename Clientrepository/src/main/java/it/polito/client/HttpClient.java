
package it.polito.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import it.polito.infrastructure.GraphDeserializer;
import it.polito.model.Execution;
import it.polito.model.ExecutionSerializer;
import it.polito.model.Graph;
import it.polito.model.GraphSerializer;

public class HttpClient {

	public Integer id = null;

	public static boolean DeleteExecutionWithId(String name, Integer id) throws Exception {

		HttpClient http = new HttpClient();

		URL url = null;

		url = new URL("http://localhost:8080/tesiclustering/rest/graphs/" + name + "/executions/" + id);

		try {

			http.sendDelete(url);

		} catch (RuntimeException e) {

			System.out.println(e.getMessage());

		}

		return true;

	}

	public static boolean PostGraph(String path) throws Exception {

		File f = new File(path);
		String graphName = f.getName();

		BufferedReader br = null;

		br = new BufferedReader(new FileReader(new File(f.getAbsolutePath())));

		String line;
		StringBuilder sb = new StringBuilder();

		while ((line = br.readLine()) != null) {
			sb.append(line.trim());
		}

		System.out.println("Convert xml file inserted to string");
		System.out.println(sb);

		String encodedString = new String(Base64.encodeBase64(sb.toString().getBytes()));

		Graph graph = new Graph();

		graph.setName(graphName);
		graph.setXml_file(encodedString);

		System.out.println("Grafo convertito in base 64" + graph);

		System.out.println("Set name and xml_file in java object");
		String input = null;
		System.out.println("Start serialization");
		input = serialises_Graph(graph);
		System.out.println("End serialization");

		HttpClient http = new HttpClient();
		URL url = new URL("http://localhost:8080/tesiclustering/rest/graphs");

		http.sendPost(url, input);

		return true;

	}

	public static boolean GetGraph(String name) throws Exception {

		HttpClient http = new HttpClient();

		URL url = null;
		String output;
		Graph graph = new Graph();

		url = new URL("http://localhost:8080/tesiclustering/rest/graphs/" + name);
		try {

			output = http.sendGet(url);
			graph = deserialises_graph(output);
			String originalInput = graph.getXml_file();
			String decodedString = new String(Base64.decodeBase64(originalInput));
			graph.setXml_file(decodedString);

		} catch (RuntimeException e) {

			System.out.println(e.getMessage());

		}

		return true;

	}

	public static boolean PostExecutionWithRate(String name, Integer rate) throws Exception {

		HttpClient http = new HttpClient();

		URL url = null;

		url = new URL("http://localhost:8080/tesiclustering/rest/graphs/" + name + "/executions");

		Execution execution = new Execution();
		execution.setRate(rate);

		String input = serialises_Execution(execution);

		try {

			http.sendPost(url, input);

		} catch (RuntimeException e) {

			System.out.println(e.getMessage());

		}

		return true;

	}

	public static boolean PostExecutionWithNodes(String name, String nodes) throws Exception {

		HttpClient http = new HttpClient();

		URL url = null;

		Execution execution = new Execution();
		execution.setNodes(nodes);

		url = new URL("http://localhost:8080/tesiclustering/rest/graphs/" + name + "/executions");

		String input = serialises_Execution(execution);

		try {

			http.sendPost(url, input);

		} catch (RuntimeException e) {

			e.printStackTrace();

		}

		return true;

	}

	public static boolean DeleteGraph(String name) throws Exception {

		HttpClient http = new HttpClient();

		URL url = null;

		url = new URL("http://localhost:8080/tesiclustering/rest/graphs/" + name);

		try {

			http.sendDelete(url);

		} catch (RuntimeException e) {

			System.out.println(e.getMessage());

		}

		return true;

	}

	public static boolean GetGraphs() throws Exception {

		HttpClient http = new HttpClient();

		URL url = null;

		url = new URL("http://localhost:8080/tesiclustering/rest/graphs");

		try {

			http.sendGet(url);

		} catch (RuntimeException e) {

			System.out.println(e.getMessage());

		}

		return true;

	}

	public static boolean GetExecution(String name) throws Exception {

		HttpClient http = new HttpClient();

		URL url = null;

		url = new URL("http://localhost:8080/tesiclustering/rest/graphs/" + name + "/executions");

		try {

			http.sendGetByName(url);

		} catch (RuntimeException e) {

			System.out.println(e.getMessage());

		}

		return true;

	}

	public static boolean GetExecutionWithId(String name, Integer id) throws Exception {

		HttpClient http = new HttpClient();

		URL url = null;

		url = new URL("http://localhost:8080/tesiclustering/rest/graphs/" + name + "/executions/" + id);

		try {

			http.sendGet(url);

		} catch (RuntimeException e) {

			System.out.println(e.getMessage());

		}

		return true;

	}

	public static boolean GetExecutions(String name) throws Exception {

		HttpClient http = new HttpClient();

		URL url = null;

		url = new URL("http://localhost:8080/tesiclustering/rest/graphs/" + name + "/executions");

		try {

			http.sendGet(url);

		} catch (RuntimeException e) {

			System.out.println(e.getMessage());

		}

		return true;

	}

	// HTTP GET request
	/*public String sendGet(URL url) throws Exception {

		String output = null;

		try {

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {

				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			while ((output = br.readLine()) != null) {

				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}

		return output;

	}*/
	public String sendGet(URL url) throws Exception {

			    String finalResult = null;
			    String output = "";
			    StringBuilder sb = new StringBuilder();

			    try {

			      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			      conn.setRequestMethod("GET");
			      conn.setRequestProperty("Accept", "application/json");

			      if (conn.getResponseCode() != 200) {

			        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			      }

			      BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			      //while ((output = br.readLine()) != null) {
		          //System.out.println(output);
			      //}
			      
			      while ((output = br.readLine()) != null) {

			        sb.append(output);
			      }      

			      conn.disconnect();
			      
			      finalResult = sb.toString();
			      System.out.println(finalResult);

			    } catch (MalformedURLException e) {

			      e.printStackTrace();
			    } catch (IOException e) {

			      e.printStackTrace();

			    }

			    return output;

	}

	// HTTP GET request
	public Integer sendGetByName(URL url) throws Exception {

		try {

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output = null;
			System.out.println("Output from Server .... \n");

			while ((output = br.readLine()) != null) {

				System.out.println(output);
				JSONArray jsonArray = new JSONArray(output);

				for (int i = 0; i < jsonArray.length(); ++i) {

					JSONObject jsn = jsonArray.getJSONObject(i);

					Integer id = jsn.getInt("id");

					System.out.println("Id : " + id);

				}

			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}

		return id;

	}

	// HTTP POST request
	public void sendPost(URL url, String input) throws Exception {

		try {

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode() + " Errore: "
						+ conn.getResponseMessage());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {

				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	// HTTP DELETE request
	public void sendDelete(URL url) throws Exception {

		try {

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("DELETE");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {

				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	public static String serialises_Graph(Graph graph) throws JsonProcessingException, IOException {
		Writer jsonWriter = new StringWriter();
		JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
		SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();
		new GraphSerializer().serialize(graph, jsonGenerator, serializerProvider);
		jsonGenerator.flush();
		String input = jsonWriter.toString();
		return input;
	}

	public static Graph deserialises_graph(String output) throws IOException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addDeserializer(Graph.class, new GraphDeserializer());
		mapper.registerModule(module);

		Graph graph = mapper.readValue(output, Graph.class);

		return graph;
	}

	public static String serialises_Execution(Execution execution) throws JsonProcessingException, IOException {
		Writer jsonWriter = new StringWriter();
		JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
		SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();
		new ExecutionSerializer().serialize(execution, jsonGenerator, serializerProvider);
		jsonGenerator.flush();
		String input = jsonWriter.toString();
		return input;
	}

}