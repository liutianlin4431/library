/*
 Navicat Premium Data Transfer

 Source Server         : 123.57.110.144
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 123.57.110.144:3306
 Source Schema         : shiro_test

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 06/11/2020 16:25:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Role
-- ----------------------------
DROP TABLE IF EXISTS `Role`;
CREATE TABLE `Role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of Role
-- ----------------------------
INSERT INTO `Role` VALUES (1, 'sys');
INSERT INTO `Role` VALUES (2, 'user');

-- ----------------------------
-- Table structure for User
-- ----------------------------
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `role` int NOT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of User
-- ----------------------------
INSERT INTO `User` VALUES (1, 'admin', 'df655ad8d3229f3269fad2a8bab59b6c', 1);
INSERT INTO `User` VALUES (2, 'user', 'f3206cba20994d17d5e0a47abe8648f9', 2);

SET FOREIGN_KEY_CHECKS = 1;
