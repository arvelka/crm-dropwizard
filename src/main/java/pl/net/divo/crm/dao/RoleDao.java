package pl.net.divo.crm.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface RoleDao {
	@SqlUpdate("CREATE TABLE role (" +
		"id INT AUTO_INCREMENT PRIMARY KEY NOT NULL," +
		"name VARCHAR(64) NOT NULL)"
	)
	void createTable();
	
	@SqlUpdate("insert into role(name) values (:name)")
	void create(
		@Bind("name") String name
	);
	
	@SqlQuery("SELECT id > 0 as my_bool" +
		"FROM role" +
		"LEFT JOIN user_role" +
			"ON role.id=user_role.role_id" +
		"WHERE user_role.user_id=:userId AND role.name=:name LIMIT 1")
	boolean checkRoleByUserIdAndRole(
		@Bind("userId") int userId,
		@Bind("role") String role);
}
