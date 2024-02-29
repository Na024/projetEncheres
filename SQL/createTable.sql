-- Script de création de la base de données ENCHERES
--   type :      SQL Server 2012
--

-- UTILISATEURS--
CREATE TABLE UTILISATEURS (
    no_utilisateur   INTEGER IDENTITY(1,1) NOT NULL,
    pseudo           VARCHAR(30) NOT NULL,
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(100) NOT NULL,
    telephone        VARCHAR(10),
    rue              VARCHAR(100) NOT NULL,
    code_postal      VARCHAR(5) NOT NULL,
    ville            VARCHAR(30) NOT NULL,
    mot_de_passe     VARCHAR(256) NOT NULL,
    credit           INTEGER NOT NULL,
    administrateur   bit NOT NULL
)

ALTER TABLE UTILISATEURS ADD constraint utilisateur_pk PRIMARY KEY (no_utilisateur)

insert into UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
values ('toto', 'POTTER', 'Harry', 'harry@hotmail.fr','0698854545', 'Rue de la liberté','79450', 'NIORT','$2a$10$hQbphOMxReV3rK4BIONRAuQlS3v.Ts9m8bhkSBjdmESqrlK94XKH6', '740',0);
insert into UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
values ('chihiro', 'TAUTOU','Audrey','audrey@gmail.fr', '0854785682','Avenue de la joie','78545','POISSY','$2a$10$VJMLdtIETBxCjcVnbVT3XO6ypNRMDnhrXcdnNWv4.Za7Z1G1A8UqS','850',0);
insert into UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
values ('totoro', 'GRANGER','Hermione','hermione@gmail.fr', '0854745263','Boulevard de la terre','74500','TOULOUSE','$2a$10$IjGSqKwAeJHa0NQyTj9SqOeRDXSvmlab.egWqSwusd0zPdarq9Rhy','850',1);

select * from UTILISATEURS

truncate table UTILISATEURS

drop table UTILISATEURS



-- CATEGORIES--

CREATE TABLE CATEGORIES (
    no_categorie   INTEGER IDENTITY(1,1) NOT NULL,
    libelle        VARCHAR(30) NOT NULL
)
insert into CATEGORIES (libelle)
values('Informatique')
insert into CATEGORIES (libelle)
values('Ameublement')
insert into CATEGORIES (libelle)
values('Vêtement')
insert into CATEGORIES (libelle)
values ('Sport&loisir')

ALTER TABLE CATEGORIES ADD constraint categorie_pk PRIMARY KEY (no_categorie)

select * from CATEGORIES


-- ENCHERES--

CREATE TABLE ENCHERES (
    no_utilisateur   INTEGER NOT NULL,
    no_article       INTEGER NOT NULL,
    date_enchere     datetime NOT NULL,
	montant_enchere  INTEGER NOT NULL

)

ALTER TABLE ENCHERES ADD constraint enchere_pk PRIMARY KEY (no_utilisateur, no_article)

ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_articles_vendus_fk FOREIGN KEY ( no_article )
        REFERENCES ARTICLES_VENDUS ( no_article )
ON DELETE NO ACTION 
    ON UPDATE no action 

-- RETRAITS--

CREATE TABLE RETRAITS (
	no_article         INTEGER NOT NULL,
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(15) NOT NULL,
    ville            VARCHAR(30) NOT NULL
)

ALTER TABLE RETRAITS ADD constraint retrait_pk PRIMARY KEY  (no_article)

ALTER TABLE RETRAITS
    ADD CONSTRAINT retraits_articles_vendus_fk FOREIGN KEY ( no_article )
        REFERENCES ARTICLES_VENDUS ( no_article )
ON DELETE NO ACTION 
    ON UPDATE no action 

-- ARTICLES_VENDUS--

CREATE TABLE ARTICLES_VENDUS (
    no_article                    INTEGER IDENTITY(1,1) NOT NULL,
    nom_article                   VARCHAR(30) NOT NULL,
    description                   VARCHAR(300) NOT NULL,
	date_debut_encheres           DATE NOT NULL,
    date_fin_encheres             DATE NOT NULL,
    prix_initial                  INTEGER,
    prix_vente                    INTEGER,
    no_utilisateur                INTEGER NOT NULL,
    no_categorie                  INTEGER NOT NULL
)



ALTER TABLE ARTICLES_VENDUS ADD constraint articles_vendus_pk PRIMARY KEY (no_article)

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT encheres_utilisateur_fk FOREIGN KEY ( no_utilisateur ) REFERENCES UTILISATEURS ( no_utilisateur )
ON DELETE NO ACTION 
    ON UPDATE no action 


ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT articles_vendus_categories_fk FOREIGN KEY ( no_categorie )
        REFERENCES categories ( no_categorie )
ON DELETE NO ACTION 
    ON UPDATE no action 


ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT ventes_utilisateur_fk FOREIGN KEY ( no_utilisateur )
        REFERENCES utilisateurs ( no_utilisateur )
ON DELETE NO ACTION 
    ON UPDATE no action 

insert into ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)
values('MacBook Pro m3 ','Écran 14, Puce m3, Couleur noir sidéral,Mémoire 18Go,Stockage 1To','01/03/2024','31/03/2024','800',' ','1','1')
insert into ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)
values('Robe de plage ','Robe dos nu à fines bretelles.Motif fleurs des îles.','03/05/2024','31/05/2024','60',' ','2','3')
insert into ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)
values('Switch Oled','Écran 7",Mémoire 64Go,Port Ethernet intégré','14/03/2024','26/03/2024','300',' ','1','4')
insert into ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)
values('Canapé 3 pl. avec méridienne','Matière tissu,Possibilité de la déplier en lit','06/04/2024','15/04/2024','140',' ','3','2')



select * from ARTICLES_VENDUS


--supression données sur toutes les tables--
truncate table UTILISATEURS
truncate table CATEGORIES
truncate table ENCHERES
truncate table RETRAITS
truncate table ARTICLES_VENDUS


--supression des tables--
drop table RETRAITS
drop table ENCHERES
drop table  ARTICLES_VENDUS
drop table CATEGORIES
drop table UTILISATEURS