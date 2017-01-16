package application;

import metier.Catalogue;
import dao.I_ProduitDAO;

public class ControleurAchatVente {
	private Catalogue cat;
	private I_ProduitDAO dao;
	
	public ControleurAchatVente(Catalogue cat,I_ProduitDAO dao){
		this.cat=cat;
		this.dao=dao;
	}
	
	public void acheterStock(String nom, int qte){
		this.cat.acheterStock(nom, qte);
		this.dao.updateQuantité(nom, qte);
	}

	public void vendreStock(String nom, int qte){
		this.cat.vendreStock(nom, qte);
		this.dao.updateQuantité(nom, -qte);
	}
}
