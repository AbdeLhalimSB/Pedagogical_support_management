-- phpMyAdmin SQL Dump
-- version 4.1.4
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 17, 2022 at 12:30 PM
-- Server version: 5.6.15-log
-- PHP Version: 5.4.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `gestion_pep`
--

-- --------------------------------------------------------

--
-- Table structure for table `groupes`
--

CREATE TABLE IF NOT EXISTS `groupes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `groupes`
--

INSERT INTO `groupes` (`id`, `Nom`) VALUES
(1, 'TSDI'),
(2, 'GTPR');

-- --------------------------------------------------------

--
-- Table structure for table `matiers`
--

CREATE TABLE IF NOT EXISTS `matiers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `matiers`
--

INSERT INTO `matiers` (`id`, `Nom`) VALUES
(1, 'Arabic'),
(2, 'French'),
(3, 'English'),
(4, 'Bureautique'),
(5, 'Math'),
(6, 'Language C');

-- --------------------------------------------------------

--
-- Table structure for table `seances`
--

CREATE TABLE IF NOT EXISTS `seances` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(120) DEFAULT NULL,
  `duree` varchar(60) DEFAULT NULL,
  `matiere` varchar(200) DEFAULT NULL,
  `groupe` varchar(200) DEFAULT NULL,
  `stype` varchar(120) DEFAULT NULL,
  `prof` varchar(120) DEFAULT NULL,
  `objective` varchar(5000) DEFAULT NULL,
  `derouletment` varchar(5000) DEFAULT NULL,
  `outils` varchar(5000) DEFAULT NULL,
  `observations` varchar(5000) DEFAULT NULL,
  `disposiition` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `seances`
--

INSERT INTO `seances` (`id`, `date`, `duree`, `matiere`, `groupe`, `stype`, `prof`, `objective`, `derouletment`, `outils`, `observations`, `disposiition`) VALUES
(1, '2022-07-17', '1h', 'English', 'TSDI', 'CC', 'Yousra', 'Oject', 'Déro', 'Déro', 'Obser', 'Dispo');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(60) DEFAULT NULL,
  `Prenom` varchar(60) DEFAULT NULL,
  `CIN` varchar(60) DEFAULT NULL,
  `Email` varchar(60) DEFAULT NULL,
  `mdp` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `Nome`, `Prenom`, `CIN`, `Email`, `mdp`) VALUES
(1, 'Yousra', 'Dabdi', 'KBZBI23', 'yousra@gmail.com', 'admin');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
