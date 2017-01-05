package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.ProduitDAO_SQL;
import metier.*;

public class ControleurCatalogue {
	private Catalogue cat;
	private ProduitDAO_SQL dao;
	public ControleurCatalogue(Catalogue cat){
		this.cat=cat;
		this.dao = new ProduitDAO_SQL();
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
		List<Produit> produits = this.dao.read();
		for(Produit p : produits){
			this.cat.addProduit(p);
		}

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
