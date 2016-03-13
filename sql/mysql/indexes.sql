CREATE INDEX user_account_mail_idx
  ON user_account (mail);

CREATE INDEX user_account_username_idx
  ON user_account (username);

CREATE INDEX user_account_id_username_idx
  ON user_account (user_id, username);