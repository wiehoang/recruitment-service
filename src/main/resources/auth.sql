DROP TABLE if EXISTS role;
DROP TABLE if EXISTS user;
DROP TABLE if EXISTS user_roles;

CREATE TABLE role (
    id bigint not null auto_increment,
    name varchar(255),
    primary key (id)
                  );

CREATE TABLE user_roles (
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id)
                        );

INSERT INTO role (id, name) VALUES (1, 'ADMIN');
INSERT INTO role (id, name) VALUES (2, 'EMPLOYER');
INSERT INTO role (id, name) VALUES (3, 'SEEKER');