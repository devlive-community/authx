# create database user and privilege
create user bootstack identified by 'BooT@23StacK';
grant all privileges on bootstack.* to 'bootstack'@'%' identified by 'BooT@23StacK' with grant option;
flush privileges;

# create database
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
    white       boolean comment 'white list'     default true,
    path        varchar(200) comment 'interface path',
    method      varchar(200) comment 'interface method multiple method split by ,',
    active      boolean comment 'active status'  default true,
    system      boolean comment 'system default' default false,
    create_time timestamp                        default current_timestamp comment 'create time',
    update_time timestamp                        default current_timestamp comment 'update time',
    primary key (id)
) comment 'system interface table'
    default charset utf8;

insert into system_interface(name, code, description, white, path, method, active, system) value ('user interface',
                                                                                                  'ui',
                                                                                                  'user interface',
                                                                                                  true,
                                                                                                  '/api/v1/user',
                                                                                                  'get,post,put', true,
                                                                                                  true);
insert into system_interface(name, code, description, white, path, method, active, system) value ('user oauth token',
                                                                                                  'uot',
                                                                                                  'user oauth token',
                                                                                                  true,
                                                                                                  '/oauth/token',
                                                                                                  'get,post,put', true,
                                                                                                  true);

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

insert into system_log_type(name, code, description) VALUE ('User Login Log', 'ULL', 'User Login Log');

# system log table
drop table if exists system_log;
create table system_log (
    id           int auto_increment,
    remote_ip    varchar(100) comment '访问客户端地址',
    url          varchar(200) comment '访问地址',
    method       varchar(100) comment '请求方式',
    class        varchar(100) comment '访问的程序中的哪个类',
    class_method varchar(100) comment '访问的程序中的哪个类的哪个方法',
    args         text comment '请求参数',
    active       boolean comment 'active status' default true,
    create_time  timestamp                       default current_timestamp comment 'create time',
    update_time  timestamp                       default current_timestamp comment 'update time',
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

-- system role and system menu relation table
drop table if exists system_role_menu_relation;
create table system_role_menu_relation (
    system_role_id int,
    system_menu_id int
) comment 'system role and system menu relation table'
    default charset utf8;

-- system method table
drop table if exists system_method;
create table system_method (
    id          int auto_increment,
    name        varchar(100) comment 'method name',
    code        varchar(100) comment 'method code',
    description varchar(200) comment 'method description',
    method      varchar(200) comment 'interface method multiple method split by ,',
    active      boolean comment 'active status'  default true,
    system      boolean comment 'system default' default false,
    create_time timestamp                        default current_timestamp comment 'create time',
    update_time timestamp                        default current_timestamp comment 'update time',
    primary key (id)
) comment 'system method table'
    default charset utf8;

-- system interface and system method relation table
drop table if exists system_interface_method_relation;
create table system_interface_method_relation (
    system_interface_id int,
    system_method_id    int
) comment 'system interface and system method relation table'
    default charset utf8;

-- system menu and system method relation table
drop table if exists system_menu_method_relation;
create table system_menu_method_relation (
    system_menu_id   int,
    system_method_id int
) comment 'system menu and system method relation table'
    default charset utf8;

-- 系统日志与用户关系表
drop table if exists system_log_users_relation;
create table system_log_users_relation (
    system_log_id int comment '系统日志表唯一标志,唯一主键',
    users_id      int comment '用户表唯一标志,唯一主键'
) comment '系统日志与用户关系表'
    default charset utf8;
