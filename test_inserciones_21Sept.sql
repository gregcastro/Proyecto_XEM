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

-- Volcando datos para la tabla xem.xem_tbl_action: 8 rows
DELETE FROM `xem_tbl_action`;
/*!40000 ALTER TABLE `xem_tbl_action` DISABLE KEYS */;
INSERT INTO `xem_tbl_action` (`action_id`, `action_uuid`, `action_change_claymore_version`, `action_change_start_bat`, `action_download_claymore_version`, `action_restart_claymore`, `action_reset_rig`, `rig_uuid`) VALUES
	(36, '21b2efb0-dda3-4455-866f-231dde6f8e61', 0, 0, 0, 0, NULL, 'dd76f82f-994d-455a-ac73-5djdue725the'),
	(28, '384d44d2-b4ac-4f22-a757-2c6b5afe32bd', 0, 0, 0, 0, NULL, 'dd76f82f-994d-455a-ac73-29D6GE53TGT4'),
	(29, '29a0f6fb-72d8-4112-940c-f944be2915b4', 0, 0, 0, 0, NULL, 'dd76f82f-994d-455a-ac73-hdy463hathqe'),
	(30, '47f57a5f-2d83-493f-b59b-28726ed80187', 0, 0, 0, 0, NULL, 'dd76f82f-994d-455a-ac73-jf74jur75y64'),
	(31, 'db869c5e-887c-46d7-b464-3641e99ac3a0', 0, 0, 0, 0, NULL, 'dd76f82f-994d-455a-ac73-jf74h5673hy3'),
	(32, 'c4c43909-4a84-4c97-8c30-147698db85b7', 0, 0, 0, 0, NULL, 'dd76f82f-994d-455a-ac73-sgdte52f'),
	(34, 'b2e2ae4a-1dbd-4164-9fbd-2a16fbe5495d', 0, 0, 0, 0, NULL, 'dd76f82f-994d-455a-ac73-ag53te574h85'),
	(35, '6626fa4b-218c-4c05-9ce8-59a45a46f0c3', 0, 0, 0, 0, NULL, 'dd76f82f-994d-455a-ac73-54f5gt8e5w4d'),
	(37, 'b018d3ec-8086-4830-8c67-22a57bc93d53', 0, 0, 0, 0, NULL, 'dd76f82f-994d-455a-ac73-d6g3g45et456');
/*!40000 ALTER TABLE `xem_tbl_action` ENABLE KEYS */;

-- Volcando datos para la tabla xem.xem_tbl_location_rig: 8 rows
DELETE FROM `xem_tbl_location_rig`;
/*!40000 ALTER TABLE `xem_tbl_location_rig` DISABLE KEYS */;
INSERT INTO `xem_tbl_location_rig` (`location_id`, `location_create_date`, `location_name`, `user_email`, `rig_uuid`) VALUES
	(22, '2017-09-21 15:32:20', '', 'jose.castro.lazo@gmail.com', 'dd76f82f-994d-455a-ac73-54f5gt8e5w4d'),
	(19, '2017-09-21 15:32:02', '', 'alfonsof@gmail.com', 'dd76f82f-994d-455a-ac73-sgdte52f'),
	(21, '2017-09-21 15:32:14', '', 'jose.castro.lazo@gmail.com', 'dd76f82f-994d-455a-ac73-ag53te574h85'),
	(15, '2017-09-21 15:31:33', '', 'alfonsof@gmail.com', 'dd76f82f-994d-455a-ac73-29D6GE53TGT4'),
	(16, '2017-09-21 15:31:44', '', 'josegregorio994@gmail.com', 'dd76f82f-994d-455a-ac73-hdy463hathqe'),
	(17, '2017-09-21 15:31:50', '', 'josegregorio994@gmail.com', 'dd76f82f-994d-455a-ac73-jf74jur75y64'),
	(18, '2017-09-21 15:31:56', '', 'josegregorio994@gmail.com', 'dd76f82f-994d-455a-ac73-jf74h5673hy3'),
	(23, '2017-09-21 15:32:25', '', 'jose.castro.lazo@gmail.com', 'dd76f82f-994d-455a-ac73-5djdue725the'),
	(24, '2017-09-21 15:42:29', '', 'luisbello@gmail.com', 'dd76f82f-994d-455a-ac73-d6g3g45et456');
