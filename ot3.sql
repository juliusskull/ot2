/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : ot3

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2019-05-20 20:57:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `configuracion`
-- ----------------------------
DROP TABLE IF EXISTS `configuracion`;
CREATE TABLE `configuracion` (
  `version_db` int(11) NOT NULL,
  `version_fch_actualizacion` text,
  `fotos_ini` int(11) DEFAULT NULL,
  `fotos_fin` int(11) DEFAULT NULL,
  `metros_ini` int(11) DEFAULT NULL,
  `metros_fin` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of configuracion
-- ----------------------------
INSERT INTO `configuracion` VALUES ('23', '2019-01-14 11:19:09', '-1', '-1', '100', '100');

-- ----------------------------
-- Table structure for `configuracion_usuario`
-- ----------------------------
DROP TABLE IF EXISTS `configuracion_usuario`;
CREATE TABLE `configuracion_usuario` (
  `usuario` text,
  `modo_seguro` int(11) NOT NULL DEFAULT '0',
  `id_configuracion_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `intervalo` int(11) NOT NULL DEFAULT '5',
  PRIMARY KEY (`id_configuracion_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of configuracion_usuario
-- ----------------------------
INSERT INTO `configuracion_usuario` VALUES ('usuario', '1', '1', '5');
INSERT INTO `configuracion_usuario` VALUES ('50928', '0', '2', '5');
INSERT INTO `configuracion_usuario` VALUES ('50926', '0', '3', '5');
INSERT INTO `configuracion_usuario` VALUES ('50842', '0', '4', '5');

-- ----------------------------
-- Table structure for `gasto`
-- ----------------------------
DROP TABLE IF EXISTS `gasto`;
CREATE TABLE `gasto` (
  `idGasto` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `monto` int(10) unsigned NOT NULL,
  `etiqueta` varchar(25) NOT NULL,
  `fecha` date NOT NULL,
  `descripcion` varchar(125) DEFAULT NULL,
  `usuario` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idGasto`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of gasto
-- ----------------------------
INSERT INTO `gasto` VALUES ('1', '12', 'Transporte', '2015-07-01', 'Viaje al centro', null);
INSERT INTO `gasto` VALUES ('2', '200', 'Comida', '2015-06-15', 'Mercado mensual', null);
INSERT INTO `gasto` VALUES ('3', '100', 'Diversion', '2015-07-02', 'Salida a cine', null);
INSERT INTO `gasto` VALUES ('4', '25', 'Transporte', '2015-07-04', 'Compra de gasolina', null);
INSERT INTO `gasto` VALUES ('5', '5', 'Comida', '2015-07-06', 'Almuerzo de negocios', null);
INSERT INTO `gasto` VALUES ('6', '1000', 'Comida', '2015-07-11', '', null);
INSERT INTO `gasto` VALUES ('7', '1000', 'Comida', '2015-07-11', '', null);
INSERT INTO `gasto` VALUES ('8', '1000', 'Comida', '2015-07-11', '', null);
INSERT INTO `gasto` VALUES ('9', '1000', 'Comida', '2015-07-11', '', null);
INSERT INTO `gasto` VALUES ('10', '1000', 'Comida', '2015-07-11', '', null);
INSERT INTO `gasto` VALUES ('11', '1000', 'Comida', '2015-07-11', '', null);
INSERT INTO `gasto` VALUES ('12', '1000', 'Comida', '2015-07-11', '', null);
INSERT INTO `gasto` VALUES ('13', '1000', 'Comida', '2015-07-11', '', null);
INSERT INTO `gasto` VALUES ('14', '10', 'Comida', '2015-07-11', '', null);
INSERT INTO `gasto` VALUES ('15', '1000', 'Comida', '2015-07-11', '', null);
INSERT INTO `gasto` VALUES ('16', '10', 'Comida', '2015-07-11', 'nnnn', null);
INSERT INTO `gasto` VALUES ('17', '100', 'Comida', '2016-02-24', '', null);
INSERT INTO `gasto` VALUES ('18', '10', 'Comida', '2017-06-15', 'x1', null);
INSERT INTO `gasto` VALUES ('19', '10', 'Comida', '2015-07-11', '', null);
INSERT INTO `gasto` VALUES ('20', '5', 'Comida', '2015-07-11', '', null);
INSERT INTO `gasto` VALUES ('21', '8', 'Comida', '2015-07-11', '', null);
INSERT INTO `gasto` VALUES ('22', '1', 'Comida', '2015-07-11', 'usuario', null);
INSERT INTO `gasto` VALUES ('23', '14', 'Comida', '2015-07-11', 'prueba', null);
INSERT INTO `gasto` VALUES ('24', '1', 'Comida', '2015-07-11', '2', null);
INSERT INTO `gasto` VALUES ('25', '1', 'Comida', '2015-07-11', 'Q', 'julio@gmail');

-- ----------------------------
-- Table structure for `materiales`
-- ----------------------------
DROP TABLE IF EXISTS `materiales`;
CREATE TABLE `materiales` (
  `id_material` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(20) DEFAULT NULL,
  `desc_material` varchar(200) DEFAULT NULL,
  `observacion` varchar(1000) DEFAULT NULL,
  `umb` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id_material`)
) ENGINE=InnoDB AUTO_INCREMENT=1576 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of materiales
-- ----------------------------
INSERT INTO `materiales` VALUES ('2', 'T 30100011', 'ALAMBRE D/ACERO RECUBIERTO D/Cu D=3.26MM', null, 'KG');
INSERT INTO `materiales` VALUES ('3', 'T 30100012', 'CABLE MULTIPAR CILIND. PAL  2200/0.40', null, 'M');
INSERT INTO `materiales` VALUES ('4', 'T 30100013', 'CABLE  RELLENO CILIND. 10/0,40', null, 'M');
INSERT INTO `materiales` VALUES ('5', 'T 30100014', 'CABLE RELLENO CILIND. 20/0,40', null, 'M');
INSERT INTO `materiales` VALUES ('6', 'T 30100015', 'CABLE RELLENO CILIND. 30/0,40', null, 'M');
INSERT INTO `materiales` VALUES ('7', 'T 30100016', 'CABLE RELLENO CILIND. 50/0,40', null, 'M');
INSERT INTO `materiales` VALUES ('8', 'T 30100017', 'CABLE RELLENO CILIND. 100/0,40', null, 'M');
INSERT INTO `materiales` VALUES ('9', 'T 30100018', 'CABLE RELLENO CILIND. 200/0,40', null, 'M');
INSERT INTO `materiales` VALUES ('10', 'T 30100019', 'CABLE RELLENO CILIND. 300/0,40', null, 'M');
INSERT INTO `materiales` VALUES ('11', 'T 30100020', 'CABLE RELLENO CILIND. 50/0,60', null, 'M');
INSERT INTO `materiales` VALUES ('12', 'T 30100021', 'CABLE RELLENO CILIND. 100/0,60', null, 'M');
INSERT INTO `materiales` VALUES ('13', 'T 30100022', 'CABLE RELLENO CILIND. 50/0,90', null, 'M');
INSERT INTO `materiales` VALUES ('14', 'T 30100023', 'CABLE RELLENO CILIND. 100/090', null, 'M');
INSERT INTO `materiales` VALUES ('15', 'T 30100024', 'CABLE MULTIPAR FORMA 8 RELLENO 10/0,40', null, 'M');
INSERT INTO `materiales` VALUES ('16', 'T 30100025', 'CABLE MULTIPAR FORMA 8 RELLENO 20/0,40', null, 'M');
INSERT INTO `materiales` VALUES ('17', 'T 30100026', 'CABLE MULTIPAR FORMA 8 RELLENO 30/0,40', null, 'M');
INSERT INTO `materiales` VALUES ('18', 'T 30100027', 'CABLE MULTIPAR FORMA 8 RELLENO 50/0,40', null, 'M');
INSERT INTO `materiales` VALUES ('19', 'T 30100028', 'CABLE MULTIPAR FORMA 8 RELLENO 100/0,40', null, 'M');
INSERT INTO `materiales` VALUES ('20', 'T 30100029', 'CABLE MULTIPAR FORMA 8 RELLENO 200/0,40', null, 'M');
INSERT INTO `materiales` VALUES ('21', 'T 30100030', 'CABLE MULTIPAR FORMA 8 RELLENO 300/0,40', null, 'M');
INSERT INTO `materiales` VALUES ('22', 'T 30100031', 'CABLE MULTIPAR FORMA 8 RELLENO 10/0,60', null, 'M');
INSERT INTO `materiales` VALUES ('23', 'T 30100032', 'CABLE MULTIPAR FORMA 8 RELLENO 20/0,60', null, 'M');
INSERT INTO `materiales` VALUES ('24', 'T 30100033', 'CABLE MULTIPAR FORMA 8 RELLENO 30/0,60', null, 'M');
INSERT INTO `materiales` VALUES ('25', 'T 30100034', 'CABLE MULTIPAR FORMA 8 RELLENO 50/0,60', null, 'M');
INSERT INTO `materiales` VALUES ('26', 'T 30100035', 'CABLE MULTIPAR FORMA 8 RELLENO 100/0,60', null, 'M');
INSERT INTO `materiales` VALUES ('27', 'T 30100036', 'CABLE MULTIPAR FORMA 8 RELLENO 200/0,60', null, 'M');
INSERT INTO `materiales` VALUES ('28', 'T 30100037', 'CABLE MULTIPAR FORMA 8 RELLENO 20/0,90', null, 'M');
INSERT INTO `materiales` VALUES ('29', 'T 30100038', 'CABLE MULTIPAR FORMA 8 RELLENO 30/0,90', null, 'M');
INSERT INTO `materiales` VALUES ('30', 'T 30100039', 'CABLE MULTIPAR P/ENTERRAR 4/0,60', null, 'M');
INSERT INTO `materiales` VALUES ('31', 'T 30100040', 'CABLE MULTIPAR P/ENTERRAR 10/0,60', null, 'M');
INSERT INTO `materiales` VALUES ('32', 'T 30100041', 'CABLE MULTIPAR P/ENTERRAR 30/0,60', null, 'M');
INSERT INTO `materiales` VALUES ('33', 'T 30100042', 'CABLE MULTIPAR P/ENTERRAR 50/0,60', null, 'M');
INSERT INTO `materiales` VALUES ('34', 'T 30100043', 'CABLE MULTIPAR P/ENTERRAR 100/0,60', null, 'M');
INSERT INTO `materiales` VALUES ('35', 'T 30100044', 'CABLE MULTIPAR P/ENTERRAR 200/0,60', null, 'M');
INSERT INTO `materiales` VALUES ('36', 'T 30100045', 'CABLE MULTIPAR CILIND. PAL  100/0.40', null, 'M');
INSERT INTO `materiales` VALUES ('37', 'T 30100046', 'CABLE MULTIPAR CILIND. PAL  200/0.40', null, 'M');
INSERT INTO `materiales` VALUES ('38', 'T 30100047', 'CABLE MULTIPAR CILIND. PAL  300/0.40', null, 'M');
INSERT INTO `materiales` VALUES ('39', 'T 30100048', 'CABLE MULTIPAR CILIND. PAL  600/0.40', null, 'M');
INSERT INTO `materiales` VALUES ('40', 'T 30100049', 'CABLE MULTIPAR CILIND. PAL  100/0.60', null, 'M');
INSERT INTO `materiales` VALUES ('41', 'T 30100050', 'CABLE MULTIPAR CILIND. PAL  200/0.60', null, 'M');
INSERT INTO `materiales` VALUES ('42', 'T 30100051', 'CABLE MULTIPAR CILIND. PAL  300/0.60', null, 'M');
INSERT INTO `materiales` VALUES ('43', 'T 30100052', 'CABLE MULTIPAR CILIND. PAL  600/0.60', null, 'M');
INSERT INTO `materiales` VALUES ('44', 'T 30100053', 'CABLE MULTIPAR CILIND. PAL  100/0.90', null, 'M');
INSERT INTO `materiales` VALUES ('45', 'T 30100054', 'CABLE MULTIPAR CILIND. PAL  900/0.60', null, 'M');
INSERT INTO `materiales` VALUES ('46', 'T 30100055', 'CABLE MULTIPAR CILIND. PAL  900/0.40', null, 'M');
INSERT INTO `materiales` VALUES ('47', 'T 30100056', 'CABLE MULTIPAR CILIND. PAL  1200/0.40', null, 'M');
INSERT INTO `materiales` VALUES ('48', 'T 30100057', 'CABLE MULTIPAR CILIND. PAL  1800/0.40', null, 'M');
INSERT INTO `materiales` VALUES ('49', 'T 30100058', 'CAB.TROZOS 100/200/300 0.40 L.E/40 Y 80M', null, 'M');
INSERT INTO `materiales` VALUES ('50', 'T 30100059', 'CAB.TROZOS 600/900 0.40 LONG.E/40 Y 80 M', null, 'M');
INSERT INTO `materiales` VALUES ('51', 'T 30100060', 'CAB.TROZOS 1200/1800 0.40 L.E/ 80 Y 100M', null, 'M');
INSERT INTO `materiales` VALUES ('52', 'T 30100061', 'CAB.TROZOS 2200 0.40 LONG. E/ 80 Y 100 M', null, 'M');
INSERT INTO `materiales` VALUES ('53', 'T 30100062', 'CABLE TROZO 100/0.6 CILIND. L=40a80 M', null, 'M');
INSERT INTO `materiales` VALUES ('54', 'T 30100063', 'CAB.TROZOS 200/300 0.60 LONG. E/40 Y 80M', null, 'M');
INSERT INTO `materiales` VALUES ('55', 'T 30100064', 'CAB.TROZO 600 0.6 900 0.9 L. E/80 Y 100M', null, 'M');
INSERT INTO `materiales` VALUES ('56', 'T 30100065', 'CABLE T.50?100/0.6?50/0,90 C.REL. 40a80M', null, 'M');
INSERT INTO `materiales` VALUES ('57', 'T 30100066', 'CAB.TROZOS 100 0.90 LONG. E/ 80 Y 100 M', null, 'M');
INSERT INTO `materiales` VALUES ('58', 'T 30100067', 'CABLE UTP 4P,CAT5 D=0,51MM, COLOR MARFIL', null, 'M');
INSERT INTO `materiales` VALUES ('59', 'T 30100068', 'CABLE STP 4P, CAT5 D=0,51MM, COLOR GRIS', null, 'M');
INSERT INTO `materiales` VALUES ('60', 'T 30100069', 'CABLE 64 PARES/0,40 ESTA?ADO PVC/PVC', null, 'M');
INSERT INTO `materiales` VALUES ('61', 'T 30100070', 'CABLE CONEXION A TIERRA P/BLINDAJE CABLE', null, 'UN');
INSERT INTO `materiales` VALUES ('62', 'T 30100071', 'CABLE D/COBRE DESNUDO 50 MM2', null, 'M');
INSERT INTO `materiales` VALUES ('63', 'T 30100074', 'CABLE DOBLE CRUZADA O,5 MM AZUL/AMARILLO', null, 'M');
INSERT INTO `materiales` VALUES ('64', 'T 30100075', 'CABLE DOBLE CRUZADA O,5MM BLANCO/ANARAN.', null, 'M');
INSERT INTO `materiales` VALUES ('65', 'T 30100076', 'CABLE DOBLE CRUZADA 0,5 MM BLANCO/NEGRO', null, 'M');
INSERT INTO `materiales` VALUES ('66', 'T 30100077', 'ALAMBRE TRIPLE CRUZAD.0,5MM AZU/AMA/MAR', null, 'M');
INSERT INTO `materiales` VALUES ('67', 'T 30100078', 'CABLE DOBLE CRUZADA 0,5MM ANARANJ./NEGRO', null, 'M');
INSERT INTO `materiales` VALUES ('68', 'T 30100079', 'CABLE P/ INST.  INTERNA  D/1PAR,  MARFIL', null, 'M');
INSERT INTO `materiales` VALUES ('69', 'T 30100082', 'CABLE 3 PARES/0,50 ESTA?ADO PVC/PVC', null, 'M');
INSERT INTO `materiales` VALUES ('70', 'T 30100083', 'CABLE 5 PARES/0,50 ESTA?ADO PVC/PVC', null, 'M');
INSERT INTO `materiales` VALUES ('71', 'T 30100085', 'CABLE 14 PARES/0,50 ESTA?ADO PVC/PVC', null, 'M');
INSERT INTO `materiales` VALUES ('72', 'T 30100087', 'CABLE 53 PARES/0,50 ESTA?ADO', null, 'M');
INSERT INTO `materiales` VALUES ('73', 'T 30100094', 'CABLE 10 PARES/0,40 ESTA?ADO PVC/PVC', null, 'M');
INSERT INTO `materiales` VALUES ('74', 'T 30100095', 'CABLE 20PARES/0,40 ESTA?ADO PVC/PVC', null, 'M');
INSERT INTO `materiales` VALUES ('75', 'T 30100096', 'CABLE 30PARES/040 ESTA?ADO  PVC/PVC', null, 'M');
INSERT INTO `materiales` VALUES ('76', 'T 30100097', 'CABLE 50 PARES/0,40 ESTA?ADO  PVC/PVC', null, 'M');
INSERT INTO `materiales` VALUES ('77', 'T 30100098', 'CABLE 100 PARES/0,40 ESTA?ADO PVC/PVC', null, 'M');
INSERT INTO `materiales` VALUES ('78', 'T 30100101', 'CABLE COAXIL 3C-2W', null, 'M');
INSERT INTO `materiales` VALUES ('79', 'T 30100103', 'CABLE COAXIL 2YCCY', null, 'M');
INSERT INTO `materiales` VALUES ('80', 'T 30100104', 'CABLE COAXIL 3C-2V', null, 'M');
INSERT INTO `materiales` VALUES ('81', 'T 30100105', 'CABLE COAXIL 2YCY', null, 'M');
INSERT INTO `materiales` VALUES ('82', 'T 30100106', 'CABLE COAXIL RG6', null, 'M');
INSERT INTO `materiales` VALUES ('83', 'T 30100107', 'CABLE COAXIL RG11/AU', null, 'M');
INSERT INTO `materiales` VALUES ('84', 'T 30100108', 'CABLE COAXIL RG59/AU  SIMPLE MALLA', null, 'M');
INSERT INTO `materiales` VALUES ('85', 'T 30100109', 'CABLE MULTICOAXIL 8X2YCY', null, 'M');
INSERT INTO `materiales` VALUES ('86', 'T 30100110', 'CABLE MULTICOAXIL 8XFLEX-2/75', null, 'M');
INSERT INTO `materiales` VALUES ('87', 'T 30100111', 'CABLE COAXIL RG213/U', null, 'M');
INSERT INTO `materiales` VALUES ('88', 'T 30100114', 'CABLE COAXIL 1 5/8\"', null, 'M');
INSERT INTO `materiales` VALUES ('89', 'T 30100122', 'CABLE MULTIPAR (4 PARES)', null, 'M');
INSERT INTO `materiales` VALUES ('90', 'T 30100124', 'CABLE MULTIPAR (16 PARES)', null, 'M');
INSERT INTO `materiales` VALUES ('91', 'T 30100127', 'CABLE BAJ. Y DISTRIB. 1 PAR COLOR NEGRO', null, 'M');
INSERT INTO `materiales` VALUES ('92', 'T 30100128', 'CABLE BAJ. Y DISTRIB. 2 PS COLOR NEGRO', null, 'M');
INSERT INTO `materiales` VALUES ('93', 'T 30100129', 'CABLE BAJ.Y DIST. 1PS C/BLIND. COL.NEGRO', null, 'M');
INSERT INTO `materiales` VALUES ('94', 'T 30100130', 'CABLE BAJ.Y DIST. 2PS C/BLIND. COL.NEGRO', null, 'M');
INSERT INTO `materiales` VALUES ('95', 'T 30100131', 'CABLE DISTRIBUCION 1 PAR COLOR NEGRO', null, 'M');
INSERT INTO `materiales` VALUES ('96', 'T 30100132', 'CABLE DISTRIBUCION 2 PARES COLOR NEGRO', null, 'M');
INSERT INTO `materiales` VALUES ('97', 'T 30100133', 'CABLE DISTRIB. 1PAR C/BLIND. COL. NEGRO', null, 'M');
INSERT INTO `materiales` VALUES ('98', 'T 30100134', 'CABLE DISTRIB. 2 PS C/BLIND. COL. NEGRO', null, 'M');
INSERT INTO `materiales` VALUES ('99', 'T 30100135', 'CABLE MULTIPAR CILIN.PAL 2200/0.4 L=230M', null, 'M');
INSERT INTO `materiales` VALUES ('100', 'T 30100136', 'CABLE MULTIPAR CILIN.PAL 2200/0.4 L=265M', null, 'M');
INSERT INTO `materiales` VALUES ('101', 'T 30100137', 'CABLE MULTIPAR CILIN.PAL 2200/0.4 L=306M', null, 'M');
INSERT INTO `materiales` VALUES ('102', 'T 30100138', 'CABLE MULTIPAR CILIN.PAL 2200/0.4 L=320M', null, 'M');
INSERT INTO `materiales` VALUES ('103', 'T 30100139', 'CABLE MULTIPAR CILIN.PAL  600/0.4 L=230M', null, 'M');
INSERT INTO `materiales` VALUES ('104', 'T 30100140', 'CABLE MULTIPAR CILIN.PAL  600/0.4 L=265M', null, 'M');
INSERT INTO `materiales` VALUES ('105', 'T 30100141', 'CABLE MULTIPAR CILIN.PAL  600/0.4 L=306M', null, 'M');
INSERT INTO `materiales` VALUES ('106', 'T 30100142', 'CABLE MULTIPAR CILIN.PAL  600/0.4 L=320M', null, 'M');
INSERT INTO `materiales` VALUES ('107', 'T 30100143', 'CABLE MULTIPAR CILIN.PAL  600/0.6 L=230M', null, 'M');
INSERT INTO `materiales` VALUES ('108', 'T 30100144', 'CABLE MULTIPAR CILIN.PAL  600/0.6 L=265M', null, 'M');
INSERT INTO `materiales` VALUES ('109', 'T 30100145', 'CABLE MULTIPAR CILIN.PAL  600/0.6 L=306M', null, 'M');
INSERT INTO `materiales` VALUES ('110', 'T 30100146', 'CABLE MULTIPAR CILIN.PAL  600/0.6 L=320M', null, 'M');
INSERT INTO `materiales` VALUES ('111', 'T 30100147', 'CABLE MULTIPAR CILIN.PAL  900/0.6 L=230M', null, 'M');
INSERT INTO `materiales` VALUES ('112', 'T 30100148', 'CABLE MULTIPAR CILIN.PAL  900/0.6 L=265M', null, 'M');
INSERT INTO `materiales` VALUES ('113', 'T 30100149', 'CABLE MULTIPAR CILIN.PAL  900/0.6 L=306M', null, 'M');
INSERT INTO `materiales` VALUES ('114', 'T 30100150', 'CABLE MULTIPAR CILIN.PAL  900/0.6 L=320M', null, 'M');
INSERT INTO `materiales` VALUES ('115', 'T 30100151', 'CABLE MULTIPAR CILIN.PAL  900/0.4 L=230M', null, 'M');
INSERT INTO `materiales` VALUES ('116', 'T 30100152', 'CABLE MULTIPAR CILIN.PAL  900/0.4 L=265M', null, 'M');
INSERT INTO `materiales` VALUES ('117', 'T 30100153', 'CABLE MULTIPAR CILIN.PAL  900/0.4 L=306M', null, 'M');
INSERT INTO `materiales` VALUES ('118', 'T 30100154', 'CABLE MULTIPAR CILIN.PAL  900/0.4 L=320M', null, 'M');
INSERT INTO `materiales` VALUES ('119', 'T 30100155', 'CABLE MULTIPAR CILIN.PAL 1200/0.4 L=230M', null, 'M');
INSERT INTO `materiales` VALUES ('120', 'T 30100156', 'CABLE MULTIPAR CILIN.PAL 1200/0.4 L=265M', null, 'M');
INSERT INTO `materiales` VALUES ('121', 'T 30100157', 'CABLE MULTIPAR CILIN.PAL 1200/0.4 L=306M', null, 'M');
INSERT INTO `materiales` VALUES ('122', 'T 30100158', 'CABLE MULTIPAR CILIN.PAL 1200/0.4 L=320M', null, 'M');
INSERT INTO `materiales` VALUES ('123', 'T 30100159', 'CABLE MULTIPAR CILIN.PAL 1800/0.4 L=230M', null, 'M');
INSERT INTO `materiales` VALUES ('124', 'T 30100160', 'CABLE MULTIPAR CILIN.PAL 1800/0.4 L=265M', null, 'M');
INSERT INTO `materiales` VALUES ('125', 'T 30100161', 'CABLE MULTIPAR CILIN.PAL 1800/0.4 L=306M', null, 'M');
INSERT INTO `materiales` VALUES ('126', 'T 30100162', 'CABLE MULTIPAR CILIN.PAL 1800/0.4 L=320M', null, 'M');
INSERT INTO `materiales` VALUES ('127', 'T 30100163', 'CABLE UTP 4P, CAT5 D=0,51MM, COLOR AZUL', null, 'M');
INSERT INTO `materiales` VALUES ('128', 'T 30100164', 'CABLE COAXIL RG58', null, 'M');
INSERT INTO `materiales` VALUES ('129', 'T 30100165', 'CABLE DE ALIMENTACION D/TEMM WLL', null, 'UN');
INSERT INTO `materiales` VALUES ('130', 'T 30100166', 'JUMPER COAXIL RG213 CONEC.N.H-SMA.M 44CM', null, 'UN');
INSERT INTO `materiales` VALUES ('131', 'T 30100176', 'CABLE COAXIL 1/4\"', null, 'M');
INSERT INTO `materiales` VALUES ('132', 'T 30100177', 'CABLE COAXIL 1/4\" SUPERFEXIBLE', null, 'UN');
INSERT INTO `materiales` VALUES ('133', 'T 30100179', 'CABLE COAXIL7/8\" BANDA 300 A **inhibido*', null, 'M');
INSERT INTO `materiales` VALUES ('134', 'T 30100180', 'CABLE COAXIL 1/2\" RFS LCF12-50J', null, 'M');
INSERT INTO `materiales` VALUES ('135', 'T 30100181', 'CABLE COAXIL 7/8\" BANDA 1 A 2 GHZ', null, 'M');
INSERT INTO `materiales` VALUES ('136', 'T 30100182', 'CABLE COAXIL 7/8\" BANDA 2 A 3 GHZ', null, 'M');
INSERT INTO `materiales` VALUES ('137', 'T 30100183', 'CABLE COAXIL 1/2\" SUPERFLEXIBLE', null, 'M');
INSERT INTO `materiales` VALUES ('138', 'T 30100184', 'GO ELIPTICA ANDREW EWP64-71', null, 'UN');
INSERT INTO `materiales` VALUES ('139', 'T 30100185', 'GO ELIPTICA RFS E70', null, 'UN');
INSERT INTO `materiales` VALUES ('140', 'T 30100186', 'GUIA DE ONDA ANDREW MOD.EWP52-58', null, 'M');
INSERT INTO `materiales` VALUES ('141', 'T 30100187', 'GO ELIPTICA ANDREW EWP63-65', null, 'M');
INSERT INTO `materiales` VALUES ('142', 'T 30100188', 'GO ELIPTICA RFS E65', null, 'M');
INSERT INTO `materiales` VALUES ('143', 'T 30100189', 'GO ELIPTICA ANDREW EWP77-71W', null, 'M');
INSERT INTO `materiales` VALUES ('144', 'T 30100190', 'GO ELIPTICA RFS E78', null, 'M');
INSERT INTO `materiales` VALUES ('145', 'T 30100192', 'CABLE COAXIL 1 5/8\" BANDA 1 A 2 GHZ', null, 'M');
INSERT INTO `materiales` VALUES ('146', 'T 30100193', 'CABLE COAXIL 1 5/8\" BANDA 2 A 3 GHZ', null, 'M');
INSERT INTO `materiales` VALUES ('147', 'T 30200004', 'MOLDE P/BLOQUEO  D/CABLES D=19 A 35MM .', null, 'KI');
INSERT INTO `materiales` VALUES ('148', 'T 30200005', 'MOLDE P/BLOQUEO D/CABLES  D=35 A 50MM', null, 'KI');
INSERT INTO `materiales` VALUES ('149', 'T 30200006', 'MOLDE P/BLOQUEO CAB.D=19a35MM E/SUBREP.', null, 'KI');
INSERT INTO `materiales` VALUES ('150', 'T 30200007', 'MOLDE P/BLOQUEO CAB. D=35a50MM E/SUBREP.', null, 'KI');
INSERT INTO `materiales` VALUES ('151', 'T 30200010', 'CAJA INTERCONEXION D/1 PAR S/PROTECCION', null, 'UN');
INSERT INTO `materiales` VALUES ('152', 'T 30200011', 'CAJA INTERCONEXION D/1 PAR C/ PROTECCION', null, 'UN');
INSERT INTO `materiales` VALUES ('153', 'T 30200015', 'CAJA DISTRIBUCION POR POSTE**inhibido**', null, 'UN');
INSERT INTO `materiales` VALUES ('154', 'T 30200017', 'CAJA EMP Y DIST. C/MOD.222048 P/CAM.DV', null, 'UN');
INSERT INTO `materiales` VALUES ('155', 'T 30200018', 'PEDESTAL PARA DISTRIBUCION DE 10 PARES', null, 'UN');
INSERT INTO `materiales` VALUES ('156', 'T 30200019', 'MODULO CORTE Y PRUEBA P/FIJAR C/TORNILLO', null, 'UN');
INSERT INTO `materiales` VALUES ('157', 'T 30200020', 'CARCAZA P/CAJA DISTRIB. 10 PS. P/FACHADA', null, 'UN');
INSERT INTO `materiales` VALUES ('158', 'T 30200021', 'CAJA INTERCONEXION C/2 CONECT. S/PROTEC.', null, 'UN');
INSERT INTO `materiales` VALUES ('159', 'T 30200022', 'CAJA INTERCONEXION C/2 CONECT. C/PROTEC.', null, 'UN');
INSERT INTO `materiales` VALUES ('160', 'T 30200023', 'CAJA EXT. D/DERIV. P/4 HILOS (ISDN)', null, 'UN');
INSERT INTO `materiales` VALUES ('161', 'T 30200025', 'COMPONENTE CONECTOR 222093/94', null, 'UN');
INSERT INTO `materiales` VALUES ('162', 'T 30200027', 'CONECTOR S/FACILIDAD PROTECCION', null, 'UN');
INSERT INTO `materiales` VALUES ('163', 'T 30200030', 'CONECTOR C/PROTEC.P/CAJAS DISTRI./INTERC', null, 'UN');
INSERT INTO `materiales` VALUES ('164', 'T 30200031', 'CAJA EMPALME RECTO O C/2DERV', null, 'UN');
INSERT INTO `materiales` VALUES ('165', 'T 30200033', 'CAJA EMPALME 3 ? 4 DERIV.', null, 'UN');
INSERT INTO `materiales` VALUES ('166', 'T 30200038', 'SOPORTE P/CAJAS 222403/404P/EMPS AEREOS', null, 'UN');
INSERT INTO `materiales` VALUES ('167', 'T 30200039', 'CONJUNTO D/REINT.P/CAJAS CAT. 222400', null, 'UN');
INSERT INTO `materiales` VALUES ('168', 'T 30200041', 'CONJUNTO D/REINT.P/CAJAS CAT. 222403/404', null, 'UN');
INSERT INTO `materiales` VALUES ('169', 'T 30200042', 'CAJA EMP.VENTILADA P/CABLE DIAM.MAX 25MM', null, 'UN');
INSERT INTO `materiales` VALUES ('170', 'T 30200043', 'CAJA EMP.VENTILADA P/CABLE DIAM.MAX 41MM', null, 'UN');
INSERT INTO `materiales` VALUES ('171', 'T 30200044', 'CAJA EMP.VENTILADA P/CABLE DIAM.MAX 46MM', null, 'UN');
INSERT INTO `materiales` VALUES ('172', 'T 30200045', 'CAJA MEC.P/EMP.PROV.INS.AER.P/CAB H.49MM', null, 'UN');
INSERT INTO `materiales` VALUES ('173', 'T 30200046', 'CAJA EMP PROVIS.INST.AER.P/CAB HASTA49MM', null, 'UN');
INSERT INTO `materiales` VALUES ('174', 'T 30200047', 'CAJA EMP P/ENTERR.**usar 30200160**', null, 'UN');
INSERT INTO `materiales` VALUES ('175', 'T 30200048', 'CAJA EMP P/ENTERR.**usar 30200162**', null, 'UN');
INSERT INTO `materiales` VALUES ('176', 'T 30200049', 'CAJA EMP.RyD P/ENTER**usar 30200163**', null, 'UN');
INSERT INTO `materiales` VALUES ('177', 'T 30200050', 'CAJA EMP.RyD P/ENTER. **usar 30200164**', null, 'UN');
INSERT INTO `materiales` VALUES ('178', 'T 30200051', 'CAJA EMP PRESUR. P/CAB DIAM. 15 A 30 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('179', 'T 30200052', 'CAJA EMP PRESURIZ. P/CAB DIAM. 30A 40MM', null, 'UN');
INSERT INTO `materiales` VALUES ('180', 'T 30200053', 'CAJA EMPS PRESURIZ.P/CAB DIAM. 38 A 45MM', null, 'UN');
INSERT INTO `materiales` VALUES ('181', 'T 30200054', 'CAJA EMPS PRESURIZ.P/CAB DIAM. 55 A 65MM', null, 'UN');
INSERT INTO `materiales` VALUES ('182', 'T 30200055', 'CAJA EMPS PRESURIZ.P/CAB DIAM. 65 A 90MM', null, 'UN');
INSERT INTO `materiales` VALUES ('183', 'T 30200057', 'CONJUNTO D/DERIV.P/CAJ.EMP 30200054/55', null, 'KI');
INSERT INTO `materiales` VALUES ('184', 'T 30200058', 'CAJA EMP NO PRESUR. P/CAB DIAM 8A 22MM', null, 'UN');
INSERT INTO `materiales` VALUES ('185', 'T 30200059', 'CAJA EMP NO PRESUR. P/CAB DIAM. 15A 35MM', null, 'UN');
INSERT INTO `materiales` VALUES ('186', 'T 30200060', 'CAJA EMP NO PRESUR. P/CAB DIAM. 30A 48MM', null, 'UN');
INSERT INTO `materiales` VALUES ('187', 'T 30200061', 'CONJUNTO DE DERIVACIONCAJA DE EMPALME', null, 'KI');
INSERT INTO `materiales` VALUES ('188', 'T 30200062', 'CONJUNTO DE DERIVACION', null, 'KI');
INSERT INTO `materiales` VALUES ('189', 'T 30200066', 'CAJA DISTR. 5PS EQUIPADA P/CAMARA ACCESO', null, 'UN');
INSERT INTO `materiales` VALUES ('190', 'T 30200068', 'CAJA EMP Y DISTRIBUCIONHASTA 200 PARES', null, 'UN');
INSERT INTO `materiales` VALUES ('191', 'T 30200071', 'CIERRE D/EMPALME P/BAJADA 2 PS FORMA \"8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('192', 'T 30200072', 'CONECTOR RELLENO DE 3 VIAS TIPO \"UR\"', null, 'UN');
INSERT INTO `materiales` VALUES ('193', 'T 30200073', 'CONECTOR RELLENO DE 2 VIAS TIPO \"UY\"', null, 'UN');
INSERT INTO `materiales` VALUES ('194', 'T 30200074', 'CONECTOR ABIERTO P/CONDUC. 0.4a0.6 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('195', 'T 30200075', 'CONECTOR ABIERTO P/CONDUC. 0.6 a 0.9 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('196', 'T 30200076', 'CONECTOR RELLENO MINI-PICABOND', null, 'UN');
INSERT INTO `materiales` VALUES ('197', 'T 30200078', 'CONECTOR MODULAR 25 PARES', null, 'UN');
INSERT INTO `materiales` VALUES ('198', 'T 30200079', 'MANGUITO TERMOC. ABIERTO P/CAB. 10a22MM', null, 'KI');
INSERT INTO `materiales` VALUES ('199', 'T 30200080', 'MANGUITO TERMOC. ABIERTO P/CAB. 16a36MM', null, 'KI');
INSERT INTO `materiales` VALUES ('200', 'T 30200081', 'MANGUITO TERMOC. ABIERTO P/CAB. 24a53MM', null, 'KI');
INSERT INTO `materiales` VALUES ('201', 'T 30200082', 'MANGUITO TERMOC. ABIERTO P/CAB. 41a95MM', null, 'KI');
INSERT INTO `materiales` VALUES ('202', 'T 30200096', 'TAPA P/CAJA D/DIST D/10 PS CAT. 222025', null, 'UN');
INSERT INTO `materiales` VALUES ('203', 'T 30200097', 'TAPA P/CAJA D/DIST D/20 PS CAT. 222026', null, 'UN');
INSERT INTO `materiales` VALUES ('204', 'T 30200098', 'TAPA CAJA DISTRIBUCIONPARA 222036', null, 'UN');
INSERT INTO `materiales` VALUES ('205', 'T 30200099', 'TAPA CAJA DISTRIB. PARA 222040 Y 222044', null, 'UN');
INSERT INTO `materiales` VALUES ('206', 'T 30200100', 'CARCAZA P/CAJAS DISTRIB. 20 P/POS O FACH', null, 'UN');
INSERT INTO `materiales` VALUES ('207', 'T 30200101', 'TAPA P/CAJA DE DIST. POUYET-3M 10 PARES', null, 'UN');
INSERT INTO `materiales` VALUES ('208', 'T 30200102', 'TAPA P/CAJA DE DIST. MELLER 10 PARES', null, 'UN');
INSERT INTO `materiales` VALUES ('209', 'T 30200103', 'CONEC. C/PROT. P/CAJ. D/DIST. 222040/044', null, 'UN');
INSERT INTO `materiales` VALUES ('210', 'T 30200104', 'CAJA D/EMP VERT.D92MM A215MM C.E. D30MM', null, 'UN');
INSERT INTO `materiales` VALUES ('211', 'T 30200105', 'CAJA EMPALME VERITCAL, D=92MM, ALT=310MM', null, 'UN');
INSERT INTO `materiales` VALUES ('212', 'T 30200106', 'CAJA EMPALME VERITCAL, D=92MM, ALT=430MM', null, 'UN');
INSERT INTO `materiales` VALUES ('213', 'T 30200107', 'CAJA EMPALME VERITCAL,D=125MM, ALT=430MM', null, 'UN');
INSERT INTO `materiales` VALUES ('214', 'T 30200111', 'BLOQUEO C/VAL.P/SUBREP.P/CAB D=49 A 65MM', null, 'KI');
INSERT INTO `materiales` VALUES ('215', 'T 30200112', 'BLOQUEO C/VAL. P/TUNEL P/CAB D=22 A 49MM', null, 'KI');
INSERT INTO `materiales` VALUES ('216', 'T 30200113', 'BLOQUEO C/VAL. P/TUNEL P/CAB D=55 A 69MM', null, 'KI');
INSERT INTO `materiales` VALUES ('217', 'T 30200118', 'RESINA P/BLOQUEO D/CABLES RELL. D/300GR', null, 'KI');
INSERT INTO `materiales` VALUES ('218', 'T 30200119', 'RESINA P/BLOQUEO D/CABLES RELL D/600GR', null, 'KI');
INSERT INTO `materiales` VALUES ('219', 'T 30200122', 'BLOQUEO C/VAL.P/SUBREP.P/CAB D=22A 45MM', null, 'KI');
INSERT INTO `materiales` VALUES ('220', 'T 30200130', 'CARCAZA P/CAJA DISTR.10P POSTE O FACHADA', null, 'UN');
INSERT INTO `materiales` VALUES ('221', 'T 30200170', 'CIERRE D/EMPALME P/CABLE BAJADA D/1 PAR', null, 'UN');
INSERT INTO `materiales` VALUES ('222', 'T 30200180', 'CAJA EMP. Y DIST. P10PS, POSTE O FACHADA', null, 'UN');
INSERT INTO `materiales` VALUES ('223', 'T 30200190', 'CAJA DE INTERCONEXION CON SPLITTER', null, 'UN');
INSERT INTO `materiales` VALUES ('224', 'T 30404641', 'Outdoor Bundle Equipo Tipo 2', null, 'UN');
INSERT INTO `materiales` VALUES ('225', 'T 30404642', 'Outdoor Bundle Equipo Tipo 3', null, 'UN');
INSERT INTO `materiales` VALUES ('226', 'T 30404644', 'Outdoor Bundle Equipo Tipo 5', null, 'UN');
INSERT INTO `materiales` VALUES ('227', 'T 30404660', 'CCPE 64 PORTS VDSL2&POTS COMBO BOARD', null, 'UN');
INSERT INTO `materiales` VALUES ('228', 'T 30404665', 'eSFP-1310nm-1000Base-Lx SM Optical trans', null, 'UN');
INSERT INTO `materiales` VALUES ('229', 'T 30404708', 'H83B2F01S100 F01S100 Assembly Cabinet (D', null, 'UN');
INSERT INTO `materiales` VALUES ('230', 'T 30404709', 'H80BF01S3002 F01S300 Assembly Cabinet(22', null, 'UN');
INSERT INTO `materiales` VALUES ('231', 'T 30404710', 'H80B0F01D500 F01D500 Assembly Integratio', null, 'UN');
INSERT INTO `materiales` VALUES ('232', 'T 30404711', 'H82BZ1MABB02 Multi-service Access Equipm', null, 'UN');
INSERT INTO `materiales` VALUES ('233', 'T 30404712', 'H80KZ4MABO01 Middle Service Shelf Suppor', null, 'UN');
INSERT INTO `materiales` VALUES ('234', 'T 30404713', 'H80K00ETSI02 ETSI Service Shelf,48V/60V,', null, 'UN');
INSERT INTO `materiales` VALUES ('235', 'T 30404714', 'H83D00CCUE01 Multi-Service Centralized C', null, 'UN');
INSERT INTO `materiales` VALUES ('236', 'T 30404715', 'H83D00UP2AA1 EPON/GPON/GE TypeC Uplink I', null, 'UN');
INSERT INTO `materiales` VALUES ('237', 'T 30404716', 'H83D00ASDA01 Analog Subscriber DSP Card', null, 'UN');
INSERT INTO `materiales` VALUES ('238', 'T 30404717', 'H80-FLBA Forwarding Logic Board', null, 'UN');
INSERT INTO `materiales` VALUES ('239', 'T 30404718', 'H80D00SCUN02 Super Control Unit Board', null, 'UN');
INSERT INTO `materiales` VALUES ('240', 'T 30404719', 'H806VPEA 384 Channels(64*6) Vector Centr', null, 'UN');
INSERT INTO `materiales` VALUES ('241', 'T 30404720', 'VPEA00T 384 Channels(64*6) Vector Centra', null, 'UN');
INSERT INTO `materiales` VALUES ('242', 'T 30404722', 'H83D0BCVLC01 32-port VDSL2 and 32-port P', null, 'UN');
INSERT INTO `materiales` VALUES ('243', 'T 30404724', 'H80D00ASPB02 64-port VOIP Subscriber Boa', null, 'UN');
INSERT INTO `materiales` VALUES ('244', 'T 30404725', 'CCPE 64 PORTS VDSL2&POTS COMBO BOARD', null, 'UN');
INSERT INTO `materiales` VALUES ('245', 'T 30404727', 'H83D02PDVAA1 Vector-supporting secondary', null, 'UN');
INSERT INTO `materiales` VALUES ('246', 'T 30404728', 'H80-PRTE Connect Power Board', null, 'UN');
INSERT INTO `materiales` VALUES ('247', 'T 30404730', 'eSFP(S)-1310nm-1000Base-Lx Optical Trans', null, 'UN');
INSERT INTO `materiales` VALUES ('248', 'T 30404734', 'H83X0F01S001 Engineering Required Delive', null, 'UN');
INSERT INTO `materiales` VALUES ('249', 'T 30404735', 'HP6X01E20002 Engineering Required Delive', null, 'UN');
INSERT INTO `materiales` VALUES ('250', 'T 30404736', 'SS-OP-LC-FC-S-0.8 Patch cord-FC/PC-LC/PC', null, 'UN');
INSERT INTO `materiales` VALUES ('251', 'T 30404737', 'SS-OP-LC-FC-S-5 Patch cord-FC/PC-LC/PC-S', null, 'UN');
INSERT INTO `materiales` VALUES ('252', 'T 30404738', 'OP-FC/PC-1 Pigtail-FC/PC-Single mode-G.6', null, 'UN');
INSERT INTO `materiales` VALUES ('253', 'T 30404739', 'QW1P0FIBER06 Optical adapter-LC/PC-LC/PC', null, 'UN');
INSERT INTO `materiales` VALUES ('254', 'T 30404740', 'FOPCOUS00 Optical Coupler,SM,1*2,1310&15', null, 'UN');
INSERT INTO `materiales` VALUES ('255', 'T 30404741', 'EBP561X01 MA561X blank panel', null, 'UN');
INSERT INTO `materiales` VALUES ('256', 'T 30404742', 'H80XBPMCBS00 Blank Panel for Main Contro', null, 'UN');
INSERT INTO `materiales` VALUES ('257', 'T 30404743', 'H80XBPGIUS00 Blank Panel for GIU Board S', null, 'UN');
INSERT INTO `materiales` VALUES ('258', 'T 30404744', 'H80X0BPPBS00 Blank Panel for Power Board', null, 'UN');
INSERT INTO `materiales` VALUES ('259', 'T 30404745', 'H80XBPCIBS00 Blank Panel for Common Inte', null, 'UN');
INSERT INTO `materiales` VALUES ('260', 'T 30404746', 'H80XBPSPLB00 Blank Panel for Service Boa', null, 'UN');
INSERT INTO `materiales` VALUES ('261', 'T 30404747', 'EN1MMHSM01B1 SMU01B Site Monitor Unit', null, 'UN');
INSERT INTO `materiales` VALUES ('262', 'T 30404748', 'R4815N1 R4815N1-15A Normal Efficiency Re', null, 'UN');
INSERT INTO `materiales` VALUES ('263', 'T 30404749', 'R4830N2 R4830N2-30A Normal Efficiency Re', null, 'UN');
INSERT INTO `materiales` VALUES ('264', 'T 30404750', 'WMM12AH00 Rechargeable battery,VRLA batt', null, 'UN');
INSERT INTO `materiales` VALUES ('265', 'T 30404751', 'WES005001 Rechargeable battery,VRLA batt', null, 'UN');
INSERT INTO `materiales` VALUES ('266', 'T 30404752', 'WSD100004 Rechargeable Battery, VRLA bat', null, 'UN');
INSERT INTO `materiales` VALUES ('267', 'T 30404754', 'H83Z3SF01S50 Components for Socket,Multi', null, 'UN');
INSERT INTO `materiales` VALUES ('268', 'T 30404756', 'H83B0MDFS100 MDF Auxiliary Components(JP', null, 'UN');
INSERT INTO `materiales` VALUES ('269', 'T 30404757', 'EBTSensor001 Temperature Sensor,-40~80de', null, 'UN');
INSERT INTO `materiales` VALUES ('270', 'T 30404758', 'H80ZF01S300B Components for Socket(for C', null, 'UN');
INSERT INTO `materiales` VALUES ('271', 'T 30404759', 'H83XHAR03203 Harness,F01E50,32-port Subs', null, 'UN');
INSERT INTO `materiales` VALUES ('272', 'T 30404760', 'H83XHAR03207 Harness,F01S300,64-port Sub', null, 'UN');
INSERT INTO `materiales` VALUES ('273', 'T 30404761', 'CVDSL5966 Harness,F01D500,64-port Subscr', null, 'UN');
INSERT INTO `materiales` VALUES ('274', 'T 30404762', 'EDFTOOL01 IDC Tool', null, 'UN');
INSERT INTO `materiales` VALUES ('275', 'T 30404763', 'JPX658CSSV Test Plug', null, 'UN');
INSERT INTO `materiales` VALUES ('276', 'T 30404764', 'EJPX65803 10 pairs Exchange Side Strip (', null, 'UN');
INSERT INTO `materiales` VALUES ('277', 'T 30404765', 'EJPX65804 10 pairs Exchange Side Strip (', null, 'UN');
INSERT INTO `materiales` VALUES ('278', 'T 30404766', 'E00SFD201 GDT Protective Unit', null, 'UN');
INSERT INTO `materiales` VALUES ('279', 'T 30500007', 'BLOQUE TERM. D/10 PS P/ CENTR. \"PABX\"', null, 'UN');
INSERT INTO `materiales` VALUES ('280', 'T 30500002', 'ANILLA DISTRIBUCION D=15MM P/CABLE DISTR', null, 'UN');
INSERT INTO `materiales` VALUES ('281', 'T 30500003', 'ANILLA DISTRIBUCION D=32MM P/CABLE DISTR', null, 'UN');
INSERT INTO `materiales` VALUES ('282', 'T 30500004', 'ANILLA RETENCION  25X80 MM P/CABLE BAJ.', null, 'UN');
INSERT INTO `materiales` VALUES ('283', 'T 30500005', 'ANILLA RETENCION  25X115 MM P/CABLE BAJ.', null, 'UN');
INSERT INTO `materiales` VALUES ('284', 'T 30500006', 'ANILLA RETENCION  27X155 MM P/CABLE BAJ.', null, 'UN');
INSERT INTO `materiales` VALUES ('285', 'T 30500007', 'BLOQUE TERM. D/10 PS P/ CENTR. \"PABX\"', null, 'UN');
INSERT INTO `materiales` VALUES ('286', 'T 30500008', 'BLOQUE TERM. P/EXT.C/RC,FIL.,1CONE.RJ11', null, 'UN');
INSERT INTO `materiales` VALUES ('287', 'T 30500009', 'BLOQUE TERM. P/EXT C/FILT.RF,1 CONE.RJ11', null, 'UN');
INSERT INTO `materiales` VALUES ('288', 'T 30500010', 'BLOQUE TERM. P/EXT. C/RC Y 1 CONEC RJ11', null, 'UN');
INSERT INTO `materiales` VALUES ('289', 'T 30500011', 'BLOQUE TERM. P/EXT. C /2RC  2 CONEC RJ11', null, 'UN');
INSERT INTO `materiales` VALUES ('290', 'T 30500012', 'BLOQUE TERM. P/EXT .C/1 CONEC. RJ11', null, 'UN');
INSERT INTO `materiales` VALUES ('291', 'T 30500013', 'BLOQUE TERM. P/EXT. C/ 2 CONECTORES RJ11', null, 'UN');
INSERT INTO `materiales` VALUES ('292', 'T 30500014', 'BLOQUE TERM  P/EXT C/CONEX. 4CONTAC.RJ11', null, 'UN');
INSERT INTO `materiales` VALUES ('293', 'T 30500015', 'BLOQUE TERM. P/EMB. C/1 RC, 1 CONEC.RJ11', null, 'UN');
INSERT INTO `materiales` VALUES ('294', 'T 30500016', 'BLOQUE TERM. P/EMB C/2 RC Y 2 CONEC.RJ11', null, 'UN');
INSERT INTO `materiales` VALUES ('295', 'T 30500017', 'BLOQUE TERM. P/EMB. C/1 CONECTOR RJ11', null, 'UN');
INSERT INTO `materiales` VALUES ('296', 'T 30500018', 'BLOQUE TERM. P/EMB. C/2 CONECTORES RJ11', null, 'UN');
INSERT INTO `materiales` VALUES ('297', 'T 30500019', 'BLOQUE TERM. P/EM C/GEL, CONEX. E/4CONT.', null, 'UN');
INSERT INTO `materiales` VALUES ('298', 'T 30500020', 'BLOQUE TERM. ABON.EXTER. C/CONEC. RJ45', null, 'UN');
INSERT INTO `materiales` VALUES ('299', 'T 30500022', 'BLOQUE TERM. ABON.C/CONEC. RJ45 Y 2 RES.', null, 'UN');
INSERT INTO `materiales` VALUES ('300', 'T 30500026', 'SEPARADOR ANTIHUMEDAD P/BLOQUE ABONADO', null, 'UN');
INSERT INTO `materiales` VALUES ('301', 'T 30500027', 'CONECTOR A COMPRESION P/CABLE DE BAJADA', null, 'UN');
INSERT INTO `materiales` VALUES ('302', 'T 30500028', 'CONECTOR A COMPRESION P/CAB.BAJADA/DISTR', null, 'UN');
INSERT INTO `materiales` VALUES ('303', 'T 30500029', 'GRAMPA 1 OREJA D/8 MM P/FIJAR CABLE', null, 'UN');
INSERT INTO `materiales` VALUES ('304', 'T 30500030', 'GRAMPA 1 OREJA D/10 MM P/FIJAR CABLE', null, 'UN');
INSERT INTO `materiales` VALUES ('305', 'T 30500031', 'GRAMPA 1 OREJA D/13 MM P/FIJAR CABLE', null, 'UN');
INSERT INTO `materiales` VALUES ('306', 'T 30500032', 'GRAMPA 1 OREJA D/16 MM P/FIJAR CABLE', null, 'UN');
INSERT INTO `materiales` VALUES ('307', 'T 30500033', 'GRAMPA P/1 CABLE FACHADA', null, 'UN');
INSERT INTO `materiales` VALUES ('308', 'T 30500034', 'GRAMPA P/2 CABLES DE FACHADA', null, 'UN');
INSERT INTO `materiales` VALUES ('309', 'T 30500035', 'GRAMPA P/3 CABLES DE FACHADA', null, 'UN');
INSERT INTO `materiales` VALUES ('310', 'T 30500036', 'GRAMPA P/CABLES MULTIPARES', null, 'UN');
INSERT INTO `materiales` VALUES ('311', 'T 30500038', 'RETEN CAB BAJ. P/CAB D/BAJ.30100127/ 129', null, 'UN');
INSERT INTO `materiales` VALUES ('312', 'T 30500039', 'RETEN CABLE BAJ./ DISTRIB.*usar30500040*', null, 'UN');
INSERT INTO `materiales` VALUES ('313', 'T 30500040', 'RETEN CABLE BAJ. D/2 PARESC/SUSPENSOR', null, 'UN');
INSERT INTO `materiales` VALUES ('314', 'T 30500042', 'SOPORTE DE PARED P/RETEN', null, 'UN');
INSERT INTO `materiales` VALUES ('315', 'T 30500043', 'TUBO PASA PARED P/CAB D/DIST. 203020/022', null, 'UN');
INSERT INTO `materiales` VALUES ('316', 'T 30500044', 'TUBO PASA PARED P/CAB D/DIST. 203017/019', null, 'UN');
INSERT INTO `materiales` VALUES ('317', 'T 30500045', 'TUBO PASA PARED P/CAB D/DIST. 203026', null, 'UN');
INSERT INTO `materiales` VALUES ('318', 'T 30500046', 'TUBO PASA PARED P/CAB D/DIST. 203016/018', null, 'UN');
INSERT INTO `materiales` VALUES ('319', 'T 30500047', 'CORDON D/EXTENSION ENTRADA LINEA', null, 'UN');
INSERT INTO `materiales` VALUES ('320', 'T 30500048', 'CORDON P/MICROTELEFONO TIPO ESPERIAL', null, 'UN');
INSERT INTO `materiales` VALUES ('321', 'T 30500049', 'CORDON P/CALLER-ID  LARGO=35 CM', null, 'UN');
INSERT INTO `materiales` VALUES ('322', 'T 30500051', 'MULTIPLICADOR PARES 1+1 COMP.', null, 'UN');
INSERT INTO `materiales` VALUES ('323', 'T 30500057', 'ALIMENTACION P/EQUIPO 540551', null, 'UN');
INSERT INTO `materiales` VALUES ('324', 'T 30500059', 'EQUIPO LADO ABONADO P/540555', null, 'UN');
INSERT INTO `materiales` VALUES ('325', 'T 30500062', 'EQUIPO LADO ABONADO P/540558', null, 'UN');
INSERT INTO `materiales` VALUES ('326', 'T 30500067', 'TELEFONO CON CALLER ID GENERAL ELECTRIC', null, 'UN');
INSERT INTO `materiales` VALUES ('327', 'T 30500070', 'TELEFONO BASICO MARCA \"SIEMENS\"', null, 'UN');
INSERT INTO `materiales` VALUES ('328', 'T 30500071', 'TELEFONO BASICO MARCA \"XINGTEL\"', null, 'UN');
INSERT INTO `materiales` VALUES ('329', 'T 30500072', 'TELEFONO BASICO MARCA \"INTELBRAS\"', null, 'UN');
INSERT INTO `materiales` VALUES ('330', 'T 30500074', 'TERMINAL D/COMUNICACIONES P/HIPOACUSTICO', null, 'UN');
INSERT INTO `materiales` VALUES ('331', 'T 30500075', 'TERMINAL TDD D/COMUNIC.P/HIPOACUSTICO', null, 'UN');
INSERT INTO `materiales` VALUES ('332', 'T 30500077', 'TERMINAL CALLER ID MARCA \"DIPLOMA\"', null, 'UN');
INSERT INTO `materiales` VALUES ('333', 'T 30500078', 'TERMINAL CALLER ID MARCA \"SIEMENS\"', null, 'UN');
INSERT INTO `materiales` VALUES ('334', 'T 30500079', 'TERMINAL DE RED NT PLUS PARA ISDN', null, 'UN');
INSERT INTO `materiales` VALUES ('335', 'T 30500080', 'TERMINAL DE RED NT PLUS C/RS232 P/ISDN', null, 'UN');
INSERT INTO `materiales` VALUES ('336', 'T 30500082', 'DISPOSITIVO SELECTOR DE NUMERO', null, 'UN');
INSERT INTO `materiales` VALUES ('337', 'T 30500083', 'TERMINAL CALLER ID MARCA \"MERCURY\"', null, 'UN');
INSERT INTO `materiales` VALUES ('338', 'T 30500089', 'TELEFONO INALAMBRICO ALADINO 300', null, 'UN');
INSERT INTO `materiales` VALUES ('339', 'T 30500090', 'TELEFONO INALAMBRICO ALADINO 500', null, 'UN');
INSERT INTO `materiales` VALUES ('340', 'T 30500091', 'VIDEOTELEFONO ALADINO VT', null, 'UN');
INSERT INTO `materiales` VALUES ('341', 'T 30500092', 'FAX MARCA OLIVETTI MOD. OFX-180', null, 'UN');
INSERT INTO `materiales` VALUES ('342', 'T 30500093', 'TELEFONO BASICO ALCATEL TEMPORIS 12', null, 'UN');
INSERT INTO `materiales` VALUES ('343', 'T 30500103', 'TELEFONO ISDN MARCA ERICSON MD 110', null, 'UN');
INSERT INTO `materiales` VALUES ('344', 'T 30500105', 'CAJA INTERC/EX. P/2 C/EC. SIN EQUIPAR', null, 'UN');
INSERT INTO `materiales` VALUES ('345', 'T 30500106', 'TELEFONO BASICO \"SIEMENS\" EUROSET 805', null, 'UN');
INSERT INTO `materiales` VALUES ('346', 'T 30500107', 'TELEFONO BASICO \"SIEMENS\" MOD.3005', null, 'UN');
INSERT INTO `materiales` VALUES ('347', 'T 30500333', 'PORTAROTULO ***usar 30500678***', null, 'UN');
INSERT INTO `materiales` VALUES ('348', 'T 30500337', 'PORTAROTULO PARA SOPORTE TIPO KRONE', null, 'UN');
INSERT INTO `materiales` VALUES ('349', 'T 30500377', 'TELEFONO BASICO PIRELLI MOD. MERA ME 31', null, 'UN');
INSERT INTO `materiales` VALUES ('350', 'T 30500378', 'FAX MARCA OLIVETTI MOD. 105F', null, 'UN');
INSERT INTO `materiales` VALUES ('351', 'T 30500557', 'TELEFONO BASICO MARCA \"BHARTI\"', null, 'UN');
INSERT INTO `materiales` VALUES ('352', 'T 30500587', 'KIT MODEM ARNET ADSL ETH', null, 'UN');
INSERT INTO `materiales` VALUES ('353', 'T 30500588', 'KIT MODEM ARNET ADSL WI-FI', null, 'UN');
INSERT INTO `materiales` VALUES ('354', 'T 30500608', 'TELEFONO INALAMBRICO ALADINO 410', null, 'UN');
INSERT INTO `materiales` VALUES ('355', 'T 30500678', 'PORTAROTULO PARA SOPORTE TIPO POUYET', null, 'UN');
INSERT INTO `materiales` VALUES ('356', 'T 30500687', 'TEL?FONO B?SICO  MOD. SIGMA PLUS II', null, 'UN');
INSERT INTO `materiales` VALUES ('357', 'T 30500737', 'TELEFONO INAL?MBRICO ALADINO 420', null, 'UN');
INSERT INTO `materiales` VALUES ('358', 'T 30500817', 'BLOQUE TER. EXT. C/SPLITER INCORPORADO', null, 'UN');
INSERT INTO `materiales` VALUES ('359', 'T 30500828', 'BLOQUE TER. INT. C/SPLITER INCORPORADO', null, 'UN');
INSERT INTO `materiales` VALUES ('360', 'T 30500877', 'KIT MODEM ARNET VDSL WI-FI', null, 'UN');
INSERT INTO `materiales` VALUES ('361', 'T 30500967', 'TELEFONO BASICO  ACONCAWA MOD. SL 9292', null, 'UN');
INSERT INTO `materiales` VALUES ('362', 'T 30600000', 'CONECTOR RJ45 8 POSICIONES 4 CONTACTOS', null, 'UN');
INSERT INTO `materiales` VALUES ('363', 'T 30600001', 'FICHA RJ11 D/SEIS POSICIONES2 CONTACTOS', null, 'UN');
INSERT INTO `materiales` VALUES ('364', 'T 30600006', 'GRAPA D/CONT.D/PANTALLA CAB.DIAM.>A 37MM', null, 'UN');
INSERT INTO `materiales` VALUES ('365', 'T 30600007', 'CONECTOR DIN 1.6/5.6 MODELO**inhibido**', null, 'UN');
INSERT INTO `materiales` VALUES ('366', 'T 30600012', 'TERMINAL P/CONDUC. 50 MM2', null, 'UN');
INSERT INTO `materiales` VALUES ('367', 'T 30600015', 'CONECTOR COAXIL N M. P/CABLE RG213', null, 'UN');
INSERT INTO `materiales` VALUES ('368', 'T 30600017', 'CONECTOR COAXIL N M. \"L\" P/CABLE RG213', null, 'UN');
INSERT INTO `materiales` VALUES ('369', 'T 30600018', 'JUMPER COAXIL CONECTOR N P/MONOCANAL', null, 'UN');
INSERT INTO `materiales` VALUES ('370', 'T 30600019', 'PROTECTOR COAXIL 200 A 500 MHZ NH-NH', null, 'UN');
INSERT INTO `materiales` VALUES ('371', 'T 30600021', 'CONECTOR N-HEMBRA PARA CABLE**inhibido**', null, 'UN');
INSERT INTO `materiales` VALUES ('372', 'T 30600022', 'KIT P/GUIA D/ONDA ELIPTICA*USAR 30600153', null, 'UN');
INSERT INTO `materiales` VALUES ('373', 'T 30600024', 'JUMPER DE CABLE COAXIL DE 1/2\"**inhibido', null, 'UN');
INSERT INTO `materiales` VALUES ('374', 'T 30600027', 'KIT P/COAXIL 7/8\" A 1 1/2\" **inhibido**', null, 'UN');
INSERT INTO `materiales` VALUES ('375', 'T 30600028', 'KIT PAT P/GO RFS E70', null, 'UN');
INSERT INTO `materiales` VALUES ('376', 'T 30600045', 'CONECTOR P/COAXIL (HEMBRA ACO-**inhibido', null, 'UN');
INSERT INTO `materiales` VALUES ('377', 'T 30600046', 'REGLETA WIRE-RAP 2X25', null, 'UN');
INSERT INTO `materiales` VALUES ('378', 'T 30600077', 'CONECTOR COAXIL  N(M) P/CABLE RG58', null, 'UN');
INSERT INTO `materiales` VALUES ('379', 'T 30600078', 'CONECTOR  COAXIL TNC(M) P/CABLE RG58', null, 'UN');
INSERT INTO `materiales` VALUES ('380', 'T 30600079', 'ADAPTADOR TNC MACHO A N HEMBRA', null, 'UN');
INSERT INTO `materiales` VALUES ('381', 'T 30600080', 'CONECTOR  TNC MACHO P/COAXIL 3/8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('382', 'T 30600081', 'ADAPTADOR CONECTOR TNC HEM. A SMA MACHO', null, 'UN');
INSERT INTO `materiales` VALUES ('383', 'T 30600082', 'CONECTOR COAXIL 1.6/5.6 H. ANG.P/FLEX-2', null, 'UN');
INSERT INTO `materiales` VALUES ('384', 'T 30600083', 'CONECTOR COAXIL 1.6/5.6 H. ANG.P/2YCY', null, 'UN');
INSERT INTO `materiales` VALUES ('385', 'T 30600084', 'CONECTOR COAXIL 1.6/5.6 H. ANG.P/2.5C-2V', null, 'UN');
INSERT INTO `materiales` VALUES ('386', 'T 30600085', 'CONECTOR COAXIL 1.6/5.6 H. ANG.P/3C-2V', null, 'UN');
INSERT INTO `materiales` VALUES ('387', 'T 30600086', 'CONECTOR COAXIL 1.6/5.6 H. ANG.P/2YCCY', null, 'UN');
INSERT INTO `materiales` VALUES ('388', 'T 30600087', 'CONECTOR COAXIL 1.6/5.6 H. ANG.P/3C-2W', null, 'UN');
INSERT INTO `materiales` VALUES ('389', 'T 30600088', 'CONECTOR COAXIL 1.6/5.6 M. ANG.P/ FLEX 2', null, 'UN');
INSERT INTO `materiales` VALUES ('390', 'T 30600089', 'CONECTOR COAXIL 1.6/5.6 M. ANG. P/ 2YCY', null, 'UN');
INSERT INTO `materiales` VALUES ('391', 'T 30600090', 'CONECTOR COAXIL 1.6/5.6 M. ANG.P/2.5C-2V', null, 'UN');
INSERT INTO `materiales` VALUES ('392', 'T 30600091', 'CONECTOR COAXIL 1.6/5.6 M. ANG. P/3C-2V', null, 'UN');
INSERT INTO `materiales` VALUES ('393', 'T 30600092', 'CONECTOR COAXIL 1.6/5.6 M. ANG. P/2YCCY', null, 'UN');
INSERT INTO `materiales` VALUES ('394', 'T 30600093', 'CONECTOR COAXIL 1.6/5.6 M. ANG. P/3C-2W', null, 'UN');
INSERT INTO `materiales` VALUES ('395', 'T 30600094', 'CONECTOR COAXIL 1.6/5.6 M. RECT. P/FLEX2', null, 'UN');
INSERT INTO `materiales` VALUES ('396', 'T 30600095', 'CONECTOR COAXIL 1.6/5.6 M. RECT. P/2YCY', null, 'UN');
INSERT INTO `materiales` VALUES ('397', 'T 30600096', 'CONECTOR COAXIL 1.6/5.6 M.RECT.P/2.5C-2V', null, 'UN');
INSERT INTO `materiales` VALUES ('398', 'T 30600097', 'CONECTOR COAXIL 1.6/5.6 M. RECT. P/3C-2V', null, 'UN');
INSERT INTO `materiales` VALUES ('399', 'T 30600098', 'CONECTOR COAXIL 1.6/5.6 M. RECT. P/2YCCY', null, 'UN');
INSERT INTO `materiales` VALUES ('400', 'T 30600100', 'CONECTOR COAXIL 1.0/2.3 M.RECT. P/FLEX-2', null, 'UN');
INSERT INTO `materiales` VALUES ('401', 'T 30600101', 'CONECTOR COAXIL 1.0/2.3 M.RECT. P/2YCY', null, 'UN');
INSERT INTO `materiales` VALUES ('402', 'T 30600102', 'CONECTOR COAXIL 1.0/2.3 M.RECT.P/2.5C-2V', null, 'UN');
INSERT INTO `materiales` VALUES ('403', 'T 30600103', 'CONECTOR COAXIL 1.0/2.3 M.RECT.P/3C-2V', null, 'UN');
INSERT INTO `materiales` VALUES ('404', 'T 30600104', 'CONECTOR COAXIL 1.0/2.3 M.RECT.P/2YCCY', null, 'UN');
INSERT INTO `materiales` VALUES ('405', 'T 30600106', 'CONECTOR COAXIL BNC H. 75 OHM P/FLEX-2', null, 'UN');
INSERT INTO `materiales` VALUES ('406', 'T 30600107', 'CONECTOR COAXIL BNC H. 75 OHM P/2YCY', null, 'UN');
INSERT INTO `materiales` VALUES ('407', 'T 30600108', 'CONECTOR COAXIL BNC H. 75 OHM P/2.5C-2V', null, 'UN');
INSERT INTO `materiales` VALUES ('408', 'T 30600109', 'CONECTOR COAXIL BNC H. 75 OHM P/3C-2V', null, 'UN');
INSERT INTO `materiales` VALUES ('409', 'T 30600110', 'CONECTOR COAXIL BNC H. 75 OHM P/2YCCY', null, 'UN');
INSERT INTO `materiales` VALUES ('410', 'T 30600111', 'CONECTOR COAXIL BNC H. 75 OHM P/3C-2W', null, 'UN');
INSERT INTO `materiales` VALUES ('411', 'T 30600112', 'CONECTOR COAXIL BNC M. 75 OHM P/FLEX-2', null, 'UN');
INSERT INTO `materiales` VALUES ('412', 'T 30600113', 'CONECTOR COAXIL BNC M. 75 OHM P/2YCY', null, 'UN');
INSERT INTO `materiales` VALUES ('413', 'T 30600114', 'CONECTOR COAXIL BNC M. 75 OHM P/2.5C-2V', null, 'UN');
INSERT INTO `materiales` VALUES ('414', 'T 30600115', 'CONECTOR COAXIL BNC M. 75 OHM P/3C-2V', null, 'UN');
INSERT INTO `materiales` VALUES ('415', 'T 30600116', 'CONECTOR COAXIL BNC M. 75 OHM P/2YCCY', null, 'UN');
INSERT INTO `materiales` VALUES ('416', 'T 30600117', 'CONECTOR COAXIL BNC M. 75 OHM P/3C-2W', null, 'UN');
INSERT INTO `materiales` VALUES ('417', 'T 30600118', 'CONECTOR COAXIL BNC M. 50 OHM P/RG58', null, 'UN');
INSERT INTO `materiales` VALUES ('418', 'T 30600119', 'CRUZADA COAXIL 2YCCY 2M 1.6/5.6 M.ANG.', null, 'UN');
INSERT INTO `materiales` VALUES ('419', 'T 30600120', 'CRUZADA COAXIL 2YCCY 2,5M 1.6/5.6 M.ANG.', null, 'UN');
INSERT INTO `materiales` VALUES ('420', 'T 30600121', 'CRUZADA COAXIL 2YCCY 3M 1.6/5.6 M.ANG.', null, 'UN');
INSERT INTO `materiales` VALUES ('421', 'T 30600122', 'CRUZADA COAXIL 2YCCY 5M 1.6/5.6 M.ANG.', null, 'UN');
INSERT INTO `materiales` VALUES ('422', 'T 30600123', 'CRUZADA COAXIL 2YCCY 8M 1.6/5.6 M.ANG.', null, 'UN');
INSERT INTO `materiales` VALUES ('423', 'T 30600124', 'CRUZADA COAXIL 2YCCY 10M 1.6/5.6 M.ANG.', null, 'UN');
INSERT INTO `materiales` VALUES ('424', 'T 30600125', 'CRUZADA COAXIL 2YCCY 12M 1.6/5.6 M.ANG.', null, 'UN');
INSERT INTO `materiales` VALUES ('425', 'T 30600126', 'CRUZADA COAXIL 3C-2V 2M 1.6/5.6 M.ANG.', null, 'UN');
INSERT INTO `materiales` VALUES ('426', 'T 30600127', 'CRUZADA COAXIL 3C-2V 2,5M 1.6/5.6 M.ANG.', null, 'UN');
INSERT INTO `materiales` VALUES ('427', 'T 30600128', 'CRUZADA COAXIL 3C-2V 3M 1.6/5.6 M.ANG.', null, 'UN');
INSERT INTO `materiales` VALUES ('428', 'T 30600129', 'CRUZADA COAXIL 3C-2V 5M 1.6/5.6 M.ANG.', null, 'UN');
INSERT INTO `materiales` VALUES ('429', 'T 30600131', 'CRUZADA COAXIL 3C-2V 10M 1.6/5.6 M.ANG.', null, 'UN');
INSERT INTO `materiales` VALUES ('430', 'T 30600132', 'CRUZADA COAXIL 3C-2V 12M 1.6/5.6 M.ANG.', null, 'UN');
INSERT INTO `materiales` VALUES ('431', 'T 30600137', 'CONECTOR COAXIL N M. P/CABLE 3/8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('432', 'T 30600138', 'CONECTOR COAXIL N H. P/CABLE 3/8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('433', 'T 30600139', 'CONECTOR COAXIL N M. P/CABLE 1/4\"', null, 'UN');
INSERT INTO `materiales` VALUES ('434', 'T 30600140', 'CONECTOR COAXIL N H. P/CABLE 1/4\"', null, 'UN');
INSERT INTO `materiales` VALUES ('435', 'T 30600141', 'CONECTOR COAXIL N M. P/CABLE 1/4\" S.FLEX', null, 'UN');
INSERT INTO `materiales` VALUES ('436', 'T 30600142', 'CONECTOR COAXIL N H. P/CABLE 1/4\" S.FLEX', null, 'UN');
INSERT INTO `materiales` VALUES ('437', 'T 30600143', 'CONECTOR COAXIL N H. P/CABLE RG213', null, 'UN');
INSERT INTO `materiales` VALUES ('438', 'T 30600144', 'CONECTOR COAXIL N H. \"L\" P/CABLE RG213', null, 'UN');
INSERT INTO `materiales` VALUES ('439', 'T 30600145', 'CONECTOR COAXIL N M. P/CABLE 1 1/4\"', null, 'UN');
INSERT INTO `materiales` VALUES ('440', 'T 30600146', 'CONECTOR COAXIL N H. P/CABLE 1 1/4\"', null, 'UN');
INSERT INTO `materiales` VALUES ('441', 'T 30600148', 'CONECTOR COAXIL N M. P/CABLE 7/8\" PRES.', null, 'UN');
INSERT INTO `materiales` VALUES ('442', 'T 30600149', 'CONECTOR COAXIL N H. P/CABLE 7/8\" ROSCA', null, 'UN');
INSERT INTO `materiales` VALUES ('443', 'T 30600150', 'CONECTOR COAXIL N H. P/CABLE 7/8\" PRES.', null, 'UN');
INSERT INTO `materiales` VALUES ('444', 'T 30600151', 'KIT PAT P/GO ANDREW EWP52  COD. 204989-4', null, 'UN');
INSERT INTO `materiales` VALUES ('445', 'T 30600152', 'KIT PAT P/GO ANDREW EWP63 COD. 204989-4', null, 'UN');
INSERT INTO `materiales` VALUES ('446', 'T 30600153', 'KIT PAT P/GO ANDREW EWP64 COD. 204989-3', null, 'UN');
INSERT INTO `materiales` VALUES ('447', 'T 30600154', 'KIT PAT P/GO ANDREW EWP77 COD. 204989-3', null, 'UN');
INSERT INTO `materiales` VALUES ('448', 'T 30600155', 'KIT PAT P/GO RFS E65 y E 70', null, 'UN');
INSERT INTO `materiales` VALUES ('449', 'T 30600156', 'KIT PAT P/GO RFS E78', null, 'UN');
INSERT INTO `materiales` VALUES ('450', 'T 30600157', 'JUMPER COAXIL 1/2\" S.FLEX NM-NH L=1M', null, 'UN');
INSERT INTO `materiales` VALUES ('451', 'T 30600158', 'JUMPER COAXIL 1/2\" S.FLEX NM-NH L=1,5M', null, 'UN');
INSERT INTO `materiales` VALUES ('452', 'T 30600159', 'CONECTOR COAXIL N M. P/CABLE 1/2\" S.FLEX', null, 'UN');
INSERT INTO `materiales` VALUES ('453', 'T 30600160', 'CONECTOR COAXIL N H. P/CABLE 1/2\" S.FLEX', null, 'UN');
INSERT INTO `materiales` VALUES ('454', 'T 30600161', 'CONECT.N M.RECT.P/CAB 1/2 RFS LCF12-50J', null, 'UN');
INSERT INTO `materiales` VALUES ('455', 'T 30600162', 'CONECTOR COAXIL N H. P/CABLE 1/2\"', null, 'UN');
INSERT INTO `materiales` VALUES ('456', 'T 30600164', 'KIT ABRAZADERA METALICA P/CABLE 1/2\"', null, 'UN');
INSERT INTO `materiales` VALUES ('457', 'T 30600172', 'KIT ABRAZADERA METALICA P/CABLE 1 1/4\"', null, 'UN');
INSERT INTO `materiales` VALUES ('458', 'T 30600186', 'KIT ABRAZADERA METALICA P/CABLE 1/4\"', null, 'UN');
INSERT INTO `materiales` VALUES ('459', 'T 30600193', 'KIT ABRAZADERA METALICA P/CABLE RG213', null, 'UN');
INSERT INTO `materiales` VALUES ('460', 'T 30600200', 'KIT ABRAZADERA METALICA P/CABLE 3/8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('461', 'T 30600221', 'KIT ABRAZADERA METAL. P/GO ANDREW EWP52', null, 'UN');
INSERT INTO `materiales` VALUES ('462', 'T 30600222', 'KIT ABRAZADERA METAL. P/GO ANDREW EWP63', null, 'UN');
INSERT INTO `materiales` VALUES ('463', 'T 30600223', 'KIT ABRAZADERA METAL. P/GO ANDREW EWP64', null, 'UN');
INSERT INTO `materiales` VALUES ('464', 'T 30600224', 'KIT ABRAZADERA METAL. P/GO ANDREW EWP77', null, 'UN');
INSERT INTO `materiales` VALUES ('465', 'T 30600225', 'KIT ABRAZADERA METALICA P/GO RFS E65', null, 'UN');
INSERT INTO `materiales` VALUES ('466', 'T 30600226', 'KIT ABRAZADERA METALICA P/GO RFS E70', null, 'UN');
INSERT INTO `materiales` VALUES ('467', 'T 30600227', 'KIT ABRAZADERA METALICA P/GO RFS E78', null, 'UN');
INSERT INTO `materiales` VALUES ('468', 'T 30600229', 'KIT PAT P/CABLE RG213', null, 'UN');
INSERT INTO `materiales` VALUES ('469', 'T 30600230', 'KIT PAT AUTOSELLANTE P/CABLE 1/4\"', null, 'UN');
INSERT INTO `materiales` VALUES ('470', 'T 30600231', 'KIT PAT AUTOSELLANTE P/CABLE RG213', null, 'UN');
INSERT INTO `materiales` VALUES ('471', 'T 30600232', 'KIT PAT AUTOSELLANTE P/CABLE 3/8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('472', 'T 30600234', 'KIT PAT AUTOSELLANTE P/CABLE 1/2\" S.FLEX', null, 'UN');
INSERT INTO `materiales` VALUES ('473', 'T 30600235', 'KIT PAT AUTOSELLANTE P/CABLE 7/8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('474', 'T 30600238', 'KIT PAT P/CABLE 3/8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('475', 'T 30600239', 'KIT PAT P/CABLE 1/2\" S.FLEX', null, 'UN');
INSERT INTO `materiales` VALUES ('476', 'T 30600240', 'KIT PAT P/CABLE 7/8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('477', 'T 30600241', 'KIT PAT P/CABLE 1 1/4\"', null, 'UN');
INSERT INTO `materiales` VALUES ('478', 'T 30600242', 'KIT PAT P/CABLE 1 5/8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('479', 'T 30600244', 'CONECTOR ANDREW P/GO EWP64 CPR112G FIJO', null, 'UN');
INSERT INTO `materiales` VALUES ('480', 'T 30600245', 'CONECTOR RFS P/GO E65 CPR137G SINT.', null, 'UN');
INSERT INTO `materiales` VALUES ('481', 'T 30600246', 'CONECTOR RFS P/GO E70 CPR137G SINT.', null, 'UN');
INSERT INTO `materiales` VALUES ('482', 'T 30600247', 'CONECTOR RFS P/GO E78 CPR112G SINT.', null, 'UN');
INSERT INTO `materiales` VALUES ('483', 'T 30600249', 'CONECTOR ANDREW P/GO EWP77 CPR112G FIJO', null, 'UN');
INSERT INTO `materiales` VALUES ('484', 'T 30600250', 'CONECTOR ANDREW P/GO EWP52 CPR159G FIJO', null, 'UN');
INSERT INTO `materiales` VALUES ('485', 'T 30600251', 'CONECTOR ANDREW P/GO EWP52 CPR137G FIJO', null, 'UN');
INSERT INTO `materiales` VALUES ('486', 'T 30600252', 'TRANSICION GO CPR159G-CPR137G L=152MM', null, 'UN');
INSERT INTO `materiales` VALUES ('487', 'T 30600253', 'TRANSICION GO CPR137G-CPR112G L=152MM', null, 'UN');
INSERT INTO `materiales` VALUES ('488', 'T 30600255', 'CONECTOR ANDREW P/GO EWP63 CPR137G SINT.', null, 'UN');
INSERT INTO `materiales` VALUES ('489', 'T 30600256', 'CONECTOR ANDREW P/GO EWP63 CPR137G FIJO', null, 'UN');
INSERT INTO `materiales` VALUES ('490', 'T 30600258', 'CONECTOR ANDREW P/GO EWP64 CPR137G FIJO', null, 'UN');
INSERT INTO `materiales` VALUES ('491', 'T 30600262', 'PRESURIZADOR 48VCC CAPACIDAD 100L/H', null, 'UN');
INSERT INTO `materiales` VALUES ('492', 'T 30600263', 'PROTECTOR COAXIL DC A 2500 MHZ NH-NH', null, 'UN');
INSERT INTO `materiales` VALUES ('493', 'T 30600264', 'PROTECTOR COAXIL 1427 A 1525 MHZ NH-NH', null, 'UN');
INSERT INTO `materiales` VALUES ('494', 'T 30600265', 'PROTECTOR COAXIL 2300 A 2500 MHZ NH-NH', null, 'UN');
INSERT INTO `materiales` VALUES ('495', 'T 30600266', 'PROTECTOR COAXIL 5725 A 5850 MHZ NH-NH', null, 'UN');
INSERT INTO `materiales` VALUES ('496', 'T 30600267', 'KIT ABRAZADERA PLASTICA P/1 CABLE 1/2\"', null, 'UN');
INSERT INTO `materiales` VALUES ('497', 'T 30600268', 'KIT ABRAZADERA PLASTICA P/2 CABLE 1/2\"', null, 'UN');
INSERT INTO `materiales` VALUES ('498', 'T 30600269', 'KIT ABRAZADERA PLASTICA P/3 CABLE 1/2\"', null, 'UN');
INSERT INTO `materiales` VALUES ('499', 'T 30600271', 'KIT ABRAZADERA PLASTICA P/ 2 CABLE 7/8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('500', 'T 30600272', 'KIT ABRAZADERA PLASTICA P/ 3 CABLE 7/8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('501', 'T 30600273', 'KIT ABRAZADERA PLASTICA P/1 CABLE 1 1/4\"', null, 'UN');
INSERT INTO `materiales` VALUES ('502', 'T 30600274', 'KIT ABRAZADERA PLASTICA P/2 CABLE 1 1/4\"', null, 'UN');
INSERT INTO `materiales` VALUES ('503', 'T 30600275', 'KIT ABRAZADERA PLASTICA P/3 CABLE 1 1/4\"', null, 'UN');
INSERT INTO `materiales` VALUES ('504', 'T 30600276', 'KIT ABRAZADERA PLASTICA P/1 CABLE 1 5/8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('505', 'T 30600277', 'KIT ABRAZADERA PLASTICA P/2 CABLE 1 5/8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('506', 'T 30600278', 'KIT ABRAZADERA PLASTICA P/3 CABLE 1 5/8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('507', 'T 30600280', 'KIT ABRAZADERA PLASTICA P/2 CABLE 1/4\"', null, 'UN');
INSERT INTO `materiales` VALUES ('508', 'T 30600281', 'KIT ABRAZADERA PLASTICA P/3 CABLE 1/4\"', null, 'UN');
INSERT INTO `materiales` VALUES ('509', 'T 30600283', 'KIT ABRAZADERA PLASTICA P/2 CABLE RG213', null, 'UN');
INSERT INTO `materiales` VALUES ('510', 'T 30600284', 'KIT ABRAZADERA PLASTICA P/3 CABLE RG213', null, 'UN');
INSERT INTO `materiales` VALUES ('511', 'T 30600286', 'KIT ABRAZADERA PLASTICA P/2 CABLE 3/8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('512', 'T 30600287', 'KIT ABRAZADERA PLASTICA P/3 CABLE 3/8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('513', 'T 30700000', 'CUPLA DE ACOPLES DE BANDEJAS', null, 'UN');
INSERT INTO `materiales` VALUES ('514', 'T 30700002', 'VARILLA ROSC.ROSCA W5/16\"C/TUER Y ARAND.', null, 'UN');
INSERT INTO `materiales` VALUES ('515', 'T 30700005', 'CABLE 6 F.O. MONO.STD IGNIFUGO', null, 'M');
INSERT INTO `materiales` VALUES ('516', 'T 30700006', 'CABLE 12 F.O. MONO.STD. IGNIFUGO', null, 'M');
INSERT INTO `materiales` VALUES ('517', 'T 30700007', 'CABLE 24 FO G.652.D IGNIFUGO', null, 'M');
INSERT INTO `materiales` VALUES ('518', 'T 30700008', 'CABLE 60 FO G.652.D IGNIFUGO', null, 'M');
INSERT INTO `materiales` VALUES ('519', 'T 30700009', 'CABLE 96 FO G.652.D IGNIFUGO', null, 'M');
INSERT INTO `materiales` VALUES ('520', 'T 30700010', 'CABLE 48 FO G.652.D IGNIFUGO', null, 'M');
INSERT INTO `materiales` VALUES ('521', 'T 30700011', 'CABLE 144 FO?G.652.D IGNIFUGO', null, 'M');
INSERT INTO `materiales` VALUES ('522', 'T 30700012', 'CABLE 6 FO  MONO.STD. DIELEC.', null, 'M');
INSERT INTO `materiales` VALUES ('523', 'T 30700013', 'CABLE 96 FO G.652.D DIELECTRICO', null, 'M');
INSERT INTO `materiales` VALUES ('524', 'T 30700014', 'CABLE 144 FO?G.652.D DIELECTRICO', null, 'M');
INSERT INTO `materiales` VALUES ('525', 'T 30700015', 'CABLE 12 F.O.MONO.STD.DIELEC. P/AEREO', null, 'M');
INSERT INTO `materiales` VALUES ('526', 'T 30700016', 'CABLE 24 FO G.652.D DIELECTRICO P/AEREO', null, 'M');
INSERT INTO `materiales` VALUES ('527', 'T 30700017', 'CAJA P/EMPALME 60 FO DOMO', null, 'UN');
INSERT INTO `materiales` VALUES ('528', 'T 30700018', 'CAJA P/EMPALME 96 FO DOMO', null, 'UN');
INSERT INTO `materiales` VALUES ('529', 'T 30700019', 'CAJA  EMPALME 24 FO P/AEREO Y SUBT.DOMO', null, 'UN');
INSERT INTO `materiales` VALUES ('530', 'T 30700020', 'CAJA DE EMPALME P/144 FO DOMO', null, 'UN');
INSERT INTO `materiales` VALUES ('531', 'T 30700021', 'PATCHCORD FO STD E2000-APC L=6M, 900 uM', null, 'UN');
INSERT INTO `materiales` VALUES ('532', 'T 30700022', 'PATCHCORD FO STD FC-PC, L=6M, 900 uM', null, 'UN');
INSERT INTO `materiales` VALUES ('533', 'T 30700023', 'PATCHCORD FO DS FC-PC, L=6M, 900 uM', null, 'UN');
INSERT INTO `materiales` VALUES ('534', 'T 30700024', 'PATCHCORD FO STD SC-PC, L= 10M,  900 uM', null, 'UN');
INSERT INTO `materiales` VALUES ('535', 'T 30700027', 'CABEZA D/CABLE D/12 FO P/BAST. SLIM', null, 'UN');
INSERT INTO `materiales` VALUES ('536', 'T 30700028', 'ADAPTADOR D/INTERCONEXION E2000/E2000', null, 'UN');
INSERT INTO `materiales` VALUES ('537', 'T 30700029', 'DISTRIBUIDOR HASTA  864 FO P/6 CABEZAS', null, 'UN');
INSERT INTO `materiales` VALUES ('538', 'T 30700034', 'DISTRIBUIDOR 12FO L.CLIENTE PRECONEC. 6F', null, 'UN');
INSERT INTO `materiales` VALUES ('539', 'T 30700035', 'PROTECCION TERMOCON. P/EMP. FO L=60MM', null, 'UN');
INSERT INTO `materiales` VALUES ('540', 'T 30700036', 'PROTECCION TERMOCON. P/EMP. FO L=40MM', null, 'UN');
INSERT INTO `materiales` VALUES ('541', 'T 30700037', 'SOPORTE 1 CANAL P/GANANCIA DE CABLES  FO', null, 'KI');
INSERT INTO `materiales` VALUES ('542', 'T 30700038', 'SOPORTE 2 CANALES GANANCIA DE CABLE FO', null, 'KI');
INSERT INTO `materiales` VALUES ('543', 'T 30700039', 'SOPORTE INSTALACION P/CAB. AEREO H/60 FO', null, 'UN');
INSERT INTO `materiales` VALUES ('544', 'T 30700042', 'ADAPTADOR D/INTERCONEXION FC-PC/FC-PC', null, 'UN');
INSERT INTO `materiales` VALUES ('545', 'T 30700044', 'CABLE 12 F.O. DS C/ARMADURA', null, 'M');
INSERT INTO `materiales` VALUES ('546', 'T 30700045', 'CABLE 24 F.O.DS C/ARMADURA', null, 'M');
INSERT INTO `materiales` VALUES ('547', 'T 30700046', 'CABLE 30 F.O. DS C/ARMADURA', null, 'M');
INSERT INTO `materiales` VALUES ('548', 'T 30700048', 'CABLE 24 F.O.MONO.STD C/ARMADURA', null, 'M');
INSERT INTO `materiales` VALUES ('549', 'T 30700051', 'CABLE 12 F.O.MONO.STD DIELECTRICO', null, 'M');
INSERT INTO `materiales` VALUES ('550', 'T 30700052', 'CABLE 24 FO G.652.D DIELECTRICO', null, 'M');
INSERT INTO `materiales` VALUES ('551', 'T 30700053', 'CABLE 48 FO?G.652.D DIELECTRICO', null, 'M');
INSERT INTO `materiales` VALUES ('552', 'T 30700054', 'CABLE 60 FO?G.652.D DIELECTRICO', null, 'M');
INSERT INTO `materiales` VALUES ('553', 'T 30700061', 'CABLE 36 F.O. DS C/ARMADURA', null, 'M');
INSERT INTO `materiales` VALUES ('554', 'T 30700064', 'CABLE 60 F.O. DS DIELECTRICO', null, 'M');
INSERT INTO `materiales` VALUES ('555', 'T 30700067', 'PATCHCORDS F.O.DINPC/DINPC 1 MFIBRA STD', null, 'UN');
INSERT INTO `materiales` VALUES ('556', 'T 30700068', 'PATCHCORDS F.O. D4/DINPC 1,5 MFIBRA STD', null, 'UN');
INSERT INTO `materiales` VALUES ('557', 'T 30700069', 'ATENUADOR OPTICO FIJO 5 DB CONECTOR FC', null, 'UN');
INSERT INTO `materiales` VALUES ('558', 'T 30700070', 'ATENUADOR OPTICO FIJO 10 DB CONECTOR FC', null, 'UN');
INSERT INTO `materiales` VALUES ('559', 'T 30700071', 'ATENUADOR OPTICO FIJO 15 DB CONECTOR FC', null, 'UN');
INSERT INTO `materiales` VALUES ('560', 'T 30700072', 'ATENUADOR OPTICO FIJO 20 DB CONECTOR FC', null, 'UN');
INSERT INTO `materiales` VALUES ('561', 'T 30700073', 'ATENUADOR OPTICO FIJO 5 DB CONECTOR SC', null, 'UN');
INSERT INTO `materiales` VALUES ('562', 'T 30700074', 'ATENUADOR OPTICO FIJO 10 DB CONECTOR SC', null, 'UN');
INSERT INTO `materiales` VALUES ('563', 'T 30700075', 'ATENUADOR OPTICO FIJO 15 DB CONECTOR SC', null, 'UN');
INSERT INTO `materiales` VALUES ('564', 'T 30700076', 'ATENUADOR OPTICO FIJO 20 DB CONECTOR SC', null, 'UN');
INSERT INTO `materiales` VALUES ('565', 'T 30700077', 'ATENUADOR OPTICO FIJO 5 DB CONECTOR LC', null, 'UN');
INSERT INTO `materiales` VALUES ('566', 'T 30700078', 'ATENUADOR OPTICO FIJO 10 DB CONECTOR LC', null, 'UN');
INSERT INTO `materiales` VALUES ('567', 'T 30700079', 'ATENUADOR OPTICO FIJO 15 DB CONECTOR LC', null, 'UN');
INSERT INTO `materiales` VALUES ('568', 'T 30700080', 'ATENUADOR OPTICO FIJO 20 DB CONECTOR LC', null, 'UN');
INSERT INTO `materiales` VALUES ('569', 'T 30700081', 'ATENUADOR OPTICO FIJO 5 DB CONECTOR ST', null, 'UN');
INSERT INTO `materiales` VALUES ('570', 'T 30700082', 'ATENUADOR OPTICO FIJO 10 DB CONECTOR ST', null, 'UN');
INSERT INTO `materiales` VALUES ('571', 'T 30700083', 'ATENUADOR OPTICO FIJO 15 DB CONECTOR ST', null, 'UN');
INSERT INTO `materiales` VALUES ('572', 'T 30700084', 'ATENUADOR OPTICO FIJO 20 DB CONECTOR ST', null, 'UN');
INSERT INTO `materiales` VALUES ('573', 'T 30700085', 'ATENUADOR OPTICO FIJO 5 DB CONEC. E2000', null, 'UN');
INSERT INTO `materiales` VALUES ('574', 'T 30700086', 'ATENUADOR OPTICO FIJO 10 DB CONEC. E2000', null, 'UN');
INSERT INTO `materiales` VALUES ('575', 'T 30700087', 'ATENUADOR OPTICO FIJO 15 DB CONEC. E2000', null, 'UN');
INSERT INTO `materiales` VALUES ('576', 'T 30700088', 'ATENUADOR OPTICO FIJO 20 DB CONEC. E2000', null, 'UN');
INSERT INTO `materiales` VALUES ('577', 'T 30700089', 'TRAMO REC.D/250MM D/ANC Y 3000MM D/LAR.', null, 'UN');
INSERT INTO `materiales` VALUES ('578', 'T 30700090', 'TAPA PARA TRAMO RECTO DE 250MM', null, 'UN');
INSERT INTO `materiales` VALUES ('579', 'T 30700093', 'UNION \"T\" BANDEJAS DE 250 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('580', 'T 30700095', 'CURVA PLANA A 90? PARA BANDEJA', null, 'UN');
INSERT INTO `materiales` VALUES ('581', 'T 30700096', 'TAPA P/ CURVA A 90? D/BAND. D/250 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('582', 'T 30700099', 'CURVA VERTICAL PARA BANDEJAS', null, 'UN');
INSERT INTO `materiales` VALUES ('583', 'T 30700100', 'TAPA P/ CURVA VERT DEBAND. D/250MMASC', null, 'UN');
INSERT INTO `materiales` VALUES ('584', 'T 30700101', 'TAPA P/ CURVA VERT. DEBAND.D/250MM(DESC)', null, 'UN');
INSERT INTO `materiales` VALUES ('585', 'T 30700128', 'CAB 12 FO MON.STD.IGNIF.TIR.D/30 A 300MS', null, 'M');
INSERT INTO `materiales` VALUES ('586', 'T 30700129', 'CAB 12 FO MON.STD.IGN.TIR. D/301 A 600MS', null, 'M');
INSERT INTO `materiales` VALUES ('587', 'T 30700130', 'CAB 24 FO MON.STD.IGN.TIR. D/30 A 300MS', null, 'M');
INSERT INTO `materiales` VALUES ('588', 'T 30700131', 'CAB 24 FO MON.STD.IGN.TIR. D/301 A 600MS', null, 'M');
INSERT INTO `materiales` VALUES ('589', 'T 30700132', 'CAB 60 FO MON.STD.IGNIF.TIR. D/30A 300MS', null, 'M');
INSERT INTO `materiales` VALUES ('590', 'T 30700133', 'CAB 60 FO MON.STD.IGNIF.TIR.D/301A 600MS', null, 'M');
INSERT INTO `materiales` VALUES ('591', 'T 30700134', 'CAB 96 FO MON.STD.IGN.TIR. D/30A 300MS', null, 'M');
INSERT INTO `materiales` VALUES ('592', 'T 30700135', 'CAB 96 FO MON.STD.IGN.TIR. D/301 A 600MS', null, 'M');
INSERT INTO `materiales` VALUES ('593', 'T 30700136', 'CAB 48 FO MON.STD.IGN.TIR. D/30A 300MS', null, 'M');
INSERT INTO `materiales` VALUES ('594', 'T 30700137', 'CAB 48 FO MON.STD.IGN.TIR.D/301 A 600 MS', null, 'M');
INSERT INTO `materiales` VALUES ('595', 'T 30700140', 'CAB 96 FO MON.STD.DIEL.TIR. D/30 A 300MS', null, 'M');
INSERT INTO `materiales` VALUES ('596', 'T 30700144', 'CAB 24 FOMON.S.D.P/AER TIR. D/30A 300 MS', null, 'M');
INSERT INTO `materiales` VALUES ('597', 'T 30700146', 'CABLE 36 FO  NZDS DIELECTRICO', null, 'M');
INSERT INTO `materiales` VALUES ('598', 'T 30700147', 'CABLE 24 FO  NZDS DIELECTRICO', null, 'M');
INSERT INTO `materiales` VALUES ('599', 'T 30700148', 'CABLE 36 FO  NZDS IGNIFUGO', null, 'M');
INSERT INTO `materiales` VALUES ('600', 'T 30700149', 'CABLE 24 FO  NZDS IGNIFUGO', null, 'M');
INSERT INTO `materiales` VALUES ('601', 'T 30700150', 'CABLE 36 FO NZDS AUTOSOPORTADO DIELEC.', null, 'M');
INSERT INTO `materiales` VALUES ('602', 'T 30700151', 'CABLE 12 FO STD P/PRESURIZ.+4x0.6 CU', null, 'M');
INSERT INTO `materiales` VALUES ('603', 'T 30700152', 'CABLE 24 FO STD P/PRESURIZ.+4x0.6 CU', null, 'M');
INSERT INTO `materiales` VALUES ('604', 'T 30700153', 'CABLE 36 FO STD P/PRESURIZ.+4x0.6 CU', null, 'M');
INSERT INTO `materiales` VALUES ('605', 'T 30700154', 'CABLE 48 FO STD P/PRESURIZ.+4x0.6 CU', null, 'M');
INSERT INTO `materiales` VALUES ('606', 'T 30700155', 'CABLE 60 FO STD P/PRESURIZ.+4x0.6 CU', null, 'M');
INSERT INTO `materiales` VALUES ('607', 'T 30700156', 'CABLE 12 FO STD P/PRESURIZ.+4x0.6CU,IGNI', null, 'M');
INSERT INTO `materiales` VALUES ('608', 'T 30700157', 'CABLE 24 FO STD P/PRESURIZ.+4x0.6CU,IGNI', null, 'M');
INSERT INTO `materiales` VALUES ('609', 'T 30700158', 'CABLE 36 FO STD P/PRESURIZ.+4x0.6CU,IGNI', null, 'M');
INSERT INTO `materiales` VALUES ('610', 'T 30700159', 'CABLE 48 FO STD P/PRESURIZ.+4x0.6CU,IGNI', null, 'M');
INSERT INTO `materiales` VALUES ('611', 'T 30700160', 'CABLE 60 FO STD P/PRESURIZ.+4x0.6CU,IGNI', null, 'M');
INSERT INTO `materiales` VALUES ('612', 'T 30700161', 'PATCHCORD FO E2000/E2000 L=2MT, C.3MM', null, 'UN');
INSERT INTO `materiales` VALUES ('613', 'T 30700162', 'PATCHCORD FO STD E2000-APC, L=6M', null, 'UN');
INSERT INTO `materiales` VALUES ('614', 'T 30700163', 'PATCHCORD FO STD E2000-APC, L=10M', null, 'UN');
INSERT INTO `materiales` VALUES ('615', 'T 30700164', 'PATCHCORD FO STD E2000-APC, L=14M', null, 'UN');
INSERT INTO `materiales` VALUES ('616', 'T 30700165', 'PATCHCORD FO STD E2000-APC, L=20M', null, 'UN');
INSERT INTO `materiales` VALUES ('617', 'T 30700166', 'PATCHCORD FO STD E2000-APC, L=30M', null, 'UN');
INSERT INTO `materiales` VALUES ('618', 'T 30700167', 'PATCHCORD FO STD FC-PC, L=6M', null, 'UN');
INSERT INTO `materiales` VALUES ('619', 'T 30700168', 'PATCHCORD FO STD FC-PC, L=10M', null, 'UN');
INSERT INTO `materiales` VALUES ('620', 'T 30700169', 'PATCHCORD FO STD FC-PC, L=14M', null, 'UN');
INSERT INTO `materiales` VALUES ('621', 'T 30700170', 'PATCHCORD FO STD FC-PC, L=20M', null, 'UN');
INSERT INTO `materiales` VALUES ('622', 'T 30700171', 'PATCHCORD FO STD FC-PC, L=30M', null, 'UN');
INSERT INTO `materiales` VALUES ('623', 'T 30700172', 'PATCHCORD FO STD FC-PC, L=35M**inhibido*', null, 'UN');
INSERT INTO `materiales` VALUES ('624', 'T 30700174', 'PATCHCORD FO DS FC-PC, L=6M', null, 'UN');
INSERT INTO `materiales` VALUES ('625', 'T 30700175', 'PATCHCORD FO DS FC-PC, L=10M', null, 'UN');
INSERT INTO `materiales` VALUES ('626', 'T 30700176', 'PATCHCORD FO DS FC-PC, L=14M', null, 'UN');
INSERT INTO `materiales` VALUES ('627', 'T 30700177', 'PATCHCORD FO DS FC-PC, L=20M', null, 'UN');
INSERT INTO `materiales` VALUES ('628', 'T 30700178', 'PATCHCORD FO STD SC-PC, L=6 M', null, 'UN');
INSERT INTO `materiales` VALUES ('629', 'T 30700179', 'PATCHCORD FO STD SC-PC, L=10 M', null, 'UN');
INSERT INTO `materiales` VALUES ('630', 'T 30700180', 'PATCHCORD FO STD SC-PC, L=14 M', null, 'UN');
INSERT INTO `materiales` VALUES ('631', 'T 30700181', 'PATCHCORD FO STD SC-PC, L=20 M', null, 'UN');
INSERT INTO `materiales` VALUES ('632', 'T 30700182', 'PATCHCORD FO STD SC-PC, L=30 M', null, 'UN');
INSERT INTO `materiales` VALUES ('633', 'T 30700184', 'PATCHCORD FO STD ST-PC, L=10 M', null, 'UN');
INSERT INTO `materiales` VALUES ('634', 'T 30700185', 'PATCHCORD FO STD ST-PC L=14 M', null, 'UN');
INSERT INTO `materiales` VALUES ('635', 'T 30700186', 'PATCHCORD FO STD ST-PC, L=20 M', null, 'UN');
INSERT INTO `materiales` VALUES ('636', 'T 30700187', 'PATCHCORD FO STD ST-PC, L=30 M', null, 'UN');
INSERT INTO `materiales` VALUES ('637', 'T 30700188', 'PATCHCORD D4/D4 L=2MT STD', null, 'UN');
INSERT INTO `materiales` VALUES ('638', 'T 30700189', 'PATCHCORD FO D4/D4, L=3 MT', null, 'UN');
INSERT INTO `materiales` VALUES ('639', 'T 30700190', 'PATCHCORD FO D4/D4, L=5 MT', null, 'UN');
INSERT INTO `materiales` VALUES ('640', 'T 30700192', 'PATCHCORD FO D4/D4 L=15 MT', null, 'UN');
INSERT INTO `materiales` VALUES ('641', 'T 30700194', 'PATCHCORD FO D4/D4 L=2 MT DS', null, 'UN');
INSERT INTO `materiales` VALUES ('642', 'T 30700196', 'PATCHCORD FO STD  LC-PC, L=6 M', null, 'UN');
INSERT INTO `materiales` VALUES ('643', 'T 30700197', 'PATCHCORD FO STD  LC-PC, L=10 M', null, 'UN');
INSERT INTO `materiales` VALUES ('644', 'T 30700198', 'PATCHCORD FO STD  LC-PC, L=14 M', null, 'UN');
INSERT INTO `materiales` VALUES ('645', 'T 30700199', 'PATCHCORD FO STD  LC-PC, L=20 M', null, 'UN');
INSERT INTO `materiales` VALUES ('646', 'T 30700200', 'PATCHCORD FO STD  LC-PC, L=30 M', null, 'UN');
INSERT INTO `materiales` VALUES ('647', 'T 30700201', 'PATCHCORD FO STD E2000-APC/SC-PC, L=6M', null, 'UN');
INSERT INTO `materiales` VALUES ('648', 'T 30700202', 'PATCHCORD FO STD E2000-APC/SC-PC, L=10M', null, 'UN');
INSERT INTO `materiales` VALUES ('649', 'T 30700203', 'PATCHCORD FO STD E2000-APC/SC-PC, L=14M', null, 'UN');
INSERT INTO `materiales` VALUES ('650', 'T 30700204', 'PATCHCORD FO STD E2000-APC/SC-PC, L=20M', null, 'UN');
INSERT INTO `materiales` VALUES ('651', 'T 30700205', 'PATCHCORD FO STD E2000-APC/SC-PC, L=30M', null, 'UN');
INSERT INTO `materiales` VALUES ('652', 'T 30700206', 'PATCHCORD FO STD E2000-APC/ST-PC, L=6M', null, 'UN');
INSERT INTO `materiales` VALUES ('653', 'T 30700207', 'PATCHCORD FO STD E2000-APC/ST-PC, L=10M', null, 'UN');
INSERT INTO `materiales` VALUES ('654', 'T 30700208', 'PATCHCORD FO STD E2000-APC/ST-PC, L=14M', null, 'UN');
INSERT INTO `materiales` VALUES ('655', 'T 30700209', 'PATCHCORD FO STD E2000-APC/ST-PC, L=20M', null, 'UN');
INSERT INTO `materiales` VALUES ('656', 'T 30700210', 'PATCHCORD FO STD E2000-APC/ST-PC, L=30M', null, 'UN');
INSERT INTO `materiales` VALUES ('657', 'T 30700211', 'PATCHCORD FO STD E2000-APC/FC-PC, L=6 M', null, 'UN');
INSERT INTO `materiales` VALUES ('658', 'T 30700212', 'PATCHCORD FO STD E2000-APC/FC-PC, L=10M', null, 'UN');
INSERT INTO `materiales` VALUES ('659', 'T 30700213', 'PATCHCORD FO STD E2000-APC/FC-PC, L=14M', null, 'UN');
INSERT INTO `materiales` VALUES ('660', 'T 30700214', 'PATCHCORD FO STD E2000-APC/FC-PC, L=20M', null, 'UN');
INSERT INTO `materiales` VALUES ('661', 'T 30700215', 'PATCHCORD FO STD E2000-APC/FC-PC, L=30M', null, 'UN');
INSERT INTO `materiales` VALUES ('662', 'T 30700216', 'PATCHCORD FO STD FC-PC/SC-PC, L=6 M', null, 'UN');
INSERT INTO `materiales` VALUES ('663', 'T 30700217', 'PATCHCORD FO STD FC-PC/SC-PC, L=10 M', null, 'UN');
INSERT INTO `materiales` VALUES ('664', 'T 30700218', 'PATCHCORD FO STD FC-PC/SC-PC, L=14 M', null, 'UN');
INSERT INTO `materiales` VALUES ('665', 'T 30700219', 'PATCHCORD FO STD FC-PC/SC-PC, L=20 M', null, 'UN');
INSERT INTO `materiales` VALUES ('666', 'T 30700220', 'PATCHCORD FO STD FC-PC/SC-PC, L=30 M', null, 'UN');
INSERT INTO `materiales` VALUES ('667', 'T 30700221', 'PATCHCORD FO STD FC-PC,  L=35 MT', null, 'UN');
INSERT INTO `materiales` VALUES ('668', 'T 30700222', 'PATCHCORD FO STD FC-PC/ST-PC, L=6 M', null, 'UN');
INSERT INTO `materiales` VALUES ('669', 'T 30700223', 'PATCHCORD FO STD FC-PC/ST-PC, L=10 M', null, 'UN');
INSERT INTO `materiales` VALUES ('670', 'T 30700224', 'PATCHCORD FO STD FC-PC/ST-PC, L=14 M', null, 'UN');
INSERT INTO `materiales` VALUES ('671', 'T 30700225', 'PATCHCORD FO STD FC-PC/ST-PC, L=20 M', null, 'UN');
INSERT INTO `materiales` VALUES ('672', 'T 30700227', 'PATCHCORD FO STD SC-PC/ST-PC, L=6 MTS', null, 'UN');
INSERT INTO `materiales` VALUES ('673', 'T 30700228', 'PATCHCORD FO STD SC-PC/ST-PC, L=10 M', null, 'UN');
INSERT INTO `materiales` VALUES ('674', 'T 30700229', 'PATCHCORD FO STD SC-PC/ST-PC, L=14 M', null, 'UN');
INSERT INTO `materiales` VALUES ('675', 'T 30700230', 'PATCHCORD FO STD SC-PC/ST-PC, L=20 M', null, 'UN');
INSERT INTO `materiales` VALUES ('676', 'T 30700231', 'PATCHCORD FO STD SC-PC/ST-PC, L=30 M', null, 'UN');
INSERT INTO `materiales` VALUES ('677', 'T 30700232', 'PATCHCORD FO D4/DINPC L=10 MT', null, 'UN');
INSERT INTO `materiales` VALUES ('678', 'T 30700233', 'PATCHCORD FO D4/DINPC L=15 MT', null, 'UN');
INSERT INTO `materiales` VALUES ('679', 'T 30700234', 'PATCHCORD FO D4/SCPC L=5 MT', null, 'UN');
INSERT INTO `materiales` VALUES ('680', 'T 30700235', 'PATCHCORD FO SCPC/D4 L=10 MT', null, 'UN');
INSERT INTO `materiales` VALUES ('681', 'T 30700236', 'PATCHCORD FO SCPC/DINPC L=10', null, 'UN');
INSERT INTO `materiales` VALUES ('682', 'T 30700237', 'PATCHCORD FO SCPC/DINPC L=15', null, 'UN');
INSERT INTO `materiales` VALUES ('683', 'T 30700238', 'PATCHCORD FO FCPC/DINPC L=5 MT', null, 'UN');
INSERT INTO `materiales` VALUES ('684', 'T 30700241', 'PATCHCORD FO STD  FCPC/D4 L=25 MTS', null, 'UN');
INSERT INTO `materiales` VALUES ('685', 'T 30700242', 'PATCHCORD FO STD FC-PC/LC-PC,  L=6 M', null, 'UN');
INSERT INTO `materiales` VALUES ('686', 'T 30700243', 'PATCHCORD FO STD FC-PC/LC-PC,  L=10 M', null, 'UN');
INSERT INTO `materiales` VALUES ('687', 'T 30700244', 'PATCHCORD FO STD FC-PC/LC-PC,  L=14 M', null, 'UN');
INSERT INTO `materiales` VALUES ('688', 'T 30700246', 'PATCHCORD FO STD FC-PC/LC-PC,  L=20 M', null, 'UN');
INSERT INTO `materiales` VALUES ('689', 'T 30700247', 'PATCHCORD FO STD FC-PC/LC-PC,  L=25 MTS', null, 'UN');
INSERT INTO `materiales` VALUES ('690', 'T 30700248', 'PATCHCORD FO STD FC-PC/LC-PC  L=30 M', null, 'UN');
INSERT INTO `materiales` VALUES ('691', 'T 30700249', 'PATCHCORD FO STD E2000-APC/LC-PC, L=6 M', null, 'UN');
INSERT INTO `materiales` VALUES ('692', 'T 30700250', 'PATCHCORD FO STD E2000-APC/LC-PC, L=10M', null, 'UN');
INSERT INTO `materiales` VALUES ('693', 'T 30700251', 'PATCHCORD FO STD E2000-APC/LC-PC, L= 14M', null, 'UN');
INSERT INTO `materiales` VALUES ('694', 'T 30700252', 'PATCHCORD FO STD E2000-APC/LC-PC, L=20M', null, 'UN');
INSERT INTO `materiales` VALUES ('695', 'T 30700253', 'PATCHCORD FO STD E2000-APC/LC-PC,  L=30M', null, 'UN');
INSERT INTO `materiales` VALUES ('696', 'T 30700254', 'PATCHCORD FO STD ST-PC/LC-PC,  L=6 M', null, 'UN');
INSERT INTO `materiales` VALUES ('697', 'T 30700255', 'PATCHCORD FO STD ST-PC/LC-PC,  L=10 M', null, 'UN');
INSERT INTO `materiales` VALUES ('698', 'T 30700256', 'PATCHCORD FO STD ST-PC/LC-PC,  L=14 M', null, 'UN');
INSERT INTO `materiales` VALUES ('699', 'T 30700257', 'PATCHCORD FO STD ST-PC/LC-PC,  L=20 M', null, 'UN');
INSERT INTO `materiales` VALUES ('700', 'T 30700258', 'PATCHCORD FO STD ST-PC/LC-PC,  L=30 M', null, 'UN');
INSERT INTO `materiales` VALUES ('701', 'T 30700259', 'PATCHCORD FO STD SC-PC/LC-PC,  L=6 M', null, 'UN');
INSERT INTO `materiales` VALUES ('702', 'T 30700260', 'PATCHCORD FO STD SC-PC/LC-PC,  L=10 M', null, 'UN');
INSERT INTO `materiales` VALUES ('703', 'T 30700261', 'PATCHCORD FO STD SC-PC/LC-PC,  L=14 M', null, 'UN');
INSERT INTO `materiales` VALUES ('704', 'T 30700262', 'PATCHCORD FO STD SC-PC/LC-PC,  L=20 M', null, 'UN');
INSERT INTO `materiales` VALUES ('705', 'T 30700263', 'PATCHCORD FO STD SC-PC/LC-PC,  L=30 M', null, 'UN');
INSERT INTO `materiales` VALUES ('706', 'T 30700264', 'PATCHCORD FO STD E2000-APC/D4  30 M', null, 'UN');
INSERT INTO `materiales` VALUES ('707', 'T 30700265', 'BASTIDOR FO P/CABEZAS 19\" ( 220x30x30CM)', null, 'UN');
INSERT INTO `materiales` VALUES ('708', 'T 30700268', 'CABEZA D/CAB.72 FO /BAST.TIPO ADC IZQUIE', null, 'UN');
INSERT INTO `materiales` VALUES ('709', 'T 30700269', 'ADAPTADOR HEMBRA/HEMBRAP/CONECTORES ST', null, 'UN');
INSERT INTO `materiales` VALUES ('710', 'T 30700270', 'CABEZA D/CABLE 96 FO LADO DERECHO', null, 'UN');
INSERT INTO `materiales` VALUES ('711', 'T 30700271', 'CABEZA D/CABLE 96 FO LADO IZQUIERDO', null, 'UN');
INSERT INTO `materiales` VALUES ('712', 'T 30700272', 'BASTIDOR FO P/CABEZAS 19\" ( 220x60x30CM)', null, 'UN');
INSERT INTO `materiales` VALUES ('713', 'T 30700274', 'CABEZA. D/CAB P/24 FO P/BAST. ETSI \"ADC\"', null, 'UN');
INSERT INTO `materiales` VALUES ('714', 'T 30700275', 'CABEZA DE CABLE 19\" P/12 FO', null, 'UN');
INSERT INTO `materiales` VALUES ('715', 'T 30700278', 'CAB 24 FODS C/ARMAD. TIR. D/301 A 600MS', null, 'M');
INSERT INTO `materiales` VALUES ('716', 'T 30700281', 'CAB 12 FO MON.STD C/AR.TIR.D/30 A 300 MS', null, 'M');
INSERT INTO `materiales` VALUES ('717', 'T 30700282', 'CAB 12 FO MON.STD C/AR.TIR.D/301 A 600MS', null, 'M');
INSERT INTO `materiales` VALUES ('718', 'T 30700283', 'CAB 24 FOMON.STD C/ARM.TIR. D/30A 300MS', null, 'M');
INSERT INTO `materiales` VALUES ('719', 'T 30700284', 'CAB 24 FOMON.STD C/ARM.TIR. D/301A 600MS', null, 'M');
INSERT INTO `materiales` VALUES ('720', 'T 30700285', 'CAB 48 FOMON.STD C/ARM TIR. D/30 A 300MS', null, 'M');
INSERT INTO `materiales` VALUES ('721', 'T 30700286', 'CAB 48 FOMON.STD C/ARM TIR. D/301A 600MS', null, 'M');
INSERT INTO `materiales` VALUES ('722', 'T 30700287', 'CAB 60 FOMON.STD C/ARM TIR. D/30 A 300MS', null, 'M');
INSERT INTO `materiales` VALUES ('723', 'T 30700288', 'CAB 60 FOMON.STD C/ARM TIR. D/301A 600MS', null, 'M');
INSERT INTO `materiales` VALUES ('724', 'T 30700290', 'CAB 12 FO MON.STD DIEL.TIR. D/301A 600MS', null, 'M');
INSERT INTO `materiales` VALUES ('725', 'T 30700291', 'CAB 24 FOMON.STD DIELEC.TIR. D/30A 300MS', null, 'M');
INSERT INTO `materiales` VALUES ('726', 'T 30700292', 'CAB 24 FOMON.STD DIE.TIR. D/301 A 600 MS', null, 'M');
INSERT INTO `materiales` VALUES ('727', 'T 30700293', 'CAB 48 FOMON.STD DIEL.TIR. D/30 A 300 MS', null, 'M');
INSERT INTO `materiales` VALUES ('728', 'T 30700294', 'CAB 48 FOMON.STD DIEL.TIR. D/301 A 600MS', null, 'M');
INSERT INTO `materiales` VALUES ('729', 'T 30700295', 'CAB 60 FO MON.STD DIEL.TIR. D/30 A 300MS', null, 'M');
INSERT INTO `materiales` VALUES ('730', 'T 30700296', 'CAB 60 FO MON.STD DIEL.TIR. D/301A 600MS', null, 'M');
INSERT INTO `materiales` VALUES ('731', 'T 30700298', 'CAJA D/EMPALME  72 F.O. (RECTANGULAR)', null, 'UN');
INSERT INTO `materiales` VALUES ('732', 'T 30700299', 'CAJA D/EMPALME 24 FO (RECTANGULAR)', null, 'UN');
INSERT INTO `materiales` VALUES ('733', 'T 30700302', 'CAB 24 FO NZDS C/AR.TIR. D/301 A 600MS', null, 'UN');
INSERT INTO `materiales` VALUES ('734', 'T 30700306', 'CORDON CONECT.F.OPTICA LSH-HRL', null, 'UN');
INSERT INTO `materiales` VALUES ('735', 'T 30700371', 'CABEZA D/CABLE 144 FO LADO DERECHO', null, 'UN');
INSERT INTO `materiales` VALUES ('736', 'T 30700372', 'CABEZA D/CABLE 144 FO LADO IZQUIERDO', null, 'UN');
INSERT INTO `materiales` VALUES ('737', 'T 30700391', 'CABLE 36 FO G.652.D DIELEC. P/ENTERRAR', null, 'M');
INSERT INTO `materiales` VALUES ('738', 'T 30700401', 'DISTRIBUIDOR FO(FTTX) P/EDIF L/CENTRAL', null, 'UN');
INSERT INTO `materiales` VALUES ('739', 'T 30700442', 'CABLE 60 FO G.652.D DIELECTRICO P/AEREO', null, 'M');
INSERT INTO `materiales` VALUES ('740', 'T 30700466', 'CAJA D/EMPALME P/8 SPLITTER FO', null, 'UN');
INSERT INTO `materiales` VALUES ('741', 'T 30700467', 'CAJA D/DISTRIBUCION 2 FO', null, 'UN');
INSERT INTO `materiales` VALUES ('742', 'T 30700468', 'CAJA D/DISTRIBUCION 8/16 FO', null, 'UN');
INSERT INTO `materiales` VALUES ('743', 'T 30700469', 'CAJA DISTRIBUCION 12 FO P/EDIFICO', null, 'UN');
INSERT INTO `materiales` VALUES ('744', 'T 30700482', 'BLOQUE TERMINAL OPTICO (BTO) P/EXTERIOR', null, 'UN');
INSERT INTO `materiales` VALUES ('745', 'T 30700492', 'CABLE 1 FO G.657 B3 P/BAJADA FTTH', null, 'M');
INSERT INTO `materiales` VALUES ('746', 'T 30700493', 'CABLE 12 FO G657 A1 P/DITRIBUCION FTTH', null, 'M');
INSERT INTO `materiales` VALUES ('747', 'T 30700494', 'CABLE 1 FO G.657 B3 P/INST.INTERIOR FTTH', null, 'M');
INSERT INTO `materiales` VALUES ('748', 'T 30700495', 'CABLE 48 FO G657 A2 P/VERTICAL P/EDIF.', null, 'M');
INSERT INTO `materiales` VALUES ('749', 'T 30700501', 'PATHCORD FO SC-APC (G.657B3) L=2MTS', null, 'UN');
INSERT INTO `materiales` VALUES ('750', 'T 30700504', 'DIVISOR OPTICO PASIVO 2:8 CON.SC-APC LGX', null, 'UN');
INSERT INTO `materiales` VALUES ('751', 'T 30700505', 'DIVISOR OPTICO PASIVO 1:8 S/CONECT', null, 'UN');
INSERT INTO `materiales` VALUES ('752', 'T 30700506', 'DIVISOR OPTICO PASIVO 2:64 CONEC. SC-APC', null, 'UN');
INSERT INTO `materiales` VALUES ('753', 'T 30700511', 'CABLE 288 FO G.652.D DIELECTRICO', null, 'M');
INSERT INTO `materiales` VALUES ('754', 'T 30700512', 'CAJA P/EMPALME 288FO DOMO', null, 'UN');
INSERT INTO `materiales` VALUES ('755', 'T 30700531', 'CABLE 144 FO G.652.D DIELECTRICO P/AEREO', null, 'M');
INSERT INTO `materiales` VALUES ('756', 'T 30700591', 'SOPORTE INSTALACION P/CAB AEREO DE 144FO', null, 'UN');
INSERT INTO `materiales` VALUES ('757', 'T 30700592', 'CONJ. PREFORMADO P/FO AEREA DE 144FO', null, 'UN');
INSERT INTO `materiales` VALUES ('758', 'T 30700632', 'ARMARIO DISTRIB. FO FTTX 96 CONECTORES', null, 'UN');
INSERT INTO `materiales` VALUES ('759', 'T 30700701', 'PATCHCORD FO G657A2 SC-APC L=6M, 900 uM', null, 'UN');
INSERT INTO `materiales` VALUES ('760', 'T 30700724', 'DISTRIBUIDOR FO(FTTX) P/EDIF.48C L/CLIE.', null, 'UN');
INSERT INTO `materiales` VALUES ('761', 'T 30701031', 'KIT CONECTOR MECANICO SC-APC C/CORTADORA', null, 'KI');
INSERT INTO `materiales` VALUES ('762', 'T 30701121', 'MICROCABLE 24 FO CORRUGADO  DIEL?CTRICO', null, 'M');
INSERT INTO `materiales` VALUES ('763', 'T 30701122', 'CAJA D/DISTRIBUCION 8 FO P/EDIF. CONECT.', null, 'UN');
INSERT INTO `materiales` VALUES ('764', 'T 30800000', 'SUFRIDERA (PERNO HINCADO)PARA JABALINA', null, 'UN');
INSERT INTO `materiales` VALUES ('765', 'T 30800001', 'JABALINA PARA TOMA TIERRA', null, 'UN');
INSERT INTO `materiales` VALUES ('766', 'T 30800002', 'MANGUITO D/ACOPLAMIENTO  D/JABALINA', null, 'UN');
INSERT INTO `materiales` VALUES ('767', 'T 30800003', 'TOMA CABLE DE TIERRA A JABALINA', null, 'UN');
INSERT INTO `materiales` VALUES ('768', 'T 30800058', 'BATERIA 7AH P/PCU D/TERMINAL WLL TADIRAN', null, 'UN');
INSERT INTO `materiales` VALUES ('769', 'T 30800059', 'BATERIA 3,3 AH P/PCU TERMINALWLL TADIRAN', null, 'UN');
INSERT INTO `materiales` VALUES ('770', 'T 30800060', 'BATERIA TERMINAL WLL KRONE.TELELINK 2', null, 'UN');
INSERT INTO `materiales` VALUES ('771', 'T 30800062', 'PROTECTOR DE TENSION P/TERMINALES WLL', null, 'UN');
INSERT INTO `materiales` VALUES ('772', 'T 30800063', 'REGULADOR DE TENSION P/PANELES SOLARES', null, 'UN');
INSERT INTO `materiales` VALUES ('773', 'T 31200000', 'TAPA+ARMAZON COMPLETA P/CAM.CALZADA', null, 'UN');
INSERT INTO `materiales` VALUES ('774', 'T 31200001', 'TAPA CAMARA EXTERIOR D=921 MM P/EX222102', null, 'UN');
INSERT INTO `materiales` VALUES ('775', 'T 31200003', 'ANILLO CAUCHO D=749 MM  P/CAMARA CALZADA', null, 'UN');
INSERT INTO `materiales` VALUES ('776', 'T 31200005', 'CAMARA PREMOLDEADA D/3 TAPAS', null, 'UN');
INSERT INTO `materiales` VALUES ('777', 'T 31200006', 'CAMARA PREMOLDEADA D/4 TAPAS', null, 'UN');
INSERT INTO `materiales` VALUES ('778', 'T 31200007', 'TAPA PARA CAMARAS PREMOLDEADAS', null, 'UN');
INSERT INTO `materiales` VALUES ('779', 'T 31200008', 'CAMARA DISTRIBUCION D/PRFV TIPO DU4', null, 'UN');
INSERT INTO `materiales` VALUES ('780', 'T 31200009', 'CAMARA ACCESO DE VEREDA 500x270x250 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('781', 'T 31200010', 'TAPA+ARMAZON COMPL.P/CAM.VEREDA AL=250MM', null, 'UN');
INSERT INTO `materiales` VALUES ('782', 'T 31200011', 'TAPA+ARMAZON COMPL.P/CAM.VEREDA AL=80MM', null, 'UN');
INSERT INTO `materiales` VALUES ('783', 'T 31200012', 'CONJUNTO MARCO Y TAPAPARA CAMARA DV3', null, 'UN');
INSERT INTO `materiales` VALUES ('784', 'T 31200013', 'CONJUNTO MARCO Y TAPAPARA CAMARA DV4', null, 'UN');
INSERT INTO `materiales` VALUES ('785', 'T 31200014', 'CONJUNTO MARCO Y TAPA PARA CAMARA DV5', null, 'UN');
INSERT INTO `materiales` VALUES ('786', 'T 31200015', 'CONJUNTO MARCO Y TAPAPARA CAMARA DV6', null, 'UN');
INSERT INTO `materiales` VALUES ('787', 'T 31200016', 'CONJUNTO MARCO Y TAPAPARA CAMARA DV7', null, 'UN');
INSERT INTO `materiales` VALUES ('788', 'T 31200017', 'MODULO P/TAPAS D/CAMARA DV', null, 'UN');
INSERT INTO `materiales` VALUES ('789', 'T 31200018', 'TAPA CAMARA EXTERIOR D=654 MM P/EX222151', null, 'UN');
INSERT INTO `materiales` VALUES ('790', 'T 31200019', 'TAPA CAMARA EXTERIOR D=730 MM (VEREDA)', null, 'UN');
INSERT INTO `materiales` VALUES ('791', 'T 31200020', 'TAPA CAMARA EXTERIOR D=750 MM (VEREDA)', null, 'UN');
INSERT INTO `materiales` VALUES ('792', 'T 31200021', 'TAPA CAMARA INTERIOR D=720 MM P/EX222153', null, 'UN');
INSERT INTO `materiales` VALUES ('793', 'T 31200023', 'TAPA CAM. EXTER.D=725 MMP/EX222152 HORM.', null, 'UN');
INSERT INTO `materiales` VALUES ('794', 'T 31200024', 'TAPA CAM. EXTER.D=750MMP/222153/154 HOR.', null, 'UN');
INSERT INTO `materiales` VALUES ('795', 'T 31200025', 'TAPA CAMARA INTERIOR D=720 MM P/EX222154', null, 'UN');
INSERT INTO `materiales` VALUES ('796', 'T 31200026', 'REJILLA PARA CAMARA', null, 'UN');
INSERT INTO `materiales` VALUES ('797', 'T 31200027', 'CA?O ACERO CINCADO 1\" C/CUPLA D/ UNION', null, 'M');
INSERT INTO `materiales` VALUES ('798', 'T 31200028', 'CA?O ACERO CINC. 1 1/4\" C/CUPLA D/ UNION', null, 'M');
INSERT INTO `materiales` VALUES ('799', 'T 31200029', 'CA?O ACERO CINC. 1 1/2\" C/CUPLA D/ UNION', null, 'M');
INSERT INTO `materiales` VALUES ('800', 'T 31200030', 'CA?O ACERO CINC. 2\" C/CUPLA D/UNION', null, 'M');
INSERT INTO `materiales` VALUES ('801', 'T 31200031', 'CA?O ACERO CINC. 2 1/2\" C/CUPLA D/UNION', null, 'M');
INSERT INTO `materiales` VALUES ('802', 'T 31200032', 'CA?O ACERO CINC. 3\" C/CUPLA D/UNION', null, 'M');
INSERT INTO `materiales` VALUES ('803', 'T 31200033', 'CA?O ACERO CINCA. 4\" C/CUPLA D/UNION', null, 'M');
INSERT INTO `materiales` VALUES ('804', 'T 31200034', 'TUBO DE PVC RIGIDO 45/50', null, 'UN');
INSERT INTO `materiales` VALUES ('805', 'T 31200035', 'TUBO DE PVC RIGIDO 87/92', null, 'UN');
INSERT INTO `materiales` VALUES ('806', 'T 31200037', 'TUBO CORRUGADO TIPO CO-FLEX', null, 'RO');
INSERT INTO `materiales` VALUES ('807', 'T 31200038', 'MONOTUBO 32/36 P/TENDIDO EN DUCTOS', null, 'M');
INSERT INTO `materiales` VALUES ('808', 'T 31200039', 'TRITUBO D/POLIETILENO 34/40 P/TENDIDO FO', null, 'M');
INSERT INTO `materiales` VALUES ('809', 'T 31200040', 'MANGUITO UNION PARA TRITUBOS', null, 'UN');
INSERT INTO `materiales` VALUES ('810', 'T 31200041', 'TUBO DE PVC RIGIDO 87/95', null, 'UN');
INSERT INTO `materiales` VALUES ('811', 'T 31200042', 'MANGUITO UNION PARA TUBO 87/92', null, 'UN');
INSERT INTO `materiales` VALUES ('812', 'T 31200043', 'MANGUITO UNION REDUCTOR 87/95a87/92', null, 'UN');
INSERT INTO `materiales` VALUES ('813', 'T 31200044', 'CURVA 10? D/PVC RIGIDO 87/92 RADIO 3 MT', null, 'UN');
INSERT INTO `materiales` VALUES ('814', 'T 31200045', 'CURVA 90? D/PVC RIGIDO 87/92 RADIO 600MM', null, 'UN');
INSERT INTO `materiales` VALUES ('815', 'T 31200047', 'CURVA 90? D/PVC RIGIDO 45/50 RADIO 450MM', null, 'UN');
INSERT INTO `materiales` VALUES ('816', 'T 31200048', 'CURVA 90? D/PVC RIGIDO 45/50 RADIO 1MT', null, 'UN');
INSERT INTO `materiales` VALUES ('817', 'T 31200049', 'TAPA DE PVC PARA TUBOS 45/50', null, 'UN');
INSERT INTO `materiales` VALUES ('818', 'T 31200050', 'MANGUITO UNION TUBO DE PVC (45/50)', null, 'UN');
INSERT INTO `materiales` VALUES ('819', 'T 31200051', 'MANGUITO UNI.RED.P/TUBO 223383/385', null, 'UN');
INSERT INTO `materiales` VALUES ('820', 'T 31200052', 'CABEZAL D/ANCLAJE P/TUBO POLIETIL. 32/36', null, 'UN');
INSERT INTO `materiales` VALUES ('821', 'T 31200055', 'PEINE BASE 2BOCAS P/CA? 45/50 CAT.223384', null, 'UN');
INSERT INTO `materiales` VALUES ('822', 'T 31200057', 'PEINE BASE 4BOCAS P/CA? 45/50 CAT.223384', null, 'UN');
INSERT INTO `materiales` VALUES ('823', 'T 31200058', 'PEINE INTER.2X4 BOC.P/CA? 45/50 C.223384', null, 'UN');
INSERT INTO `materiales` VALUES ('824', 'T 31200059', 'PEINE BASE PL. 4BOC.P/CA? 87/92 C.223385', null, 'UN');
INSERT INTO `materiales` VALUES ('825', 'T 31200060', 'PEINE IN.PL.2X4 BOC P/CA? 87/92 C.223385', null, 'UN');
INSERT INTO `materiales` VALUES ('826', 'T 31200061', 'PEINE BASE PL. 2BOC.P/CA? 87/92 C.223385', null, 'UN');
INSERT INTO `materiales` VALUES ('827', 'T 31200062', 'PEINE IN.PL.2X2 BOC P/CA? 87/92 C.223385', null, 'UN');
INSERT INTO `materiales` VALUES ('828', 'T 31200064', 'SE?ALIZADOR LOCALIZACION MINIMARKER', null, 'UN');
INSERT INTO `materiales` VALUES ('829', 'T 31200065', 'SE?ALIZADOR LOCALIZACION  BALL MARKER', null, 'UN');
INSERT INTO `materiales` VALUES ('830', 'T 31200066', 'HILO GUIA DIAM. 2 MM', null, 'RO');
INSERT INTO `materiales` VALUES ('831', 'T 31200067', 'CHAPA P/IDENTIF. CIRCULAR P/CABLE SUBT.', null, 'UN');
INSERT INTO `materiales` VALUES ('832', 'T 31200070', 'GRAPA DE TIRO P/EMPOTRAR EN CAMARAS', null, 'UN');
INSERT INTO `materiales` VALUES ('833', 'T 31200071', 'PROTECCION PLAST.  \"U\" P/CAB. 45x525MM', null, 'UN');
INSERT INTO `materiales` VALUES ('834', 'T 31200073', 'CANALETA PROTEC.P/CAB.CINC.135X50X2000MM', null, 'UN');
INSERT INTO `materiales` VALUES ('835', 'T 31200074', 'CANALETA PROTEC.P/CAB.CINC.135X90X2000MM', null, 'UN');
INSERT INTO `materiales` VALUES ('836', 'T 31200075', 'CANALETA PROTEC.P/CAB. ALQ. 80X80X2000MM', null, 'UN');
INSERT INTO `materiales` VALUES ('837', 'T 31200076', 'CANALETA PROTEC.P/CAB.ALQ. 135X50X2000MM', null, 'UN');
INSERT INTO `materiales` VALUES ('838', 'T 31200077', 'CANALETA PROTEC.P/CAB.ALQ. 135X90X2000MM', null, 'UN');
INSERT INTO `materiales` VALUES ('839', 'T 31200079', 'REGLETA C/18 ABERTURAS P/SOPORTES D/CAB.', null, 'UN');
INSERT INTO `materiales` VALUES ('840', 'T 31200080', 'SOPORTE D/ACERO P/CABLES L=100MM', null, 'UN');
INSERT INTO `materiales` VALUES ('841', 'T 31200081', 'SOPORTE D/ACERO P/CABLES 190MM', null, 'UN');
INSERT INTO `materiales` VALUES ('842', 'T 31200082', 'SOPORTE D/ACERO P/CABLES L=255MM', null, 'UN');
INSERT INTO `materiales` VALUES ('843', 'T 31200084', 'CORDON FIB. VIDR. A.S.C.C. MAIL 25/06/03', null, 'UN');
INSERT INTO `materiales` VALUES ('844', 'T 31200088', 'TAPON CERRADO P/DUCTO FO 32/34', null, 'UN');
INSERT INTO `materiales` VALUES ('845', 'T 31200089', 'TAPON ABIERTO P/DUCTO FO 32/34', null, 'UN');
INSERT INTO `materiales` VALUES ('846', 'T 31200090', 'TAPON CERRADO P/DUCTO FO 34/40', null, 'UN');
INSERT INTO `materiales` VALUES ('847', 'T 31200091', 'TAPON ABIERTO P/DUCTO FO 34/40', null, 'UN');
INSERT INTO `materiales` VALUES ('848', 'T 31200092', 'TAPON ABIERTO P/TUB 45/50  CAB.D=9a18MM', null, 'UN');
INSERT INTO `materiales` VALUES ('849', 'T 31200093', 'TAPON ABIERTO P/TUB 45/50  CAB.D=18a28MM', null, 'UN');
INSERT INTO `materiales` VALUES ('850', 'T 31200094', 'TAPON ABIERTO P/TUB 87/XX  CAB.D=18a38MM', null, 'UN');
INSERT INTO `materiales` VALUES ('851', 'T 31200095', 'TAPON ABIERTO P/TUB 87/XX  CAB.D=35a55MM', null, 'UN');
INSERT INTO `materiales` VALUES ('852', 'T 31200096', 'TAPON ABIERTO P/TUB 87/XX  CAB.D=55a69MM', null, 'UN');
INSERT INTO `materiales` VALUES ('853', 'T 31200097', 'TAPON CERRADO P/TUBOS 87/XX', null, 'UN');
INSERT INTO `materiales` VALUES ('854', 'T 31200099', 'CONJ.M/T D/FUNDICONP/CAMARA DV5 (VEREDA)', null, 'UN');
INSERT INTO `materiales` VALUES ('855', 'T 31200100', 'CONJ.M/T D/FUN.P/CAM. DV4 /CALZ.800X1300', null, 'UN');
INSERT INTO `materiales` VALUES ('856', 'T 31200103', 'MARCO DV5 P/TAPAS DE ACERO ESTAMPADO', null, 'UN');
INSERT INTO `materiales` VALUES ('857', 'T 31200104', 'MARCO DV4 P/TAPAS DE ACERO ESTAMPADO', null, 'UN');
INSERT INTO `materiales` VALUES ('858', 'T 31200105', 'ACOPLE P/TUBO D/25MM EN CAMARAS PRFV', null, 'UN');
INSERT INTO `materiales` VALUES ('859', 'T 31200110', 'ACOPLE P/TUBO D/90MM EN CAMARAS PRFV', null, 'UN');
INSERT INTO `materiales` VALUES ('860', 'T 31200111', 'CONJUNTO MARCO/TAPA DV3 D/ACERO ESTAMP.', null, 'UN');
INSERT INTO `materiales` VALUES ('861', 'T 31200112', 'CONJUNTO MARCO/TAPA DV4 D/ACERO ESTAMP.', null, 'UN');
INSERT INTO `materiales` VALUES ('862', 'T 31200113', 'CONJUNTO MARCO/TAPA DV5 D/ACERO ESTAMP.', null, 'UN');
INSERT INTO `materiales` VALUES ('863', 'T 31200116', 'TUBO POLIETILENO DIAM. EXT.=110 MM', null, 'M');
INSERT INTO `materiales` VALUES ('864', 'T 31200117', 'TUBO POLIETILENO DIAM. EXT.=25 MM', null, 'M');
INSERT INTO `materiales` VALUES ('865', 'T 31200118', 'TUBO POLIETILENO DIAM. EXT.=32 MM', null, 'M');
INSERT INTO `materiales` VALUES ('866', 'T 31200120', 'TUBO POLIETILENO DIAM. EXT.=50 MM', null, 'M');
INSERT INTO `materiales` VALUES ('867', 'T 31200121', 'TUBO POLIETILENO DIAM. EXT.=63 MM', null, 'M');
INSERT INTO `materiales` VALUES ('868', 'T 31200122', 'TUBO POLIETILENO DIAM. EXT.=90 MM', null, 'M');
INSERT INTO `materiales` VALUES ('869', 'T 31200123', 'HITO D/SE?ALIZ. D/CAMRA D/EMPALME CAB.FO', null, 'UN');
INSERT INTO `materiales` VALUES ('870', 'T 31200124', 'HITO D/SE?ALIZ. P/CAMARA D/PASO CAB. FO', null, 'UN');
INSERT INTO `materiales` VALUES ('871', 'T 31200125', 'TAPON ABIER. P/TUBO 99/110 CAB.D=45a55MM', null, 'UN');
INSERT INTO `materiales` VALUES ('872', 'T 31200127', 'TAPON ABIER.P/TUBO 19/25 CAB.D=7.5a12MM', null, 'UN');
INSERT INTO `materiales` VALUES ('873', 'T 31200128', 'TAPON ABIER. P/TUBO 29/32 CAB. D=12a16MM', null, 'UN');
INSERT INTO `materiales` VALUES ('874', 'T 31200129', 'TAPON ABIER. P/TUBO 33/40 CAB. D=16a19MM', null, 'UN');
INSERT INTO `materiales` VALUES ('875', 'T 31200130', 'TAPON ABIER. P/TUBO 43/50 CAB. D=19a25MM', null, 'UN');
INSERT INTO `materiales` VALUES ('876', 'T 31200132', 'TAPON ABIER. P/TUBO 87/90 CAB. D=45a55MM', null, 'UN');
INSERT INTO `materiales` VALUES ('877', 'T 31200133', 'TAPON CERRADO P/TUBOS 93,8/110 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('878', 'T 31200135', 'TAPON CERRADO P/TUBOS 24,8/32 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('879', 'T 31200137', 'TAPON CERRADO P/TUBOS 42,6/50 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('880', 'T 31200138', 'TAPON CERRADO P/TUBOS 53,6/63 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('881', 'T 31200139', 'TAPON CERRADO P/TUBOS 76,8/90 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('882', 'T 31200141', 'TAPA/MARCO P/CAMARAS DV3 EN PRFV', null, 'UN');
INSERT INTO `materiales` VALUES ('883', 'T 31200145', 'DILUYENTE P/SELLADOR D/TAPAS D/CAMARAS', null, 'KI');
INSERT INTO `materiales` VALUES ('884', 'T 31200146', 'SELLADOR PARA TAPAS DE CAMARAS', null, 'KI');
INSERT INTO `materiales` VALUES ('885', 'T 31200149', 'CONJUNTO MARCO/TAPA DV6 D/ACERO ESTAMP.', null, 'UN');
INSERT INTO `materiales` VALUES ('886', 'T 31200151', 'CONJUNTO MARCO/TAPA DV7 D/ACERO ESTAMP.', null, 'UN');
INSERT INTO `materiales` VALUES ('887', 'T 31300001', 'ARMARIO POSTES 700 PARES', null, 'UN');
INSERT INTO `materiales` VALUES ('888', 'T 31300002', 'ARMARIO PARA POSTES 300 PARES', null, 'UN');
INSERT INTO `materiales` VALUES ('889', 'T 31300003', 'ARMARIO DE 1 PUERTA DE 700 PARES', null, 'UN');
INSERT INTO `materiales` VALUES ('890', 'T 31300005', 'ARMARIO DE 2  PUERTAS 1400 PARES', null, 'UN');
INSERT INTO `materiales` VALUES ('891', 'T 31300006', 'SOPORTE P/10 MODULOS TIPO KRONE ALTO', null, 'UN');
INSERT INTO `materiales` VALUES ('892', 'T 31300007', 'MODULO CONEXION TIPO KRONE 10 PARES', null, 'UN');
INSERT INTO `materiales` VALUES ('893', 'T 31300008', 'MODULO CORTE Y PRUEBA  TIPO KRONE 10 PS', null, 'UN');
INSERT INTO `materiales` VALUES ('894', 'T 31300009', 'MAGAZINE PROTECCION P/MODULOS TIPO KRONE', null, 'UN');
INSERT INTO `materiales` VALUES ('895', 'T 31300010', 'MAGAZINE PROTECCION KRONE', null, 'UN');
INSERT INTO `materiales` VALUES ('896', 'T 31300011', 'DESCARGADOR GASEOSO P/MAGAZINE EX210021', null, 'UN');
INSERT INTO `materiales` VALUES ('897', 'T 31300012', 'TAPA PROTECTORAP/MODULOS 210016/017', null, 'UN');
INSERT INTO `materiales` VALUES ('898', 'T 31300013', 'CORDON D/PRUEBA TETRAPOLAR P/MOD.KRONE', null, 'UN');
INSERT INTO `materiales` VALUES ('899', 'T 31300017', 'MAGAZINE PROTEC.P/MOD.POUYET S/CONTINUID', null, 'UN');
INSERT INTO `materiales` VALUES ('900', 'T 31300018', 'MODULO CORTE Y PRUEBA TIPO POUYET 10 PS', null, 'UN');
INSERT INTO `materiales` VALUES ('901', 'T 31300019', 'MODULO CONEXION TIPO POUYET 10 PARES', null, 'UN');
INSERT INTO `materiales` VALUES ('902', 'T 31300020', 'CORDON PUENTE P/MODULOS TIPO POUYET', null, 'UN');
INSERT INTO `materiales` VALUES ('903', 'T 31300021', 'CORDON PRUEBA TETRAPOLAR MOD.TIPO POUYE', null, 'UN');
INSERT INTO `materiales` VALUES ('904', 'T 31300024', 'DESCARGADOR GASEOSO TRIP.P/MAGAZ.210037', null, 'UN');
INSERT INTO `materiales` VALUES ('905', 'T 31300026', 'DEFENSA PROTECTORA DE  ARMARIOS', null, 'UN');
INSERT INTO `materiales` VALUES ('906', 'T 31300027', 'SOPORTE P/10 MODULOS TIPO POUYET', null, 'UN');
INSERT INTO `materiales` VALUES ('907', 'T 31300029', 'SOPORTE P/MODULOS POUYET VER. FRANCES', null, 'UN');
INSERT INTO `materiales` VALUES ('908', 'T 31300031', 'SOPORTE P/10 MODULOS TIPO KRONE BAJO', null, 'UN');
INSERT INTO `materiales` VALUES ('909', 'T 31300032', 'BLOQUE HORIZONTAL D/ALTA DENSID. C/CORTE', null, 'UN');
INSERT INTO `materiales` VALUES ('910', 'T 31300037', 'MODULO D/HERRAJES REPART. MED. Y GRANDE', null, 'UN');
INSERT INTO `materiales` VALUES ('911', 'T 31300038', 'MODULO D/HERRAJ.REPART. D/PARED O CHICO', null, 'UN');
INSERT INTO `materiales` VALUES ('912', 'T 31300039', 'CONJUNTO D/HERRAJE  P/TUNEL DE/CABLES', null, 'UN');
INSERT INTO `materiales` VALUES ('913', 'T 31300040', 'CONJUNTO D/HERRAJE FIN P/TUNEL D/CABLES', null, 'UN');
INSERT INTO `materiales` VALUES ('914', 'T 31300041', 'JUEGO PARRILLAS PROTEC.REPAR .MED./GRAND', null, 'UN');
INSERT INTO `materiales` VALUES ('915', 'T 31300042', 'SOPORTE GUIA P/HERRAJES EN REPARTIDORES', null, 'UN');
INSERT INTO `materiales` VALUES ('916', 'T 31300045', 'MANIJA PLASTICAARMARIO DE 700/1400 PS', null, 'UN');
INSERT INTO `materiales` VALUES ('917', 'T 31300046', 'PLACA PESTILLO', null, 'UN');
INSERT INTO `materiales` VALUES ('918', 'T 31300047', 'PUERTA DE ZOCALO', null, 'UN');
INSERT INTO `materiales` VALUES ('919', 'T 31300053', 'TECHO EXTERNOARMARIOS DE 700/1400 PS', null, 'UN');
INSERT INTO `materiales` VALUES ('920', 'T 31300054', 'BURLETE P/ PUERTA ARM. D/700/1400 PS', null, 'UN');
INSERT INTO `materiales` VALUES ('921', 'T 31300073', 'CIERRE MEC.P/ARM C/COMB. P/REG.LIT./MEDI', null, 'UN');
INSERT INTO `materiales` VALUES ('922', 'T 31300074', 'LLAVE P/CIERRRE .MEC.ARM.C/COMB  LIT/MED', null, 'UN');
INSERT INTO `materiales` VALUES ('923', 'T 31300075', 'MODULO CONTIN. AMARILLO P/BLOQ.TERM 100P', null, 'UN');
INSERT INTO `materiales` VALUES ('924', 'T 31300076', 'MODULO PUESTA A TIERRA /BLOQ.TERM.100P', null, 'UN');
INSERT INTO `materiales` VALUES ('925', 'T 31300077', 'MODULO PROTECCION ELECTRONICA COLOR GRIS', null, 'UN');
INSERT INTO `materiales` VALUES ('926', 'T 31300078', 'MODULO PROTECCION ELECTRONICA,  NARANJA', null, 'UN');
INSERT INTO `materiales` VALUES ('927', 'T 31300080', 'ARMARIO EDIFICIO150/200 PARES', null, 'UN');
INSERT INTO `materiales` VALUES ('928', 'T 31300083', 'BLOQUE TERMINAL 128 PS C/CORTE', null, 'UN');
INSERT INTO `materiales` VALUES ('929', 'T 31300084', 'BLOQUE TERMINAL ( C-318) 100 PARES', null, 'UN');
INSERT INTO `materiales` VALUES ('930', 'T 31300085', 'BASTIDOR P/DDF TIPO N2 ALTURA 2600 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('931', 'T 31300086', 'BASTIDOR P/DDF TIPO N3 ALTURA 2200 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('932', 'T 31300087', 'REGLETA COAXIL 16 CONECTORES P/DDF', null, 'UN');
INSERT INTO `materiales` VALUES ('933', 'T 31300088', 'REGLETA COAXIL 40 CONECTORES P/DDF', null, 'UN');
INSERT INTO `materiales` VALUES ('934', 'T 31300092', 'BASTIDOR 19 \" P/SIST. SSERT', null, 'UN');
INSERT INTO `materiales` VALUES ('935', 'T 31300094', 'PROTECCION P/MUX MARCA MARCONI', null, 'UN');
INSERT INTO `materiales` VALUES ('936', 'T 31300096', 'CAPUCHON P/PROT. D/ARM D/UNA 1 PUERTA', null, 'UN');
INSERT INTO `materiales` VALUES ('937', 'T 31300097', 'CAPUCHON P/PROT. D/ARM D/2 PUERTAS', null, 'UN');
INSERT INTO `materiales` VALUES ('938', 'T 31300098', 'CIERRE MEC.P/ARM C/COMB. P/REG. BS.AS.', null, 'UN');
INSERT INTO `materiales` VALUES ('939', 'T 31300099', 'LLAVE P/CIERRRE .MEC.ARM.C/COMB  BS.AS.', null, 'UN');
INSERT INTO `materiales` VALUES ('940', 'T 31300115', 'BASTIDOR P/DDF BIFRENTE 2600MM', null, 'UN');
INSERT INTO `materiales` VALUES ('941', 'T 31300116', 'BASTIDOR P/DDF BIFRENTE 2200MM', null, 'UN');
INSERT INTO `materiales` VALUES ('942', 'T 31300117', 'BASTIDOR P/DDF ALCATEL DC-120', null, 'UN');
INSERT INTO `materiales` VALUES ('943', 'T 31300118', 'REGLETA COAXIL 16 CONEC. ALCAT', null, 'UN');
INSERT INTO `materiales` VALUES ('944', 'T 31300152', 'ARMARIO D/CHAPA D/1 PUERTA 700 PARES', null, 'UN');
INSERT INTO `materiales` VALUES ('945', 'T 31300153', 'PEDESTAL ALTO P/ARM. D/CHAPA', null, 'UN');
INSERT INTO `materiales` VALUES ('946', 'T 31300154', 'MODULO PROTEC. ELECTRONICA P/XDSL AZUL', null, 'UN');
INSERT INTO `materiales` VALUES ('947', 'T 31300163', 'ARMARIO D/CHAPA D/2 PUERTA 1400 PARES', null, 'UN');
INSERT INTO `materiales` VALUES ('948', 'T 31300164', 'PEDESTAL BAJO P/ARM. D/CHAPA', null, 'UN');
INSERT INTO `materiales` VALUES ('949', 'T 31300166', 'PLANTILLA DE ANCLAJE P/ARM D/CHAPA', null, 'UN');
INSERT INTO `materiales` VALUES ('950', 'T 31400001', 'ALAMBRE ACERO D=4,8 MMP/RIENDA-SUSPENSOR', null, 'M');
INSERT INTO `materiales` VALUES ('951', 'T 31400002', 'ALAMBRE ACERO D=8,1 MMP/RIENDA-SUSPENSOR', null, 'M');
INSERT INTO `materiales` VALUES ('952', 'T 31400003', 'ALAMBRE ACERO D=9 MMP/RIENDA-SUSPENSOR', null, 'M');
INSERT INTO `materiales` VALUES ('953', 'T 31400004', 'ALAMBRE ACERO D=6 MMP/CRUCE AMERICANO', null, 'M');
INSERT INTO `materiales` VALUES ('954', 'T 31400006', 'ALAMBRE ACERO DEVANDORA D=2.3MM ROL.70M', null, 'RO');
INSERT INTO `materiales` VALUES ('955', 'T 31400007', 'ANCLA D/EXPANSION 150 MMP/FIJAR RIENDA', null, 'UN');
INSERT INTO `materiales` VALUES ('956', 'T 31400008', 'ANCLA D/EXPANSION 175 MMP/FIJAR RIENDA', null, 'UN');
INSERT INTO `materiales` VALUES ('957', 'T 31400009', 'ANCLA D/EXPANSION 200 MMP/FIJAR RIENDA', null, 'UN');
INSERT INTO `materiales` VALUES ('958', 'T 31400010', 'ANCLA D/EXPASION DE 240 MMP/FIJAR RIENDA', null, 'UN');
INSERT INTO `materiales` VALUES ('959', 'T 31400011', 'BARRA P/ANCLA L=2240 MM , D=15.8', null, 'UN');
INSERT INTO `materiales` VALUES ('960', 'T 31400012', 'BARRA P/ANCLA L=2750 MM , D=19.1 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('961', 'T 31400013', 'BARRA P/ANCLA L=3050MM , D=25.4 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('962', 'T 31400015', 'BARRA P/ANCLA L=1500 MM , D=15.8 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('963', 'T 31400016', 'BARRA P/ANCLA L=1800 MM , D=19.1 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('964', 'T 31400019', 'ANCLA PARA ROCA', null, 'UN');
INSERT INTO `materiales` VALUES ('965', 'T 31400020', 'ARANDELA CUADRADA  PLANA 57 MM , D=18 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('966', 'T 31400027', 'BRAZO D/EXTEN. CHICO P/RIEN. S/ SI MISMA', null, 'UN');
INSERT INTO `materiales` VALUES ('967', 'T 31400028', 'BRAZO D/EXTEN. GRANDEP/RIEN. S/ SI MISMA', null, 'UN');
INSERT INTO `materiales` VALUES ('968', 'T 31400029', 'BULON 5/8\"W CABEZA/TUERCA CUADR. L=160MM', null, 'UN');
INSERT INTO `materiales` VALUES ('969', 'T 31400030', 'BULON 5/8\"W CABEZA/TUERCA CUADR. L=180MM', null, 'UN');
INSERT INTO `materiales` VALUES ('970', 'T 31400031', 'BULON 5/8\"W CABEZA/TUERCA CUADR. L=200MM', null, 'UN');
INSERT INTO `materiales` VALUES ('971', 'T 31400032', 'BULON 5/8\"W CABEZA/TUERCA CUADR. L=260MM', null, 'UN');
INSERT INTO `materiales` VALUES ('972', 'T 31400034', 'BULON 5/8\"W CABEZA/TUERCA CUADR. L=350MM', null, 'UN');
INSERT INTO `materiales` VALUES ('973', 'T 31400035', 'BULON 5/8\"W CABEZA/TUERCA CUADR. L=400MM', null, 'UN');
INSERT INTO `materiales` VALUES ('974', 'T 31400036', 'BULON 5/8\"W CABEZA/TUERCA CUADR. L=450MM', null, 'UN');
INSERT INTO `materiales` VALUES ('975', 'T 31400038', 'BULON 5/8\"W CABEZA/TUERCA CUADR. L=550MM', null, 'UN');
INSERT INTO `materiales` VALUES ('976', 'T 31400045', 'ESPARRAGO 5/8\"W L=350MM C/TUERCAS CUADR.', null, 'UN');
INSERT INTO `materiales` VALUES ('977', 'T 31400046', 'ESPARRAGO 5/8\"W L=400MM C/TUERCAS CUADR.', null, 'UN');
INSERT INTO `materiales` VALUES ('978', 'T 31400047', 'ESPARRAGO 5/8\"W L=450MM C/TUERCAS CUADR.', null, 'UN');
INSERT INTO `materiales` VALUES ('979', 'T 31400048', 'ESPARRAGO 5/8\"W L=500MM C/TUERCAS CUADR.', null, 'UN');
INSERT INTO `materiales` VALUES ('980', 'T 31400049', 'BULON DE OJO RECTO 5/8\"WL=180 MM C/TUERC', null, 'UN');
INSERT INTO `materiales` VALUES ('981', 'T 31400050', 'BULON DE OJO RECTO 5/8\"WL=205 MM C/TUERC', null, 'UN');
INSERT INTO `materiales` VALUES ('982', 'T 31400051', 'BULON DE OJO RECTO 5/8\"WL=230 MM C/TUERC', null, 'UN');
INSERT INTO `materiales` VALUES ('983', 'T 31400052', 'BULON DE OJO RECTO 5/8\"WL=255 MM C/TUERC', null, 'UN');
INSERT INTO `materiales` VALUES ('984', 'T 31400053', 'BULON DE OJO RECTO 3/4\"WL=180 MM C/TUERC', null, 'UN');
INSERT INTO `materiales` VALUES ('985', 'T 31400054', 'BULON DE OJO RECTO 3/4\"WL=210 MM C/TUERC', null, 'UN');
INSERT INTO `materiales` VALUES ('986', 'T 31400057', 'BULON DE OJO RECTO 5/8\"W SIN TUERCA', null, 'UN');
INSERT INTO `materiales` VALUES ('987', 'T 31400058', 'BULON OJO CURVO 5/8\"WL=180 MM C/TUERCA', null, 'UN');
INSERT INTO `materiales` VALUES ('988', 'T 31400059', 'BULON OJO CURVO 5/8\"WL=205 MM C/TUERCA', null, 'UN');
INSERT INTO `materiales` VALUES ('989', 'T 31400060', 'BULON OJO CURVO 5/8\"WL=230 MM C/TUERCA', null, 'UN');
INSERT INTO `materiales` VALUES ('990', 'T 31400061', 'BULON OJO CURVO 5/8\"WL=255 MM C/TUERCA', null, 'UN');
INSERT INTO `materiales` VALUES ('991', 'T 31400063', 'BULON OJO CURVO 3/4\"WL=210 MM C/TUERCA', null, 'UN');
INSERT INTO `materiales` VALUES ('992', 'T 31400064', 'BULON OJO CURVO 3/4\"WL=240 MM C/TUERCA', null, 'UN');
INSERT INTO `materiales` VALUES ('993', 'T 31400066', 'BULON OJO CURVO 1\"WL=180 MM C/TUERCA', null, 'UN');
INSERT INTO `materiales` VALUES ('994', 'T 31400067', 'BULON OJO CURVO 1\"WL=210 MM C/TUERCA', null, 'UN');
INSERT INTO `materiales` VALUES ('995', 'T 31400068', 'BULON OJO CURVO 1\"WL=240 MM C/TUERCA', null, 'UN');
INSERT INTO `materiales` VALUES ('996', 'T 31400070', 'BULON OJO RECTO 5/8\"WL=180MM TUER. D/OJO', null, 'UN');
INSERT INTO `materiales` VALUES ('997', 'T 31400071', 'BULON OJO RECTO 5/8\"WL=205MM TUER. D/OJO', null, 'UN');
INSERT INTO `materiales` VALUES ('998', 'T 31400072', 'BULON OJO RECTO 5/8\"WL=230MM TUER. D/OJO', null, 'UN');
INSERT INTO `materiales` VALUES ('999', 'T 31400074', 'BULON OJO RECTO 3/4\"WL=180MM TUER. D/OJO', null, 'UN');
INSERT INTO `materiales` VALUES ('1000', 'T 31400075', 'BULON OJO RECTO 3/4\"WL=210MM TUER. D/OJO', null, 'UN');
INSERT INTO `materiales` VALUES ('1001', 'T 31400076', 'BULON OJO CURVO 5/8\"WL=180MM TUER. D/OJO', null, 'UN');
INSERT INTO `materiales` VALUES ('1002', 'T 31400077', 'BULON OJO CURVO 5/8\"WL=205MM TUER. D/OJO', null, 'UN');
INSERT INTO `materiales` VALUES ('1003', 'T 31400078', 'BULON OJO CURVO 5/8\"WL=230MM TUER. D/OJO', null, 'UN');
INSERT INTO `materiales` VALUES ('1004', 'T 31400080', 'BULON OJO CURVO 3/4\"WL=210MM TUER. D/OJO', null, 'UN');
INSERT INTO `materiales` VALUES ('1005', 'T 31400081', 'BULON OJO CURVO 3/4\"WL=240MM TUER. D/OJO', null, 'UN');
INSERT INTO `materiales` VALUES ('1006', 'T 31400082', 'BULON OJO CURVO 3/4\"WL=270MM TUER. D/OJO', null, 'UN');
INSERT INTO `materiales` VALUES ('1007', 'T 31400083', 'BULON OJO CURVO 1\"WL=240 MM TUER. D/OJO', null, 'UN');
INSERT INTO `materiales` VALUES ('1008', 'T 31400084', 'BULON OJO CURVO 1\"WL=270 MM TUER. D/OJO', null, 'UN');
INSERT INTO `materiales` VALUES ('1009', 'T 31400085', 'BULON 3/8\"W CABEZA/TUERCA CUADR. L=40MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1010', 'T 31400086', 'BULON 1/4\"Wx20MM, TUERCA HEXA.Y ARN. AL', null, 'KI');
INSERT INTO `materiales` VALUES ('1011', 'T 31400087', 'CONEC. TIPO NUEZ D/FUND.P/EMPALMAR SUSP.', null, 'UN');
INSERT INTO `materiales` VALUES ('1012', 'T 31400092', 'ESTRIBO P/POSTES FORMA \"L\"  254x22 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1013', 'T 31400093', 'ESTRIBO P/POSTES FORMA \"L\"  254x60 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1014', 'T 31400094', 'ESTRIBO DE MADERA', null, 'UN');
INSERT INTO `materiales` VALUES ('1015', 'T 31400095', 'GANCHO TIPO J P/INST.CAB. AEREO E/POSTE', null, 'UN');
INSERT INTO `materiales` VALUES ('1016', 'T 31400096', 'TIRA ABARZADERA CINC D/300 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1017', 'T 31400097', 'TIRA ABRAZADERA CINC D/520 MM.', null, 'UN');
INSERT INTO `materiales` VALUES ('1018', 'T 31400098', 'TIRA ABRAZADERA CINC D/650 MM.', null, 'UN');
INSERT INTO `materiales` VALUES ('1019', 'T 31400099', 'TIRA ABRAZADERA CINC D/700 MM.', null, 'UN');
INSERT INTO `materiales` VALUES ('1020', 'T 31400102', 'GRAMPA C/2 BUL 95MM P/RET. SUSP. ACE/RIE', null, 'UN');
INSERT INTO `materiales` VALUES ('1021', 'T 31400103', 'GRAMPA C/3 BUL 149MM P/RET. SUSP.ACE/RIE', null, 'UN');
INSERT INTO `materiales` VALUES ('1022', 'T 31400104', 'GRAMPA D/SUSP. 2 BUL/SUJ SUSP.  A POSTE', null, 'UN');
INSERT INTO `materiales` VALUES ('1023', 'T 31400106', 'POSTE DE 7,5 M. GRUESO', null, 'UN');
INSERT INTO `materiales` VALUES ('1024', 'T 31400108', 'POSTE DE 8,5 M. GRUESO', null, 'UN');
INSERT INTO `materiales` VALUES ('1025', 'T 31400109', 'POSTE DE 8,5 M. MEDIANO', null, 'UN');
INSERT INTO `materiales` VALUES ('1026', 'T 31400111', 'POSTE DE 10 M, GRUESO', null, 'UN');
INSERT INTO `materiales` VALUES ('1027', 'T 31400112', 'POSTE DE 10 M, MEDIANO', null, 'UN');
INSERT INTO `materiales` VALUES ('1028', 'T 31400113', 'POSTE DE 11 M, GRUESO', null, 'UN');
INSERT INTO `materiales` VALUES ('1029', 'T 31400114', 'POSTE DE 11 M, MEDIANO', null, 'UN');
INSERT INTO `materiales` VALUES ('1030', 'T 31400120', 'POSTE DE 16 M. MEDIANO', null, 'UN');
INSERT INTO `materiales` VALUES ('1031', 'T 31400121', 'CONJ. PREFORMADO P/FO AEREA HASTA 60FO', null, 'KI');
INSERT INTO `materiales` VALUES ('1032', 'T 31400122', 'PROTECCION DOBLE (2 MED.CA?AS)P/SUSPENS.', null, 'PA');
INSERT INTO `materiales` VALUES ('1033', 'T 31400123', 'PROTECCION FORMA\"U\" D/CHAPA 2440x36X32MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1034', 'T 31400124', 'PROTECCION FORMA\"U\" D/CHAPA 2440x59X55MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1035', 'T 31400125', 'PROTECCION 1/2CA?. ANCH:25MM LONG.2500MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1036', 'T 31400126', 'PROTECCION 1/2CA?.PVC A:45MMLONG.2500 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1037', 'T 31400127', 'SOPORTE D/APOYO P/ESCALERA EN POSTE', null, 'UN');
INSERT INTO `materiales` VALUES ('1038', 'T 31400128', 'SOPORTE CADENA', null, 'UN');
INSERT INTO `materiales` VALUES ('1039', 'T 31400129', 'TUERCA DE OJO DE D=16 MM.', null, 'UN');
INSERT INTO `materiales` VALUES ('1040', 'T 31400130', 'TUERCA DE OJO DE D=19 MM.', null, 'UN');
INSERT INTO `materiales` VALUES ('1041', 'T 31400132', 'TUERCA D/OJO  W5/8\" P/FIJ.CAB FO E/POSTE', null, 'UN');
INSERT INTO `materiales` VALUES ('1042', 'T 31400146', 'POSTE DE HORMIGON TRONCOCONICO DE 7,50 M', null, 'UN');
INSERT INTO `materiales` VALUES ('1043', 'T 31400159', 'POSTE DE 7,5 M.', null, 'UN');
INSERT INTO `materiales` VALUES ('1044', 'T 31400168', 'POSTE DE 8,5 M. GRUESO', null, 'UN');
INSERT INTO `materiales` VALUES ('1045', 'T 31400171', 'POSTE DE 8,5 M. MEDIANO', null, 'UN');
INSERT INTO `materiales` VALUES ('1046', 'T 31400172', 'POSTE DE 10 M. GRUESO', null, 'UN');
INSERT INTO `materiales` VALUES ('1047', 'T 31400173', 'POSTE DE 10 M. MEDIANO', null, 'UN');
INSERT INTO `materiales` VALUES ('1048', 'T 31400174', 'POSTE DE 11 M. GRUESO', null, 'UN');
INSERT INTO `materiales` VALUES ('1049', 'T 31400175', 'POSTE DE 11 M. MEDIANO', null, 'UN');
INSERT INTO `materiales` VALUES ('1050', 'T 31400176', 'POSTE DE 13 M. GRUESO', null, 'UN');
INSERT INTO `materiales` VALUES ('1051', 'T 31400177', 'POSTE DE 13 M. MEDIANO', null, 'UN');
INSERT INTO `materiales` VALUES ('1052', 'T 31400178', 'POSTE DE 16 M.', null, 'UN');
INSERT INTO `materiales` VALUES ('1053', 'T 31400186', 'SOPORTE P/FIJACION DE LA PERTIGA', null, 'UN');
INSERT INTO `materiales` VALUES ('1054', 'T 31400196', 'POSTE DE HORMIGON CUADRADO DE 7,50 M', null, 'UN');
INSERT INTO `materiales` VALUES ('1055', 'T 31400216', 'POSTE DE HORMIGON DE LINEA DE 8,5 M R400', null, 'UN');
INSERT INTO `materiales` VALUES ('1056', 'T 31400217', 'POSTE DE HORMIGON DE LINEA DE 10 M R500', null, 'UN');
INSERT INTO `materiales` VALUES ('1057', 'T 31400218', 'POSTE DE HORMIGON DE LINEA DE 11 M', null, 'UN');
INSERT INTO `materiales` VALUES ('1058', 'T 31400221', 'POSTE DE EUCALIPTO PRESERVADO 9m GRUESO', null, 'UN');
INSERT INTO `materiales` VALUES ('1059', 'T 31400222', 'POSTE DE EUCALIPTO PRESERVADO 9m MEDIANO', null, 'UN');
INSERT INTO `materiales` VALUES ('1060', 'T 31400223', 'ABRAZADER PARA POSTE 160mm', null, 'UN');
INSERT INTO `materiales` VALUES ('1061', 'T 31400224', 'ORDENADOR DE BAJADA CORTO', null, 'UN');
INSERT INTO `materiales` VALUES ('1062', 'T 31400225', 'ORDENADOR DE BAJADAS LARGO', null, 'UN');
INSERT INTO `materiales` VALUES ('1063', 'T 31400226', 'POSTE DE HORMIGON DE LINEA 8,5 M R600', null, 'UN');
INSERT INTO `materiales` VALUES ('1064', 'T 31400227', 'POSTE DE HORMIGON DE LINEA 10 M R650', null, 'UN');
INSERT INTO `materiales` VALUES ('1065', 'T 31400236', 'ESTRIBO PARA POSTE DE HORMIGON', null, 'UN');
INSERT INTO `materiales` VALUES ('1066', 'T 31400246', 'POSTE DE PRFV 7,5m / R300', null, 'UN');
INSERT INTO `materiales` VALUES ('1067', 'T 31400247', 'POSTE DE PRFV 9m / R400 MEDIANO', null, 'UN');
INSERT INTO `materiales` VALUES ('1068', 'T 31400248', 'POSTE DE PRFV 9m / R600 GRUESO', null, 'UN');
INSERT INTO `materiales` VALUES ('1069', 'T 31400249', 'POSTE DE PRFV 10m / R500 MEDIANO', null, 'UN');
INSERT INTO `materiales` VALUES ('1070', 'T 31400276', 'ABRAZADERA PARA POSTES 210mm', null, 'UN');
INSERT INTO `materiales` VALUES ('1071', 'T 31400286', 'ESTRIBO PARA ESCALAR POSTES DE PRFV', null, 'UN');
INSERT INTO `materiales` VALUES ('1072', 'T 31600000', 'ORU 2,5 W C/OMT Y LNA 160K SA', null, 'UN');
INSERT INTO `materiales` VALUES ('1073', 'T 31600002', 'CHASSIS 8 CU SA 7800', null, 'UN');
INSERT INTO `materiales` VALUES ('1074', 'T 31600003', 'UNIDAD CANAL C/FAX RELAY CU', null, 'UN');
INSERT INTO `materiales` VALUES ('1075', 'T 31600004', 'CANCELADOR ECO E1 64ms TELLABS', null, 'UN');
INSERT INTO `materiales` VALUES ('1076', 'T 31600005', 'CONVERTIDOR POTENCIA TELLABS', null, 'UN');
INSERT INTO `materiales` VALUES ('1077', 'T 31600006', 'SUBASTIDOR 19\" TELLABS 258J,', null, 'UN');
INSERT INTO `materiales` VALUES ('1078', 'T 31600007', 'CONVERTIDOR POTENCIA TELLABS', null, 'UN');
INSERT INTO `materiales` VALUES ('1079', 'T 31600008', 'SUBBASTIDOR 19\" TELLABS 8 POSI', null, 'UN');
INSERT INTO `materiales` VALUES ('1080', 'T 31600009', 'ESTACION DAMA SA 8000, 2CU,', null, 'UN');
INSERT INTO `materiales` VALUES ('1081', 'T 31600010', 'ESTACION SCPC SUB E1 PARA SER-', null, 'UN');
INSERT INTO `materiales` VALUES ('1082', 'T 31600011', 'CHASSIS FI AC SA 8000', null, 'UN');
INSERT INTO `materiales` VALUES ('1083', 'T 31600012', 'PLACA CU VFD SA 8000', null, 'UN');
INSERT INTO `materiales` VALUES ('1084', 'T 31600014', 'TRANSCEPTOR CODAN 16 W BANDA KU', null, 'UN');
INSERT INTO `materiales` VALUES ('1085', 'T 31600015', 'MODEM CDM 600, G703, D&I', null, 'UN');
INSERT INTO `materiales` VALUES ('1086', 'T 31600016', 'PLACA TRASLADORA DE PULSOS D/TASACION', null, 'UN');
INSERT INTO `materiales` VALUES ('1087', 'T 31600017', 'R.E.MONOCANAL\"KOMBI\" MOD.MONOKOM UP', null, 'UN');
INSERT INTO `materiales` VALUES ('1088', 'T 31600018', 'FUENTE ALIMENTACION 220/13.2', null, 'UN');
INSERT INTO `materiales` VALUES ('1089', 'T 31600019', 'CONVERTIDOR 48/13.2 PARA MONO-', null, 'UN');
INSERT INTO `materiales` VALUES ('1090', 'T 31600020', 'RE MNC ACS TMC-25 250 MHZ(2H-SIN SIGILO)', null, 'UN');
INSERT INTO `materiales` VALUES ('1091', 'T 31600021', 'RE MNC ACS TMC-35 350 MHZ(2H-SIN SIGILO)', null, 'UN');
INSERT INTO `materiales` VALUES ('1092', 'T 31600022', 'RE MNC ACS TMC-45 450 MHZ(2H-SIN SIGILO)', null, 'UN');
INSERT INTO `materiales` VALUES ('1093', 'T 31600023', 'INTERFAZ TELEF.A 6 HILOS P/RE MNC \"ACS\"', null, 'UN');
INSERT INTO `materiales` VALUES ('1094', 'T 31600024', 'MODULO DE SIGILO P/ RE MNC \"ACS\"', null, 'UN');
INSERT INTO `materiales` VALUES ('1095', 'T 31600025', 'MANUAL TEC. RE MONOCANAL ACS', null, 'UN');
INSERT INTO `materiales` VALUES ('1096', 'T 31600026', 'TRANSCEPTOR TERMINAL RE ALCA-', null, 'UN');
INSERT INTO `materiales` VALUES ('1097', 'T 31600027', 'TRANSCEPTOR TERMINAL RE ALCA-', null, 'UN');
INSERT INTO `materiales` VALUES ('1098', 'T 31600028', 'TRANSCEPTOR TERMINAL RE ALCA-', null, 'UN');
INSERT INTO `materiales` VALUES ('1099', 'T 31600029', 'TRANSCEPTOR TERMINAL RE ALCA-', null, 'UN');
INSERT INTO `materiales` VALUES ('1100', 'T 31600030', 'TRANSCEPTOR TERMINAL RE ALCA-', null, 'UN');
INSERT INTO `materiales` VALUES ('1101', 'T 31600031', 'TRANSCEPTOR TERMINAL RE ALCA-', null, 'UN');
INSERT INTO `materiales` VALUES ('1102', 'T 31600032', 'TRANSCEPTOR REPETIDOR RE ALCA-', null, 'UN');
INSERT INTO `materiales` VALUES ('1103', 'T 31600033', 'TRANSCEPTOR REPETIDOR RE ALCA-', null, 'UN');
INSERT INTO `materiales` VALUES ('1104', 'T 31600034', 'TRANSCEPTOR REPETIDOR RE ALCA-', null, 'UN');
INSERT INTO `materiales` VALUES ('1105', 'T 31600035', 'TRANSCEPTOR REPETIDOR RE ALCA-', null, 'UN');
INSERT INTO `materiales` VALUES ('1106', 'T 31600036', 'TRANSCEPTOR REPETIDOR RE ALCA-', null, 'UN');
INSERT INTO `materiales` VALUES ('1107', 'T 31600037', 'TRANSCEPTOR REPETIDOR RE ALCA-', null, 'UN');
INSERT INTO `materiales` VALUES ('1108', 'T 31600038', 'TRANSCEPTOR REPETIDOR RE ALCA-', null, 'UN');
INSERT INTO `materiales` VALUES ('1109', 'T 31600039', 'TRANSCEPTOR REPETIDOR RE ALCA-', null, 'UN');
INSERT INTO `materiales` VALUES ('1110', 'T 31600040', 'BASTIDOR CONMUTACION 1+1 RE ALCATEL 9467', null, 'UN');
INSERT INTO `materiales` VALUES ('1111', 'T 31600041', 'BASTIDOR SERVICIOS TERM. RE ALCATEL 9467', null, 'UN');
INSERT INTO `materiales` VALUES ('1112', 'T 31600042', 'BASTIDOR INTERC.TERMINAL RE ALCATEL 9467', null, 'UN');
INSERT INTO `materiales` VALUES ('1113', 'T 31600043', 'RADIOENLACE SIEMENS L SRA 4X2,', null, 'UN');
INSERT INTO `materiales` VALUES ('1114', 'T 31600044', 'RADIOENLACE SIEMENS L SRA 4X2,', null, 'UN');
INSERT INTO `materiales` VALUES ('1115', 'T 31600045', 'RADIOENLACE SIEMENS L SRA 4X2,', null, 'UN');
INSERT INTO `materiales` VALUES ('1116', 'T 31600046', 'RADIOENLACE SIEMENS SRAL 4X2,', null, 'UN');
INSERT INTO `materiales` VALUES ('1117', 'T 31600047', 'RADIOENLACE SIEMENS SRAL 4X2,', null, 'UN');
INSERT INTO `materiales` VALUES ('1118', 'T 31600048', 'RADIOENLACE SIEMENS SRAL 4X2,', null, 'UN');
INSERT INTO `materiales` VALUES ('1119', 'T 31600049', 'RADIOENLACE SIEMENS SRAL 4X2,', null, 'UN');
INSERT INTO `materiales` VALUES ('1120', 'T 31600050', 'RADIOENLACE SIEMENS SRAL 4X2,', null, 'UN');
INSERT INTO `materiales` VALUES ('1121', 'T 31600051', 'RADIOENLACE SIEMENS SRAL 4X2,', null, 'UN');
INSERT INTO `materiales` VALUES ('1122', 'T 31600052', 'RADIOENLACE SIEMENS SRAL 4X2,', null, 'UN');
INSERT INTO `materiales` VALUES ('1123', 'T 31600053', 'RADIOENLACE SIEMENS SRAL 4X2,', null, 'UN');
INSERT INTO `materiales` VALUES ('1124', 'T 31600054', 'RADIOENLACE SIEMENS SRAL 4X2,', null, 'UN');
INSERT INTO `materiales` VALUES ('1125', 'T 31600055', 'RADIOENLACE SIEMENS SRAL 4X2,', null, 'UN');
INSERT INTO `materiales` VALUES ('1126', 'T 31600056', 'RADIOENLACE SIEMENS SRAL 4X2,', null, 'UN');
INSERT INTO `materiales` VALUES ('1127', 'T 31600057', 'RADIOENLACE SIEMENS SRAL 4X2,', null, 'UN');
INSERT INTO `materiales` VALUES ('1128', 'T 31600058', 'RADIOENLACE SIEMENS SRAL 4X2,', null, 'UN');
INSERT INTO `materiales` VALUES ('1129', 'T 31600059', 'RADIOENLACE SIEMENS SRAL 4X2,', null, 'UN');
INSERT INTO `materiales` VALUES ('1130', 'T 31600060', 'RADIOENLACE SIEMENS SRAL 4X2,', null, 'UN');
INSERT INTO `materiales` VALUES ('1131', 'T 31600061', 'RADIOENLACE SIEMENS SRA L 4X2', null, 'UN');
INSERT INTO `materiales` VALUES ('1132', 'T 31600062', 'RADIOENLACE SIEMENS L SRA 4X2,', null, 'UN');
INSERT INTO `materiales` VALUES ('1133', 'T 31600063', 'RADIOENLACE SIEMENS SRA L 4X2', null, 'UN');
INSERT INTO `materiales` VALUES ('1134', 'T 31600064', 'RADIOENLACE SIEMENS SRA L 4X2', null, 'UN');
INSERT INTO `materiales` VALUES ('1135', 'T 31600065', 'RADIOENLACE SIEMENS SRA L 16X2', null, 'UN');
INSERT INTO `materiales` VALUES ('1136', 'T 31600066', 'RADIOENLACE SIEMENS SRA L 16X2', null, 'UN');
INSERT INTO `materiales` VALUES ('1137', 'T 31600067', 'RADIOENLACE SIEMENS SRA L 16X2', null, 'UN');
INSERT INTO `materiales` VALUES ('1138', 'T 31600068', 'RADIOENLACE SIEMENS SRA L 16X2', null, 'UN');
INSERT INTO `materiales` VALUES ('1139', 'T 31600069', 'RADIOENLACE SIEMENS SRA L 16X2', null, 'UN');
INSERT INTO `materiales` VALUES ('1140', 'T 31600070', 'RADIOENLACE SIEMENS SRA L 16X2', null, 'UN');
INSERT INTO `materiales` VALUES ('1141', 'T 31600071', 'RADIOENLACE SIEMENS SRA L 16X2', null, 'UN');
INSERT INTO `materiales` VALUES ('1142', 'T 31600072', 'RE SIE SRT1F HP 1+1DF 6U TX/RX=1-3', null, 'UN');
INSERT INTO `materiales` VALUES ('1143', 'T 31600073', 'RE SIE SRT1F HP 1+1DF 6U TX/RX=3-5', null, 'UN');
INSERT INTO `materiales` VALUES ('1144', 'T 31600074', 'RE SIE SRT1F HP 1+1DF 6U TX/RX=5-7', null, 'UN');
INSERT INTO `materiales` VALUES ('1145', 'T 31600075', 'RE SIE SRT1F HP 1+1DF 6U TX/RX=2-4', null, 'UN');
INSERT INTO `materiales` VALUES ('1146', 'T 31600076', 'RE SIE SRT1F HP 1+1DF 6U TX/RX=4-6', null, 'UN');
INSERT INTO `materiales` VALUES ('1147', 'T 31600077', 'RE SIE SRT1F HP 1+1DF 6U TX/RX=6-8', null, 'UN');
INSERT INTO `materiales` VALUES ('1148', 'T 31600078', 'RE SIE SRT1F HP 1+1DFE 6U TX/RX=1-3', null, 'UN');
INSERT INTO `materiales` VALUES ('1149', 'T 31600079', 'RE SIE SRT1F HP 1+1DFE 6U TX/RX=3-5', null, 'UN');
INSERT INTO `materiales` VALUES ('1150', 'T 31600080', 'RE SIE SRT1F HP 1+1DFE 6U TX/RX=5-7', null, 'UN');
INSERT INTO `materiales` VALUES ('1151', 'T 31600081', 'RE SIE SRT1F HP 1+1DFE 6U TX/RX=2-4', null, 'UN');
INSERT INTO `materiales` VALUES ('1152', 'T 31600082', 'RE SIE SRT1F HP 1+1DFE 6U TX/RX=4-6', null, 'UN');
INSERT INTO `materiales` VALUES ('1153', 'T 31600083', 'RE SIE SRT1F HP 1+1DFE 6U TX/RX=6-8', null, 'UN');
INSERT INTO `materiales` VALUES ('1154', 'T 31600084', 'RE SIE SRT1F HP 2+1DF 6U TX/RX=1-3-5', null, 'UN');
INSERT INTO `materiales` VALUES ('1155', 'T 31600085', 'RE SIE SRT1F HP 2+1DF 6U TX/RX=3-5-7', null, 'UN');
INSERT INTO `materiales` VALUES ('1156', 'T 31600086', 'RE SIE SRT1F HP 2+1DF 6U TX/RX=2-4-6', null, 'UN');
INSERT INTO `materiales` VALUES ('1157', 'T 31600087', 'RE SIE SRT1F HP 2+1DF 6U TX/RX=4-6-8', null, 'UN');
INSERT INTO `materiales` VALUES ('1158', 'T 31600088', 'RE SIE SRT1F HP 2+1DFE 6U TX/RX=1-3-5', null, 'UN');
INSERT INTO `materiales` VALUES ('1159', 'T 31600089', 'RE SIE SRT1F HP 2+1DFE 6U TX/RX=3-5-7', null, 'UN');
INSERT INTO `materiales` VALUES ('1160', 'T 31600090', 'RE SIE SRT1F HP 2+1DFE 6U TX/RX=2-4-6', null, 'UN');
INSERT INTO `materiales` VALUES ('1161', 'T 31600091', 'RE SIE SRT1F HP 2+1DFE 6U TX/RX=4-6-8', null, 'UN');
INSERT INTO `materiales` VALUES ('1162', 'T 31600092', 'RE SIE SRT1F HP 1+1DF 7U TX/RX=1-3', null, 'UN');
INSERT INTO `materiales` VALUES ('1163', 'T 31600093', 'RE SIE SRT1F HP 1+1DF 7U TX/RX=3-5', null, 'UN');
INSERT INTO `materiales` VALUES ('1164', 'T 31600094', 'RE SIE SRT1F HP 1+1DF 7U TX/RX=2-4', null, 'UN');
INSERT INTO `materiales` VALUES ('1165', 'T 31600095', 'RE SIE SRT1F HP 1+1DFE 7U TX/RX=1-3', null, 'UN');
INSERT INTO `materiales` VALUES ('1166', 'T 31600096', 'RE SIE SRT1F HP 1+1DFE 7U TX/RX=3-5', null, 'UN');
INSERT INTO `materiales` VALUES ('1167', 'T 31600097', 'RE SIE SRT1F HP 1+1DFE 7U TX/RX=2-4', null, 'UN');
INSERT INTO `materiales` VALUES ('1168', 'T 31600098', 'RE SIE SRT1F HP 1+1DF 8L TX/RX=1-3', null, 'UN');
INSERT INTO `materiales` VALUES ('1169', 'T 31600099', 'RE SIE SRT1F HP 1+1DF 8L TX/RX=3-5', null, 'UN');
INSERT INTO `materiales` VALUES ('1170', 'T 31600100', 'RE SIE SRT1F HP 1+1DF 8L TX/RX=5-7', null, 'UN');
INSERT INTO `materiales` VALUES ('1171', 'T 31600101', 'RE SIE SRT1F HP 1+1DF 8L TX/RX=2-4', null, 'UN');
INSERT INTO `materiales` VALUES ('1172', 'T 31600102', 'RE SIE SRT1F HP 1+1DF 8L TX/RX=4-6', null, 'UN');
INSERT INTO `materiales` VALUES ('1173', 'T 31600103', 'RE SIE SRT1F HP 1+1DF 8L TX/RX=6-8', null, 'UN');
INSERT INTO `materiales` VALUES ('1174', 'T 31600104', 'RE SIE SRT1F HP 1+1DFE 8L TX/RX=1-3', null, 'UN');
INSERT INTO `materiales` VALUES ('1175', 'T 31600105', 'RE SIE SRT1F HP 1+1DFE 8L TX/RX=3-5', null, 'UN');
INSERT INTO `materiales` VALUES ('1176', 'T 31600106', 'RE SIE SRT1F HP 1+1DFE 8L TX/RX=5-7', null, 'UN');
INSERT INTO `materiales` VALUES ('1177', 'T 31600107', 'RE SIE SRT1F HP 1+1DFE 8L TX/RX=2-4', null, 'UN');
INSERT INTO `materiales` VALUES ('1178', 'T 31600108', 'RE SIE SRT1F HP 1+1DFE 8L TX/RX=4-6', null, 'UN');
INSERT INTO `materiales` VALUES ('1179', 'T 31600109', 'RE SIE SRT1F HP 1+1DFE 8L TX/RX=6-8', null, 'UN');
INSERT INTO `materiales` VALUES ('1180', 'T 31600110', 'R.E. 4x2 Mb/s 1+0, 7/8 GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1181', 'T 31600111', 'R.E.4x2 Mb/s 1+0, 7/8 GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1182', 'T 31600112', 'R.E.4x2 Mb/s 1+1, HSB 7/8 GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1183', 'T 31600113', 'R.E.4x2 Mb/s 1+1 HSB 7/8 GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1184', 'T 31600114', 'R.E.4x2 Mb/s 1+0, 15 GHZ 9415', null, 'UN');
INSERT INTO `materiales` VALUES ('1185', 'T 31600115', 'R.E.4x2 Mb/s 1+0, 15 GHZ 9415', null, 'UN');
INSERT INTO `materiales` VALUES ('1186', 'T 31600116', 'R.E.4x2 Mb/s 1+1, HSB 15 GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1187', 'T 31600117', 'R.E.4x2 Mb/s 1+1 HSB 15 GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1188', 'T 31600118', 'R.E.4x2 Mb/s 1+0, 23 GHZ 9423', null, 'UN');
INSERT INTO `materiales` VALUES ('1189', 'T 31600119', 'R.E.4x2 Mb/s 1+0, 23 GHZ 9423', null, 'UN');
INSERT INTO `materiales` VALUES ('1190', 'T 31600120', 'R.E.4x2 Mb/s 1+1, HSB 23 GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1191', 'T 31600121', 'R.E.4x2 Mb/s 1+1 HSB 23 GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1192', 'T 31600122', 'R.E.16x2 Mb/s 1+0, 7/8 GHZ 947', null, 'UN');
INSERT INTO `materiales` VALUES ('1193', 'T 31600123', 'R.E.16x2 Mb/s 1+0, 7/8 GHZ 947', null, 'UN');
INSERT INTO `materiales` VALUES ('1194', 'T 31600124', 'R.E.16x2 Mb/s 1+1, HSB 7/8 GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1195', 'T 31600125', 'R.E.16x2 Mb/s 1+1 HSB 7/8 GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1196', 'T 31600126', 'R.E.16x2 Mb/s 1+0, 15 GHZ 9415', null, 'UN');
INSERT INTO `materiales` VALUES ('1197', 'T 31600127', 'R.E.16x2 Mb/s 1+0, 15 GHZ 9415', null, 'UN');
INSERT INTO `materiales` VALUES ('1198', 'T 31600128', 'R.E.16x2 Mb/s 1+1, HSB 15 GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1199', 'T 31600129', 'R.E.16x2 Mb/s 1+1 HSB 15 GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1200', 'T 31600130', 'R.E.16x2 Mb/s 1+0, 23 GHZ 9423', null, 'UN');
INSERT INTO `materiales` VALUES ('1201', 'T 31600131', 'R.E.16x2 Mb/s 1+0, 23 GHZ 9423', null, 'UN');
INSERT INTO `materiales` VALUES ('1202', 'T 31600132', 'R.E.16x2 Mb/s 1+1, HSB 23 GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1203', 'T 31600133', 'R.E.16x2 Mb/s 1+1 HSB 23 GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1204', 'T 31600134', 'R.E.16X2 MB/S 1+1 DF 7/8 GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1205', 'T 31600135', 'R.E.16X2 MB/S 1+1,DF 15 GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1206', 'T 31600136', 'R.E.16X2 MB/S 1+1,DF 23 GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1207', 'T 31600137', 'R.E.16X2 MB/S 1+1,DFyE HIB', null, 'UN');
INSERT INTO `materiales` VALUES ('1208', 'T 31600138', 'R.E.4X2 MB/S 1+0,', null, 'UN');
INSERT INTO `materiales` VALUES ('1209', 'T 31600139', 'R.E.4X2 MB/S 1+0, 7/8GHZ 9470', null, 'UN');
INSERT INTO `materiales` VALUES ('1210', 'T 31600140', 'R.E.4X2 MB/S 1+1,HSB 7/8GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1211', 'T 31600141', 'R.E.4X2 MB/S 1+1 HSB 7/8 GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1212', 'T 31600142', 'R.E.4X2 MB/S 1+1 HSB/DE 7/8GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1213', 'T 31600143', 'R.E.4X2 MB/S 1+1 HSB/DE 7/8GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1214', 'T 31600144', 'R.E.4X2 MB/S 1+1 DF 7/8GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1215', 'T 31600145', 'R.E.4X2 MB/S 1+1 DF 7/8GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1216', 'T 31600146', 'R.E.4X2 MB/S 1+1 DF 7/8GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1217', 'T 31600147', 'R.E.4X2 MB/S 1+1 DF 7/8GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1218', 'T 31600148', 'R.E.4X2 MB/S 1+1 DF 15GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1219', 'T 31600149', 'R.E.4X2 MB/S 1+1 DF 15GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1220', 'T 31600150', 'R.E.4X2 MB/S 1+1 DF 23GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1221', 'T 31600151', 'R.E.4X2 MB/S 1+1 DF 23GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1222', 'T 31600152', 'R.E.4X2 MB/S 1+1 DFYE HIB7/8', null, 'UN');
INSERT INTO `materiales` VALUES ('1223', 'T 31600153', 'R.E.4X2 MB/S 1+1 DFYE HIB7/8', null, 'UN');
INSERT INTO `materiales` VALUES ('1224', 'T 31600154', 'R.E.4X2 MB/S 1+1 DFYE HIB 7/8', null, 'UN');
INSERT INTO `materiales` VALUES ('1225', 'T 31600155', 'R.E.4X2 MB/S 1+1 DFYE HIB 7/8', null, 'UN');
INSERT INTO `materiales` VALUES ('1226', 'T 31600156', 'R.E.16X2 MB/S 1+0, 7/8GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1227', 'T 31600157', 'R.E.16X2 MB/S 1+1,HSB 7/8GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1228', 'T 31600158', 'R.E.16X2 MB/S 1+1,HSB/DE 7/8', null, 'UN');
INSERT INTO `materiales` VALUES ('1229', 'T 31600159', 'R.E.16X2 MB/S 1+1,DF 7/8 GHZ', null, 'UN');
INSERT INTO `materiales` VALUES ('1230', 'T 31600160', 'R.E.16X2 MB/S 1+1,DFYE HIB 7/8', null, 'UN');
INSERT INTO `materiales` VALUES ('1231', 'T 31600161', 'RADIOENLACE ALCATEL 9415UX', null, 'UN');
INSERT INTO `materiales` VALUES ('1232', 'T 31600162', 'RADIOENLACE ALCATEL 9423UX', null, 'UN');
INSERT INTO `materiales` VALUES ('1233', 'T 31600163', 'RE SPREAD GLENAYRE 31850, 4x2', null, 'UN');
INSERT INTO `materiales` VALUES ('1234', 'T 31600164', 'RE SPREAD GLENAYRE 31950, Nx64', null, 'UN');
INSERT INTO `materiales` VALUES ('1235', 'T 31600165', 'RE SPREAD GLENAYRE 31950, Nx64', null, 'UN');
INSERT INTO `materiales` VALUES ('1236', 'T 31600166', 'RE SPREAD GLENAYRE 31950, Nx64', null, 'UN');
INSERT INTO `materiales` VALUES ('1237', 'T 31600167', 'RE SPREAD GLENAYRE 31400, 2 Mb', null, 'UN');
INSERT INTO `materiales` VALUES ('1238', 'T 31600168', 'RE SPREAD GLENAYRE 31400, 2 Mb', null, 'UN');
INSERT INTO `materiales` VALUES ('1239', 'T 31600169', 'RE SPREAD GLENAYRE 31400, 2 Mb', null, 'UN');
INSERT INTO `materiales` VALUES ('1240', 'T 31600170', 'RE SPREAD GLENAYRE 31700, 2x2', null, 'UN');
INSERT INTO `materiales` VALUES ('1241', 'T 31600171', 'RE SPREAD GLENAYRE 31700, 2x2', null, 'UN');
INSERT INTO `materiales` VALUES ('1242', 'T 31600172', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1243', 'T 31600173', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1244', 'T 31600174', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1245', 'T 31600175', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1246', 'T 31600176', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1247', 'T 31600177', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1248', 'T 31600178', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1249', 'T 31600179', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1250', 'T 31600180', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1251', 'T 31600181', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1252', 'T 31600182', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1253', 'T 31600183', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1254', 'T 31600184', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1255', 'T 31600185', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1256', 'T 31600186', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1257', 'T 31600187', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1258', 'T 31600188', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1259', 'T 31600189', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1260', 'T 31600190', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1261', 'T 31600191', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1262', 'T 31600192', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1263', 'T 31600193', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1264', 'T 31600194', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1265', 'T 31600195', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1266', 'T 31600196', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1267', 'T 31600197', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1268', 'T 31600198', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1269', 'T 31600199', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1270', 'T 31600200', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1271', 'T 31600201', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1272', 'T 31600202', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1273', 'T 31600203', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1274', 'T 31600204', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1275', 'T 31600205', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1276', 'T 31600206', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1277', 'T 31600207', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1278', 'T 31600208', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1279', 'T 31600209', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1280', 'T 31600210', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1281', 'T 31600211', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1282', 'T 31600212', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1283', 'T 31600213', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1284', 'T 31600214', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1285', 'T 31600215', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1286', 'T 31600216', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1287', 'T 31600217', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1288', 'T 31600218', 'RE STRATEX N. ECLIPSE 4X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1289', 'T 31600219', 'RE STRATEX N ECLIPSE 4X2MB/S 1', null, 'UN');
INSERT INTO `materiales` VALUES ('1290', 'T 31600220', 'RE STRATEX N ECLIPSE 4X2MB/S 1', null, 'UN');
INSERT INTO `materiales` VALUES ('1291', 'T 31600221', 'RE STRATEX N ECLIPSE 4X2MB/S 1', null, 'UN');
INSERT INTO `materiales` VALUES ('1292', 'T 31600222', 'RE STRATEX N ECLIPSE 4X2MB/S 1', null, 'UN');
INSERT INTO `materiales` VALUES ('1293', 'T 31600223', 'RE STRATEX N ECLIPSE 4X2MB/S H', null, 'UN');
INSERT INTO `materiales` VALUES ('1294', 'T 31600224', 'RE STRATEX N ECLIPSE 4X2MB/S H', null, 'UN');
INSERT INTO `materiales` VALUES ('1295', 'T 31600225', 'RE STRATEX N ECLIPSE 4X2MB/S H', null, 'UN');
INSERT INTO `materiales` VALUES ('1296', 'T 31600226', 'RE STRATEX N ECLIPSE 4X2MB/S H', null, 'UN');
INSERT INTO `materiales` VALUES ('1297', 'T 31600227', 'RE STRATEX N. ECLIPSE 16X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1298', 'T 31600228', 'RE STRATEX N. ECLIPSE 16X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1299', 'T 31600229', 'RE STRATEX N. ECLIPSE 16X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1300', 'T 31600230', 'RE STRATEX N. ECLIPSE 16X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1301', 'T 31600231', 'RE STRATEX N. ECLIPSE 16X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1302', 'T 31600232', 'RE STRATEX N. ECLIPSE 16X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1303', 'T 31600233', 'RE STRATEX N. ECLIPSE 16X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1304', 'T 31600234', 'RE STRATEX N. ECLIPSE 16X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1305', 'T 31600235', 'RE STRATEX N. ECLIPSE 16X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1306', 'T 31600236', 'RE STRATEX ECLIPSE 16X2MB/S 1+', null, 'UN');
INSERT INTO `materiales` VALUES ('1307', 'T 31600237', 'RE STRATEX ECLIPSE 16X2MB/S 1+', null, 'UN');
INSERT INTO `materiales` VALUES ('1308', 'T 31600238', 'RE STRATEX ECLIPSE 16X2MB/S HS', null, 'UN');
INSERT INTO `materiales` VALUES ('1309', 'T 31600239', 'RE STRATEX ECLIPSE 16X2MB/S HS', null, 'UN');
INSERT INTO `materiales` VALUES ('1310', 'T 31600240', 'RE STRATEX ECLIPSE 16X2MB/S DF', null, 'UN');
INSERT INTO `materiales` VALUES ('1311', 'T 31600241', 'RE STRATEX ECLIPSE 16X2MB/S DF', null, 'UN');
INSERT INTO `materiales` VALUES ('1312', 'T 31600242', 'RE STRATEX N ECLIPSE 16X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1313', 'T 31600243', 'RE STRATEX N ECLIPSE 16X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1314', 'T 31600244', 'RE STRATEX N ECLIPSE 16X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1315', 'T 31600245', 'RE STRATEX N ECLIPSE 16X2MB/S', null, 'UN');
INSERT INTO `materiales` VALUES ('1316', 'T 31600246', 'RE STRATEX ECLIPSE 16X2MB/S 1+', null, 'UN');
INSERT INTO `materiales` VALUES ('1317', 'T 31600247', 'RE STRATEX ECLIPSE 16X2MB/S 1+', null, 'UN');
INSERT INTO `materiales` VALUES ('1318', 'T 31600248', 'RE STRATEX ECLIPSE 16X2MB/S 1+', null, 'UN');
INSERT INTO `materiales` VALUES ('1319', 'T 31600249', 'RE STRATEX ECLIPSE 16X2MB/S 1+', null, 'UN');
INSERT INTO `materiales` VALUES ('1320', 'T 31600250', 'RE STRATEX ECLIPSE 16X2MB/S HS', null, 'UN');
INSERT INTO `materiales` VALUES ('1321', 'T 31600251', 'RE STRATEX ECLIPSE 16X2MB/S HS', null, 'UN');
INSERT INTO `materiales` VALUES ('1322', 'T 31600252', 'RE STRATEX ECLIPSE 16X2MB/S HS', null, 'UN');
INSERT INTO `materiales` VALUES ('1323', 'T 31600253', 'RE STRATEX ECLIPSE 16X2MB/S HS', null, 'UN');
INSERT INTO `materiales` VALUES ('1324', 'T 32100000', 'TERMINAL WLL 1 CLIENTE MOD.TADIRAN FAU-1', null, 'UN');
INSERT INTO `materiales` VALUES ('1325', 'T 32100001', 'TERMINAL WLL 2 CLIENT. MOD TADIRAN FAU-2', null, 'UN');
INSERT INTO `materiales` VALUES ('1326', 'T 32100003', 'PCU 48 VCC P/TERMINAL WLL MARCA TADIRAN', null, 'UN');
INSERT INTO `materiales` VALUES ('1327', 'T 32100004', 'TRANSF. 220VCA-25VCA P/PCU WLL TADIRAN', null, 'UN');
INSERT INTO `materiales` VALUES ('1328', 'T 32100005', 'TERMINAL WLL 4 CLIENTES +PCU+', null, 'UN');
INSERT INTO `materiales` VALUES ('1329', 'T 32100006', 'TERMINAL WLL 1 CLIEN.S/PCU TADIRAN FAU-1', null, 'UN');
INSERT INTO `materiales` VALUES ('1330', 'T 32100007', 'TERMINAL WLL 2 CLIENTES S/PCU', null, 'UN');
INSERT INTO `materiales` VALUES ('1331', 'T 32100008', 'TERMINAL WLL P/TPA S/PCU', null, 'UN');
INSERT INTO `materiales` VALUES ('1332', 'T 32100009', 'PCU 48 VCC+TRANSF.+BATERIA WLL TADIRAN', null, 'UN');
INSERT INTO `materiales` VALUES ('1333', 'T 32100010', 'TRANSF. 220VCA-16VCA P/WLL TADIRAN', null, 'UN');
INSERT INTO `materiales` VALUES ('1334', 'T 32100011', 'PCU 57 VCC P/TERMINAL WLL TADIRAN', null, 'UN');
INSERT INTO `materiales` VALUES ('1335', 'T 32100012', 'PCU PARA TPA TERMINAL WLL', null, 'UN');
INSERT INTO `materiales` VALUES ('1336', 'T 32100016', 'INTERFAZ ANALOGICA INCLUYE PS', null, 'UN');
INSERT INTO `materiales` VALUES ('1337', 'T 32100019', 'MULTIPLEXOR MCX P/4 RPU (AMBOS', null, 'UN');
INSERT INTO `materiales` VALUES ('1338', 'T 32100020', 'SISTEMA POSICIONADOR GPS', null, 'UN');
INSERT INTO `materiales` VALUES ('1339', 'T 32100023', 'PROCESADOR PRINCIPAL DE RPCU', null, 'UN');
INSERT INTO `materiales` VALUES ('1340', 'T 32100024', 'INTERFAZ P/GESTION MODELO NMI.', null, 'UN');
INSERT INTO `materiales` VALUES ('1341', 'T 32100025', 'INTERFAZ RED DIGITAL P/AIU-120', null, 'UN');
INSERT INTO `materiales` VALUES ('1342', 'T 32100026', 'INTERFAZ DE RPU MOD.RPI BD', null, 'UN');
INSERT INTO `materiales` VALUES ('1343', 'T 32100027', 'GLOBAL TIMING UNIT MODELO GTU', null, 'UN');
INSERT INTO `materiales` VALUES ('1344', 'T 32100028', 'UNIDAD DE PUERTO DE RADIO COM-', null, 'UN');
INSERT INTO `materiales` VALUES ('1345', 'T 32100029', 'TERMIANL WLL S/BATERIA  KRONE TELELINK 2', null, 'UN');
INSERT INTO `materiales` VALUES ('1346', 'T 32100030', 'FUENTE ALIM. P/TERM. WLL KRONE TELELINK2', null, 'UN');
INSERT INTO `materiales` VALUES ('1347', 'T 32100031', 'TERM. WLL S/BAT./FUENTE KRONE TELELINK 3', null, 'UN');
INSERT INTO `materiales` VALUES ('1348', 'T 32100032', 'FUNTE ALIM. P/TERM. WLL KRONE TELELINK 3', null, 'UN');
INSERT INTO `materiales` VALUES ('1349', 'T 32100033', 'TERMINAL WLL 1 CLIENTE MODELO NEC 2W-FT', null, 'UN');
INSERT INTO `materiales` VALUES ('1350', 'T 32100034', 'CAJA D/CONT.P/FUENTE ALIM.Y BAT. P/2W-FT', null, 'UN');
INSERT INTO `materiales` VALUES ('1351', 'T 32100035', 'BATERIA 6 VOLT P/WLL', null, 'UN');
INSERT INTO `materiales` VALUES ('1352', 'T 32100037', 'TERMINAL WLL D/ABON. LICEA 2000, VOZ+DAT', null, 'UN');
INSERT INTO `materiales` VALUES ('1353', 'T 32100038', 'TERMINAL GSMF 900MHZ ERICSSON G32E', null, 'UN');
INSERT INTO `materiales` VALUES ('1354', 'T 32100039', 'TRANSFORMADOR AC/DC P/TER. ERICSSON GSM', null, 'UN');
INSERT INTO `materiales` VALUES ('1355', 'T 32100041', 'TERMINAL GSMF 850MHZ ERICSSON G32A', null, 'UN');
INSERT INTO `materiales` VALUES ('1356', 'T 32100042', 'TARJETA SIM MARCA GYD MOD. CALLISTO 64K', null, 'UN');
INSERT INTO `materiales` VALUES ('1357', 'T 32100052', 'TERMINAL GSMF 850/1900MHZ ERICSSON G36A', null, 'UN');
INSERT INTO `materiales` VALUES ('1358', 'T 32100053', 'TERMINAL GSMF 900MHZ ERICSSON G36E', null, 'UN');
INSERT INTO `materiales` VALUES ('1359', 'T 32100055', 'TERMINAL GSMF 850/1900MHZ HUAWEI ETS3228', null, 'UN');
INSERT INTO `materiales` VALUES ('1360', 'T 32100081', 'TERMINAL GSMF INTEGRADO HUAWEI MOD.3223', null, 'UN');
INSERT INTO `materiales` VALUES ('1361', 'T 32100091', 'TERMINAL GSMF INTEGRADO ZTE MODELO 623H', null, 'UN');
INSERT INTO `materiales` VALUES ('1362', 'T 32100171', 'TARJETA SIM MARCA GEMALTO HZV2', null, 'UN');
INSERT INTO `materiales` VALUES ('1363', 'T 32100172', 'TERMINAL GSMF INTEG.  HUAWEI ETS3125I H', null, 'UN');
INSERT INTO `materiales` VALUES ('1364', 'T 32100174', 'TERMINAL GSMF 850 INTEGRADO ZTE WP658 V2', null, 'UN');
INSERT INTO `materiales` VALUES ('1365', 'T 32100181', 'TARJETA SIM MARCA GEMALTO HZV3', null, 'UN');
INSERT INTO `materiales` VALUES ('1366', 'T 32100191', 'TERMINAL GSMF INTEGRADA MOTOROLA FXP860', null, 'UN');
INSERT INTO `materiales` VALUES ('1367', 'T 32300249', 'EQUIPO REMOTIZADORES D/ALARMAS', null, 'UN');
INSERT INTO `materiales` VALUES ('1368', 'T 32300251', 'LOTE DE MATERIALES INST.P/TER-', null, 'UN');
INSERT INTO `materiales` VALUES ('1369', 'T 32400090', 'VENDA DE GOMA', null, 'RO');
INSERT INTO `materiales` VALUES ('1370', 'T 32400091', 'ADHESIVO EN BARRA P/CABLE DE FACHADA', null, 'BR');
INSERT INTO `materiales` VALUES ('1371', 'T 32400093', 'BROCHE ACE. CIN. D/14X11,2 P/ABROC. 1012', null, 'UN');
INSERT INTO `materiales` VALUES ('1372', 'T 32400094', 'BROCHE RAPID 28/11PARA ABROCHADORA', null, 'UN');
INSERT INTO `materiales` VALUES ('1373', 'T 32400095', 'TARUGO P/MAMPOSTERIA 8X27 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1374', 'T 32400096', 'TARUGO P/MAMPOSTERIA 8X40 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1375', 'T 32400097', 'TARUGO P/MAMPOSTERIA 10X40 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1376', 'T 32400098', 'TARUGO P/MAMP. D:5MM L:25MM T.FISCHER S5', null, 'UN');
INSERT INTO `materiales` VALUES ('1377', 'T 32400099', 'BROCA D/EXPANSION 1/2\"W LARGO 50MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1378', 'T 32400100', 'BROCA DE EXPANSION D:5/16 \"W\"', null, 'UN');
INSERT INTO `materiales` VALUES ('1379', 'T 32400101', 'CAPUCHON S/VALVULA P/CABLE D=12 A 16 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1380', 'T 32400102', 'CAPUCHON S/VALVULA P/CABLE D=16 A 22 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1381', 'T 32400103', 'CAPUCHON S/VALVULA P/CABLE D=22 A 26 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1382', 'T 32400104', 'CAPUCHON S/VALVULA P/CABLE D=26 A 36 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1383', 'T 32400105', 'CAPUCHON S/VALVULA P/CABLE D=36 A 44 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1384', 'T 32400106', 'CAPUCHON S/VALVULA P/CABLE D=44 A 70 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1385', 'T 32400107', 'CAPUCHON C/VALVULA P/CABLE D=12 A 16 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1386', 'T 32400108', 'CAPUCHON C/VALVULA P/CABLE D=16 A 22 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1387', 'T 32400109', 'CAPUCHON C/VALVULA P/CABLE D=22 A 26 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1388', 'T 32400110', 'CAPUCHON C/VALVULA P/CABLE D=26 A 36 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1389', 'T 32400111', 'CAPUCHON C/VALVULA P/CABLE D=36 A 44 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1390', 'T 32400112', 'CAPUCHON C/VALVULA P/CABLE D=44 A 70 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1391', 'T 32400113', 'CINTA AISLADORA D/GOMA NO VULCANIZADA', null, 'RO');
INSERT INTO `materiales` VALUES ('1392', 'T 32400115', 'CINTA AISLADORA PLASTICA COLOR GRIS', null, 'RO');
INSERT INTO `materiales` VALUES ('1393', 'T 32400116', 'CINTA AISLADORA PVC COLOR NEGRA 19MM', null, 'RO');
INSERT INTO `materiales` VALUES ('1394', 'T 32400118', 'CINTA D/PAPEL CREPE P/PROT. FLAMEO EMP.', null, 'KG');
INSERT INTO `materiales` VALUES ('1395', 'T 32400119', 'CINTA AISLADORA PLASTICA COLOR MARFIL', null, 'RO');
INSERT INTO `materiales` VALUES ('1396', 'T 32400120', 'CINTA D/PREVENCION PLASTICA P/INST.SUBT.', null, 'RO');
INSERT INTO `materiales` VALUES ('1397', 'T 32400121', 'CINTA PLASTICA TRANSPARENTEP/EMP. PRES.', null, 'RO');
INSERT INTO `materiales` VALUES ('1398', 'T 32400126', 'CLAVO PUNTA PARIS CINCADO DE 2\"', null, 'KG');
INSERT INTO `materiales` VALUES ('1399', 'T 32400129', 'ETIQUETA  AUTOADHESIVA INSC.FIBRA OPTICA', null, 'KI');
INSERT INTO `materiales` VALUES ('1400', 'T 32400130', 'NUMEROS AUTOADH.(0al9)  P/IDENTIF.CAJ.FO', null, 'KI');
INSERT INTO `materiales` VALUES ('1401', 'T 32400131', 'ETIQUETA AUTOLAMINANTE P/IDENTTIFICACION', null, 'KI');
INSERT INTO `materiales` VALUES ('1402', 'T 32400132', 'ESTOPA VEGETAL', null, 'KG');
INSERT INTO `materiales` VALUES ('1403', 'T 32400135', 'GRAMPA 2 OREJAS D/16 MM P/FIJAR CABLE', null, 'UN');
INSERT INTO `materiales` VALUES ('1404', 'T 32400136', 'GRAMPA 2 OREJAS D/23 MM P/FIJAR CABLE', null, 'UN');
INSERT INTO `materiales` VALUES ('1405', 'T 32400137', 'GRAMPA 2 OREJAS D/25 MM P/FIJAR CABLE', null, 'UN');
INSERT INTO `materiales` VALUES ('1406', 'T 32400138', 'GRAMPA 2 OREJAS D/28 MM P/FIJAR CABLE', null, 'UN');
INSERT INTO `materiales` VALUES ('1407', 'T 32400139', 'GRAMPA 2 OREJAS D/35 MM P/FIJAR CABLE', null, 'UN');
INSERT INTO `materiales` VALUES ('1408', 'T 32400142', 'GRAMPA PLASTICA C/CLAVO P/CABLE DISTRIB.', null, 'UN');
INSERT INTO `materiales` VALUES ('1409', 'T 32400153', 'GRAPAS PARA CERCO L:19MMP/TOMA TIERR', null, 'UN');
INSERT INTO `materiales` VALUES ('1410', 'T 32400156', 'GUARDA RIENDA P/PROTECCION', null, 'UN');
INSERT INTO `materiales` VALUES ('1411', 'T 32400158', 'LIGADURA A COMPRES. RET.FINA P/LINEA AER', null, 'UN');
INSERT INTO `materiales` VALUES ('1412', 'T 32400159', 'PRECINTO PLASTICO L=100MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1413', 'T 32400160', 'PRECINTO PLASTICO L=150MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1414', 'T 32400161', 'PRECINTO PLAST. L=300 MM P/SUJ. D/CABLES', null, 'UN');
INSERT INTO `materiales` VALUES ('1415', 'T 32400162', 'PRECINTO PLASTICO L=120MM C/PROTEC. UV', null, 'UN');
INSERT INTO `materiales` VALUES ('1416', 'T 32400163', 'PRECINTO PLASTICO L=180MM C/PROTEC. UV', null, 'UN');
INSERT INTO `materiales` VALUES ('1417', 'T 32400166', 'MASILLA', null, 'KG');
INSERT INTO `materiales` VALUES ('1418', 'T 32400167', 'TARJETA IDENTIFICACION CAB. FIBRA OPTICA', null, 'KI');
INSERT INTO `materiales` VALUES ('1419', 'T 32400168', 'TIRAFONDO DE D=6,5 MM L=40 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1420', 'T 32400169', 'TIRAFONDO DE D=8 MM L=63 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1421', 'T 32400170', 'TIRAFONDO DE D=8 MM L=80 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1422', 'T 32400171', 'TIRAFONDO DE D=9,5 MM L=50 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1423', 'T 32400174', 'TIRAFONDO D/D=12,7 MM L=110 MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1424', 'T 32400177', 'OVULO D/RETENCION P/VALVULA D/PRESURIZ.', null, 'UN');
INSERT INTO `materiales` VALUES ('1425', 'T 32400178', 'VALVULA DE PRUEBA DE PRESION COMPLETA', null, 'UN');
INSERT INTO `materiales` VALUES ('1426', 'T 32400179', 'CONJUNTO TOMA D/PRES P/CAB D.E. 15A 45MM', null, 'KI');
INSERT INTO `materiales` VALUES ('1427', 'T 32400180', 'CONJUNTO TOMA D/PRES P/CAB D.E. 20A 65MM', null, 'KI');
INSERT INTO `materiales` VALUES ('1428', 'T 32400181', 'CONJUNTO TOMA D/PRES P/CAB D.E. 30A 90MM', null, 'KI');
INSERT INTO `materiales` VALUES ('1429', 'T 32400184', 'TRANSDUCTOR DE PRESION', null, 'UN');
INSERT INTO `materiales` VALUES ('1430', 'T 32400185', 'PRECINTO PLASTICO AMARILLO L=300MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1431', 'T 32400186', 'CONEXION P/INYEC. Y MEDIC. D/PRES.D/AIRE', null, 'UN');
INSERT INTO `materiales` VALUES ('1432', 'T 32400187', 'CONTENEDOR EXT.P/TRANSDUCTOR D/PRESION', null, 'UN');
INSERT INTO `materiales` VALUES ('1433', 'T 32400193', 'CARRETE DE MADERA 1200X900X600', null, 'UN');
INSERT INTO `materiales` VALUES ('1434', 'T 32400194', 'CARRETE DE MADERA 1500x900x800', null, 'UN');
INSERT INTO `materiales` VALUES ('1435', 'T 32400195', 'CARRETE DE MADERA 1900X900X1000', null, 'UN');
INSERT INTO `materiales` VALUES ('1436', 'T 32400196', 'CARRETE DE  MADERA 2200X1100X1200', null, 'UN');
INSERT INTO `materiales` VALUES ('1437', 'T 32400197', 'CARRETE DE MADERA 1000X900X500', null, 'UN');
INSERT INTO `materiales` VALUES ('1438', 'T 32400198', 'TORNILLO CABEZA REDONDA  P/MADERA  23/25', null, 'UN');
INSERT INTO `materiales` VALUES ('1439', 'T 32400199', 'TORNILLO CABEZA REDONDA  P/MADERA  22/25', null, 'UN');
INSERT INTO `materiales` VALUES ('1440', 'T 32400200', 'TORNILLO CABEZA REDONDA  P/MADERA  22/60', null, 'UN');
INSERT INTO `materiales` VALUES ('1441', 'T 32400201', 'TORNILLO CABEZA REDONDA  P/MADERA  23/40', null, 'UN');
INSERT INTO `materiales` VALUES ('1442', 'T 32400202', 'TORNILLO CABEZA FRESADA P/MADERA 19/30', null, 'UN');
INSERT INTO `materiales` VALUES ('1443', 'T 32400203', 'TORNILLO CABEZA FRESADA P/MADERA 25/60', null, 'UN');
INSERT INTO `materiales` VALUES ('1444', 'T 32400206', 'KIT DE PRESURIZACION', null, 'UN');
INSERT INTO `materiales` VALUES ('1445', 'T 32400210', 'CA#O  1\" DIAM. INTER. P/ SOP. D/ANTENA', null, 'M');
INSERT INTO `materiales` VALUES ('1446', 'T 32400211', 'CABLE CANAL (10 X 10)', null, 'UN');
INSERT INTO `materiales` VALUES ('1447', 'T 32400214', 'POSTE LIVIANO DE L=2M Y DIAME-TRO 1,5\"', null, 'UN');
INSERT INTO `materiales` VALUES ('1448', 'T 32400217', 'GABINETE P/TERMINAL DE TELECOM', null, 'UN');
INSERT INTO `materiales` VALUES ('1449', 'T 32400218', 'GABINETE P/PASIVO DE CATV', null, 'UN');
INSERT INTO `materiales` VALUES ('1450', 'T 32400232', 'KIT INST. INT. Y EXT.P/WLL MARCA TADIRAN', null, 'UN');
INSERT INTO `materiales` VALUES ('1451', 'T 32400233', 'KIT GRAMPAS CA?O 1 1/4\" P/WLL TADIRAN', null, 'UN');
INSERT INTO `materiales` VALUES ('1452', 'T 32400235', 'CABLE CANAL (10X10X3)', null, 'M');
INSERT INTO `materiales` VALUES ('1453', 'T 32400237', 'MEDIA CA?A PLASTICA INST.TERM.WLL KRONE', null, 'M');
INSERT INTO `materiales` VALUES ('1454', 'T 32400246', 'ESTRELLA P/SOSTEN DEL CA?O', null, 'UN');
INSERT INTO `materiales` VALUES ('1455', 'T 32400247', 'BULON L=50 MM ROSCA W 16 PARA', null, 'UN');
INSERT INTO `materiales` VALUES ('1456', 'T 32400248', 'SOPORTE TIPO MENSULA 30CM  P/CA?O ANTENA', null, 'UN');
INSERT INTO `materiales` VALUES ('1457', 'T 32400249', 'GRAPA \"U\" 100x55 MM P/FIJACION CA?O ANT.', null, 'UN');
INSERT INTO `materiales` VALUES ('1458', 'T 32400252', 'TENSOR P/RIENDAS DEL MASTIL', null, 'UN');
INSERT INTO `materiales` VALUES ('1459', 'T 32400253', 'PRENSACABLE \"U\" 22 X 11 MM P/', null, 'UN');
INSERT INTO `materiales` VALUES ('1460', 'T 32400255', 'GRILLETE \"U\" 115X20 MM PARA', null, 'UN');
INSERT INTO `materiales` VALUES ('1461', 'T 32400256', 'ANCLAJE DE CEMENTO P/RIENDAS', null, 'UN');
INSERT INTO `materiales` VALUES ('1462', 'T 32400257', 'SUNCHO L=1400 MM P/ANCLAJE DE', null, 'UN');
INSERT INTO `materiales` VALUES ('1463', 'T 32400258', 'BASE DE CEMENTO P/MASTIL DE', null, 'UN');
INSERT INTO `materiales` VALUES ('1464', 'T 32400260', 'PRECINTO LATON CINC.C/PROT.PLAST.100X9MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1465', 'T 32400261', 'PRECINTO LATON CINC.C/PROT.PLAST.300X9MM', null, 'UN');
INSERT INTO `materiales` VALUES ('1466', 'T 32400263', 'PASAMURO 1 BOCA C/ BOTA GO ANDREW EWP52', null, 'UN');
INSERT INTO `materiales` VALUES ('1467', 'T 32400266', 'PASAMURO 1 BOCA C/ BOTA GO RFS E78', null, 'UN');
INSERT INTO `materiales` VALUES ('1468', 'T 32400267', 'PASAMURO 1 BOCA C/ BOTA GO ANDREW EWP63', null, 'UN');
INSERT INTO `materiales` VALUES ('1469', 'T 32400268', 'PASAMURO 1 BOCA C/BOTA P/CAB.1/4\"-RG213', null, 'UN');
INSERT INTO `materiales` VALUES ('1470', 'T 32400269', 'PASAMURO 1 BOCA C/BOTA P/CABLE DE 1/2\"', null, 'UN');
INSERT INTO `materiales` VALUES ('1471', 'T 32400272', 'PASAMURO 1 BOCA C/BOTA P/CABLE DE 1 1/4\"', null, 'UN');
INSERT INTO `materiales` VALUES ('1472', 'T 32400274', 'PASAMURO 1 BOCA C/BOTA P/CABLE RG213', null, 'UN');
INSERT INTO `materiales` VALUES ('1473', 'T 32400275', 'PASAMURO 1 BOCA C/ BOTA GO ANDREW EWP64', null, 'UN');
INSERT INTO `materiales` VALUES ('1474', 'T 32400276', 'PASAMURO 1 BOCA C/ BOTA GO ANDREW EWP77', null, 'UN');
INSERT INTO `materiales` VALUES ('1475', 'T 32400277', 'PLACA PASAMURO 2 BOCAS DE 4\"', null, 'UN');
INSERT INTO `materiales` VALUES ('1476', 'T 32400278', 'PLACA PASAMURO 3 BOCAS DE 4\"', null, 'UN');
INSERT INTO `materiales` VALUES ('1477', 'T 32400279', 'PLACA PASAMURO 4 BOCAS DE 4\" EN 1 FILA', null, 'UN');
INSERT INTO `materiales` VALUES ('1478', 'T 32400280', 'PLACA PASAMURO 4 BOCAS DE 4\" EN 2 FILAS', null, 'UN');
INSERT INTO `materiales` VALUES ('1479', 'T 32400283', 'PASAMURO 1 BOCA C/ BOTA GO ANDREW EWP52', null, 'UN');
INSERT INTO `materiales` VALUES ('1480', 'T 32400285', 'BOTA DE GOMA P/CABLE 1/4\"', null, 'UN');
INSERT INTO `materiales` VALUES ('1481', 'T 32400286', 'BOTA DE GOMA P/CABLE RG213', null, 'UN');
INSERT INTO `materiales` VALUES ('1482', 'T 32400287', 'BOTA DE GOMA P/CABLE 3/8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('1483', 'T 32400288', 'BOTA DE GOMA P/CABLE 1/2\"', null, 'UN');
INSERT INTO `materiales` VALUES ('1484', 'T 32400289', 'BOTA DE GOMA P/CABLE 7/8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('1485', 'T 32400290', 'BOTA DE GOMA P/CABLE 1 1/4\"', null, 'UN');
INSERT INTO `materiales` VALUES ('1486', 'T 32400291', 'BOTA DE GOMA P/CABLE 1 5/8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('1487', 'T 32400292', 'BOTA DE GOMA P/GO ANDREW EWP64', null, 'UN');
INSERT INTO `materiales` VALUES ('1488', 'T 32400293', 'BOTA DE GOMA P/GO RFS E70', null, 'UN');
INSERT INTO `materiales` VALUES ('1489', 'T 32400294', 'BOTA DE GOMA P/GO ANDREW EWP52', null, 'UN');
INSERT INTO `materiales` VALUES ('1490', 'T 32400296', 'BOTA DE GOMA P/GO ANDREW EWP63', null, 'UN');
INSERT INTO `materiales` VALUES ('1491', 'T 32400297', 'BOTA DE GOMA P/GO RFS E65', null, 'UN');
INSERT INTO `materiales` VALUES ('1492', 'T 32400298', 'BOTA DE GOMA P/GUIA DE ONDA ANDREW EWP77', null, 'UN');
INSERT INTO `materiales` VALUES ('1493', 'T 32400300', 'VENTANA PRES. P/GO CON FLANGE CPR137G', null, 'UN');
INSERT INTO `materiales` VALUES ('1494', 'T 32400305', 'KIT PRES. C/MANGUERA 3/8\" 6M CONECT.3/8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('1495', 'T 32400306', 'KIT PRES. MANGUERA 3/8\" 12M CONECT.3/8\"', null, 'UN');
INSERT INTO `materiales` VALUES ('1496', 'T 32400406', 'ADHESIVO LIQUIDO P/ELEMETOS DE PVC', null, 'KG');
INSERT INTO `materiales` VALUES ('1497', 'T 32400408', 'SILICA GEL P/PRESURIZADORES DE GO', null, 'KG');
INSERT INTO `materiales` VALUES ('1498', 'T 32400466', 'PRECINTO PLAST. AMARILLO 90CM P/SE?ALIZ.', null, 'UN');
INSERT INTO `materiales` VALUES ('1499', 'T 32400467', 'PRECINTO PLAST. AZUL 90CM P/SE?ALIZACION', null, 'UN');
INSERT INTO `materiales` VALUES ('1500', 'T 32400761', 'PRECINTO PLASTICO L=600 MM C/PROTEC. UV', null, 'UN');
INSERT INTO `materiales` VALUES ('1501', 'T 32710139', 'eSFP-1310nm-1000Base-Lx SM 1310nm 10km', null, 'UN');
INSERT INTO `materiales` VALUES ('1502', 'T 32710572', '33?C-65?C-90V-290V-53.5V/15A-GERM4815T', null, 'UN');
INSERT INTO `materiales` VALUES ('1503', 'T 32711600', 'MA5616 MultiService Centralized Control', null, 'UN');
INSERT INTO `materiales` VALUES ('1504', 'T 32711601', 'MA5616 EPON/GPON/GE TypeC Uplink', null, 'UN');
INSERT INTO `materiales` VALUES ('1505', 'T 32711603', 'MA5616 H83D01PDIA01-DC Power Input Unit', null, 'UN');
INSERT INTO `materiales` VALUES ('1506', 'T 32712360', 'MA5616 Analog Subscriber DSP Card', null, 'UN');
INSERT INTO `materiales` VALUES ('1507', 'T 32712361', 'MA5616 32port VDSL2&POTS Combo&Splitter', null, 'UN');
INSERT INTO `materiales` VALUES ('1508', 'T 32712362', 'PAIB MA5616 AC Power Board', null, 'UN');
INSERT INTO `materiales` VALUES ('1509', 'T 32712369', 'MA5603T/5600T Connect Power Board', null, 'UN');
INSERT INTO `materiales` VALUES ('1510', 'T 32712380', 'Selector de fase externo', null, 'UN');
INSERT INTO `materiales` VALUES ('1511', 'T 32712381', 'MA5616 F01S50 Cabinet(Support Battery)', null, 'UN');
INSERT INTO `materiales` VALUES ('1512', 'T 32712382', 'MA5616 F01S100 Cabinet(Support Battery)', null, 'UN');
INSERT INTO `materiales` VALUES ('1513', 'T 32712383', 'MA5616 MultiService Access Equip.Host', null, 'UN');
INSERT INTO `materiales` VALUES ('1514', 'T 32712409', 'MA5603T/5600T S300 Cabinet+Interca.Calor', null, 'UN');
INSERT INTO `materiales` VALUES ('1515', 'T 32712411', 'MA5603T/5600T Forwarding Logic Board', null, 'UN');
INSERT INTO `materiales` VALUES ('1516', 'T 32712412', 'MA5603T/5600T D500 Cabinet Integration', null, 'UN');
INSERT INTO `materiales` VALUES ('1517', 'T 32712413', 'MA5603T/5600T Service Shelf,48V/60V,4Fan', null, 'UN');
INSERT INTO `materiales` VALUES ('1518', 'T 32712414', 'Selector de fase externo 2', null, 'UN');
INSERT INTO `materiales` VALUES ('1519', 'T 32712684', 'Gabinete Exterior VDSL2 - Crece en ANCHO', null, 'UN');
INSERT INTO `materiales` VALUES ('1520', 'T 32713581', 'MA5616 DC 4830 H83E1PDU1,3Input 3Output', null, 'UN');
INSERT INTO `materiales` VALUES ('1521', 'T 32713582', 'MA5616 Component Enhance Heat Consumo', null, 'UN');
INSERT INTO `materiales` VALUES ('1522', 'T 32713583', 'MA5603T/5600T Middle Service Shelf', null, 'UN');
INSERT INTO `materiales` VALUES ('1523', 'T 32713584', 'MA5603T/5600T 48PORTS VDSL2&POTS CVME', null, 'UN');
INSERT INTO `materiales` VALUES ('1524', 'T 32713586', 'MA5603T/5600T 64port VOIP Subscriber', null, 'UN');
INSERT INTO `materiales` VALUES ('1525', 'T 32713587', 'MA5603T/5600T Sensor de Temperatura', null, 'UN');
INSERT INTO `materiales` VALUES ('1526', 'T 32713588', 'MA5603T/5600T Battery Temperature Sensor', null, 'UN');
INSERT INTO `materiales` VALUES ('1527', 'T 32713600', 'Engineering Required Delivery Accessory', null, 'UN');
INSERT INTO `materiales` VALUES ('1528', 'T 32713711', 'Engineering Required Delivery Accessory', null, 'UN');
INSERT INTO `materiales` VALUES ('1529', 'T 32713712', 'Patch Cord,FC/PC,LC/PC,Singlemode,2mm,5m', null, 'UN');
INSERT INTO `materiales` VALUES ('1530', 'T 32713714', 'MA561X blank panel', null, 'UN');
INSERT INTO `materiales` VALUES ('1531', 'T 32713715', 'Components of Battery for S50', null, 'UN');
INSERT INTO `materiales` VALUES ('1532', 'T 32713716', 'Component of Battery-DC Distribute(S100)', null, 'UN');
INSERT INTO `materiales` VALUES ('1533', 'T 32713717', 'High protection,without RCD S50', null, 'UN');
INSERT INTO `materiales` VALUES ('1534', 'T 32713718', 'High protection,without RCD S100', null, 'UN');
INSERT INTO `materiales` VALUES ('1535', 'T 32713730', 'Patch Cord,FC/PC,LC/PC,Singlemode,2mm,5m', null, 'UN');
INSERT INTO `materiales` VALUES ('1536', 'T 32713732', 'Blank Panel for Common Interface Board', null, 'UN');
INSERT INTO `materiales` VALUES ('1537', 'T 32713733', 'Blank Panel for GIU Board Slot Frame', null, 'UN');
INSERT INTO `materiales` VALUES ('1538', 'T 32713734', 'Blank Panel for Service Board Slot', null, 'UN');
INSERT INTO `materiales` VALUES ('1539', 'T 32713735', 'Indicator,41mm,Transparent,Lamp,48V', null, 'UN');
INSERT INTO `materiales` VALUES ('1540', 'T 32713736', 'GDT Protective Unit', null, 'UN');
INSERT INTO `materiales` VALUES ('1541', 'T 32713737', 'Harness,F01S300,64port Cable 4*16pair', null, 'UN');
INSERT INTO `materiales` VALUES ('1542', 'T 32713738', 'Harness,F01D500,64port Cable 4*16pair', null, 'UN');
INSERT INTO `materiales` VALUES ('1543', 'T 32713740', 'Harness,F01E100,32-port Subscriber Cable', null, 'UN');
INSERT INTO `materiales` VALUES ('1544', 'T 32713741', '10pairs ADSL/POTS/VDSL(Board in SPL)', null, 'UN');
INSERT INTO `materiales` VALUES ('1545', 'T 32713742', 'IDC Tool', null, 'UN');
INSERT INTO `materiales` VALUES ('1546', 'T 32713743', 'Testing Plug', null, 'UN');
INSERT INTO `materiales` VALUES ('1547', 'T 32713750', '10-pair Cable Side Strip,white', null, 'UN');
INSERT INTO `materiales` VALUES ('1548', 'T 32713751', '10-pair Cable Side Strip(Yellow)', null, 'UN');
INSERT INTO `materiales` VALUES ('1549', 'T 32713760', 'Rechargeable battery,VRLA 48V,12Ah,', null, 'UN');
INSERT INTO `materiales` VALUES ('1550', 'T 32713762', 'Rechargeable battery,VRLA 48V,92Ah', null, 'UN');
INSERT INTO `materiales` VALUES ('1551', 'T 32713799', 'MA5600T-Super Control Unit -H80D00SCUN01', null, 'UN');
INSERT INTO `materiales` VALUES ('1552', 'T 32713800', 'MA5600T-2GE Optico/Elect.-H80D00GICK01', null, 'UN');
INSERT INTO `materiales` VALUES ('1553', 'T 32713809', 'CPDOT0301 - Power Cable,3.0m,1.2mm', null, 'UN');
INSERT INTO `materiales` VALUES ('1554', 'T 32713810', 'CPPOT.501 - Single Cable,PGND Feeder', null, 'UN');
INSERT INTO `materiales` VALUES ('1555', 'T 32713811', 'SS-OP-D-LC-S-5-Patch Cord,LC/PC,SM,5m', null, 'UN');
INSERT INTO `materiales` VALUES ('1556', 'T 32713812', 'Subscriber Cable,32-Channel,3m,0.4mm', null, 'UN');
INSERT INTO `materiales` VALUES ('1557', 'T 31400326', 'POSTE DE OREFV. 9M / R300 LIVISNO ', null, 'UN');
INSERT INTO `materiales` VALUES ('1558', 'T 32713817', 'Cable Ext. Tetrapolar 4 mm 3F+N (xmetro)', null, 'UN');
INSERT INTO `materiales` VALUES ('1559', 'T 32713818', 'Materiales menores instalacion DSLAM', null, 'UN');
INSERT INTO `materiales` VALUES ('1560', 'T 32713856', 'VDSL Placa controladora-H83D00CCUE01', null, 'UN');
INSERT INTO `materiales` VALUES ('1561', 'T 32713857', 'VDSL Pinchboard Uplink 2port (sin SFP)', null, 'UN');
INSERT INTO `materiales` VALUES ('1562', 'T 32713858', 'VDSL Placa 48 port VDSL2-Vectoring ready', null, 'UN');
INSERT INTO `materiales` VALUES ('1563', 'T 32713859', 'VDSL Secondary Power Board-H83D02PDVA01', null, 'UN');
INSERT INTO `materiales` VALUES ('1564', 'T 32713860', 'VDSL System Vectoring Processing Board', null, 'UN');
INSERT INTO `materiales` VALUES ('1565', 'T 32713864', 'VDSL Alimentacion Trifasica', null, 'UN');
INSERT INTO `materiales` VALUES ('1566', null, null, null, 'UN');
INSERT INTO `materiales` VALUES ('1567', null, null, null, 'UN');
INSERT INTO `materiales` VALUES ('1568', null, null, null, 'UN');
INSERT INTO `materiales` VALUES ('1569', null, null, null, 'UN');
INSERT INTO `materiales` VALUES ('1570', null, null, null, 'UN');
INSERT INTO `materiales` VALUES ('1571', 'Ultimo', null, null, 'UN');
INSERT INTO `materiales` VALUES ('1572', null, null, null, null);
INSERT INTO `materiales` VALUES ('1573', null, null, null, null);
INSERT INTO `materiales` VALUES ('1574', null, 'NO SE DEBE CAMBIAR LA TABLA DE MATERIALES', null, null);
INSERT INTO `materiales` VALUES ('1575', null, 'esta hoja solo es para utiilizarla con filtros', null, null);

-- ----------------------------
-- Table structure for `materiales_ot`
-- ----------------------------
DROP TABLE IF EXISTS `materiales_ot`;
CREATE TABLE `materiales_ot` (
  `codigo` varchar(50) DEFAULT NULL,
  `ot` varchar(20) DEFAULT NULL,
  `cantidad` varchar(20) DEFAULT NULL,
  `usuario` varchar(50) DEFAULT NULL,
  `fchalta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of materiales_ot
-- ----------------------------

-- ----------------------------
-- Table structure for `operario`
-- ----------------------------
DROP TABLE IF EXISTS `operario`;
CREATE TABLE `operario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) DEFAULT NULL,
  `geren` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `imei` varchar(100) DEFAULT NULL,
  `fchalta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of operario
-- ----------------------------
INSERT INTO `operario` VALUES ('12', 'Luis', 'jmolina', 'luis', '', '2019-04-02 08:57:04');
INSERT INTO `operario` VALUES ('13', 'TEL01', 'jmolina', 'tel01', '', '2019-05-03 17:49:51');

-- ----------------------------
-- Table structure for `ot`
-- ----------------------------
DROP TABLE IF EXISTS `ot`;
CREATE TABLE `ot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nro_ot` int(11) DEFAULT NULL,
  `id_loc` int(11) DEFAULT NULL,
  `localidad` varchar(100) DEFAULT NULL,
  `zona` varchar(100) DEFAULT NULL,
  `id_bar` int(11) DEFAULT NULL,
  `barrio` varchar(100) DEFAULT NULL,
  `id_cal` int(11) DEFAULT NULL,
  `calle` varchar(100) DEFAULT NULL,
  `altura` varchar(100) DEFAULT NULL,
  `id_motivo` int(11) DEFAULT NULL,
  `motivo` varchar(100) DEFAULT NULL,
  `cod_empleado_asig` int(11) DEFAULT NULL,
  `nombre_empleado_asig` varchar(100) DEFAULT NULL,
  `cod_cuadrilla_asig` int(11) DEFAULT NULL,
  `contratista_asig` varchar(11) DEFAULT '''''',
  `fchalta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lat` varchar(30) NOT NULL DEFAULT '-24.7918987',
  `lng` varchar(30) NOT NULL DEFAULT '-65.429728',
  `observacion` varchar(800) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1021391 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ot
-- ----------------------------
INSERT INTO `ot` VALUES ('1021389', '1021389', '0', '', '', '0', '', '0', '', '', '0', 'Tendido FO Telecom', '13', 'TEL01', '0', '', '2019-05-15 08:01:01', '-24.7918987', '-65.429728', '');
INSERT INTO `ot` VALUES ('1021390', '1021390', '0', '', '', '0', '', '0', '', '', '0', 'Tendido FO Telecom METAN', '13', 'TEL01', '0', '', '0000-00-00 00:00:00', '-24.7918987', '-65.429728', null);

-- ----------------------------
-- Table structure for `ot_finalizada`
-- ----------------------------
DROP TABLE IF EXISTS `ot_finalizada`;
CREATE TABLE `ot_finalizada` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `OT` int(11) DEFAULT NULL,
  `fechainicio` varchar(20) DEFAULT NULL,
  `fechafinalizo` varchar(20) DEFAULT NULL,
  `idmotivofinaliza` int(11) DEFAULT NULL,
  `lat` varchar(20) DEFAULT NULL,
  `lng` varchar(20) DEFAULT NULL,
  `altura` varchar(20) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL,
  `t` varchar(20) DEFAULT NULL,
  `fch` varchar(20) DEFAULT NULL,
  `fchalta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LEGAJO` varchar(50) NOT NULL,
  `OBSERVACION` varchar(5000) DEFAULT NULL,
  `CODIGO_CUADRILLA` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ot_finalizada
-- ----------------------------
INSERT INTO `ot_finalizada` VALUES ('35', '1021389', '15/05/2019 08:14:02', '', '0', '-24.776878333333336', '-65.424175', '', 'A', 'TEL01', '15/05/2019 08:14:02', '2019-05-15 08:14:06', '', null, null);
INSERT INTO `ot_finalizada` VALUES ('36', '1021389', null, '17/05/2019 08:23:57', '5', '-24.776968333333336', '-65.42404166666667', '', 'F', 'TEL01', '17/05/2019 08:23:57', '2019-05-17 08:24:00', '', null, null);

-- ----------------------------
-- Table structure for `ot_finalizada_valor`
-- ----------------------------
DROP TABLE IF EXISTS `ot_finalizada_valor`;
CREATE TABLE `ot_finalizada_valor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `OT` int(11) DEFAULT NULL,
  `valor` varchar(25000) DEFAULT NULL,
  `fchalta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `paso_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of ot_finalizada_valor
-- ----------------------------
INSERT INTO `ot_finalizada_valor` VALUES ('1', '1021375', '1', '2019-02-27 22:14:41', '12');
INSERT INTO `ot_finalizada_valor` VALUES ('2', '1021373', '', '2019-02-27 22:47:55', '0');
INSERT INTO `ot_finalizada_valor` VALUES ('3', '1021373', '1', '2019-02-27 22:47:55', '7');
INSERT INTO `ot_finalizada_valor` VALUES ('4', '1021373', '', '2019-02-27 22:47:56', '0');
INSERT INTO `ot_finalizada_valor` VALUES ('5', '1021373', '1', '2019-02-27 22:47:56', '7');
INSERT INTO `ot_finalizada_valor` VALUES ('6', '1021263', '', '2019-02-28 23:31:39', '15');
INSERT INTO `ot_finalizada_valor` VALUES ('7', '1021374', '1', '2019-03-11 19:31:18', '1');
INSERT INTO `ot_finalizada_valor` VALUES ('8', '1021374', 'ok 23', '2019-03-11 19:31:18', '2');
INSERT INTO `ot_finalizada_valor` VALUES ('9', '1021374', 'no', '2019-03-11 19:31:18', '3');
INSERT INTO `ot_finalizada_valor` VALUES ('10', '1021374', '600', '2019-03-11 19:31:18', '4');
INSERT INTO `ot_finalizada_valor` VALUES ('11', '1021374', '1', '2019-03-11 19:31:18', '5');
INSERT INTO `ot_finalizada_valor` VALUES ('12', '1021374', '', '2019-03-11 19:31:18', '0');
INSERT INTO `ot_finalizada_valor` VALUES ('13', '1021374', '', '2019-03-11 19:31:18', '7');
INSERT INTO `ot_finalizada_valor` VALUES ('14', '1021374', '', '2019-03-11 19:31:18', '11');
INSERT INTO `ot_finalizada_valor` VALUES ('15', '1021374', '', '2019-03-11 19:31:18', '12');
INSERT INTO `ot_finalizada_valor` VALUES ('16', '1021374', '1', '2019-03-11 19:31:18', '13');
INSERT INTO `ot_finalizada_valor` VALUES ('17', '1021374', '', '2019-03-11 19:31:18', '15');
INSERT INTO `ot_finalizada_valor` VALUES ('18', '1021376', '10', '2019-03-21 11:03:22', '0');
INSERT INTO `ot_finalizada_valor` VALUES ('19', '1021376', '1', '2019-03-21 11:03:22', '19');
INSERT INTO `ot_finalizada_valor` VALUES ('20', '1021386', 'ok', '2019-04-21 19:46:45', '0');
INSERT INTO `ot_finalizada_valor` VALUES ('21', '1021389', '', '2019-05-17 08:24:00', '0');
INSERT INTO `ot_finalizada_valor` VALUES ('22', '1021389', '1', '2019-05-17 08:24:00', '0');
INSERT INTO `ot_finalizada_valor` VALUES ('23', '1021389', '', '2019-05-17 08:24:00', '68');
INSERT INTO `ot_finalizada_valor` VALUES ('24', '1021389', 'de vuelta 6', '2019-05-17 08:24:00', '0');
INSERT INTO `ot_finalizada_valor` VALUES ('25', '1021389', '', '2019-05-17 08:24:00', '70');
INSERT INTO `ot_finalizada_valor` VALUES ('26', '1021389', '', '2019-05-17 08:24:00', '0');
INSERT INTO `ot_finalizada_valor` VALUES ('27', '1021389', '', '2019-05-17 08:24:00', '72');
INSERT INTO `ot_finalizada_valor` VALUES ('28', '1021389', '', '2019-05-17 08:24:00', '73');

-- ----------------------------
-- Table structure for `pasos`
-- ----------------------------
DROP TABLE IF EXISTS `pasos`;
CREATE TABLE `pasos` (
  `id_paso` int(11) NOT NULL,
  `desc_campo` varchar(500) DEFAULT NULL,
  `tipo` varchar(500) DEFAULT NULL,
  `foto` int(11) DEFAULT NULL,
  `obligatorio` int(11) DEFAULT NULL,
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `ot` int(11) NOT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of pasos
-- ----------------------------
INSERT INTO `pasos` VALUES ('1', 'foto inicio', 'FOTO\r\n', '1', '1', '1', '0');
INSERT INTO `pasos` VALUES ('2', 'estados de los postes\r\n', 'CADENA\r\n', '0', '0', '2', '0');
INSERT INTO `pasos` VALUES ('3', 'cruces americanos\r\n', 'CADENA\r\n', '0', '1', '3', '0');
INSERT INTO `pasos` VALUES ('4', 'distancia\r\n', 'MEDIDA\r\n', '0', '1', '4', '0');
INSERT INTO `pasos` VALUES ('5', 'preuba', 'CADENA', '1', '1', '5', '0');
INSERT INTO `pasos` VALUES ('0', 'Cargar Cosas', 'FOTO', '1', '0', '6', '1021373');
INSERT INTO `pasos` VALUES ('7', 'cortar la caja', 'CADENA', '1', '0', '7', '1021373');
INSERT INTO `pasos` VALUES ('11', 'ver distancias', 'CADENA', '1', '0', '11', '1021374');
INSERT INTO `pasos` VALUES ('12', 'foto inicial antes de la apertura', 'FOTO', '1', '0', '12', '1021375');
INSERT INTO `pasos` VALUES ('13', 'foto de inicio de trabajo', 'FOTO', '1', '0', '13', '1021372');
INSERT INTO `pasos` VALUES ('15', 'foto obligatoria', 'FOTO', '1', '0', '15', '1021263');
INSERT INTO `pasos` VALUES ('16', 'actividad 01', 'CADENA', '1', '0', '16', '1021152');
INSERT INTO `pasos` VALUES ('0', 'prueba cantidad 1', 'CANTIDAD', '1', '0', '18', '1021376');
INSERT INTO `pasos` VALUES ('19', 'prueba foto 1', 'FOTO', '1', '0', '19', '1021376');
INSERT INTO `pasos` VALUES ('26', 'prueba para borrar', 'CADENA', '1', '0', '26', '1021378');
INSERT INTO `pasos` VALUES ('27', 'prueba para borrar 2', 'CADENA', '1', '0', '27', '1021379');
INSERT INTO `pasos` VALUES ('36', 'prueba 1-1', 'CADENA', '1', '0', '36', '1021377');
INSERT INTO `pasos` VALUES ('37', 'prueba 1-1', 'CADENA', '1', '0', '37', '1021377');
INSERT INTO `pasos` VALUES ('0', 'prueba 1-1', 'CADENA', '1', '0', '38', '1021377');
INSERT INTO `pasos` VALUES ('39', 'prueba 1-1', 'CADENA', '1', '0', '39', '1021377');
INSERT INTO `pasos` VALUES ('40', 'actividad 1', 'CADENA', '1', '0', '40', '1021377');
INSERT INTO `pasos` VALUES ('41', 'actividad 2', 'CANTIDAD', '1', '0', '41', '1021377');
INSERT INTO `pasos` VALUES ('42', 'ssfsdfsdf', 'CADENA', '0', '0', '42', '1021381');
INSERT INTO `pasos` VALUES ('43', 'activiadad con observacion', 'CADENA', '0', '0', '43', '1021382');
INSERT INTO `pasos` VALUES ('0', 'prueba con observacion', 'CADENA', '0', '0', '44', '1021383');
INSERT INTO `pasos` VALUES ('45', 'prueba con observacion 2', 'CADENA', '0', '0', '45', '1021383');
INSERT INTO `pasos` VALUES ('46', 'nueva prueba 01', 'CADENA', '0', '0', '46', '1021384');
INSERT INTO `pasos` VALUES ('47', 'demarcacion de area, permisos', 'CADENA', '0', '0', '47', '1021385');
INSERT INTO `pasos` VALUES ('48', 'Fotografia lugar de inicio, se debe ver señalizacion', 'FOTO', '1', '0', '48', '1021385');
INSERT INTO `pasos` VALUES ('0', 'Fotografia lugar de finalizacion, se debe ver señalizacion', 'FOTO', '1', '0', '49', '1021385');
INSERT INTO `pasos` VALUES ('0', 'Longitud zanjeo', 'CANTIDAD', '0', '0', '50', '1021385');
INSERT INTO `pasos` VALUES ('51', 'Longitud tritubo', 'CANTIDAD', '0', '0', '51', '1021385');
INSERT INTO `pasos` VALUES ('0', 'demarcacion de area, permisos', 'CADENA', '0', '0', '52', '1021386');
INSERT INTO `pasos` VALUES ('53', 'Sacar foto inicio tarea', 'FOTO', '1', '0', '53', '1021387');
INSERT INTO `pasos` VALUES ('0', 'Foto actividad', 'FOTO', '1', '0', '54', '1021387');
INSERT INTO `pasos` VALUES ('0', 'Inicio secuencial tendido', 'CANTIDAD', '0', '0', '55', '1021387');
INSERT INTO `pasos` VALUES ('56', 'Fin secuencial tendido', 'CANTIDAD', '0', '0', '56', '1021387');
INSERT INTO `pasos` VALUES ('57', 'Mediciones con OTDR', 'CADENA', '0', '0', '57', '1021387');
INSERT INTO `pasos` VALUES ('58', 'Sellado camara Grupo electrogeno', 'FOTO', '1', '0', '58', '1021388');
INSERT INTO `pasos` VALUES ('0', 'Sellado camara Nº 2 y 3 con armado de marco. Enviar foto de cada camara', 'FOTO', '1', '0', '59', '1021388');
INSERT INTO `pasos` VALUES ('60', 'Armar marco camara nº3, antes del ripio. Enmarcar y sellar camara 3 y 4. Preveer que se debe abrir esa camara para terminar de pasar el cable', 'FOTO', '1', '0', '60', '1021388');
INSERT INTO `pasos` VALUES ('61', 'Colocar prisionero en caja exterior poder judicial', 'FOTO', '1', '0', '61', '1021388');
INSERT INTO `pasos` VALUES ('62', 'Reparar manposteria en el techo poder judicial, antes de ingreso de fibra', 'FOTO', '1', '0', '62', '1021388');
INSERT INTO `pasos` VALUES ('63', 'Colocar bajdeja para cambio de altura en Subsuelo', 'FOTO', '1', '0', '63', '1021388');
INSERT INTO `pasos` VALUES ('64', 'repasar fijacion de fibra en bandeja poder judicial', 'FOTO', '1', '0', '64', '1021388');
INSERT INTO `pasos` VALUES ('65', 'tarea nueva', 'CADENA', '0', '0', '65', '1021386');
INSERT INTO `pasos` VALUES ('0', 'Foto secuencial inicio', 'FOTO', '1', '0', '66', '1021389');
INSERT INTO `pasos` VALUES ('0', 'Foto secuencial fin', 'FOTO', '1', '0', '67', '1021389');
INSERT INTO `pasos` VALUES ('68', 'Describir bufer y fibra a empalmar.', 'CADENA', '0', '0', '68', '1021389');
INSERT INTO `pasos` VALUES ('0', 'Cantidad de pelos FO existente', 'CADENA', '0', '0', '69', '1021389');
INSERT INTO `pasos` VALUES ('70', 'Foto empalme', 'FOTO', '1', '0', '70', '1021389');
INSERT INTO `pasos` VALUES ('0', 'Ubicacion de empalme', 'CADENA', '0', '0', '71', '1021389');
INSERT INTO `pasos` VALUES ('72', 'Foto rotulado Telecom, foto vista de rack', 'FOTO', '1', '0', '72', '1021389');
INSERT INTO `pasos` VALUES ('73', 'Guardar lectura en ODF, indicar nombre de archivo', 'CADENA', '0', '0', '73', '1021389');
INSERT INTO `pasos` VALUES ('75', 'sadf', 'CADENA', '0', '0', '75', '1021391');
INSERT INTO `pasos` VALUES ('75', 'sadf', 'CADENA', '0', '0', '76', '1021392');
INSERT INTO `pasos` VALUES ('75', 'sadf', 'CADENA', '0', '0', '77', '1021393');
INSERT INTO `pasos` VALUES ('0', 'Foto secuencial inicio', 'FOTO', '1', '0', '78', '1021390');
INSERT INTO `pasos` VALUES ('0', 'Foto secuencial fin', 'FOTO', '1', '0', '79', '1021390');
INSERT INTO `pasos` VALUES ('0', 'Describir bufer y fibra a empalmar.', 'CADENA', '0', '0', '80', '1021390');
INSERT INTO `pasos` VALUES ('0', 'Cantidad de pelos FO existente', 'CADENA', '0', '0', '81', '1021390');
INSERT INTO `pasos` VALUES ('0', 'Foto empalme', 'FOTO', '1', '0', '82', '1021390');
INSERT INTO `pasos` VALUES ('0', 'Ubicacion de empalme', 'CADENA', '0', '0', '83', '1021390');
INSERT INTO `pasos` VALUES ('0', 'Foto rotulado Telecom, foto vista de rack', 'FOTO', '1', '0', '84', '1021390');
INSERT INTO `pasos` VALUES ('0', 'Guardar lectura en ODF, indicar nombre de archivo', 'CADENA', '0', '0', '85', '1021390');

-- ----------------------------
-- Table structure for `pasosxtemplate`
-- ----------------------------
DROP TABLE IF EXISTS `pasosxtemplate`;
CREATE TABLE `pasosxtemplate` (
  `id_pasosxtemplate` int(11) NOT NULL AUTO_INCREMENT,
  `id_paso` int(11) DEFAULT NULL,
  `id_template` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_pasosxtemplate`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of pasosxtemplate
-- ----------------------------
INSERT INTO `pasosxtemplate` VALUES ('1', '1', '2');
INSERT INTO `pasosxtemplate` VALUES ('2', '2', '2');
INSERT INTO `pasosxtemplate` VALUES ('3', '3', '2');
INSERT INTO `pasosxtemplate` VALUES ('4', '4', '2');
INSERT INTO `pasosxtemplate` VALUES ('5', '1', '3');

-- ----------------------------
-- Table structure for `roles`
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `fchalta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `fchcad` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modulo` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', 'admin', '2019-01-14 12:13:37', '0000-00-00 00:00:00', 'OT');
INSERT INTO `roles` VALUES ('2', 'operario', '2019-01-14 12:13:37', '0000-00-00 00:00:00', 'OT');
INSERT INTO `roles` VALUES ('3', 'inspector', '2019-01-14 12:13:37', '0000-00-00 00:00:00', 'OT');
INSERT INTO `roles` VALUES ('4', 'super-inspector', '2019-01-14 12:13:37', '0000-00-00 00:00:00', 'OT');

-- ----------------------------
-- Table structure for `sincronizar`
-- ----------------------------
DROP TABLE IF EXISTS `sincronizar`;
CREATE TABLE `sincronizar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `envio` text,
  `tipo` text,
  `valor` text,
  `lat` text,
  `lng` text,
  `fchalta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `usuario` text,
  `imei` text,
  `precision` text,
  `gps` int(11) DEFAULT NULL,
  `red` int(11) DEFAULT NULL,
  `version` text,
  `aplicacion` text,
  `actualizada` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=553 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sincronizar
-- ----------------------------
INSERT INTO `sincronizar` VALUES ('1', '.', 'R', '.', null, null, '2019-01-24 15:46:12', null, null, null, null, null, null, null, null);
INSERT INTO `sincronizar` VALUES ('15', '.', 'R', '.', null, null, '2019-02-14 21:51:08', null, null, null, null, null, null, null, null);
INSERT INTO `sincronizar` VALUES ('16', '.', 'R', '.', null, null, '2019-02-14 21:54:22', null, null, null, null, null, null, null, null);
INSERT INTO `sincronizar` VALUES ('501', '', 'R', '', '0.0', '0.0', '2019-04-02 08:57:20', 'Luis', '355519073020059', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('502', '', 'FOTOS', '{\"prueba\": \"prueba de conexion\"  }', '', '', '2019-04-02 09:00:10', 'Luis', '355519073020059', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('503', '', 'FOTOS', '{\"prueba\": \"prueba de conexion\"  }', '', '', '2019-04-02 09:00:13', 'Luis', '355519073020059', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('504', '', 'FOTOS', '{\"prueba\": \"prueba de conexion\"  }', '', '', '2019-04-02 09:00:14', 'Luis', '355519073020059', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('505', '', 'INICIO_DE_DIA', '{\"fecha\": \"16-04-2019 08:42:50\" }', '-24.78863666666667', '-65.41496000000001', '2019-04-16 08:43:10', 'Luis', '353108080913514', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('506', '', 'R', '', '-24.78863666666667', '-65.41496000000001', '2019-04-16 08:43:11', 'Luis', '353108080913514', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('507', '', 'R', '', '-24.788543333333337', '-65.41482666666667', '2019-04-16 08:57:36', 'Luis', '353108080913514', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('508', '', 'INICIO_DE_DIA', '{\"fecha\": \"21-04-2019 19:41:11\" }', '0.0', '0.0', '2019-04-21 19:41:37', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('509', '', 'R', '', '0.0', '0.0', '2019-04-21 19:41:38', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('510', '', 'FOTOS', '{\"prueba\": \"prueba de conexion\"  }', '', '', '2019-04-21 19:45:16', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('511', '', 'FOTOS', '{\"prueba\": \"prueba de conexion\"  }', '', '', '2019-04-21 19:45:21', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('512', '1021386', 'OT', '{\"altura\":\"\",\"codigo_cuadrilla\":0,\"estado\":\"A\",\"fch\":\"21/04/2019 19:46:10\",\"fchalta\":\"\",\"fechafinalizo\":\"\",\"fechainicio\":\"21/04/2019 19:46:10\",\"id\":0,\"id_inm\":0,\"id_seg\":0,\"id_txc\":0,\"idmotivofinaliza\":0,\"lat\":\"-24.776819999999997\",\"legajo\":0,\"lng\":\"-65.42400333333333\",\"nro_form\":0,\"nro_sec\":0,\"observacion\":\"\",\"ot\":1021386,\"t\":\"Luis\",\"_id\":7}', '-24.776819999999997', '-65.42400333333333', '2019-04-21 19:46:33', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('513', '1021386', 'OT', '{\"altura\":\"\",\"codigo_cuadrilla\":0,\"estado\":\"F\",\"fch\":\"21/04/2019 19:46:22\",\"fchalta\":\"\",\"fechafinalizo\":\"21/04/2019 19:46:22\",\"id\":0,\"id_inm\":0,\"id_seg\":0,\"id_txc\":0,\"idmotivofinaliza\":5,\"lat\":\"-24.776855\",\"legajo\":0,\"lng\":\"-65.42399\",\"nro_form\":0,\"nro_sec\":0,\"observacion\":\"\",\"ot\":1021386,\"t\":\"Luis\",\"_id\":7}', '-24.776855', '-65.42399', '2019-04-21 19:46:45', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('514', '1021386', 'PASOS', '{\"data\":[{\"desc_campo\":\"demarcacion de area, permisos\",\"foto\":0,\"id_paso\":0,\"obligatorio\":0,\"ot\":0,\"tipo\":\"CADENA\",\"valor\":\"ok\",\"_id\":52}]}', '-24.776855', '-65.42399', '2019-04-21 19:46:45', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('515', '', 'FOTOS', '{\"prueba\": \"prueba de conexion\"  }', '', '', '2019-04-21 19:50:10', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('516', '', 'FOTOS', '{\"prueba\": \"prueba de conexion\"  }', '', '', '2019-04-21 19:50:11', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('517', '', 'R', '', '-24.776901666666664', '-65.42441666666667', '2019-04-21 19:50:27', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('518', '', 'R', '', '-24.77689833333333', '-65.42407', '2019-04-21 19:51:21', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('519', '', 'R', '', '-24.776905000000003', '-65.42403833333333', '2019-04-21 19:51:52', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('520', '', 'R', '', '-24.776864999999997', '-65.42406333333334', '2019-04-21 19:52:10', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('521', '', 'R', '', '-24.77676166666667', '-65.42427666666667', '2019-04-21 19:52:24', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('522', '', 'R', '', '-24.777113333333332', '-65.4259', '2019-04-21 19:52:43', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('523', '', 'R', '', '0.0', '0.0', '2019-04-28 09:10:15', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('524', '', 'R', '', '-24.784135', '-65.43059666666666', '2019-04-28 09:11:42', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('525', '', 'INICIO_DE_DIA', '{\"fecha\": \"03-05-2019 17:51:54\" }', '-24.776884360882292', '-65.42403474829928', '2019-05-03 17:51:58', 'TEL01', '863451033775074', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('526', '', 'R', '', '-24.776884360882292', '-65.42403474829928', '2019-05-03 17:52:02', 'TEL01', '863451033775074', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('527', '', 'R', '', '-24.758651666666665', '-65.35304666666666', '2019-05-04 08:05:27', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('528', '', 'R', '', '-24.7808538594645', '-65.42549308959119', '2019-05-07 17:03:21', 'TEL01', '863451033775074', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('529', '', 'R', '', '-24.802746666666668', '-65.44176166666666', '2019-05-07 17:06:48', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('530', '', 'R', '', '-24.776758333333337', '-65.423485', '2019-05-08 08:03:03', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('531', '', 'R', '', '-25.499531666666666', '-64.97070666666667', '2019-05-08 21:19:45', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('532', '', 'R', '', '-25.499531666666666', '-64.97070666666667', '2019-05-08 21:19:48', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('533', '', 'R', '', '-25.492736666666662', '-64.97616833333333', '2019-05-08 22:32:17', 'Luis', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('534', '', 'R', '', '-24.77689666666667', '-65.424195', '2019-05-15 08:13:41', 'TEL01', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('535', '1021389', 'OT', '{\"altura\":\"\",\"codigo_cuadrilla\":0,\"estado\":\"A\",\"fch\":\"15/05/2019 08:14:02\",\"fchalta\":\"\",\"fechafinalizo\":\"\",\"fechainicio\":\"15/05/2019 08:14:02\",\"id\":0,\"id_inm\":0,\"id_seg\":0,\"id_txc\":0,\"idmotivofinaliza\":0,\"lat\":\"-24.776878333333336\",\"legajo\":0,\"lng\":\"-65.424175\",\"nro_form\":0,\"nro_sec\":0,\"observacion\":\"\",\"ot\":1021389,\"t\":\"TEL01\",\"_id\":75}', '-24.776878333333336', '-65.424175', '2019-05-15 08:14:06', 'TEL01', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('536', '', 'FOTOS', '{\"fotos\": \"/storage/emulated/0/ot_img/1021389_20190515091714_TEL01_.jpg\", \"observacion\":\"Error archivo borrado\"  }', '-24.845881666666667', '-65.44427666666667', '2019-05-15 16:46:43', 'TEL01', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('537', '1021389', 'FOTOS', '{\"fotos\": \"1021389_20190515091758_TEL01_.jpg\", \"observacion\":\"Enviado\"  }', '-24.845998333333334', '-65.44436166666667', '2019-05-15 16:46:43', 'TEL01', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('538', '', 'R', '', '-24.853395', '-65.436515', '2019-05-15 16:46:44', 'TEL01', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('539', '', 'R', '', '-24.7769', '-65.42405333333333', '2019-05-16 07:10:23', 'TEL01', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('540', '1021389', 'FOTOS', '{\"fotos\": \"1021389_20190516130452_TEL01_.jpg\", \"observacion\":\"Enviado\"  }', '-25.499248333333338', '-64.97054499999999', '2019-05-17 08:22:19', 'TEL01', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('541', '1021389', 'FOTOS', '{\"fotos\": \"1021389_20190516130546_TEL01_.jpg\", \"observacion\":\"Enviado\"  }', '-25.499258333333334', '-64.97053333333334', '2019-05-17 08:22:20', 'TEL01', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('542', '1021389', 'FOTOS', '{\"fotos\": \"1021389_20190516130617_TEL01_.jpg\", \"observacion\":\"Enviado\"  }', '-25.49925166666667', '-64.970535', '2019-05-17 08:22:20', 'TEL01', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('543', '1021389', 'FOTOS', '{\"fotos\": \"1021389_20190516131811_TEL01_.jpg\", \"observacion\":\"Enviado\"  }', '-25.499573333333334', '-64.97070666666667', '2019-05-17 08:22:20', 'TEL01', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('544', '1021389', 'FOTOS', '{\"fotos\": \"1021389_20190516131830_TEL01_.jpg\", \"observacion\":\"Enviado\"  }', '-25.499588333333335', '-64.97065166666667', '2019-05-17 08:22:20', 'TEL01', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('545', '1021389', 'FOTOS', '{\"fotos\": \"1021389_20190516132314_TEL01_.jpg\", \"observacion\":\"Enviado\"  }', '-25.497754999999998', '-64.971975', '2019-05-17 08:22:20', 'TEL01', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('546', '1021389', 'FOTOS', '{\"fotos\": \"1021389_20190516132440_TEL01_.jpg\", \"observacion\":\"Enviado\"  }', '-25.497865', '-64.972', '2019-05-17 08:22:20', 'TEL01', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('547', '', 'R', '', '-24.776988333333332', '-65.42399999999999', '2019-05-17 08:23:07', 'TEL01', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('548', '', 'FOTOS', '{\"fotos\": \"/storage/emulated/0/ot_img/1021389_20190517082332_TEL01_.jpg\", \"observacion\":\"Error archivo borrado\"  }', '-24.777008333333335', '-65.42405333333333', '2019-05-17 08:24:00', 'TEL01', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('549', '1021389', 'OT', '{\"altura\":\"\",\"codigo_cuadrilla\":0,\"estado\":\"F\",\"fch\":\"17/05/2019 08:23:57\",\"fchalta\":\"\",\"fechafinalizo\":\"17/05/2019 08:23:57\",\"id\":0,\"id_inm\":0,\"id_seg\":0,\"id_txc\":0,\"idmotivofinaliza\":5,\"lat\":\"-24.776968333333336\",\"legajo\":0,\"lng\":\"-65.42404166666667\",\"nro_form\":0,\"nro_sec\":0,\"observacion\":\"\",\"ot\":1021389,\"t\":\"TEL01\",\"_id\":75}', '-24.776968333333336', '-65.42404166666667', '2019-05-17 08:24:00', 'TEL01', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('550', '1021389', 'PASOS', '{\"data\":[{\"desc_campo\":\"Foto secuencial inicio\",\"foto\":1,\"id_paso\":0,\"obligatorio\":0,\"ot\":0,\"tipo\":\"FOTO\",\"valor\":\"\",\"_id\":66},{\"desc_campo\":\"Foto secuencial fin\",\"foto\":1,\"id_paso\":0,\"obligatorio\":0,\"ot\":0,\"tipo\":\"FOTO\",\"valor\":\"1\",\"_id\":67},{\"desc_campo\":\"Describir bufer y fibra a empalmar.\",\"foto\":0,\"id_paso\":68,\"obligatorio\":0,\"ot\":0,\"tipo\":\"CADENA\",\"valor\":\"\",\"_id\":68},{\"desc_campo\":\"Cantidad de pelos FO existente\",\"foto\":0,\"id_paso\":0,\"obligatorio\":0,\"ot\":0,\"tipo\":\"CADENA\",\"valor\":\"de vuelta 6\",\"_id\":69},{\"desc_campo\":\"Foto empalme\",\"foto\":1,\"id_paso\":70,\"obligatorio\":0,\"ot\":0,\"tipo\":\"FOTO\",\"valor\":\"\",\"_id\":70},{\"desc_campo\":\"Ubicacion de empalme\",\"foto\":0,\"id_paso\":0,\"obligatorio\":0,\"ot\":0,\"tipo\":\"CADENA\",\"valor\":\"\",\"_id\":71},{\"desc_campo\":\"Foto rotulado Telecom, foto vista de rack\",\"foto\":1,\"id_paso\":72,\"obligatorio\":0,\"ot\":0,\"tipo\":\"FOTO\",\"valor\":\"\",\"_id\":72},{\"desc_campo\":\"Guardar lectura en ODF, indicar nombre de archivo\",\"foto\":0,\"id_paso\":73,\"obligatorio\":0,\"ot\":0,\"tipo\":\"CADENA\",\"valor\":\"\",\"_id\":73}]}', '-24.776968333333336', '-65.42404166666667', '2019-05-17 08:24:00', 'TEL01', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('551', '', 'R', '', '-24.84588666666667', '-65.44451333333333', '2019-05-17 09:30:20', 'TEL01', '359978090556790', null, '0', '0', null, 'ordenes', null);
INSERT INTO `sincronizar` VALUES ('552', '', 'R', '', '-24.839223333333333', '-65.47552833333333', '2019-05-17 11:22:39', 'TEL01', '359978090556790', null, '0', '0', null, 'ordenes', null);

-- ----------------------------
-- Table structure for `template`
-- ----------------------------
DROP TABLE IF EXISTS `template`;
CREATE TABLE `template` (
  `id_template` int(11) NOT NULL,
  `desc_template` varchar(500) DEFAULT NULL,
  `observacion` varchar(1500) DEFAULT NULL,
  `titulo` varchar(500) DEFAULT NULL,
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of template
-- ----------------------------
INSERT INTO `template` VALUES ('1', 'NORMAL', 'NORMAL', 'NORMAL', '1');
INSERT INTO `template` VALUES ('2', 'Obra N\r\nNombre Contacto\r\nTelefono Contacto\r\nOtros', 'Plano\r\nPermisos\r\nPerro\r\nCredenciales\r\nLlave central', 'Relevamiento Tendido', '2');
INSERT INTO `template` VALUES ('3', 'prueba23', 'preuba 23', 'prueba3', '3');

-- ----------------------------
-- Table structure for `usuarios`
-- ----------------------------
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(150) NOT NULL,
  `rol_id` int(11) DEFAULT NULL,
  `fchalta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `fchultimoacceso` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `fchcad` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `geren` text,
  `modulo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of usuarios
-- ----------------------------
INSERT INTO `usuarios` VALUES ('1', 'usuario', 'info@prueba.com.ar', '9250e222c4c71f0c58d4c54b50a880a312e9f9fed55d5c3aa0b0e860ded99165', '3', '2019-01-14 12:15:14', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 'S', 'ot');
INSERT INTO `usuarios` VALUES ('2', '50928', 'info@prueba.com.ar', '9250e222c4c71f0c58d4c54b50a880a312e9f9fed55d5c3aa0b0e860ded99165', '2', '2019-01-14 12:15:14', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 'S', 'ot');
INSERT INTO `usuarios` VALUES ('3', '50926', 'info@prueba.com.ar', '9250e222c4c71f0c58d4c54b50a880a312e9f9fed55d5c3aa0b0e860ded99165', '2', '2019-01-14 12:15:14', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 'S', 'ot');
INSERT INTO `usuarios` VALUES ('4', '50842', 'info@prueba.com.ar', '9250e222c4c71f0c58d4c54b50a880a312e9f9fed55d5c3aa0b0e860ded99165', '2', '2019-01-14 12:15:14', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 'S', 'ot');
INSERT INTO `usuarios` VALUES ('5', 'admin', 'info@prueba.com.ar', '123456', '1', '2019-01-14 12:15:14', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 'S', 'admin');
INSERT INTO `usuarios` VALUES ('6', 'jmolina', 'info@prueba.com.ar', '123456', '1', '2019-03-16 20:33:28', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 'S', 'admin');

-- ----------------------------
-- View structure for `ot_finalizada_valor_view`
-- ----------------------------
DROP VIEW IF EXISTS `ot_finalizada_valor_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ot_finalizada_valor_view` AS select `p`.`desc_campo` AS `desc_campo`,`p`.`tipo` AS `tipo`,`f`.`valor` AS `valor`,`f`.`fchalta` AS `fchalta`,`p`.`ot` AS `OT`,if(isnull(`f`.`valor`),'NO','SI') AS `REALIZADO` from (`pasos` `p` left join `ot_finalizada_valor` `f` on(((`p`.`ot` = `f`.`OT`) and (`p`.`id_paso` = `f`.`paso_id`))));

-- ----------------------------
-- View structure for `ot_finalizar_view`
-- ----------------------------
DROP VIEW IF EXISTS `ot_finalizar_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ot_finalizar_view` AS select distinct `otf`.`OT` AS `ot`,(select max(ifnull(`ot_finalizada`.`fechainicio`,'')) from `ot_finalizada` where (`otf`.`OT` = `ot_finalizada`.`OT`)) AS `FECHA_INICIO`,(select max(ifnull(`ot_finalizada`.`fechafinalizo`,'')) from `ot_finalizada` where (`otf`.`OT` = `ot_finalizada`.`OT`)) AS `FECHA_FINALIZO`,1 AS `MOT`,`otf`.`t` AS `t`,`otgetlatfin`(`otf`.`OT`,`otf`.`t`) AS `LAT`,`otgetlngfin`(`otf`.`OT`,`otf`.`t`) AS `LON`,`otgetlatIni`(`otf`.`OT`,`otf`.`t`) AS `LAT_INI`,`otgetlngIni`(`otf`.`OT`,`otf`.`t`) AS `LON_INI`,`OTGETDIRECCION`(`otf`.`OT`) AS `direccion`,`otf`.`t` AS `C`,(select `ot_finalizada`.`OBSERVACION` from `ot_finalizada` where (`otf`.`OT` = `ot_finalizada`.`OT`) limit 1) AS `OBS`,`ot_is_activa`(`otf`.`OT`) AS `ACTUAL`,`OTGETMOTIVO`(`otf`.`OT`) AS `MOT_OT`,'' AS `equipos`,`otf`.`t` AS `legajo`,'0' AS `movil` from `ot_finalizada` `otf` group by `otf`.`OT`,`otf`.`idmotivofinaliza`,`otf`.`t`;

-- ----------------------------
-- View structure for `ot_view`
-- ----------------------------
DROP VIEW IF EXISTS `ot_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ot_view` AS select `ot`.`id` AS `id`,`ot`.`nro_ot` AS `nro_ot`,`ot`.`id_loc` AS `id_loc`,`ot`.`id_bar` AS `id_bar`,`ot`.`barrio` AS `barrio`,`ot`.`id_cal` AS `id_cal`,`ot`.`calle` AS `calle`,`ot`.`altura` AS `altura`,`ot`.`id_motivo` AS `id_motivo`,`ot`.`motivo` AS `motivo`,`ot`.`cod_empleado_asig` AS `cod_empleado_asig`,`ot`.`nombre_empleado_asig` AS `nombre_empleado_asig`,`ot`.`cod_cuadrilla_asig` AS `cod_cuadrilla_asig`,`ot`.`contratista_asig` AS `template`,`t`.`titulo` AS `template_titulo`,`ot`.`contratista_asig` AS `contratista_asig`,`ot`.`fchalta` AS `fchalta`,`ot`.`localidad` AS `localidad`,`ot`.`zona` AS `zona`,`ot`.`observacion` AS `observacion` from (`ot` left join `template` `t` on((`ot`.`contratista_asig` = `t`.`id_template`)));

-- ----------------------------
-- View structure for `pasosxtemplate_view`
-- ----------------------------
DROP VIEW IF EXISTS `pasosxtemplate_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `pasosxtemplate_view` AS select `pxt`.`id_pasosxtemplate` AS `id_pasosxtemplate`,`pxt`.`id_paso` AS `id_paso`,`pxt`.`id_template` AS `id_template`,`t`.`titulo` AS `titulo`,`p`.`desc_campo` AS `desc_campo` from ((`pasosxtemplate` `pxt` join `template` `t`) join `pasos` `p`) where ((`pxt`.`id_template` = `t`.`id_template`) and (`pxt`.`id_paso` = `p`.`id_paso`));

-- ----------------------------
-- View structure for `template_view`
-- ----------------------------
DROP VIEW IF EXISTS `template_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `template_view` AS select `ot`.`nro_ot` AS `id`,`ot`.`motivo` AS `motivo`,(select count(0) from `pasos` where (`pasos`.`ot` = `ot`.`nro_ot`)) AS `cantidad_pasos` from `ot` where exists(select 1 from `pasos` where (`pasos`.`ot` = `ot`.`nro_ot`));

-- ----------------------------
-- Function structure for `GETEQUIPOTIPOID`
-- ----------------------------
DROP FUNCTION IF EXISTS `GETEQUIPOTIPOID`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `GETEQUIPOTIPOID`(p_tipoid int,legajo int) RETURNS varchar(500) CHARSET latin1
begin 
declare s varchar(500);
set s='';
return s; 
end
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `operario_cuadrilla`
-- ----------------------------
DROP FUNCTION IF EXISTS `operario_cuadrilla`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `operario_cuadrilla`(p_legajo int) RETURNS varchar(50) CHARSET latin1
begin 

return 'USUARIO'; 
end
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `OTCAPTAZNOMBRE`
-- ----------------------------
DROP FUNCTION IF EXISTS `OTCAPTAZNOMBRE`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `OTCAPTAZNOMBRE`(CODIGO_CUADRILLA int) RETURNS varchar(500) CHARSET latin1
begin 
declare s varchar(500);
set s='USUARIO';
return s; 
end
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `OTCAPTAZNOMBREID`
-- ----------------------------
DROP FUNCTION IF EXISTS `OTCAPTAZNOMBREID`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `OTCAPTAZNOMBREID`(legajo int) RETURNS varchar(500) CHARSET latin1
begin 
declare s varchar(500);
set s='USUARIO';
return s; 
end
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `OTGETDIRECCION`
-- ----------------------------
DROP FUNCTION IF EXISTS `OTGETDIRECCION`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `OTGETDIRECCION`(
	`OT_nro` INT

) RETURNS varchar(500) CHARSET latin1
begin 
declare s varchar(500);
declare c int;
select count(*) into c from ot where nro_ot=OT_nro;
 if c>0 then
	select CONCAT(calle," ", altura) into s from ot where nro_ot=OT_nro limit 1;
 else
 	set s:='';
 end if;	
return s; 
end
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `otgetequipos`
-- ----------------------------
DROP FUNCTION IF EXISTS `otgetequipos`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `otgetequipos`(`legajo` int,`ot` int) RETURNS int(11)
BEGIN
	

	RETURN 0;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `otgetlatfin`
-- ----------------------------
DROP FUNCTION IF EXISTS `otgetlatfin`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `otgetlatfin`(
	`p_ot` int,
	`p_legajo` VARCHAR(50)


) RETURNS varchar(250) CHARSET latin1
BEGIN
   DECLARE  s   VARCHAR(250);
   DECLARE  c   int; 
   
  set s = '0.0';
	SELECT count(*)      INTO c
        FROM ot_finalizada
       WHERE ot = p_ot
         AND t = p_legajo
         AND fechafinalizo IS NOT NULL;
   if c>0 then      
		SELECT IFNULL(lat,'0.0')
        INTO s
        FROM ot_finalizada
       WHERE ot = p_ot
         AND t = p_legajo
         AND fechafinalizo IS NOT NULL
        limit 1;
 end if;
   RETURN s;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `otgetlatIni`
-- ----------------------------
DROP FUNCTION IF EXISTS `otgetlatIni`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `otgetlatIni`(
	`p_ot` int,
	`p_legajo` VARCHAR(50)

) RETURNS varchar(50) CHARSET latin1
begin 
declare s varchar(50); 
declare c int; 
set s='-24.789107'; 
begin

select count(*) into c from ot_finalizada where ot=p_ot and estado='A' and t=p_legajo; 
if(c>0) then
	select max(lat) into s from ot_finalizada where ot=p_ot and estado='A' and t=p_legajo; 
else
	set s='-24.789107'; 
end if;	
	
end;
return s; 
end
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `otgetlngfin`
-- ----------------------------
DROP FUNCTION IF EXISTS `otgetlngfin`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `otgetlngfin`(
	`p_ot` int,
	`p_legajo` VARCHAR(50)


) RETURNS varchar(30) CHARSET latin1
BEGIN
 	declare s   VARCHAR (30);
   declare c   int;
   set s = '0.0';
   
	SELECT count(*)            
        INTO c
        FROM ot_finalizada
       WHERE ot = p_ot
         AND t = p_legajo
         AND fechafinalizo IS NOT NULL
         limit 1;
    if c>0 then
		SELECT lng            
        INTO s
        FROM ot_finalizada
       WHERE ot = p_ot
         AND t = p_legajo
         AND fechafinalizo IS NOT NULL
         limit 1;
    end if;
   RETURN s;

end
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `otgetlngIni`
-- ----------------------------
DROP FUNCTION IF EXISTS `otgetlngIni`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `otgetlngIni`(
	`p_ot` int,
	`p_legajo` VARCHAR(50)

) RETURNS varchar(50) CHARSET latin1
begin 
declare s varchar(50); 
declare c int;
set s='-65.410366'; 
begin
select count(*) into c from ot_finalizada where ot=p_ot and estado='A' and t=p_legajo;
if(c>0) then
	select max(lng) into s from ot_finalizada where ot=p_ot and estado='A' and t=p_legajo;
end if;

end;

return s; 
end
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `OTGETMOTIVO`
-- ----------------------------
DROP FUNCTION IF EXISTS `OTGETMOTIVO`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `OTGETMOTIVO`(
	`OT_nro` int

) RETURNS varchar(500) CHARSET latin1
begin 
declare s varchar(500);
declare c int;
select count(*) into c from ot where nro_ot=OT_nro;
if c>0 then 
	select motivo into s from ot where nro_ot=OT_nro limit 1;
end if;
return s; 
end
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `ot_is_activa`
-- ----------------------------
DROP FUNCTION IF EXISTS `ot_is_activa`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `ot_is_activa`(
	`p_ot` int

) RETURNS varchar(50) CHARSET latin1
begin 
declare s varchar(50); 
declare c int;
set s='SI'; 
begin
select count(*) into c from ot_finalizada where ot=p_ot and fechafinalizo <> '' ;

if(c>0) then
	set s:='NO';
end if;

end;

return s; 
end
;;
DELIMITER ;
