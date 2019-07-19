--global database
create database global;

drop table IF EXISTS `tenant_meta`;
--sharding meta data
create TABLE `tenant_meta` (
  `id` bigint NOT NULL auto_increment,
  `tenant_id` bigint NOT NULL,
  `name` varchar(36) NOT NULL,
  `url` varchar(100) NOT NULL,
  `create_time` timestamp NOT NULL,
  `update_time` timestamp,
  PRIMARY KEY (`id`),
  UNIQUE KEY TENANT_ID (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

