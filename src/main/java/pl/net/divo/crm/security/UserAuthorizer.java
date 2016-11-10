package pl.net.divo.crm.security;

import io.dropwizard.auth.Authorizer;
import pl.net.divo.crm.dao.RoleDao;
import pl.net.divo.crm.model.User;

public class UserAuthorizer implements Authorizer<User> {
	private final RoleDao roleDao;
	
	public UserAuthorizer(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public boolean authorize(User user, String role) {
		return roleDao.checkRoleByUserIdAndRole(user.getId(), role);
	}
}