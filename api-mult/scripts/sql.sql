--BD MYSQL
CREATE SCHEMA IF NOT EXISTS `grupomult`;
commit;

USE `grupomult`;

CREATE TABLE `produto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoria` int(1) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `valor` double (15,2)NOT NULL,
  PRIMARY KEY (`id`)
); 
commit;

INSERT INTO grupomult.produto (categoria,nome,valor) VALUES 
(1,'Sprite',13.99)
,(1,'Coca Cola',13.99)
,(0,'Café',8.9)
,(0,'Macarrão',5.9)
,(1,'Jujuba',1.2)
,(0,'Feijao',8)
,(0,'Soja',5)
,(0,'Figado de Galinha',35)
;
commit;