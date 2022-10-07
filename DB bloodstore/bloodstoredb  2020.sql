/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.26 : Database - bloodstore_db
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
  `phoneno` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`donorid`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

/*Data for the table `blooddonor_tb` */

insert  into `blooddonor_tb`(`donorid`,`regid`,`bloodgrp`,`gender`,`donortype`,`donationdate`,`donorname`,`phoneno`,`address`) values (1,'11','AB -ve','Female','student','2020-01-07','PRIYA','9632505177','ALUVA'),(2,'19','AB -ve','Female','student','2019-09-13','HUSMAN','6646434518','AHAJAIA'),(3,'14','A -ve','Female','student','2020-01-07','POOJA','1234567898','EDAPALLY'),(4,'1','O +ve','Male','student','2019-09-13','Philipose','9845236960','abcde'),(5,'10','O -ve','Male','student','2020-01-07','aswin','1234567891','kollam'),(6,'15','AB +ve','Female','student','2020-01-07','OLIVER','1234567898','EDAPALLY'),(7,'5','O +ve','irrelevant','faculty','2020-01-08','shyam','9335087153','goa'),(8,'6','AB -ve','irrelevant','faculty','2020-01-08','amit','1553807463','Pala'),(9,'6','AB -ve','irrelevant','Non Faculty','2020-01-09','amit','1553807463','Pala'),(10,'14','A -ve','Female','student','2020-01-09','POOJA','1234567898','EDAPALLY'),(11,'6','AB -ve','irrelevant','faculty','2020-01-09','amit','1553807463','Pala'),(12,'6','AB -ve','irrelevant','faculty','2020-01-09','amit','1553807463','Pala'),(13,'24','B +ve','Female','student','2020-02-19','AKSHARA','1234567890','VALATH'),(14,'9','AB -ve','irrelevant','faculty','2020-02-19','deena','1234567899','qqwwee'),(15,'22','B -ve','Female','student','2020-02-20','sreedevi','9847280939','delhi'),(16,'3','O +ve','irrelevant','Non Faculty','2020-02-20','amritha','9847059917','ab'),(17,'8','B -ve','irrelevant','Non Faculty','2020-02-23','alu','1234567890','valam'),(18,'11','AB -ve','Female','student','2020-02-24','PRIYA','9632505177','ALUVA'),(19,'9','AB -ve','irrelevant','faculty','2020-02-24','deena','1234567899','qqwwee'),(20,'5','O +ve','irrelevant','faculty','2020-02-24','shyam','9335087153','goa'),(21,'11','AB -ve','Female','student','2020-02-24','PRIYA','9632505177','ALUVA'),(22,'28','AB +ve','Female','student','2020-02-29','JOSIANN J','8590285551','aluva'),(23,'29','B -ve','Male','student','2020-03-02','SHANKAR','9847059917','SREEVIHAR'),(24,'30','A +ve','Female','student','2020-03-02','PARVATHY','9847059913','PARA VIHAR'),(25,'11','AB -ve','irrelevant','faculty','2020-03-03','deepa','9847059913','deepa vihar'),(26,'9','O -ve','irrelevant','Non Faculty','2020-03-03','aminta','9847059913','aminta nivas'),(27,'9','O -ve','irrelevant','Non Faculty','2020-03-03','aminta','9847059913','aminta nivas'),(28,'9','O -ve','irrelevant','Non Faculty','2020-03-03','aminta','9847059913','aminta nivas'),(29,'33','AB +ve','Female','student','2020-03-04','JO','1237894560','ELAMAKARA'),(30,'15','B -ve','irrelevant','faculty','2020-03-04','seema','7894561230','pachalam'),(31,'12','B -ve','irrelevant','Non Faculty','2020-03-04','maya','4567891230','kunnumpuram'),(32,'34','B -ve','Male','student','2020-03-05','ARSHA','9847059912','ARSHA BAVAN');

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `nonstaffenroll_tb` */

insert  into `nonstaffenroll_tb`(`nonfac_id`,`name`,`dob`,`address`,`phoneno`,`bloodgrp`) values (1,'Philipose','30/01/2009','abcde','9845236960','O +ve'),(2,'Manu','10/01/2020','abcde','9544435168','O -ve'),(3,'amritha','18/01/2020','ab','9847059917','O +ve'),(4,'ammu','01/01/2020','valath','1234567890','AB -ve'),(5,'shyam','29/03/1999','goa','9335087153','O +ve'),(6,'maman Roy','25/06/1971','kumili','9335087849','A -ve'),(7,'ambily','19/02/2020','abcdefg','9847509917','B -ve'),(8,'alu','22/02/2020','valam','1234567890','B -ve'),(9,'aminta','03/03/1985','aminta nivas','9847059913','O -ve'),(11,'raju','03/03/1988','pachalam','1234567899','A -ve'),(12,'maya','04/03/1974','kunnumpuram','4567891230','B -ve'),(13,'sundari','15/12/1995','sundari bhavan','5643187526','B -ve');

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `staffenrol_tb` */

