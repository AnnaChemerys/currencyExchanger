use `currency_exchanger_directory`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `exchanger_details_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `creation_time` timestamp NOT NULL,
  `calculated_commission_fee` decimal(10, 6) DEFAULT NULL,
  PRIMARY KEY (`id`), 
  
  KEY `FK_EXCHANGER_DETAILS_idx` (`exchanger_details_id`),
  CONSTRAINT `_EXCHANGER_DETAILS` FOREIGN KEY (`exchanger_details_id`) 
  REFERENCES `exchanger_details` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,

  KEY `FK_USER_idx` (`user_id`),
  CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `balance`;

CREATE TABLE `balance` (
  `id` int NOT NULL AUTO_INCREMENT,
  `currency_code` varchar(3) NOT NULL,
  `sum_on_the_balance` decimal(10, 6) NOT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  
  KEY `FK_USER_idx` (`user_id`),
  CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1; 

DROP TABLE IF EXISTS `sum_unit`;

CREATE TABLE `sum_unit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount_of_money` decimal(10,6) NOT NULL,
  `currency_code` varchar(3) NOT NULL,
  `transaction_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  
  KEY `FK_TRANSACTION_idx` (`transaction_id`),
  CONSTRAINT `FK_TRANSACTION` FOREIGN KEY (`transaction_id`) 
  REFERENCES `transaction` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1; 

DROP TABLE IF EXISTS `exchanger_details`;

CREATE TABLE `exchanger_details`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `commission_fee` float NOT NULL,
  `count_of_free_transactions` int NOT NULL,
  PRIMARY KEY (`id`)
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1; 

INSERT INTO `exchanger_details` (`commission_fee`, `count_of_free_transactions`) 
VALUES (0.7, 5)