-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: just_instruments
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `master_instruments`
--

DROP TABLE IF EXISTS `master_instruments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `master_instruments` (
  `asset_no` int(11) NOT NULL,
  `description` varchar(500) NOT NULL,
  `make` varchar(200) NOT NULL,
  `model` varchar(250) NOT NULL,
  `instrument_serial_no` varchar(100) NOT NULL,
  `cal_date` date DEFAULT NULL,
  `due_date` date NOT NULL,
  PRIMARY KEY (`asset_no`),
  UNIQUE KEY `instrument_serial_no_UNIQUE` (`instrument_serial_no`)
) ENGINE=MyISAM AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `master_instruments`
--

LOCK TABLES `master_instruments` WRITE;
/*!40000 ALTER TABLE `master_instruments` DISABLE KEYS */;
INSERT INTO `master_instruments` VALUES (1,'Multimeter','Rishab','Multi 18S','154255',NULL,'2020-08-02'),(2,'Temp Calibrator','Fluke','Fluke 724','1667065',NULL,'2020-08-02'),(3,'Rh Calibrator','Thermoworks','6002 Hygrometer','D18141104',NULL,'2019-09-04'),(4,'Dry Block Calibrator','Tel-Tru','Check-set 2','6398',NULL,'2020-08-02'),(5,'Dry Block Calibrator','Accurate Thermal System','Thermcall 400','717-2627',NULL,'2020-08-02'),(6,'Pressure Gauge','DPG','DPG-254LM','1611070065',NULL,'2020-08-02'),(7,'Pressure Gauge','Winters','DPG225','Y8B757',NULL,'2020-03-14'),(8,'Pressure Calibrator','Ametek','CPC200CINDG','8491002',NULL,'2020-08-02'),(9,'Universal Calibrator','Masibus','UC-12','18070242',NULL,'2020-01-16'),(10,'Loop Calibrator','Masibus','LC-11','18129482',NULL,'2020-06-14'),(11,'RTD Simulator','Masibus','RS-12','17080035',NULL,'2020-05-15'),(12,'Laser Distance Meter','Shimana','SHSESM076','11083778',NULL,'2019-09-26'),(13,'Resistance Decade Box','Extech Instruments','380400','H.170060',NULL,'2020-05-15'),(14,'Lab Incubator','Ivyx scientific','3BCR-25','1014',NULL,'2020-05-15'),(15,'Lab Temp Indicator','Selec','PIC1010-N','1206J06-152',NULL,'2020-05-15'),(16,'4-20 mA Signal source','Lany1','1','1001',NULL,'2020-05-15'),(17,'Calibration Gas','NorLAB','Hydrogen Sulfide Nitrogen 50 ppm','7-186-6',NULL,'2020-11-08'),(18,'Calibration Gas','NorLAB','Methane Air 50%','8-225-781',NULL,'2021-11-08'),(19,'Viscosity Standard','Cannon Instrument','S600','19301',NULL,'2021-05-31'),(20,'Weight 90kg','MSSI S2/15/L8-L10','10 kg to 20 kg, 90 kg','9345345',NULL,'2020-06-05'),(21,'Weight Measuring Kit','Shan Dong Pong Lai','5kg to 1mg','1328',NULL,'2019-07-20'),(22,'Weight 2kg & 5kg','OIML R111','Class M1 g/mg','A',NULL,'2019-09-12'),(23,'Ammonia gas','NorLAB','Ammonia ','9-126-733',NULL,'2020-07-29'),(24,'Ammonia gas','NorLAB','Ammonia ','2147483647',NULL,'2020-07-29'),(25,'Ammonia gas','NorLAB','Ammonia ','66666',NULL,'2020-07-29');
/*!40000 ALTER TABLE `master_instruments` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-17 11:15:42
