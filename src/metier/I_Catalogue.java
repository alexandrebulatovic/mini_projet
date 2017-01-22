package metier;
import java.util.List;

/**
 * Cette interface fournit des méthodes permettant de manipuler un catalogue de produits.
 */

public interface I_Catalogue {

	/** On ajoute un objet produit au catalogue s'il n'existe pas déjà. 
	 @param produit : un produit à ajouter 
	 @return Vrai si l'ajout à reussi. */
	public abstract boolean addProduit(I_Produit produit);

	/** Ré-utilise la methode addProduit(Produit) pour ajouter un produit qu'on créé. */
	public abstract boolean addProduit(String nom, double prix, int qte);

	/** Ajoute le produit s'il n'est pas déjà  présent dans notre liste. 
	 * @param l : Liste d'objets de type Produit à ajouter.
	 * @return Le nombre de produits effectivement ajouté. */
	public abstract int addProduits(List<I_Produit> l);

	/** Enlève le produit du catalogue. 
	 * @param nom : le nom du produit à enlever 
	 * @return Vrai si le produit existe et a été supprimé, Faux sinon. */
	public abstract boolean removeProduit(String nom);

	/** Met à jour le stock d'un produit de notre catalogue.
	 * @param nomProduit : nom du produit à mettre à jour.
	 * @param qteAchetee : quantite à ajouter au stock. 
	 * @return Vrai si l'objet appartient au stock et a été mis à jour, Faux sinon. */
	public abstract boolean acheterStock(String nomProduit, int qteAchetee);

	/** Met à jour le stock d'un produit de notre catalogue.
	 * @param nomProduit : nom du produit à mettre à jour.
	 * @param qteVendue : quantite à enlever du stock.
	 * @return Vrai si l'objet appartient au stock et a été mis à jour, Faux sinon. */
	public abstract boolean vendreStock(String nomProduit, int qteVendue);

	/** @return un tableau dont chaque case contient le nom d'un élément du catalogue. */
	public abstract String[] getNomProduits();

	/** @return la valeur totale des produits du catalogue en stock (en prenant en compte la quantité). */
	public abstract double getMontantTotalTTC();

	/** Methode d'affichage dans le format demandé. */
	public abstract String toString();

	/** @return un objet {@code List} contenant tous les produits du catalogue. */
	public List<I_Produit> getLesProduits();

	/**	Efface tous les objets du catalogue. */
	public abstract void clear();

}