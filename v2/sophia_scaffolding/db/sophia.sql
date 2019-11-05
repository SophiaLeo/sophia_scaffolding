/*
 Navicat Premium Data Transfer

 Source Server         : me
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : sophia

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 05/11/2019 10:38:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_api_logger
-- ----------------------------
DROP TABLE IF EXISTS `sys_api_logger`;
CREATE TABLE `sys_api_logger`  (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `URI` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'url',
  `METHOD_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `CLASS_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类名',
  `CREATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '访问时间',
  `TIMES` int(11) NULL DEFAULT NULL COMMENT '耗时',
  `USER_ID` int(11) NULL DEFAULT NULL COMMENT '访问用户',
  `IP` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问ip',
  `METHOD` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方式',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth`;
CREATE TABLE `sys_auth`  (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `AUTH_NAME` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权限名称',
  `AUTH_CODE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编号',
  `AUTH_URL` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'AUTH_URL',
  `CREATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` bigint(11) NULL DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `UPDATE_USER` bigint(11) NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_auth
-- ----------------------------
INSERT INTO `sys_auth` VALUES (1, '系统管理', 'system', '/system', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `NUM` int(11) NULL DEFAULT NULL COMMENT '排序',
  `PID` bigint(11) NULL DEFAULT NULL COMMENT '父部门id',
  `PIDS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级ids',
  `SIMPLE_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '简称',
  `FULL_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '全称',
  `TIPS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '提示',
  `ADDRESS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `DEPT_TYPE` int(11) NULL DEFAULT NULL COMMENT '部门类型(0 公司1部门)',
  `CREATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` bigint(11) NULL DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `UPDATE_USER` bigint(11) NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `VALUE` int(11) NULL DEFAULT NULL COMMENT '字典值',
  `PID` bigint(11) NULL DEFAULT NULL COMMENT '上级ID',
  `NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '字典名称',
  `TYPE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '字典类型',
  `IS_DELETED` int(1) NULL DEFAULT NULL COMMENT '是否删除 (0 是  1否)',
  `CREATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` bigint(11) NULL DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `UPDATE_USER` bigint(11) NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `PID` bigint(11) NULL DEFAULT NULL COMMENT '父级菜单主键',
  `MENU_NAME` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `MENU_PATH` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单路径',
  `MENU_CODE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单编号',
  `CREATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` bigint(11) NULL DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `UPDATE_USER` bigint(11) NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (0, NULL, '顶级菜单', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', '/system', 'system', NULL, NULL, NULL, NULL);

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
INSERT INTO `sys_oauth_client_details` VALUES ('amor-admin', NULL, 'amor-admin-secret', 'all,read,write', 'password,refresh_token', NULL, NULL, 21600, 28800, NULL, 'true');
INSERT INTO `sys_oauth_client_details` VALUES ('sophia-admin', NULL, 'sophia-admin-secret', 'all,read,write', 'password,refresh_token,authorization_code,client_credentials', NULL, NULL, 21600, 28800, NULL, 'true');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色名称',
  `ROLE_CODE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编号',
  `DESCRIPTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `DEPT_ID` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `CREATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` bigint(11) NULL DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `UPDATE_USER` bigint(11) NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '系统管理员', 'admin', '超级管理员', NULL, '2019-09-27 16:10:23', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_auth`;
CREATE TABLE `sys_role_auth`  (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` bigint(11) NULL DEFAULT NULL COMMENT '角色id',
  `AUTH_ID` bigint(11) NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_auth
-- ----------------------------
INSERT INTO `sys_role_auth` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `NICKNAME` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `PASSWORD` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `SEX` int(11) NULL DEFAULT NULL COMMENT '性别',
  `PHONE` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `EMAIL` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `CREATE_TIME` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `CREATE_USER` bigint(11) NULL DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `UPDATE_USER` bigint(11) NULL DEFAULT NULL COMMENT '修改人',
  `LAST_LOGIN_IP` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `LAST_LOGIN_TIME` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后登陆时间',
  `IS_DELETED` int(1) NULL DEFAULT NULL COMMENT '是否删除 (0 是  1否)',
  `STATUS` int(1) NULL DEFAULT NULL COMMENT '状态 0无效 1有效',
  `HEAD_IMAGE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `DEPT_ID` bigint(11) NULL DEFAULT NULL COMMENT '部门id 一个用户只有 一个部门',
  `COMP_ID` bigint(11) NULL DEFAULT NULL COMMENT '公司id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '系统管理员', '$2a$10$ts07XkBaX7OwC5xA449gh.MO1Sa3KfyJlcx./lZKkMEgP8XDSoR9e', 1, '13269630365', '1234@qq.com', '2019-09-27 15:04:26', NULL, '2019-09-27 15:04:26', NULL, '0:0:0:0:0:0:0:1', '2019-09-27 15:04:26', 0, 0, '1/2/ts_Penguins.jpg', 20009, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` bigint(11) NULL DEFAULT NULL COMMENT '角色id',
  `USER_ID` bigint(11) NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
