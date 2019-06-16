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

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` bigint(19) NOT NULL AUTO_INCREMENT,
  `tenant_id` int NOT NULL,
  `user_id` bigint(19) NOT NULL,
  `order_name` varchar(45) DEFAULT NULL,
  `create_time` timestamp NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `TENANT` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
