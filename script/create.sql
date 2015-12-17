-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.6.17 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para iparsex
CREATE DATABASE IF NOT EXISTS `iparsex` /*!40100 DEFAULT CHARACTER SET utf16 */;
USE `iparsex`;


-- Volcando estructura para tabla iparsex.persona
CREATE TABLE IF NOT EXISTS `persona` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'identificador',
  `pass` varchar(250) NOT NULL DEFAULT '0' COMMENT 'password del usuario',
  `nombre` varchar(200) NOT NULL DEFAULT 'ANIMO' COMMENT 'nombre persona',
  `dni` varchar(9) DEFAULT 'ANIMO' COMMENT 'DNI persona, no es un NIF',
  `fecha_nacimiento` date DEFAULT NULL COMMENT 'Fecha nacimiento',
  `observaciones` text,
  `email` varchar(250) DEFAULT NULL COMMENT 'email de contacto',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf16 COMMENT='persona fisica';

-- Volcando datos para la tabla iparsex.persona: ~3 rows (aproximadamente)
DELETE FROM `persona`;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` (`id`, `pass`, `nombre`, `dni`, `fecha_nacimiento`, `observaciones`, `email`) VALUES
	(1, '123456', 'Pepe Biñuela Ajúiz', '11111111H', '1885-12-16', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum', 'pepe@gmailo.com'),
	(2, '123456', 'Sabrina Debora Salerno', 'ANIMO', '1969-08-18', 'Sus comienzos fueron como modelo y en televisión. De ahí en 1986, de la mano de un DJ, empezó su carrera musical con el éxito Sexy girl que solo tuvo una buena acogida en países de Europa, pero su éxito internacional más importante fue en 1987 con la canción Boys, con la que llega a ser conocida también en Sudamérica y Asia.', 'sabrina@tetasgordas.com'),
	(3, '123456', 'Samanta Fox', 'ANIMO', '1966-05-15', 'Samantha Karen Fox es una modelo, actriz y cantante inglesa que obtuvo gran fama a nivel mundial a finales de los años 80.', 'samata.fox@gmailo.com');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
