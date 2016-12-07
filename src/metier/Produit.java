package metier;

public class Produit implements I_Produit {

	private int quantiteStock;
	private String nom;
	private double prixUnitaireHT;
	private static final double tauxTVA = 0.2;

	/** Constructeur du Produit, on verifie que la quantite n'est pas negative.
	 * @param nom : nom du produit
	 * @param prixUnitaireHT : prix HT du produit
	 * @param quantiteStock : quantite en stock du produit
	 */
	public Produit(String nom, double prixUnitaireHT, int quantiteStock) {
			this.quantiteStock = quantiteStock;
			this.nom = nom;
			this.prixUnitaireHT = prixUnitaireHT;
		}

	/** On met à jour le stock d'un produit (ajout) */
	@Override
	public boolean ajouter(int qteAchetee) {
		if (qteAchetee < 0){
			return false;
		} else {		
			this.quantiteStock+=qteAchetee;
			return true;
		}
	}

	/** On met à jour le stock d'un produit (retrait) */
	@Override
	public boolean enlever(int qteVendue) {
		if (qteVendue > this.quantiteStock || qteVendue == 0){
			return false;
		} else {		
			this.quantiteStock-=qteVendue;
			return true;
		}

	}
	
	/** Accesseur du nom.  */
	@Override
	public String getNom() {
		return this.nom;
	}

	/** Accesseur de la quantite.  */
	@Override
	public int getQuantite() {
		return this.quantiteStock;
	}

	/** Accesseur du prix HT.  */
	@Override
	public double getPrixUnitaireHT() {
		return this.prixUnitaireHT;
	}

	/** Accesseur du prix TTC.  */
	@Override
	public double getPrixUnitaireTTC() {
		return this.prixUnitaireHT+(this.prixUnitaireHT*tauxTVA);
	}

	/** Accesseur de la valeur TTC du stock du produit.  */
	@Override
	public double getPrixStockTTC() {
		return (this.getPrixUnitaireTTC()*this.quantiteStock);
	}

	/** Methode d'affichage respectant le format demandé.  */
	@Override
	public String toString() {
		return nom + "   prix HT : " + this.getPrixUnitaireHT() + "€ prix TTC : " + this.getPrixUnitaireTTC() + "€"+"  quantité en stock : "+ this.getQuantite();
	}

}
