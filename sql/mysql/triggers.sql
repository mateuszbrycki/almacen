--DROP TRIGGER deleted_trigger_folder ON folder;
DELIMITER $$
CREATE TRIGGER deleted_trigger_user_account
AFTER DELETE ON user_account
    FOR EACH ROW BEGIN
      INSERT INTO user_account_archive VALUES(
        OLD.user_id,
        OLD.username,
        OLD.fk_role_id,
        OLD.mail,
        OLD.password,
        OLD.is_active,
        OLD.audit_cd,
        OLD.audit_md,
        NOW()
      );
    END$$
DELIMITER ;

--DROP TRIGGER audit_cd_trigger_folder ON folder;
DELIMITER $$
CREATE TRIGGER audit_cd_trigger_folder
BEFORE INSERT ON user_account
    FOR EACH ROW BEGIN
      SET NEW.audit_cd = NOW();
    END$$
DELIMITER ;

--DROP TRIGGER audit_md_trigger_folder ON folder;
DELIMITER $$
CREATE TRIGGER audit_md_trigger_folder
BEFORE UPDATE ON user_account
    FOR EACH ROW BEGIN
      SET NEW.audit_md = NOW();
    END$$
DELIMITER ;