-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: dbpuporgsearch
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tblpostsdata`
--

DROP TABLE IF EXISTS `tblpostsdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblpostsdata` (
  `strPostID` varchar(45) NOT NULL,
  `strLocation` varchar(45) NOT NULL,
  `strStudentID` varchar(45) NOT NULL,
  `strTitle` varchar(45) NOT NULL,
  `strBody` varchar(500) DEFAULT NULL,
  `dtime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=armscii8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblpostsdata`
--

LOCK TABLES `tblpostsdata` WRITE;
/*!40000 ALTER TABLE `tblpostsdata` DISABLE KEYS */;
INSERT INTO `tblpostsdata` VALUES ('null','null','null','An interesting title','Your text post (optional)','2022-02-27 13:51:17'),('null','null','null','lawrd dassss','helloooo','2022-02-27 14:10:22'),('null','null','null','titleeeee','body body body','2022-02-27 14:10:37'),('null','null','null','emotional','damage','2022-02-27 14:10:43'),('null','null','null','gasdasdasf','Your text post (optional)','2022-02-27 17:30:54'),('null','null','null','happy birthday matthew','happy birthday matthew','2022-02-27 17:33:56');
/*!40000 ALTER TABLE `tblpostsdata` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-27 17:46:04
