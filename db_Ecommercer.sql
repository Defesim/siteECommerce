create database E_commerce;
use E_commerce;
create table E_commerce.Categorie(nom_Categorie varchar(50), description varchar(200),
constraint pk_Categorie primary key (nom_Categorie));

create table E_commerce.Produit( id_Produit integer auto_increment, nom varchar(100),prix double, quantite integer,
description varchar(200), selectionner boolean, photo varchar(200), categorie_NOM varchar(50),
constraint pk_Produit primary key (id_Produit),
foreign key (categorie_NOM) references Categorie(nom_Categorie)
);

create table E_commerce.Utilisateur(id_Utilisateur integer auto_increment, identifiant varchar(50), mot_de_passe varchar(50),
constraint pk_Utilisateur primary key (id_Utilisateur)
);

create table E_commerce.Clients(id_Client integer auto_increment, nom_Client varchar (50),
 adresse varchar (100), email varchar(100), telephone varchar(10),
 constraint pk_Clients primary key (id_Client)
);

insert into  Categorie (nom_Categorie, description) values 
('Jeux Videos', 'Categorie dédiée aux jeux videos sur PC, Playstation, XBox, Nitendo Switch');
insert into  Categorie (nom_Categorie, description) values 
('Livres', 'Categorie dédiée à la littérature de tout genre et tout pays');
insert into  Categorie (nom_Categorie, description) values 
('Sport', 'Categorie dédiée aux sports collectifs et individuels');
insert into  Categorie (nom_Categorie, description) values 
('Jeux de sociétés', 'Categorie dédiée aux jeux de sociétés se pratiquant entre 2 et 10 joueurs');

insert into  Produit (nom, prix, quantite, description, selectionner, photo, categorie_NOM) values 
('Ballon de Zidane',50.00,10,'ballon de football pour jouer comme Zidane',true,  'ballonFootball.jpg', 'Sport');
insert into  Produit (nom, prix, quantite, description, selectionner, photo, categorie_NOM) values 
('Ballon de Handball',3300.00,8,'ballon de handball touché par Adrien Tessanne :O',true,  'Handball.jpg', 'Sport');
insert into  Produit (nom, prix, quantite, description, selectionner, photo, categorie_NOM) values 
('Monopoly',18.00,54,'jeu pour se facher avec sa famille en 15 min',true,  'monopoly.jpg', 'Jeux de sociétés');
insert into  Produit (nom, prix, quantite, description, selectionner, photo, categorie_NOM) values 
('SmaWorld',58.00,8,'jeu super coool !!!!',true,  'smallworld.jpg', 'Jeux de sociétés');
insert into  Produit (nom, prix, quantite, description, selectionner, photo, categorie_NOM) values 
('Fifa 2026 (en exclusivité)',580.00,2,'Jeu de football avec 6 ans d avance !!!!',true,  'fifa.jpg', 'Jeux Videos');
insert into  Produit (nom, prix, quantite, description, selectionner, photo, categorie_NOM) values 
('Age of Empire',8.00,87,'jeu pour les nostalgiques',true,  'ageofempire.jpg', 'Jeux Videos');
insert into  Produit (nom, prix, quantite, description, selectionner, photo, categorie_NOM) values 
('Tchoupi',4.00,8,'livre pour les gens immatures :p',true,  'tchoupie.jpg', 'Livres');
insert into  Produit (nom, prix, quantite, description, selectionner, photo, categorie_NOM) values 
('Tchoupi saison 2',14.00,6,'livre pour les gens toujours très immatures :p :p',true,  'tchoupie2.jpg', 'Livres');

insert into utilisateur(identifiant, mot_de_passe) values ('admin','admin');

insert into Clients (nom_Client, adresse, email, telephone) values ('Bob','10 route des bobs 58963 Bobcity','bob.bob@bob.fr','0676826165');

select * from Produit;
select * from Panier;

SELECT * FROM Produit where categorie_NOM = 'Jeux de sociétés' ORDER BY id_Produit DESC ;

drop table Panier;
create table E_commerce.Panier( id_Produit integer, nom varchar(100),prix double, quantite integer

);

insert into Panier select id_Produit, nom, prix, quantite from Produit where id_Produit = 3;
update Produit set quantite = quantite - 1 where id_Produit = 1;
truncate table Panier; 
truncate table Commande; 

drop view TotaleCommande;

drop table Commande;
create table Commande(prixTotale double, 
id_Command integer auto_increment, DATE_Commande  TIMESTAMP (0),
constraint pk_Commande primary key (id_Command)

);

Create view TotaleCommande as
SELECT SUM(prix * quantite) AS totale FROM Panier;

select * from TotaleCommande;

insert into Commande (prixTotale) select totale from TotaleCommande;



select * from Commande;

select * from Produit WHERE nom like '%Ballon%';

insert into Panier (id_Produit, nom, prix, quantite) values ((select id_Produit from Produit where id_Produit = 1), (select nom from Produit where id_Produit = 1), (select prix from Produit where id_Produit = 1), 1);
update Panier set quantite=quantite+1; 




SET SQL_SAFE_UPDATES = 0;

insert into Commande (prixTotale)
SELECT SUM(prix * quantite)
FROM Panier;



create table BilanCommande (id_BilanCommande integer auto_increment, prixtotale double,DATE_Commande  TIMESTAMP (0),
constraint pk_Commande primary key (id_BilanCommande));
insert into BilanCommande (prixtotale)
SELECT prixTotale
FROM Commande;

create table BilanPanier (id_Produit integer, nom varchar(200), prix double, quantite integer, IDBilanCommande integer,
foreign key (IDBilanCommande) references BilanCommande(id_BilanCommande)
 );



INSERT INTO ma_nouvelle_table (numero_client, date, info1) SELECT '000' || id_client, paate, info1 FROM ma_table;

SELECT COUNT(*) FROM bilanpanier;

drop procedure if exists doWhile;
DELIMITER //  
CREATE PROCEDURE doWhile()   
BEGIN
DECLARE i INT DEFAULT 2376921001; 
WHILE (i <= 237692200) DO
       INSERT INTO `mytable` (code, active, total) values (i, 1, 1);
       SET i = i+1;
END WHILE;
END;
//  

CALL doWhile();





