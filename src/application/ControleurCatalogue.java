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
	 * Initialise le {@code Catalogue} avec les produits enregistrés.
	 * @param catalogue : {@code Catalogue} à utiliser.
	 * @param dao : {@code ProduitDAO} à utiliser pour obtenir les produits.
	 */
	public ControleurCatalogue(I_Catalogue catalogue, I_ProduitDAO dao){
		this.catalogue = catalogue;
		this.dao = dao;
		this.remplirCatalogue();
	}

	/**
	 * Ajoute un produit au {@code Catalogue}.
	 * @param nom : nom du produit.
	 * @param prix : prix hors taxe du produit.
	 * @param qte : quantité en stock du produit.
	 */
	public void addProduit(String nom, double prix, int qte) 
	{
		Produit p = new Produit(nom, prix, qte);

		if (this.catalogue.addProduit(p))
			this.dao.create(p);
	}

	/**
	 * Supprime un produit du {@code Catalogue}.
	 * @param nom : nom du produit à supprimer.
	 */
	public void removeProduit(String nom)
	{
		if (this.catalogue.removeProduit(nom))
			this.dao.delete(nom);
	}

	/**
	 * Remplit le catalogue à partir des données persistantes.
	 * @see Catalogue
	 */
	public void remplirCatalogue() {
		List<I_Produit> listeProduits = this.dao.findAll();

		this.catalogue.addProduits(listeProduits);
	}

	/**
	 * Permet d'obtenir un tableau de tous les noms des produits
	 * du catalogue.
	 * @return un tableau de {@code String}.
	 */
	public String[] getNomProduits(){
		return this.catalogue.getNomProduits();
	}

}
