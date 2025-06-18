CREATE DATABASE  IF NOT EXISTS `projeto` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `projeto`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: projeto
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `aluno_materia_semestre`
--

DROP TABLE IF EXISTS `aluno_materia_semestre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aluno_materia_semestre` (
  `id` int NOT NULL AUTO_INCREMENT,
  `usuario_id` int NOT NULL,
  `materia_semestre_id` int NOT NULL,
  `flg_aceito` varchar(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unico_aluno_materia` (`usuario_id`,`materia_semestre_id`),
  KEY `fk_aluno_materia_semestre` (`materia_semestre_id`),
  CONSTRAINT `fk_aluno_materia_semestre` FOREIGN KEY (`materia_semestre_id`) REFERENCES `materia_semestre` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_aluno_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario_dashboard` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aplicativo`
--

DROP TABLE IF EXISTS `aplicativo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aplicativo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `aula_registrada`
--

DROP TABLE IF EXISTS `aula_registrada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aula_registrada` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) NOT NULL,
  `dia` date NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fim` time NOT NULL,
  `materia_semestre_id` int DEFAULT NULL,
  `laboratorio_id` int DEFAULT NULL,
  `flg_status` varchar(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `materia_semestre_id` (`materia_semestre_id`),
  KEY `laboratorio_id` (`laboratorio_id`),
  CONSTRAINT `aula_registrada_ibfk_1` FOREIGN KEY (`materia_semestre_id`) REFERENCES `materia_semestre` (`id`) ON DELETE SET NULL,
  CONSTRAINT `aula_registrada_ibfk_2` FOREIGN KEY (`laboratorio_id`) REFERENCES `laboratorio` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `duracao_semestres` int DEFAULT NULL,
  `instituicao_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `instituicao_id` (`instituicao_id`),
  CONSTRAINT `curso_ibfk_1` FOREIGN KEY (`instituicao_id`) REFERENCES `instituicao` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `instituicao`
--

DROP TABLE IF EXISTS `instituicao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instituicao` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `key` varchar(100) NOT NULL,
  `flg_ativo` varchar(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `laboratorio`
--

DROP TABLE IF EXISTS `laboratorio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `laboratorio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `instituicao_id` int DEFAULT NULL,
  `qtd_computadores` int NOT NULL,
  `qtd_lugares` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `usuario_dashboard_ifk_1` (`instituicao_id`),
  CONSTRAINT `usuario_dashboard_ifk_1` FOREIGN KEY (`instituicao_id`) REFERENCES `instituicao` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `laboratorio_aplicativos`
--

DROP TABLE IF EXISTS `laboratorio_aplicativos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `laboratorio_aplicativos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `laboratorio_id` int NOT NULL,
  `aplicativo_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `laboratorio_id` (`laboratorio_id`),
  KEY `aplicativo_id` (`aplicativo_id`),
  CONSTRAINT `laboratorio_aplicativos_ibfk_1` FOREIGN KEY (`laboratorio_id`) REFERENCES `laboratorio` (`id`),
  CONSTRAINT `laboratorio_aplicativos_ibfk_2` FOREIGN KEY (`aplicativo_id`) REFERENCES `aplicativo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `materia`
--

DROP TABLE IF EXISTS `materia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materia` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `sigla` varchar(8) DEFAULT NULL,
  `qtd_aulas` int DEFAULT NULL,
  `curso_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `curso_id` (`curso_id`),
  CONSTRAINT `materia_ibfk_1` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `materia_semestre`
--

DROP TABLE IF EXISTS `materia_semestre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materia_semestre` (
  `id` int NOT NULL AUTO_INCREMENT,
  `materia_id` int DEFAULT NULL,
  `semestre_id` bigint DEFAULT NULL,
  `usuario_id` int DEFAULT NULL,
  `numero_modulo` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `materia_id` (`materia_id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `materia_semestre_ibfk_3` (`semestre_id`),
  CONSTRAINT `materia_semestre_ibfk_1` FOREIGN KEY (`materia_id`) REFERENCES `materia` (`id`) ON DELETE SET NULL,
  CONSTRAINT `materia_semestre_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuario_dashboard` (`id`) ON DELETE SET NULL,
  CONSTRAINT `materia_semestre_ibfk_3` FOREIGN KEY (`semestre_id`) REFERENCES `semestre` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `matricula_curso`
--

DROP TABLE IF EXISTS `matricula_curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matricula_curso` (
  `id` int NOT NULL AUTO_INCREMENT,
  `usuario_id` int NOT NULL,
  `curso_id` int NOT NULL,
  `data_matricula` date NOT NULL,
  `data_fim_matricula` date DEFAULT NULL,
  `status_matricula` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unico_usuario_curso` (`usuario_id`,`curso_id`),
  KEY `fk_matricula_curso` (`curso_id`),
  CONSTRAINT `fk_matricula_curso` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_matricula_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario_dashboard` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notificacao`
--

DROP TABLE IF EXISTS `notificacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notificacao` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `mensagem` varchar(255) NOT NULL,
  `data_criacao` datetime NOT NULL,
  `visualizado` tinyint(1) NOT NULL DEFAULT '0',
  `aula_id` int DEFAULT NULL,
  `usuario_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_notificacao_aula` (`aula_id`),
  KEY `fk_notificacao_usuario` (`usuario_id`),
  CONSTRAINT `fk_notificacao_aula` FOREIGN KEY (`aula_id`) REFERENCES `aula_registrada` (`id`),
  CONSTRAINT `fk_notificacao_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario_dashboard` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `semestre`
--

DROP TABLE IF EXISTS `semestre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semestre` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `instituicao_id` int NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `flg_ativo` varchar(1) NOT NULL,
  `data_inicio` date NOT NULL,
  `data_fim` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_instituicao2` (`instituicao_id`),
  CONSTRAINT `fk_instituicao2` FOREIGN KEY (`instituicao_id`) REFERENCES `instituicao` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario_dashboard`
--

DROP TABLE IF EXISTS `usuario_dashboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_dashboard` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dsc_usuario` varchar(255) NOT NULL,
  `senha_usuario` varchar(255) NOT NULL,
  `flg_ativo` varchar(1) NOT NULL,
  `celular` varchar(14) DEFAULT NULL,
  `nome_usuario` varchar(255) DEFAULT NULL,
  `instituicao_id` int DEFAULT NULL,
  `is_admin` int DEFAULT NULL,
  `flg_professor` int DEFAULT NULL,
  `flg_mobile` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `instituicao_id` (`instituicao_id`),
  CONSTRAINT `usuario_dashboard_ibfk_1` FOREIGN KEY (`instituicao_id`) REFERENCES `instituicao` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `vw_aulas_dia`
--

DROP TABLE IF EXISTS `vw_aulas_dia`;
/*!50001 DROP VIEW IF EXISTS `vw_aulas_dia`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vw_aulas_dia` AS SELECT 
 1 AS `id`,
 1 AS `descricao`,
 1 AS `dia`,
 1 AS `hora_inicio`,
 1 AS `hora_fim`,
 1 AS `materia_semestre_id`,
 1 AS `flg_status`,
 1 AS `numero_modulo`,
 1 AS `laboratorio_id`,
 1 AS `lab_nome`,
 1 AS `semestre_descricao`,
 1 AS `semestre_id`,
 1 AS `data_inicio`,
 1 AS `data_fim`,
 1 AS `instituicao_id`,
 1 AS `materia_nome`,
 1 AS `materia_id`,
 1 AS `professor_id`,
 1 AS `professor_nome`,
 1 AS `curso_id`,
 1 AS `nome_curso`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vw_materias_aluno`
--

DROP TABLE IF EXISTS `vw_materias_aluno`;
/*!50001 DROP VIEW IF EXISTS `vw_materias_aluno`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vw_materias_aluno` AS SELECT 
 1 AS `id`,
 1 AS `usuario_id`,
 1 AS `materia_semestre_id`,
 1 AS `flg_aceito`,
 1 AS `nome_usuario`,
 1 AS `dsc_usuario`,
 1 AS `nome`,
 1 AS `materia_id`,
 1 AS `semestre_id`,
 1 AS `descricao`,
 1 AS `curso_id`,
 1 AS `nome_curso`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vw_materias_semestre`
--

DROP TABLE IF EXISTS `vw_materias_semestre`;
/*!50001 DROP VIEW IF EXISTS `vw_materias_semestre`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vw_materias_semestre` AS SELECT 
 1 AS `id`,
 1 AS `materia_id`,
 1 AS `nome`,
 1 AS `sigla`,
 1 AS `qtd_aulas`,
 1 AS `numero_modulo`,
 1 AS `curso_id`,
 1 AS `nome_curso`,
 1 AS `semestre_id`,
 1 AS `descricao`,
 1 AS `data_inicio`,
 1 AS `data_fim`,
 1 AS `usuario_id`,
 1 AS `nome_usuario`,
 1 AS `instituicao_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `vw_aulas_dia`
--

/*!50001 DROP VIEW IF EXISTS `vw_aulas_dia`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_aulas_dia` AS select `a`.`id` AS `id`,`a`.`descricao` AS `descricao`,`a`.`dia` AS `dia`,`a`.`hora_inicio` AS `hora_inicio`,`a`.`hora_fim` AS `hora_fim`,`a`.`materia_semestre_id` AS `materia_semestre_id`,`a`.`flg_status` AS `flg_status`,`d`.`numero_modulo` AS `numero_modulo`,`a`.`laboratorio_id` AS `laboratorio_id`,`c`.`nome` AS `lab_nome`,`b`.`descricao` AS `semestre_descricao`,`b`.`id` AS `semestre_id`,`b`.`data_inicio` AS `data_inicio`,`b`.`data_fim` AS `data_fim`,`b`.`instituicao_id` AS `instituicao_id`,`e`.`nome` AS `materia_nome`,`e`.`id` AS `materia_id`,`f`.`id` AS `professor_id`,`f`.`nome_usuario` AS `professor_nome`,`e`.`curso_id` AS `curso_id`,`g`.`nome` AS `nome_curso` from ((((((`aula_registrada` `a` join `semestre` `b`) join `laboratorio` `c`) join `materia_semestre` `d`) join `materia` `e`) join `usuario_dashboard` `f`) join `curso` `g`) where ((`a`.`laboratorio_id` = `c`.`id`) and (`a`.`materia_semestre_id` = `d`.`id`) and (`d`.`semestre_id` = `b`.`id`) and (`d`.`materia_id` = `e`.`id`) and (`f`.`id` = `d`.`usuario_id`) and (`g`.`id` = `e`.`curso_id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vw_materias_aluno`
--

/*!50001 DROP VIEW IF EXISTS `vw_materias_aluno`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_materias_aluno` AS select `a`.`id` AS `id`,`a`.`usuario_id` AS `usuario_id`,`a`.`materia_semestre_id` AS `materia_semestre_id`,`a`.`flg_aceito` AS `flg_aceito`,`b`.`nome_usuario` AS `nome_usuario`,`b`.`dsc_usuario` AS `dsc_usuario`,`d`.`nome` AS `nome`,`d`.`id` AS `materia_id`,`c`.`semestre_id` AS `semestre_id`,`e`.`descricao` AS `descricao`,`f`.`id` AS `curso_id`,`f`.`nome` AS `nome_curso` from (((((`aluno_materia_semestre` `a` join `usuario_dashboard` `b`) join `materia_semestre` `c`) join `materia` `d`) join `semestre` `e`) join `curso` `f`) where ((`a`.`materia_semestre_id` = `c`.`id`) and (`b`.`id` = `a`.`usuario_id`) and (`d`.`id` = `c`.`materia_id`) and (`e`.`id` = `c`.`semestre_id`) and (`f`.`id` = `d`.`curso_id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vw_materias_semestre`
--

/*!50001 DROP VIEW IF EXISTS `vw_materias_semestre`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_materias_semestre` AS select `a`.`id` AS `id`,`a`.`materia_id` AS `materia_id`,`b`.`nome` AS `nome`,`b`.`sigla` AS `sigla`,`b`.`qtd_aulas` AS `qtd_aulas`,`a`.`numero_modulo` AS `numero_modulo`,`b`.`curso_id` AS `curso_id`,`e`.`nome` AS `nome_curso`,`a`.`semestre_id` AS `semestre_id`,`c`.`descricao` AS `descricao`,`c`.`data_inicio` AS `data_inicio`,`c`.`data_fim` AS `data_fim`,`a`.`usuario_id` AS `usuario_id`,`d`.`nome_usuario` AS `nome_usuario`,`d`.`instituicao_id` AS `instituicao_id` from ((((`materia_semestre` `a` join `materia` `b`) join `semestre` `c`) join `usuario_dashboard` `d`) join `curso` `e`) where ((`a`.`materia_id` = `b`.`id`) and (`c`.`id` = `a`.`semestre_id`) and (`a`.`usuario_id` = `d`.`id`) and (`e`.`id` = `b`.`curso_id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-18  9:40:16
