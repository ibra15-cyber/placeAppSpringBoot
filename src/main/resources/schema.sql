create table users(username varchar_ignorecase(50) not null primary key,password varchar_ignorecase(500) not null,enabled boolean not null);
create table authorities (username varchar_ignorecase(50) not null,authority varchar_ignorecase(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);


-- -- Create the 'users' table
-- CREATE TABLE users (
--                        username CITEXT NOT NULL PRIMARY KEY,
--                        password CITEXT NOT NULL,
--                        enabled BOOLEAN NOT NULL
-- );
--
-- -- Create the 'authorities' table
-- CREATE TABLE authorities (
--                              username CITEXT NOT NULL,
--                              authority CITEXT NOT NULL,
--                              CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users(username)
-- );
--
-- -- Create a unique index on 'username' and 'authority' columns of the 'authorities' table
-- CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);
