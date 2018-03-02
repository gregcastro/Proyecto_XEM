-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.5.24-log - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para xem
CREATE DATABASE IF NOT EXISTS `xem` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `xem`;

-- Volcando estructura para procedimiento xem.sp_action_create
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_action_create`(

IN _action_uuid varchar(45),
IN _action_change_claymore_version tinyint(4),
IN _action_change_start_bat tinyint(4),
IN _action_download_claymore_version tinyint(4),
IN _action_restart_claymore tinyint(4),
IN _action_reset_rig tinyint(4),
IN _rig_uuid varchar(45)


)
BEGIN
INSERT INTO `xem_tbl_action`
(
`action_uuid`,
`action_change_claymore_version`,
`action_change_start_bat`,
`action_download_claymore_version`,
`action_restart_claymore`,
`action_reset_rig`,
`rig_uuid`)
VALUES
(
_action_uuid,
_action_change_claymore_version,
_action_change_start_bat,
_action_download_claymore_version,
_action_restart_claymore,
_action_reset_rig,
_rig_uuid);


END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_action_delete
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_action_delete`(IN _action_uuid varchar(45))
BEGIN
DELETE FROM `xem_tbl_action`
WHERE `action_uuid` = _action_uuid;

END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_action_delete_by_rig
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_action_delete_by_rig`(
IN _rig_uuid varchar(45))
BEGIN
DELETE FROM `xem_tbl_action`
WHERE `rig_uuid` = _rig_uuid;

END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_action_list
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_action_list`()
BEGIN
SELECT `action_id`,
    `action_uuid`,
    `action_change_claymore_version`,
    `action_change_start_bat`,
    `action_download_claymore_version`,
    `action_restart_claymore`,
    `action_reset_rig`,
    `rig_uuid`
FROM `xem_tbl_action`;

END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_action_read
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_action_read`(
IN _action_uuid varchar(45)
)
BEGIN
SELECT `action_id`,
    `action_uuid`,
    `action_change_claymore_version`,
    `action_change_start_bat`,
    `action_download_claymore_version`,
    `action_restart_claymore`,
	`action_reset_rig`,
    `rig_uuid`
FROM `xem_tbl_action`
where `action_uuid` = _action_uuid
;
END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_action_read_by_rig
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_action_read_by_rig`(
in _rig_uuid varchar(45))
BEGIN
SELECT a.action_id,
    a.action_uuid,
    a.action_change_claymore_version,
    a.action_change_start_bat,
    a.action_download_claymore_version,
    a.action_restart_claymore,
    a.rig_uuid,
    u.user_email,
    r.user_email,
    r.rig_claymore_version,
    r.rig_start_bat_data,
    r.rig_reseter_number
FROM 
	xem_tbl_action a
INNER JOIN
	xem_tbl_rig r
ON
	r.rig_uuid = a.rig_uuid
INNER JOIN
	xem_tbl_user u
ON
	u.user_email = r.user_email
WHERE
	a.rig_uuid	= _rig_uuid;
END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_action_reset_by_rig
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_action_reset_by_rig`(
in _rig_uuid varchar(45))
BEGIN
SELECT 
    a.rig_uuid,
    u.user_email,
    r.user_email,
    r.rig_claymore_version,
    u.user_start_bat_data,
	a.action_reset_rig,
    r.rig_reseter_number
FROM 
	xem_tbl_action a
INNER JOIN
	xem_tbl_rig r
ON
	r.rig_uuid = a.rig_uuid
INNER JOIN
	xem_tbl_user u
ON
	u.user_email = r.user_email
WHERE
	a.rig_uuid	= _rig_uuid;
END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_action_rig_reset_values
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_action_rig_reset_values`(
IN _rig_uuid varchar(45)
)
BEGIN

