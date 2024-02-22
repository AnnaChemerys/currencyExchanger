use `currency_exchanger_directory`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `commission_fee` float NOT NULL,
  `count_of_free_transactions` int NOT NULL,
  `sell_sum_unit_id` int NOT NULL,
  `receive_sum_unit_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),

  KEY `FK_SELL_SUM_UNIT_idx` (`sell_sum_unit_id`),
  CONSTRAINT `FK_SELL_SUM_UNIT` FOREIGN KEY (`sell_sum_unit_id`) 
  REFERENCES `sum_unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,

  KEY `FK_RECEIVE_SUM_UNIT_idx` (`receive_sum_unit`),
  CONSTRAINT `FK_RECEIVE_SUM_UNIT` FOREIGN KEY (`receive_sum_unit`) 
  REFERENCES `sum_unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,

  KEY `FK_USER_idx` (`user_id`),
  CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `balance`;

CREATE TABLE `balance` (
  `id` int NOT NULL AUTO_INCREMENT,
  `currency_id` int NOT NULL,
  `sum_on_the_balance` money??? NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  
  KEY `FK_CURRENCY_idx` (`currency_id`),
  CONSTRAINT `FK_CURRENCY` FOREIGN KEY (`currency_id`) 
  REFERENCES `currency` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  KEY `FK_USER_idx` (`user_id`),
  CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1; 

DROP TABLE IF EXISTS `sum_unit`;

CREATE TABLE `sum_unit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount_of_money` money??? NOT NULL,
  `currency_id` int NOT NULL,
  `transaction_id` int NOT NULL,
  PRIMARY KEY (`id`),
  
  KEY `FK_CURRENCY_idx` (`currency_id`),
  CONSTRAINT `FK_CURRENCY` FOREIGN KEY (`currency_id`) 
  REFERENCES `currency` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  KEY `FK_TRANSACTION_idx` (`transaction_id`),
  CONSTRAINT `FK_TRANSACTION` FOREIGN KEY (`transaction_id`) 
  REFERENCES `transaction` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;