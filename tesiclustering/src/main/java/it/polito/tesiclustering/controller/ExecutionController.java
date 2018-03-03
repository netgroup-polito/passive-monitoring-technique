package it.polito.tesiclustering.controller;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.polito.tesiclustering.model.Execution;
import it.polito.tesiclustering.model.ExecutionDTO;
import it.polito.tesiclustering.model.Graph;
import it.polito.tesiclustering.model.InfoCluster;
import it.polito.tesiclustering.model.InfoClusterDTO;
import it.polito.tesiclustering.model.exception.DaoException;
import it.polito.tesiclustering.model.exception.ExecutionAlreadyCreatedException;
import it.polito.tesiclustering.service.ExecutionService;
import it.polito.tesiclustering.service.GraphService;

/**
 * Questa classe risponde alle richieste HTTP per i path che iniziano con
 * "/graphs/{name}/executions". Ogni metodo esegue una delle operazioni indicate
 * sul progetto del Web Service REST.
 * 
 * -- OPERAZIONI -------------------------- POST /graphs/{name}/executions GET
 * /graphs/{name}/executions/{id} GET /graphs/{name}/executions DELETE
 * /graphs/{name}/executions/{id} -------------------------------------------
 */
@Path("/graphs/{name}/executions")
public class ExecutionController {

	// Devo creare una istanza del servizio per poterlo usare.
	// Il servizio mi permette di gestire le risorse (in questo caso le
	// executions)
	static final ExecutionService service = new ExecutionService();
	static final GraphService graphservice = new GraphService();

	private static final String SUCCESS_RESULT = "{ \"result\": \"success\" }";
	private static final String FAILURE_RESULT = "{ \"result\": \"failure\" }";

	private static ExecutionDTO ToExecutionDTO(Execution execution) {

		ExecutionDTO dto = new ExecutionDTO();

		List<InfoClusterDTO> lists_infoclustersdto = new LinkedList<InfoClusterDTO>();

		List<InfoCluster> list_infocluster = new LinkedList<InfoCluster>();
		list_infocluster = execution.getInfo_clusters();

		for (InfoCluster elem : list_infocluster) {

			InfoClusterDTO infodto = ToInfoClusterDTO(elem);

			lists_infoclustersdto.add(infodto);

		}

		dto.setId(execution.getId());
		dto.setInfo_clusters(lists_infoclustersdto);
		dto.setNodes(execution.getNodes());
		dto.setNum_clusters(execution.getNum_clusters());
		dto.setRate(execution.getRate());
		dto.setTopology(execution.getTopology());

		return dto;

	}

	private static InfoClusterDTO ToInfoClusterDTO(InfoCluster infocluster) {

		InfoClusterDTO dto = new InfoClusterDTO();

		dto.setId(infocluster.getId());
		dto.setId_cluster(infocluster.getId_cluster());
		dto.setMonitored_diameter(infocluster.getMonitored_diameter());
		dto.setExtended_diameter(infocluster.getExtended_diameter());
		dto.setNum_extended_edges(infocluster.getNum_extended_edges());
		dto.setNum_extended_nodes(infocluster.getNum_extended_nodes());
		dto.setNum_monitored_edges(infocluster.getNum_monitored_edges());
		dto.setNum_monitored_nodes(infocluster.getNum_monitored_nodes());

		return dto;

	}

	private static Execution ToExecution(ExecutionDTO executiondto) {

		Execution execution = new Execution();

		execution.setRate(executiondto.getRate());
		execution.setNodes(executiondto.getNodes());
		execution.setTopology(executiondto.getTopology());

		return execution;

	}

	/**
	 * Questo metodo restituisce una Execution in formato JSON. L'ID della
	 * risorsa da cercare viene letto dalla URL tramite l'annotazione PathParam.
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getExecution(@PathParam("name") String name, @PathParam("id") Integer id) {

		ExecutionDTO result = null;
		Execution execution = null;
		Response response;

		try {

			execution = service.getExecutionByTopologyAndId(name, id);

			if (execution == null) {

				return Response.status(404).entity("Execution not found").build();
			}

			result = ToExecutionDTO(execution);

			response = Response.status(200).entity(result).build();

		} catch (DaoException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			response = Response.status(500).entity(e.getMessage()).build();
			return response;
		}

		// Il risultato sarà automaticamente convertito in un JSON da Jackson,
		// una libreria che ho incluso tra le dipendenze nel POM
		return response;

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getExecutions(@PathParam("name") String name) {

		Response response;
		List<Execution> executions;
		List<ExecutionDTO> executionsdto = new LinkedList<ExecutionDTO>();
		ExecutionDTO executiondto;

		try {

			executions = service.getExecutionsByTopology(name);

			for (Execution elem : executions) {

				if (elem != null) {

					executiondto = ToExecutionDTO(elem);
					executionsdto.add(executiondto);
				}

			}

			response = Response.status(200).entity(executionsdto).build();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = Response.status(500).entity(e.getMessage()).build();
			return response;
		}

		return response;

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createExecution(@PathParam("name") String name, ExecutionDTO execution) throws DaoException {

		Response response;
		Execution created;
		ExecutionDTO executiondto;

		Graph graph = null;

		if (name != null) {

			graph = graphservice.getGraphByName(name);

			if (graph != null) {

				execution.setTopology(graph);

			} else {

				return Response.status(404).entity("Graph not found for this execution.").build();

			}
		}

		try {

			created = service.createExecution(ToExecution(execution));

			executiondto = ToExecutionDTO(created);

		} catch (ExecutionAlreadyCreatedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Conflict
			response = Response.status(409).build();
			return response;

		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = Response.status(500).entity(e.getMessage()).build();
			return response;
		}

		if (created != null)
			response = Response.status(201).entity(executiondto).build();
		else
			response = Response.status(404).build();

		return response;

	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteExecution(@PathParam("name") String name, @PathParam("id") Integer id) {

		int result;

		try {
			result = service.deleteExecution(name, id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = 0;
		}
		if (result == 1) {
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}

}
