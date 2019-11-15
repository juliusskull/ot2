/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : ot3

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2019-05-20 23:11:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `materiales_ot`
-- ----------------------------
DROP TABLE IF EXISTS `materiales_ot`;
CREATE TABLE `materiales_ot` (
  `id_material_ot` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(50) DEFAULT NULL,
  `ot` varchar(20) DEFAULT NULL,
  `cantidad` varchar(20) DEFAULT NULL,
  `usuario` varchar(50) DEFAULT NULL,
  `fchalta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_material_ot`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of materiales_ot
-- ----------------------------
INSERT INTO `materiales_ot` VALUES ('1', '1', '', '1', 'usuario', '2019-05-20 23:09:48');
