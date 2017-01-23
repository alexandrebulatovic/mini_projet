package metier;
import java.util.List;

/**
 * Cette interface fournit des méthodes permettant de manipuler un catalogue de produits.
 */

public interface I_Catalogue {

	/** On ajoute un produit au catalogue. 
	 @param produit : un objet implémentant {@code I_Produit} à ajouter 
	 @return Vrai si l'ajout a réussi, faux si le produit est incohérent ou existe déjà. */
	public abstract boolean addProduit(I_Produit produit);

	/**
	 * On ajoute un produit au catalogue.
	 * @param nom : nom du produit à ajouter.
	 * @param prix : prix du produit.
	 * @param qte : quantité en stock du produit.
	 * @return Vrai si l'ajout a réussi, faux si le produit est incohérent ou existe déjà.
	 */
	public abstract boolean addProduit(String nom, double prix, int qte);

	/** Ajoute une liste de produits au catalogue. 
	 * @param liste : une {@code List} de produits à ajouter.
	 * @return le nombre de produits effectivement ajoutés. */
	public abstract int addProduits(List<I_Produit> liste);

	/** Enlève le produit du catalogue. 
	 * @param nom : le nom du produit à enlever. 
	 * @return Vrai si le produit existe et a été enlevé, Faux sinon. */
	public abstract boolean removeProduit(String nom);

	/** Met à jour le stock d'un produit de notre catalogue.
	 * @param nomProduit : nom du produit à mettre à jour.
	 * @param qteAchetee : quantite à ajouter au stock. 
	 * @return Vrai si le produit appartient au stock et a été mis à jour, Faux sinon. */
	public abstract boolean acheterStock(String nomProduit, int qteAchetee);

	/** Met à jour le stock d'un produit de notre catalogue.
	 * @param nomProduit : nom du produit à mettre à jour.
	 * @param qteVendue : quantite à enlever du stock.
	 * @return Vrai si le produit appartient au stock et a été mis à jour, Faux sinon. */
	public abstract boolean vendreStock(String nomProduit, int qteVendue);

	/** @return un tableau de {@code String} trié par ordre alphabétique et
	 * dont chaque case contient le nom d'un produit du catalogue. */
	public abstract String[] getNomProduits();

	/** @return la valeur totale du stock de produits du catalogue. */
	public abstract double getMontantTotalTTC();

	/** Methode d'affichage dans le format demandé. */
	public abstract String toString();

	/** @return un objet {@code List} contenant tous les produits du catalogue. */
	public List<I_Produit> getListeProduits();

	/**	Efface tous les produits du catalogue. */
	public abstract void clear();

}