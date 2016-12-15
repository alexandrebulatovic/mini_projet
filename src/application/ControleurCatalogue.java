package application;

import metier.*;

public class ControleurCatalogue {
	private Catalogue cat;
	public ControleurCatalogue(Catalogue cat){
		this.cat=cat;
	}
	
	public void addProduit(String nom, double prix, int qte) {
		this.cat.addProduit(nom, prix, qte);
	}

	public String[] getNomProduits(){
		return this.cat.getNomProduits();
	}
	
	public void removeProduit(String nom){
		this.cat.removeProduit(nom);
	}
}
