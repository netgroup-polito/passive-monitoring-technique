package it.polito.tesiclustering.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.polito.tesiclustering.model.Graph;
import it.polito.tesiclustering.model.User;
import it.polito.tesiclustering.model.exception.DaoException;
import it.polito.tesiclustering.model.exception.GraphAlreadyCreatedException;
import it.polito.tesiclustering.model.exception.UserAlreadyCreatedException;
import it.polito.tesiclustering.service.GraphService;

/**
 * Questa classe risponde alle richieste HTTP per i path che iniziano con
 * "/graphs/". Ogni metodo esegue una delle operazioni indicate sul progetto del
 * Web Service REST.
 * 
 * -- OPERAZIONI ---------------------------- POST /graphs GET /graphs/{name}
 * GET /graphs DELETE /graphs/{name}
 * -----------------------------------------------
 */
@Path("/graphs")
public class GraphController {

	// Devo creare una istanza del servizio per poterlo usare.
	// Il servizio mi permette di gestire le risorse (in questo caso le
	// executions)
	static final GraphService service = new GraphService();
	private static final String SUCCESS_RESULT = "{ \"result\": \"success\" }";
	private static final String FAILURE_RESULT = "{ \"result\": \"failure\" }";

	/**
	 * Questo metodo restituisce un Graph in formato JSON. L'ID della risorsa da
	 * cercare viene letto dalla URL tramite l'annotazione PathParam.
	 * 
	 * @param id
	 * @return
	 */

	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGraphByName(@PathParam("name") String name) {

		Graph result = null;
		Response response;

		try {

			result = service.getGraphByName(name);

			if (result == null)
				return Response.status(404).entity("Graph not found.").build();

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
	public Response getGraphs() {

		Response response;
		List<Graph> graphs;

		try {
			graphs = service.getGraphs();
			response = Response.status(200).entity(graphs).build();
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
	public Response createGraph(Graph graph) {

		Response response;
		Graph created;

		try {

			created = service.createGraph(graph);

		} catch (GraphAlreadyCreatedException e) {
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
			response = Response.status(201).entity(created).build();
		else
			response = Response.status(404).build();

		return response;

	}

	@DELETE
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteGraph(@PathParam("name") String name) {

		int result;

		try {
			result = service.deleteGraph(name);
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
