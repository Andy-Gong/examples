--sharding database
create database schema_2;

drop table IF EXISTS `user`;
--sharding tables
create TABLE `user` (
  `id` bigint NOT NULL auto_increment,
  `name` varchar(36) NOT NULL,
  `region` varchar(36) DEFAULT NULL,
  `create_time` timestamp NOT NULL,
  `update_time` timestamp,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

