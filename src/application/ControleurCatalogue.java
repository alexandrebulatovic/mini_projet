package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.FactoryDAO;
import dao.I_ProduitDAO;
import dao.ProduitDAO_SQL;
import metier.*;

/**
 * Correspond au scenario "Creer ou Supprimer un produit".
 */
public class ControleurCatalogue {
	private Catalogue cat;
	private I_ProduitDAO dao;
	public ControleurCatalogue(Catalogue cat,I_ProduitDAO dao){
		this.cat = cat;
		this.dao = dao;
		this.initialiseCatalogue();
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

	/**
	 * Initialise le {@code Catalogue} à partir des données persistantes.
	 * @see Catalogue
	 */
	public void initialiseCatalogue() {
		List<I_Produit> produits = this.dao.findAll();
		for(I_Produit p : produits){
			this.cat.addProduit(p);
		}

	}


}
