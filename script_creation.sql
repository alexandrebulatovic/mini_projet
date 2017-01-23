CREATE TABLE Produits
(
id_produit INT NOT NULL,
nom VARCHAR(255) NOT NULL UNIQUE,
prixHT FLOAT NOT NULL,
quantite INT NOT NULL,
CONSTRAINT pk_produit PRIMARY KEY (id_produit)
)

CREATE SEQUENCE id_produit_sequence;

CREATE OR REPLACE TRIGGER auto_increment_id_produit
  BEFORE INSERT ON Produits
  FOR EACH ROW
BEGIN
  SELECT id_produit_sequence.NEXTVAL
  INTO :new.id_produit
  FROM Dual;
END;
