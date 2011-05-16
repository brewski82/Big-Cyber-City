-- MySQL dump 10.13  Distrib 5.1.54, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: BCC
-- ------------------------------------------------------
-- Server version	5.1.54-1ubuntu4

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
-- Table structure for table `ALERTS`
--

DROP TABLE IF EXISTS `ALERTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ALERTS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `fromId` bigint(20) NOT NULL,
  `type` varchar(50) NOT NULL,
  `fromName` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `COMMENTS`
--

DROP TABLE IF EXISTS `COMMENTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COMMENTS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fromId` bigint(20) NOT NULL,
  `toId` bigint(20) NOT NULL,
  `comment` text NOT NULL,
  `approved` tinyint(1) NOT NULL DEFAULT '1',
  `type` varchar(25) NOT NULL DEFAULT 'profile',
  `date` datetime NOT NULL DEFAULT '2007-12-31 11:30:45',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=182 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `FRIENDS`
--

DROP TABLE IF EXISTS `FRIENDS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FRIENDS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `friendId` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=846 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `MAIL`
--

DROP TABLE IF EXISTS `MAIL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MAIL` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fromId` bigint(20) NOT NULL DEFAULT '0',
  `toId` bigint(20) NOT NULL DEFAULT '0',
  `subject` varchar(100) DEFAULT NULL,
  `body` text,
  `isRead` tinyint(1) NOT NULL DEFAULT '0',
  `delFrom` tinyint(1) NOT NULL DEFAULT '0',
  `delTo` tinyint(1) NOT NULL DEFAULT '0',
  `date` datetime NOT NULL DEFAULT '2008-12-20 11:17:32',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=495 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `NEW`
--

DROP TABLE IF EXISTS `NEW`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NEW` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `rnd` bigint(20) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `new_index` (`rnd`)
) ENGINE=MyISAM AUTO_INCREMENT=124 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PHOTOS`
--

DROP TABLE IF EXISTS `PHOTOS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PHOTOS` (
  `photoId` bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) NOT NULL DEFAULT '0',
  `url` varchar(100) DEFAULT NULL,
  `fileName` varchar(50) DEFAULT NULL,
  `path` varchar(250) DEFAULT NULL,
  `caption` varchar(100) DEFAULT NULL,
  `displayPic` tinyint(1) NOT NULL DEFAULT '0',
  `type` varchar(20) NOT NULL DEFAULT 'profile',
  PRIMARY KEY (`photoId`)
) ENGINE=MyISAM AUTO_INCREMENT=654 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `USERS`
--

DROP TABLE IF EXISTS `USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `privilege` varchar(20) NOT NULL DEFAULT 'ROLE_USER',
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `display_name` varchar(50) NOT NULL DEFAULT 'I Need A Name',
  `gender` varchar(6) NOT NULL DEFAULT 'Male',
  `pic` varchar(50) NOT NULL DEFAULT 'noPhoto.GIF',
  `pic_path` varchar(250) NOT NULL DEFAULT '/photos/',
  `birthdate` date NOT NULL DEFAULT '1980-01-01',
  `location` varchar(200) NOT NULL DEFAULT 'everywhere',
  `relationship_status` varchar(20) NOT NULL DEFAULT 'Single',
  `zip` varchar(5) NOT NULL DEFAULT '0',
  `about` text,
  `last_login` datetime NOT NULL DEFAULT '2008-12-20 11:17:32',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `zips`
--

DROP TABLE IF EXISTS `zips`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zips` (
  `zip` varchar(5) NOT NULL DEFAULT '',
  `state` char(2) NOT NULL DEFAULT '',
  `latitude` varchar(10) NOT NULL DEFAULT '',
  `longitude` varchar(10) NOT NULL DEFAULT '',
  `city` varchar(50) DEFAULT NULL,
  `full_state` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-05-16 18:54:43
