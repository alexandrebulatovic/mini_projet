package dao;

import java.util.List;

import metier.I_Produit;


public interface I_ProduitDAO {

	/** Crée un nouveau produit dans la base de données.
	 * @param produit : objet {@code I_Produit} à ajouter.
	 * @param catalogue : nom du catalogue du produit à ajouter. */
	public abstract boolean create(I_Produit produit,String catalogue);

	/** Génère une liste de tous les produits stockés dans la base de données.
	 * @return un objet {@code List} contenant tous les produits. */
	//public abstract List<I_Produit> findAll();

	/** Cherche le produit demandé dans la base de données.
	 * @param nom : nom du produit à générer.
	 * @return un objet {@code I_Produit} correspondant à un produit existant. */
	public abstract I_Produit findByName(String nom);

	/** Génère une liste de tous les produits d'un catalogue stockés dans la base de données.
	 *  @param catalogue : nom du ctatalogue.
	 *  @return un objet {@code List} contenant tous les produits. */
	public abstract List<I_Produit> findAll(String catalogue);
	
	/** Supprime un produit de la base de données.
	 * @param nom : nom du produit à supprimer. */
	boolean delete(String nom, String nomCtalogue);
	/** Met à jour la quantité d'un produit dans la base de données.
	 * @param nom : nom du produit à mettre à jour.
	 * @param quantite : quantité à rajouter au stock. 
	 * @return Vrai si l'ajout a réussi, faux sinon. */
	boolean addQuantite(String nom, String catalogue, int quantite);
	
	/** Met à jour la quantité d'un produit dans la base de données.
	 * @param nom : nom du produit à mettre à jour.
	 * @param quantite : quantité à enlever du stock. 
	 * @return Vrai si le retrait a réussi, faux sinon. */
	public abstract boolean removeQuantite(String nom,String catalogue, int quantite);
}