create database master;
create database slave;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user_0`;
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


-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order_0`;
CREATE TABLE `order_0` (
  `order_id` bigint(19) NOT NULL AUTO_INCREMENT,
  `tenant_id` int NOT NULL,
  `user_id` bigint(19) NOT NULL,
  `order_name` varchar(45) DEFAULT NULL,
  `create_time` timestamp NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `TENANT` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `order_1`;
CREATE TABLE `order_1` (
  `order_id` bigint(19) NOT NULL AUTO_INCREMENT,
  `tenant_id` int NOT NULL,
  `user_id` bigint(19) NOT NULL,
  `order_name` varchar(45) DEFAULT NULL,
  `create_time` timestamp NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `TENANT` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
