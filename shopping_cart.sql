/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.55-community : Database - shopping_cart
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shopping_cart` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `shopping_cart`;

/*Table structure for table `t_computer` */

DROP TABLE IF EXISTS `t_computer`;

CREATE TABLE `t_computer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `model` varchar(50) DEFAULT NULL,
  `pic` varchar(50) DEFAULT NULL,
  `prodInfo` text,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_computer` */

LOCK TABLES `t_computer` WRITE;

insert  into `t_computer`(`id`,`model`,`pic`,`prodInfo`,`price`) values (1,'x200','x200.jpg','挺不错',2000),(2,'x500','x500.jpg','挺不错',5000),(3,'x600','x600.jpg','挺不错',6000);

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
