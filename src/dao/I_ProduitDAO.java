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
	public abstract void create(I_Produit p);

	/** Supprime un produit de la base de données.
	 * @param nom : nom du produit à supprimer. */
	public abstract void delete(String nom);

	/** Génère une liste de tous les produits stockés dans la base de données.
	 * @return un objet {@code List} contenant tous les produits. */
	public abstract List<I_Produit> findAll();

	/** Génère le produit demandé parmi ceux stockés dans la base de données.
	 * @param nom : nom du produit à générer.
	 * @return un objet {@code I_Produit} correspondant à un produit existant. */
	public abstract I_Produit findByName(String nom);

	/** Met à jour un produit de la base de données..
	 * @param p : {@code Produit} avec les informations à jour. */
	public abstract void update(I_Produit p);

	/** Ferme la connexion à la base de données. */
	public abstract void disconnect();
}
