-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3308
-- Tiempo de generación: 06-08-2022 a las 00:49:43
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdfabrica`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `ConsultarPiezasPorFechas` (IN `fechaI` VARCHAR(255), IN `fechaF` VARCHAR(255))  select * from pieza_fabricada where (fecha_fabricacion BETWEEN fechaI AND fechaF)$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fabrica`
--

CREATE TABLE `fabrica` (
  `id_fabrica` int(11) NOT NULL,
  `sede` varchar(30) NOT NULL,
  `direccion` varchar(40) NOT NULL,
  `telefono` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `fabrica`
--

INSERT INTO `fabrica` (`id_fabrica`, `sede`, `direccion`, `telefono`) VALUES
(1, 'PIURA', 'prueba edirt', '7985471'),
(2, 'AREQUIPA', 'arequipa avenida', '7452135');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `linea`
--

CREATE TABLE `linea` (
  `id_linea` int(11) NOT NULL,
  `tipo` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `linea`
--

INSERT INTO `linea` (`id_linea`, `tipo`) VALUES
(1, 'A'),
(2, 'B'),
(3, 'C'),
(4, 'D');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pieza_fabricada`
--

CREATE TABLE `pieza_fabricada` (
  `id_pieza_fabricada` int(11) NOT NULL,
  `id_fabrica` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `fecha_fabricacion` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pieza_fabricada`
--

INSERT INTO `pieza_fabricada` (`id_pieza_fabricada`, `id_fabrica`, `id_producto`, `cantidad`, `fecha_fabricacion`) VALUES
(1, 1, 1, 50, '2022-07-12'),
(2, 1, 1, 40, '2022-07-19'),
(3, 2, 2, 20, '2022-08-04');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL,
  `id_linea` int(11) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `descripcion` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `id_linea`, `nombre`, `descripcion`) VALUES
(1, 1, 'Prueba', 'Prueba'),
(2, 2, 'prueba1', 'prueba1');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `fabrica`
--
ALTER TABLE `fabrica`
  ADD PRIMARY KEY (`id_fabrica`);

--
-- Indices de la tabla `linea`
--
ALTER TABLE `linea`
  ADD PRIMARY KEY (`id_linea`);

--
-- Indices de la tabla `pieza_fabricada`
--
ALTER TABLE `pieza_fabricada`
  ADD PRIMARY KEY (`id_pieza_fabricada`),
  ADD KEY `FK5ppiiaj9u3xphc3drrjnomubv` (`id_fabrica`),
  ADD KEY `FKi2ejbhpcsny7opdgby3v8ycle` (`id_producto`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `FKd5u1rtwm6ln7a2y1roiwuvr32` (`id_linea`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `fabrica`
--
ALTER TABLE `fabrica`
  MODIFY `id_fabrica` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `linea`
--
ALTER TABLE `linea`
  MODIFY `id_linea` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `pieza_fabricada`
--
ALTER TABLE `pieza_fabricada`
  MODIFY `id_pieza_fabricada` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `pieza_fabricada`
--
ALTER TABLE `pieza_fabricada`
  ADD CONSTRAINT `FK5ppiiaj9u3xphc3drrjnomubv` FOREIGN KEY (`id_fabrica`) REFERENCES `fabrica` (`id_fabrica`),
  ADD CONSTRAINT `FKi2ejbhpcsny7opdgby3v8ycle` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`);

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `FKd5u1rtwm6ln7a2y1roiwuvr32` FOREIGN KEY (`id_linea`) REFERENCES `linea` (`id_linea`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
