CREATE TABLE Catalogues
(
id_catalogue INT NOT NULL,
nom VARCHAR(255) NOT NULL UNIQUE,
CONSTRAINT pk_id_catalogue PRIMARY KEY (id_catalogue)
)


CREATE TABLE Produits
(
id_produit INT NOT NULL,
nom VARCHAR(255) NOT NULL UNIQUE,
prixHT FLOAT NOT NULL,
quantite INT NOT NULL,
nom_catalogue VARCHAR(255) NOT NULL,
CONSTRAINT pk_produit PRIMARY KEY (id_produit),
CONSTRAINT fk_produit_catalogue FOREIGN KEY (nom_catalogue) REFERENCES Catalogues(nom)
)

CREATE SEQUENCE id_catalogue_sequence;

CREATE OR REPLACE TRIGGER auto_increment_id_catalogue
  BEFORE INSERT ON Catalogues
  FOR EACH ROW
BEGIN
  SELECT id_catalogue_sequence.NEXTVAL
  INTO :new.id_catalogue
  FROM Dual;
END;

CREATE SEQUENCE id_produit_sequence;

CREATE OR REPLACE TRIGGER auto_increment_id_produit
  BEFORE INSERT ON Produits
  FOR EACH ROW
BEGIN
  SELECT id_produit_sequence.NEXTVAL
  INTO :new.id_produit
  FROM Dual;
END;
