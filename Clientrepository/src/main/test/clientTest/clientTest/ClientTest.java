package clientTest;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;

import it.polito.client.HttpClient;
import it.polito.model.Execution;
import it.polito.model.ExecutionSerializer;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ClientTest {

	/*
	 * Aggiungere un metodo per ogni operazione definita nei controller. Usare
	 * le funzioni definite in HttpClient passando l'URL che vuoi testare. Le
	 * URL da testare sono rispettivamente:
	 * 
	 * GET /rest/graphs GET /rest/graphs/Geant2012.graphml GET
	 * /rest/graphs/Geant2012.graphml/executions GET
	 * /rest/graphs/Geant2012.graphnl/executions/id
	 * 
	 * POST /rest/graphs POST /rest/graphs/Geant2012.graphml/executions
	 * 
	 * DELETE /rest/graphs/Geant2012.graphnl DELETE
	 * /rest/graphs/Geant2012.graphml/executions/id
	 */

	@Test
	public void getExecutionsTest() {

		// Test per l'operazione GET /rest/graphs/Geant2012.graphml/executions
		// chiamo sendGet()
		HttpClient http = new HttpClient();

		try {

			URL url = new URL("http://localhost:8080/tesiclustering/rest/graphs/Belnet2006.graphml/executions");
			http.sendGet(url);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Questo fa fallire il test e stampa il messaggio d'errore
			Assert.fail(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}

	@Test
	public void getGraphsTest() {

		// Test per l'operazione GET /rest/graphs

		HttpClient http = new HttpClient();
		try {
			URL url = new URL("http://localhost:8080/tesiclustering/rest/graphs");

			http.sendGet(url);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Questo fa fallire il test e stampa il messaggio d'errore
			Assert.fail(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}
	
	@Test
	public void getExecutionTest() {

		// Test per l'operazione GET
		// /rest/graphs/Geant2012.graphnl/executions/id

		HttpClient http = new HttpClient();
		try {
			URL url = new URL("http://localhost:8080/tesiclustering/rest/graphs/Geant2012.graphml/executions/8");

			http.sendGet(url);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Questo fa fallire il test e stampa il messaggio d'errore
			Assert.fail(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}
	
	@Test
	public void getGraphTest() {

		// Test per l'operazione GET /rest/graphs/Geant2012.graphml

		HttpClient http = new HttpClient();
		try {
			URL url = new URL("http://localhost:8080/tesiclustering/rest/graphs/Geant2012.graphml");

			http.sendGet(url);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Questo fa fallire il test e stampa il messaggio d'errore
			Assert.fail(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}
	
	@Test
	public void postGraphsTest() {

		// Test per l'operazione POST /rest/graphs

		HttpClient http = new HttpClient();
		try {
			URL url = new URL("http://localhost:8080/tesiclustering/rest/graphs");

			String input = "Ibm.graphml";
			http.PostGraph(input);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Questo fa fallire il test e stampa il messaggio d'errore
			Assert.fail(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}
	
	@Test
	public void postExecutionsWithRateTest() {

		// Test per l'operazione POST /rest/graphs/Geant2012.graphml/executions

		HttpClient http = new HttpClient();
		try {
			URL url = new URL("http://localhost:8080/tesiclustering/rest/graphs/Geant2012.graphml/executions");

			Integer rate = 10;

			Execution execution = new Execution();

			execution.setRate(rate);

			String input = serialises_Execution(execution);

			http.sendPost(url, input);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Questo fa fallire il test e stampa il messaggio d'errore
			Assert.fail(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}
	
	@Test
	public void postExecutionsWithNodesTest() {

		// Test per l'operazione POST /rest/graphs/Geant2012.graphml/executions

		HttpClient http = new HttpClient();
		try {
			URL url = new URL("http://localhost:8080/tesiclustering/rest/graphs/Geant2012.graphml/executions");

			Execution execution = new Execution();

			String nodes = "[[33,34,\"in\"], [33, 34, \"out\"], [34, 33, \"in\"], [34, 33,\"out\"]]";

			execution.setNodes(nodes);

			String input = serialises_Execution(execution);

			http.sendPost(url, input);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Questo fa fallire il test e stampa il messaggio d'errore
			Assert.fail(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}
	
	@Test
	public void deleteExecutionTest() {

		// Test per l'operazione DELETE
		// /rest/graphs/Geant2012.graphml/executions/id

		HttpClient http = new HttpClient();
		try {
			URL url = new URL("http://localhost:8080/tesiclustering/rest/graphs/Geant2012.graphml/executions/11");

			http.sendDelete(url);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Questo fa fallire il test e stampa il messaggio d'errore
			Assert.fail(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}
	
	@Test
	public void deleteGraphTest() {

		// Test per l'operazione DELETE /rest/graphs/Geant2012.graphnl

		HttpClient http = new HttpClient();
		try {
			URL url = new URL("http://localhost:8080/tesiclustering/rest/graphs/Uran.graphml");

			http.sendDelete(url);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Questo fa fallire il test e stampa il messaggio d'errore
			Assert.fail(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

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
