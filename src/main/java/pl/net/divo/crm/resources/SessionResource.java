package pl.net.divo.crm.resources;

import io.dropwizard.jersey.sessions.Session;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/session")
@Produces(MediaType.APPLICATION_JSON)
public class SessionResource {

	private UserDAO userDAO;
	private SessionDAO sessionDAO;

	public SessionResource(UserDAO userDAO, SessionDAO sessionDAO) {
		super();
		this.userDAO = userDAO;
		this.sessionDAO = sessionDAO;
	}

	@POST
	public Session login(
		@FormParam("username") String username,
		@FormParam("password") String password) throws Exception {

		if (userDAO.findUsersByUsernameAndPassword(username, password).isEmpty()) {
			throw new Exception("user not found");
		}

		Session session = new Session(username);
		sessionDAO.insert(session.getAccessToken(), session.getIdentity(), new java.util.Date());

		return session;
}
}
