--TABLES

CREATE TABLE user_role (
	role_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	role VARCHAR(255) NOT NULL
);

CREATE TABLE user_account (
	user_id INTEGER PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(255) NOT NULL,
	fk_role_id INTEGER NOT NULL,
	mail VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	is_active BOOLEAN DEFAULT TRUE,
	audit_cd TIMESTAMP NOT NULL,
	audit_md DATE DEFAULT NULL,
	FOREIGN KEY (fk_role_id)
		REFERENCES user_role (role_id)
		ON UPDATE CASCADE
);


--DATA
INSERT INTO `user_role` (`id`, `role_id`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_MODERATOR'),
(3, 'ROLE_USER');

CREATE TABLE user_account_archive AS SELECT *, NOW() as action_time FROM user_account;

--DROP TABLE user_account;
--DROP TABLE user_role;


