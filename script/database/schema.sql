CREATE TABLE `users`
(
    `id`          int       NOT NULL AUTO_INCREMENT,
    `name`        varchar(100)   DEFAULT NULL,
    `password`    varchar(200)   DEFAULT NULL,
    `avatar`      varchar(100)   DEFAULT NULL,
    `active`      tinyint        DEFAULT NULL,
    `create_time` timestamp NULL DEFAULT NULL,
    `update_time` timestamp NULL DEFAULT NULL,
    `locked`      tinyint        DEFAULT NULL,
    `is_system`    tinyint        DEFAULT NULL,
    `email`       varchar(100)   DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
    COMMENT = '用户数据表';
