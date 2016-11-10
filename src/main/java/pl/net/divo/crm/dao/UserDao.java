package pl.net.divo.crm.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import pl.net.divo.crm.mapping.UserMapper;
import pl.net.divo.crm.model.User;

public interface UserDao {

	@SqlQuery("select * from user")
	@Mapper(UserMapper.class)
	List<User> findAll();
	
	@SqlQuery("select * from user where username = :username and password = :password")
	@Mapper(UserMapper.class)
	List<User> findUsersByUsernameAndPassword(
		@Bind("username") String username
		,@Bind("password") String password
	);
}