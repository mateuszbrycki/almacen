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


CREATE TABLE `file_folder` (
  `fk_folder_id` int(11) NOT NULL,
  `fk_file_id` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`fk_folder_id`,`fk_file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;


CREATE TABLE `folder` (
  `folder_id` int(11) NOT NULL AUTO_INCREMENT,
  `folder_name` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `parent_folder_id` int(11) DEFAULT NULL,
  `physical_path` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `fk_owner_id` int(11) NOT NULL,
  `is_default_folder` int(1) DEFAULT NULL,
  PRIMARY KEY (`folder_id`),
  KEY `fk_owner_id_key_temp` (`fk_owner_id`),
  CONSTRAINT `fk_owner_id_key_temp` FOREIGN KEY (`fk_owner_id`) REFERENCES `user_account` (`user_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

CREATE TABLE logger_message (
  message_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  message TEXT NOT NULL,
  status INTEGER NOT NULL,
  audit_cd TIMESTAMP NOT NULL,
  fk_user_id INTEGER DEFAULT NULL,
  FOREIGN KEY (fk_user_id)
		REFERENCES user (user_id)
		ON UPDATE CASCADE
);

--DATA
INSERT INTO `user_role` (`role_id`, `role`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_MODERATOR'),
(3, 'ROLE_USER');

CREATE TABLE user_account_archive AS SELECT *, NOW() as action_time FROM user_account;

CREATE TABLE file (
  file_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  fk_owner_id INTEGER NOT NULL,
  name VARCHAR(255) NOT NULL,
  extension VARCHAR(255) NOT NULL,
  size INTEGER NOT NULL,
  audit_cd TIMESTAMP NOT NULL,
    FOREIGN KEY(fk_owner_id)
      REFERENCES user_account(user_id)
      ON UPDATE CASCADE
);

CREATE TABLE system_configuration (
  property_name  VARCHAR(255) PRIMARY KEY,
  property_value VARCHAR(255) NOT NULL,
  audit_cd TIMESTAMP NOT NULL
);

INSERT INTO `system_configuration` (`property_name`, `property_value`) VALUES
('file.blocked.extensions', 'exe'),
('file.maximum_size', '100000000'),
('folder.illegal_characters', '(');

--DROP TABLE user_account;
--DROP TABLE user_role;
--DROP TABLE folder;

--DROP TABLE file;
--DROP TABLE system_configuration;

