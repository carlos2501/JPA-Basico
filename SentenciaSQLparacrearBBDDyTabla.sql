CREATE DATABASE `java_curso` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_spanish2_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) COLLATE utf8mb3_spanish2_ci NOT NULL,
  `nombre` varchar(45) COLLATE utf8mb3_spanish2_ci DEFAULT NULL,
  `apellidos` varchar(45) COLLATE utf8mb3_spanish2_ci DEFAULT NULL,
  `clave` varchar(100) COLLATE utf8mb3_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `usuario_UNIQUE` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish2_ci;
