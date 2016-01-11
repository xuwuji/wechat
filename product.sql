/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : wechat

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2016-01-11 17:18:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `category` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `count` int(11) NOT NULL,
  `picUrl` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `time` varchar(255) NOT NULL,
  `flag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', 'lv', 'dsfdsfds', 'bag', '10000', '10', 'http://7xpxq6.com1.z0.glb.clouddn.com/Tulips.jpg-1', 'www.baidu.com', '2016-01-11 15:40', '1');
INSERT INTO `product` VALUES ('2', 'aaa', 'dsf', 'bag', '231', '10', 'http://7xpxq6.com1.z0.glb.clouddn.com/Tulips.jpg', 'www.baidu.com', '2016-01-11 15:56', '1');
INSERT INTO `product` VALUES ('3', 'aaa', 'dsf', 'bag', '231', '10', 'http://7xpxq6.com1.z0.glb.clouddn.com/Tulips.jpg', 'www.baidu.com', '2016-01-11 15:57', '1');
INSERT INTO `product` VALUES ('4', 'aaa', 'dsf', 'bag', '231', '10', 'http://7xpxq6.com1.z0.glb.clouddn.com/Tulips.jpg2016-01-11 15:58', 'www.baidu.com', '2016-01-11 15:58', '1');
INSERT INTO `product` VALUES ('5', 'aaa', 'dsf', 'bag', '231', '10', 'http://7xpxq6.com1.z0.glb.clouddn.com/Tulips.jpg-2016-01-11 16:00', 'www.baidu.com', '2016-01-11 16:00', '1');
INSERT INTO `product` VALUES ('6', 'aaa', 'dsfdsfds', 'bag', '10000', '10', 'http://7xpxq6.com1.z0.glb.clouddn.com/Tulips.jpg-2016-01-11 16:48', 'ds', '2016-01-11 16:48', '1');
