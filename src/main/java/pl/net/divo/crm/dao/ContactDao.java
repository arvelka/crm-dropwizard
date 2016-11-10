package pl.net.divo.crm.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import pl.net.divo.crm.model.Contact;

public interface ContactDao {
	@SqlUpdate("CREATE TABLE contact (" +
		"id INT AUTO_INCREMENT PRIMARY KEY NOT NULL," +
		"name VARCHAR(64) NOT NULL," +
		"surname VARCHAR(64) NOT NULL," +
		"companyName VARCHAR(64) NOT NULL," +
		"email name VARCHAR(64) NOT NULL," +
		"phone VARCHAR(16) NOT NULL," +
		")"
	)
	void createTable();
	
	@SqlUpdate("INSERT INTO contact(name, surname, companyName, email, phone) " +
	" VALUES (:name, :surname, :companyName, :email, :phone)")
	void create(
		@Bind("name") String name,
		@Bind("surname") String surname,
		@Bind("companyName") String companyName,
		@Bind("email") String email,
		@Bind("phone") String phone
	);
	
	@SqlUpdate("UPDATE contact SET " +
	"name=:name, " +
	"surname=:surname, " +
	"companyName=:companyName, " +
	"email=:email, " +
	"phone=:phone, " +
	" VALUES (:name, :surname, :companyName, :email, :phone) WHERE id=:id")
	void update(
		@Bind("id") int id,
		@Bind("name") String name,
		@Bind("surname") String surname,
		@Bind("companyName") String companyName,
		@Bind("email") String email,
		@Bind("phone") String phone
	);
	
	@SqlUpdate("SELECT * FROM contact")
	List<Contact> fetchAll();
	
	@SqlUpdate("SELECT * FROM contact WHERE id=:id")
	Contact getById(@Bind("id") int id);
	
	@SqlUpdate("SELECT * FROM contact WHERE name=:name")
	Contact getByName(@Bind("id") String name);
}