UPDATE `xem_tbl_action`
SET
`action_change_claymore_version` = 0,
`action_change_start_bat` = 0,
`action_download_claymore_version` = 0,
`action_restart_claymore` = 0,
`action_reset_rig` = 0
WHERE `rig_uuid` = _rig_uuid;
END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_action_update
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_action_update`(

IN _action_uuid varchar(45),
IN _action_change_claymore_version tinyint(4),
IN _action_change_start_bat tinyint(4),
IN _action_download_claymore_version tinyint(4),
IN _action_restart_claymore tinyint(4),
IN _action_reset_rig tinyint(4),
IN _rig_uuid varchar(45)
)
BEGIN
UPDATE `xem_tbl_action`
SET
`action_uuid` = _action_uuid,
`action_change_claymore_version` = _action_change_claymore_version,
`action_change_start_bat` = _action_change_start_bat,
`action_download_claymore_version` = _action_download_claymore_version,
`action_restart_claymore` = _action_restart_claymore,
`action_reset_rig`=_action_reset_rig,
`rig_uuid` = _rig_uuid
WHERE `action_uuid` = _action_uuid;

END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_location_rig_create
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_location_rig_create`(
IN _location_name varchar(45),
IN _user_email varchar(45),
IN _rig_uuid varchar(45)
)
BEGIN
INSERT INTO `xem_tbl_location_rig`
(
`location_create_date`,
`location_name`,
`user_email`,
`rig_uuid`)
VALUES
(
now(),
_location_name,
_user_email,
_rig_uuid);
END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_location_rig_delete
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_location_rig_delete`(
IN _rig_uuid varchar(45))
BEGIN
delete 
FROM `xem_tbl_location_rig`

where `rig_uuid` = _rig_uuid ;

END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_location_rig_list
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_location_rig_list`(
IN _user_email varchar(45))
BEGIN
SELECT `location_id`,
    `location_name`,
    `user_email`,
    `rig_uuid`
FROM `xem_tbl_location_rig`

where `user_email` = _user_email ;

END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_location_rig_read
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_location_rig_read`(
IN _rig_uuid varchar(45))
BEGIN
SELECT `location_id`,
    `location_name`,
    `user_email`,
    `rig_uuid`
FROM `xem_tbl_location_rig`

where `rig_uuid` = _rig_uuid ;

END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_location_rig_update
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_location_rig_update`(
_location_name varchar(45),
_user_email varchar(45),
_rig_uuid varchar(45))
BEGIN
UPDATE `xem_tbl_location_rig`
SET
`location_name` = _location_name
WHERE `rig_uuid` = _rig_uuid;

END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_reset_create
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_reset_create`(
IN _rig_reseter_number VARCHAR(45),
IN _user_email VARCHAR(45),
IN _rig_uuid VARCHAR(45)
)
BEGIN
INSERT INTO `xem_tbl_reset`
(
`rig_reseter_number`,
`reset_create_datetime`,
`user_email`,
`rig_uuid`)
VALUES
(_rig_reseter_number,
now(),
_user_email,
_rig_uuid);
END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_reset_delete
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_reset_delete`(
IN _rig_uuid varchar(45)
)
BEGIN
DELETE FROM `xem_tbl_reset`
WHERE `rig_uuid` = _rig_uuid;
END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_reset_list
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_reset_list`()
BEGIN
SELECT `reset_id`,
    `rig_reseter_number`,
    `reset_create_datetime`,
    `user_email`,
    `rig_uuid`
FROM `xem_tbl_reset`;

END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_reset_read
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_reset_read`(
IN _rig_reseter_number varchar(45) 
)
BEGIN
SELECT `reset_id`,
    `rig_reseter_number`,
    `reset_create_datetime`,
    `user_email`,
    `rig_uuid`
FROM `xem_tbl_reset`
where `rig_reseter_number` = _rig_reseter_number ;

