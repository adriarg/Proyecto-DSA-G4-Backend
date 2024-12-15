-- Dumping database structure for bbdd
DROP DATABASE IF EXISTS `bbdd`;
CREATE DATABASE IF NOT EXISTS `bbdd` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `bbdd`;


-- Dumping structure for table bbdd.usuari
DROP TABLE IF EXISTS `usuari`;
CREATE TABLE IF NOT EXISTS `Usuari` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) DEFAULT NULL,
  `cognom` varchar(50) DEFAULT NULL,
  `nomusuari` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `password2` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nomusuari` (`nomusuari`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='taula que guarda la llista d''usuaris registrats';

-- Dumping data for table bbdd.usuari: ~5 rows (approximately)
INSERT INTO `Usuari` (`id`, `nom`, `cognom`, `nomusuari`, `password`, `password2`) VALUES
	(1, 'Adrià', 'Rocamora', 'arocam', '111', '111'),
	(2, 'Joaquin', 'Roca', 'jroca', '222', '222'),
	(3, 'David', 'Lamas', 'dlamas', '333', '333'),

