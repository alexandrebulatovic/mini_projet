package application;

import presentation.*;
import metier.*;

public class ControleurAchatsVentes {
	private FenetreAchat fa;
	private FenetreVente fv;

	private Catalogue cat;

	public ControleurAchatsVentes(Catalogue cat){
		this.cat=cat;	

	}

	public String[] getNomProduits(){
		return this.cat.getNomProduits();
	}

	public void acheterStock(String nom, int qte){
		this.cat.acheterStock(nom, qte);
	}
	
	public void vendreStock(String nom, int qte){
		this.cat.vendreStock(nom, qte);
	}



}
