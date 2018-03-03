package infrastructure;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.polito.tesiclustering.model.Graph;

public class TestDeserializer {
		
	public static void main(String[] args) {
	
	    Graph graph = null;
		try {
			graph = new ObjectMapper()
			  .readerFor(Graph.class)
			  .readValue(new File("C:\\Users\\Federica Mesolella\\gitRespository\\tesiclustering\\src\\main\\test\\infrastructure\\file.json"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    System.out.println(graph);
	    
	}
}

