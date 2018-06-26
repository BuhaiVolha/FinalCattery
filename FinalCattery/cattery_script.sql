CREATE DATABASE  IF NOT EXISTS `cattery` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `cattery`;
-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: cattery
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cat`
--

DROP TABLE IF EXISTS `cat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cat` (
  `cat_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL DEFAULT 'Milacoon' COMMENT 'Фамилия кота - это имя питомника, то есть по умолчанию надо ставить имя данного питомника, если только это не приобретенный от пользователя котенок или просто кот из другого питомника',
  `gender` tinyint(1) unsigned NOT NULL COMMENT '0 - мужской, 1 - женский',
  `age` datetime NOT NULL,
  `photo` blob,
  `description` text NOT NULL,
  `EMS_code` varchar(15) NOT NULL COMMENT 'код цвета',
  `FIFe_eyes_colour_code` int(10) unsigned NOT NULL,
  `pedigree_id` int(10) unsigned NOT NULL,
  `price` decimal(20,2) unsigned NOT NULL,
  `flag_approved` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT 'если кота предложил пользователь, то он будет иметь значение 0, оценщик сможет увидеть заявки на оценку, принять кота, тогда администратор сможет его добавить, изменив значение на 1',
  `flag_sold` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '1 - продан. Чтобы можно было показывать, как котят вообще, так доступных для покупки',
  PRIMARY KEY (`cat_id`),
  UNIQUE KEY `cat_id_UNIQUE` (`cat_id`),
  KEY `EMS_code_idx` (`EMS_code`),
  KEY `FIFe_eyes_colour_code_idx` (`FIFe_eyes_colour_code`),
  KEY `pedigree_id_idx` (`pedigree_id`),
  CONSTRAINT `EMS_code` FOREIGN KEY (`EMS_code`) REFERENCES `cat_colour` (`ems_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FIFe_eyes_colour_code` FOREIGN KEY (`FIFe_eyes_colour_code`) REFERENCES `cat_eyes_colour` (`fife_eyes_colour_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pedigree_id` FOREIGN KEY (`pedigree_id`) REFERENCES `cat_pedigree` (`pedigree_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Таблица хранит животных для продажи';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cat`
--

LOCK TABLES `cat` WRITE;
/*!40000 ALTER TABLE `cat` DISABLE KEYS */;
INSERT INTO `cat` VALUES (1,'Apelsin','Milacoon',0,'2018-03-20 00:00:00',NULL,'sweet tempered and gentle','d',64,1,400.00,1,0),(2,'Alisa','Milacoon',1,'2018-03-20 00:00:00',NULL,'when she runs, she can be quite loud','f',64,2,450.00,1,1),(3,'Ben','Bright Star',0,'2018-02-24 00:00:00',NULL,'body is long and rectangular and the tail is also long','v',63,3,500.00,0,0),(4,'Amelie','Milacoon',1,'2018-03-20 00:00:00',NULL,'she should be brushed to make certain that her fur does not tangle','f',61,1,400.00,1,0),(5,'Aragorn','Milacoon',0,'2018-03-20 00:00:00',NULL,'his origins are shrouded in mystery','d',64,1,400.00,1,0);
/*!40000 ALTER TABLE `cat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cat_colour`
--

DROP TABLE IF EXISTS `cat_colour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cat_colour` (
  `EMS_code` varchar(15) NOT NULL,
  `name` varchar(45) NOT NULL COMMENT 'имя цвета',
  PRIMARY KEY (`EMS_code`),
  UNIQUE KEY `EMS_code_UNIQUE` (`EMS_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Окрас животных. (blacksmoketortie, blacktortie tabby, Cremetabby, Creme, Bluetortie, Redtabby, red, redsilver mach tabby, Redsmoke, Blacksmoke etc). У каждого окраса есть свой международный код, который будет естественным первичным ключом';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cat_colour`
--

LOCK TABLES `cat_colour` WRITE;
/*!40000 ALTER TABLE `cat_colour` DISABLE KEYS */;
INSERT INTO `cat_colour` VALUES ('a','blue'),('d','red'),('e','creme'),('f','blacktortie'),('n','black'),('q','bluetortie'),('s','silver'),('w','white'),('y','golden');
/*!40000 ALTER TABLE `cat_colour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cat_eyes_colour`
--

DROP TABLE IF EXISTS `cat_eyes_colour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cat_eyes_colour` (
  `FIFe_eyes_colour_code` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  PRIMARY KEY (`FIFe_eyes_colour_code`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='FIFe code - starting at 61';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cat_eyes_colour`
--

LOCK TABLES `cat_eyes_colour` WRITE;
/*!40000 ALTER TABLE `cat_eyes_colour` DISABLE KEYS */;
INSERT INTO `cat_eyes_colour` VALUES (61,'blue'),(62,'orange'),(63,'odd'),(64,'green');
/*!40000 ALTER TABLE `cat_eyes_colour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cat_pedigree`
--

DROP TABLE IF EXISTS `cat_pedigree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cat_pedigree` (
  `pedigree_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent_female` varchar(45) NOT NULL,
  `parent_male` varchar(45) NOT NULL,
  PRIMARY KEY (`pedigree_id`),
  UNIQUE KEY `pedigree_id_UNIQUE` (`pedigree_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Таблица хранит информацию о предках животного. Здесь, возможно, надо сделать рекурсивную связь';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cat_pedigree`
--

LOCK TABLES `cat_pedigree` WRITE;
/*!40000 ALTER TABLE `cat_pedigree` DISABLE KEYS */;
INSERT INTO `cat_pedigree` VALUES (1,'Apple Milacoon','Benedict Bright Star'),(2,'Princess Milacoon','Sam White Lynx');
/*!40000 ALTER TABLE `cat_pedigree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` binary(60) NOT NULL COMMENT 'Binary(60), потому что пароль шифруется с помощью BCrypt и в зашифрованном виде занимает такой размер',
  `name` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `role_id` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '1 - пользователь\n2 - админ\n3 - оценщик',
  `colour_preference` varchar(15) DEFAULT NULL,
  `discount` tinyint(3) unsigned DEFAULT NULL COMMENT 'скидка в процентах',
  `flag_banned` tinyint(1) unsigned DEFAULT '0' COMMENT '1 - забанен админом за нарушение правил',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `role_id_idx` (`role_id`),
  KEY `colour_preference_idx` (`colour_preference`),
  CONSTRAINT `colour_preference` FOREIGN KEY (`colour_preference`) REFERENCES `cat_colour` (`ems_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Таблица, которая будет хранить зарегистрированных пользователей';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','343\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','admin','admin','adm@mail.ru',2,NULL,NULL,0),(2,'cat','3424234\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','Mike','Atter','mike@mail.ru',1,'f',NULL,0),(3,'koko','3424f\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','Anna','Peters','ann32@mail.ru',1,'y',5,0),(4,'catlover','6636\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','Violet','Ru','vvvv@mail.ru',1,'y',NULL,0),(5,'expert','6666666\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','expert','expert','sams@mail.ru',3,NULL,NULL,0),(6,'mur','4444\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','Lena','Law','kittens@gmail.ru',1,'d',10,0),(7,'dog','3636436\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0','Jake','Juke','or55h@yandex.com',1,'w',NULL,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_feedback`
--

DROP TABLE IF EXISTS `user_feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_feedback` (
  `feedback_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `feedback` text NOT NULL COMMENT 'Может, использовать VARCHAR(2000)?',
  PRIMARY KEY (`feedback_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `feedback_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Отзыв, который пользователь может оставить о питомнике';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_feedback`
--

LOCK TABLES `user_feedback` WRITE;
/*!40000 ALTER TABLE `user_feedback` DISABLE KEYS */;
INSERT INTO `user_feedback` VALUES (1,4,'I give this cattery a 5 star review!'),(2,6,'Use your common sense. Her reviews are all 5 stars or 1 star. What does that tell you?');
/*!40000 ALTER TABLE `user_feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_order`
--

DROP TABLE IF EXISTS `user_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_order` (
  `order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `cat_id` int(10) unsigned NOT NULL COMMENT 'Это котенок, которого покупают',
  `order_date` datetime NOT NULL,
  `total` decimal(20,2) unsigned NOT NULL COMMENT 'Это должна быть сумма оплаты с учетом скидки',
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `order_id_UNIQUE` (`order_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `cat_id_UNIQUE` (`cat_id`),
  CONSTRAINT `cat_id` FOREIGN KEY (`cat_id`) REFERENCES `cat` (`cat_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Эта таблица представляет собой заказ пользователя';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_order`
--

LOCK TABLES `user_order` WRITE;
/*!40000 ALTER TABLE `user_order` DISABLE KEYS */;
INSERT INTO `user_order` VALUES (1,3,2,'2018-06-24 00:00:00',450.00);
/*!40000 ALTER TABLE `user_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_role` (
  `role_id` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `role` varchar(15) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Таблица с ролями. 1) Пользователь - покупает котенка / предлагает котенка в питомник 2) Админ - будет наполнять сайт контентом и редактировать его 3) Оценщик - оценивать предложенных пользователем котят, которых после Админ будет размещать. (Хотя, может, эти роли можно совместить)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'user'),(2,'admin'),(3,'expert');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-26 21:47:07
