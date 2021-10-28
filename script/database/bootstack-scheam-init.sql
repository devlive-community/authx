/*
 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : bootstack

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 28/10/2021 14:01:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE bootstack;

USE bootstack;

-- ----------------------------
-- Table structure for icon
-- ----------------------------
DROP TABLE IF EXISTS `icon`;
CREATE TABLE `icon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '图标名称',
  `code` varchar(100) DEFAULT NULL COMMENT '图标编码',
  `zh_name` varchar(100) DEFAULT NULL COMMENT '图标中文名',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `description` varchar(200) DEFAULT NULL COMMENT '图标描述',
  `active` tinyint(1) DEFAULT '1' COMMENT '激活状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='ICON图标表';

-- ----------------------------
-- Records of icon
-- ----------------------------
BEGIN;
INSERT INTO `icon` VALUES (1, '3d-rotation', 'zmdi zmdi-3d-rotation', NULL, NULL, '3d-rotation', NULL, '2021-10-28 10:52:51', '2021-10-28 10:52:51');
COMMIT;

-- ----------------------------
-- Table structure for icon_type
-- ----------------------------
DROP TABLE IF EXISTS `icon_type`;
CREATE TABLE `icon_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '图标类型名称',
  `code` varchar(100) DEFAULT NULL COMMENT '图标类型编码',
  `description` varchar(200) DEFAULT NULL COMMENT '图标类型描述',
  `active` tinyint(1) DEFAULT '1' COMMENT '激活状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='ICON图标类型表';

-- ----------------------------
-- Records of icon_type
-- ----------------------------
BEGIN;
INSERT INTO `icon_type` VALUES (1, 'Font Awesome', 'FONTAWESOME', 'Font Awesome', 1, '2021-10-28 13:50:27', '2021-10-28 13:50:27');
INSERT INTO `icon_type` VALUES (2, 'Material Design Iconic Font', 'MATERIALDESIGNICONICFONT', 'Material Design Iconic Font', 1, '2021-10-28 13:50:30', '2021-10-28 13:50:30');
COMMIT;

-- ----------------------------
-- Table structure for icon_type_icon_relation
-- ----------------------------
DROP TABLE IF EXISTS `icon_type_icon_relation`;
CREATE TABLE `icon_type_icon_relation` (
  `icon_id` int(11) DEFAULT NULL COMMENT '图标表唯一标志,唯一主键',
  `icon_type_id` int(11) DEFAULT NULL COMMENT '图标类型表唯一标志,唯一主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图标与图标类型关系表';

-- ----------------------------
-- Records of icon_type_icon_relation
-- ----------------------------
BEGIN;
INSERT INTO `icon_type_icon_relation` VALUES (1, 2);
COMMIT;

-- ----------------------------
-- Table structure for icon_usage
-- ----------------------------
DROP TABLE IF EXISTS `icon_usage`;
CREATE TABLE `icon_usage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '图标类型名称',
  `code` varchar(100) DEFAULT NULL COMMENT '图标类型编码',
  `description` varchar(200) DEFAULT NULL COMMENT '图标类型描述',
  `active` tinyint(1) DEFAULT '1' COMMENT '激活状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='ICON图标用途表';

-- ----------------------------
-- Records of icon_usage
-- ----------------------------
BEGIN;
INSERT INTO `icon_usage` VALUES (1, '网站', 'WZ', '网站使用图标', 1, '2021-10-28 13:49:43', '2021-10-28 13:49:43');
INSERT INTO `icon_usage` VALUES (2, '通知', 'TZ', '通知使用图标', 1, '2021-10-28 13:49:53', '2021-10-28 13:49:53');
COMMIT;

-- ----------------------------
-- Table structure for icon_usage_icon_relation
-- ----------------------------
DROP TABLE IF EXISTS `icon_usage_icon_relation`;
CREATE TABLE `icon_usage_icon_relation` (
  `icon_id` int(11) DEFAULT NULL COMMENT '图标表唯一标志,唯一主键',
  `icon_usage_id` int(11) DEFAULT NULL COMMENT '图标用途表唯一标志,唯一主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图标与图标用途关系表';

-- ----------------------------
-- Records of icon_usage_icon_relation
-- ----------------------------
BEGIN;
INSERT INTO `icon_usage_icon_relation` VALUES (1, 1);
COMMIT;

-- ----------------------------
-- Table structure for system_interface
-- ----------------------------
DROP TABLE IF EXISTS `system_interface`;
CREATE TABLE `system_interface` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT 'interface name',
  `code` varchar(100) DEFAULT NULL COMMENT 'interface code',
  `description` varchar(200) DEFAULT NULL COMMENT 'interface description',
  `white` tinyint(1) DEFAULT '1' COMMENT 'white list',
  `path` varchar(200) DEFAULT NULL COMMENT 'interface path',
  `method` varchar(200) DEFAULT NULL COMMENT 'interface method multiple method split by ,',
  `active` tinyint(1) DEFAULT '1' COMMENT 'active status',
  `system` tinyint(1) DEFAULT '0' COMMENT 'system default',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='system interface table';

-- ----------------------------
-- Records of system_interface
-- ----------------------------
BEGIN;
INSERT INTO `system_interface` VALUES (1, 'user interface', 'ui', 'user interface', 1, '/api/v1/user', 'get,post,put', 1, 1, '2021-10-27 14:12:19', '2021-10-27 14:12:19');
INSERT INTO `system_interface` VALUES (2, '用户登录授权接口', 'YHDLSQJK', '用于用户登录授权', 1, '/oauth/token', 'get,post,put', 1, 1, '2021-10-28 13:51:33', '2021-10-28 13:51:33');
INSERT INTO `system_interface` VALUES (3, 'user interface', 'ui', 'user interface', 1, '/api/v1/user/register', 'get,post,put', 1, 1, '2021-10-27 14:12:19', '2021-10-27 14:12:19');
COMMIT;

-- ----------------------------
-- Table structure for system_interface_method_relation
-- ----------------------------
DROP TABLE IF EXISTS `system_interface_method_relation`;
CREATE TABLE `system_interface_method_relation` (
  `system_interface_id` int(11) DEFAULT NULL,
  `system_method_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='system interface and system method relation table';

-- ----------------------------
-- Records of system_interface_method_relation
-- ----------------------------
BEGIN;
INSERT INTO `system_interface_method_relation` VALUES (3, 1);
INSERT INTO `system_interface_method_relation` VALUES (1, 2);
INSERT INTO `system_interface_method_relation` VALUES (2, 1);
COMMIT;

-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `remote_ip` varchar(100) DEFAULT NULL COMMENT '访问客户端地址',
  `url` varchar(200) DEFAULT NULL COMMENT '访问地址',
  `method` varchar(100) DEFAULT NULL COMMENT '请求方式',
  `class` varchar(100) DEFAULT NULL COMMENT '访问的程序中的哪个类',
  `class_method` varchar(100) DEFAULT NULL COMMENT '访问的程序中的哪个类的哪个方法',
  `args` text COMMENT '请求参数',
  `active` tinyint(1) DEFAULT '1' COMMENT 'active status',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='system log table';

-- ----------------------------
-- Records of system_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for system_log_type
-- ----------------------------
DROP TABLE IF EXISTS `system_log_type`;
CREATE TABLE `system_log_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT 'system log type name',
  `code` varchar(100) DEFAULT NULL COMMENT 'system log type code',
  `description` varchar(200) DEFAULT NULL COMMENT 'system log type description',
  `active` tinyint(1) DEFAULT '1' COMMENT 'active status',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='system log type table';

-- ----------------------------
-- Records of system_log_type
-- ----------------------------
BEGIN;
INSERT INTO `system_log_type` VALUES (1, 'User Login Log', 'ULL', 'User Login Log', 1, '2021-10-27 14:13:15', '2021-10-27 14:13:15');
INSERT INTO `system_log_type` VALUES (2, '系统日志', 'XTRZ', '系统日志', NULL, '2021-10-28 12:20:51', '2021-10-28 12:20:51');
COMMIT;

-- ----------------------------
-- Table structure for system_log_type_relation
-- ----------------------------
DROP TABLE IF EXISTS `system_log_type_relation`;
CREATE TABLE `system_log_type_relation` (
  `system_log_id` int(11) DEFAULT NULL,
  `system_log_type_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='system log and system log type relation table';

-- ----------------------------
-- Records of system_log_type_relation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for system_log_users_relation
-- ----------------------------
DROP TABLE IF EXISTS `system_log_users_relation`;
CREATE TABLE `system_log_users_relation` (
  `system_log_id` int(11) DEFAULT NULL COMMENT '系统日志表唯一标志,唯一主键',
  `users_id` int(11) DEFAULT NULL COMMENT '用户表唯一标志,唯一主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志与用户关系表';

-- ----------------------------
-- Records of system_log_users_relation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT 'system menu name',
  `code` varchar(100) DEFAULT NULL COMMENT 'system menu code',
  `url` varchar(200) DEFAULT NULL COMMENT 'system menu url',
  `icon` varchar(100) DEFAULT NULL COMMENT 'system menu icon',
  `sorted` int(11) DEFAULT '0' COMMENT 'system menu sort',
  `level` int(11) DEFAULT '0' COMMENT 'system menu level',
  `tips` varchar(200) DEFAULT NULL COMMENT 'system menu tips description',
  `newd` tinyint(1) DEFAULT '1' COMMENT 'system menu is new ?',
  `parent` int(11) unsigned DEFAULT '0' COMMENT 'system menu parent menu id',
  `method` varchar(200) DEFAULT NULL COMMENT 'system menu method multiple method split by ,',
  `description` varchar(100) DEFAULT NULL COMMENT 'system menu description',
  `active` tinyint(1) DEFAULT '1' COMMENT 'active status',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='system menu table';

-- ----------------------------
-- Records of system_menu
-- ----------------------------
BEGIN;
INSERT INTO `system_menu` VALUES (1, '仪表盘概览接口', NULL, '/api/v1/overview', NULL, 0, NULL, NULL, 1, 0, 'GET', NULL, 1, '2021-10-27 22:41:06', '2021-10-27 22:41:06');
INSERT INTO `system_menu` VALUES (2, '用户详情接口', NULL, '/api/v1/user/info/*', NULL, 0, NULL, NULL, 1, 0, NULL, NULL, 1, '2021-10-27 22:52:15', '2021-10-27 22:52:15');
INSERT INTO `system_menu` VALUES (3, '系统菜单接口', NULL, '/api/v1/system/role/menu', NULL, 0, NULL, NULL, 1, 0, NULL, NULL, 1, '2021-10-27 22:55:45', '2021-10-27 22:55:45');
INSERT INTO `system_menu` VALUES (4, '仪表盘', NULL, '/dashboard', NULL, 0, NULL, NULL, 1, 0, NULL, NULL, 1, '2021-10-27 23:00:55', '2021-10-27 23:00:55');
INSERT INTO `system_menu` VALUES (5, '菜单管理', NULL, '/system', NULL, 0, NULL, NULL, 1, 0, NULL, NULL, 1, '2021-10-27 23:13:34', '2021-10-27 23:13:34');
INSERT INTO `system_menu` VALUES (6, '菜单接口', NULL, '/api/v1/system/menu', NULL, 1, NULL, NULL, 1, 0, NULL, NULL, 1, '2021-10-27 23:15:28', '2021-10-27 23:15:28');
INSERT INTO `system_menu` VALUES (7, '菜单管理', NULL, '/system/menu', NULL, 2, NULL, NULL, 1, 5, NULL, NULL, 1, '2021-10-27 23:18:12', '2021-10-27 23:18:12');
INSERT INTO `system_menu` VALUES (8, '菜单类型接口', NULL, '/api/v1/system/menu/type', NULL, 3, NULL, NULL, 1, 0, NULL, NULL, 1, '2021-10-28 10:11:32', '2021-10-28 10:11:32');
INSERT INTO `system_menu` VALUES (9, '图标接口', NULL, '/api/v1/icon', NULL, 0, NULL, NULL, 1, 0, NULL, NULL, 1, '2021-10-28 10:19:05', '2021-10-28 10:19:05');
INSERT INTO `system_menu` VALUES (10, '图标管理', NULL, '/iconManager', NULL, 0, NULL, NULL, 1, 0, NULL, NULL, 1, '2021-10-28 10:32:15', '2021-10-28 10:32:15');
INSERT INTO `system_menu` VALUES (11, '图标管理', NULL, '/iconManager/icon', NULL, 0, NULL, NULL, 1, 10, NULL, NULL, 1, '2021-10-28 10:37:20', '2021-10-28 10:37:20');
INSERT INTO `system_menu` VALUES (12, '图标类型接口', NULL, '/api/v1/icon/type', NULL, 0, NULL, NULL, 1, 0, NULL, NULL, 1, '2021-10-28 10:45:44', '2021-10-28 10:45:44');
INSERT INTO `system_menu` VALUES (13, '图标使用位置接口', NULL, '/api/v1/icon/usage', NULL, 0, NULL, NULL, 1, 0, NULL, NULL, 1, '2021-10-28 10:47:42', '2021-10-28 10:47:42');
INSERT INTO `system_menu` VALUES (14, '菜单父类接口', NULL, '/api/v1/system/menu/parent', NULL, 0, NULL, NULL, 1, 0, NULL, NULL, 1, '2021-10-28 10:55:15', '2021-10-28 10:55:15');
INSERT INTO `system_menu` VALUES (15, '请求类型接口', NULL, '/api/v1/system/method', NULL, 0, NULL, NULL, 1, 0, NULL, NULL, 1, '2021-10-28 11:06:40', '2021-10-28 11:06:40');
INSERT INTO `system_menu` VALUES (16, '菜单类型管理', 'CDLXGL', '/system/menu-type', NULL, 1, 1, '菜单类型', 0, 5, NULL, '管理菜单相关类型', 1, '2021-10-28 11:07:23', '2021-10-28 11:07:23');
INSERT INTO `system_menu` VALUES (17, '系统管理', 'XTGL', '/system', NULL, 0, NULL, NULL, 1, 0, NULL, NULL, 1, '2021-10-28 11:27:55', '2021-10-28 11:27:55');
INSERT INTO `system_menu` VALUES (18, '权限管理', 'QXGL', '/system/role', NULL, 0, NULL, '系统管理', 0, 17, NULL, '系统管理', 1, '2021-10-28 11:30:12', '2021-10-28 11:30:12');
INSERT INTO `system_menu` VALUES (19, '系统权限接口', 'XTQXJK', '/api/v1/system/role', NULL, 0, NULL, '权限接口', 0, 8, NULL, '权限接口', 1, '2021-10-28 11:32:16', '2021-10-28 11:32:16');
INSERT INTO `system_menu` VALUES (20, '权限树形菜单接口', 'QXSXCDJK', '/api/v1/system/role/menu/tree', NULL, 0, NULL, '权限树形菜单接口', 0, 8, NULL, '权限树形菜单接口', 1, '2021-10-28 11:34:06', '2021-10-28 11:34:06');
INSERT INTO `system_menu` VALUES (21, '接口白名单管理', 'BMDJK', '/system/settings/interface', NULL, 0, NULL, '白名单接口', 0, 17, NULL, '白名单接口', 1, '2021-10-28 11:55:45', '2021-10-28 11:55:45');
INSERT INTO `system_menu` VALUES (22, '白名单接口', 'BMDJK', '/api/v1/system/interface', NULL, 0, NULL, '白名单接口', 0, 3, NULL, '白名单接口', 1, '2021-10-28 11:57:10', '2021-10-28 11:57:10');
INSERT INTO `system_menu` VALUES (23, '请求方式管理', 'QQFSGL', '/system/settings/method', NULL, 0, NULL, '请求方式管理', 0, 17, NULL, '请求方式管理', 1, '2021-10-28 11:59:01', '2021-10-28 11:59:01');
INSERT INTO `system_menu` VALUES (24, '用户管理', 'YHGL', '/userManager', NULL, 0, NULL, '用户管理', 0, 0, NULL, '用户管理', 1, '2021-10-28 12:03:45', '2021-10-28 12:03:45');
INSERT INTO `system_menu` VALUES (25, '用户管理', 'YHGL', '/userManager/users', NULL, 0, NULL, '用户管理', 0, 24, NULL, '用户管理', 1, '2021-10-28 12:04:57', '2021-10-28 12:04:57');
INSERT INTO `system_menu` VALUES (26, '用户组管理', 'YHZGL', '/userManager/menu-type', NULL, 0, NULL, '用户组管理', 0, 24, NULL, '用户组管理', 1, '2021-10-28 12:08:37', '2021-10-28 12:08:37');
INSERT INTO `system_menu` VALUES (27, '图标类型管理', 'TBLXGL', '/iconManager/icon/type', NULL, 0, NULL, '图标类型管理', 0, 10, NULL, '图标类型管理', 1, '2021-10-28 12:11:09', '2021-10-28 12:11:09');
INSERT INTO `system_menu` VALUES (28, '图标用途管理', 'TBYTGL', '/iconManager/icon/usage', NULL, 0, NULL, '图标用途管理', 0, 10, NULL, '图标用途管理', 1, '2021-10-28 12:11:44', '2021-10-28 12:11:44');
INSERT INTO `system_menu` VALUES (29, '日志管理', 'RZGL', '/logManager/logs', NULL, 0, NULL, '日志管理', 0, 0, NULL, '日志管理', 1, '2021-10-28 12:13:07', '2021-10-28 12:13:07');
INSERT INTO `system_menu` VALUES (30, '日志管理', 'RZGL', '/logManager/logs', NULL, 0, NULL, '日志管理', 0, 29, NULL, '日志管理', 1, '2021-10-28 12:13:41', '2021-10-28 12:13:41');
INSERT INTO `system_menu` VALUES (31, '日志类型管理', 'RZLXGL', '/logManager/logs/type', NULL, 0, NULL, '日志类型管理', 0, 29, NULL, '日志类型管理', 1, '2021-10-28 12:14:08', '2021-10-28 12:14:08');
INSERT INTO `system_menu` VALUES (32, '日志接口', 'RZJK', '/api/v1/system/log', NULL, 0, NULL, '日志接口', 0, 0, NULL, '日志接口', 1, '2021-10-28 12:16:23', '2021-10-28 12:16:23');
INSERT INTO `system_menu` VALUES (33, '日志类型接口', 'RZLXJK', '/api/v1/system/log/type', NULL, 0, NULL, '日志类型接口', 0, 32, NULL, '日志类型接口', 1, '2021-10-28 12:17:11', '2021-10-28 12:17:11');
INSERT INTO `system_menu` VALUES (34, '日志详情接口', 'RZXQJK', '/api/v1/system/log/details', NULL, 0, NULL, '日志详情接口', 0, 32, NULL, '日志详情接口', 1, '2021-10-28 12:22:34', '2021-10-28 12:22:34');
INSERT INTO `system_menu` VALUES (35, '常用小工具', 'CYXGJ', '/toolsManager', NULL, 0, NULL, '常用小工具', 0, 0, NULL, '常用小工具', 1, '2021-10-28 12:24:51', '2021-10-28 12:24:51');
INSERT INTO `system_menu` VALUES (36, 'JSON格式化', 'JSONGSH', '/toolsManager/json/format', NULL, 0, NULL, 'JSON格式化', 0, 35, NULL, 'JSON格式化', 1, '2021-10-28 12:25:20', '2021-10-28 12:25:20');
INSERT INTO `system_menu` VALUES (37, 'JSON转CSV', 'JSONZCSV', '/toolsManager/json/json2csv', NULL, 0, NULL, 'JSON转CSV', 0, 35, NULL, 'JSON转CSV', 1, '2021-10-28 12:26:01', '2021-10-28 12:26:01');
INSERT INTO `system_menu` VALUES (38, '小工具接口', 'XGJJK', '/api/v1/tools/*', NULL, 0, NULL, '小工具接口', 0, 0, NULL, '小工具接口', 1, '2021-10-28 12:28:02', '2021-10-28 12:28:02');
INSERT INTO `system_menu` VALUES (39, '表头管理', 'BTGL', '/system/settings/table/row', NULL, 0, NULL, '表头管理', 0, 17, NULL, '表头管理', 1, '2021-10-28 12:30:56', '2021-10-28 12:30:56');
INSERT INTO `system_menu` VALUES (40, '表头接口', 'BTJK', '/api/v1/table/row', NULL, 0, NULL, '表头接口', 0, 0, NULL, '表头接口', 1, '2021-10-28 12:32:55', '2021-10-28 12:32:55');
INSERT INTO `system_menu` VALUES (41, '菜单详情接口', 'CDXQJK', '/api/v1/system/menu/detail', NULL, NULL, NULL, '菜单详情接口', 0, 6, NULL, '菜单详情接口', 1, '2021-10-28 12:38:49', '2021-10-28 12:38:49');
INSERT INTO `system_menu` VALUES (42, '菜单表头接口', 'CDBTJK', '/api/v1/table/row/*', NULL, NULL, NULL, '菜单表头接口', 0, 6, NULL, '菜单表头接口', 1, '2021-10-28 12:40:31', '2021-10-28 12:40:31');
INSERT INTO `system_menu` VALUES (43, '用户权限接口', 'YHQXJK', '/api/v1/user/role', NULL, NULL, NULL, '用户权限接口', 0, 3, NULL, '用户权限接口', 1, '2021-10-28 13:26:36', '2021-10-28 13:26:36');
INSERT INTO `system_menu` VALUES (44, '用户接口分类', 'YHJKFL', '#', NULL, NULL, NULL, '用户接口分类', 0, 0, NULL, '用户接口分类', 1, '2021-10-28 13:31:49', '2021-10-28 13:31:49');
INSERT INTO `system_menu` VALUES (45, '用户接口', 'YHJK', '/api/v1/user', NULL, NULL, NULL, '用户接口', 0, 44, NULL, '用户接口', 1, '2021-10-28 13:32:46', '2021-10-28 13:32:46');
COMMIT;

-- ----------------------------
-- Table structure for system_menu_icon_relation
-- ----------------------------
DROP TABLE IF EXISTS `system_menu_icon_relation`;
CREATE TABLE `system_menu_icon_relation` (
  `system_menu_id` int(11) DEFAULT NULL COMMENT '菜单表唯一标志,唯一主键',
  `icon_id` int(11) DEFAULT NULL COMMENT '图标表唯一标志,唯一主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单与图标关系表';

-- ----------------------------
-- Records of system_menu_icon_relation
-- ----------------------------
BEGIN;
INSERT INTO `system_menu_icon_relation` VALUES (16, 1);
INSERT INTO `system_menu_icon_relation` VALUES (18, 1);
INSERT INTO `system_menu_icon_relation` VALUES (19, 1);
INSERT INTO `system_menu_icon_relation` VALUES (20, 1);
INSERT INTO `system_menu_icon_relation` VALUES (21, 1);
INSERT INTO `system_menu_icon_relation` VALUES (22, 1);
INSERT INTO `system_menu_icon_relation` VALUES (23, 1);
INSERT INTO `system_menu_icon_relation` VALUES (24, 1);
INSERT INTO `system_menu_icon_relation` VALUES (25, 1);
INSERT INTO `system_menu_icon_relation` VALUES (26, 1);
INSERT INTO `system_menu_icon_relation` VALUES (27, 1);
INSERT INTO `system_menu_icon_relation` VALUES (28, 1);
INSERT INTO `system_menu_icon_relation` VALUES (29, 1);
INSERT INTO `system_menu_icon_relation` VALUES (30, 1);
INSERT INTO `system_menu_icon_relation` VALUES (31, 1);
INSERT INTO `system_menu_icon_relation` VALUES (32, 1);
INSERT INTO `system_menu_icon_relation` VALUES (33, 1);
INSERT INTO `system_menu_icon_relation` VALUES (34, 1);
INSERT INTO `system_menu_icon_relation` VALUES (35, 1);
INSERT INTO `system_menu_icon_relation` VALUES (36, 1);
INSERT INTO `system_menu_icon_relation` VALUES (37, 1);
INSERT INTO `system_menu_icon_relation` VALUES (38, 1);
INSERT INTO `system_menu_icon_relation` VALUES (39, 1);
INSERT INTO `system_menu_icon_relation` VALUES (40, 1);
INSERT INTO `system_menu_icon_relation` VALUES (41, 1);
INSERT INTO `system_menu_icon_relation` VALUES (42, 1);
INSERT INTO `system_menu_icon_relation` VALUES (43, 1);
INSERT INTO `system_menu_icon_relation` VALUES (44, 1);
INSERT INTO `system_menu_icon_relation` VALUES (45, 1);
COMMIT;

-- ----------------------------
-- Table structure for system_menu_method_relation
-- ----------------------------
DROP TABLE IF EXISTS `system_menu_method_relation`;
CREATE TABLE `system_menu_method_relation` (
  `system_menu_id` int(11) DEFAULT NULL,
  `system_method_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='system menu and system method relation table';

-- ----------------------------
-- Records of system_menu_method_relation
-- ----------------------------
BEGIN;
INSERT INTO `system_menu_method_relation` VALUES (1, 2);
INSERT INTO `system_menu_method_relation` VALUES (2, 2);
INSERT INTO `system_menu_method_relation` VALUES (3, 2);
INSERT INTO `system_menu_method_relation` VALUES (6, 2);
INSERT INTO `system_menu_method_relation` VALUES (8, 2);
INSERT INTO `system_menu_method_relation` VALUES (9, 2);
INSERT INTO `system_menu_method_relation` VALUES (6, 3);
INSERT INTO `system_menu_method_relation` VALUES (6, 1);
INSERT INTO `system_menu_method_relation` VALUES (12, 2);
INSERT INTO `system_menu_method_relation` VALUES (13, 2);
INSERT INTO `system_menu_method_relation` VALUES (9, 1);
INSERT INTO `system_menu_method_relation` VALUES (14, 2);
INSERT INTO `system_menu_method_relation` VALUES (15, 2);
INSERT INTO `system_menu_method_relation` VALUES (16, 2);
INSERT INTO `system_menu_method_relation` VALUES (8, 3);
INSERT INTO `system_menu_method_relation` VALUES (8, 1);
INSERT INTO `system_menu_method_relation` VALUES (18, 2);
INSERT INTO `system_menu_method_relation` VALUES (19, 1);
INSERT INTO `system_menu_method_relation` VALUES (19, 2);
INSERT INTO `system_menu_method_relation` VALUES (19, 3);
INSERT INTO `system_menu_method_relation` VALUES (20, 2);
INSERT INTO `system_menu_method_relation` VALUES (3, 3);
INSERT INTO `system_menu_method_relation` VALUES (21, 2);
INSERT INTO `system_menu_method_relation` VALUES (22, 1);
INSERT INTO `system_menu_method_relation` VALUES (22, 2);
INSERT INTO `system_menu_method_relation` VALUES (22, 3);
INSERT INTO `system_menu_method_relation` VALUES (23, 1);
INSERT INTO `system_menu_method_relation` VALUES (23, 2);
INSERT INTO `system_menu_method_relation` VALUES (23, 3);
INSERT INTO `system_menu_method_relation` VALUES (15, 3);
INSERT INTO `system_menu_method_relation` VALUES (24, 2);
INSERT INTO `system_menu_method_relation` VALUES (25, 2);
INSERT INTO `system_menu_method_relation` VALUES (26, 2);
INSERT INTO `system_menu_method_relation` VALUES (27, 2);
INSERT INTO `system_menu_method_relation` VALUES (28, 2);
INSERT INTO `system_menu_method_relation` VALUES (29, 2);
INSERT INTO `system_menu_method_relation` VALUES (30, 2);
INSERT INTO `system_menu_method_relation` VALUES (31, 2);
INSERT INTO `system_menu_method_relation` VALUES (32, 1);
INSERT INTO `system_menu_method_relation` VALUES (32, 2);
INSERT INTO `system_menu_method_relation` VALUES (32, 3);
INSERT INTO `system_menu_method_relation` VALUES (33, 1);
INSERT INTO `system_menu_method_relation` VALUES (33, 2);
INSERT INTO `system_menu_method_relation` VALUES (33, 3);
INSERT INTO `system_menu_method_relation` VALUES (34, 2);
INSERT INTO `system_menu_method_relation` VALUES (35, 2);
INSERT INTO `system_menu_method_relation` VALUES (36, 2);
INSERT INTO `system_menu_method_relation` VALUES (37, 2);
INSERT INTO `system_menu_method_relation` VALUES (38, 1);
INSERT INTO `system_menu_method_relation` VALUES (38, 2);
INSERT INTO `system_menu_method_relation` VALUES (38, 3);
INSERT INTO `system_menu_method_relation` VALUES (39, 2);
INSERT INTO `system_menu_method_relation` VALUES (40, 1);
INSERT INTO `system_menu_method_relation` VALUES (40, 2);
INSERT INTO `system_menu_method_relation` VALUES (40, 3);
INSERT INTO `system_menu_method_relation` VALUES (41, 1);
INSERT INTO `system_menu_method_relation` VALUES (41, 2);
INSERT INTO `system_menu_method_relation` VALUES (41, 3);
INSERT INTO `system_menu_method_relation` VALUES (42, 2);
INSERT INTO `system_menu_method_relation` VALUES (43, 3);
INSERT INTO `system_menu_method_relation` VALUES (44, 2);
INSERT INTO `system_menu_method_relation` VALUES (45, 1);
INSERT INTO `system_menu_method_relation` VALUES (45, 2);
INSERT INTO `system_menu_method_relation` VALUES (45, 3);
INSERT INTO `system_menu_method_relation` VALUES (13, 3);
INSERT INTO `system_menu_method_relation` VALUES (12, 3);
COMMIT;

-- ----------------------------
-- Table structure for system_menu_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `system_menu_role_relation`;
CREATE TABLE `system_menu_role_relation` (
  `system_menu_id` int(11) DEFAULT NULL,
  `system_role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='system menu and system role relation table';

-- ----------------------------
-- Records of system_menu_role_relation
-- ----------------------------
BEGIN;
INSERT INTO `system_menu_role_relation` VALUES (1, 1);
INSERT INTO `system_menu_role_relation` VALUES (2, 1);
INSERT INTO `system_menu_role_relation` VALUES (3, 1);
INSERT INTO `system_menu_role_relation` VALUES (4, 1);
INSERT INTO `system_menu_role_relation` VALUES (5, 1);
INSERT INTO `system_menu_role_relation` VALUES (6, 1);
INSERT INTO `system_menu_role_relation` VALUES (7, 1);
INSERT INTO `system_menu_role_relation` VALUES (8, 1);
INSERT INTO `system_menu_role_relation` VALUES (9, 1);
INSERT INTO `system_menu_role_relation` VALUES (10, 1);
INSERT INTO `system_menu_role_relation` VALUES (11, 1);
INSERT INTO `system_menu_role_relation` VALUES (12, 1);
INSERT INTO `system_menu_role_relation` VALUES (13, 1);
INSERT INTO `system_menu_role_relation` VALUES (14, 1);
INSERT INTO `system_menu_role_relation` VALUES (15, 1);
INSERT INTO `system_menu_role_relation` VALUES (16, 1);
INSERT INTO `system_menu_role_relation` VALUES (17, 1);
INSERT INTO `system_menu_role_relation` VALUES (18, 1);
INSERT INTO `system_menu_role_relation` VALUES (19, 1);
INSERT INTO `system_menu_role_relation` VALUES (20, 1);
COMMIT;

-- ----------------------------
-- Table structure for system_menu_type
-- ----------------------------
DROP TABLE IF EXISTS `system_menu_type`;
CREATE TABLE `system_menu_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT 'menu type name',
  `code` varchar(100) DEFAULT NULL COMMENT 'menu type code',
  `description` varchar(200) DEFAULT NULL COMMENT 'menu type description',
  `active` tinyint(1) DEFAULT '1' COMMENT 'active status',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='system menu type table';

-- ----------------------------
-- Records of system_menu_type
-- ----------------------------
BEGIN;
INSERT INTO `system_menu_type` VALUES (1, '按钮', 'AN', '按钮', 1, '2021-10-28 13:44:25', '2021-10-28 13:44:25');
INSERT INTO `system_menu_type` VALUES (2, 'API接口', 'APIJK', 'API接口', 1, '2021-10-27 14:12:25', '2021-10-27 14:12:25');
INSERT INTO `system_menu_type` VALUES (3, '菜单', 'CD', '菜单', 1, '2021-10-27 14:12:25', '2021-10-27 14:12:25');
INSERT INTO `system_menu_type` VALUES (4, '超链接', 'CLJ', '超链接', 1, '2021-10-28 11:16:30', '2021-10-28 11:16:30');
COMMIT;

-- ----------------------------
-- Table structure for system_menu_type_relation
-- ----------------------------
DROP TABLE IF EXISTS `system_menu_type_relation`;
CREATE TABLE `system_menu_type_relation` (
  `system_menu_id` int(11) DEFAULT NULL,
  `system_menu_type_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='system menu and system menu type relation table';

-- ----------------------------
-- Records of system_menu_type_relation
-- ----------------------------
BEGIN;
INSERT INTO `system_menu_type_relation` VALUES (1, 2);
INSERT INTO `system_menu_type_relation` VALUES (4, 3);
INSERT INTO `system_menu_type_relation` VALUES (3, 2);
INSERT INTO `system_menu_type_relation` VALUES (2, 2);
INSERT INTO `system_menu_type_relation` VALUES (5, 3);
INSERT INTO `system_menu_type_relation` VALUES (6, 2);
INSERT INTO `system_menu_type_relation` VALUES (7, 3);
INSERT INTO `system_menu_type_relation` VALUES (8, 2);
INSERT INTO `system_menu_type_relation` VALUES (9, 2);
INSERT INTO `system_menu_type_relation` VALUES (10, 3);
INSERT INTO `system_menu_type_relation` VALUES (11, 3);
INSERT INTO `system_menu_type_relation` VALUES (12, 2);
INSERT INTO `system_menu_type_relation` VALUES (13, 2);
INSERT INTO `system_menu_type_relation` VALUES (14, 2);
INSERT INTO `system_menu_type_relation` VALUES (16, 3);
INSERT INTO `system_menu_type_relation` VALUES (15, 2);
INSERT INTO `system_menu_type_relation` VALUES (17, 3);
INSERT INTO `system_menu_type_relation` VALUES (18, 3);
INSERT INTO `system_menu_type_relation` VALUES (19, 2);
INSERT INTO `system_menu_type_relation` VALUES (20, 2);
INSERT INTO `system_menu_type_relation` VALUES (21, 3);
INSERT INTO `system_menu_type_relation` VALUES (22, 2);
INSERT INTO `system_menu_type_relation` VALUES (23, 3);
INSERT INTO `system_menu_type_relation` VALUES (24, 3);
INSERT INTO `system_menu_type_relation` VALUES (25, 3);
INSERT INTO `system_menu_type_relation` VALUES (26, 3);
INSERT INTO `system_menu_type_relation` VALUES (27, 3);
INSERT INTO `system_menu_type_relation` VALUES (28, 3);
INSERT INTO `system_menu_type_relation` VALUES (29, 3);
INSERT INTO `system_menu_type_relation` VALUES (30, 3);
INSERT INTO `system_menu_type_relation` VALUES (31, 3);
INSERT INTO `system_menu_type_relation` VALUES (32, 2);
INSERT INTO `system_menu_type_relation` VALUES (33, 2);
INSERT INTO `system_menu_type_relation` VALUES (34, 2);
INSERT INTO `system_menu_type_relation` VALUES (35, 3);
INSERT INTO `system_menu_type_relation` VALUES (36, 3);
INSERT INTO `system_menu_type_relation` VALUES (37, 3);
INSERT INTO `system_menu_type_relation` VALUES (38, 2);
INSERT INTO `system_menu_type_relation` VALUES (39, 3);
INSERT INTO `system_menu_type_relation` VALUES (40, 2);
INSERT INTO `system_menu_type_relation` VALUES (41, 2);
INSERT INTO `system_menu_type_relation` VALUES (42, 2);
INSERT INTO `system_menu_type_relation` VALUES (43, 2);
INSERT INTO `system_menu_type_relation` VALUES (44, 2);
INSERT INTO `system_menu_type_relation` VALUES (45, 2);
COMMIT;

-- ----------------------------
-- Table structure for system_method
-- ----------------------------
DROP TABLE IF EXISTS `system_method`;
CREATE TABLE `system_method` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT 'method name',
  `code` varchar(100) DEFAULT NULL COMMENT 'method code',
  `description` varchar(200) DEFAULT NULL COMMENT 'method description',
  `method` varchar(200) DEFAULT NULL COMMENT 'interface method multiple method split by ,',
  `active` tinyint(1) DEFAULT '1' COMMENT 'active status',
  `system` tinyint(1) DEFAULT '0' COMMENT 'system default',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='system method table';

-- ----------------------------
-- Records of system_method
-- ----------------------------
BEGIN;
INSERT INTO `system_method` VALUES (1, 'POST', NULL, NULL, 'POST', 1, 0, '2021-10-27 15:56:27', '2021-10-27 15:56:27');
INSERT INTO `system_method` VALUES (2, 'GET', NULL, NULL, 'GET', 1, 0, '2021-10-27 18:28:08', '2021-10-27 18:28:08');
INSERT INTO `system_method` VALUES (3, 'PUT', NULL, NULL, 'PUT', 1, 0, '2021-10-28 10:25:07', '2021-10-28 10:25:07');
COMMIT;

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT 'role name',
  `code` varchar(50) DEFAULT NULL COMMENT 'role code',
  `description` varchar(100) DEFAULT NULL COMMENT 'role description',
  `active` tinyint(1) DEFAULT '1' COMMENT 'active status',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='system role table';

-- ----------------------------
-- Records of system_role
-- ----------------------------
BEGIN;
INSERT INTO `system_role` VALUES (1, '管理员', 'GLY', '管理员', NULL, '2021-10-28 13:59:36', '2021-10-28 13:59:36');
INSERT INTO `system_role` VALUES (2, '默认用户', 'MRYH', '默认用户', NULL, '2021-10-28 13:59:42', '2021-10-28 13:59:42');
COMMIT;

-- ----------------------------
-- Table structure for system_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `system_role_menu_relation`;
CREATE TABLE `system_role_menu_relation` (
  `system_role_id` int(11) DEFAULT NULL,
  `system_menu_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='system role and system menu relation table';

-- ----------------------------
-- Records of system_role_menu_relation
-- ----------------------------
BEGIN;
INSERT INTO `system_role_menu_relation` VALUES (2, 36);
INSERT INTO `system_role_menu_relation` VALUES (2, 35);
INSERT INTO `system_role_menu_relation` VALUES (2, 37);
INSERT INTO `system_role_menu_relation` VALUES (2, 35);
INSERT INTO `system_role_menu_relation` VALUES (1, 33);
INSERT INTO `system_role_menu_relation` VALUES (1, 32);
INSERT INTO `system_role_menu_relation` VALUES (1, 34);
INSERT INTO `system_role_menu_relation` VALUES (1, 32);
INSERT INTO `system_role_menu_relation` VALUES (1, 1);
INSERT INTO `system_role_menu_relation` VALUES (1, 2);
INSERT INTO `system_role_menu_relation` VALUES (1, 22);
INSERT INTO `system_role_menu_relation` VALUES (1, 3);
INSERT INTO `system_role_menu_relation` VALUES (1, 43);
INSERT INTO `system_role_menu_relation` VALUES (1, 3);
INSERT INTO `system_role_menu_relation` VALUES (1, 38);
INSERT INTO `system_role_menu_relation` VALUES (1, 41);
INSERT INTO `system_role_menu_relation` VALUES (1, 6);
INSERT INTO `system_role_menu_relation` VALUES (1, 42);
INSERT INTO `system_role_menu_relation` VALUES (1, 6);
INSERT INTO `system_role_menu_relation` VALUES (1, 40);
INSERT INTO `system_role_menu_relation` VALUES (1, 19);
INSERT INTO `system_role_menu_relation` VALUES (1, 8);
INSERT INTO `system_role_menu_relation` VALUES (1, 20);
INSERT INTO `system_role_menu_relation` VALUES (1, 8);
INSERT INTO `system_role_menu_relation` VALUES (1, 9);
INSERT INTO `system_role_menu_relation` VALUES (1, 12);
INSERT INTO `system_role_menu_relation` VALUES (1, 13);
INSERT INTO `system_role_menu_relation` VALUES (1, 14);
INSERT INTO `system_role_menu_relation` VALUES (1, 15);
INSERT INTO `system_role_menu_relation` VALUES (1, 45);
INSERT INTO `system_role_menu_relation` VALUES (1, 44);
INSERT INTO `system_role_menu_relation` VALUES (1, 16);
INSERT INTO `system_role_menu_relation` VALUES (1, 18);
INSERT INTO `system_role_menu_relation` VALUES (1, 21);
INSERT INTO `system_role_menu_relation` VALUES (1, 23);
INSERT INTO `system_role_menu_relation` VALUES (1, 24);
INSERT INTO `system_role_menu_relation` VALUES (1, 25);
INSERT INTO `system_role_menu_relation` VALUES (1, 26);
INSERT INTO `system_role_menu_relation` VALUES (1, 27);
INSERT INTO `system_role_menu_relation` VALUES (1, 28);
INSERT INTO `system_role_menu_relation` VALUES (1, 29);
INSERT INTO `system_role_menu_relation` VALUES (1, 30);
INSERT INTO `system_role_menu_relation` VALUES (1, 31);
INSERT INTO `system_role_menu_relation` VALUES (1, 35);
INSERT INTO `system_role_menu_relation` VALUES (1, 36);
INSERT INTO `system_role_menu_relation` VALUES (1, 37);
INSERT INTO `system_role_menu_relation` VALUES (1, 39);
INSERT INTO `system_role_menu_relation` VALUES (1, 4);
INSERT INTO `system_role_menu_relation` VALUES (1, 5);
INSERT INTO `system_role_menu_relation` VALUES (1, 7);
INSERT INTO `system_role_menu_relation` VALUES (1, 10);
INSERT INTO `system_role_menu_relation` VALUES (1, 11);
INSERT INTO `system_role_menu_relation` VALUES (1, 17);
COMMIT;

-- ----------------------------
-- Table structure for system_settings
-- ----------------------------
DROP TABLE IF EXISTS `system_settings`;
CREATE TABLE `system_settings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `code` varchar(100) DEFAULT NULL COMMENT '编码',
  `value` varchar(200) DEFAULT NULL COMMENT '配置信息',
  `label` varchar(100) DEFAULT NULL COMMENT '显示名称',
  `active` tinyint(1) DEFAULT '1' COMMENT '激活状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置表';

-- ----------------------------
-- Records of system_settings
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for table_row
-- ----------------------------
DROP TABLE IF EXISTS `table_row`;
CREATE TABLE `table_row` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `title` varchar(100) DEFAULT NULL COMMENT '名称',
  `active` tinyint(1) DEFAULT '1' COMMENT '激活状态',
  `checked` tinyint(1) DEFAULT '0' COMMENT '选中状态',
  `properties` varchar(100) DEFAULT NULL COMMENT '对应数据的字段',
  `type` varchar(10) DEFAULT NULL COMMENT '字段类型',
  `sorted` int(11) DEFAULT NULL COMMENT '排列顺序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='表格(头)配置表';

-- ----------------------------
-- Records of table_row
-- ----------------------------
BEGIN;
INSERT INTO `table_row` VALUES (1, 'id', '编码', 1, 1, 'id', 'Int', 1, '2021-10-28 12:34:59', '2021-10-28 12:34:59');
INSERT INTO `table_row` VALUES (2, 'active', '激活状态', 1, 1, 'active', 'String', 2, '2021-10-28 12:42:11', '2021-10-28 12:42:11');
INSERT INTO `table_row` VALUES (3, 'createTime', '创建时间', 1, 1, 'createTime', 'String', 3, '2021-10-28 12:42:56', '2021-10-28 12:42:56');
INSERT INTO `table_row` VALUES (4, 'updateTime', '更新时间', 1, 1, 'updateTime', 'String', 4, '2021-10-28 12:50:26', '2021-10-28 12:50:26');
COMMIT;

-- ----------------------------
-- Table structure for table_row_system_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `table_row_system_menu_relation`;
CREATE TABLE `table_row_system_menu_relation` (
  `system_menu_id` int(11) DEFAULT NULL COMMENT '菜单表唯一标志,唯一主键',
  `table_row_id` int(11) DEFAULT NULL COMMENT '表格(头)表唯一标志,唯一主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='表格(头)与菜单关系表';

-- ----------------------------
-- Records of table_row_system_menu_relation
-- ----------------------------
BEGIN;
INSERT INTO `table_row_system_menu_relation` VALUES (1, 39);
INSERT INTO `table_row_system_menu_relation` VALUES (2, 39);
INSERT INTO `table_row_system_menu_relation` VALUES (3, 39);
INSERT INTO `table_row_system_menu_relation` VALUES (4, 39);
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT 'user name',
  `password` varchar(200) DEFAULT NULL COMMENT 'user password',
  `avatar` varchar(100) DEFAULT NULL COMMENT 'user avatar',
  `active` tinyint(1) DEFAULT '1' COMMENT 'active status',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'update time',
  `locked` tinyint(1) DEFAULT '0' COMMENT '是否锁定,锁定后用户无法登录',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱地址',
  `systemed` tinyint(1) DEFAULT '0' COMMENT '是否为系统默认,系统默认用户无法做任何操作',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='user table';

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES (1, 'admin', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', NULL, 1, '2019-01-26 01:05:36', '2019-01-26 01:05:36', 0, NULL, 0);
INSERT INTO `users` VALUES (2, 'default', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', NULL, 1, '2021-10-27 16:17:11', '2021-10-27 16:17:11', 0, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for users_system_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `users_system_role_relation`;
CREATE TABLE `users_system_role_relation` (
  `users_id` int(11) DEFAULT NULL,
  `system_role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='user and system role relation table';

-- ----------------------------
-- Records of users_system_role_relation
-- ----------------------------
BEGIN;
INSERT INTO `users_system_role_relation` VALUES (1, 1);
INSERT INTO `users_system_role_relation` VALUES (2, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
