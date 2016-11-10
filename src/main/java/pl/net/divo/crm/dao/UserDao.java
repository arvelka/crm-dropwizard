package pl.net.divo.crm.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import pl.net.divo.crm.mapping.UserMapper;
import pl.net.divo.crm.model.User;

public interface UserDao {
	@SqlUpdate("CREATE TABLE user (" +
		"id INT AUTO_INCREMENT PRIMARY KEY NOT NULL," +
		"username VARCHAR(64) NOT NULL," +
		"password VARCHAR(64) NOT NULL," +
		"display_name VARCHAR(64) NOT NULL," +
		"created TIMESTAMP default current_timestamp)"
	)
	void createTable();

	@SqlUpdate("insert into user" +
		"(username, password, display_name)" +
		"values (:username, MD5(:password), :displayName)"
	)
	void create(
		@Bind("username") String username,
		@Bind("password") String password,
		@Bind("displayName") String displayName
	);

	@SqlQuery("select * from user")
	@Mapper(UserMapper.class)
	List<User> findAll();
	
	@SqlQuery("select * from user where username = :username and password = MD5(:password) limit 1")
	@Mapper(UserMapper.class)
	User findUserByUsernameAndPassword(
		@Bind("username") String username,
		@Bind("password") String password
	);
	
	@SqlUpdate("insert into user_role" +
		"(user_id, role_id)" +
		"values (:id, :roleId)"
	)
	void addRole(@Bind("id") int id, @Bind("roleId") int roleId);
}