/*
 Navicat Premium Data Transfer

 Source Server         : server_cadangan
 Source Server Type    : MariaDB
 Source Server Version : 100138
 Source Host           : 192.168.62.177:3306
 Source Schema         : sik_palangkaraya

 Target Server Type    : MariaDB
 Target Server Version : 100138
 File Encoding         : 65001

 Date: 01/02/2024 01:32:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for berkas_tte
-- ----------------------------
DROP TABLE IF EXISTS `berkas_tte`;
CREATE TABLE `berkas_tte`  (
  `no_dokumen` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `no_rawat` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `tgl_tte` datetime(0) NULL DEFAULT NULL,
  `kode` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `lokasi_file` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `nama_file` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `status` enum('-','MEDIS','NON MEDIS') CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT '-'
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
