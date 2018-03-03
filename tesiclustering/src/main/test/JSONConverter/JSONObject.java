package JSONConverter;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;

//import org.json.XML;




public class JSONObject {
	
	
	public static void main(String[] args) throws Exception {
	
	//filename is filepath string
	BufferedReader br = new BufferedReader(new FileReader(new File("Geant2012.graphml")));
	String line;
	StringBuilder sb = new StringBuilder();

	while((line=br.readLine())!= null){
	    sb.append(line.trim());
	}
	
	System.out.println("Convert xml file to string");
	System.out.println(sb);	
	//String json = XML.toJSONObject(sb.toString()).toString();	
	//System.out.println("Convert string to json");
	//System.out.println(json);
	
	
	}

}
