package dao;

import java.util.List;
import metier.I_Produit;

/** Cette interface fournit des méthodes permettant de manipuler une base de donnees.
 * 	<P>
 * <strong>Note:</strong>
 *  Une classe correspondant à une nouvelle technologie de stockage devra implémenter cette 
 *  interface pour continuer à stocker les données. Il faudra aussi veiller à rajouter la 
 *  technologie employée dans la méthode {@link DAOFactory#createDAO(int)}.
 *  */

public interface I_ProduitDAO {

	/** Crée un nouveau produit dans la base de données.
	 * @param p : objet {@code I_Produit} à ajouter. */
	public abstract boolean create(I_Produit p);

	/** Supprime un produit de la base de données.
	 * @param nom : nom du produit à supprimer. */
	public abstract boolean delete(String nom);

	/** Génère une liste de tous les produits stockés dans la base de données.
	 * @return un objet {@code List} contenant tous les produits. */
	public abstract List<I_Produit> findAll();

	/** Cherche le produit demandé dans la base de données.
	 * @param nom : nom du produit à générer.
	 * @return un objet {@code I_Produit} correspondant à un produit existant. */
	public abstract I_Produit findByName(String nom);

	/** Met à jour la quantité d'un produit dans la base de données.
	 * @param nom : nom du produit à mettre à jour.
	 * @param qte : quantité à rajouter au stock. 
	 * @return Vrai si l'ajout a réussi, faux sinon. */
	public abstract boolean addQuantite(String nom, int qte);

	/** Met à jour la quantité d'un produit dans la base de données.
	 * @param nom : nom du produit à mettre à jour.
	 * @param qte : quantité à enlever du stock. 
	 * @return Vrai si le retrait a réussi, faux sinon. */
	public abstract boolean removeQuantite(String nom, int qte);

	/** Ferme la connexion à la base de données. */
	public abstract void disconnect();
}