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
    active      boolean comment 'active status' default true,
    create_time timestamp                       default current_timestamp comment 'create time',
    update_time timestamp                       default current_timestamp comment 'update time',
    primary key (id)
) comment 'system interface table'
    default charset utf8;
