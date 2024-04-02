-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: 17_nguyenquanghuy_manageuser
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `mst_group`
--

DROP TABLE IF EXISTS `mst_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mst_group` (
  `group_id` int NOT NULL AUTO_INCREMENT COMMENT 'mã nhóm',
  `group_name` varchar(255) NOT NULL COMMENT 'tên nhóm',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_group`
--

LOCK TABLES `mst_group` WRITE;
/*!40000 ALTER TABLE `mst_group` DISABLE KEYS */;
INSERT INTO `mst_group` VALUES (1,'Phòng phát triển số 1'),(2,'phòng phát triển số 2'),(3,'phòng phát triển số 3'),(4,'phòng phát triển số 4'),(5,'phòng phát triển số 5');
/*!40000 ALTER TABLE `mst_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mst_japan`
--

DROP TABLE IF EXISTS `mst_japan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mst_japan` (
  `code_level` varchar(15) NOT NULL COMMENT 'mã level tiêng Nhật',
  `name_level` varchar(255) NOT NULL COMMENT 'tên level tiếng Nhật',
  PRIMARY KEY (`code_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_japan`
--

LOCK TABLES `mst_japan` WRITE;
/*!40000 ALTER TABLE `mst_japan` DISABLE KEYS */;
INSERT INTO `mst_japan` VALUES ('N1','trình độ tiếng Nhật cấp 1'),('N2','trình độ tiếng Nhật cấp 2'),('N3','trình độ tiếng Nhật cấp 3'),('N4','trình độ tiếng Nhật cấp 4'),('N5','trình độ tiếng Nhật cấp 5');
/*!40000 ALTER TABLE `mst_japan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_detail_user_japan`
--

DROP TABLE IF EXISTS `tbl_detail_user_japan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_detail_user_japan` (
  `detail_user_japan_id` int NOT NULL AUTO_INCREMENT COMMENT 'id chi tiết của user',
  `user_id` int NOT NULL COMMENT 'id của user',
  `code_level` varchar(15) NOT NULL COMMENT 'mã level tiếng Nhật',
  `start_date` date NOT NULL COMMENT 'ngày cấp chứng chỉ',
  `end_date` date NOT NULL COMMENT 'ngày hết hạn',
  `total` int NOT NULL COMMENT 'tổng điểm',
  PRIMARY KEY (`detail_user_japan_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tbl_detail_user_japan_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_detail_user_japan`
--

LOCK TABLES `tbl_detail_user_japan` WRITE;
/*!40000 ALTER TABLE `tbl_detail_user_japan` DISABLE KEYS */;
INSERT INTO `tbl_detail_user_japan` VALUES (1,1,'N1','2005-07-07','2006-07-07',180),(2,2,'N2','2005-07-07','2006-07-07',160),(3,3,'N3','2006-07-09','2007-07-09',150),(4,4,'N4','2006-09-08','2007-09-08',120),(5,5,'N5','2007-07-06','2008-07-06',100),(6,6,'N1','2004-09-08','2005-09-08',170),(7,7,'N1','2006-12-02','2007-12-02',142),(8,8,'N2','2004-09-07','2005-09-07',150),(9,9,'N3','2003-11-13','2004-11-13',140),(10,10,'N4','2005-07-06','2006-07-06',120),(11,11,'N5','2011-09-08','2012-05-04',90),(12,12,'N3','2021-09-09','2022-09-09',150);
/*!40000 ALTER TABLE `tbl_detail_user_japan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT 'user id',
  `group_id` int NOT NULL COMMENT 'mã nhóm',
  `login_name` varchar(15) NOT NULL COMMENT 'tên đăng nhập',
  `password` varchar(50) NOT NULL COMMENT 'mật khẩu',
  `full_name` varchar(255) NOT NULL COMMENT 'họ tên user',
  `full_name_kana` varchar(255) DEFAULT NULL COMMENT 'họ tên kana của user',
  `email` varchar(255) NOT NULL COMMENT 'địa chỉ mail',
  `tel` varchar(15) NOT NULL COMMENT 'số điện thoại',
  `birthday` date NOT NULL COMMENT 'ngày sinh',
  `rule` int NOT NULL COMMENT 'phân quyên.0:admin, 1:user',
  `salt` varchar(255) NOT NULL COMMENT 'chuỗi bảo mật password',
  PRIMARY KEY (`user_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `tbl_user_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `mst_group` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES (1,1,'ntmhuong','123456','Nguyễn Thị Mai Hương','Hương Kana','huong@gmail.com','123456789','1983-07-08',1,'abcxyz1'),(2,2,'ddson','123456','wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww40','Sơn Kana','son@gmail.com','1234567878','1999-03-14',1,'abcxyz2'),(3,3,'tung','123456','Đỗ Mạnh Tùng','Tùng Kana','tung@gmail.com','0234567878','1999-03-14',1,'abcxyz3'),(4,2,'admin','7f7bd60bea356a8e5aa0707f5acc8dd61604c3be','Nguyễn Quang Huy','Huy Kana','huy@gmail.com','1234567878','1999-03-14',0,'1664784106895'),(5,2,'hdthang','123456','Hà Đức Thắng','Thằng Kana','thang@gmail.com','1234567878','1999-03-14',1,'abcxyz4'),(6,3,'btanh','123456','<script>alert(\"Hiep\");</script>','Anh Kana','anhwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww@gmail.com','1222222222','2000-01-01',1,'abcxyz5'),(7,2,'tqtung','123456','Trần Quang Tùng','Tunfg2 Kana','tung@gmail.com','2111111111','1900-09-01',1,'abcxyz6'),(8,3,'tqphuoc','123456','Trương Quang Phước','Huy1 Kana','phuoc@gmail.com','0903111222','2000-09-09',1,'abcxyz7'),(9,3,'huy1','123456','_Huy','Huy2 Kana','huy1@gmail.com','0903111222','2000-09-09',1,'abcxyz7'),(10,3,'huy2','123456','%Huy 1','Huy3 Kana','huy2@gmail.com','1903111222','2000-09-09',1,'abcxyz8'),(11,3,'huy3','123456',';Huy 2','Huy4 Kana','huy3@gmail.com','0903121222','2000-09-09',1,'abcxyz9'),(12,3,'huy4','123456',' /Huy 3','Huy5 Kana','huy4@gmail.com','0902111222','2000-09-09',1,'abcxyz8'),(13,3,'huy5','123456','%Huy 4','Huy6 Kana','huy5@gmail.com','0905111222','2000-09-09',1,'abcxyz9'),(14,3,'huy6','123456','%Huy 5','Huy7 Kana','huy6@gmail.com','0903511222','2000-09-09',1,'abcxyzz'),(15,3,'huy7','123456','H%uy 6','Huy8 Kana','huy7@gmail.com','0903611222','2000-09-09',1,'abcxyza'),(16,3,'huy8','123456','Hu%y 7','Huy9 kana','huy8@gmail.com','0903711222','2000-09-09',1,'abcxyzq'),(17,3,'huy9','123456','H%uy 8','Huy10 Kana','huy9@gmail.com','0903811222','2000-09-09',1,'abcxyzd'),(18,3,'huy10','123456','Hu%y 9','Huy11 Kana','huy10@gmail.com','0993111222','2000-09-09',1,'abcxyzf'),(19,3,'huy11','123456','Huy% 10','Huy12 Kana','huy11@gmail.com','0803111222','2000-09-09',1,'abcxyzg'),(20,3,'huy12','123456','Huy %11','Huy13 Kana','huy12@gmail.com','2903111222','2000-09-09',1,'abcxyzt'),(21,3,'huy13','123456','Huy 12','Huy14 Kana','huy13@gmail.com','0933111222','2000-09-09',1,'abcxyzy'),(22,3,'huy14','123456','Huy 13','Huy15 Kana','huy14@gmail.com','0943111222','2000-09-09',1,'abcxyzu'),(23,3,'huy15','123456','Huy %14','Huy16 Kana','huy15@gmail.com','0953111222','2000-09-09',1,'abcxyzi'),(24,3,'huy16','123456','Huy 15','Huy17 Kana','huy16@gmail.com','0953111222','2000-09-09',1,'abcxyzo'),(25,3,'huy17','123456','Hu%y 16','Huy18 Kana','huy17@gmail.com','0973111222','2000-09-09',1,'abcxyzx'),(26,3,'huy18','123456','Huy 17','Huy19 Kana','huy18@gmail.com','0963111222','2000-09-09',1,'abcxyzf'),(27,3,'huy19','123456','Huy %18','Huy20 Kana','huy19@gmail.com','0908111222','2000-09-09',1,'abcxyzg'),(28,3,'huy20','123456','Huy 19','Huy21 Kana','huy20@gmail.com','0903911222','2000-09-09',1,'abcxyzy'),(29,3,'huy21','123456','Huy %20','Huy22 Kana','huy21@gmail.com','0903311222','2000-09-09',1,'abcxyzo'),(30,3,'huy22','123456','Huy% 21','Huy23 Kana','huy22@gmail.com','0903114222','2000-09-09',1,'abcxyzy'),(31,3,'huy23','123456','Hu%y 22','Huy24 Kana','huy23@gmail.com','0903115222','2000-09-09',1,'abcxyzi'),(32,3,'huy24','123456','Huy %23','Huy25 Kana','huy23@gmail.com','0903116222','2000-09-09',1,'abcxyz0');
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-07 14:31:38
