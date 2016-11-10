package pl.net.divo.crm.resources;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.net.divo.crm.dao.ContactDao;
import pl.net.divo.crm.model.Contact;

@Path("/contact")
@Produces({MediaType.APPLICATION_JSON})
public class ContactResource {
	private ContactDao dao;
	
	public ContactResource(ContactDao dao) {
		super();
		this.dao = dao;
	}
	
	@POST
	@RolesAllowed("user")
	public void create(Contact contact){
		dao.create(
			contact.getName(),
			contact.getSurname(),
			contact.getCompanyName(),
			contact.getEmail(),
			contact.getPhone()
		);
	}
	
	@PUT
	@RolesAllowed("user")
	@Path("/{id}")
	public void update(@PathParam("id") int id, Contact contact){
		dao.update(
			id,
			contact.getName(),
			contact.getSurname(),
			contact.getCompanyName(),
			contact.getEmail(),
			contact.getPhone()
		);
	}
	
	@GET
	@RolesAllowed("user")
	public void getAll(){
		dao.fetchAll();
	}
	
	@GET
	@RolesAllowed("user")
	@Path("/{id}")
	public void getById(@PathParam("id") int id){
		dao.getById(id);
	}
	
	@GET
	@RolesAllowed("user")
	@Path("/{name}")
	public void getByName(@PathParam("name") String name){
		dao.getByName(name);
	}
}
