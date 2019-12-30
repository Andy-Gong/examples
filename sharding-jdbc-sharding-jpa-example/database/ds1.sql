Create database ds1;
-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user_0`;
CREATE TABLE `user_0` (
  `user_id` bigint(19) NOT NULL AUTO_INCREMENT,
  `tenant_id` int NOT NULL,
  `company_id` bigint(19) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `account` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `TENANT` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_1`;
CREATE TABLE `user_1` (
  `user_id` bigint(19) NOT NULL AUTO_INCREMENT,
  `tenant_id` int NOT NULL,
  `company_id` bigint(19) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `account` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `TENANT` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
