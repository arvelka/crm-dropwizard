package pl.net.divo.crm.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import pl.net.divo.crm.model.User;

public class UserMapper implements ResultSetMapper<User> {

	@Override
	public User map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
		User user = new User();

		user.setUsername(resultSet.getString("username"));
		user.setPassword(resultSet.getString("password"));
		user.setName(resultSet.getString("display_name"));

		return user;
	}
}