/*!40000 ALTER TABLE `xem_tbl_location_rig` ENABLE KEYS */;

-- Volcando datos para la tabla xem.xem_tbl_reset: 8 rows
DELETE FROM `xem_tbl_reset`;
/*!40000 ALTER TABLE `xem_tbl_reset` DISABLE KEYS */;
INSERT INTO `xem_tbl_reset` (`reset_id`, `rig_reseter_number`, `reset_create_datetime`, `user_email`, `rig_uuid`) VALUES
	(2, '0', '2017-09-21 15:31:33', 'alfonsof@gmail.com', 'dd76f82f-994d-455a-ac73-29D6GE53TGT4'),
	(3, '0', '2017-09-21 15:31:44', 'josegregorio994@gmail.com', 'dd76f82f-994d-455a-ac73-hdy463hathqe'),
	(4, '0', '2017-09-21 15:31:50', 'josegregorio994@gmail.com', 'dd76f82f-994d-455a-ac73-jf74jur75y64'),
	(5, '0', '2017-09-21 15:31:56', 'josegregorio994@gmail.com', 'dd76f82f-994d-455a-ac73-jf74h5673hy3'),
	(6, '0', '2017-09-21 15:32:02', 'alfonsof@gmail.com', 'dd76f82f-994d-455a-ac73-sgdte52f'),
	(8, '0', '2017-09-21 15:32:14', 'jose.castro.lazo@gmail.com', 'dd76f82f-994d-455a-ac73-ag53te574h85'),
	(9, '0', '2017-09-21 15:32:20', 'jose.castro.lazo@gmail.com', 'dd76f82f-994d-455a-ac73-54f5gt8e5w4d'),
	(10, '0', '2017-09-21 15:32:25', 'jose.castro.lazo@gmail.com', 'dd76f82f-994d-455a-ac73-5djdue725the'),
	(11, '0', '2017-09-21 15:42:29', 'luisbello@gmail.com', 'dd76f82f-994d-455a-ac73-d6g3g45et456');
/*!40000 ALTER TABLE `xem_tbl_reset` ENABLE KEYS */;

