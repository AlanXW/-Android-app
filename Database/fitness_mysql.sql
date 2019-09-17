/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : fitness_mysql

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-04-12 09:37:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `newsId` int(11) NOT NULL,
  `comment` varchar(255) NOT NULL,
  `replyUser` varchar(20) DEFAULT '',
  `commentTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('1', '1', '1', 'Good', 'wx', '2018-04-10 19:00:57', '0');
INSERT INTO `comments` VALUES ('2', '2', '1', 'Nice', 'Alan', '2018-04-10 19:01:05', '0');
INSERT INTO `comments` VALUES ('3', '1', '2', 'Great', 'wx', '2018-04-10 19:01:08', '0');
INSERT INTO `comments` VALUES ('4', '1', '2', 'Amazing', 'wx', '2018-04-10 19:01:12', '0');
INSERT INTO `comments` VALUES ('5', '1', '1', 'Fantastic', 'wx', '2018-04-10 19:01:40', '0');
INSERT INTO `comments` VALUES ('6', '2', '1', 'Happy', 'Alan', '2018-04-10 19:01:55', '0');
INSERT INTO `comments` VALUES ('7', '2', '1', 'Yes', 'Alan', '2018-04-10 19:03:15', '0');
INSERT INTO `comments` VALUES ('8', '1', '2', 'Hello', 'wx', '2018-04-10 19:03:22', '0');
INSERT INTO `comments` VALUES ('9', '5', '4', '123', '', '2018-04-12 08:38:24', '0');

-- ----------------------------
-- Table structure for dailycheck
-- ----------------------------
DROP TABLE IF EXISTS `dailycheck`;
CREATE TABLE `dailycheck` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `checkDate` date NOT NULL,
  `checkTime` time DEFAULT NULL,
  `status` int(2) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dailycheck
-- ----------------------------
INSERT INTO `dailycheck` VALUES ('1', '1', '2018-04-06', '22:29:43', '0');
INSERT INTO `dailycheck` VALUES ('2', '1', '2018-04-07', '22:29:46', '0');
INSERT INTO `dailycheck` VALUES ('3', '1', '2018-04-08', '07:31:44', '0');
INSERT INTO `dailycheck` VALUES ('4', '1', '2018-04-09', '19:56:04', '0');
INSERT INTO `dailycheck` VALUES ('5', '1', '2018-04-10', '14:28:58', '0');

-- ----------------------------
-- Table structure for favors
-- ----------------------------
DROP TABLE IF EXISTS `favors`;
CREATE TABLE `favors` (
  `favorId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `newsId` int(11) NOT NULL,
  `favorTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`favorId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of favors
-- ----------------------------
INSERT INTO `favors` VALUES ('1', '1', '1', '2018-04-10 16:44:47', '0');
INSERT INTO `favors` VALUES ('2', '2', '1', '2018-04-10 16:44:54', '0');
INSERT INTO `favors` VALUES ('3', '1', '5', '2018-04-10 13:30:07', '0');
INSERT INTO `favors` VALUES ('4', '1', '4', '2018-04-10 15:41:31', '0');
INSERT INTO `favors` VALUES ('5', '0', '3', '2018-04-10 18:58:16', '0');
INSERT INTO `favors` VALUES ('6', '1', '2', '2018-04-10 19:04:00', '0');
INSERT INTO `favors` VALUES ('7', '0', '4', '2018-04-11 13:50:51', '0');
INSERT INTO `favors` VALUES ('8', '5', '4', '2018-04-12 08:38:14', '0');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `newsId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `releaseTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`newsId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('1', '1', 'First Post', 'Nice!', 'ac99f6db-80ca-443d-a814-eda47e980650.png', '2018-04-10 18:51:25', '0');
INSERT INTO `news` VALUES ('2', '2', 'Good Application', 'This application is very good.', '6a9aa202-90c2-4c36-8224-199d8997cc2b#icon.jpg', '2018-04-10 18:52:58', '0');
INSERT INTO `news` VALUES ('3', '1', 'AB Ripper x', 'Practice abdominal muscles', 'fb9c2f3f-5e11-4d1e-8476-54f0aa09064a#icon.jpg', '2018-04-10 18:55:12', '0');
INSERT INTO `news` VALUES ('4', '2', 'Question', 'What should I eat to be healthy?', '592d1962-7d65-4757-a647-27ba4402401a#icon.jpg', '2018-04-10 18:57:19', '0');
INSERT INTO `news` VALUES ('5', '2', 'Tired', 'Sleep', 'ac99f6db-80ca-443d-a814-eda47e980650.png', '2018-04-10 18:58:20', '0');

-- ----------------------------
-- Table structure for training
-- ----------------------------
DROP TABLE IF EXISTS `training`;
CREATE TABLE `training` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `trainTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `duration` int(2) NOT NULL,
  `status` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of training
-- ----------------------------
INSERT INTO `training` VALUES ('1', '1', '2018-04-10 09:01:46', '8', '0');
INSERT INTO `training` VALUES ('2', '1', '2018-04-10 09:01:48', '8', '0');
INSERT INTO `training` VALUES ('3', '1', '2018-04-10 08:43:19', '8', '0');
INSERT INTO `training` VALUES ('4', '1', '2018-04-10 08:56:40', '8', '0');
INSERT INTO `training` VALUES ('5', '1', '2018-04-10 09:01:27', '8', '0');
INSERT INTO `training` VALUES ('6', '0', '2018-04-11 13:49:14', '2', '0');
INSERT INTO `training` VALUES ('7', '0', '2018-04-12 08:54:57', '0', '0');
INSERT INTO `training` VALUES ('8', '0', '2018-04-12 08:55:02', '0', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `sex` varchar(6) NOT NULL,
  `height` double DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `registerTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Wang Xin', '123', 'male', '187', '72', '2018-04-10 18:45:56', '0');
INSERT INTO `user` VALUES ('2', 'Alan', '456', 'male', '183', '70', '2018-04-10 19:04:05', '0');
INSERT INTO `user` VALUES ('4', 'test', '123', 'male', '190', '80', '2018-04-11 04:07:33', '0');
INSERT INTO `user` VALUES ('5', 'wx', '123', 'male', '183', '70', '2018-04-11 13:45:25', '0');
INSERT INTO `user` VALUES ('6', 'test2', '123', 'male', '190', '80', '2018-04-12 08:54:12', '0');
INSERT INTO `user` VALUES ('7', 'test3', '123', 'female', '170', '50', '2018-04-12 08:54:38', '0');
