DROP USER if exists 'currencyexchanger'@'localhost' ;

CREATE USER 'currencyexchanger'@'localhost' IDENTIFIED BY 'currencyexchanger';

GRANT ALL PRIVILEGES ON * . * TO 'currencyexchanger'@'localhost';