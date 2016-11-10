package pl.net.divo.crm.security;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;

import pl.net.divo.crm.dao.UserDao;
import pl.net.divo.crm.model.User;


public class UserAuthenticator implements Authenticator<BasicCredentials, User> {
	private final UserDao userDao;
	
	public UserAuthenticator(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
		User user = userDao.findUserByUsernameAndPassword(
			credentials.getUsername(),
			credentials.getPassword()
		);

		if (user.getPassword().equals(credentials.getPassword())) {
			return Optional.of(user);
		}
		return Optional.empty();
	}
}