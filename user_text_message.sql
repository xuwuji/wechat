/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : wechat

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2016-01-11 17:16:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user_text_message`
-- ----------------------------
DROP TABLE IF EXISTS `user_text_message`;
CREATE TABLE `user_text_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userOpenId` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time` varchar(255) NOT NULL DEFAULT 'CURRENT_TIMESTAMP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of user_text_message
-- ----------------------------
INSERT INTO `user_text_message` VALUES ('1', 'oLUO5jm7NHn7cMTU6HYZXI52Eavw', '?', '1452503567');
INSERT INTO `user_text_message` VALUES ('2', 'oLUO5jm7NHn7cMTU6HYZXI52Eavw', '?', '1452503647');
