
CREATE TABLE `url_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `long_url` varchar(32) DEFAULT NULL,
  `short_url` varchar(32) DEFAULT NULL,
  `yn` tinyInt(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;