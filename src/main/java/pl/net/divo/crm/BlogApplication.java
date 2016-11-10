package pl.net.divo.crm;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.skife.jdbi.v2.DBI;

import pl.net.divo.crm.dao.SessionDao;
import pl.net.divo.crm.dao.UserDao;
import pl.net.divo.crm.resources.SessionResource;

public class BlogApplication extends Application<BlogConfiguration> {
	public static String APPLICATION_NAME = "test-crm";
	
	public static void main(String[] args) {
		try {
			new BlogApplication().run(args); 
		} catch (Exception e) {
			System.out.format("Error was occured: %s\n", e.getMessage());
		}
	}
	
	@Override
	public String getName() {
		return BlogApplication.APPLICATION_NAME;
	}
	
	@Override
	public void initialize(Bootstrap<BlogConfiguration> bootstrap) {
	}
	
	@Override
	public void run(BlogConfiguration config, Environment environment) throws Exception {
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "mysql");
		final UserDao userDao = jdbi.onDemand(UserDao.class);
		final SessionDao sessionDao = jdbi.onDemand(SessionDao.class);

		environment.addResource(new SessionResource(userDao, sessionDao));
	}
}
