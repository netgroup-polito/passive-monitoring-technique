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

import it.polito.tesiclustering.model.User;
import it.polito.tesiclustering.model.exception.DaoException;
import it.polito.tesiclustering.model.exception.UserAlreadyCreatedException;
import it.polito.tesiclustering.service.UserService;

/**
 * Questa classe risponde alle richieste HTTP per i path che iniziano con
 * "/users". Ogni metodo esegue una delle operazioni indicate sul progetto del
 * Web Service REST.
 * 
 * -- OPERAZIONI -- POST /users -> crea un nuovo utente GET /users ->
 * restituisce l'elenco degli utenti nel sistema GET /users/{id} -> restituisce
 * l'utente con l'ID richiesto GET /users?username={username} -> restituisce
 * l'utente con lo username richiesto
 *
 */
@Path("/users")
public class UserController {

	// Devo creare una istanza del servizio per poterlo usare.
	// Il servizio mi permette di gestire le risorse (in questo caso le
	// executions)
	static final UserService service = new UserService();
	private static final String SUCCESS_RESULT = "{ \"result\": \"success\" }";
	private static final String FAILURE_RESULT = "{ \"result\": \"failure\" }";

	/**
	 * Questo metodo restituisce una user in formato JSON. L'ID della risorsa da
	 * cercare viene letto dalla URL tramite l'annotazione PathParam.
	 * 
	 * @param id
	 * @return
	 */

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("id") Integer id) {

		User result = null;
		Response response;

		try {
			result = service.getUser(id);
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
	public Response getUsers() {

		Response response;
		List<User> users;

		try {
			users = service.getUsers();
			response = Response.status(200).entity(users).build();
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
	public Response createUser(User user) {

		Response response;
		User created;
		try {
			created = service.createUser(user);
		} catch (UserAlreadyCreatedException e) {
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
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUser(@PathParam("id") Integer id) {

		int result;

		try {
			result = service.deleteUser(id);
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
