package pl.net.divo.crm.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface SessionDao {
	@SqlUpdate("insert into session (access_token, username, created) values (:accessToken, :username, :created)")
	void insert(
		@Bind("accessToken") String accessToken
		,@Bind("username") String username
		,@Bind("created") java.util.Date created
	);
}