-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: gct_1
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.18.04.1

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
-- Table structure for table `atividade`
--

DROP TABLE IF EXISTS `atividade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `atividade` (
  `id` mediumint(6) unsigned NOT NULL AUTO_INCREMENT,
  `chamado_id` mediumint(6) unsigned DEFAULT NULL,
  `titulo` varchar(255) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `tipo` mediumint(6) unsigned NOT NULL,
  `status` mediumint(6) unsigned NOT NULL,
  `ativo` tinyint(1) unsigned NOT NULL,
  `data_inicio` datetime NOT NULL,
  `data_final` datetime DEFAULT NULL,
  `usuario_atribuido_id` mediumint(6) unsigned NOT NULL,
  `usuario_inclusao_id` mediumint(6) unsigned NOT NULL,
  `usuario_alteracao_id` mediumint(6) unsigned DEFAULT NULL,
  `usuario_inativacao_id` mediumint(6) unsigned DEFAULT NULL,
  `data_inclusao` datetime NOT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_inativacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  KEY `tipo` (`tipo`),
  KEY `chamado_id` (`chamado_id`),
  KEY `usuario_atribuido_id` (`usuario_atribuido_id`),
  KEY `usuario_inclusao_id` (`usuario_inclusao_id`),
  KEY `usuario_alteracao_id` (`usuario_alteracao_id`),
  KEY `usuario_inativacao_id` (`usuario_inativacao_id`),
  CONSTRAINT `atividade_ibfk_1` FOREIGN KEY (`chamado_id`) REFERENCES `chamado` (`id`),
  CONSTRAINT `atividade_ibfk_2` FOREIGN KEY (`usuario_atribuido_id`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `atividade_ibfk_3` FOREIGN KEY (`usuario_inclusao_id`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `atividade_ibfk_4` FOREIGN KEY (`usuario_alteracao_id`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `atividade_ibfk_5` FOREIGN KEY (`usuario_inativacao_id`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `atividade_ibfk_6` FOREIGN KEY (`status`) REFERENCES `ocorrencia_status` (`id`),
  CONSTRAINT `atividade_ibfk_7` FOREIGN KEY (`tipo`) REFERENCES `ocorrencia_tipo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atividade`
--

LOCK TABLES `atividade` WRITE;
/*!40000 ALTER TABLE `atividade` DISABLE KEYS */;
INSERT INTO `atividade` VALUES (1,2,'Atividade direto na Base','Descricao direto na base',1,1,1,'2019-04-28 19:30:00','2019-04-29 12:00:00',1,1,1,NULL,'2019-04-28 19:29:00','2019-05-25 11:05:38',NULL),(2,1,'Outra atividade','Descricao direto na base',2,2,1,'2019-04-28 19:30:00','2019-04-29 12:00:00',1,1,NULL,NULL,'2019-04-28 19:29:00',NULL,NULL),(3,2,'teste alteracao','nova alteracao',4,1,1,'2019-05-21 03:00:00','2019-05-12 01:26:04',1,1,1,NULL,'2019-05-12 01:26:04','2019-05-24 23:03:43',NULL),(4,1,'Atividade do form para o chamado 1','Deu uma zica monstra e vou consertar.',3,1,1,'2019-05-13 03:00:00','2019-05-12 01:30:00',1,1,NULL,NULL,'2019-05-12 01:30:00',NULL,NULL),(5,4,'Titulo atividade de Chmado inativo','Teste inativo em chamado igualmente inativo',5,1,1,'2019-05-15 03:00:00','2019-05-12 02:20:27',1,1,NULL,NULL,'2019-05-12 02:20:27',NULL,NULL),(6,3,'Aumentar eficiencia de respota','O banco de dados deve retornar dados mais rapidamente que na ultima vez que estava em 0.8s',3,1,1,'2019-05-28 03:00:00','2019-05-12 02:44:36',1,1,NULL,NULL,'2019-05-12 02:44:36',NULL,NULL),(9,3,'Maio com tipo = 1 e status = 2','Vamos ver',1,3,1,'2019-05-25 00:00:00','2019-05-26 17:59:18',1,1,NULL,NULL,'2019-05-26 17:59:18',NULL,NULL),(10,3,'Maio com tipo = 1 e status = 2','Vamos ver',1,2,1,'2019-05-25 00:00:00','2019-05-26 17:59:58',1,1,NULL,NULL,'2019-05-26 17:59:58',NULL,NULL),(11,3,'Maio com tipo = 1 e status = 2','Vamos ver',1,1,1,'2019-05-25 00:00:00','2019-05-26 18:00:05',1,1,NULL,NULL,'2019-05-26 18:00:05',NULL,NULL),(12,3,'OUtro outro','Vamos ver',2,1,1,'2019-05-25 00:00:00','2019-05-26 18:00:21',1,1,NULL,NULL,'2019-05-26 18:00:21',NULL,NULL),(13,3,'OUtro outro','Vamos ver',2,1,1,'2019-05-25 00:00:00','2019-05-26 18:00:57',1,1,NULL,NULL,'2019-05-26 18:00:57',NULL,NULL),(14,3,'OUtro outro','Vamos ver',2,1,1,'2019-05-25 00:00:00','2019-05-26 18:01:00',1,1,NULL,NULL,'2019-05-26 18:01:00',NULL,NULL),(15,4,'OUtro outro','Vamos ver',3,1,1,'2019-05-25 00:00:00','2019-05-26 18:01:44',1,1,NULL,NULL,'2019-05-26 18:01:44',NULL,NULL),(16,4,'OUtro outro','Vamos ver',4,1,1,'2019-05-25 00:00:00','2019-05-26 18:01:51',1,1,NULL,NULL,'2019-05-26 18:01:51',NULL,NULL),(17,4,'OUtro outro','Vamos ver',2,3,1,'2019-05-25 00:00:00','2019-05-26 18:02:04',1,1,NULL,NULL,'2019-05-26 18:02:04',NULL,NULL),(18,4,'Novos inserts','Vamos ver',2,3,1,'2019-05-25 00:00:00','2019-05-26 18:02:14',1,1,NULL,NULL,'2019-05-26 18:02:14',NULL,NULL),(19,5,'Assistir Vingadores Ultimato','Ir ao cinema comprar ingresso e assistir.',2,6,1,'2019-05-27 00:00:00','2019-05-27 03:03:33',1,1,NULL,NULL,'2019-05-27 03:03:33',NULL,NULL);
/*!40000 ALTER TABLE `atividade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chamado`
--

DROP TABLE IF EXISTS `chamado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chamado` (
  `id` mediumint(6) unsigned NOT NULL AUTO_INCREMENT,
  `usuario_atribuido_id` mediumint(6) unsigned NOT NULL,
  `cliente_id` mediumint(6) unsigned NOT NULL,
  `produto_id` mediumint(6) unsigned NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `tipo` mediumint(6) unsigned NOT NULL,
  `status` mediumint(6) unsigned NOT NULL,
  `ativo` tinyint(1) unsigned NOT NULL,
  `data_inicio` datetime NOT NULL,
  `data_final` datetime DEFAULT NULL,
  `usuario_inclusao_id` mediumint(6) unsigned NOT NULL,
  `usuario_alteracao_id` mediumint(6) unsigned DEFAULT NULL,
  `usuario_inativacao_id` mediumint(6) unsigned DEFAULT NULL,
  `data_inclusao` datetime NOT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_inativacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  KEY `tipo` (`tipo`),
  KEY `produto_id` (`produto_id`),
  KEY `cliente_id` (`cliente_id`),
  KEY `usuario_atribuido_id` (`usuario_atribuido_id`),
  KEY `usuario_inclusao_id` (`usuario_inclusao_id`),
  KEY `usuario_alteracao_id` (`usuario_alteracao_id`),
  KEY `usuario_inativacao_id` (`usuario_inativacao_id`),
  CONSTRAINT `chamado_ibfk_2` FOREIGN KEY (`usuario_atribuido_id`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `chamado_ibfk_3` FOREIGN KEY (`usuario_inclusao_id`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `chamado_ibfk_4` FOREIGN KEY (`usuario_alteracao_id`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `chamado_ibfk_5` FOREIGN KEY (`usuario_inativacao_id`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `chamado_ibfk_6` FOREIGN KEY (`status`) REFERENCES `ocorrencia_status` (`id`),
  CONSTRAINT `chamado_ibfk_7` FOREIGN KEY (`tipo`) REFERENCES `ocorrencia_tipo` (`id`),
  CONSTRAINT `chamado_ibfk_8` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`),
  CONSTRAINT `chamado_ibfk_9` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chamado`
--

LOCK TABLES `chamado` WRITE;
/*!40000 ALTER TABLE `chamado` DISABLE KEYS */;
INSERT INTO `chamado` VALUES (1,1,1,1,'TEste primeiro add','sdfsdfsd',3,1,1,'2019-05-14 03:00:00','2019-05-16 03:00:00',1,NULL,NULL,'2019-05-01 20:30:16',NULL,NULL),(2,1,1,1,'ksdjfksj','sdkfjsdjk',1,1,0,'2019-05-22 03:00:00','2019-05-24 03:00:00',1,NULL,NULL,'2019-05-01 20:41:32',NULL,NULL),(3,1,1,1,'TEste primeiro add','sdfsdfsd',3,1,1,'2019-05-14 03:00:00','2019-05-16 03:00:00',1,NULL,NULL,'2019-05-01 20:30:16',NULL,NULL),(4,1,1,1,'INATIVO chamado','INATIVO INATIVO!!!',1,1,0,'2019-05-22 03:00:00','2019-05-24 03:00:00',1,NULL,NULL,'2019-05-01 20:41:32',NULL,NULL),(5,1,4,2,'Qualquer titulo','Qualquer descricao',2,3,1,'2019-05-26 03:00:00','2019-05-28 03:00:00',1,NULL,NULL,'2019-05-26 20:41:32',NULL,NULL),(6,1,6,4,'Qualquer titulo','Qualquer descricao',5,5,1,'2019-05-26 03:00:00','2019-05-28 03:00:00',1,NULL,NULL,'2019-05-26 20:41:32',NULL,NULL);
/*!40000 ALTER TABLE `chamado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id` mediumint(6) unsigned NOT NULL AUTO_INCREMENT,
  `contato_id` mediumint(6) unsigned NOT NULL,
  `sla` int(6) unsigned NOT NULL,
  `status` mediumint(6) unsigned NOT NULL,
  `ativo` tinyint(1) unsigned NOT NULL,
  `usuario_inclusao_id` mediumint(6) unsigned NOT NULL,
  `usuario_alteracao_id` mediumint(6) unsigned DEFAULT NULL,
  `usuario_inativacao_id` mediumint(6) unsigned DEFAULT NULL,
  `data_inclusao` datetime NOT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_inativacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `contato_id` (`contato_id`),
  KEY `status` (`status`),
  KEY `usuario_inclusao_id` (`usuario_inclusao_id`),
  KEY `usuario_alteracao_id` (`usuario_alteracao_id`),
  KEY `usuario_inativacao_id` (`usuario_inativacao_id`),
  CONSTRAINT `cliente_fk1` FOREIGN KEY (`contato_id`) REFERENCES `contato` (`id`),
  CONSTRAINT `cliente_fk2` FOREIGN KEY (`usuario_inclusao_id`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `cliente_fk3` FOREIGN KEY (`usuario_alteracao_id`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `cliente_fk4` FOREIGN KEY (`usuario_inativacao_id`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `cliente_fk5` FOREIGN KEY (`status`) REFERENCES `cliente_status` (`cliente_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,15,48,1,1,1,NULL,NULL,'2019-03-31 12:00:00',NULL,NULL),(2,20,48,1,1,1,NULL,NULL,'2019-03-31 12:00:00',NULL,NULL),(3,26,48,1,1,1,NULL,NULL,'2019-03-31 12:00:00',NULL,NULL),(4,16,48,1,1,1,NULL,NULL,'2019-03-31 12:00:00',NULL,NULL),(5,15,456,1,1,1,NULL,NULL,'2019-04-20 01:11:00',NULL,NULL),(6,15,4645,2,1,1,NULL,NULL,'2019-04-20 01:42:46',NULL,NULL),(7,15,123,2,1,1,NULL,NULL,'2019-04-20 01:46:25',NULL,NULL),(8,15,56,2,1,1,NULL,NULL,'2019-04-20 01:48:20',NULL,NULL),(9,30,45,1,1,1,NULL,NULL,'2019-04-20 02:02:54',NULL,NULL),(10,15,13,2,1,1,NULL,NULL,'2019-04-20 02:09:35',NULL,NULL),(11,35,48,1,1,1,NULL,NULL,'2019-04-30 00:46:31',NULL,NULL),(12,36,48,1,1,1,NULL,NULL,'2019-04-30 00:50:57',NULL,NULL),(13,36,48,1,1,1,NULL,NULL,'2019-04-30 00:53:06',NULL,NULL),(14,36,48,1,1,1,NULL,NULL,'2019-04-30 00:56:34',NULL,NULL);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente_status`
--

DROP TABLE IF EXISTS `cliente_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente_status` (
  `cliente_status_id` mediumint(6) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `ativo` tinyint(1) unsigned NOT NULL,
  PRIMARY KEY (`cliente_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente_status`
--

LOCK TABLES `cliente_status` WRITE;
/*!40000 ALTER TABLE `cliente_status` DISABLE KEYS */;
INSERT INTO `cliente_status` VALUES (1,'Produção',1),(2,'Em andamento',1);
/*!40000 ALTER TABLE `cliente_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contato`
--

DROP TABLE IF EXISTS `contato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contato` (
  `id` mediumint(6) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `cpf_cnpj` varchar(24) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `ativo` tinyint(1) unsigned NOT NULL,
  `usuario_inclusao_id` mediumint(6) unsigned DEFAULT NULL,
  `usuario_alteracao_id` mediumint(6) unsigned DEFAULT NULL,
  `usuario_inativacao_id` mediumint(6) unsigned DEFAULT NULL,
  `data_inclusao` datetime NOT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_inativacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usuario_inclusao_id` (`usuario_inclusao_id`),
  KEY `usuario_alteracao_id` (`usuario_alteracao_id`),
  KEY `usuario_inativacao_id` (`usuario_inativacao_id`),
  CONSTRAINT `contato_fk1` FOREIGN KEY (`usuario_inclusao_id`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `contato_fk2` FOREIGN KEY (`usuario_alteracao_id`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `contato_fk3` FOREIGN KEY (`usuario_inativacao_id`) REFERENCES `usuario` (`usuario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contato`
--

LOCK TABLES `contato` WRITE;
/*!40000 ALTER TABLE `contato` DISABLE KEYS */;
INSERT INTO `contato` VALUES (1,'Administrador','1111111111','123456','admin@admin.com',1,1,NULL,NULL,'2019-02-01 00:00:00',NULL,NULL),(15,'Teste DAO','385.620.053-39','11 4674-1111','email@email.com',1,1,1,NULL,'2019-03-02 20:11:38',NULL,NULL),(16,'Teste DAO','385.620.053-39','999999999999','email@email.com',1,1,1,NULL,'2019-03-02 20:13:08',NULL,NULL),(17,'Acerto DBeaver','1231231','4444444','v@v.com',1,1,1,NULL,'2019-03-25 04:00:08',NULL,NULL),(18,'Victor Oliveira','123123123','11 2222-666','v@v.com',1,1,1,NULL,'2019-03-25 04:02:19',NULL,NULL),(19,'Donald Trump','12312312','11 2222-5555','dt@m.com',1,1,NULL,NULL,'2019-03-25 15:46:56',NULL,NULL),(20,'Pato Donald','1123123','1221312321','pd@p.com',1,1,NULL,NULL,'2019-03-25 15:52:07',NULL,NULL),(21,'Pato Donald','1123123','1221312321','pd@p.com',1,1,NULL,NULL,'2019-03-25 15:55:00',NULL,NULL),(22,'sWITCH DA hora','56546','1221212','e@m.com',1,1,NULL,NULL,'2019-03-26 03:19:29',NULL,NULL),(23,'Victor Oliveira','123123123','11 2222-5555','v@v.com',1,1,NULL,NULL,'2019-04-01 23:53:13',NULL,NULL),(24,'teste','1232121\'','11123321','victord@g.com',1,1,1,NULL,'2019-04-01 23:54:16',NULL,NULL),(25,'null','000000','11123321','victord@g.com',0,1,1,NULL,'2019-04-02 00:08:15',NULL,NULL),(26,'Victor','','11123321','victord@g.com',0,1,NULL,NULL,'2019-04-02 00:09:00',NULL,NULL),(27,'null','','11123321','victord@g.com',1,1,NULL,NULL,'2019-04-02 00:09:07',NULL,NULL),(28,'Victor','00000','11123321','victord@g.com',1,1,NULL,NULL,'2019-04-02 00:09:25',NULL,NULL),(29,'Contato Ativo','999999','11 9999999','v@v.com',0,1,NULL,NULL,'2019-04-07 17:15:19',NULL,NULL),(30,'Contato FATEC','12312312','11 321313','v@v.com',1,1,NULL,NULL,'2019-04-16 00:32:31',NULL,NULL),(31,'Singleton','1231231','12313212','v@v.com',1,1,NULL,NULL,'2019-04-16 03:38:09',NULL,NULL),(32,'Wesghf','1255','22457','c@c.com',1,1,NULL,NULL,'2019-04-16 04:04:41',NULL,NULL),(33,'Victor Oliveira','123123123','11 2222-5555','v@v.com',1,1,NULL,NULL,'2019-04-16 04:08:54',NULL,NULL),(34,'','','11 99999-9999','v@v.com',1,1,NULL,NULL,'2019-04-19 15:10:50',NULL,NULL),(35,'Novo nome novo','1231313564','99999999','v@c.com',1,1,1,NULL,'2019-04-30 00:45:37',NULL,NULL),(36,'Novo cliente apresen','12345679890','55555555','novo@t.con',1,1,1,NULL,'2019-04-30 00:49:56',NULL,NULL),(37,'Novo cliente apresen','12345679890','55555555','novo@t.con',1,1,1,NULL,'2019-04-30 00:52:59',NULL,NULL),(38,'Novo cliente apresen','12345679890','111222333','t@t.con',1,1,NULL,NULL,'2019-04-30 00:53:59',NULL,NULL),(39,'Novo cliente apresen','12345679890','55555555','novo@t.con',1,1,1,NULL,'2019-04-30 00:56:08',NULL,NULL),(40,'Caique do Bigode','12312321','55555','c@c.com',0,1,1,NULL,'2019-05-09 00:57:14',NULL,NULL);
/*!40000 ALTER TABLE `contato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contrato`
--

DROP TABLE IF EXISTS `contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contrato` (
  `id` mediumint(6) unsigned NOT NULL AUTO_INCREMENT,
  `produto_id` mediumint(6) unsigned NOT NULL,
  `cliente_id` mediumint(6) unsigned NOT NULL,
  `data_inicio` date NOT NULL,
  `data_fim` date NOT NULL,
  `status` mediumint(6) unsigned NOT NULL,
  `ativo` tinyint(1) unsigned NOT NULL,
  `data_inclusao` date NOT NULL,
  `data_alteracao` date DEFAULT NULL,
  `data_exclusao` date DEFAULT NULL,
  `usuario_inclusao_id` mediumint(6) unsigned NOT NULL,
  `usuario_alteracao_id` mediumint(6) unsigned DEFAULT NULL,
  `usuario_exclusao_id` mediumint(6) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `contrato_produto_FK` (`produto_id`),
  KEY `contrato_cliente_FK` (`cliente_id`),
  KEY `contrato_usuario_FK` (`usuario_inclusao_id`),
  KEY `contrato_usuario_FK_1` (`usuario_alteracao_id`),
  KEY `contrato_usuario_FK_2` (`usuario_exclusao_id`),
  CONSTRAINT `contrato_cliente_FK` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `contrato_produto_FK` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`),
  CONSTRAINT `contrato_usuario_FK` FOREIGN KEY (`usuario_inclusao_id`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `contrato_usuario_FK_1` FOREIGN KEY (`usuario_alteracao_id`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `contrato_usuario_FK_2` FOREIGN KEY (`usuario_exclusao_id`) REFERENCES `usuario` (`usuario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contrato`
--

LOCK TABLES `contrato` WRITE;
/*!40000 ALTER TABLE `contrato` DISABLE KEYS */;
INSERT INTO `contrato` VALUES (1,1,1,'2019-04-19','2020-04-19',0,0,'2019-04-19',NULL,NULL,1,NULL,NULL),(6,1,3,'2019-07-02','2020-12-22',0,0,'2019-04-19',NULL,NULL,1,NULL,NULL),(7,4,9,'2019-04-22','2019-04-22',2,1,'2019-04-21',NULL,NULL,1,NULL,NULL),(8,2,3,'2019-04-22','2019-04-22',3,1,'2019-04-21',NULL,NULL,1,NULL,NULL),(9,3,3,'2019-04-22','2019-04-22',2,1,'2019-04-22',NULL,NULL,1,NULL,NULL),(10,4,6,'2019-04-22','2019-04-22',3,1,'2019-04-22',NULL,NULL,1,NULL,NULL),(11,3,9,'2019-04-22','2019-04-22',3,1,'2019-04-22',NULL,NULL,1,NULL,NULL),(12,4,3,'2019-04-22','2019-04-22',3,1,'2019-04-22',NULL,NULL,1,NULL,NULL),(13,4,1,'2012-07-23','2020-11-02',1,1,'2019-04-22',NULL,NULL,1,NULL,NULL);
/*!40000 ALTER TABLE `contrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ocorrencia_status`
--

DROP TABLE IF EXISTS `ocorrencia_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ocorrencia_status` (
  `id` mediumint(6) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `ativo` tinyint(1) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ocorrencia_status`
--

LOCK TABLES `ocorrencia_status` WRITE;
/*!40000 ALTER TABLE `ocorrencia_status` DISABLE KEYS */;
INSERT INTO `ocorrencia_status` VALUES (1,'Aberto',1),(2,'Fechado',1),(3,'Em Andamento',1),(4,'Cancelado',1),(5,'Finalizado',1),(6,'Aguardando',1),(7,'Em Execução',1),(8,'Atribuido',1),(9,'Não Atribuido',1);
/*!40000 ALTER TABLE `ocorrencia_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ocorrencia_tipo`
--

DROP TABLE IF EXISTS `ocorrencia_tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ocorrencia_tipo` (
  `id` mediumint(6) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `ativo` tinyint(1) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ocorrencia_tipo`
--

LOCK TABLES `ocorrencia_tipo` WRITE;
/*!40000 ALTER TABLE `ocorrencia_tipo` DISABLE KEYS */;
INSERT INTO `ocorrencia_tipo` VALUES (1,'Manutenção',1),(2,'Alteração',1),(3,'Melhoria',1),(4,'Tarefa',1),(5,'Análise',1);
/*!40000 ALTER TABLE `ocorrencia_tipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `id` mediumint(6) unsigned NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `codigo` varchar(24) DEFAULT NULL,
  `versao` varchar(24) DEFAULT NULL,
  `produto_status_id` mediumint(6) unsigned NOT NULL DEFAULT '1',
  `tipo` mediumint(6) unsigned NOT NULL DEFAULT '1',
  `ativo` tinyint(1) unsigned NOT NULL,
  `usuario_inclusao_id` mediumint(6) unsigned NOT NULL,
  `usuario_alteracao_id` mediumint(6) unsigned DEFAULT NULL,
  `usuario_inativacao_id` mediumint(6) unsigned DEFAULT NULL,
  `data_inclusao` datetime NOT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_inativacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usuario_inclusao_id` (`usuario_inclusao_id`),
  KEY `usuario_alteracao_id` (`usuario_alteracao_id`),
  KEY `usuario_inativacao_id` (`usuario_inativacao_id`),
  KEY `produto_status_id` (`produto_status_id`),
  KEY `tipo` (`tipo`),
  CONSTRAINT `produto_fk1` FOREIGN KEY (`usuario_inclusao_id`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `produto_fk2` FOREIGN KEY (`usuario_alteracao_id`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `produto_fk3` FOREIGN KEY (`usuario_inativacao_id`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `produto_fk4` FOREIGN KEY (`produto_status_id`) REFERENCES `produto_status` (`id`),
  CONSTRAINT `produto_fk5` FOREIGN KEY (`tipo`) REFERENCES `produto_tipo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'Titulo - Inserido direto na base','Descricao de um produto','COD1','1.0.0',1,1,1,1,NULL,NULL,'2019-05-13 02:25:06',NULL,'0000-00-00 00:00:00'),(2,'Produto Teste','Descricao de um produto','COD003','3.0.0',2,1,1,1,NULL,NULL,'2019-05-13 02:25:06',NULL,'0000-00-00 00:00:00'),(3,'Produto 3','Descricao de um produto','COD003','3.0.0',2,1,1,1,NULL,NULL,'2019-05-13 02:25:06',NULL,'0000-00-00 00:00:00'),(4,'Bola de Futebol automatica','Descricao de um produto','COD003','3.0.0',2,1,1,1,NULL,NULL,'2019-05-13 02:25:06',NULL,'0000-00-00 00:00:00'),(7,'KLJkdfslkj','sdklfjsklfjsdkljfsd ','cod02','2',4,1,1,1,NULL,NULL,'2019-05-13 02:25:06',NULL,'0000-00-00 00:00:00'),(8,'kjelkjsdklfj','sdklfjdsklj ','kldjfsdklj','1',2,2,1,1,NULL,NULL,'2019-05-13 02:25:06',NULL,NULL),(9,'PRoduto do Form','Decrição do form ','CF12',NULL,4,2,1,1,NULL,NULL,'2019-05-13 00:00:47',NULL,NULL),(10,'Controle de estoque','Novo supply chain teste para analitico ','SC01','1.0.0',3,6,1,1,1,NULL,'2019-05-13 00:02:48','2019-05-26 17:10:42',NULL),(11,'sdjfhksjahjk','dsjkfsdjkfhsk ','jksdjhfkjsdh',NULL,3,3,1,1,NULL,NULL,'2019-05-13 00:04:31',NULL,NULL),(12,'sdjfhksjahjk','dsjkfsdjkfhsk ','jksdjhfkjsdh',NULL,3,3,1,1,NULL,NULL,'2019-05-13 00:08:35',NULL,NULL),(13,'Produto do form','  dfgsdfgsd','fdgd','1.2.3',2,3,1,1,1,NULL,'2019-05-13 00:13:05','2019-05-25 13:12:40',NULL),(14,'Middleware','Atualizacao para o relatorio ','MAM','1.0.0',2,5,1,1,NULL,NULL,'2019-05-26 17:02:24',NULL,NULL),(15,'Ttyt',' Ttru','Ty6','134',3,5,1,1,NULL,NULL,'2019-05-26 17:32:14',NULL,NULL);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto_status`
--

DROP TABLE IF EXISTS `produto_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto_status` (
  `id` mediumint(6) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `ativo` tinyint(1) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_status`
--

LOCK TABLES `produto_status` WRITE;
/*!40000 ALTER TABLE `produto_status` DISABLE KEYS */;
INSERT INTO `produto_status` VALUES (1,'Teste',1),(2,'Homologação',1),(3,'Produção',1),(4,'Descontinuado',1);
/*!40000 ALTER TABLE `produto_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto_tipo`
--

DROP TABLE IF EXISTS `produto_tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto_tipo` (
  `id` mediumint(6) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `ativo` tinyint(1) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_tipo`
--

LOCK TABLES `produto_tipo` WRITE;
/*!40000 ALTER TABLE `produto_tipo` DISABLE KEYS */;
INSERT INTO `produto_tipo` VALUES (1,'Infraestrutura',1),(2,'Suporte',1),(3,'Rede',1),(4,'Business Intelligence (B.I.)',1),(5,'ERP',1),(6,'Supply Chain',1);
/*!40000 ALTER TABLE `produto_tipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `usuario_id` mediumint(6) unsigned NOT NULL AUTO_INCREMENT,
  `contato_id` mediumint(6) unsigned DEFAULT '1',
  `tipo` mediumint(6) unsigned NOT NULL,
  `login` varchar(100) DEFAULT NULL,
  `pass` varchar(100) DEFAULT NULL,
  `ativo` tinyint(1) unsigned NOT NULL,
  `usuario_inclusao_id` mediumint(6) unsigned DEFAULT NULL,
  `usuario_alteracao_id` mediumint(6) unsigned DEFAULT NULL,
  `usuario_inativacao_id` mediumint(6) unsigned DEFAULT NULL,
  `data_inclusao` datetime NOT NULL,
  `data_alteracao` datetime DEFAULT NULL,
  `data_inativacao` datetime DEFAULT NULL,
  PRIMARY KEY (`usuario_id`),
  KEY `contato_id` (`contato_id`),
  KEY `tipo` (`tipo`),
  KEY `usuario_inclusao_id` (`usuario_inclusao_id`),
  KEY `usuario_alteracao_id` (`usuario_alteracao_id`),
  KEY `usuario_inativacao_id` (`usuario_inativacao_id`),
  CONSTRAINT `usuario_fk2` FOREIGN KEY (`tipo`) REFERENCES `usuario_tipo` (`usuario_tipo_id`),
  CONSTRAINT `usuario_fk3` FOREIGN KEY (`usuario_inclusao_id`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `usuario_fk4` FOREIGN KEY (`usuario_alteracao_id`) REFERENCES `usuario` (`usuario_id`),
  CONSTRAINT `usuario_fk5` FOREIGN KEY (`usuario_inativacao_id`) REFERENCES `usuario` (`usuario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,1,1,'admin','md5(123)',1,1,NULL,NULL,'2019-02-01 00:00:00',NULL,NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_tipo`
--

DROP TABLE IF EXISTS `usuario_tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_tipo` (
  `usuario_tipo_id` mediumint(6) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `ativo` tinyint(1) unsigned NOT NULL,
  PRIMARY KEY (`usuario_tipo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_tipo`
--

LOCK TABLES `usuario_tipo` WRITE;
/*!40000 ALTER TABLE `usuario_tipo` DISABLE KEYS */;
INSERT INTO `usuario_tipo` VALUES (1,'Administrador',1),(2,'Colaborador',1),(3,'Cliente',1),(4,'Colaborador',1),(5,'Analítico',1);
/*!40000 ALTER TABLE `usuario_tipo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-27  3:11:28
