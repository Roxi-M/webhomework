/*
 Navicat Premium Data Transfer

 Source Server         : SuperMarket
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : roxi

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 16/12/2018 23:35:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `usename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `state` int(255) NOT NULL,
  PRIMARY KEY (`usename`, `password`, `state`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '1', 1);
INSERT INTO `users` VALUES ('1123', '113', 1);
INSERT INTO `users` VALUES ('123', '123', 1);
INSERT INTO `users` VALUES ('123', '1234', 1);
INSERT INTO `users` VALUES ('1231234', '231/sda', 1);
INSERT INTO `users` VALUES ('1234', '123', 1);
INSERT INTO `users` VALUES ('12345', '12345', 1);
INSERT INTO `users` VALUES ('13', '231', 1);
INSERT INTO `users` VALUES ('13321', '231414', 1);

SET FOREIGN_KEY_CHECKS = 1;
