package services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import it.polito.tesiclustering.service.ExecutionService;

public class WriteFileTest {
	
	private static void writeFileOnServer(String name, String content) throws IOException{
		
		File file = new File(name);
		
		BufferedWriter bw;
        	
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);               
            bw.write(content);
            bw.close();
            System.out.println("done!!");
		
	}
	
	public static void main(String[] args) {
		
		try {
			writeFileOnServer("prova.xml", "<xml><input>10</input></xml>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
