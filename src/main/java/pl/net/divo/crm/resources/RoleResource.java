package pl.net.divo.crm.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.net.divo.crm.dao.RoleDao;
import pl.net.divo.crm.model.Role;

@Path("/role")
@Produces({MediaType.APPLICATION_JSON})
public class RoleResource {
	private RoleDao dao;
	
	public RoleResource(RoleDao dao) {
		super();
		this.dao = dao;
	}
	
	@POST
	public void create(Role role){
		dao.create(role.getName());
	}
}
