CREATE TABLE `NewTable` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`desc_campo`  varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL ,
`tipo`  varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL ,
`id_grupo`  int(11) NULL DEFAULT NULL ,
`session`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci
AUTO_INCREMENT=1
ROW_FORMAT=COMPACT
INSERT INTO `pasos_aux` VALUES (1, 'paso1', 'cadena', 1, NULL);
INSERT INTO `pasos_aux` VALUES (2, 'paso2', 'cadena', 1, NULL);
INSERT INTO `pasos_aux` VALUES (3, 'paso3', 'cadena', 1, NULL);
INSERT INTO `pasos_aux` VALUES (4, 'paso1', 'cadena', 1, NULL);
INSERT INTO `pasos_aux` VALUES (5, 'paso2', 'cadena', 1, NULL);
INSERT INTO `pasos_aux` VALUES (6, 'paso3', 'cadena', 1, NULL);
