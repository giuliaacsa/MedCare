-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 01/11/2025 às 23:03
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `medcare`
--
CREATE DATABASE IF NOT EXISTS `medcare` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `medcare`;

-- --------------------------------------------------------

--
-- Estrutura para tabela `medicamentos`
--

CREATE TABLE `medicamentos` (
  `id` bigint(20) NOT NULL,
  `nome_medicamento` varchar(255) NOT NULL,
  `dosagem_valor` double NOT NULL,
  `dosagem_unidade` varchar(255) NOT NULL,
  `frequencia_por_dia` int(11) NOT NULL,
  `horarios_personalizados` varchar(1000) DEFAULT NULL,
  `data_inicio` date NOT NULL,
  `data_fim` date DEFAULT NULL,
  `observacoes` varchar(2000) DEFAULT NULL,
  `ativo` tinyint(1) DEFAULT 1,
  `criado_em` timestamp NOT NULL DEFAULT current_timestamp(),
  `atualizado_em` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `tomado` tinyint(1) DEFAULT 0,
  `ultima_tomada` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `medicamentos`
--

INSERT INTO `medicamentos` (`id`, `nome_medicamento`, `dosagem_valor`, `dosagem_unidade`, `frequencia_por_dia`, `horarios_personalizados`, `data_inicio`, `data_fim`, `observacoes`, `ativo`, `criado_em`, `atualizado_em`, `tomado`, `ultima_tomada`) VALUES
(7, 'Losarta', 1, 'mg', 1, '09:00', '2025-09-30', NULL, NULL, 1, '2025-09-27 05:35:38', '2025-10-12 20:08:46', 1, '2025-10-12 23:08:46'),
(10, 'Dipirona', 1, 'mg', 1, '09:00', '2025-09-29', NULL, NULL, 0, '2025-09-29 23:00:08', '2025-10-12 23:08:53', 1, '2025-10-12 23:08:46'),
(12, 'Daflon', 1, 'comprimido', 2, '09:00, 21:00', '2025-10-12', '2025-10-14', 'Após alimentação', 1, '2025-10-12 23:08:07', '2025-10-12 20:08:45', 1, '2025-10-12 23:08:45');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `medicamentos`
--
ALTER TABLE `medicamentos`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `medicamentos`
--
ALTER TABLE `medicamentos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
