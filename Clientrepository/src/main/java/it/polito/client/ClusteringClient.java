package it.polito.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public class ClusteringClient {

	private static class CommandExecutor {

		private static final String SEPARATOR = " ";

		enum ClusteringCommands {

			// carica file , carica rate , visualizza grafo visualizza execution
			// , visualizza grafi, viaualizza executions
			STORE_GRAPH("storegraph"), // POST /graphs
			LIST_GRAPHS("listgraphs"), // GET /graphs
			SHOW_GRAPH("showgraph"), // GET /graphs/:name
			DELETE_GRAPH("deletegraph"), // DELETE /graphs/:name
			DELETE_EXECUTION("deleteexecution"), // DELETE /graphs/:name/executions/:id
			SEND_RATE("executerate"), // POST /graphs/:name/executions with rate
			SEND_NODES("executenodes"), // POST /graphs/:name/executions with nodes
			LIST_EXECUTIONS("listexecutions"), // GET /graphs/:name/executions
			SHOW_EXECUTION("showexecution"), // GET /graphs/:name/execution/:id
			STOP("quit"), MENU("menu");

			private String command;

			private ClusteringCommands(String command) {

				this.command = command;

			}

			String getCommand() {

				return this.command;

			}

			static ClusteringCommands valueOfCommand(String command) {

				for (ClusteringCommands cc : values()) {

					if (cc.command.equalsIgnoreCase(command))
						return cc;

				}

				return null;

			}

		}

		private static String[] readArgumentsFromConsole() throws IOException {

			// An empty line or Ctrl-Z terminates the program

			System.out.println("Per terminare usa Ctrl+z o digita Invio.");
			System.out.print("> ");

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String s;
			String[] args = new String[] {};

			s = in.readLine();

			if (s != null && s.length() != 0)
				args = StringUtils.split(s);

			return args;

		}

		private static boolean executeCommand(String[] args) {
			// TODO Auto-generated method stub

			boolean result = false;

			if (args.length == 0)
				return false;

			ClusteringCommands command = ClusteringCommands.valueOfCommand(args[0]);
			String[] params = Arrays.copyOfRange(args, 1, args.length);

			if (command == null) {
				
				System.out.println("Per favore, inserisci un comando corretto.");
				return true;
				
			}

			switch (command) {

			// tutti i metodi devono ritornare un boolean

			case STORE_GRAPH:
				// qui il metodo per gestire il caricamento del grafo
				result = storeGraph(params);
				break;

			case SEND_RATE:
				// qui il metodo per inviare un rate e richiedere l'esecuzione
				result = sendRate(params);
				break;

			case SEND_NODES:
				// qui il metodo per inviare dei nodi e richiedere l'esecuzione
				result = sendNodes(params);
				break;

			case LIST_GRAPHS:
				// qui il metodo per elencare i grafi già presenti sul server
				result = listGraphs(params);
				break;

			case LIST_EXECUTIONS:
				// qui il metodo per elencare le esecuzioni disponibili per un
				// grafo
				result = listExecutions(params);
				break;

			case SHOW_GRAPH:
				// qui il metodo per visualizzare le informazioni relative ad un
				// grafo
				result = showGraph(params);
				break;

			case SHOW_EXECUTION:
				// qui il metodo per visualizzare le informazioni relative ad
				// una esecuzione
				result = showExecution(params);
				break;

			case DELETE_GRAPH:
				// qui il metodo per cancellare un grafo precedentemente
				// caricato
				result = deleteGraph(params);
				break;

			case DELETE_EXECUTION:
				// qui il metodo per cancellare un'esecuzione precedentemente
				// richiesta
				result = deleteExecution(params);
				break;

			case MENU:
				// qui il metodo per elencare i comandi disponibili
				result = menu(params);
				break;

			case STOP:
				result = false;
				break;

			default:
				// qui il metodo per gestire il caso in cui il comando è
				// sconosciuto
				result = unknownCommand(params);
				break;

			}

			return result;
		}

		private static boolean showGraph(String[] params) {
			// TODO Auto-generated method stub

			boolean result = true;

			// Only name of topology is required
			if (params == null || params.length < 1) {

				System.out.println("Per favore, inserisci il nome del grafo richiesto.");
				return true;

			}

			String topologyName = params[0];

			try {
				System.out.println("Ricerca grafi disponibili sul server...");
				result = HttpClient.GetGraph(topologyName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Errore: impossibile elencare le esecuzioni disponibili.");
				return true;
			}

			return result;
		}

		private static boolean listExecutions(String[] params) {
			// TODO Auto-generated method stub

			boolean result = true;

			// Only name of topology is required
			if (params == null || params.length < 1) {

				System.out.println("Per favore, inserisci il nome del grafo di riferimento.");
				return true;

			}

			String topologyName = params[0];

			try {
				System.out.println("Ricerca esecuzioni disponibili sul server...");
				result = HttpClient.GetExecutions(topologyName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Errore: impossibile elencare le esecuzioni disponibili.");
				return true;
			}

			return result;
		}

		private static boolean listGraphs(String[] params) {
			// TODO Auto-generated method stub

			boolean result = true;

			// No parameters required

			try {
				System.out.println("Ricerca grafi presenti sul server...");
				result = HttpClient.GetGraphs();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Errore: impossibile elencare i grafi presenti sul server.");
				return true;
			}

			return result;
		}

		// COMMANDS

		private static boolean deleteExecution(String[] params) {
			// TODO Auto-generated method stub

			boolean result = true;

			// Two params: name of the topology and ID of the execution
			if (params == null || params.length < 2) {

				System.out.println();
				return true;

			}

			String topologyName = params[0];
			Integer id = Integer.parseInt(params[1]);

			try {
				System.out.println("Cancellazione esecuzione in corso...");
				result = HttpClient.DeleteExecutionWithId(topologyName, id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Errore: impossibile cancellare l'esecuzione.");
				return true;
			}

			return result;
		}

		private static boolean deleteGraph(String[] params) {
			// TODO Auto-generated method stub

			boolean result = true;

			// Only name of topology is required
			if (params == null || params.length < 1)
				return true;

			String topologyName = params[0];

			try {
				System.out.println("Cancellazione grafo in corso...");
				result = HttpClient.DeleteGraph(topologyName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Errore: impossibile cancellare il grafo.");
			}

			return result;
		}

		private static boolean unknownCommand(String[] params) {
			// TODO Auto-generated method stub

			System.out.println("Comando sconosciuto");

			return true;
		}

		private static boolean menu(String[] params) {
			// TODO Auto-generated method stub

			System.out.println();
			System.out.println("Comandi disponibili:");
			System.out.println();
			for (ClusteringCommands cc : ClusteringCommands.values()) {

				System.out.println("   - " + cc.getCommand());
				System.out.println();

			}
			System.out.println();

			return true;
		}

		private static boolean showExecution(String[] params) {
			// TODO Auto-generated method stub

			boolean result = true;

			// Due parametri sono richiesti: nome della topologia di riferimento
			// e id dell'esecuzione
			if (params.length < 2) {

				System.out.println("Sono richiesti due parametri: nome della topologia e id dell'esecuzione.");
				return true;

			}

			String topology = params[0];
			Integer id = Integer.parseInt(params[1]);

			System.out.println("Mostra il risultato di una esecuzione.");
			System.out.println("Topologia: " + topology);
			System.out.println("ID: " + id);

			try {
				result = HttpClient.GetExecutionWithId(topology, id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Errore: impossibile visualizzare l'esecuzione.");
				return true;
			}

			return result;
		}

		private static boolean sendNodes(String[] params) {
			// TODO Auto-generated method stub
			// Only one parameter: name of graph which contains topology
			if (params == null || params.length < 1)

				return true;

			String topologyFilePath = params[0];
			String nodes = params[1];

			boolean result = true;

			try {
				System.out.println("Invio execution in corso...");
				result = HttpClient.PostExecutionWithNodes(topologyFilePath, nodes);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Errore: impossibile caricare una nuova execution.");

				return true;
			}

			return result;
		}

		private static boolean sendRate(String[] params) {
			// TODO Auto-generated method stub
			// Only one parameter: name of graph which contains topology
			if (params == null || params.length < 1)

				return true;

			String topologyFilePath = params[0];
			String rateVal = params[1];
			Integer rate;

			boolean result = true;

			try {
				System.out.println("Invio execution in corso...");
				rate = Integer.parseInt(rateVal);
				result = HttpClient.PostExecutionWithRate(topologyFilePath, rate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Errore: impossibile caricare una nuova execution.");
				return true;
			}

			return result;
		}

		/**
		 * Store a graph into server.
		 * 
		 * @param params
		 * @return
		 */
		private static boolean storeGraph(String[] params) {
			// TODO Auto-generated method stub

			// Only one parameter: name of graph which contains topology
			if (params == null || params.length < 1)
				return true;

			String topologyFilePath = params[0];

			boolean result = true;

			try {
				System.out.println("Invio grafo in corso...");
				result = HttpClient.PostGraph(topologyFilePath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Errore: impossibile caricare un nuovo grafo.");
				return true;
			}

			return result;
		}

		static boolean execute() {

			boolean result = false;

			try {

				String[] args = readArgumentsFromConsole();
				result = executeCommand(args);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("Errors while reading arguments.");
				return true;
			}

			return result;

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		boolean next = true;

		while (next) {

			next = CommandExecutor.execute();

		}

		System.out.println("Execution completed.");

	}

}
