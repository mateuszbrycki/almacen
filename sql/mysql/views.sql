CREATE VIEW user_account_view AS
    SELECT ua.*, ur.role
    FROM user_account ua
    JOIN user_role ur ON ua.fk_role_id = ur.role_id;