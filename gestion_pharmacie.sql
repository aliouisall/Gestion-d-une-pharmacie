-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 23 mars 2018 à 02:30
-- Version du serveur :  10.1.29-MariaDB
-- Version de PHP :  7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gestion_pharmacie`
--

-- --------------------------------------------------------

--
-- Structure de la table `achat`
--

CREATE TABLE `achat` (
  `idAchat` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `idProd` int(11) DEFAULT NULL,
  `nomProduit` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `achat`
--

INSERT INTO `achat` (`idAchat`, `date`, `idProd`, `nomProduit`) VALUES
(7, '2000-10-00 00:00:00', 7, 'Hexaspray Collutoire Mal de Gorge Flacon 30 g'),
(8, '2000-10-20 00:00:00', 39, 'heptamyl');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `email` varchar(50) NOT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `nom` varchar(30) DEFAULT NULL,
  `passwd` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `email` varchar(50) NOT NULL,
  `num_tel` int(11) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `adresse` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `pharmacien`
--

CREATE TABLE `pharmacien` (
  `email` varchar(50) NOT NULL,
  `passwd` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `idProd` int(11) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prix` int(11) DEFAULT NULL,
  `type` enum('Médicament','Organe et tissu','Produit Sanguin Labile','Lait maternel','Lentille','Produit Cosmétique') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`idProd`, `nom`, `prix`, `type`) VALUES
(13, 'Oscillococcinum Etats Grippaux 6 Doses', 4263, 'Médicament'),
(15, 'Lehning L72 Troubles Mineurs du Sommeil 30 ml', 3273, 'Médicament'),
(17, 'Granions de Sélénium 30 Ampoules buvables', 4536, 'Médicament'),
(18, 'Bioderma Créaline H2O', 1725, 'Produit Cosmétique'),
(20, 'La Roche-Posay Effaclar K+ Soin Peaux Grasses', 6546, 'Produit Cosmétique'),
(23, 'Caudalie Eau de Raisin 200 ml', 5182, 'Produit Cosmétique'),
(24, 'Caudalie Eau de Raisin 200 ml', 5182, 'Produit Cosmétique'),
(27, 'Laino Shampooing Douche 3en1 Monoï de Tahiti', 1935, 'Produit Cosmétique'),
(28, 'Vichy Dercos Aminexil Shampooing Energisant', 4558, 'Produit Cosmétique'),
(30, 'Vichy Dercos Aminexil Shampooing Energisant', 4558, 'Produit Cosmétique'),
(31, 'Capricare 3 Lait de Chèvre Croissance Dès 12 Mois', 15743, 'Lait maternel'),
(32, 'Novalac AR Lait Poudre 6-12 Mois', 10167, 'Lait maternel'),
(33, 'Novalac AR Lait Poudre 6-12 Mois', 10167, 'Lait maternel'),
(34, 'Nestlé Nidal Lait Croissance 3 ', 6526, NULL),
(35, 'Nestlé Nidal Lait Croissance 3 ', 6526, 'Lait maternel'),
(36, 'Abbott Complete Revitalens Désinfectant Lentilles', 3273, 'Lentille'),
(37, 'Abbott Complete Revitalens Désinfectant Lentilles', 3273, 'Lentille');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `achat`
--
ALTER TABLE `achat`
  ADD PRIMARY KEY (`idAchat`),
  ADD KEY `fk_Prod` (`idProd`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`email`);

--
-- Index pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD PRIMARY KEY (`email`);

--
-- Index pour la table `pharmacien`
--
ALTER TABLE `pharmacien`
  ADD PRIMARY KEY (`email`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`idProd`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `achat`
--
ALTER TABLE `achat`
  MODIFY `idAchat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `idProd` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
