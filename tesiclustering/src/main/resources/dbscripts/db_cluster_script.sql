
CREATE TABLE `users` (
  `id` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `createdBy` varchar(255) DEFAULT NULL,
  `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8


CREATE TABLE `graphs` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `xml_file` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8


CREATE TABLE `executions` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `rate` int(11) DEFAULT NULL,
  `topology` varchar(255) DEFAULT NULL,
  `num_clusters` int(11) DEFAULT NULL,
  `graph_id` int(11) DEFAULT NULL,
  `nodes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8


CREATE TABLE `infoclusters` (
  `id` int(11) DEFAULT NULL,
  `num_monitored_nodes` int(11) DEFAULT NULL,
  `num_extended_nodes` int(11) DEFAULT NULL,
  `num_monitored_edges` int(11) DEFAULT NULL,
  `num_extended_edges` int(11) DEFAULT NULL,
  `monitored_diameter` int(11) DEFAULT NULL,
  `extended_diameter` int(11) DEFAULT NULL,
  `cluster_id` int(11) NOT NULL,
  PRIMARY KEY (`cluster_id`),
  KEY `id` (`id`),
  CONSTRAINT `infoclusters_ibfk_1` FOREIGN KEY (`id`) REFERENCES `executions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

