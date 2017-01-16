package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.FactoryDAO;
import dao.I_ProduitDAO;
import dao.ProduitDAO_SQL;
import metier.*;

public class ControleurCatalogue {
	private Catalogue cat;
	private I_ProduitDAO dao;
	public ControleurCatalogue(Catalogue cat,I_ProduitDAO dao){
		this.cat = cat;
		this.dao = dao;
		try {
			this.initialisterCatalogue();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		this.dao.delete(nom);
	}

	public void initialisterCatalogue() throws SQLException{
		List<I_Produit> produits = this.dao.read();
		for(I_Produit p : produits){
			this.cat.addProduit(p);
		}

	}


}
