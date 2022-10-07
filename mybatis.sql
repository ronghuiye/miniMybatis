SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activity_id` bigint(20) NOT NULL,
  `activity_name` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL,
  `activity_desc` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_activity_id` (`activity_id`)
);

BEGIN;
INSERT INTO `activity` VALUES (1, 100001, 'name', 'test', '2021-08-08 20:14:50', '2021-08-08 20:14:50');
INSERT INTO `activity` VALUES (3, 100002, 'name', 'test', '2021-10-05 15:49:21', '2021-10-05 15:49:21');
COMMIT;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` varchar(9) DEFAULT NULL,
  `userHead` varchar(16) DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT NULL,
  `userName` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

BEGIN;
INSERT INTO `user` VALUES (1, '10001', '1_04', '2022-04-13 00:00:00', '2022-04-13 00:00:00', 'cat');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