insert  into `staffenrol_tb`(`id`,`name`,`dob`,`department`,`address`,`phoneno`,`bloodgrp`) values (1,'mary','25/03/1932','Visual Media','Joseph vihar','9544435166','B -ve'),(2,'meera','29/02/2020','visual med','teertham','1234567890','A +ve'),(3,'amrithaar','22/02/2020','commerce','karikzhil','7418529630','a+ve'),(4,'prof Newton','31/01/1939','computer science','New York','9999999999','AB -ve'),(5,'prashob','08/01/1940','commerce','vytilla','3698280741','AB +ve'),(6,'amit','09/12/1991','visual media','Pala','1553807463','AB -ve'),(7,'ammu','15/01/2020','visual media','abcd','9847059917','B -ve'),(8,'alu','11/01/2020','visual media','abhi@ gmail. com','9847059917','O +ve'),(9,'deena','15/02/2020','computer science','qqwwee','1234567899','AB -ve'),(10,'sree','11/03/2020','visual media','sree 123','9847059912','A -ve'),(11,'deepa','13/03/2020','visual media','deepa vihar','9847059913','AB -ve'),(14,'asiya','03/03/2004','commerce','fortkochi','1234569870','AB -ve'),(15,'seema','04/03/1987','commerce','pachalam','7894561230','B -ve'),(16,'sheethal','13/05/2011','visual media','kaloor','9842589715','B -ve');

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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

/*Data for the table `studentenroll_tb` */

insert  into `studentenroll_tb`(`enroll_id`,`st_name`,`st_phone`,`st_email`,`st_address`,`st_dob`,`st_bldgrp`,`st_course`,`st_batch`,`st_gender`) values (1,'ADRIAN SEBASTIAN','9544351997','maksim.adrian@gmail.com','KOCHI','12/02/1993','O +ve','MCA','2016-2019','Male'),(10,'aswin','1234567891','aswin@gmail.com','kollam','25/04/2020','O -ve','BSc.Mathematics','2019-2022','Male'),(11,'PRIYA','9632505177','priya@gmail.in','ALUVA','30/04/1993','AB -ve','BSc.Visual Media','2016-2021','Female'),(13,'MEERA','1234567898','meera@gmail.com','ERNAKULAM','19/12/2019','AB -ve','BSc.Mathematics','2016-2019','Female'),(14,'POOJA','1234567898','pooja@gmail.com','EDAPALLY','20/12/2019','A -ve','BCA','2017-2020','Female'),(15,'OLIVER','1234567898','oliver@gmail.com','EDAPALLY','16/12/2019','AB +ve','BSc.Computer Science','2016-2021','Female'),(19,'HUSMAN','6646434518','maksim.adrian@gmail.com','AHAJAIA','01/12/2019','AB -ve','MCA','2016-2021','Female'),(20,'AMRITHA','9847059917','avsbn@gmail.com','ABNSMN','10/01/2020','B -ve','BSc.Computer Science','2016-2021','Female'),(21,'HJJK','3126489758','m@gmail.com','MNBBBZBZ','16/01/2020','B -ve','BCA','2019-2022','Female'),(22,'sreedevi','9847280939','abc@gmail.com','delhi','04/03/2014','B -ve','BSc.Mathematics','2017-2020','Female'),(23,'Akshay','9847059918','abcdd@gmail.com','ABCS','28/02/2020','O -ve','MCA','2016-2021','Male'),(24,'AKSHARA','1234567890','akshara@gmail.com','VALATH','01/04/2020','B +ve','MCA','2016-2019','Female'),(25,'RAMAN','1234567890','ramanqwe@gmail.com','AAAAADDDD','06/02/2020','O -ve','BCA','2016-2019','Male'),(26,'DEENA','1122334455','deena@gmail.com','MMMNNNMMM','01/02/2020','A -ve','BSc.Computer Science','2016-2021','Female'),(27,'POOJA','1234567890','pooja@gmail.com','AAA','02/02/2020','O -ve','BSc.Mathematics','2016-2021','Female'),(28,'JOSIANN J','8590285551','tikkujoseph@gmail.com','aluva','29/02/2020','AB +ve','MCA','2019-2022','Female'),(29,'SHANKAR','9847059917','sankar@gmail.com','SREEVIHAR','21/03/2020','B -ve','BCA','2017-2020','Male'),(30,'PARVATHY','9847059913','paru@gmail.com','PARU VIHAR','29/03/1995','A +ve','MCA','2019-2022','Female'),(31,'SAIRAM','8921360851','sairamsai2426@gmail.com','NADUVILATHARA','24/10/1999','O +ve','BSc.Visual Media','2016-2021','Male'),(32,'ATHUL','1234567893','athul@gmail.com','VADUTHALA','09/04/1988','AB -ve','BSc.Computer Science','2019-2022','Male'),(33,'JO','1237894560','jo@gmail.com','ELAMAKARA','04/03/1993','AB +ve','BCA','2016-2019','Female'),(34,'ARSHA','9847059912','arsha@gmail.com','ARSHA BAVAN','05/03/1963','B -ve','BSc.Computer Science','2016-2021','Male'),(35,'MANU','4569873210','manu@gmail.com','MANU BHAVAN','05/03/2003','B -ve','BSc.Computer Science','2017-2020','Male'),(36,'GOPIKA','9842536791','gopika@gmail.com','GOPIKA BHAVAN','08/08/1998','A -ve','BSc.Computer Science','2017-2020','Male'),(37,'DEVI','9842536791','gopika@gmail.com','GOPIKA BHAVAN','08/08/1998','A -ve','BSc.Computer Science','2017-2020','Female'),(38,'UNNI','9841236797','unnikuttan@gmail.com','UNNI VIHAR','05/03/2006','AB +ve','BCA','2016-2019','Male');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
