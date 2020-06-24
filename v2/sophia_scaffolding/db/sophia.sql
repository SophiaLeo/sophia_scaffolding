/*
 Navicat Premium Data Transfer

 Source Server         : me本地
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : sophia_scaffolding

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 24/06/2020 13:44:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_api_logger
-- ----------------------------
DROP TABLE IF EXISTS `sys_api_logger`;
CREATE TABLE `sys_api_logger`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `URI` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'url',
  `METHOD_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `CLASS_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类名',
  `CREATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '访问时间',
  `SERVICE_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务id',
  `USER_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `PARAMS` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数',
  `IP` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问ip',
  `METHOD` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方式',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_api_logger
-- ----------------------------
INSERT INTO `sys_api_logger` VALUES ('50245c58f1fe4eec88f6cf4a18c24b0d', '/user/web/status/9ca92a697f4e4bb7b284a51969714275', '修改用户状态:updateStatus', 'com.scaffolding.sophia.admin.biz.controller.UserController', '2020-06-20 18:53:31', 'sophia-admin', 'admin', '[9ca92a697f4e4bb7b284a51969714275, 0]', '0:0:0:0:0:0:0:1', 'PUT');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `NUM` int(11) NULL DEFAULT NULL COMMENT '排序',
  `PID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父部门id',
  `PIDS` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级ids',
  `SIMPLE_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '简称',
  `FULL_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '全称',
  `TIPS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '提示',
  `ADDRESS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `DEPT_TYPE` int(11) NULL DEFAULT NULL COMMENT '部门类型(0 公司1部门)',
  `COMP_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门所在的公司id',
  `CREATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `UPDATE_USER` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公司部门' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('324e6b3d94204c158f292e17d2cb9c8d', 1, '3596accca0e848a682b9f96b6d08a68a', '[3e88ef89ece9460abf889c9f3aeb5d8c],[3596accca0e848a682b9f96b6d08a68a],[324e6b3d94204c158f292e17d2cb9c8d]', 'js', '技术一部', '', '北京软件园', 1, '3e88ef89ece9460abf889c9f3aeb5d8c', '2019-12-20 18:04:01', 'bf755585d01d46539a5584a38eca7c98', NULL, NULL);
INSERT INTO `sys_dept` VALUES ('3596accca0e848a682b9f96b6d08a68a', 1, '3e88ef89ece9460abf889c9f3aeb5d8c', '[3e88ef89ece9460abf889c9f3aeb5d8c],[3596accca0e848a682b9f96b6d08a68a]', 'js', '技术部', NULL, '北京软件园', 1, '3e88ef89ece9460abf889c9f3aeb5d8c', NULL, NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES ('3e88ef89ece9460abf889c9f3aeb5d8c', 1, '0', '[3e88ef89ece9460abf889c9f3aeb5d8c]', 'tx', '腾讯', '互联网', '北京', 0, NULL, '2019-12-20 17:50:30', 'bf755585d01d46539a5584a38eca7c98', NULL, NULL);
INSERT INTO `sys_dept` VALUES ('623c541008f24dbc8d220e812c5aea30', 1, 'ae6e484f4ba3444b90cdf46a10056560', '[ae6e484f4ba3444b90cdf46a10056560],[623c541008f24dbc8d220e812c5aea30]', 'js', '技术部', '', '杭州软件园', 1, 'ae6e484f4ba3444b90cdf46a10056560', '2019-12-20 17:59:05', 'bf755585d01d46539a5584a38eca7c98', '2019-12-20 18:01:04', 'bf755585d01d46539a5584a38eca7c98');
INSERT INTO `sys_dept` VALUES ('ae6e484f4ba3444b90cdf46a10056560', 2, '0', '[ae6e484f4ba3444b90cdf46a10056560]', 'AliBaBa', '阿里巴巴', '互联网', '杭州', 0, NULL, '2019-12-20 17:52:02', 'bf755585d01d46539a5584a38eca7c98', '2019-12-20 17:53:23', 'bf755585d01d46539a5584a38eca7c98');
INSERT INTO `sys_dept` VALUES ('d6fe08e93a464048b5c33977c5cfd48c', 2, '623c541008f24dbc8d220e812c5aea30', '[ae6e484f4ba3444b90cdf46a10056560],[623c541008f24dbc8d220e812c5aea30],[d6fe08e93a464048b5c33977c5cfd48c]', 'js2', '技术二部', '', '杭州软件园', 1, 'ae6e484f4ba3444b90cdf46a10056560', '2019-12-20 18:02:10', 'bf755585d01d46539a5584a38eca7c98', NULL, NULL);
INSERT INTO `sys_dept` VALUES ('d85f8ec37db3456c8d3cd4061ff98516', 1, '623c541008f24dbc8d220e812c5aea30', '[ae6e484f4ba3444b90cdf46a10056560],[623c541008f24dbc8d220e812c5aea30],[d85f8ec37db3456c8d3cd4061ff98516]', 'js1', '技术一部', '', '杭州软件园', 1, 'ae6e484f4ba3444b90cdf46a10056560', '2019-12-20 18:01:27', 'bf755585d01d46539a5584a38eca7c98', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `VALUE` int(11) NULL DEFAULT NULL COMMENT '字典值',
  `PID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级ID',
  `NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '字典名称',
  `TYPE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '字典类型',
  `STATUS` int(1) NULL DEFAULT NULL COMMENT '0无效 1有效',
  `CREATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `UPDATE_USER` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('0bc6f67337ae463ba8fec77822f279d2', NULL, '0', '性别', 'sex', 1, '2019-12-19 15:35:46', 'bf755585d01d46539a5584a38eca7c98', '2019-12-19 15:38:00', 'bf755585d01d46539a5584a38eca7c98');
INSERT INTO `sys_dict` VALUES ('1026ac7a4d984264b80a730b60ca9bc1', 0, '927c7f553c724e0ca3b5ec1baea6ee19', '前台角色', NULL, 1, '2019-12-20 10:21:02', 'bf755585d01d46539a5584a38eca7c98', NULL, NULL);
INSERT INTO `sys_dict` VALUES ('2181742e6e1b445bbc4c670c052dc877', 2, '85583a442b344b3992496b4b57845eb6', 'PUT', NULL, 1, '2019-12-19 15:31:59', 'bf755585d01d46539a5584a38eca7c98', NULL, NULL);
INSERT INTO `sys_dict` VALUES ('4d9ca78b48ac44169e06cee0845f9293', 1, '927c7f553c724e0ca3b5ec1baea6ee19', '后台角色', NULL, 1, '2019-12-20 10:21:02', 'bf755585d01d46539a5584a38eca7c98', NULL, NULL);
INSERT INTO `sys_dict` VALUES ('56013fbf8adf4f0b80bdced12f351392', 3, '85583a442b344b3992496b4b57845eb6', 'DELETE', NULL, 1, '2019-12-19 15:31:59', 'bf755585d01d46539a5584a38eca7c98', NULL, NULL);
INSERT INTO `sys_dict` VALUES ('5c30163d7fe746a6a0776bb7932405f3', 1, 'dbb2452ced1a419f8bb0098995a90569', '后台用户', NULL, 1, '2019-12-20 10:21:41', 'bf755585d01d46539a5584a38eca7c98', NULL, NULL);
INSERT INTO `sys_dict` VALUES ('600b9f4313dd412d9ea54c11e02a648d', 1, '0bc6f67337ae463ba8fec77822f279d2', '男', NULL, 1, '2019-12-19 15:35:46', 'bf755585d01d46539a5584a38eca7c98', '2019-12-19 15:38:00', 'bf755585d01d46539a5584a38eca7c98');
INSERT INTO `sys_dict` VALUES ('83a23ea224c3460ead5d60d634aff32a', 2, '0bc6f67337ae463ba8fec77822f279d2', '女', NULL, 1, '2019-12-19 15:35:46', 'bf755585d01d46539a5584a38eca7c98', '2019-12-19 15:38:00', 'bf755585d01d46539a5584a38eca7c98');
INSERT INTO `sys_dict` VALUES ('85583a442b344b3992496b4b57845eb6', NULL, '0', '请求方式', 'method', 1, '2019-12-19 15:31:59', 'bf755585d01d46539a5584a38eca7c98', NULL, NULL);
INSERT INTO `sys_dict` VALUES ('8bb3c23be65244d1ac8e0e62079f816e', 0, 'dbb2452ced1a419f8bb0098995a90569', '前台用户', NULL, 1, '2019-12-20 10:21:41', 'bf755585d01d46539a5584a38eca7c98', NULL, NULL);
INSERT INTO `sys_dict` VALUES ('9051aa7025e7456e88806a5f17e13fcf', 1, '85583a442b344b3992496b4b57845eb6', 'POST', NULL, 1, '2019-12-19 15:31:59', 'bf755585d01d46539a5584a38eca7c98', NULL, NULL);
INSERT INTO `sys_dict` VALUES ('927c7f553c724e0ca3b5ec1baea6ee19', NULL, '0', '角色类型', 'role_type', 1, '2019-12-20 10:21:02', 'bf755585d01d46539a5584a38eca7c98', NULL, NULL);
INSERT INTO `sys_dict` VALUES ('959a6931b5474c519192dc6fd1a98c33', 0, '97b50f894be6473b9dc72913fd723dc9', '禁用', NULL, 1, '2019-12-20 17:26:35', 'bf755585d01d46539a5584a38eca7c98', '2019-12-20 17:28:15', 'bf755585d01d46539a5584a38eca7c98');
INSERT INTO `sys_dict` VALUES ('96a0e9a826054fd58e16727c816ee871', 1, '97b50f894be6473b9dc72913fd723dc9', '启用', NULL, 1, '2019-12-20 17:26:35', 'bf755585d01d46539a5584a38eca7c98', '2019-12-20 17:28:15', 'bf755585d01d46539a5584a38eca7c98');
INSERT INTO `sys_dict` VALUES ('97b50f894be6473b9dc72913fd723dc9', NULL, '0', '状态', 'status', 1, '2019-12-20 17:26:35', 'bf755585d01d46539a5584a38eca7c98', '2019-12-20 17:28:15', 'bf755585d01d46539a5584a38eca7c98');
INSERT INTO `sys_dict` VALUES ('dbb2452ced1a419f8bb0098995a90569', NULL, '0', '用户类型', 'user_type', 1, '2019-12-20 10:21:41', 'bf755585d01d46539a5584a38eca7c98', NULL, NULL);

-- ----------------------------
-- Table structure for sys_membership
-- ----------------------------
DROP TABLE IF EXISTS `sys_membership`;
CREATE TABLE `sys_membership`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `USER_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `APP_ID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'appid 微信openid',
  `IMG_PATH` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `IS_DELETED` int(1) NULL DEFAULT NULL COMMENT '是否删除 (1 是  0否)',
  `STATUS` int(1) NULL DEFAULT NULL COMMENT '0无效 1有效',
  `CREATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `UPDATE_USER` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '前端会员信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details`  (
  `client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorities` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `autoapprove` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'false',
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'oauth2信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
INSERT INTO `sys_oauth_client_details` VALUES ('amor-admin', NULL, 'amor-admin-secret', 'all,read,write', 'password,refresh_token', NULL, NULL, 21600, 2592000, NULL, 'true');
INSERT INTO `sys_oauth_client_details` VALUES ('sophia-admin', NULL, 'sophia-admin-secret', 'all,read,write', 'password,refresh_token,authorization_code,client_credentials', NULL, NULL, 21600, 2592000, NULL, 'true');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `NAME` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权限名称',
  `CODE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `URL_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路径',
  `PID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级权限',
  `TYPE` int(1) NULL DEFAULT NULL COMMENT '菜单类型 （0菜单 1按钮）',
  `ICON_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `SORT` int(1) NULL DEFAULT NULL COMMENT '排序',
  `STATUS` int(1) NULL DEFAULT NULL COMMENT '是否有效(0无效，1有效)',
  `CREATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `UPDATE_USER` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('06751d8cfea847d8a61c237ca16edc08', '新增', 'role_add', '/role/add', 'f5a1299587784941a0961f065925d56d', 1, '', 1, 1, '2019-12-17 17:54:49', 'bf755585d01d46539a5584a38eca7c98', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('45a22451a81c4aebb43ca1b71e2f56ca', '权限管理', 'permission', '/permission', '930787e5ff154aabad69204af3817abd', 0, 'permission_icon', 3, 1, '2019-12-17 18:00:40', 'bf755585d01d46539a5584a38eca7c98', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('930787e5ff154aabad69204af3817abd', '系统管理', 'system', '/system', '0', 0, 'system_icon', 1, 1, '2019-12-17 17:57:17', 'bf755585d01d46539a5584a38eca7c98', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('ea23982da45f4bf5b5d6ff42c963e96a', '用户管理', 'user', '/user', '930787e5ff154aabad69204af3817abd', 0, 'user_icon', 1, 1, '2019-12-17 18:01:07', 'bf755585d01d46539a5584a38eca7c98', NULL, NULL);
INSERT INTO `sys_permission` VALUES ('f5a1299587784941a0961f065925d56d', '角色管理', 'role', '/role', '930787e5ff154aabad69204af3817abd', 0, 'role_icon', 2, 1, '2019-12-17 17:40:28', 'bf755585d01d46539a5584a38eca7c98', '2019-12-17 17:41:59', 'bf755585d01d46539a5584a38eca7c98');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ROLE_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色名称',
  `ROLE_CODE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编号',
  `DESCRIPTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `ROLE_TYPE` int(1) NULL DEFAULT NULL COMMENT '角色类型 1:后台角色 0:前台角色',
  `STATUS` int(1) NULL DEFAULT NULL COMMENT '是否有效(0无效，1有效)',
  `CREATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `UPDATE_USER` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1057eb16afc84aa0ae30b3b35396d7d9', '超级管理员', 'SUPER_ADMIN', '超级管理员(不能删除)', 1, 1, '2019-12-18 15:22:05', 'bf755585d01d46539a5584a38eca7c98', NULL, NULL);
INSERT INTO `sys_role` VALUES ('32cb29e48d5e4be29abe50972ac38fa0', '测试', 'TEST', '测试cs', 1, 1, '2019-12-20 17:36:23', 'bf755585d01d46539a5584a38eca7c98', '2019-12-20 17:37:32', 'bf755585d01d46539a5584a38eca7c98');
INSERT INTO `sys_role` VALUES ('6944512a3ee847709586bb0294b9f0bc', '前台用户', 'QT_YH', '前台用户', 0, 1, '2019-12-18 15:31:43', 'bf755585d01d46539a5584a38eca7c98', '2019-12-18 15:32:56', 'bf755585d01d46539a5584a38eca7c98');
INSERT INTO `sys_role` VALUES ('f742168c51d545bab6d49584b5dc9467', '管理员', 'ADMIN', '管理员', 1, 1, '2019-12-18 15:27:02', 'bf755585d01d46539a5584a38eca7c98', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ROLE_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `PERM_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('062279c9df6446d3917e255bcdbd7b2f', '1057eb16afc84aa0ae30b3b35396d7d9', '930787e5ff154aabad69204af3817abd');
INSERT INTO `sys_role_permission` VALUES ('0c551bd8fa2c4b7c8c0c371ee870ff5d', 'f742168c51d545bab6d49584b5dc9467', '06751d8cfea847d8a61c237ca16edc08');
INSERT INTO `sys_role_permission` VALUES ('134a9d45bddd4b7bb1908854cbfd1c77', 'f742168c51d545bab6d49584b5dc9467', '930787e5ff154aabad69204af3817abd');
INSERT INTO `sys_role_permission` VALUES ('1814d843f7fa49e7856c371123a692ea', 'f742168c51d545bab6d49584b5dc9467', 'ea23982da45f4bf5b5d6ff42c963e96a');
INSERT INTO `sys_role_permission` VALUES ('2ebdaa254c7442f68368e513ffd062e0', '1057eb16afc84aa0ae30b3b35396d7d9', 'ea23982da45f4bf5b5d6ff42c963e96a');
INSERT INTO `sys_role_permission` VALUES ('461e37af0b2a4811b1f4bcb0ef24d405', 'f742168c51d545bab6d49584b5dc9467', 'f5a1299587784941a0961f065925d56d');
INSERT INTO `sys_role_permission` VALUES ('4fe87b41335c49ceb49adf75b787f3ce', 'f742168c51d545bab6d49584b5dc9467', '45a22451a81c4aebb43ca1b71e2f56ca');
INSERT INTO `sys_role_permission` VALUES ('5f1b15bf20e542f2bad260ff8e037783', '1057eb16afc84aa0ae30b3b35396d7d9', '06751d8cfea847d8a61c237ca16edc08');
INSERT INTO `sys_role_permission` VALUES ('60bc55fccb384c5fb7ff2f0a3da93e05', '32cb29e48d5e4be29abe50972ac38fa0', 'f5a1299587784941a0961f065925d56d');
INSERT INTO `sys_role_permission` VALUES ('9c70bad6867342e9b6df242d8fe3d5f3', '32cb29e48d5e4be29abe50972ac38fa0', '06751d8cfea847d8a61c237ca16edc08');
INSERT INTO `sys_role_permission` VALUES ('a757e3fff84a4cfba6138ea29448c9e9', '1057eb16afc84aa0ae30b3b35396d7d9', 'f5a1299587784941a0961f065925d56d');
INSERT INTO `sys_role_permission` VALUES ('dbb3e25f5b1340079a93304176c7b4ec', '1057eb16afc84aa0ae30b3b35396d7d9', '45a22451a81c4aebb43ca1b71e2f56ca');
INSERT INTO `sys_role_permission` VALUES ('eff0ac4e7bc84b68918c36ff27d0c471', '32cb29e48d5e4be29abe50972ac38fa0', '930787e5ff154aabad69204af3817abd');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `USERNAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `NICKNAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `PASSWORD` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `AGE` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `SEX` int(11) NULL DEFAULT NULL COMMENT '性别 1:男 2:女',
  `BIRTHDAY` timestamp(0) NULL DEFAULT NULL COMMENT '生日',
  `PHONE` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `EMAIL` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `PROVINCE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省',
  `CITY` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '市',
  `AREA` varchar(50) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL COMMENT '区',
  `ADDRESS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '住址',
  `USER_TYPE` int(1) NULL DEFAULT NULL COMMENT '用户类型 1:后台用户 0:前台用户',
  `HEAD_IMAGE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '后台用户头像',
  `DEPT_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门id 一个用户只有 一个部门',
  `COMP_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司id',
  `LAST_LOGIN_IP` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `LAST_LOGIN_TIME` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后登陆时间',
  `IS_DELETED` int(1) NULL DEFAULT NULL COMMENT '是否删除 (1 是  0否)',
  `STATUS` int(1) NULL DEFAULT NULL COMMENT '是否有效 0否(无效) 1是(有效)',
  `CREATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `UPDATE_USER` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('63f30a75fab64a81b5454bfe4b194056', 'gs', '公司管理员', '$2a$10$N52.f.hH4i2GpxMheg/XGes.UD7f1y.w2riHH.KqfwJOLDbNUP6vO', 24, 1, '1996-12-23 07:08:54', '13699632394', '', '湖北省', '武汉市', '洪山区', '光谷', 1, 'https://wx.qlogo.cn/mmopen/vi_32/ajNVdqHZLLANP0gcO1TDoMdeqonzCgUTckiaJwA2DHR71HrbTlicIOyDzWX9B04t1jHEORbPicccobeDOjyTCliaIQ/132', NULL, 'ae6e484f4ba3444b90cdf46a10056560', NULL, '2020-06-24 13:35:07', 0, 1, '2019-12-23 10:12:15', 'bf755585d01d46539a5584a38eca7c98', '2019-12-25 12:19:43', 'bf755585d01d46539a5584a38eca7c98');
INSERT INTO `sys_user` VALUES ('9ca92a697f4e4bb7b284a51969714275', 'bm', '部门管理员', '$2a$10$HRoLELhiOkGiXxpX6iwhfeEVkX4C2wIIi9sNNwAQM9xMv06AkBNQq', 24, 2, '1996-12-23 07:08:54', '13699632394', '', '湖北省', '武汉市', '洪山区', '光谷', 1, 'https://wx.qlogo.cn/mmopen/vi_32/ajNVdqHZLLANP0gcO1TDoMdeqonzCgUTckiaJwA2DHR71HrbTlicIOyDzWX9B04t1jHEORbPicccobeDOjyTCliaIQ/132', '623c541008f24dbc8d220e812c5aea30', 'ae6e484f4ba3444b90cdf46a10056560', NULL, '2020-06-24 13:35:11', 0, 1, '2019-12-23 10:14:02', 'bf755585d01d46539a5584a38eca7c98', '2020-06-20 18:53:32', 'bf755585d01d46539a5584a38eca7c98');
INSERT INTO `sys_user` VALUES ('bf755585d01d46539a5584a38eca7c98', 'admin', '超级管理员', '$2a$10$2O.A6cHczwBQR5M3s8FQnOfV2/WpZguhWzvALTPyhL39nnWjvyyoe', 18, 1, '1997-04-04 16:00:01', '15623590149', 'aomrlee@gmail.com', '湖北省', '武汉市', '洪山区', '湖北省武汉市洪山区光谷', 1, 'https://wx.qlogo.cn/mmopen/vi_32/ajNVdqHZLLANP0gcO1TDoMdeqonzCgUTckiaJwA2DHR71HrbTlicIOyDzWX9B04t1jHEORbPicccobeDOjyTCliaIQ/132', NULL, NULL, '0:0:0:0:0:0:0:1', '2020-06-24 13:35:13', 0, 1, NULL, NULL, '2020-06-23 15:15:34', 'bf755585d01d46539a5584a38eca7c98');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ROLE_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `USER_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('099e270e68544997a2738623c9b322df', 'f742168c51d545bab6d49584b5dc9467', '9ca92a697f4e4bb7b284a51969714275');
INSERT INTO `sys_user_role` VALUES ('3fdbda0318004712b93e4e0893581889', '1057eb16afc84aa0ae30b3b35396d7d9', 'bf755585d01d46539a5584a38eca7c98');
INSERT INTO `sys_user_role` VALUES ('c39db21c94a34fc3bc4a82a04a94de72', 'f742168c51d545bab6d49584b5dc9467', '63f30a75fab64a81b5454bfe4b194056');

SET FOREIGN_KEY_CHECKS = 1;
