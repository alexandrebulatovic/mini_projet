package dao;

import java.util.List;

import metier.I_Produit;
import metier.Produit;

/** Cette interface fournit des méthodes permettant de manipuler une base de donnees.
 * 	<P>
 *  Une classe correspondant à une nouvelle technologie de stockage devra implémenter cette 
 *  interface pour continuer à stocker les données. */

public interface I_ProduitDAO {

	/** Crée un nouveau produit dans la base de données.
	 * @param p : objet {@code Produit} à ajouter. */
	public abstract void create(I_Produit p);

	/** Supprime un produit de la base de données.
	 * @param nom : nom du produit à supprimer. */
	public abstract void delete(String nom);

	/** Génère une liste de tous les produits stockés dans la base de données.
	 * @return la liste générée. */
	public abstract List<I_Produit> findAll();

	/** Génère le produit demandé parmi ceux stockés dans la base de données.
	 * @param nom : nom du produit stocké.
	 * @return un objet {@code Produit} correspondant à un produit existant. */
	public abstract I_Produit findByName(String nom);

	/** Met à jour la quantité d'un produit.
	 * @param nom : nom du produit à mettre à jour.
	 * @param qte : nouvelle quantité. */
	//public abstract void updateQuantité(String nom, int qte);

	/** Met à jour un produit de la base de données..
	 * @param p : {@code Produit} avec les informations à jour. */
	public abstract void updateProduit(I_Produit p);
}