END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_reset_update
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_reset_update`(
IN _rig_reseter_number varchar(45),
IN _user_email varchar(45),
IN _rig_uuid varchar(45)
)
BEGIN
UPDATE `xem_tbl_reset`
SET
`rig_reseter_number` = _rig_reseter_number,
`user_email` = _user_email,
`rig_uuid` =_rig_uuid
WHERE `rig_uuid` = _rig_uuid;
END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_rig_create
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_rig_create`(
IN _rig_uuid varchar(45),
IN _rig_name varchar(45),
IN _user_email varchar(45),
IN _rig_lan_ip varchar(15),
IN _rig_claymore_version varchar(45),
IN _rig_time_up varchar(45),
IN _rig_reset_today varchar(45),
IN _rig_claymore_reset_today varchar(45),
IN _rig_gpu_info_eth varchar(1000),
IN _rig_gpu_second_coin varchar(45),
IN _rig_gpu_info_second_coin varchar(1000),
IN _rig_start_bat_data varchar(1000),
IN _location_uuid varchar(45),
IN _rig_reseter_number varchar(2)

)
BEGIN
INSERT INTO `xem_tbl_rig`
(`rig_uuid`,
`rig_create_datetime`,
`rig_name`,
`user_email`,
`rig_lan_ip`,
`rig_claymore_version`,
`rig_time_up`,
`rig_reset_today`,
`rig_claymore_reset_today`,
`rig_gpu_info_eth`,
`rig_gpu_second_coin`,
`rig_gpu_info_second_coin`,
`rig_start_bat_data`,
`location_uuid`,
`rig_reseter_number`)
VALUES
(_rig_uuid,
now(),
_rig_name,
_user_email,
_rig_lan_ip,
_rig_claymore_version,
_rig_time_up,
_rig_reset_today,
_rig_claymore_reset_today,
_rig_gpu_info_eth,
_rig_gpu_second_coin,
_rig_gpu_info_second_coin,
_rig_start_bat_data,
_location_uuid,
_rig_reseter_number
);

END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_rig_delete
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_rig_delete`(in _rig_uuid VARCHAR(45))
BEGIN
DELETE
FROM `xem_tbl_rig`
WHERE `rig_uuid` =_rig_uuid
;

END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_rig_list
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_rig_list`()
BEGIN
SELECT `rig_id`,
    `rig_uuid`,
    `rig_create_datetime`,
    `rig_name`,
    `user_email`,
    `rig_lan_ip`,
    `rig_claymore_version`,
    `rig_time_up`,
    `rig_reset_today`,
    `rig_claymore_reset_today`,
    `rig_gpu_info_eth`,
    `rig_gpu_second_coin`,
    `rig_gpu_info_second_coin`,
    `rig_start_bat_data`,
    `location_uuid`,
    `rig_reseter_number`
FROM `xem_tbl_rig`
;

END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_rig_list_by_user
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_rig_list_by_user`(
IN _user_email varchar(45)
)
BEGIN
SELECT `rig_id`,
    `rig_uuid`,
    `rig_create_datetime`,
    `rig_name`,
    `user_email`,
    `rig_lan_ip`,
    `rig_claymore_version`,
    `rig_time_up`,
    `rig_reset_today`,
    `rig_claymore_reset_today`,
    `rig_gpu_info_eth`,
    `rig_gpu_second_coin`,
    `rig_gpu_info_second_coin`,
    `rig_start_bat_data`,
    `location_uuid`,
    `rig_reseter_number`
FROM `xem_tbl_rig`
WHERE `user_email` = _user_email;

END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_rig_read
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_rig_read`(in _rig_uuid VARCHAR(45))
BEGIN
SELECT `rig_id`,
    `rig_uuid`,
    `rig_create_datetime`,
     `rig_name`,
    `user_email`,
    `rig_lan_ip`,
    `rig_claymore_version`,
    `rig_time_up`,
    `rig_reset_today`,
    `rig_claymore_reset_today`,
    `rig_gpu_info_eth`,
    `rig_gpu_second_coin`,
    `rig_gpu_info_second_coin`,
    `rig_start_bat_data`,
    `location_uuid`,
    `rig_reseter_number`
FROM `xem_tbl_rig`
WHERE `rig_uuid` =_rig_uuid
;

