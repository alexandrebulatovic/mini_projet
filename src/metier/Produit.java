package metier;

public class Produit implements I_Produit {

	private int quantiteStock;
	private String nom;
	private double prixUnitaireHT;
	private static final double tauxTVA = 0.2;

	/** Constructeur du Produit.
	 * @param nom : nom du produit
	 * @param prixUnitaireHT : prix HT du produit
	 * @param quantiteStock : quantite en stock du produit
	 */
	public Produit(String nom, double prixUnitaireHT, int quantiteStock) {
		this.quantiteStock = quantiteStock;
		this.nom = formaterNom(nom);
		this.prixUnitaireHT = prixUnitaireHT;
	}

	@Override
	public boolean ajouter(int qteAchetee) {
		if (qteAchetee <= 0){
			return false;
		} else {		
			this.quantiteStock+=qteAchetee;
			return true;
		}
	}

	@Override
	public boolean enlever(int qteVendue) {
		if (qteVendue > this.quantiteStock || qteVendue <= 0){
			return false;
		} else {		
			this.quantiteStock-=qteVendue;
			return true;
		}
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public int getQuantite() {
		return this.quantiteStock;
	}

	@Override
	public double getPrixUnitaireHT() {
		return this.prixUnitaireHT;
	}

	@Override
	public double getPrixUnitaireTTC() {
		return this.prixUnitaireHT+(this.prixUnitaireHT*tauxTVA);
	}

	@Override
	public double getPrixStockTTC() {
		return (this.getPrixUnitaireTTC()*this.quantiteStock);
	}

	@Override
	public String toString() 
	{
		return nom + " - prix HT : " + Catalogue.formater(this.getPrixUnitaireHT()) + 
				" € - prix TTC : " + Catalogue.formater(this.getPrixUnitaireTTC()) + " €"+
				" - quantité en stock : "+ this.getQuantite();
	}

	/**
	 *  Méthode qui permet de mettre le nom sous la bonne forme en
	 *  supprimant les espaces et les tabulations.
	 *  @param name : la chaîne de caractères à convertir.
	 *  @return le nom au format demandé.
	 **/
	public String formaterNom(String name){
		name = name.replaceAll("\t"," ");
		name = name.trim();
		return name;
	}
}
