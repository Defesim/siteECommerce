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

