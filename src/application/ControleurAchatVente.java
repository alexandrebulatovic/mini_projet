package application;

import metier.Catalogue;
import metier.I_Produit;
import metier.Produit;
import dao.I_ProduitDAO;

public class ControleurAchatVente {
	private Catalogue cat;
	private I_ProduitDAO dao;
	
	public ControleurAchatVente(Catalogue cat,I_ProduitDAO dao){
		this.cat=cat;
		this.dao=dao;
	}
	
	public void acheterStock(String nom, int qte){
		I_Produit produit = new Produit(nom,0,qte);
		this.cat.acheterStock(nom, qte);
		this.dao.updateProduit(produit);
	}

	public void vendreStock(String nom, int qte){
		I_Produit produit = new Produit(nom,0,-qte);
		this.cat.vendreStock(nom, qte);
		this.dao.updateProduit(produit);
	}
}
