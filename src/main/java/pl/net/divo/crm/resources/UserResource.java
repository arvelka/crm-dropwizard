package pl.net.divo.crm.resources;

import io.dropwizard.auth.Auth;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.net.divo.crm.dao.UserDao;
import pl.net.divo.crm.model.User;

@Path("/user")
@Produces({MediaType.APPLICATION_JSON})
public class UserResource {
	private UserDao dao;
	
	public UserResource(UserDao dao) {
		super();
		this.dao = dao;
	}
	
	@POST
	public void create(User user){
		dao.create(
			user.getUsername(),
			user.getPassword(),
			user.getName()
		);
	}
	
	@POST
	@Path("/{id}/role")
	public void create(@PathParam("id") int id, int roleId){
		dao.addRole(id, roleId);
	}
	
	@GET
	@Path("/login")
	public User login(@Auth User user) {
		return user;
	}

	@RolesAllowed("admin")
	@GET
	public List<User> fetchAll(){
		return dao.findAll();
	}
}
