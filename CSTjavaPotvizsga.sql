-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2020. Júl 29. 19:49
-- Kiszolgáló verziója: 10.4.13-MariaDB
-- PHP verzió: 7.3.19

START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: "javapotvizsga"
--
CREATE DATABASE IF NOT EXISTS "javapotvizsga" DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE javapotvizsga;

DELIMITER $$
--
-- Eljárások
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `getOsszesFelnottHegymaszoByNevsor` (IN `id_hegymaszo` INT)  NO SQL
SELECT * FROM `hegymaszok` WHERE `hegymaszok`.`kor` >18 ORDER BY `hegymaszok`.`nev` ASC$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getOsszesHegy` (IN `id_hegyekIN` INT)  NO SQL
SELECT * FROM `hegyek` ORDER BY `hegyek`.`magassag` DESC$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ujHegy` (IN `id_hegyekIN` INT(2), IN `szelessegi_koordinataIN` VARCHAR(8) CHARSET utf8, IN `hosszusagi_koordinataIN` VARCHAR(8) CHARSET utf8, IN `magassagIN` INT(4), IN `megmaszas_nehezsegi_fokaIN` INT(2))  NO SQL
INSERT INTO `hegyek` (`hegyek`.`id_hegyek`,`hegyek`.`szelessegi_koordinata`,`hegyek`.`hosszusagi_koordinata`, `hegyek`.`magassag`, `hegyek`.`megmaszas_nehezsegi_foka`) VALUES (NULL, szelessegi_koordinataIN, hosszusagi_koordinataIN, magassagIN, megmaszas_nehezsegi_fokaIN)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateMaszasiKepessegById` (IN `id_hegymaszoIN` INT(2))  NO SQL
UPDATE `hegymaszok` SET `hegymaszok`.`kepessegi_szint`=`hegymaszok`.`kepessegi_szint`+1 WHERE  `hegymaszok`.`id_hegymaszo`=id_hegymaszoIN$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához "hegyek"
--

CREATE TABLE "hegyek" ;

--
-- A tábla adatainak kiíratása "hegyek"
--

SET IDENTITY_INSERT "hegyek" ON ;
INSERT INTO "hegyek" ("id_hegyek", "szelessegi_koordinata", "hosszusagi_koordinata", "magassag", "megmaszas_nehezsegi_foka") VALUES
(1, 'E1231111', 'K1231111', 3450, 3),
(2, 'D345623', 'N345623', 4502, 4),
(3, 'E1111111', 'N1111111', 5600, 5),
(4, 'E432345', 'N1204334', 6705, 6),
(5, 'D0567867', 'K1232312', 8848, 10);

SET IDENTITY_INSERT "hegyek" OFF;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához "hegymaszok"
--

CREATE TABLE "hegymaszok" ;

--
-- A tábla adatainak kiíratása "hegymaszok"
--

SET IDENTITY_INSERT "hegymaszok" ON ;
INSERT INTO "hegymaszok" ("id_hegymaszo", "nev", "kor", "orszag", "kepessegi_szint") VALUES
(1, 'Gipsz Jakab', 27, 'HUN', 4),
(2, 'V Elemér', 53, 'HUN', 6),
(3, 'John Doe', 16, 'UK', 7),
(4, 'Kuala Lumpur', 45, 'MAL', 7),
(5, 'Bret Adler', 34, 'GER', 8);

SET IDENTITY_INSERT "hegymaszok" OFF;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
