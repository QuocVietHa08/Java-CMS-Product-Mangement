-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 27, 2022 lúc 04:23 PM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `qlch`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `Makh` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `Tenkh` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Diachi` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Sdt` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`Makh`, `Tenkh`, `Diachi`, `Sdt`) VALUES
('1', 'nghia', 'nam dinh', 1234567),
('2', 'nghiaa', 'nam dinha', 12345671);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `Mancc` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `Tenncc` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Diachi` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Sdt` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`Mancc`, `Tenncc`, `Diachi`, `Sdt`) VALUES
('1', 'thuy linhh', 'nam dinhh', 1234567891);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `Masp` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `Tensp` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Gia` int(50) NOT NULL,
  `Soluong` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Dv` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`Masp`, `Tensp`, `Gia`, `Soluong`, `Dv`) VALUES
('1', 'bim', 12, '35', 'chiec'),
('2', 'bim bim', 125, '32', 'chiecc');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `Id` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `Name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Pass` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`Id`, `Name`, `Pass`) VALUES
('1', 'thang', '1');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
