/*
 Navicat Premium Data Transfer

 Source Server         : me本地
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : sophia

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 29/05/2020 11:11:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_api_logger
-- ----------------------------
DROP TABLE IF EXISTS `sys_api_logger`;
CREATE TABLE `sys_api_logger`  (
  `ID` bigint(11) NOT NULL,
  `URI` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'url',
  `METHOD_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `CLASS_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类名',
  `CREATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '访问时间',
  `USER_ID` bigint(11) NULL DEFAULT NULL COMMENT '访问用户',
  `USER_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `PARAMS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数',
  `IP` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问ip',
  `METHOD` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方式',
  `TIMES` bigint(11) NULL DEFAULT NULL COMMENT '耗时',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日志' ROW_FORMAT = Dynamic;

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
  `COMP_ID` bigint(11) NULL DEFAULT NULL COMMENT '部门所在的公司id',
  `CREATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` bigint(11) NULL DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `UPDATE_USER` bigint(11) NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, 1, NULL, '[1]', 'AliBaba', '阿里巴巴', NULL, NULL, 0, NULL, '2019-11-05 13:46:10', 1, NULL, NULL);
INSERT INTO `sys_dept` VALUES (2, 1, 1, '[1],[2]', '技术部', '技术部', NULL, NULL, 1, 1, '2019-11-05 13:46:14', 1, NULL, NULL);
INSERT INTO `sys_dept` VALUES (3, 1, 2, '[1],[2],[3]', '研发一部', '研发一部', NULL, NULL, 1, 1, '2019-11-22 16:22:33', 1, NULL, NULL);
INSERT INTO `sys_dept` VALUES (4, 2, 2, '[1],[2],[4]', '研发二部', '研发二部', NULL, NULL, 1, 1, '2019-11-22 16:22:33', 1, NULL, NULL);
INSERT INTO `sys_dept` VALUES (5, 2, NULL, '[5]', 'TenXun', '腾讯', NULL, NULL, 0, NULL, '2019-11-22 16:22:33', 1, NULL, NULL);
INSERT INTO `sys_dept` VALUES (6, 3, NULL, '[6]', 'xx科技', 'XX科技有限公司', '互联网', '湖北省武汉市洪山区关南村', 0, NULL, '2019-11-27 17:30:25', 1, NULL, NULL);
INSERT INTO `sys_dept` VALUES (7, 4, NULL, '[7]', 'XX信息', 'XX信息产业有限公司', '物流,互联网', '湖北省武汉市江夏区藏龙岛', 0, NULL, '2019-11-27 17:36:40', 1, NULL, NULL);

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
INSERT INTO `sys_oauth_client_details` VALUES ('amor-admin', NULL, 'amor-admin-secret', 'all,read,write', 'password,refresh_token', NULL, NULL, 21600, 2592000, NULL, 'true');
INSERT INTO `sys_oauth_client_details` VALUES ('sophia-admin', NULL, 'sophia-admin-secret', 'all,read,write', 'password,refresh_token,authorization_code,client_credentials', NULL, NULL, 21600, 2592000, NULL, 'true');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `ID` bigint(11) NOT NULL,
  `NAME` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权限名称',
  `CODE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `URL_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路径',
  `PID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级权限',
  `TYPE` int(1) NULL DEFAULT NULL COMMENT '菜单类型 （0菜单 1按钮）',
  `ICON_PATH` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `SORT` int(1) NULL DEFAULT NULL COMMENT '排序',
  `STATUS` int(1) NULL DEFAULT NULL COMMENT '是否有效(0无效，1有效)',
  `CREATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` bigint(11) NULL DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `UPDATE_USER` bigint(11) NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '系统管理', 'system', '/system', NULL, 0, NULL, 1, 1, NULL, NULL, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '系统管理员', 'admin', '超级管理员', NULL, '2019-09-27 16:10:23', NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (2, '公司管理员', 'COM_ADMIN', '公司管理员', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (3, '部门管理员', 'DEPT_ADMIN', '部门管理员', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_auth`;
CREATE TABLE `sys_role_auth`  (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` bigint(11) NULL DEFAULT NULL COMMENT '角色id',
  `AUTH_ID` bigint(11) NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_auth
-- ----------------------------
INSERT INTO `sys_role_auth` VALUES (1, 1, 1);
INSERT INTO `sys_role_auth` VALUES (2, 2, 1);
INSERT INTO `sys_role_auth` VALUES (3, 3, 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `NICKNAME` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `PASSWORD` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `AGE` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `SEX` int(11) NULL DEFAULT NULL COMMENT '性别',
  `BIRTHDAY` timestamp(0) NULL DEFAULT NULL COMMENT '生日',
  `PHONE` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `EMAIL` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `PROVINCE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省',
  `CITY` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '市',
  `AREA` varchar(50) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL COMMENT '区',
  `ADDRESS` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '住址',
  `CREATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `CREATE_USER` bigint(11) NULL DEFAULT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp(0) NULL DEFAULT NULL COMMENT '修改时间',
  `UPDATE_USER` bigint(11) NULL DEFAULT NULL COMMENT '修改人',
  `LAST_LOGIN_IP` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `LAST_LOGIN_TIME` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后登陆时间',
  `IS_DELETED` int(1) NULL DEFAULT NULL COMMENT '是否删除 (0 是  1否)',
  `STATUS` int(1) NULL DEFAULT NULL COMMENT '状态 0无效 1有效',
  `HEAD_IMAGE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `DEPT_ID` bigint(11) NULL DEFAULT NULL COMMENT '部门id 一个用户只有 一个部门',
  `COMP_ID` bigint(11) NULL DEFAULT NULL COMMENT '公司id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '超级管理员', '$2a$10$ts07XkBaX7OwC5xA449gh.MO1Sa3KfyJlcx./lZKkMEgP8XDSoR9e', 18, 1, '1997-04-04 16:00:01', '15271808834', 'aomrlee412@gmail.com', '湖北省', '武汉市', '洪山区', '湖北省武汉市洪山区光谷', '2019-10-29 07:17:27', NULL, '2019-12-06 15:02:13', 1, '0:0:0:0:0:0:0:1', '2019-12-06 15:02:13', 1, 1, '1/2/ts_Penguins.jpg', NULL, NULL);
INSERT INTO `sys_user` VALUES (2, 'gs', '公司管理员', '$2a$10$ts07XkBaX7OwC5xA449gh.MO1Sa3KfyJlcx./lZKkMEgP8XDSoR9e', 18, 1, '1997-05-18 00:00:01', '18986091954', '1054868512@qq.com', '湖北省', '武汉市', '洪山区', '湖北省武汉市洪山区光谷', '2019-11-05 15:20:14', 1, '2019-11-05 15:20:14', 1, NULL, '2019-11-22 19:54:49', 1, 1, NULL, NULL, 5);
INSERT INTO `sys_user` VALUES (3, 'bm', '部门管理员', '$2a$10$ts07XkBaX7OwC5xA449gh.MO1Sa3KfyJlcx./lZKkMEgP8XDSoR9e', 18, 0, '1997-05-18 00:00:01', '18923689269', '2359878269@qq.com', '湖北省', '武汉市', '洪山区', '湖北省武汉市洪山区光谷', '2019-11-05 15:20:10', 1, '2019-11-05 15:20:10', 1, NULL, '2019-11-22 16:33:33', 1, 1, NULL, 2, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` bigint(11) NULL DEFAULT NULL COMMENT '角色id',
  `USER_ID` bigint(11) NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2, 2);
INSERT INTO `sys_user_role` VALUES (3, 3, 3);

SET FOREIGN_KEY_CHECKS = 1;
