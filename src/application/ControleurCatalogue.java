package application;

import java.util.List;
import dao.I_ProduitDAO;
import metier.Catalogue;
import metier.I_Catalogue;
import metier.I_Produit;
import metier.Produit;

/**
 * Permet d'initialiser le {@code Catalogue} du programme avec 
 * les {@code Produit} stockés dans la base de données.
 * <P>
 * Cette classe se charge aussi d'ajouter et de supprimer des produits à notre 
 * catalogue et propage les modifications à la base de données.
 */

public class ControleurCatalogue {

	/* ATTRIBUTS */

	private I_Catalogue catalogue;

	private I_ProduitDAO dao;

	/* METHODES */

	/**
	 * Remplit le {@code Catalogue}.
	 * @param catalogue : {@code Catalogue} à utiliser.
	 * @param dao : {@code ProduitDAO} à utiliser pour obtenir les produits.
	 */
	public ControleurCatalogue(I_Catalogue catalogue, I_ProduitDAO dao){
		this.catalogue = catalogue;
		this.dao = dao;
		this.remplirCatalogue();
	}

	public void addProduit(String nom, double prix, int qte) {
		Produit p = new Produit(nom, prix, qte);
		this.catalogue.addProduit(p);
		this.dao.create(p);

	}

	public String[] getNomProduits(){
		return this.catalogue.getNomProduits();
	}

	public void removeProduit(String nom){
		this.catalogue.removeProduit(nom);
		this.dao.delete(nom);
	}

	/**
	 * Remplit le {@code Catalogue} à partir des données persistantes.
	 * @see Catalogue
	 */
	public void remplirCatalogue() {
		List<I_Produit> produits = this.dao.findAll();
		for(I_Produit p : produits){
			this.catalogue.addProduit(p);
		}

	}


}
