create database bootstack default charset utf8;

# user table
drop table if exists users;
create table users (
    id          int auto_increment,
    name        varchar(100) comment 'user name',
    password    varchar(200) comment 'user password',
    avatar      varchar(100) comment 'user avatar',
    active      boolean comment 'active status' default true,
    create_time timestamp                       default current_timestamp comment 'create time',
    update_time timestamp                       default current_timestamp comment 'update time',
    primary key (id)
) comment 'user table'
    default charset utf8;

insert into users(name, password, avatar, active, create_time, update_time)
values ('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', null, '1', '2019-01-26 01:05:36',
        '2019-01-26 01:05:36');

# system interface table
drop table if exists system_interface;
create table system_interface (
    id          int auto_increment,
    name        varchar(100) comment 'interface name',
    code        varchar(100) comment 'interface code',
    description varchar(200) comment 'interface description',
    white       boolean comment 'white list'    default true,
    path        varchar(200) comment 'interface path',
    method      varchar(200) comment 'interface method multiple method split by ,',
    active      boolean comment 'active status' default true,
    create_time timestamp                       default current_timestamp comment 'create time',
    update_time timestamp                       default current_timestamp comment 'update time',
    primary key (id)
) comment 'system interface table'
    default charset utf8;

insert into system_interface(name, code, description, white, path, method, active) value ('User Interface', 'UI',
                                                                                          'User Interface', true,
                                                                                          '/api/v1/user',
                                                                                          'get,post,put', true);
insert into system_interface(name, code, description, white, path, method, active) value ('User Oauth Token', 'UOT',
                                                                                          'User Oauth Token', true,
                                                                                          '/oauth/token',
                                                                                          'get,post,put', true);
insert into system_interface(name, code, description, white, path, method, active) value ('User Register', 'UR',
                                                                                          'User Register Interface',
                                                                                          true,
                                                                                          '/api/v1/user',
                                                                                          'post', true);

# system interface type table
drop table if exists system_interface_type;
create table system_interface_type (
    id          int auto_increment,
    name        varchar(100) comment 'interface type name',
    code        varchar(100) comment 'interface type code',
    description varchar(200) comment 'interface type description',
    active      boolean comment 'active status' default true,
    create_time timestamp                       default current_timestamp comment 'create time',
    update_time timestamp                       default current_timestamp comment 'update time',
    primary key (id)
) comment 'system interface type table'
    default charset utf8;

insert into system_interface_type(name, code, description) value ('Button', 'button', 'button');
insert into system_interface_type(name, code, description) value ('API', 'api', 'api');
insert into system_interface_type(name, code, description) value ('Menu', 'menu', 'menu');

-- system role table
DROP TABLE IF EXISTS system_role;
CREATE TABLE system_role (
    id          INT AUTO_INCREMENT,
    name        VARCHAR(100) COMMENT 'role name',
    code        VARCHAR(50) COMMENT 'role code',
    description VARCHAR(100) COMMENT 'role description',
    active      BOOLEAN COMMENT 'active status' DEFAULT TRUE,
    create_time timestamp                       default current_timestamp comment 'create time',
    update_time timestamp                       default current_timestamp comment 'update time',
    PRIMARY KEY (id)
) COMMENT 'system role table'
    DEFAULT CHARSET utf8;

insert into system_role(name, code, description) VALUE ('Super User', 'SU', 'Super User');
insert into system_role(name, code, description) VALUE ('Default User', 'DU', 'Default User');

-- user and system role relation table
DROP TABLE IF EXISTS users_system_role_relation;
CREATE TABLE users_system_role_relation (
    users_id       INT,
    system_role_id INT
) COMMENT 'user and system role relation table'
    DEFAULT CHARSET utf8;

insert into users_system_role_relation(users_id, system_role_id) VALUE (1, 1);
