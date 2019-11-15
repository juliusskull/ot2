-- phpMyAdmin SQL Dump
-- version 4.0.10.11
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 18-05-2019 a las 21:21:53
-- Versión del servidor: 5.6.42
-- Versión de PHP: 5.2.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `ot`
--

DELIMITER $$
--
-- Funciones
--
CREATE DEFINER=`root`@`localhost` FUNCTION `GETEQUIPOTIPOID`(p_tipoid int,legajo int) RETURNS varchar(500) CHARSET latin1
begin 
declare s varchar(500);
set s='';
return s; 
end$$

CREATE DEFINER=`root`@`localhost` FUNCTION `operario_cuadrilla`(p_legajo int) RETURNS varchar(50) CHARSET latin1
begin 

return 'USUARIO'; 
end$$

CREATE DEFINER=`root`@`localhost` FUNCTION `OTCAPTAZNOMBRE`(CODIGO_CUADRILLA int) RETURNS varchar(500) CHARSET latin1
begin 
declare s varchar(500);
set s='USUARIO';
return s; 
end$$

CREATE DEFINER=`root`@`localhost` FUNCTION `OTCAPTAZNOMBREID`(legajo int) RETURNS varchar(500) CHARSET latin1
begin 
declare s varchar(500);
set s='USUARIO';
return s; 
end$$

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
end$$

CREATE DEFINER=`root`@`localhost` FUNCTION `otgetequipos`(`legajo` int,`ot` int) RETURNS int(11)
BEGIN
	

	RETURN 0;
END$$

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
END$$

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
end$$

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

end$$

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
end$$

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
end$$

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
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `configuracion`
--

