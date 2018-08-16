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
  `gender` enum('FEMALE','MALE') NOT NULL COMMENT '0 - мужской, 1 - женский',
  `birth_date` date NOT NULL,
  `cat_photo` varchar(30) DEFAULT NULL,
  `body_colour_code` char(1) NOT NULL COMMENT 'код цвета',
  `cat_eyes_colour_code` char(4) NOT NULL,
  `price` decimal(20,2) unsigned NOT NULL,
  `sale_status_id` char(5) NOT NULL DEFAULT 'AVAIL' COMMENT '1 - доступен для продажи, 2 - забронирован, 3 - продан',
  `user_suggested_id` int(10) unsigned NOT NULL DEFAULT '1' COMMENT 'FK на id пользователя предложившего котенка. 1 - id админа, тк он добавляет по умолчанию на сайт котят, но если id равен id какого-либо пользователя, это значит, что он предложил котенка, который был одобрен экспертом, а затем добавлен админом',
  `offer_made_id` int(10) unsigned NOT NULL DEFAULT '1',
  `flag_cat_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`cat_id`),
  UNIQUE KEY `cat_id_UNIQUE` (`cat_id`),
  KEY `id_status_idx` (`sale_status_id`),
  KEY `user_suggested_id_idx` (`user_suggested_id`),
  KEY `body_colour_code_idx` (`body_colour_code`),
  KEY `cat_eyes_colour_code_idx` (`cat_eyes_colour_code`),
  KEY `offer_made_id_idx` (`offer_made_id`),
  CONSTRAINT `body_colour_code` FOREIGN KEY (`body_colour_code`) REFERENCES `cat_colour` (`ems_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cat_eyes_colour_code` FOREIGN KEY (`cat_eyes_colour_code`) REFERENCES `cat_eyes_colour` (`fife_eyes_colour_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `offer_made_id` FOREIGN KEY (`offer_made_id`) REFERENCES `user_offer` (`offer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sale_status_id` FOREIGN KEY (`sale_status_id`) REFERENCES `cat_status` (`id_status`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_suggested_id` FOREIGN KEY (`user_suggested_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Таблица хранит животных для продажи';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cat`
--

LOCK TABLES `cat` WRITE;
/*!40000 ALTER TABLE `cat` DISABLE KEYS */;
INSERT INTO `cat` VALUES (1,'MALE','2018-06-13','cat1.jpg','E','F64',400.00,'AVAIL',1,1,0),(2,'FEMALE','2018-04-20','cat2.jpg','W','F64',450.00,'AVAIL',1,1,0),(3,'MALE','2018-04-20','cat3.jpg','A','F63',500.00,'AVAIL',1,1,0),(4,'FEMALE','2018-04-20','cat-up2475128236201967147.jpg','F','F61',400.00,'AVAIL',1,1,0),(5,'MALE','2018-06-12','cat-up2169998576400098764.jpg','F','F61',400.00,'AVAIL',1,1,0),(6,'FEMALE','2018-06-14','cat6.jpg','Q','F61',450.00,'RSRV',1,1,0),(13,'MALE','2018-01-14','cat7.jpg','S','F63',550.00,'AVAIL',3,8,0),(15,'MALE','2018-06-05','cat8.jpg','F','F64',550.00,'AVAIL',6,9,0),(17,'MALE','2018-06-12','cat9.jpg','Y','F61',500.00,'AVAIL',2,7,0),(41,'MALE','2018-05-08','cat10.jpg','E','F61',560.00,'SOLD',1,1,0),(56,'FEMALE','2018-05-22','cat11.jpg','D','F63',777.00,'RSRV',3,20,0),(66,'FEMALE','2018-07-11','cat13.jpg','A','F61',780.00,'AVAIL',1,1,0),(68,'MALE','2018-06-13','cat-up378073102890985781.jpg','A','F61',600.00,'AVAIL',1,1,0),(69,'MALE','2018-07-19','cat-up7643868860987636625.jpg','S','F61',550.00,'AVAIL',1,1,0),(70,'MALE','2018-06-05','cat-up861906644820998435.jpg','D','F61',300.00,'AVAIL',19,33,0),(71,'MALE','2018-06-19','offer5022040088048988746.jpg','D','F61',554.00,'AVAIL',20,34,0),(72,'FEMALE','2018-07-03','offer8386040540062044300.jpg','D','F64',500.00,'AVAIL',20,44,0),(73,'FEMALE','2018-07-20','offer3702765196028177867.jpg','A','F61',400.00,'AVAIL',3,43,0),(74,'MALE','2018-06-13',NULL,'N','F61',400.00,'AVAIL',1,1,0),(75,'MALE','2018-07-11','cat-up2559325644077609344.jpg','W','F64',550.00,'AVAIL',1,1,0),(76,'FEMALE','2018-07-11','offer7540283564693479432.jpg','F','F61',420.00,'AVAIL',3,45,0),(77,'FEMALE','2018-07-01','offer5246146400902396934.jpg','A','F61',300.00,'AVAIL',3,35,0),(78,'MALE','2018-07-19','cat-up2475378047112847072.jpg','Q','F61',400.00,'AVAIL',1,1,0),(79,'MALE','2018-07-11','cat-up2382809704540594281.jpg','A','F62',420.00,'AVAIL',1,1,0),(80,'FEMALE','2018-07-16','cat-up142459320150607533.jpg','F','F61',320.00,'AVAIL',1,1,0),(81,'MALE','2018-07-16','cat-up5728121961915216140.jpg','D','F62',340.00,'AVAIL',1,1,0),(84,'MALE','2018-07-03','cat-up8445438777447347105.jpg','A','F61',400.00,'AVAIL',1,1,0),(85,'FEMALE','2018-07-12','offer2189223048871110737.jpg','E','F61',250.00,'AVAIL',6,11,0);
/*!40000 ALTER TABLE `cat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cat_colour`
--

DROP TABLE IF EXISTS `cat_colour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cat_colour` (
  `EMS_code` char(1) NOT NULL,
  PRIMARY KEY (`EMS_code`),
  UNIQUE KEY `EMS_code_UNIQUE` (`EMS_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Окрас животных. (blacksmoketortie, blacktortie tabby, Cremetabby, Creme, Bluetortie, Redtabby, red, redsilver mach tabby, Redsmoke, Blacksmoke etc). У каждого окраса есть свой международный код, который будет естественным первичным ключом';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cat_colour`
--

LOCK TABLES `cat_colour` WRITE;
/*!40000 ALTER TABLE `cat_colour` DISABLE KEYS */;
INSERT INTO `cat_colour` VALUES ('A'),('D'),('E'),('F'),('N'),('Q'),('S'),('W'),('Y');
/*!40000 ALTER TABLE `cat_colour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cat_eyes_colour`
--

DROP TABLE IF EXISTS `cat_eyes_colour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cat_eyes_colour` (
  `FIFe_eyes_colour_code` char(4) NOT NULL,
  PRIMARY KEY (`FIFe_eyes_colour_code`),
  UNIQUE KEY `FIFe_eyes_colour_code_UNIQUE` (`FIFe_eyes_colour_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='FIFe code - starting at 61';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cat_eyes_colour`
--

LOCK TABLES `cat_eyes_colour` WRITE;
/*!40000 ALTER TABLE `cat_eyes_colour` DISABLE KEYS */;
INSERT INTO `cat_eyes_colour` VALUES ('F61'),('F62'),('F63'),('F64');
/*!40000 ALTER TABLE `cat_eyes_colour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cat_localized`
--

DROP TABLE IF EXISTS `cat_localized`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cat_localized` (
  `cat_localized_id` int(10) unsigned NOT NULL,
  `locale` char(3) NOT NULL,
  `name` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL COMMENT 'Фамилия кота - это имя питомника, то есть по умолчанию надо ставить имя данного питомника, если только это не приобретенный от пользователя котенок или просто кот из другого питомника',
  `description` text NOT NULL,
  `parent_female` varchar(45) NOT NULL,
  `parent_male` varchar(45) NOT NULL,
  PRIMARY KEY (`cat_localized_id`,`locale`),
  KEY `locale_idx` (`locale`),
  CONSTRAINT `cat_localized_id` FOREIGN KEY (`cat_localized_id`) REFERENCES `cat` (`cat_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `locale` FOREIGN KEY (`locale`) REFERENCES `locale_language` (`locale_language_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cat_localized`
--

LOCK TABLES `cat_localized` WRITE;
/*!40000 ALTER TABLE `cat_localized` DISABLE KEYS */;
INSERT INTO `cat_localized` VALUES (1,'EN','Apelsin','Milacoon','Clean','Countess Milacoon','Krakotun Milacoon'),(1,'RU','Апельсин','Милакун','Большая чистюля','Графиня Милакун','Кракотун Милакун'),(2,'EN','Alisa','Milacoon','She can be quite loud when she runs','Countess Milacoon','Krakotun Milacoon'),(2,'RU','Алиса','Милакун','Довольно шумная, когда бегает','Графиня Милакун','Кракотун Милакун'),(3,'EN','Ben','Leoland','The body is long and tail is also long','Murka Leoland','Tom Leoland'),(3,'RU','Бен','Леоланд','Длинное тело и хвост тоже длинный','Мурка Леоланд','Том Леоланд'),(4,'EN','Amelie','Milacoon','She must be brushed','Countess Milacoon','Krakotun Milacoon'),(4,'RU','Амели','Милакун','Ее нужно вычесывать, чтобы шерсть не спуталась','Графиня Милакун','Кракотун Милакун'),(5,'EN','Aragorn','Milacoon','His origins are shrouded in mystery','Countess Milacoon','Krakotun Milacoon'),(5,'RU','Арагорн','Милакун','Его происхождение покрыто тайной','Графиня Милакун','Кракотун Милакун'),(6,'EN','Alpha','Milacoon','The leader of the pack','Countess Milacoon','Krakotun Milacoon'),(6,'RU','Альфа','Милакун','Лидер стаи','Графиня Милакун','Кракотун Милакун'),(13,'EN','Sweety','Tigrun','Makes loud noises','Monique Tigrun','Edward Tigrun'),(13,'RU','Милашка','Тигрун','Издает громкие звуки','Моника Тигрун','Эдвард Тигрун'),(15,'EN','Red','Benedictus','Is of royal origin','Carol Benedictus','Ajax Benedictus'),(15,'RU','Рыжик','Бенедикт','Обладает аристократическим происхождением','Кэрол Бенедикт','Аякс Бенедикт'),(17,'EN','Barsik','Tigrun','Can be tought to stay on back feet','Monique Tigrun','Edward Tigrun'),(17,'RU','Барсик','Тигрун','Его можно научить стоять на задних лапах','Моника Тигрун','Эдвард Тигрун'),(41,'EN','Mars','Milacoon','Likes to be pat','Countess Milacoon','Krakotun Milacoon'),(41,'RU','Марс','Милакун','Непременно мурлычет, когда его гладят','Графиня Милакун','Кракотун Милакун'),(56,'EN','Lucky','Milacoon','Beauty one','Countess Milacoon','Krakotun Milacoon'),(56,'RU','Удачка','Милакун',' Просто красавица и вислоушка, личность яркая и неординарная во всех отношениях','Графиня Милакун','Кракотун Милакун'),(66,'EN','Pea','Milacoon','Loves people','Countess Milacoon','Krakotun Milacoon'),(66,'RU','Горошенка','Милакун','Любит двуногих','Графиня Милакун','Кракотун Милакун'),(68,'EN','Spot','Milacoon','Cat do simple tricks','Countess Milacoon','Krakotun Milacoon'),(68,'RU','Пятнышко','Милакун','Умеет выполнять примитивные трюки','Графиня Милакун','Кракотун Милакун'),(69,'EN','Controller','Milacoon','Loves sweets (but don\'t give it to him)','Countess Milacoon','Krakotun Milacoon'),(69,'RU','Контроллер','Милакун','Любит есть сладкое, но ему его нельзя','Графиня Милакун','Кракотун Милакун'),(70,'EN','Leo','Leoland','This cat is a winner. Always','Murka Leoland','Tom Leoland'),(70,'RU','Лео','Леоланд','Этот кот - победитель по жизни','Мурка Леоланд','Том Леоланд'),(71,'EN','Beigel','Benedictus','Write something','Carol Benedictus','Ajax Benedictus'),(71,'RU','Бублик','Бенедикт','Написать что-то','Кэрол Бенедикт','Аякс Бенедикт'),(72,'EN','Kitty','White Lion','Cat loves water','Snowflake White Lion','Gandalf White Lion'),(72,'RU','Китти','Белый Лев','Котенок любит воду и обладает добродушным характером','Снежинка Белый Лев','Гендальф Белый Лев'),(73,'EN','Sparky','White Lion','Looks good on violet','Snowflake White Lion','Gandalf White Lion'),(73,'RU','Искорка','Белый Лев','Хорошо смотрится на фиолетовом фоне','Снежинка Белый Лев','Гендальф Белый Лев'),(74,'EN','Pi','Milacoon','Can swim','Countess Milacoon','Krakotun Milacoon'),(74,'RU','Пи','Милакун','Умеет плавать в воде','Графиня Милакун','Кракотун Милакун'),(75,'EN','Grzegorz','White Lion','Перевести','Snowflake White Lion','Gandalf White Lion'),(75,'RU','Грегор','Белый Лев','Радует глаз своей белоснежностью','Снежинка Белый Лев','Гендальф Белый Лев'),(76,'EN','Christine','Whitecoon','Перевести','Ruth Whitecoon','Shaggy Whitecoon'),(76,'RU','Кристина','Белокун','Тихий и послушный котенок','Руфь Белокун','Хвостик Белокун'),(77,'EN','Anna','Benedictus','Перевести','Carol Benedictus','Ajax Benedictus'),(77,'RU','Анна','Бенедикт','Очень большая коробочка!','Кэрол Бенедикт','Аякс Бенедикт'),(78,'EN','Michael','Milacoon','Перевести','Countess Milacoon','Krakotun Milacoon'),(78,'RU','Майкл','Милакун','Посещает класс импровизации','Графиня Милакун','Кракотун Милакун'),(79,'EN','Plato','Milacoon','A popular mouser','Countess Milacoon','Krakotun Milacoon'),(79,'RU','Платон','Милакун','Умеет ловить мышей','Графиня Милакун','Кракотун Милакун'),(80,'EN','Betty','Milacoon','Перевести','Countess Milacoon','Krakotun Milacoon'),(80,'RU','Бетти','Милакун','Принимает собаку, хотя из-за сильного характера непременно подчинит ее','Графиня Милакун','Кракотун Милакун'),(81,'EN','Kira','Milacoon','Перевести','Countess Milacoon','Krakotun Milacoon'),(81,'RU','Кира','Милакун','Легко приспосабливается к постоянному пребыванию в квартире, но с радостью будет использовать балкон, обеспеченный сетью','Графиня Милакун','Кракотун Милакун'),(84,'EN','Johny','Milacoon','He didn\'t hit her','Countess Milacoon','Krakotun Milacoon'),(84,'RU','Джонни','Милакун','Джонни бой','Графиня Милакун','Кракотун Милакун'),(85,'EN','Ada','Whitecoon','Will eat anything','Ruth Whitecoon','Shaggy Whitecoon'),(85,'RU','Ада','Белокун','Ест все','Руфь Белокун','Хвостик Белокун');
/*!40000 ALTER TABLE `cat_localized` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cat_pedigree_type`
--

DROP TABLE IF EXISTS `cat_pedigree_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cat_pedigree_type` (
  `pedigree_id` char(6) NOT NULL,
  PRIMARY KEY (`pedigree_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cat_pedigree_type`
--

LOCK TABLES `cat_pedigree_type` WRITE;
/*!40000 ALTER TABLE `cat_pedigree_type` DISABLE KEYS */;
INSERT INTO `cat_pedigree_type` VALUES ('DELUXE'),('GOLD'),('SIMPLE');
/*!40000 ALTER TABLE `cat_pedigree_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cat_status`
--

DROP TABLE IF EXISTS `cat_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cat_status` (
  `id_status` char(5) NOT NULL,
  PRIMARY KEY (`id_status`),
  UNIQUE KEY `id_status_UNIQUE` (`id_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='1 - доступен для продажи, 2 - забронирован, 3 - продан';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cat_status`
--

LOCK TABLES `cat_status` WRITE;
/*!40000 ALTER TABLE `cat_status` DISABLE KEYS */;
INSERT INTO `cat_status` VALUES ('AVAIL'),('RSRV'),('SOLD');
/*!40000 ALTER TABLE `cat_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locale_language`
--

DROP TABLE IF EXISTS `locale_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `locale_language` (
  `locale_language_id` char(3) NOT NULL,
  PRIMARY KEY (`locale_language_id`),
  UNIQUE KEY `locale_language_id_UNIQUE` (`locale_language_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locale_language`
--

LOCK TABLES `locale_language` WRITE;
/*!40000 ALTER TABLE `locale_language` DISABLE KEYS */;
INSERT INTO `locale_language` VALUES ('EN'),('RU');
/*!40000 ALTER TABLE `locale_language` ENABLE KEYS */;
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
  `phone` varchar(15) NOT NULL,
  `colour_preference` char(1) DEFAULT NULL,
  `role_id` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '1 - пользователь\\\\n2 - админ\\\\n3 - оценщик',
  `discount` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT 'скидка в процентах',
  `flag_banned` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '1 - забанен админом за нарушение правил',
  `flag_review_left` tinyint(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `role_id_idx` (`role_id`),
  KEY `colour_preference_idx` (`colour_preference`),
  CONSTRAINT `colour_preference` FOREIGN KEY (`colour_preference`) REFERENCES `cat_colour` (`ems_code`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Таблица, которая будет хранить зарегистрированных пользователей';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$10$MhHOQ0dzEAd.1/td.9lGj.wiDwnf8m6kZbmwXPFwD2yXMFa7HP3DG','Майкл','Скотт','adm@mail.ru','29 0112358',NULL,2,0,0,0),(2,'cat','$2a$10$MhHOQ0dzEAd.1/td.9lGj.wiDwnf8m6kZbmwXPFwD2yXMFa7HP3DG','Mike','Atter','mike@mail.ru','29 3141592','F',1,10,0,0),(3,'koko','$2a$10$kThb.Euqqk6UzBhztUKoNuGcE1FHyyoTGuFS4GX6g4AL38pi/QdOm','Анна','Кристева','kristeva@yandex.com','29 6545344','Y',1,5,0,1),(4,'catlover','$2a$10$MhHOQ0dzEAd.1/td.9lGj.wiDwnf8m6kZbmwXPFwD2yXMFa7HP3DG','Виолетта','Рожденственская','vvvv@mail.ru','25 1228033','Y',1,0,0,1),(5,'expert','$2a$10$MhHOQ0dzEAd.1/td.9lGj.wiDwnf8m6kZbmwXPFwD2yXMFa7HP3DG','Светлана','Люлькович','sams@mail.ru','29 1688033',NULL,3,0,0,0),(6,'purr','$2a$10$MhHOQ0dzEAd.1/td.9lGj.wiDwnf8m6kZbmwXPFwD2yXMFa7HP3DG','Ада','Турова','kittens@gmail.ru','25 1619333','D',1,10,0,1),(7,'dog','$2a$10$MhHOQ0dzEAd.1/td.9lGj.wiDwnf8m6kZbmwXPFwD2yXMFa7HP3DG','Jake','Juke','or55h@yandex.com','29 1618133','W',1,0,0,0),(8,'ofa','$2a$10$MhHOQ0dzEAd.1/td.9lGj.wiDwnf8m6kZbmwXPFwD2yXMFa7HP3DG','Вера','Кошкина','vera@mail.ru','25 1888033',NULL,1,0,0,0),(10,'sima','$2a$10$9rynM1KLW2RZi8m1AWIg4uepugmTIxWBNvgHUsQCtbkNErZHQ4qSS','Simba','KorolLev','kira-smi@mail.ru','29 1619133','D',1,0,0,1),(11,'barsuk','$2a$10$/7/ZhFPKvq6Ov4rat2rhx.k.wuKshbFaH2kg24vSpHnPx04oVt9wa','Barsuk','Barsu4ij','ki@mail.ru','25 1418033','D',1,0,1,0),(12,'kotkot','$2a$10$jb8W38UoGrk8FxCOiaJd9egetYjG7JnAVoS9HEvgvlVwqqgWGOiQG','kolya','checkov','kir6mi@mail.ru','29 1619033','N',1,0,0,0),(13,'kroshka','$2a$10$bciwj/j0knZhhHcP1a8nFerBIEYXlqq90cB1KY1CexJbF.lzQl3H6','Duke','Horington','kir5555gami@mail.ru','29 1668033','A',1,0,0,0),(14,'grusha','$2a$10$Bt/bo.v4Y1kx3Lo0SToxR.M5ptek5dRhcmZE2xHZwqdqT/aJRZyPa','Boris','Petrovich','ki77ami@mail.ru','33 7978033',NULL,1,0,0,1),(15,'geraldina','$2a$10$4E8GrIQlQwN/Ox9SyWBp2u0iCbL2rAFd6hXOpxJ8kuOTLBBIubWwO','Анна','Как-нибудь','kira-980444mi@mail.ru','29 5558033','E',1,0,0,0),(16,'pupsik','$2a$10$ISSFNr.eo8ayOx7.nkbmSOWsL5Gjs0nJZMOmC1jvECnt7zmVhZVDS','Simba','Rassmeshi','simba@mail.ru','29 1690033','D',1,0,1,0),(18,'Mary-Ann','$2a$10$1p7zAokebfpMGdc.RzVgX.gnbWbAJT3q3zNA9mdvYunxmncNy73qS','Мария','Коржикова','korzhik@yandex.by','29 5618033',NULL,1,0,0,0),(19,'King','$2a$10$zXLPAdkBq6NPqRT1wM.mpeotSBmbyObW0ljkI/ITkrlHGjh4Vyjha','Собачий','Король','kingking23@mail.ru','44 8958586',NULL,1,0,0,0),(20,'CatsNice','$2a$10$PeTIaa82JEkaKXlckkGeZuYrnb6UPsQJ/D9MW2M8t0LH3ZNOOjfd6','Ваня','Котов','goward-kotov11@mail.ru','33 1618033','Q',1,0,0,1),(21,'Lapka','$2a$10$LCzETKCdAB0qnc7PQ.R6UuMPqABCbFs1mUsJXAIsnMps.lEWmAjsK','Витя','Терлецкий','vitka@yandex.by','33 1618033','F',1,5,0,1),(22,'Lancelap','$2a$10$YF/P21Sq4VbKNWT5kDwXwOcYr5imhDWJ3tGk0MG9UgGlqiwsjYGi.','Ланс','Лап','lancelap@gmail.com','29 8918013','N',1,0,0,0),(23,'RedCat','$2a$10$KgB.iawfjherzlHmqxKqQ.0os/gvIUaY7e1HidwQPjAazXxTpzGKG','Иван','Мельников','vanyaM@yandex.ru','29 1618033','D',1,0,0,0),(24,'Nameless','$2a$10$q2EQ2qObdDxqbStu/s5QkeNC43PC7OZZuvpxB2aHBDlyKJI6JBOdq','Иван','Иванов','torment-pl@mail.ru','29 1617433',NULL,1,0,0,0),(25,'котяра','$2a$10$54O6ZkUMYqMVPNliTD4krODfs6WZbV8d66j9K6gSZe3RWrzQUGwl2','Ян','Хвалей','hvaley@mail.ru','29 2455429','N',1,5,0,0),(30,'double','$2a$10$jWF9fn0FECpoYw.xC8Lw8.MoHcQe8Q9iev3nYoncpwavD5/zsT/oi','Дабла','Крекер','double@gmail.com','29 6323321',NULL,1,0,0,1),(31,'robthemob','$2a$10$cxxUPLsISiGrjq1kv6cX3.gxpwgFvmQuieTZDyAJSwPkH9ceq/bam','Вика','Третьякова','vika-tretyak@gmail.com','29 7362637',NULL,1,0,0,1),(32,'papuga','$2a$10$EyD2C41QlOvkOzhyfALn3.fvm7CXcPe2X/fkrq6gXcaIbnaIGW/9q','Василий','Торкин','papuga-guga@mail.ru','29 7636232','Q',1,0,0,1),(33,'superman','$2a$10$SWzyFFA1du5gvs8zN/yeWeT5lDkbI0ZpZpBJKRIWdpg3C5ckapuKK','Кларк','Кент','superman@yandex.by','29 8347372','S',1,0,0,1),(34,'pokemon','$2a$10$qe16RdNP43iWHvwZkI1yDeKKUEj69rpD9w5X6bgrAAQtH5cKekCQO','Саша','Хмельницкий','pokebol@gmail.ru','29 3234342',NULL,1,0,0,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_offer`
--

DROP TABLE IF EXISTS `user_offer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_offer` (
  `offer_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_made_offer_id` int(10) unsigned NOT NULL,
  `cat_description` text NOT NULL COMMENT 'описание на основании которого эксперт делает вывод. Характеристики породы. Хотя тут можно контактные данные для телефонного разговора оставить и проч , продумать',
  `offered_cat_photo` varchar(30) DEFAULT NULL COMMENT 'прилагаемая фотка',
  `price` decimal(20,2) unsigned DEFAULT NULL COMMENT 'цена, которую предложит эксперт. (при заполнении пользователь сам может указать желаемую цену, от которой эксперт будет отталкиваться) !!!! nprice - NOT NULL??????',
  `user_offer_status_id` char(5) NOT NULL DEFAULT 'AWAIT' COMMENT '1 - awaiting по умолчанию',
  `expert_message` text COMMENT 'то, что эксперт пишет, когда поясняет, пользователю почему отклоняет котенка или почему снижает цену',
  `expert_message_to_admin` text COMMENT 'данные для админа, чтобы тот добавил животное',
  `flag_offer_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`offer_id`),
  KEY `user_id_idx` (`user_made_offer_id`),
  KEY `user_offer_status_id_idx` (`user_offer_status_id`),
  CONSTRAINT `user_made_offer_id` FOREIGN KEY (`user_made_offer_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_offer_status_id` FOREIGN KEY (`user_offer_status_id`) REFERENCES `user_offer_status` (`offer_status_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_offer`
--

LOCK TABLES `user_offer` WRITE;
/*!40000 ALTER TABLE `user_offer` DISABLE KEYS */;
INSERT INTO `user_offer` VALUES (1,1,'Котенок питомника, добавленный админом',NULL,0.00,'SENT',NULL,NULL,0),(2,3,'Этот котенок очень любит мясо и молоко','offer1303193368605210581.jpg',420.00,'AWAIT',NULL,NULL,0),(3,25,'Черно-белый котяра. Белый медальон и галстук  в придачу к белым носочкам!','offer1956987230810080526.jpg',500.00,'AWAIT',NULL,NULL,0),(4,6,'Я нашла этого котенка  за несколько недель до смерти','offer8788522280027144517.jpg',700.00,'AWAIT',NULL,NULL,0),(5,22,'Все характеристики соответствуют породе. Есть документы.',NULL,550.00,'DISC','Мы заинтересованы в приобретении вашего котенка, но в связи с присутствующими дефектами (лапы недостаточно большие), предлагаем вам следующую цену.',NULL,0),(6,6,'Котенок премиум-класса, рожденный от победителей выставки \"Кот Мира 2008\"','offer8680588950082093292.jpg',1000.00,'AWAIT',NULL,NULL,0),(7,2,'Очень красивый, ласковый котенок, приучен к лотку, красивая блестящая шерсть. Любит купаться.',NULL,300.00,'SENT',NULL,'Очень дешево, надо брать!',0),(8,3,'Котенок умеет выполнять трюки. Пьет воду с помощью лап',NULL,500.00,'DISC','Как насчет 500? У вашего кота слишком короткий хвост!',NULL,0),(9,6,'Просто купите его пожалуйста',NULL,500.00,'SENT',NULL,'Красивые уши',0),(10,22,'Самый крутой кот на свете, отвечаю!!! ',NULL,300.00,'DISC','400 и по рукам?',NULL,0),(11,6,'Предлагаю так дешево, потому что у котенка небольшой недобор массы','offer2189223048871110737.jpg',250.00,'SENT',NULL,'Не указывать про недобор массы',0),(16,3,'Маленький горячий пирожок',NULL,79.00,'REJCT','не не не не',NULL,1),(20,3,'Кот на вес золота',NULL,700.00,'SENT',NULL,'Кот похож на драгоценность',0),(29,31,'Проверка что все срабоьет','offer4618052843810997040.jpg',777.00,'AWAIT',NULL,NULL,1),(31,31,'Проверка статуса','offer3938942526609147375.jpg',666.00,'DISC','кеукеуке',NULL,1),(32,3,'Ghjkjkj','offer3852711772001766026.jpg',123.00,'DISC','выаол',NULL,1),(33,19,'Этот кот победитель по жизни','offer1950840749877753403.jpg',300.00,'SENT',NULL,'Отредактировать фотографию',0),(34,20,'Кот проверяющий загрузку картинок ','offer5022040088048988746.jpg',554.00,'SENT',NULL,'Кот проверяющий загрузку картинок',0),(35,3,'Прекрасный кот, который проверит мне работает ли все как надо сейчас','offer5246146400902396934.jpg',300.00,'SENT',NULL,NULL,0),(36,20,'Просто посмотрите на фотографию','offer563742148779508640.jpg',350.00,'AWAIT',NULL,NULL,0),(43,3,'вапвапвап','offer3702765196028177867.jpg',400.00,'SENT',NULL,'Да как-то тут и нечего сказать',0),(44,20,'Кот проверяющий что все работает после обновы комманд','offer8386040540062044300.jpg',500.00,'SENT',NULL,NULL,0),(45,3,'dfsfsd','offer7540283564693479432.jpg',420.00,'SENT',NULL,NULL,0),(46,34,'Большие лапы и большая морда. И это все, что о нем можно сказать','offer819898647174103058.jpg',300.00,'AWAIT',NULL,NULL,0),(47,32,'сопровождает своего хозяина во всех повседневных действиях и хотел бы поработать с ним','offer7289513405090096902.jpg',330.00,'AWAIT',NULL,NULL,0);
/*!40000 ALTER TABLE `user_offer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_offer_status`
--

DROP TABLE IF EXISTS `user_offer_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_offer_status` (
  `offer_status_id` char(5) NOT NULL COMMENT 'AWAIT - ждет рассмотрения, REJCT - отклонен, DISC - цена обсуждается, APRVD - одобрен и отправлен админу, SENT - Размещен на сайте',
  PRIMARY KEY (`offer_status_id`),
  UNIQUE KEY `offer_status_id_UNIQUE` (`offer_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Пользователь предлагает котенка. Эксперт отклоняет либо одобряет. если одобряет и предлагает пользователю цену. Пользователь может не согласиться или согласиться и тогда предложение направляется админу для добавления на сайт котенка.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_offer_status`
--

LOCK TABLES `user_offer_status` WRITE;
/*!40000 ALTER TABLE `user_offer_status` DISABLE KEYS */;
INSERT INTO `user_offer_status` VALUES ('APRVD'),('AWAIT'),('DISC'),('REJCT'),('SENT');
/*!40000 ALTER TABLE `user_offer_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_reservation`
--

DROP TABLE IF EXISTS `user_reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_reservation` (
  `reservation_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `cat_id` int(10) unsigned NOT NULL,
  `pedigree_type` char(6) NOT NULL DEFAULT 'SIMPLE',
  `reservation_date` timestamp NOT NULL,
  `total_cost` decimal(20,2) unsigned NOT NULL COMMENT 'Это должна быть сумма оплаты с учетом скидки',
  `reservation_status` char(4) NOT NULL DEFAULT 'NEW',
  `flag_reservation_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `cheque_photo` varchar(30) DEFAULT NULL COMMENT 'Фото оплаты',
  PRIMARY KEY (`reservation_id`),
  UNIQUE KEY `order_id_UNIQUE` (`reservation_id`),
  KEY `user_id_idx` (`user_id`),
  KEY `pedigree_type_idx` (`pedigree_type`),
  KEY `reservation_status_idx` (`reservation_status`),
  KEY `cat_id_idx` (`cat_id`),
  CONSTRAINT `cat_id` FOREIGN KEY (`cat_id`) REFERENCES `cat` (`cat_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pedigree_type` FOREIGN KEY (`pedigree_type`) REFERENCES `cat_pedigree_type` (`pedigree_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reservation_status` FOREIGN KEY (`reservation_status`) REFERENCES `user_reservation_status` (`reservation_status_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Эта таблица представляет собой заказ пользователя';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_reservation`
--

LOCK TABLES `user_reservation` WRITE;
/*!40000 ALTER TABLE `user_reservation` DISABLE KEYS */;
INSERT INTO `user_reservation` VALUES (82,20,66,'DELUXE','2018-06-02 16:08:30',710.00,'EXPD',1,''),(84,3,66,'DELUXE','2018-08-02 16:53:26',675.00,'NEW',1,''),(85,3,66,'DELUXE','2018-08-02 17:07:16',675.00,'NEW',1,''),(86,20,13,'GOLD','2018-08-03 20:07:39',570.00,'NEW',1,''),(87,3,66,'GOLD','2018-08-03 20:45:04',761.00,'NEW',1,''),(88,3,69,'GOLD','2018-08-04 22:16:28',542.50,'NEW',1,''),(89,31,3,'SIMPLE','2018-08-05 22:16:59',500.00,'EXPD',0,''),(90,3,41,'SIMPLE','2018-08-07 23:32:27',532.00,'DONE',0,'cheque4405657074141479613.jpg'),(91,3,17,'SIMPLE','2018-08-07 20:05:20',475.00,'NEW',1,''),(92,11,17,'GOLD','2018-08-08 16:09:06',520.00,'NEW',1,NULL),(93,11,1,'DELUXE','2018-08-08 16:09:18',410.00,'NEW',1,NULL),(94,11,13,'DELUXE','2018-08-08 16:41:11',560.00,'NEW',1,NULL),(95,3,56,'DELUXE','2018-08-15 23:41:28',748.10,'NEW',0,NULL),(96,33,17,'DELUXE','2018-08-12 02:11:05',510.00,'NEW',1,NULL),(97,33,15,'SIMPLE','2018-08-10 02:13:23',550.00,'NEW',1,NULL),(98,3,17,'SIMPLE','2018-08-13 13:41:36',475.00,'NEW',1,NULL),(99,3,71,'SIMPLE','2018-08-13 13:49:03',526.30,'NEW',1,NULL),(100,33,17,'SIMPLE','2018-08-14 18:45:12',500.00,'NEW',0,NULL),(101,33,80,'SIMPLE','2018-08-10 18:50:34',320.00,'EXPD',1,NULL),(102,3,80,'SIMPLE','2018-08-14 18:52:23',304.00,'NEW',1,NULL),(103,3,6,'SIMPLE','2018-08-15 23:09:12',427.50,'NEW',0,'cheque4639522035561695611.jpg');
/*!40000 ALTER TABLE `user_reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_reservation_status`
--

DROP TABLE IF EXISTS `user_reservation_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_reservation_status` (
  `reservation_status_id` char(4) NOT NULL,
  PRIMARY KEY (`reservation_status_id`),
  UNIQUE KEY `reservation_status_id_UNIQUE` (`reservation_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_reservation_status`
--

LOCK TABLES `user_reservation_status` WRITE;
/*!40000 ALTER TABLE `user_reservation_status` DISABLE KEYS */;
INSERT INTO `user_reservation_status` VALUES ('DONE'),('EXPD'),('NEW');
/*!40000 ALTER TABLE `user_reservation_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_review`
--

DROP TABLE IF EXISTS `user_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_review` (
  `review_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_left_id` int(10) unsigned NOT NULL,
  `review_text` text NOT NULL COMMENT 'Может, использовать VARCHAR(2000)?',
  `stars_count` int(1) unsigned NOT NULL,
  `date` date NOT NULL,
  `flag_review_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`review_id`),
  KEY `user_id_idx` (`user_left_id`),
  CONSTRAINT `feedback_user_id` FOREIGN KEY (`user_left_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Отзыв, который пользователь может оставить о питомнике';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_review`
--

LOCK TABLES `user_review` WRITE;
/*!40000 ALTER TABLE `user_review` DISABLE KEYS */;
INSERT INTO `user_review` VALUES (1,4,'I give this cattery a 5 star review!',5,'2018-07-24',0),(2,6,'Use your common sense. Her reviews are all 5 stars or 1 star. What does that tell you?',1,'2018-06-11',0),(3,21,'Прекрасные коты! Очень большие и здоровые. 10 из 10!',5,'2018-05-24',0),(6,3,'Мне все нравится! Я куплю кого-нибудь еще у вас',5,'2018-08-10',0),(10,30,'Я искала британских котят, но это не британские коты. лол',2,'2018-07-28',0),(24,20,'Сейчас все должно сработать  урААА',5,'2018-07-30',1),(26,20,'Котики',4,'2018-08-02',1),(28,20,'Видно, что котята питаются хорошо. Я тоже так хочу!',4,'2018-08-03',0),(29,31,'Каааааак же жаааарко сегодня',1,'2018-08-05',0),(30,32,'Недавно продал сюда своего котенка. Теперь вижу его фотки у кого-то в германии. Завидую',5,'2018-08-10',0),(31,33,'Моя знакомая купила здесь котенка. Теперь я коплю деньги на такого же!',5,'2018-08-12',0),(32,10,'Купили моего котенка дешево, а продали дорого!\r\nНо персонал очень вежливый',3,'2018-08-12',0),(33,14,'Очень доволен, что мой новый котенок уже умеет ходить в лоток и у него нет блох, а что еще нужно от кота !!!!',5,'2018-08-12',0);
/*!40000 ALTER TABLE `user_review` ENABLE KEYS */;
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
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_UNIQUE` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Таблица с ролями. 1) Пользователь - покупает котенка / предлагает котенка в питомник 2) Админ - будет наполнять сайт контентом и редактировать его 3) Оценщик - оценивать предложенных пользователем котят, которых после Админ будет размещать. (Хотя, может, эти роли можно совместить)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (2,'admin'),(3,'expert'),(1,'user');
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

-- Dump completed on 2018-08-16  3:47:20