-- Volcando datos para la tabla xem.xem_tbl_rig: 8 rows
DELETE FROM `xem_tbl_rig`;
/*!40000 ALTER TABLE `xem_tbl_rig` DISABLE KEYS */;
INSERT INTO `xem_tbl_rig` (`rig_id`, `rig_uuid`, `rig_create_datetime`, `rig_name`, `user_email`, `rig_lan_ip`, `rig_claymore_version`, `rig_time_up`, `rig_reset_today`, `rig_claymore_reset_today`, `rig_gpu_info_eth`, `rig_gpu_second_coin`, `rig_gpu_info_second_coin`, `rig_start_bat_data`, `location_uuid`, `rig_reseter_number`) VALUES
	(41, 'dd76f82f-994d-455a-ac73-5djdue725the', '2017-09-21 15:32:25', 'gabo03', 'jose.castro.lazo@gmail.com', '192.168.3.104', '9.5', '0:04:06', 'None', 'None', '[{"Fan":71, "Temp":64,"Model":"Radeon RX 470/570\r\n","Mhs":20.406},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"}]', 'Decred', '[{"Mhs":612.193},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"}]', '', 'None', '0'),
	(33, 'dd76f82f-994d-455a-ac73-29D6GE53TGT4', '2017-09-21 15:31:33', 'Min02', 'alfonsof@gmail.com', '192.168.2.103', '9.5', '0:04:06', 'None', 'None', '[{"Fan":71, "Temp":64,"Model":"Radeon RX 470/570\r\n","Mhs":20.406},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"}]', 'Decred', '[{"Mhs":612.193},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"}]', '', 'None', '0'),
	(34, 'dd76f82f-994d-455a-ac73-hdy463hathqe', '2017-09-21 15:31:44', 'Greg&Gabo', 'josegregorio994@gmail.com', '192.168.0.100', '9.5', '0:04:06', 'None', 'None', '[{"Fan":71, "Temp":64,"Model":"Radeon RX 470/570\r\n","Mhs":20.406},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"}]', 'Decred', '[{"Mhs":612.193},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"}]', '', 'None', '0'),
	(35, 'dd76f82f-994d-455a-ac73-jf74jur75y64', '2017-09-21 15:31:50', 'Twins', 'josegregorio994@gmail.com', '192.168.0.101', '9.5', '0:04:06', 'None', 'None', '[{"Fan":71, "Temp":64,"Model":"Radeon RX 470/570\r\n","Mhs":20.406},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"}]', 'Decred', '[{"Mhs":612.193},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"}]', '', 'None', '0'),
	(36, 'dd76f82f-994d-455a-ac73-jf74h5673hy3', '2017-09-21 15:31:56', 'Ethedev', 'josegregorio994@gmail.com', '192.168.0.102', '9.5', '0:04:06', 'None', 'None', '[{"Fan":71, "Temp":64,"Model":"Radeon RX 470/570\r\n","Mhs":20.406},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"}]', 'Decred', '[{"Mhs":612.193},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"}]', '', 'None', '0'),
	(37, 'dd76f82f-994d-455a-ac73-sgdte52f', '2017-09-21 15:32:02', 'Min01', 'alfonsof@gmail.com', '192.168.2.100', '9.5', '0:04:06', 'None', 'None', '[{"Fan":71, "Temp":64,"Model":"Radeon RX 470/570\r\n","Mhs":20.406},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"}]', 'Decred', '[{"Mhs":612.193},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"}]', '', 'None', '0'),
	(39, 'dd76f82f-994d-455a-ac73-ag53te574h85', '2017-09-21 15:32:14', 'gabo01', 'jose.castro.lazo@gmail.com', '192.168.3.102', '9.5', '0:04:06', 'None', 'None', '[{"Fan":71, "Temp":64,"Model":"Radeon RX 470/570\r\n","Mhs":20.406},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"}]', 'Decred', '[{"Mhs":612.193},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"}]', '', 'None', '0'),
	(40, 'dd76f82f-994d-455a-ac73-54f5gt8e5w4d', '2017-09-21 15:32:20', 'gabo02', 'jose.castro.lazo@gmail.com', '192.168.3.103', '9.5', '0:04:06', 'None', 'None', '[{"Fan":71, "Temp":64,"Model":"Radeon RX 470/570\r\n","Mhs":20.406},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"}]', 'Decred', '[{"Mhs":612.193},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"}]', '', 'None', '0'),
	(42, 'dd76f82f-994d-455a-ac73-d6g3g45et456', '2017-09-21 15:42:29', 'luis01', 'luisbello@gmail.com', '192.168.1.100', '9.5', '0:04:06', 'None', 'None', '[{"Fan":71, "Temp":64,"Model":"Radeon RX 470/570\r\n","Mhs":20.406},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"Radeon RX 470/570\r\n","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"},{"Fan":"None","Temp":"None","Model":"None","Mhs":"None"}]', 'Decred', '[{"Mhs":612.193},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"},{"Mhs":"None"}]', '', 'None', '0');
/*!40000 ALTER TABLE `xem_tbl_rig` ENABLE KEYS */;

-- Volcando datos para la tabla xem.xem_tbl_user: 3 rows
DELETE FROM `xem_tbl_user`;
/*!40000 ALTER TABLE `xem_tbl_user` DISABLE KEYS */;
INSERT INTO `xem_tbl_user` (`user_id`, `user_create_date`, `user_email`, `user_password`, `user_eth_wallet`, `user_start_bat_data`) VALUES
	(27, '2017-09-21 15:30:55', 'josegregorio994@gmail.com', '50fffaadd17d8d34a0d8770bfcd65f45', NULL, NULL),
	(26, '2017-09-21 15:30:44', 'alfonsof@gmail.com', '871861812a46c507c932760e3da005e', NULL, NULL),
	(29, '2017-09-21 15:31:08', 'jose.castro.lazo@gmail.com', 'ad7b84fa719d1fa8d43c6a57829e7497', NULL, NULL),
	(30, '2017-09-21 15:42:12', 'luisbello@gmail.com', '252f7b55bb287983e9b365b32a6c45c0', NULL, NULL);
/*!40000 ALTER TABLE `xem_tbl_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
