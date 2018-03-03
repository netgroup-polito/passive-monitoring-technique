package it.polito.tesiclustering.service;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.python.util.PythonInterpreter;

import it.polito.tesiclustering.model.Execution;
import it.polito.tesiclustering.model.Result;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResultFactory {

	public ResultFactory() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		ResultFactory obj = new ResultFactory();
		obj.run();
	}

	private static String removeJSONStringEscapeChars(String toParse) {

		return toParse.replaceAll("\\\\", "");

	}

	public static String replaceLast(String string, String toReplace, String replacement) {
		int pos = string.lastIndexOf(toReplace);
		if (pos > -1) {
			return string.substring(0, pos) + replacement + string.substring(pos + toReplace.length(), string.length());
		} else {
			return string;
		}
	}

	private void run() {

		Execution execution;
		String new_nodes;
		String nodes = "[[33,34,\"in\"],[33,34,\"out\"],[34,33,\"in\"],[34,33,\"out\"]]";
		new_nodes = removeJSONStringEscapeChars(nodes);
		System.out.println(nodes);
		String[] arguments = { "CompleteIterativeClustering_v3.py", "Bics.graphml", nodes };
		PythonInterpreter.initialize(System.getProperties(), System.getProperties(), arguments);
		org.python.util.PythonInterpreter python = new org.python.util.PythonInterpreter();
		StringWriter out = new StringWriter();
		python.setOut(out);
		python.execfile("CompleteIterativeClustering_v3.py");
		String outputStr = out.toString();
		System.out.println(outputStr);

		ObjectMapper mapper = new ObjectMapper();

		// JSON from file to Object
		File f = new File("totclusters.json");

		BufferedReader br = null;
		try {

			br = new BufferedReader(new FileReader(new File(f.getAbsolutePath())));

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		String line;
		StringBuilder sb = new StringBuilder();
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line.trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Convert json file inserted to string");
		System.out.println("Stringa json " + sb);
		System.out.println("Stringa json " + sb.toString());

		String str = sb.toString();

		str = str.replaceFirst("\\[", "");

		System.out.println("Stringa new json " + str);

		str = str.substring(0, str.length() - 1) + "";

		System.out.println("Stringa new json " + str);

		File file = new File("report.json");

		BufferedWriter bw;

		try {

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(str);
			bw.close();
			System.out.println("done!!");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