END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_rig_read_by_name_and_email
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_rig_read_by_name_and_email`(
in _user_email varchar(45),
in _rig_name varchar(45)
)
BEGIN
SELECT `rig_id`,
    `rig_uuid`,
    `rig_create_datetime`,
     `rig_name`,
    `user_email`,
    `rig_lan_ip`,
    `rig_claymore_version`,
    `rig_time_up`,
    `rig_reset_today`,
    `rig_claymore_reset_today`,
    `rig_gpu_info_eth`,
    `rig_gpu_second_coin`,
    `rig_gpu_info_second_coin`,
    `rig_start_bat_data`,
    `location_uuid`,
    `rig_reseter_number`
FROM `xem_tbl_rig`
WHERE `user_email` = _user_email and `rig_name` = _rig_name ;

END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_rig_update
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_rig_update`(
IN _rig_uuid varchar(45),
IN _rig_name varchar(45),
IN _user_email varchar(45),
IN _rig_lan_ip varchar(15),
IN _rig_claymore_version varchar(45),
IN _rig_time_up varchar(45),
IN _rig_reset_today varchar(45),
IN _rig_claymore_reset_today varchar(45),
IN _rig_gpu_info_eth varchar(1000),
IN _rig_gpu_second_coin varchar(45),
IN _rig_gpu_info_second_coin varchar(1000),
IN _rig_start_bat_data VARCHAR(1000),
IN _location_uuid varchar(45),
IN _rig_reseter_number varchar(2)
)
BEGIN
UPDATE `xem_tbl_rig`
SET
`rig_name` = _rig_name,
`user_email` = _user_email,
`rig_lan_ip` = _rig_lan_ip,
`rig_claymore_version` = _rig_claymore_version,
`rig_time_up` = _rig_time_up,
`rig_reset_today` = _rig_reset_today,
`rig_claymore_reset_today` = _rig_claymore_reset_today,
`rig_gpu_info_eth` = _rig_gpu_info_eth,
`rig_gpu_second_coin` = _rig_gpu_second_coin,
`rig_gpu_info_second_coin` = _rig_gpu_info_second_coin,
`rig_start_bat_data` = _rig_start_bat_data,
`location_uuid` = _location_uuid,
`rig_reseter_number` = _rig_reseter_number
WHERE `rig_uuid` = _rig_uuid;

END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_user_create
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_user_create`(
IN _user_email varchar(45),
IN _user_password varchar(45)
)
BEGIN
INSERT INTO `xem_tbl_user`
(
`user_email`,
`user_password`,
`user_create_date`)
VALUES
(
_user_email,
_user_password,
now());
END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_user_delete
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_user_delete`(
IN _user_email varchar(45)

)
BEGIN
DELETE FROM `xem_tbl_user`
WHERE `user_email` = _user_email;

END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_user_list
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_user_list`()
BEGIN
SELECT `user_id`,
    `user_email`,
    `user_password`,
    `user_create_date`
FROM `xem_tbl_user`;

END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_user_read
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_user_read`(IN _user_email varchar(45))
BEGIN
SELECT `user_id`,
    `user_email`,
    `user_password`,
    `user_start_bat_data`,
    `user_create_date`
FROM `xem_tbl_user`
WHERE 
	`user_email`=_user_email
;

END//
DELIMITER ;

