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

insert into system_interface(name, code, description, white, path, method, active) value ('user interface', 'ui',
                                                                                          'user interface', true,
                                                                                          '/api/v1/user',
                                                                                          'get,post,put', true);
insert into system_interface(name, code, description, white, path, method, active) value ('user oauth token', 'uot',
                                                                                          'user oauth token', true,
                                                                                          '/oauth/token',
                                                                                          'get,post,put', true);

# system menu type table
drop table if exists system_menu_type;
create table system_menu_type (
    id          int auto_increment,
    name        varchar(100) comment 'menu type name',
    code        varchar(100) comment 'menu type code',
    description varchar(200) comment 'menu type description',
    active      boolean comment 'active status' default true,
    create_time timestamp                       default current_timestamp comment 'create time',
    update_time timestamp                       default current_timestamp comment 'update time',
    primary key (id)
) comment 'system menu type table'
    default charset utf8;

insert into system_menu_type(name, code, description) value ('button', 'button', 'button');
insert into system_menu_type(name, code, description) value ('api', 'api', 'api');
insert into system_menu_type(name, code, description) value ('menu', 'menu', 'menu');

-- system role table
drop table if exists system_role;
create table system_role (
    id          int auto_increment,
    name        varchar(100) comment 'role name',
    code        varchar(50) comment 'role code',
    description varchar(100) comment 'role description',
    active      boolean comment 'active status' default true,
    create_time timestamp                       default current_timestamp comment 'create time',
    update_time timestamp                       default current_timestamp comment 'update time',
    primary key (id)
) comment 'system role table'
    default charset utf8;

insert into system_role(name, code, description) value ('super user', 'su', 'super user');
insert into system_role(name, code, description) value ('default user', 'du', 'default user');

-- user and system role relation table
drop table if exists users_system_role_relation;
create table users_system_role_relation (
    users_id       int,
    system_role_id int
) comment 'user and system role relation table'
    default charset utf8;

insert into users_system_role_relation(users_id, system_role_id) value (1, 1);

# system menu table
drop table if exists system_menu;
create table system_menu (
    id          int auto_increment,
    name        varchar(100) comment 'system menu name',
    code        varchar(100) comment 'system menu code',
    url         varchar(200) comment 'system menu url',
    icon        varchar(100) comment 'system menu icon',
    sorted      int comment 'system menu sort',
    level       int comment 'system menu level',
    tips        varchar(200) comment 'system menu tips description',
    newd        boolean comment 'system menu is new ?' default true,
    parent      int comment 'system menu parent menu id',
    method      varchar(200) comment 'system menu method multiple method split by ,',
    description varchar(100) comment 'system menu description',
    active      boolean comment 'active status'        default true,
    create_time timestamp                              default current_timestamp comment 'create time',
    update_time timestamp                              default current_timestamp comment 'update time',
    primary key (id)
) comment 'system menu table'
    default charset utf8;

-- system menu and system menu type relation table
drop table if exists system_menu_type_relation;
create table system_menu_type_relation (
    system_menu_id      int,
    system_menu_type_id int
) comment 'system menu and system menu type relation table'
    default charset utf8;

-- system menu and system role relation table
drop table if exists system_menu_role_relation;
create table system_menu_role_relation (
    system_menu_id int,
    system_role_id int
) comment 'system menu and system role relation table'
    default charset utf8;

# system log type table
drop table if exists system_log_type;
create table system_log_type (
    id          int auto_increment,
    name        varchar(100) comment 'system log type name',
    code        varchar(100) comment 'system log type code',
    description varchar(200) comment 'system log type description',
    active      boolean comment 'active status' default true,
    create_time timestamp                       default current_timestamp comment 'create time',
    update_time timestamp                       default current_timestamp comment 'update time',
    primary key (id)
) comment 'system log type table'
    default charset utf8;

# system log table
drop table if exists system_log;
create table system_log (
    id          int auto_increment,
    title       varchar(100) comment 'system log title',
    url         varchar(200) comment 'system log url',
    method      varchar(100) comment 'system log method',
    active      boolean comment 'active status' default true,
    create_time timestamp                       default current_timestamp comment 'create time',
    update_time timestamp                       default current_timestamp comment 'update time',
    primary key (id)
) comment 'system log table'
    default charset utf8;

-- system log and system log type relation table
drop table if exists system_log_type_relation;
create table system_log_type_relation (
    system_log_id      int,
    system_log_type_id int
) comment 'system log and system log type relation table'
    default charset utf8;

