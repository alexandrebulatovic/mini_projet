package application;

import dao.ProduitDAO;
import metier.*;

public class ControleurCatalogue {
	private Catalogue cat;
	private ProduitDAO dao;
	public ControleurCatalogue(Catalogue cat){
		this.cat=cat;
		this.dao = new ProduitDAO();
	}
	
	public void addProduit(String nom, double prix, int qte) {
		Produit p = new Produit(nom, prix, qte);
		this.cat.addProduit(p);
		this.dao.create(p);
		
	}

	public String[] getNomProduits(){
		return this.cat.getNomProduits();
	}
	
	public void removeProduit(String nom){
		this.cat.removeProduit(nom);
		this.dao.remove(nom);
	}
}
