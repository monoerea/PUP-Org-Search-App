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
-- Table structure for table `tbluserdata`
--

DROP TABLE IF EXISTS `tbluserdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbluserdata` (
  `strStudentID` varchar(15) NOT NULL,
  `strFirstName` varchar(45) NOT NULL,
  `strMiddleName` varchar(45) NOT NULL,
  `strLastName` varchar(45) NOT NULL,
  `strCollege` varchar(60) NOT NULL,
  `strEmail` varchar(45) NOT NULL,
  `strPassword` varchar(45) NOT NULL,
  PRIMARY KEY (`strStudentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbluserdata`
--

LOCK TABLES `tbluserdata` WRITE;
/*!40000 ALTER TABLE `tbluserdata` DISABLE KEYS */;
INSERT INTO `tbluserdata` VALUES ('','First Name...','Middle Name...','Last Name...','College...','Email...','Password...'),('2016-03333-MN-0','Frank','','Woods','College of Human Kinetics','fwoods@gmail.com','pass123word'),('2018-05433-MN-0','Marquis','de','Lafayette','College of Political Science and Public Administration','mdlafayette@gmail.com','password1757'),('2019-06599-MN-0','Alex','','Mason','College of Business Administration','amason@gmail.com','123password'),('2019-06744-MN-0','Bryan Kristoffer','Montemayor','Ferrer','College of Computer and Information Sciences','bryankmferrer@gmail.com','password123');
/*!40000 ALTER TABLE `tbluserdata` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-27 17:46:03
