drop table IF EXISTS `user`;
create TABLE `user` (
  `id` bigint NOT NULL auto_increment,
  `name` varchar(36) NOT NULL,
  `region` varchar(36) DEFAULT NULL,
  `create_time` timestamp NOT NULL,
  `update_time` timestamp,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table IF EXISTS `tenant`;
create TABLE `tenant` (
  `id` bigint NOT NULL auto_increment,
  `tenant_id` bigint NOT NULL,
  `name` varchar(36) NOT NULL,
  `schema` varchar(100) NOT NULL,
  `create_time` timestamp NOT NULL,
  `update_time` timestamp,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;