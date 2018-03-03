package serializers;

import java.util.Arrays;

import it.polito.tesiclustering.model.Execution;
import it.polito.tesiclustering.model.Graph;

public class Test {
	
	private static String removeJSONStringEscapeChars(String toParse) {
		
		return toParse.replaceAll("\\\\", "");
		
	}

    public static void main(String[] args) {
    	
    	System.out.println(removeJSONStringEscapeChars("[[33,34,\"in\"],[33,34,\"out\"],[34,33,\"in\"],[34,33,\"out\"]]"));
    		
    }
	
}
