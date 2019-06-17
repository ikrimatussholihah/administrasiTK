-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 09 Jul 2018 pada 08.24
-- Versi Server: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `administrasi`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `bukudnseragam`
--

CREATE TABLE IF NOT EXISTS `bukudnseragam` (
  `no_induk` varchar(20) NOT NULL,
  `nm` varchar(30) NOT NULL,
  `kls` varchar(2) NOT NULL,
  `pembayaran` varchar(50) NOT NULL,
  `tgl_1` date NOT NULL,
  `jlm_1` int(30) NOT NULL,
  PRIMARY KEY (`no_induk`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `datasiswa`
--

CREATE TABLE IF NOT EXISTS `datasiswa` (
  `no_induk` varchar(20) NOT NULL DEFAULT '',
  `nama` varchar(50) DEFAULT NULL,
  `no_hp` varchar(20) DEFAULT NULL,
  `jns_kel` varchar(20) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `nm_ortu` varchar(30) DEFAULT NULL,
  `ttl` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`no_induk`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `datasiswa`
--

INSERT INTO `datasiswa` (`no_induk`, `nama`, `no_hp`, `jns_kel`, `alamat`, `nm_ortu`, `ttl`) VALUES
('01', 'raisa', '0888090675', 'Perempuan', 'condet', 'joko', 'jakarta'),
('02', 'rendi', '08889765', 'Laki-Laki', 'ciracas', 'dodi', 'jakarta'),
('03', 'fatmah', '0897654898', 'Perempuan', 'jatiwarna bekasi', 'budi', 'bekasi'),
('04', 'siti', '088863457', 'Perempuan', 'sumir', 'nur', 'bekasi');

-- --------------------------------------------------------

--
-- Struktur dari tabel `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `user_nm` varchar(30) NOT NULL,
  `pass` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `login`
--

INSERT INTO `login` (`user_nm`, `pass`) VALUES
('ikrima', '1010');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pembayaranspp`
--

CREATE TABLE IF NOT EXISTS `pembayaranspp` (
  `no_induk` varchar(20) NOT NULL,
  `nm` varchar(30) NOT NULL,
  `spp` varchar(30) NOT NULL,
  `tgl` varchar(30) NOT NULL,
  PRIMARY KEY (`no_induk`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pembayaranulangan`
--

CREATE TABLE IF NOT EXISTS `pembayaranulangan` (
  `no_induk` varchar(20) NOT NULL,
  `nm` varchar(30) NOT NULL,
  `kls` varchar(2) NOT NULL,
  `tgl_1` varchar(20) NOT NULL,
  `jlm_1` int(30) NOT NULL,
  `tgl_2` varchar(20) NOT NULL,
  `jml_2` int(30) NOT NULL,
  PRIMARY KEY (`no_induk`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `uanggedung`
--

CREATE TABLE IF NOT EXISTS `uanggedung` (
  `no_induk` varchar(20) NOT NULL,
  `nm` varchar(30) NOT NULL,
  `kls` varchar(2) NOT NULL,
  `cicilan1` int(30) NOT NULL,
  `tgl` date NOT NULL,
  `jlm_1` int(30) NOT NULL,
  PRIMARY KEY (`no_induk`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
