package application;

import metier.I_Catalogue;
import metier.I_Produit;
import metier.Produit;
import dao.I_ProduitDAO;

/**
 * Correspond au scenario "Enregistrer un achat ou une vente".
 */
public class ControleurAchatVente {

	/* ATTRIBUTS */

	private I_Catalogue catalogue;

	private I_ProduitDAO dao;

	/* METHODES */

	public ControleurAchatVente(I_Catalogue catalogue, I_ProduitDAO dao){
		this.catalogue = catalogue;
		this.dao = dao;
	}

	public void acheterStock(String nom, int qte){
		I_Produit produit = new Produit(nom,0,qte);
		this.catalogue.acheterStock(nom, qte);
		this.dao.update(produit);
	}

	public void vendreStock(String nom, int qte){
		I_Produit produit = new Produit(nom,0,-qte);
		this.catalogue.vendreStock(nom, qte);
		this.dao.update(produit);
	}
}
