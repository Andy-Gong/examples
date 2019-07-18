create database master;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(19) NOT NULL AUTO_INCREMENT,
  `tenant_id` int NOT NULL,
  `company_id` bigint(19) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `account` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `TENANT` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
