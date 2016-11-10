package pl.net.divo.crm;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.LinkedList;
import java.util.List;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;

import pl.net.divo.crm.dao.RoleDao;
import pl.net.divo.crm.dao.UserDao;
import pl.net.divo.crm.model.User;
import pl.net.divo.crm.resources.RoleResource;
import pl.net.divo.crm.resources.UserResource;
import pl.net.divo.crm.security.UserAuthenticator;
import pl.net.divo.crm.security.UserAuthorizer;

public class CrmApplication extends Application<CrmConfiguration> {
	public static String APPLICATION_NAME = "test-crm";
	public static String BASIC_REALM_NAME = "SECRET";
	
	public static void main(String[] args) {
		try {
			new CrmApplication().run(args);
		} catch (Exception e) {
			System.out.format("Error was occured: %s\n", e.getMessage());
		}
	}
	
	@Override
	public String getName() {
		return CrmApplication.APPLICATION_NAME;
	}
	
	@Override
	public void initialize(Bootstrap<CrmConfiguration> bootstrap) {
	}
	
	@Override
	public void run(CrmConfiguration config, Environment environment) throws Exception {
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "mysql");

		final UserDao userDao = jdbi.onDemand(UserDao.class);
		final RoleDao roleDao = jdbi.onDemand(RoleDao.class);
		
		List<Object> allRegisters = new LinkedList<Object>();
		allRegisters.add(new UserResource(userDao));
		allRegisters.add(new RoleResource(roleDao));
		allRegisters.add(getSecurityResource(environment, jdbi));
		allRegisters.add(RolesAllowedDynamicFeature.class);
		allRegisters.add(new AuthValueFactoryProvider.Binder<User>(User.class));
		
		registerAll(environment, allRegisters);
	}
	
	private void registerAll(Environment environment, List<Object> registers) {
		for (Object register : registers) {
			final Object finalRegister = register;
			environment.jersey().register(finalRegister);
		}
	}
	
	private AuthDynamicFeature getSecurityResource(Environment environment, DBI jdbi) {
		final UserDao userDao = jdbi.onDemand(UserDao.class);
		final RoleDao roleDao = jdbi.onDemand(RoleDao.class);

		return new AuthDynamicFeature(
			new BasicCredentialAuthFilter.Builder<User>()
				.setAuthenticator(new UserAuthenticator(userDao))
				.setAuthorizer(new UserAuthorizer(roleDao))
				.setRealm(CrmApplication.BASIC_REALM_NAME)
				.buildAuthFilter());
	}
}
