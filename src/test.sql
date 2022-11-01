/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.21-log : Database - delicious_food
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`delicious_food` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `delicious_food`;

/*Table structure for table `food` */

DROP TABLE IF EXISTS `food`;

CREATE TABLE `food` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `score` int(11) DEFAULT NULL,
  `title` varchar(1024) DEFAULT NULL,
  `content` varchar(4096) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `recommend` int(11) DEFAULT NULL,
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `food` */

insert  into `food`(`commentId`,`score`,`title`,`content`,`time`,`recommend`) values (1,10,'兰园二楼滑蛋饭','非常好吃，就是排队有点久','2022-10-26 17:22:11',210),(2,8,'梅园三楼水饺','好吃，又开了一家分店','2022-10-26 17:24:16',160),(3,10,'兰二赤酱干锅','好吃实惠','2022-10-26 19:55:48',230),(6,7,'兰园麻辣烫','味道还可以，感觉比其他园便宜','2022-10-30 20:45:09',30),(7,0,'菊园一楼','反向推荐一波，千万别去','2022-10-30 20:46:35',-10),(8,8,'兰园二楼','好吃啊','2022-10-31 11:02:29',40),(9,111,'123','111','2022-10-31 11:45:02',-30);

/*Table structure for table `image` */

DROP TABLE IF EXISTS `image`;

CREATE TABLE `image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(4096) DEFAULT NULL,
  `image` varchar(4096) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `image` */

insert  into `image`(`id`,`content`,`image`) values (21,'rua','images\\fc546c63-7222-439b-a679-e2c7e3025933.png');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
