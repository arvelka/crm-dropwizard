CRM system

# sql create commands
CREATE TABLE session (
	access_token VARCHAR(23) NOT NULL PRIMARY KEY,
	identity VARCHAR(64) NOT NULL,
	created TIMESTAMP default current_timestamp
);

CREATE TABLE user (
	username VARCHAR(64) NOT NULL,
	password VARCHAR(64) NOT NULL,
	display_name VARCHAR(64) NOT NULL,
	created TIMESTAMP default current_timestamp
);