CREATE TABLE IF NOT EXISTS `configuracion` (
  `version_db` int(11) NOT NULL,
  `version_fch_actualizacion` text,
  `fotos_ini` int(11) DEFAULT NULL,
  `fotos_fin` int(11) DEFAULT NULL,
  `metros_ini` int(11) DEFAULT NULL,
  `metros_fin` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Truncar tablas antes de insertar `configuracion`
--

TRUNCATE TABLE `configuracion`;
--
-- Volcado de datos para la tabla `configuracion`
--

INSERT INTO `configuracion` (`version_db`, `version_fch_actualizacion`, `fotos_ini`, `fotos_fin`, `metros_ini`, `metros_fin`) VALUES
(23, '2019-01-14 11:19:09', -1, -1, 100, 100);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `configuracion_usuario`
--

CREATE TABLE IF NOT EXISTS `configuracion_usuario` (
  `usuario` text,
  `modo_seguro` int(11) NOT NULL DEFAULT '0',
  `id_configuracion_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `intervalo` int(11) NOT NULL DEFAULT '5',
  PRIMARY KEY (`id_configuracion_usuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Truncar tablas antes de insertar `configuracion_usuario`
--

TRUNCATE TABLE `configuracion_usuario`;
--
-- Volcado de datos para la tabla `configuracion_usuario`
--

INSERT INTO `configuracion_usuario` (`usuario`, `modo_seguro`, `id_configuracion_usuario`, `intervalo`) VALUES
('usuario', 1, 1, 5),
('50928', 0, 2, 5),
('50926', 0, 3, 5),
('50842', 0, 4, 5);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `configuracion_view`
--
CREATE TABLE IF NOT EXISTS `configuracion_view` (
`version_db` int(11)
,`version_fch_actualizacion` text
,`fotos_ini` int(11)
,`fotos_fin` int(11)
,`metros_ini` int(11)
,`metros_fin` int(11)
,`modo_seguro` int(11)
,`usuario` text
,`intervalo` int(11)
);
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gasto`
--

CREATE TABLE IF NOT EXISTS `gasto` (
  `idGasto` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `monto` int(10) unsigned NOT NULL,
  `etiqueta` varchar(25) NOT NULL,
  `fecha` date NOT NULL,
  `descripcion` varchar(125) DEFAULT NULL,
  `usuario` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idGasto`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Truncar tablas antes de insertar `gasto`
--

TRUNCATE TABLE `gasto`;
--
-- Volcado de datos para la tabla `gasto`
--

INSERT INTO `gasto` (`idGasto`, `monto`, `etiqueta`, `fecha`, `descripcion`, `usuario`) VALUES
(1, 12, 'Transporte', '2015-07-01', 'Viaje al centro', NULL),
(2, 200, 'Comida', '2015-06-15', 'Mercado mensual', NULL),
(3, 100, 'Diversion', '2015-07-02', 'Salida a cine', NULL),
(4, 25, 'Transporte', '2015-07-04', 'Compra de gasolina', NULL),
(5, 5, 'Comida', '2015-07-06', 'Almuerzo de negocios', NULL),
(6, 1000, 'Comida', '2015-07-11', '', NULL),
(7, 1000, 'Comida', '2015-07-11', '', NULL),
(8, 1000, 'Comida', '2015-07-11', '', NULL),
(9, 1000, 'Comida', '2015-07-11', '', NULL),
(10, 1000, 'Comida', '2015-07-11', '', NULL),
(11, 1000, 'Comida', '2015-07-11', '', NULL),
(12, 1000, 'Comida', '2015-07-11', '', NULL),
(13, 1000, 'Comida', '2015-07-11', '', NULL),
(14, 10, 'Comida', '2015-07-11', '', NULL),
(15, 1000, 'Comida', '2015-07-11', '', NULL),
(16, 10, 'Comida', '2015-07-11', 'nnnn', NULL),
(17, 100, 'Comida', '2016-02-24', '', NULL),
(18, 10, 'Comida', '2017-06-15', 'x1', NULL),
(19, 10, 'Comida', '2015-07-11', '', NULL),
(20, 5, 'Comida', '2015-07-11', '', NULL),
(21, 8, 'Comida', '2015-07-11', '', NULL),
(22, 1, 'Comida', '2015-07-11', 'usuario', NULL),
(23, 14, 'Comida', '2015-07-11', 'prueba', NULL),
(24, 1, 'Comida', '2015-07-11', '2', NULL),
(25, 1, 'Comida', '2015-07-11', 'Q', 'julio@gmail');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `operario`
--

CREATE TABLE IF NOT EXISTS `operario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) DEFAULT NULL,
  `geren` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `imei` varchar(100) DEFAULT NULL,
  `fchalta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Truncar tablas antes de insertar `operario`
--

TRUNCATE TABLE `operario`;
--
-- Volcado de datos para la tabla `operario`
--

INSERT INTO `operario` (`id`, `nombre`, `geren`, `password`, `imei`, `fchalta`) VALUES
(12, 'Luis', 'jmolina', 'luis', '', '2019-04-02 11:57:04'),
(13, 'TEL01', 'jmolina', 'tel01', '', '2019-05-03 20:49:51');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ot`
--

CREATE TABLE IF NOT EXISTS `ot` (
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1021394 ;

--
-- Truncar tablas antes de insertar `ot`
--

TRUNCATE TABLE `ot`;
--
-- Volcado de datos para la tabla `ot`
--

INSERT INTO `ot` (`id`, `nro_ot`, `id_loc`, `localidad`, `zona`, `id_bar`, `barrio`, `id_cal`, `calle`, `altura`, `id_motivo`, `motivo`, `cod_empleado_asig`, `nombre_empleado_asig`, `cod_cuadrilla_asig`, `contratista_asig`, `fchalta`, `lat`, `lng`, `observacion`) VALUES
(1021389, 1021389, 0, '', '', 0, '', 0, '', '', 0, 'Tendido FO Telecom', 13, 'TEL01', 0, '', '2019-05-15 11:01:01', '-24.7918987', '-65.429728', ''),
(1021390, 1021390, 0, '', '', 0, '', 0, '', '', 0, 'Tendido FO Telecom METAN', 13, 'TEL01', 0, '', '0000-00-00 00:00:00', '-24.7918987', '-65.429728', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ot_finalizada`
--

CREATE TABLE IF NOT EXISTS `ot_finalizada` (
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=37 ;

--
-- Truncar tablas antes de insertar `ot_finalizada`
--

TRUNCATE TABLE `ot_finalizada`;
--
-- Volcado de datos para la tabla `ot_finalizada`
--

INSERT INTO `ot_finalizada` (`id`, `OT`, `fechainicio`, `fechafinalizo`, `idmotivofinaliza`, `lat`, `lng`, `altura`, `estado`, `t`, `fch`, `fchalta`, `LEGAJO`, `OBSERVACION`, `CODIGO_CUADRILLA`) VALUES
(35, 1021389, '15/05/2019 08:14:02', '', 0, '-24.776878333333336', '-65.424175', '', 'A', 'TEL01', '15/05/2019 08:14:02', '2019-05-15 11:14:06', '', NULL, NULL),
(36, 1021389, NULL, '17/05/2019 08:23:57', 5, '-24.776968333333336', '-65.42404166666667', '', 'F', 'TEL01', '17/05/2019 08:23:57', '2019-05-17 11:24:00', '', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ot_finalizada_valor`
--

CREATE TABLE IF NOT EXISTS `ot_finalizada_valor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `OT` int(11) DEFAULT NULL,
  `valor` varchar(25000) DEFAULT NULL,
  `fchalta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `paso_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=29 ;

--
-- Truncar tablas antes de insertar `ot_finalizada_valor`
--

TRUNCATE TABLE `ot_finalizada_valor`;
--
-- Volcado de datos para la tabla `ot_finalizada_valor`
--

INSERT INTO `ot_finalizada_valor` (`id`, `OT`, `valor`, `fchalta`, `paso_id`) VALUES
(1, 1021375, '1', '2019-02-28 00:14:41', 12),
(2, 1021373, '', '2019-02-28 00:47:55', 0),
(3, 1021373, '1', '2019-02-28 00:47:55', 7),
(4, 1021373, '', '2019-02-28 00:47:56', 0),
(5, 1021373, '1', '2019-02-28 00:47:56', 7),
(6, 1021263, '', '2019-03-01 01:31:39', 15),
(7, 1021374, '1', '2019-03-11 22:31:18', 1),
(8, 1021374, 'ok 23', '2019-03-11 22:31:18', 2),
(9, 1021374, 'no', '2019-03-11 22:31:18', 3),
(10, 1021374, '600', '2019-03-11 22:31:18', 4),
(11, 1021374, '1', '2019-03-11 22:31:18', 5),
(12, 1021374, '', '2019-03-11 22:31:18', 0),
(13, 1021374, '', '2019-03-11 22:31:18', 7),
(14, 1021374, '', '2019-03-11 22:31:18', 11),
(15, 1021374, '', '2019-03-11 22:31:18', 12),
(16, 1021374, '1', '2019-03-11 22:31:18', 13),
(17, 1021374, '', '2019-03-11 22:31:18', 15),
(18, 1021376, '10', '2019-03-21 14:03:22', 0),
(19, 1021376, '1', '2019-03-21 14:03:22', 19),
(20, 1021386, 'ok', '2019-04-21 22:46:45', 0),
(21, 1021389, '', '2019-05-17 11:24:00', 0),
(22, 1021389, '1', '2019-05-17 11:24:00', 0),
(23, 1021389, '', '2019-05-17 11:24:00', 68),
(24, 1021389, 'de vuelta 6', '2019-05-17 11:24:00', 0),
(25, 1021389, '', '2019-05-17 11:24:00', 70),
(26, 1021389, '', '2019-05-17 11:24:00', 0),
(27, 1021389, '', '2019-05-17 11:24:00', 72),
(28, 1021389, '', '2019-05-17 11:24:00', 73);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `ot_finalizada_valor_view`
--
CREATE TABLE IF NOT EXISTS `ot_finalizada_valor_view` (
`desc_campo` varchar(500)
,`tipo` varchar(500)
,`valor` varchar(25000)
,`fchalta` timestamp
,`OT` int(11)
,`REALIZADO` varchar(2)
);
-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `ot_finalizar_view`
--
CREATE TABLE IF NOT EXISTS `ot_finalizar_view` (
`ot` int(11)
,`FECHA_INICIO` varchar(20)
,`FECHA_FINALIZO` varchar(20)
,`MOT` int(1)
,`t` varchar(20)
,`LAT` varchar(250)
,`LON` varchar(30)
,`LAT_INI` varchar(50)
,`LON_INI` varchar(50)
,`direccion` varchar(500)
,`C` varchar(20)
,`OBS` text
,`ACTUAL` varchar(50)
,`MOT_OT` varchar(500)
,`equipos` char(0)
,`legajo` varchar(20)
,`movil` varchar(1)
);
-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `ot_view`
--
CREATE TABLE IF NOT EXISTS `ot_view` (
`id` int(11)
,`nro_ot` int(11)
,`id_loc` int(11)
,`id_bar` int(11)
,`barrio` varchar(100)
,`id_cal` int(11)
,`calle` varchar(100)
,`altura` varchar(100)
,`id_motivo` int(11)
,`motivo` varchar(100)
,`cod_empleado_asig` int(11)
,`nombre_empleado_asig` varchar(100)
,`cod_cuadrilla_asig` int(11)
,`template` varchar(11)
,`template_titulo` varchar(500)
,`contratista_asig` varchar(11)
,`fchalta` timestamp
,`localidad` varchar(100)
,`zona` varchar(100)
,`observacion` varchar(800)
);
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasos`
--

CREATE TABLE IF NOT EXISTS `pasos` (
  `id_paso` int(11) NOT NULL,
  `desc_campo` varchar(500) DEFAULT NULL,
  `tipo` varchar(500) DEFAULT NULL,
  `foto` int(11) DEFAULT NULL,
  `obligatorio` int(11) DEFAULT NULL,
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `ot` int(11) NOT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=93 ;

--
-- Truncar tablas antes de insertar `pasos`
--

TRUNCATE TABLE `pasos`;
--
-- Volcado de datos para la tabla `pasos`
--

INSERT INTO `pasos` (`id_paso`, `desc_campo`, `tipo`, `foto`, `obligatorio`, `_id`, `ot`) VALUES
(1, 'foto inicio', 'FOTO\r\n', 1, 1, 1, 0),
(2, 'estados de los postes\r\n', 'CADENA\r\n', 0, 0, 2, 0),
(3, 'cruces americanos\r\n', 'CADENA\r\n', 0, 1, 3, 0),
(4, 'distancia\r\n', 'MEDIDA\r\n', 0, 1, 4, 0),
(5, 'preuba', 'CADENA', 1, 1, 5, 0),
(0, 'Cargar Cosas', 'FOTO', 1, 0, 6, 1021373),
(7, 'cortar la caja', 'CADENA', 1, 0, 7, 1021373),
(11, 'ver distancias', 'CADENA', 1, 0, 11, 1021374),
(12, 'foto inicial antes de la apertura', 'FOTO', 1, 0, 12, 1021375),
(13, 'foto de inicio de trabajo', 'FOTO', 1, 0, 13, 1021372),
(15, 'foto obligatoria', 'FOTO', 1, 0, 15, 1021263),
(16, 'actividad 01', 'CADENA', 1, 0, 16, 1021152),
(0, 'prueba cantidad 1', 'CANTIDAD', 1, 0, 18, 1021376),
(19, 'prueba foto 1', 'FOTO', 1, 0, 19, 1021376),
(26, 'prueba para borrar', 'CADENA', 1, 0, 26, 1021378),
(27, 'prueba para borrar 2', 'CADENA', 1, 0, 27, 1021379),
(36, 'prueba 1-1', 'CADENA', 1, 0, 36, 1021377),
(37, 'prueba 1-1', 'CADENA', 1, 0, 37, 1021377),
(0, 'prueba 1-1', 'CADENA', 1, 0, 38, 1021377),
(39, 'prueba 1-1', 'CADENA', 1, 0, 39, 1021377),
(40, 'actividad 1', 'CADENA', 1, 0, 40, 1021377),
(41, 'actividad 2', 'CANTIDAD', 1, 0, 41, 1021377),
(42, 'ssfsdfsdf', 'CADENA', 0, 0, 42, 1021381),
(43, 'activiadad con observacion', 'CADENA', 0, 0, 43, 1021382),
(0, 'prueba con observacion', 'CADENA', 0, 0, 44, 1021383),
(45, 'prueba con observacion 2', 'CADENA', 0, 0, 45, 1021383),
(46, 'nueva prueba 01', 'CADENA', 0, 0, 46, 1021384),
(47, 'demarcacion de area, permisos', 'CADENA', 0, 0, 47, 1021385),
(48, 'Fotografia lugar de inicio, se debe ver señalizacion', 'FOTO', 1, 0, 48, 1021385),
(0, 'Fotografia lugar de finalizacion, se debe ver señalizacion', 'FOTO', 1, 0, 49, 1021385),
(0, 'Longitud zanjeo', 'CANTIDAD', 0, 0, 50, 1021385),
(51, 'Longitud tritubo', 'CANTIDAD', 0, 0, 51, 1021385),
(0, 'demarcacion de area, permisos', 'CADENA', 0, 0, 52, 1021386),
(53, 'Sacar foto inicio tarea', 'FOTO', 1, 0, 53, 1021387),
(0, 'Foto actividad', 'FOTO', 1, 0, 54, 1021387),
(0, 'Inicio secuencial tendido', 'CANTIDAD', 0, 0, 55, 1021387),
(56, 'Fin secuencial tendido', 'CANTIDAD', 0, 0, 56, 1021387),
(57, 'Mediciones con OTDR', 'CADENA', 0, 0, 57, 1021387),
(58, 'Sellado camara Grupo electrogeno', 'FOTO', 1, 0, 58, 1021388),
(0, 'Sellado camara Nº 2 y 3 con armado de marco. Enviar foto de cada camara', 'FOTO', 1, 0, 59, 1021388),
(60, 'Armar marco camara nº3, antes del ripio. Enmarcar y sellar camara 3 y 4. Preveer que se debe abrir esa camara para terminar de pasar el cable', 'FOTO', 1, 0, 60, 1021388),
(61, 'Colocar prisionero en caja exterior poder judicial', 'FOTO', 1, 0, 61, 1021388),
(62, 'Reparar manposteria en el techo poder judicial, antes de ingreso de fibra', 'FOTO', 1, 0, 62, 1021388),
(63, 'Colocar bajdeja para cambio de altura en Subsuelo', 'FOTO', 1, 0, 63, 1021388),
(64, 'repasar fijacion de fibra en bandeja poder judicial', 'FOTO', 1, 0, 64, 1021388),
(65, 'tarea nueva', 'CADENA', 0, 0, 65, 1021386),
(0, 'Foto secuencial inicio', 'FOTO', 1, 0, 66, 1021389),
(0, 'Foto secuencial fin', 'FOTO', 1, 0, 67, 1021389),
(68, 'Describir bufer y fibra a empalmar.', 'CADENA', 0, 0, 68, 1021389),
(0, 'Cantidad de pelos FO existente', 'CADENA', 0, 0, 69, 1021389),
(70, 'Foto empalme', 'FOTO', 1, 0, 70, 1021389),
(0, 'Ubicacion de empalme', 'CADENA', 0, 0, 71, 1021389),
(72, 'Foto rotulado Telecom, foto vista de rack', 'FOTO', 1, 0, 72, 1021389),
(73, 'Guardar lectura en ODF, indicar nombre de archivo', 'CADENA', 0, 0, 73, 1021389),
(75, 'sadf', 'CADENA', 0, 0, 75, 1021391),
(75, 'sadf', 'CADENA', 0, 0, 76, 1021392),
(75, 'sadf', 'CADENA', 0, 0, 77, 1021393),
(0, 'Foto secuencial inicio', 'FOTO', 1, 0, 78, 1021390),
(0, 'Foto secuencial fin', 'FOTO', 1, 0, 79, 1021390),
(0, 'Describir bufer y fibra a empalmar.', 'CADENA', 0, 0, 80, 1021390),
(0, 'Cantidad de pelos FO existente', 'CADENA', 0, 0, 81, 1021390),
(0, 'Foto empalme', 'FOTO', 1, 0, 82, 1021390),
(0, 'Ubicacion de empalme', 'CADENA', 0, 0, 83, 1021390),
(0, 'Foto rotulado Telecom, foto vista de rack', 'FOTO', 1, 0, 84, 1021390),
(0, 'Guardar lectura en ODF, indicar nombre de archivo', 'CADENA', 0, 0, 85, 1021390);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasosxtemplate`
--

CREATE TABLE IF NOT EXISTS `pasosxtemplate` (
  `id_pasosxtemplate` int(11) NOT NULL AUTO_INCREMENT,
  `id_paso` int(11) DEFAULT NULL,
  `id_template` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_pasosxtemplate`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Truncar tablas antes de insertar `pasosxtemplate`
--

TRUNCATE TABLE `pasosxtemplate`;
--
-- Volcado de datos para la tabla `pasosxtemplate`
--

INSERT INTO `pasosxtemplate` (`id_pasosxtemplate`, `id_paso`, `id_template`) VALUES
(1, 1, 2),
(2, 2, 2),
(3, 3, 2),
(4, 4, 2),
(5, 1, 3);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `pasosxtemplate_view`
--
CREATE TABLE IF NOT EXISTS `pasosxtemplate_view` (
`id_pasosxtemplate` int(11)
,`id_paso` int(11)
,`id_template` int(11)
,`titulo` varchar(500)
,`desc_campo` varchar(500)
);
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `fchalta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `fchcad` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `modulo` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Truncar tablas antes de insertar `roles`
--

TRUNCATE TABLE `roles`;
--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `nombre`, `fchalta`, `fchcad`, `modulo`) VALUES
(1, 'admin', '2019-01-14 14:13:37', '0000-00-00 00:00:00', 'OT'),
(2, 'operario', '2019-01-14 14:13:37', '0000-00-00 00:00:00', 'OT'),
(3, 'inspector', '2019-01-14 14:13:37', '0000-00-00 00:00:00', 'OT'),
(4, 'super-inspector', '2019-01-14 14:13:37', '0000-00-00 00:00:00', 'OT');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sincronizar`
--

CREATE TABLE IF NOT EXISTS `sincronizar` (
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=564 ;

--
-- Truncar tablas antes de insertar `sincronizar`
--

TRUNCATE TABLE `sincronizar`;
--
-- Volcado de datos para la tabla `sincronizar`
--

INSERT INTO `sincronizar` (`id`, `envio`, `tipo`, `valor`, `lat`, `lng`, `fchalta`, `usuario`, `imei`, `precision`, `gps`, `red`, `version`, `aplicacion`, `actualizada`) VALUES
(1, '.', 'R', '.', NULL, NULL, '2019-01-24 17:46:12', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(15, '.', 'R', '.', NULL, NULL, '2019-02-14 23:51:08', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(16, '.', 'R', '.', NULL, NULL, '2019-02-14 23:54:22', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(501, '', 'R', '', '0.0', '0.0', '2019-04-02 11:57:20', 'Luis', '355519073020059', NULL, 0, 0, NULL, 'ordenes', NULL),
(502, '', 'FOTOS', '{"prueba": "prueba de conexion"  }', '', '', '2019-04-02 12:00:10', 'Luis', '355519073020059', NULL, 0, 0, NULL, 'ordenes', NULL),
(503, '', 'FOTOS', '{"prueba": "prueba de conexion"  }', '', '', '2019-04-02 12:00:13', 'Luis', '355519073020059', NULL, 0, 0, NULL, 'ordenes', NULL),
(504, '', 'FOTOS', '{"prueba": "prueba de conexion"  }', '', '', '2019-04-02 12:00:14', 'Luis', '355519073020059', NULL, 0, 0, NULL, 'ordenes', NULL),
(505, '', 'INICIO_DE_DIA', '{"fecha": "16-04-2019 08:42:50" }', '-24.78863666666667', '-65.41496000000001', '2019-04-16 11:43:10', 'Luis', '353108080913514', NULL, 0, 0, NULL, 'ordenes', NULL),
(506, '', 'R', '', '-24.78863666666667', '-65.41496000000001', '2019-04-16 11:43:11', 'Luis', '353108080913514', NULL, 0, 0, NULL, 'ordenes', NULL),
(507, '', 'R', '', '-24.788543333333337', '-65.41482666666667', '2019-04-16 11:57:36', 'Luis', '353108080913514', NULL, 0, 0, NULL, 'ordenes', NULL),
(508, '', 'INICIO_DE_DIA', '{"fecha": "21-04-2019 19:41:11" }', '0.0', '0.0', '2019-04-21 22:41:37', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(509, '', 'R', '', '0.0', '0.0', '2019-04-21 22:41:38', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(510, '', 'FOTOS', '{"prueba": "prueba de conexion"  }', '', '', '2019-04-21 22:45:16', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(511, '', 'FOTOS', '{"prueba": "prueba de conexion"  }', '', '', '2019-04-21 22:45:21', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(512, '1021386', 'OT', '{"altura":"","codigo_cuadrilla":0,"estado":"A","fch":"21/04/2019 19:46:10","fchalta":"","fechafinalizo":"","fechainicio":"21/04/2019 19:46:10","id":0,"id_inm":0,"id_seg":0,"id_txc":0,"idmotivofinaliza":0,"lat":"-24.776819999999997","legajo":0,"lng":"-65.42400333333333","nro_form":0,"nro_sec":0,"observacion":"","ot":1021386,"t":"Luis","_id":7}', '-24.776819999999997', '-65.42400333333333', '2019-04-21 22:46:33', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(513, '1021386', 'OT', '{"altura":"","codigo_cuadrilla":0,"estado":"F","fch":"21/04/2019 19:46:22","fchalta":"","fechafinalizo":"21/04/2019 19:46:22","id":0,"id_inm":0,"id_seg":0,"id_txc":0,"idmotivofinaliza":5,"lat":"-24.776855","legajo":0,"lng":"-65.42399","nro_form":0,"nro_sec":0,"observacion":"","ot":1021386,"t":"Luis","_id":7}', '-24.776855', '-65.42399', '2019-04-21 22:46:45', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(514, '1021386', 'PASOS', '{"data":[{"desc_campo":"demarcacion de area, permisos","foto":0,"id_paso":0,"obligatorio":0,"ot":0,"tipo":"CADENA","valor":"ok","_id":52}]}', '-24.776855', '-65.42399', '2019-04-21 22:46:45', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(515, '', 'FOTOS', '{"prueba": "prueba de conexion"  }', '', '', '2019-04-21 22:50:10', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(516, '', 'FOTOS', '{"prueba": "prueba de conexion"  }', '', '', '2019-04-21 22:50:11', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(517, '', 'R', '', '-24.776901666666664', '-65.42441666666667', '2019-04-21 22:50:27', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(518, '', 'R', '', '-24.77689833333333', '-65.42407', '2019-04-21 22:51:21', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(519, '', 'R', '', '-24.776905000000003', '-65.42403833333333', '2019-04-21 22:51:52', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(520, '', 'R', '', '-24.776864999999997', '-65.42406333333334', '2019-04-21 22:52:10', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(521, '', 'R', '', '-24.77676166666667', '-65.42427666666667', '2019-04-21 22:52:24', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(522, '', 'R', '', '-24.777113333333332', '-65.4259', '2019-04-21 22:52:43', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(523, '', 'R', '', '0.0', '0.0', '2019-04-28 12:10:15', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(524, '', 'R', '', '-24.784135', '-65.43059666666666', '2019-04-28 12:11:42', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(525, '', 'INICIO_DE_DIA', '{"fecha": "03-05-2019 17:51:54" }', '-24.776884360882292', '-65.42403474829928', '2019-05-03 20:51:58', 'TEL01', '863451033775074', NULL, 0, 0, NULL, 'ordenes', NULL),
(526, '', 'R', '', '-24.776884360882292', '-65.42403474829928', '2019-05-03 20:52:02', 'TEL01', '863451033775074', NULL, 0, 0, NULL, 'ordenes', NULL),
(527, '', 'R', '', '-24.758651666666665', '-65.35304666666666', '2019-05-04 11:05:27', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(528, '', 'R', '', '-24.7808538594645', '-65.42549308959119', '2019-05-07 20:03:21', 'TEL01', '863451033775074', NULL, 0, 0, NULL, 'ordenes', NULL),
(529, '', 'R', '', '-24.802746666666668', '-65.44176166666666', '2019-05-07 20:06:48', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(530, '', 'R', '', '-24.776758333333337', '-65.423485', '2019-05-08 11:03:03', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(531, '', 'R', '', '-25.499531666666666', '-64.97070666666667', '2019-05-09 00:19:45', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(532, '', 'R', '', '-25.499531666666666', '-64.97070666666667', '2019-05-09 00:19:48', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(533, '', 'R', '', '-25.492736666666662', '-64.97616833333333', '2019-05-09 01:32:17', 'Luis', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(534, '', 'R', '', '-24.77689666666667', '-65.424195', '2019-05-15 11:13:41', 'TEL01', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(535, '1021389', 'OT', '{"altura":"","codigo_cuadrilla":0,"estado":"A","fch":"15/05/2019 08:14:02","fchalta":"","fechafinalizo":"","fechainicio":"15/05/2019 08:14:02","id":0,"id_inm":0,"id_seg":0,"id_txc":0,"idmotivofinaliza":0,"lat":"-24.776878333333336","legajo":0,"lng":"-65.424175","nro_form":0,"nro_sec":0,"observacion":"","ot":1021389,"t":"TEL01","_id":75}', '-24.776878333333336', '-65.424175', '2019-05-15 11:14:06', 'TEL01', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(536, '', 'FOTOS', '{"fotos": "/storage/emulated/0/ot_img/1021389_20190515091714_TEL01_.jpg", "observacion":"Error archivo borrado"  }', '-24.845881666666667', '-65.44427666666667', '2019-05-15 19:46:43', 'TEL01', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(537, '1021389', 'FOTOS', '{"fotos": "1021389_20190515091758_TEL01_.jpg", "observacion":"Enviado"  }', '-24.845998333333334', '-65.44436166666667', '2019-05-15 19:46:43', 'TEL01', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(538, '', 'R', '', '-24.853395', '-65.436515', '2019-05-15 19:46:44', 'TEL01', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(539, '', 'R', '', '-24.7769', '-65.42405333333333', '2019-05-16 10:10:23', 'TEL01', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(540, '1021389', 'FOTOS', '{"fotos": "1021389_20190516130452_TEL01_.jpg", "observacion":"Enviado"  }', '-25.499248333333338', '-64.97054499999999', '2019-05-17 11:22:19', 'TEL01', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(541, '1021389', 'FOTOS', '{"fotos": "1021389_20190516130546_TEL01_.jpg", "observacion":"Enviado"  }', '-25.499258333333334', '-64.97053333333334', '2019-05-17 11:22:20', 'TEL01', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(542, '1021389', 'FOTOS', '{"fotos": "1021389_20190516130617_TEL01_.jpg", "observacion":"Enviado"  }', '-25.49925166666667', '-64.970535', '2019-05-17 11:22:20', 'TEL01', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(543, '1021389', 'FOTOS', '{"fotos": "1021389_20190516131811_TEL01_.jpg", "observacion":"Enviado"  }', '-25.499573333333334', '-64.97070666666667', '2019-05-17 11:22:20', 'TEL01', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(544, '1021389', 'FOTOS', '{"fotos": "1021389_20190516131830_TEL01_.jpg", "observacion":"Enviado"  }', '-25.499588333333335', '-64.97065166666667', '2019-05-17 11:22:20', 'TEL01', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(545, '1021389', 'FOTOS', '{"fotos": "1021389_20190516132314_TEL01_.jpg", "observacion":"Enviado"  }', '-25.497754999999998', '-64.971975', '2019-05-17 11:22:20', 'TEL01', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(546, '1021389', 'FOTOS', '{"fotos": "1021389_20190516132440_TEL01_.jpg", "observacion":"Enviado"  }', '-25.497865', '-64.972', '2019-05-17 11:22:20', 'TEL01', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(547, '', 'R', '', '-24.776988333333332', '-65.42399999999999', '2019-05-17 11:23:07', 'TEL01', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(548, '', 'FOTOS', '{"fotos": "/storage/emulated/0/ot_img/1021389_20190517082332_TEL01_.jpg", "observacion":"Error archivo borrado"  }', '-24.777008333333335', '-65.42405333333333', '2019-05-17 11:24:00', 'TEL01', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(549, '1021389', 'OT', '{"altura":"","codigo_cuadrilla":0,"estado":"F","fch":"17/05/2019 08:23:57","fchalta":"","fechafinalizo":"17/05/2019 08:23:57","id":0,"id_inm":0,"id_seg":0,"id_txc":0,"idmotivofinaliza":5,"lat":"-24.776968333333336","legajo":0,"lng":"-65.42404166666667","nro_form":0,"nro_sec":0,"observacion":"","ot":1021389,"t":"TEL01","_id":75}', '-24.776968333333336', '-65.42404166666667', '2019-05-17 11:24:00', 'TEL01', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(550, '1021389', 'PASOS', '{"data":[{"desc_campo":"Foto secuencial inicio","foto":1,"id_paso":0,"obligatorio":0,"ot":0,"tipo":"FOTO","valor":"","_id":66},{"desc_campo":"Foto secuencial fin","foto":1,"id_paso":0,"obligatorio":0,"ot":0,"tipo":"FOTO","valor":"1","_id":67},{"desc_campo":"Describir bufer y fibra a empalmar.","foto":0,"id_paso":68,"obligatorio":0,"ot":0,"tipo":"CADENA","valor":"","_id":68},{"desc_campo":"Cantidad de pelos FO existente","foto":0,"id_paso":0,"obligatorio":0,"ot":0,"tipo":"CADENA","valor":"de vuelta 6","_id":69},{"desc_campo":"Foto empalme","foto":1,"id_paso":70,"obligatorio":0,"ot":0,"tipo":"FOTO","valor":"","_id":70},{"desc_campo":"Ubicacion de empalme","foto":0,"id_paso":0,"obligatorio":0,"ot":0,"tipo":"CADENA","valor":"","_id":71},{"desc_campo":"Foto rotulado Telecom, foto vista de rack","foto":1,"id_paso":72,"obligatorio":0,"ot":0,"tipo":"FOTO","valor":"","_id":72},{"desc_campo":"Guardar lectura en ODF, indicar nombre de archivo","foto":0,"id_paso":73,"obligatorio":0,"ot":0,"tipo":"CADENA","valor":"","_id":73}]}', '-24.776968333333336', '-65.42404166666667', '2019-05-17 11:24:00', 'TEL01', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(551, '', 'R', '', '-24.84588666666667', '-65.44451333333333', '2019-05-17 12:30:20', 'TEL01', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL),
(552, '', 'R', '', '-24.839223333333333', '-65.47552833333333', '2019-05-17 14:22:39', 'TEL01', '359978090556790', NULL, 0, 0, NULL, 'ordenes', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `template`
--

CREATE TABLE IF NOT EXISTS `template` (
  `id_template` int(11) NOT NULL,
  `desc_template` varchar(500) DEFAULT NULL,
  `observacion` varchar(1500) DEFAULT NULL,
  `titulo` varchar(500) DEFAULT NULL,
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Truncar tablas antes de insertar `template`
--

TRUNCATE TABLE `template`;
--
-- Volcado de datos para la tabla `template`
--

INSERT INTO `template` (`id_template`, `desc_template`, `observacion`, `titulo`, `_id`) VALUES
(1, 'NORMAL', 'NORMAL', 'NORMAL', 1),
(2, 'Obra N\r\nNombre Contacto\r\nTelefono Contacto\r\nOtros', 'Plano\r\nPermisos\r\nPerro\r\nCredenciales\r\nLlave central', 'Relevamiento Tendido', 2),
(3, 'prueba23', 'preuba 23', 'prueba3', 3);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `template_view`
--
CREATE TABLE IF NOT EXISTS `template_view` (
`id` int(11)
,`motivo` varchar(100)
,`cantidad_pasos` bigint(21)
);
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Truncar tablas antes de insertar `usuarios`
--

TRUNCATE TABLE `usuarios`;
--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `usuario`, `email`, `password`, `rol_id`, `fchalta`, `fchultimoacceso`, `fchcad`, `geren`, `modulo`) VALUES
(1, 'usuario', 'info@prueba.com.ar', '9250e222c4c71f0c58d4c54b50a880a312e9f9fed55d5c3aa0b0e860ded99165', 3, '2019-01-14 14:15:14', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 'S', 'ot'),
(2, '50928', 'info@prueba.com.ar', '9250e222c4c71f0c58d4c54b50a880a312e9f9fed55d5c3aa0b0e860ded99165', 2, '2019-01-14 14:15:14', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 'S', 'ot'),
(3, '50926', 'info@prueba.com.ar', '9250e222c4c71f0c58d4c54b50a880a312e9f9fed55d5c3aa0b0e860ded99165', 2, '2019-01-14 14:15:14', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 'S', 'ot'),
(4, '50842', 'info@prueba.com.ar', '9250e222c4c71f0c58d4c54b50a880a312e9f9fed55d5c3aa0b0e860ded99165', 2, '2019-01-14 14:15:14', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 'S', 'ot'),
(5, 'admin', 'info@prueba.com.ar', '123456', 1, '2019-01-14 14:15:14', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 'S', 'admin'),
(6, 'jmolina', 'info@prueba.com.ar', '123456', 1, '2019-03-16 23:33:28', '0000-00-00 00:00:00', '0000-00-00 00:00:00', 'S', 'admin');

-- --------------------------------------------------------

--
-- Estructura para la vista `configuracion_view`
--
DROP TABLE IF EXISTS `configuracion_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `configuracion_view` AS select `c`.`version_db` AS `version_db`,`c`.`version_fch_actualizacion` AS `version_fch_actualizacion`,`c`.`fotos_ini` AS `fotos_ini`,`c`.`fotos_fin` AS `fotos_fin`,`c`.`metros_ini` AS `metros_ini`,`c`.`metros_fin` AS `metros_fin`,`cxu`.`modo_seguro` AS `modo_seguro`,`cxu`.`usuario` AS `usuario`,`cxu`.`intervalo` AS `intervalo` from (`configuracion` `c` join `configuracion_usuario` `cxu`);

-- --------------------------------------------------------

--
-- Estructura para la vista `ot_finalizada_valor_view`
--
DROP TABLE IF EXISTS `ot_finalizada_valor_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ot_finalizada_valor_view` AS select `p`.`desc_campo` AS `desc_campo`,`p`.`tipo` AS `tipo`,`f`.`valor` AS `valor`,`f`.`fchalta` AS `fchalta`,`p`.`ot` AS `OT`,if(isnull(`f`.`valor`),'NO','SI') AS `REALIZADO` from (`pasos` `p` left join `ot_finalizada_valor` `f` on(((`p`.`ot` = `f`.`OT`) and (`p`.`id_paso` = `f`.`paso_id`))));

-- --------------------------------------------------------

--
-- Estructura para la vista `ot_finalizar_view`
--
DROP TABLE IF EXISTS `ot_finalizar_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ot_finalizar_view` AS select distinct `otf`.`OT` AS `ot`,(select max(ifnull(`ot_finalizada`.`fechainicio`,'')) from `ot_finalizada` where (`otf`.`OT` = `ot_finalizada`.`OT`)) AS `FECHA_INICIO`,(select max(ifnull(`ot_finalizada`.`fechafinalizo`,'')) from `ot_finalizada` where (`otf`.`OT` = `ot_finalizada`.`OT`)) AS `FECHA_FINALIZO`,1 AS `MOT`,`otf`.`t` AS `t`,`otgetlatfin`(`otf`.`OT`,`otf`.`t`) AS `LAT`,`otgetlngfin`(`otf`.`OT`,`otf`.`t`) AS `LON`,`otgetlatIni`(`otf`.`OT`,`otf`.`t`) AS `LAT_INI`,`otgetlngIni`(`otf`.`OT`,`otf`.`t`) AS `LON_INI`,`OTGETDIRECCION`(`otf`.`OT`) AS `direccion`,`otf`.`t` AS `C`,(select `ot_finalizada`.`OBSERVACION` from `ot_finalizada` where (`otf`.`OT` = `ot_finalizada`.`OT`) limit 1) AS `OBS`,`ot_is_activa`(`otf`.`OT`) AS `ACTUAL`,`OTGETMOTIVO`(`otf`.`OT`) AS `MOT_OT`,'' AS `equipos`,`otf`.`t` AS `legajo`,'0' AS `movil` from `ot_finalizada` `otf` group by `otf`.`OT`,`otf`.`idmotivofinaliza`,`otf`.`t`;

-- --------------------------------------------------------

--
-- Estructura para la vista `ot_view`
--
DROP TABLE IF EXISTS `ot_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ot_view` AS select `id` AS `id`,`nro_ot` AS `nro_ot`,`id_loc` AS `id_loc`,`id_bar` AS `id_bar`,`barrio` AS `barrio`,`id_cal` AS `id_cal`,`calle` AS `calle`,`altura` AS `altura`,`id_motivo` AS `id_motivo`,`motivo` AS `motivo`,`cod_empleado_asig` AS `cod_empleado_asig`,`nombre_empleado_asig` AS `nombre_empleado_asig`,`cod_cuadrilla_asig` AS `cod_cuadrilla_asig`,`contratista_asig` AS `template`,`t`.`titulo` AS `template_titulo`,`contratista_asig` AS `contratista_asig`,`fchalta` AS `fchalta`,`localidad` AS `localidad`,`zona` AS `zona`,`observacion` AS `observacion` from (`ot` left join `template` `t` on((`contratista_asig` = `t`.`id_template`))) where (not(exists(select 1 from `ot_finalizada` where ((`ot_finalizada`.`OT` = `nro_ot`) and (`ot_finalizada`.`fechafinalizo` is not null)))));

-- --------------------------------------------------------

--
-- Estructura para la vista `pasosxtemplate_view`
--
DROP TABLE IF EXISTS `pasosxtemplate_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `pasosxtemplate_view` AS select `pxt`.`id_pasosxtemplate` AS `id_pasosxtemplate`,`pxt`.`id_paso` AS `id_paso`,`pxt`.`id_template` AS `id_template`,`t`.`titulo` AS `titulo`,`p`.`desc_campo` AS `desc_campo` from ((`pasosxtemplate` `pxt` join `template` `t`) join `pasos` `p`) where ((`pxt`.`id_template` = `t`.`id_template`) and (`pxt`.`id_paso` = `p`.`id_paso`));

-- --------------------------------------------------------

--
-- Estructura para la vista `template_view`
--
DROP TABLE IF EXISTS `template_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `template_view` AS select `nro_ot` AS `id`,`motivo` AS `motivo`,(select count(0) from `pasos` where (`pasos`.`ot` = `nro_ot`)) AS `cantidad_pasos` from `ot` where exists(select 1 from `pasos` where (`pasos`.`ot` = `nro_ot`));

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