-- Volcando estructura para procedimiento xem.sp_user_update
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_user_update`(
IN _user_email varchar(45),
IN _user_password varchar(45)
)
BEGIN
UPDATE `xem_tbl_user`
SET
`user_email` = _user_email,
`user_password` = _user_password
WHERE `user_email` = _user_email;
END//
DELIMITER ;

-- Volcando estructura para tabla xem.xem_tbl_action
CREATE TABLE IF NOT EXISTS `xem_tbl_action` (
  `action_id` int(11) NOT NULL AUTO_INCREMENT,
  `action_uuid` varchar(45) DEFAULT NULL,
  `action_change_claymore_version` tinyint(4) DEFAULT NULL,
  `action_change_start_bat` tinyint(4) DEFAULT NULL,
  `action_download_claymore_version` tinyint(4) DEFAULT NULL,
  `action_restart_claymore` tinyint(4) DEFAULT NULL,
  `action_reset_rig` tinyint(4) DEFAULT NULL,
  `rig_uuid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`action_id`),
  KEY `fk_action_rig` (`rig_uuid`),
  CONSTRAINT `fk_action_rig` FOREIGN KEY (`rig_uuid`) REFERENCES `xem_tbl_rig` (`rig_uuid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla xem.xem_tbl_action: ~0 rows (aproximadamente)
DELETE FROM `xem_tbl_action`;
/*!40000 ALTER TABLE `xem_tbl_action` DISABLE KEYS */;
INSERT INTO `xem_tbl_action` (`action_id`, `action_uuid`, `action_change_claymore_version`, `action_change_start_bat`, `action_download_claymore_version`, `action_restart_claymore`, `action_reset_rig`, `rig_uuid`) VALUES
	(42, '57ce745f-63d1-4542-86a1-2ee2daf1d1e7', 0, 0, 0, 0, NULL, 'dd76f82f-994d-455a-ac73-29D6GE53TGT4'),
	(43, 'fc287fa2-55ef-4c5a-a8c0-26a0fb81fbdb', 0, 0, 0, 0, NULL, 'dd76f82f-994d-455a-ac73-sgdte52f');
/*!40000 ALTER TABLE `xem_tbl_action` ENABLE KEYS */;

-- Volcando estructura para tabla xem.xem_tbl_location_rig
CREATE TABLE IF NOT EXISTS `xem_tbl_location_rig` (
  `location_id` int(11) NOT NULL AUTO_INCREMENT,
  `location_uuid` varchar(45) DEFAULT NULL,
  `location_create_date` datetime DEFAULT NULL,
  `location_name` varchar(45) DEFAULT NULL,
  `user_email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`location_id`),
  UNIQUE KEY `location_uuid` (`location_uuid`),
  KEY `fk_location_user` (`user_email`),
  CONSTRAINT `fk_location_user` FOREIGN KEY (`user_email`) REFERENCES `xem_tbl_user` (`user_email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla xem.xem_tbl_location_rig: ~0 rows (aproximadamente)
DELETE FROM `xem_tbl_location_rig`;
/*!40000 ALTER TABLE `xem_tbl_location_rig` DISABLE KEYS */;
INSERT INTO `xem_tbl_location_rig` (`location_id`, `location_uuid`, `location_create_date`, `location_name`, `user_email`) VALUES
	(28, 'c52104be-31d4-4a94-a9fc-3962520cd386', '2017-10-18 16:28:09', 'caurimare', 'alfonsof@gmail.com');
/*!40000 ALTER TABLE `xem_tbl_location_rig` ENABLE KEYS */;

-- Volcando estructura para tabla xem.xem_tbl_reset
CREATE TABLE IF NOT EXISTS `xem_tbl_reset` (
  `reset_id` int(11) NOT NULL AUTO_INCREMENT,
  `reset_create_datetime` datetime DEFAULT NULL,
  `reset_uuid` varchar(45) NOT NULL,
  `rig_reseter_number` varchar(45) DEFAULT NULL,
  `user_email` varchar(45) DEFAULT NULL,
  `location_uuid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`reset_id`),
  UNIQUE KEY `reset_uuid` (`reset_uuid`),
  KEY `fk_reset_user` (`user_email`),
  KEY `fk_reset_location` (`location_uuid`),
  CONSTRAINT `fk_reset_location` FOREIGN KEY (`location_uuid`) REFERENCES `xem_tbl_location_rig` (`location_uuid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_reset_user` FOREIGN KEY (`user_email`) REFERENCES `xem_tbl_user` (`user_email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla xem.xem_tbl_reset: ~0 rows (aproximadamente)
DELETE FROM `xem_tbl_reset`;
/*!40000 ALTER TABLE `xem_tbl_reset` DISABLE KEYS */;
INSERT INTO `xem_tbl_reset` (`reset_id`, `reset_create_datetime`, `reset_uuid`, `rig_reseter_number`, `user_email`, `location_uuid`) VALUES
	(17, '2017-10-18 16:22:03', '09257791-f74f-4187-b87b-9287bf399d38', NULL, 'alfonsof@gmail.com', NULL);
/*!40000 ALTER TABLE `xem_tbl_reset` ENABLE KEYS */;

-- Volcando estructura para tabla xem.xem_tbl_rig
CREATE TABLE IF NOT EXISTS `xem_tbl_rig` (
  `rig_id` int(11) NOT NULL AUTO_INCREMENT,
  `rig_uuid` varchar(45) DEFAULT NULL,
  `rig_create_datetime` datetime DEFAULT NULL,
  `rig_name` varchar(45) DEFAULT NULL,
  `user_email` varchar(45) DEFAULT NULL,
  `rig_lan_ip` varchar(15) DEFAULT NULL,
  `rig_claymore_version` varchar(45) DEFAULT NULL,
  `rig_time_up` varchar(45) DEFAULT NULL,
  `rig_reset_today` varchar(45) DEFAULT NULL,
  `rig_claymore_reset_today` varchar(45) DEFAULT NULL,
  `rig_gpu_info_eth` varchar(1000) DEFAULT NULL,
  `rig_gpu_second_coin` varchar(45) NOT NULL,
  `rig_gpu_info_second_coin` varchar(1000) NOT NULL,
  `rig_start_bat_data` varchar(1000) DEFAULT NULL,
  `location_uuid` varchar(45) DEFAULT NULL,
  `rig_reseter_number` varchar(2) DEFAULT NULL,
  `reset_uuid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`rig_id`),
  UNIQUE KEY `rig_uuid` (`rig_uuid`),
  KEY `fk_rig_user` (`user_email`),
  KEY `fk_rig_reset` (`reset_uuid`),
  KEY `fk_rig_location` (`location_uuid`),
  CONSTRAINT `fk_rig_reset` FOREIGN KEY (`reset_uuid`) REFERENCES `xem_tbl_reset` (`reset_uuid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_rig_location` FOREIGN KEY (`location_uuid`) REFERENCES `xem_tbl_location_rig` (`location_uuid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_rig_user` FOREIGN KEY (`user_email`) REFERENCES `xem_tbl_user` (`user_email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla xem.xem_tbl_rig: ~0 rows (aproximadamente)
DELETE FROM `xem_tbl_rig`;
/*!40000 ALTER TABLE `xem_tbl_rig` DISABLE KEYS */;
INSERT INTO `xem_tbl_rig` (`rig_id`, `rig_uuid`, `rig_create_datetime`, `rig_name`, `user_email`, `rig_lan_ip`, `rig_claymore_version`, `rig_time_up`, `rig_reset_today`, `rig_claymore_reset_today`, `rig_gpu_info_eth`, `rig_gpu_second_coin`, `rig_gpu_info_second_coin`, `rig_start_bat_data`, `location_uuid`, `rig_reseter_number`, `reset_uuid`) VALUES
	(51, 'dd76f82f-994d-455a-ac73-29D6GE53TGT4', '2017-10-18 16:16:06', 'Min02', 'alfonsof@gmail.com', '192.168.2.103', '9.5', '0:04:06', 'None', 'None', '[{"Fan":71, "Temp":64,"Model":"Radeon RX 470/570\r\n","Mhs":20.406},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"}]', 'Decred', '[{"Mhs":612.193},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"}]', '', NULL, NULL, NULL),
	(52, 'dd76f82f-994d-455a-ac73-sgdte52f', '2017-10-18 16:29:24', 'Min01', 'alfonsof@gmail.com', '192.168.2.100', '9.5', '0:04:06', 'None', 'None', '[{"Fan":71, "Temp":64,"Model":"Radeon RX 470/570\r\n","Mhs":20.406},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"}]', 'Decred', '[{"Mhs":612.193},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"}]', '', NULL, NULL, NULL);
/*!40000 ALTER TABLE `xem_tbl_rig` ENABLE KEYS */;

-- Volcando estructura para tabla xem.xem_tbl_user
CREATE TABLE IF NOT EXISTS `xem_tbl_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_create_date` datetime DEFAULT NULL,
  `user_email` varchar(45) DEFAULT NULL,
  `user_password` varchar(45) DEFAULT NULL,
  `user_eth_wallet` varchar(45) DEFAULT NULL,
  `user_start_bat_data` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_email_UNIQUE` (`user_email`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla xem.xem_tbl_user: ~0 rows (aproximadamente)
DELETE FROM `xem_tbl_user`;
/*!40000 ALTER TABLE `xem_tbl_user` DISABLE KEYS */;
INSERT INTO `xem_tbl_user` (`user_id`, `user_create_date`, `user_email`, `user_password`, `user_eth_wallet`, `user_start_bat_data`) VALUES
	(32, '2017-10-18 16:08:37', 'josegregorio994@gmail.com', '50fffaadd17d8d34a0d8770bfcd65f45', NULL, NULL),
	(33, '2017-10-18 16:09:00', 'alfonsof@gmail.com', '871861812a46c507c932760e3da005e', NULL, NULL);
/*!40000 ALTER TABLE `xem_tbl_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
