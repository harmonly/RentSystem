/*
 Navicat Premium Data Transfer

 Source Server         : 本地MySQL
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : rent_system

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 28/04/2022 08:16:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'test', '123');

-- ----------------------------
-- Table structure for house
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` int(11) NOT NULL,
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_rent` int(11) NOT NULL,
  `b_time` datetime NOT NULL,
  `e_time` datetime NOT NULL,
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `house___fk_owner_id`(`owner_id`) USING BTREE,
  CONSTRAINT `house___fk_owner_id` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of house
-- ----------------------------
INSERT INTO `house` VALUES (1, 1, '京绫屋', 10000, '四川省成都市', 0, '2022-04-26 20:31:13', '2022-05-27 20:31:17', 'assets/img/icon/1.svg');
INSERT INTO `house` VALUES (2, 2, '馨清佳苑', 3500, '四川省成都市', 1, '2022-03-30 20:43:25', '2022-05-20 20:43:28', 'assets/img/icon/2.svg');
INSERT INTO `house` VALUES (3, 2, '旺座新天地', 2500, '四川省达州市', 0, '2021-06-01 20:44:44', '2022-09-01 20:44:47', 'assets/img/icon/3.svg');
INSERT INTO `house` VALUES (4, 3, '绿中海明苑', 9500, '四川省成都市', 0, '2022-04-26 20:52:09', '2023-03-01 20:52:12', 'assets/img/icon/4.svg');
INSERT INTO `house` VALUES (5, 3, '华益村', 750, '重庆市', 0, '2022-04-26 20:53:13', '2022-05-25 20:53:18', 'assets/img/icon/5.svg');
INSERT INTO `house` VALUES (6, 1, '禹州商业广场', 800, '上海市', 0, '2022-04-26 21:51:11', '2022-05-08 21:51:14', 'assets/img/icon/6.svg');
INSERT INTO `house` VALUES (7, 2, '金沙湾绿地和苑', 3500, '广东省广州市', 0, '2022-03-30 21:52:21', '2022-05-08 21:52:30', 'assets/img/icon/1.svg');
INSERT INTO `house` VALUES (10, 1, '天下第一', 100000, '北京市', 0, '2022-04-27 14:10:38', '2023-06-30 00:00:00', 'assets/img/icon/5.svg');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buy_id` int(11) NULL DEFAULT NULL,
  `owner_id` int(11) NULL DEFAULT NULL,
  `house_id` int(11) NULL DEFAULT NULL,
  `b_time` datetime NULL DEFAULT NULL,
  `e_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order___fk_buy_id`(`buy_id`) USING BTREE,
  INDEX `order___fk_house_id`(`house_id`) USING BTREE,
  INDEX `order___fk_owner_id`(`owner_id`) USING BTREE,
  CONSTRAINT `order___fk_buy_id` FOREIGN KEY (`buy_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order___fk_house_id` FOREIGN KEY (`house_id`) REFERENCES `house` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order___fk_owner_id` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (8, 1, 2, 2, '2022-04-27 23:13:52', '2022-08-25 23:13:52');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_account_uindex`(`account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '测试账号1', '1001', '123', '1111111111');
INSERT INTO `user` VALUES (2, '测试账号2', '1002', '123', '23333');
INSERT INTO `user` VALUES (3, '测试账号3', '1003', '123', '3333333');
INSERT INTO `user` VALUES (5, '测试账号4', '1004', '123', '4565456465');
INSERT INTO `user` VALUES (7, '测试账号5', '1005', '123', '5555555555');

SET FOREIGN_KEY_CHECKS = 1;
