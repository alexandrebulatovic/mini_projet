package metier;
public interface I_Produit {

	/** On met à jour le stock d'un produit (ajout).
	 * @param qteAchetee quantité à rajouter. */
	public abstract boolean ajouter(int qteAchetee);

	/** On met à jour le stock d'un produit (retrait).
	 * @param qteVendue quantité à enlever. */
	public abstract boolean enlever(int qteVendue);

	/** Accesseur du nom.  */
	public abstract String getNom();

	/** Accesseur de la quantité en stock du produit.  */
	public abstract int getQuantite();

	/** Accesseur du prix hors taxe.  */
	public abstract double getPrixUnitaireHT();

	/** Accesseur du prix toutes taxes comprises.  */
	public abstract double getPrixUnitaireTTC();

	/** Accesseur de la valeur TTC du stock du produit.  */
	public abstract double getPrixStockTTC();

	/** Méthode d'affichage respectant le format demandé.  */
	public abstract String toString();

}