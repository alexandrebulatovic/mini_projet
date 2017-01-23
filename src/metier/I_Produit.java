package metier;

/**
 * Cette interface fournit des méthodes permettant d'accéder aux
 * données d'un produit et de les modifier si nécessaire.
 */

public interface I_Produit {

	/** On met à jour le stock d'un produit.
	 * @param qteAchetee quantité à rajouter au stock actuel. */
	public abstract boolean ajouter(int qteAchetee);

	/** On met à jour le stock d'un produit.
	 * @param qteVendue quantité à enlever du stock actuel. */
	public abstract boolean enlever(int qteVendue);

	/** @return le nom du produit. */
	public abstract String getNom();

	/** @return la quantité en stock du produit. */
	public abstract int getQuantite();

	/** @return le prix hors taxe du produit. */
	public abstract double getPrixUnitaireHT();

	/** @return le prix toutes taxes comprises du produit. */
	public abstract double getPrixUnitaireTTC();

	/** @return la valeur toutes taxes comprises du stock du produit. */
	public abstract double getPrixStockTTC();

	/** Méthode d'affichage respectant le format demandé.  */
	public abstract String toString();

}