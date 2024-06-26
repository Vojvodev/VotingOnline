-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: localhost    Database: online_voting_db
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `campaigns`
--

DROP TABLE IF EXISTS `campaigns`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `campaigns` (
  `id` int NOT NULL AUTO_INCREMENT,
  `parties_id` int NOT NULL,
  `website` varchar(255) DEFAULT NULL,
  `social_media` varchar(255) DEFAULT NULL,
  `slogan` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_campaign_parties1_idx` (`parties_id`),
  CONSTRAINT `fk_campaign_parties1` FOREIGN KEY (`parties_id`) REFERENCES `parties` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campaigns`
--

LOCK TABLES `campaigns` WRITE;
/*!40000 ALTER TABLE `campaigns` DISABLE KEYS */;
INSERT INTO `campaigns` VALUES (1,1,'https://www.srpskaradikalnastranka.org.rs/','@srpska_radikalna_stranka','S verom u Boga, Za Kralja i Otadzbinu'),(2,2,'https://sr.wikipedia.org/sr-el/%D0%A1%D1%80%D0%BF%D1%81%D0%BA%D0%B0_%D0%B4%D0%B5%D0%BC%D0%BE%D0%BA%D1%80%D0%B0%D1%82%D1%81%D0%BA%D0%B0_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%BA%D0%B0','@sds','Neki slogan za SDS');
/*!40000 ALTER TABLE `campaigns` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidate_statistics`
--

DROP TABLE IF EXISTS `candidate_statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidate_statistics` (
  `id` int NOT NULL AUTO_INCREMENT,
  `total_votes` int DEFAULT '0',
  `percentage` decimal(10,0) DEFAULT '0',
  `candidates_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_candidate_statistics_candidates1_idx` (`candidates_id`),
  CONSTRAINT `fk_candidate_statistics_candidates1` FOREIGN KEY (`candidates_id`) REFERENCES `candidates` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate_statistics`
--

LOCK TABLES `candidate_statistics` WRITE;
/*!40000 ALTER TABLE `candidate_statistics` DISABLE KEYS */;
/*!40000 ALTER TABLE `candidate_statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidates`
--

DROP TABLE IF EXISTS `candidates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidates` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `parties_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_candidates_parties1_idx` (`parties_id`),
  CONSTRAINT `fk_candidates_parties1` FOREIGN KEY (`parties_id`) REFERENCES `parties` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidates`
--

LOCK TABLES `candidates` WRITE;
/*!40000 ALTER TABLE `candidates` DISABLE KEYS */;
INSERT INTO `candidates` VALUES (1,'Kandidat1Ime','Kandidat1Prezime',1,'2024-04-26 16:06:46'),(2,'Kandidat2Ime','Kandidat2Prezime',1,'2024-04-26 16:06:46'),(3,'Kandidat3Ime','Kandidat3Prezime',2,'2024-04-26 16:06:46'),(4,'Noviji Kandidat','Novije Prezime',2,'2024-05-11 10:52:16'),(6,'Najnoviji Ikad','asdasdasd',2,'2024-05-14 12:43:51');
/*!40000 ALTER TABLE `candidates` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `candidates_AFTER_INSERT` AFTER INSERT ON `candidates` FOR EACH ROW BEGIN
	INSERT INTO candidate_statistics (total_votes, candidates_id) VALUES (0, NEW.id);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `elections`
--

DROP TABLE IF EXISTS `elections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `elections` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `year` date DEFAULT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `active` tinyint DEFAULT '1',
  `voting_lists_id` int DEFAULT NULL,
  `total_votes` int unsigned DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `str_klj` (`voting_lists_id`),
  CONSTRAINT `str_klj` FOREIGN KEY (`voting_lists_id`) REFERENCES `voting_lists` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elections`
--

LOCK TABLES `elections` WRITE;
/*!40000 ALTER TABLE `elections` DISABLE KEYS */;
INSERT INTO `elections` VALUES (1,'Predsjednicki izbori','2024-01-01','2024-01-01','2024-12-31','predsjednicki',1,NULL,1),(2,'Opstinski izbori u Bijeljini','2024-01-01','2024-01-01','2024-12-31','lokalni',0,NULL,0),(3,'asdasd','2021-01-01','2021-12-02','2021-12-02','adwqdq',1,NULL,1),(6,'tttttt','2029-01-01','2020-12-01','2020-12-02','ffff',1,NULL,0);
/*!40000 ALTER TABLE `elections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs`
--

DROP TABLE IF EXISTS `logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `users_id` int NOT NULL,
  `action` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_users_logs_users1_idx` (`users_id`),
  CONSTRAINT `fk_users_logs_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs`
--

LOCK TABLES `logs` WRITE;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
INSERT INTO `logs` VALUES (1,1,'Could Not Create Candidate!','2024-05-14 12:43:28'),(2,1,'Could Not Create Candidate!','2024-05-14 12:43:38'),(3,1,'Candidate Successfully Created!','2024-05-14 12:43:51'),(6,1,'Successfully Logged In!','2024-05-14 13:07:12'),(7,1,'Candidate Successfully Created!','2024-05-14 13:07:43'),(8,1,'Candidate Already Exists!','2024-05-14 13:07:50'),(9,1,'Candidate Successfully Deleted!','2024-05-14 13:08:14'),(10,1,'Could Not Delete User!','2024-05-14 13:08:27'),(11,1,'User Successfully Deleted!','2024-05-14 13:09:24'),(12,1,'Active Elections With That Name Already Exist!','2024-05-14 13:10:06'),(13,1,'Elections Successfully Created!','2024-05-14 13:10:20'),(14,1,'Elections Successfully Deleted!','2024-05-14 13:10:42'),(15,1,'Successfully Logged In!','2024-05-14 13:38:01'),(16,1,'Successfully Logged In!','2024-05-15 06:16:34'),(17,1,'Successfully Logged In!','2024-05-15 10:29:34'),(18,1,'Candidate Successfully Created!','2024-05-15 10:29:48'),(19,1,'Candidate Successfully Updated!','2024-05-15 10:30:12'),(20,1,'Could Not Delete Candidate!','2024-05-15 10:30:21'),(21,1,'Candidate Successfully Deleted!','2024-05-15 10:31:04'),(22,1,'User Successfully Created!','2024-05-15 10:31:21'),(23,1,'User Successfully Updated!','2024-05-15 10:31:46'),(24,1,'User Successfully Deleted!','2024-05-15 10:31:53'),(25,1,'Elections Successfully Created!','2024-05-15 10:32:27'),(26,1,'Elections Successfully Updated!','2024-05-15 10:33:16'),(27,1,'Successfully Logged In!','2024-05-15 10:40:12'),(28,1,'Elections Successfully Updated!','2024-05-15 10:42:49'),(29,1,'Successfully Logged In!','2024-05-15 10:46:29'),(30,1,'Elections Successfully Updated!','2024-05-15 10:47:05'),(31,1,'Elections Successfully Updated!','2024-05-15 10:48:08'),(32,1,'Successfully Logged In!','2024-05-15 10:50:31'),(33,1,'Successfully Logged In!','2024-05-15 10:56:02'),(34,1,'Successfully Logged In!','2024-05-15 11:01:58'),(35,1,'Successfully Logged In!','2024-05-15 11:08:03'),(36,1,'Successfully Logged In!','2024-05-15 11:11:45'),(37,1,'Successfully Logged In!','2024-05-15 11:15:03'),(38,1,'Successfully Logged In!','2024-05-15 11:17:18'),(39,1,'Successfully Logged In!','2024-05-15 11:24:55');
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (1,'You still have not placed your vote.'),(2,'Thank you for voting.');
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parties`
--

DROP TABLE IF EXISTS `parties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parties` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `about` longtext,
  `leader` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parties`
--

LOCK TABLES `parties` WRITE;
/*!40000 ALTER TABLE `parties` DISABLE KEYS */;
INSERT INTO `parties` VALUES (1,'Srpska Radikalna Stranka','Srpska radikalna stranka u svom programu navodi, kao najzna─ìajniji politi─ìki cilj, stvaranje Velike Srbije (granice KarlobagΓÇöOgulinΓÇöKarlovacΓÇöVirovitica). Prema ┼áe┼íeljevim re─ìima, koncept Velike Srbije predstavlja smisao postojanja stranke.','Vojislav Seselj','2024-04-26 16:06:21'),(2,'Srpska Demokratska Stranka','Osniva─ì Srpske demokratske stranke u Hrvatskoj je bio Jovan Ra┼íkovi─ç. U toku 1990. stranka u SFRJ je osnovala filijalu SDS za podru─ìje SR Bosne i Hercegovine, a nakon toga SDS Republike Srpske, SDS Republike Srpske Krajine i filijalu u SR Srbiji i SR Crnoj Gori. Krovna organizacija je SDS Srpskih zemalja ─ìiji je predsednik do 1996. godine bio Radovan Karad┼╛i─ç.','Dragan Dabic','2024-04-26 16:06:21');
/*!40000 ALTER TABLE `parties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `results`
--

DROP TABLE IF EXISTS `results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `results` (
  `id` int NOT NULL AUTO_INCREMENT,
  `elections_id` int(10) unsigned zerofill NOT NULL,
  `declared_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `winner_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_results_elections1_idx` (`elections_id`),
  KEY `fk_results_candidates1_idx` (`winner_id`),
  CONSTRAINT `fk_results_candidates1` FOREIGN KEY (`winner_id`) REFERENCES `candidates` (`id`),
  CONSTRAINT `fk_results_elections1` FOREIGN KEY (`elections_id`) REFERENCES `elections` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `results`
--

LOCK TABLES `results` WRITE;
/*!40000 ALTER TABLE `results` DISABLE KEYS */;
/*!40000 ALTER TABLE `results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `voted` tinyint(1) DEFAULT '0',
  `admin` tinyint(1) DEFAULT '0',
  `address` varchar(255) DEFAULT NULL,
  `voting_locations_id` int DEFAULT '3',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_users_voting_location1_idx` (`voting_locations_id`),
  CONSTRAINT `fk_users_voting_location1` FOREIGN KEY (`voting_locations_id`) REFERENCES `voting_locations` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Darijo','Prerad','dp','dp','darijonanetu@gmail.com',1,1,'Darijeva Adresa',1,'2024-04-26 15:47:49'),(2,'Zoran','ZoranovoPrezime','zp','C93FA6462B0056C124A590D56EE1A9631F35BBA92B54CF263EDD9DF871B93E45','zoranovmejl@gmail.com',1,0,'Zoranova Adresa',2,'2024-04-26 16:21:39'),(5,'Goran','GoranovoPrezime','gp','gp','goranovmejl@gmail.com',1,0,'Goranova Adresa',3,'2024-04-26 16:40:57'),(10,'Marko','Markovic','mk','mk','mk@mk',1,0,'adressssa',3,'2024-05-14 09:47:39');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `users_AFTER_INSERT` AFTER INSERT ON `users` FOR EACH ROW BEGIN
	CALL add_notification(NEW.id, 1);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `users_AFTER_UPDATE` AFTER UPDATE ON `users` FOR EACH ROW BEGIN
	IF NEW.voted = 1 THEN
		CALL remove_notification(NEW.id, 1);
        CALL add_notification(NEW.id, 2);
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `users_feedback`
--

DROP TABLE IF EXISTS `users_feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_feedback` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_feedback`
--

LOCK TABLES `users_feedback` WRITE;
/*!40000 ALTER TABLE `users_feedback` DISABLE KEYS */;
INSERT INTO `users_feedback` VALUES (1,'Jako dobra aplikacija ja ne znam sta bih radila da nisam u svom zivotu vidjela da neko ovako\ndobru aplikaciju napravi. Uuuuuuuu ','2024-05-15 07:00:03');
/*!40000 ALTER TABLE `users_feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_has_notifications`
--

DROP TABLE IF EXISTS `users_has_notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_has_notifications` (
  `users_id` int NOT NULL,
  `notifications_id` int NOT NULL,
  PRIMARY KEY (`users_id`,`notifications_id`),
  KEY `fk_users_has_messages_messages1_idx` (`notifications_id`),
  KEY `fk_users_has_messages_users1_idx` (`users_id`),
  CONSTRAINT `fk_users_has_messages_messages1` FOREIGN KEY (`notifications_id`) REFERENCES `notifications` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_users_has_messages_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_has_notifications`
--

LOCK TABLES `users_has_notifications` WRITE;
/*!40000 ALTER TABLE `users_has_notifications` DISABLE KEYS */;
INSERT INTO `users_has_notifications` VALUES (5,2),(10,2);
/*!40000 ALTER TABLE `users_has_notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `votes`
--

DROP TABLE IF EXISTS `votes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `votes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `candidates_id` int NOT NULL,
  `users_id` int NOT NULL,
  `elections_id` int unsigned DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_votes_candidates1_idx` (`candidates_id`),
  KEY `fk_votes_users1_idx` (`users_id`),
  KEY `fk_votes_elections` (`id`),
  KEY `fk_votes_elections_idx` (`elections_id`),
  CONSTRAINT `fk_votes_candidates1` FOREIGN KEY (`candidates_id`) REFERENCES `candidates` (`id`),
  CONSTRAINT `fk_votes_elections` FOREIGN KEY (`elections_id`) REFERENCES `elections` (`id`),
  CONSTRAINT `fk_votes_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `votes`
--

LOCK TABLES `votes` WRITE;
/*!40000 ALTER TABLE `votes` DISABLE KEYS */;
INSERT INTO `votes` VALUES (1,1,2,1,'2024-04-26 16:38:02'),(3,2,5,1,'2024-04-26 16:44:25'),(5,3,1,3,'2024-05-13 17:12:37'),(8,1,1,1,'2024-05-13 17:22:59'),(9,1,10,1,'2024-05-14 10:03:24');
/*!40000 ALTER TABLE `votes` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `votes_BEFORE_INSERT` BEFORE INSERT ON `votes` FOR EACH ROW BEGIN
	UPDATE candidate_statistics SET total_votes = total_votes + 1 WHERE candidate_statistics.candidate_id = NEW.candidates_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `votes_AFTER_INSERT` AFTER INSERT ON `votes` FOR EACH ROW BEGIN
	UPDATE elections SET total_votes = total_votes + 1 WHERE elections.id = NEW.elections_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `voting_lists`
--

DROP TABLE IF EXISTS `voting_lists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voting_lists` (
  `id` int NOT NULL AUTO_INCREMENT,
  `elections_id` int(10) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_voting_list_elections1_idx` (`elections_id`),
  CONSTRAINT `fk_voting_list_elections1` FOREIGN KEY (`elections_id`) REFERENCES `elections` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voting_lists`
--

LOCK TABLES `voting_lists` WRITE;
/*!40000 ALTER TABLE `voting_lists` DISABLE KEYS */;
/*!40000 ALTER TABLE `voting_lists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voting_lists_has_candidates`
--

DROP TABLE IF EXISTS `voting_lists_has_candidates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voting_lists_has_candidates` (
  `voting_lists_id` int NOT NULL,
  `candidates_id` int NOT NULL,
  PRIMARY KEY (`voting_lists_id`,`candidates_id`),
  KEY `fk_voting_lists_has_candidates_candidates1_idx` (`candidates_id`),
  KEY `fk_voting_lists_has_candidates_voting_lists1_idx` (`voting_lists_id`),
  CONSTRAINT `fk_voting_lists_has_candidates_candidates1` FOREIGN KEY (`candidates_id`) REFERENCES `candidates` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_voting_lists_has_candidates_voting_lists1` FOREIGN KEY (`voting_lists_id`) REFERENCES `voting_lists` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voting_lists_has_candidates`
--

LOCK TABLES `voting_lists_has_candidates` WRITE;
/*!40000 ALTER TABLE `voting_lists_has_candidates` DISABLE KEYS */;
/*!40000 ALTER TABLE `voting_lists_has_candidates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voting_locations`
--

DROP TABLE IF EXISTS `voting_locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voting_locations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `street_address` varchar(255) DEFAULT NULL,
  `voting_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voting_locations`
--

LOCK TABLES `voting_locations` WRITE;
/*!40000 ALTER TABLE `voting_locations` DISABLE KEYS */;
INSERT INTO `voting_locations` VALUES (1,'Darijeva Adresa','Adresa Glasanja1'),(2,'Zoranova Adresa','Adresa Glasanja2'),(3,'Sve Ostale Adrese','Adresa GlasanjaSVI');
/*!40000 ALTER TABLE `voting_locations` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-15 14:13:25
