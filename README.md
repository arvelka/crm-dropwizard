CRM system

# sql create commands
CREATE TABLE session (
	access_token VARCHAR(23) NOT NULL PRIMARY KEY,
	identity VARCHAR(64) NOT NULL,
	created TIMESTAMP default current_timestamp
);

CREATE TABLE user (
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	username VARCHAR(64) NOT NULL,
	password VARCHAR(64) NOT NULL,
	display_name VARCHAR(64) NOT NULL,
	created TIMESTAMP default current_timestamp
);

CREATE TABLE user_role (
	user_id INT,
	role_id INT,
	PRIMARY KEY (user_id, role_id)
)

CREATE TABLE role (
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	name VARCHAR(64) NOT NULL,
);

# curls
curl -d'{"username":"divo", "password": "test", "name":"divo"}' -H"Content-type: application/json" -XPOST localhost:8080/user
curl -d'{"name":"user"}' -H"Content-type: application/json" -XPOST localhost:8080/role
curl -d'{"name":"admin"}' -H"Content-type: application/json" -XPOST localhost:8080/role

