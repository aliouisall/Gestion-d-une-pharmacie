CREATE DATABASE Gestion_Pharmacie;

USE Gestion_Pharmacie;

CREATE TABLE `Fournisseur` (
  `email` varchar(50),
  `num_tel` int,
  `nom` varchar(50),
  `adresse` varchar(50),
  CONSTRAINT pk_Fournisseur PRIMARY KEY (email)
);

CREATE TABLE `Pharmacien` (
  `email` varchar(50),
  `passwd` varchar(40),
  CONSTRAINT pk_Pharmacien PRIMARY KEY (email)
);

CREATE TABLE `Produit` (
  `idProd` int auto_increment,
  `nom` varchar(50),
  `prix` int,
  `type` enum('Médicament', 'Organe et tissu', 'Produit Sanguin Labile', 'Lait maternel', 'Lentille', 'Produit Cosmétique'),
  CONSTRAINT pk_Produit PRIMARY KEY (idProd)
);

CREATE TABLE `Client` (
  `email` varchar(50),
  `prenom` varchar(50),
  `nom` varchar(30),
  `passwd` varchar(40),
  CONSTRAINT pk_Client PRIMARY KEY (email)
);

CREATE TABLE `Achat` (
  `idAchat` int auto_increment,
  `qtAchat` int,
  `date` datetime,
  `email` varchar(50),
  `idProd` int,
  CONSTRAINT pk_Achat PRIMARY KEY (idAchat),
  CONSTRAINT fk_Client FOREIGN KEY (email) REFERENCES Client (email),
  CONSTRAINT fk_Prod FOREIGN KEY (idProd) REFERENCES Produit (idProd)
);

