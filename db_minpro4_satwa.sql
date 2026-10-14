-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 14, 2026 at 06:33 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_minpro4_satwa`
--

-- --------------------------------------------------------

--
-- Table structure for table `satwa`
--

CREATE TABLE `satwa` (
  `id` int NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jenis` varchar(20) NOT NULL,
  `habitat` varchar(100) DEFAULT NULL,
  `berbisa` tinyint(1) DEFAULT NULL,
  `berat_badan` double DEFAULT NULL,
  `panjang_tubuh` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `satwa`
--

INSERT INTO `satwa` (`id`, `nama`, `jenis`, `habitat`, `berbisa`, `berat_badan`, `panjang_tubuh`) VALUES
(1, 'Orangutan', 'Mamalia', 'Hutan', NULL, 50, NULL),
(2, 'Komodo', 'Reptil', NULL, 0, NULL, 200),
(122, 'badak', 'Reptil', NULL, 0, NULL, 233),
(124, 'bekantan', 'Mamalia', 'hutan kalimantan', NULL, 12, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `satwa`
--
ALTER TABLE `satwa`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
