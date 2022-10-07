/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.5.8-log : Database - bloodstore_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bloodstore_db` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `bloodstore_db`;

/*Table structure for table `blooddonor_tb` */

DROP TABLE IF EXISTS `blooddonor_tb`;

CREATE TABLE `blooddonor_tb` (
  `donorid` int(100) NOT NULL AUTO_INCREMENT,
  `regid` varchar(100) DEFAULT NULL,
  `bloodgrp` varchar(100) DEFAULT NULL,
  `gender` varchar(100) DEFAULT NULL,
  `donortype` varchar(100) DEFAULT NULL,
  `donationdate` varchar(100) DEFAULT NULL,
  `donorname` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`donorid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `blooddonor_tb` */

insert  into `blooddonor_tb`(`donorid`,`regid`,`bloodgrp`,`gender`,`donortype`,`donationdate`,`donorname`) values (1,'11','AB -ve','Female','student','2020-01-07','PRIYA'),(2,'19','AB -ve','Female','student','2019-09-13','HUSMAN'),(3,'14','A -ve','Female','student','2020-01-07','POOJA'),(4,'1','O +ve','Male','student','2019-09-13','ADRIAN SEBASTIAN'),(5,'10','O -ve','Female','student','2020-01-07','HUSSAIN'),(6,'15','AB +ve','Male','student','2020-01-07','OLIVER'),(7,'5','AB +ve','irrelevant','faculty','2020-01-08','prashob'),(8,'6','B +ve','irrelevant','faculty','2020-01-08','Amresh'),(9,'6','A -ve','irrelevant','Non Faculty','2020-01-09','maman Roy'),(10,'14','A -ve','Female','student','2020-01-09','POOJA'),(11,'6','B +ve','irrelevant','faculty','2020-01-09','Amresh'),(12,'6','B +ve','irrelevant','faculty','2020-01-09','Amresh');

/*Table structure for table `bloodgroups_tb` */

DROP TABLE IF EXISTS `bloodgroups_tb`;

CREATE TABLE `bloodgroups_tb` (
  `group_id` int(10) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(100) DEFAULT NULL,
  `Rarity` float DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `bloodgroups_tb` */

insert  into `bloodgroups_tb`(`group_id`,`group_name`,`Rarity`) values (1,'A+ve',35.7),(2,'B+ve',8.5),(3,'AB+ve',3.4),(4,'O+ve',37.4),(5,'A-ve',6.3),(6,'B-ve',1.5),(7,'AB-ve',0.6),(8,'O-ve',6.6);

/*Table structure for table `nonstaffenroll_tb` */

DROP TABLE IF EXISTS `nonstaffenroll_tb`;

CREATE TABLE `nonstaffenroll_tb` (
  `nonfac_id` int(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `dob` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phoneno` varchar(100) NOT NULL,
  `bloodgrp` varchar(100) NOT NULL,
  PRIMARY KEY (`nonfac_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `nonstaffenroll_tb` */

insert  into `nonstaffenroll_tb`(`nonfac_id`,`name`,`dob`,`address`,`phoneno`,`bloodgrp`) values (1,'Philipose Boshle','10/01/2020','abcd','9845236969','O -ve'),(2,'Mani','10/01/2020','abcd','9845236969','AB -ve'),(3,'Babumon','18/01/2020','ab','9847059917','O -ve'),(4,'amma','26/01/2020','abcd','9847059917','A +ve'),(5,'shamily','25/06/1971','kumbalangi','9335087159','A +ve'),(6,'maman Roy','25/06/1971','kumili','9335087849','A -ve');

/*Table structure for table `staffenrol_tb` */

DROP TABLE IF EXISTS `staffenrol_tb`;

CREATE TABLE `staffenrol_tb` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `dob` varchar(100) NOT NULL,
  `department` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phoneno` varchar(100) NOT NULL,
  `bloodgrp` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `staffenrol_tb` */

insert  into `staffenrol_tb`(`id`,`name`,`dob`,`department`,`address`,`phoneno`,`bloodgrp`) values (1,'pooja','12-02-1999','computer','palazhi','7896541230','b+ve'),(2,'meera','03-02-1998','visual med','teertham','1234567890','o+ve'),(3,'amritha','05-09-2000','commerce','karikzhil','7418529630','a+ve'),(4,'prof Newton','31/01/1939','computer science','New York','9999999999','AB -ve'),(5,'prashob','08/01/1940','commerce','vytilla','3698280741','AB +ve'),(6,'Amresh','09/12/1991','visual media','Palakkad','1553807469','B +ve');

/*Table structure for table `studentenroll_tb` */

DROP TABLE IF EXISTS `studentenroll_tb`;

CREATE TABLE `studentenroll_tb` (
  `enroll_id` int(100) NOT NULL AUTO_INCREMENT,
  `st_name` varchar(100) DEFAULT NULL,
  `st_phone` varchar(200) DEFAULT NULL,
  `st_email` varchar(200) DEFAULT NULL,
  `st_address` varchar(200) DEFAULT NULL,
  `st_dob` varchar(100) DEFAULT NULL,
  `st_bldgrp` varchar(100) DEFAULT NULL,
  `st_course` varchar(100) DEFAULT NULL,
  `st_batch` varchar(100) DEFAULT NULL,
  `st_gender` char(10) DEFAULT NULL,
  PRIMARY KEY (`enroll_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

/*Data for the table `studentenroll_tb` */

insert  into `studentenroll_tb`(`enroll_id`,`st_name`,`st_phone`,`st_email`,`st_address`,`st_dob`,`st_bldgrp`,`st_course`,`st_batch`,`st_gender`) values (1,'ADRIAN SEBASTIAN','9544351997','maksim.adrian@gmail.com','KOCHI','12/02/1993','O +ve','MCA','2016-2019','Male'),(10,'HUSSAIN','1359582018','hus@gmail.com','MALAPPURAM','26/12/2019','O -ve','BSc.Mathematics','2019-2022','Female'),(11,'PRIYA','9632505177','priya@gmail.in','ALUVA','30/04/1993','AB -ve','BSc.Mathematics','2019-2022','Female'),(13,'MEERA','1234567898','meera@gmail.com','ERNAKULAM','19/12/2019','O +ve','BCA','2017-2020','Female'),(14,'POOJA','1234567898','pooja@gmail.com','EDAPALLY','20/12/2019','A -ve','BCA','2017-2020','Female'),(15,'OLIVER','1234567898','oliver@gmail.com','EDAPALLY','16/12/2019','AB +ve','BCA','2017-2020','Male'),(19,'HUSMAN','6646434518','maksim.adrian@gmail.com','AHAJAIA','01/12/2019','AB -ve','MCA','2016-2021','Female');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
