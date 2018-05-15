INSERT INTO role (id, role_name, description, audit_user_update, audit_last_update)
  VALUES (1, 'STANDARD_USER', 'Standard User - Has no admin rights', 'TESTING-R1', '2018-01-01 00:00:00');
INSERT INTO role (id, role_name, description, audit_user_update, audit_last_update)
  VALUES (2, 'ADMIN_USER', 'Admin User - Has permission to perform admin tasks', 'TESTING-R2', '2018-01-01 00:00:00');

INSERT INTO user (id, email, password, first_name, last_name, active, audit_user_update, audit_last_update)
  VALUES (1, 'john@doe.com', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', 'John', 'Doe', 'true', 'TESTING', '2018-01-01 00:00:00');

INSERT INTO user (id, email, password, first_name, last_name, active, audit_user_update, audit_last_update)
VALUES (2, 'admin@admin.com', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', 'Admin', 'Admin', 'true', 'TESTING', '2018-01-01 00:00:00');

INSERT INTO user (id, email, password, first_name, last_name, active, audit_user_update, audit_last_update)
VALUES (3, 'adminDisable@admin.com', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', 'Admin', 'Disable', 'false', 'TESTING', '2018-01-01 00:00:00');

INSERT INTO user_role(user_id, role_id) VALUES(1,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,2);
INSERT INTO user_role(user_id, role_id) VALUES(3,2);

