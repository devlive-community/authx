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