package mini_projet.metier;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Catalogue implements I_Catalogue {

	private ArrayList<I_Produit> lesProduits;

	public Catalogue() {
		this.lesProduits = new ArrayList<I_Produit>();
	}

	/** On ajoute un objet produit au catalogue s'il n'existe pas dÃ©jÃ . 
	 @param produit : un produit Ã  ajouter 
	 @return Vrai si l'ajout Ã  reussi. */
	@Override
	public boolean addProduit(I_Produit produit) {
		if (this.existe(produit) || !(this.check(produit))){
			return false;
		}
		else {
			this.lesProduits.add(new Produit(produit.getNom(), produit.getPrixUnitaireHT(), produit.getQuantite()));
			return true;
		}
	}

	/** RÃ©-utilise la methode addProduit(Produit) pour ajouter un produit qu'on crÃ©Ã©. */
	public boolean addProduit(String nom, double prix, int qte) {
		Produit produit = new Produit(nom, prix, qte);
		return this.addProduit(produit);
	}

	/** Methode personnelle pour simplifier les tests sur l'existence des produits.
	 * @param produit : le produit Ã  comparer avec la liste
	 * @return Vrai si un produit existe dÃ©jÃ , Faux sinon
	 */
	public boolean existe(I_Produit produit ) {
		for (int i=0; i < lesProduits.size(); i++){
			if (lesProduits.get(i).getNom().equals(produit.getNom())){
				return true;
			}
		}
		return false;
	}
	
	public boolean check(I_Produit produit){
		if(produit == null || produit.getPrixUnitaireHT()<=0 || produit.getQuantite() < 0){
			return false;
		}else{
			return true;
		}
		
	}

	/** Methode pour arrondir Ã  deux chiffres apres la virgule.
	 * @param valeur : valeur qu'on veut arrondir. */
	public static double arrondir(double valeur) {
		BigDecimal bd = new BigDecimal(valeur);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public static String formater(double valeur) {
		DecimalFormat df = new DecimalFormat ( ) ; 
		df.setMaximumFractionDigits ( 2 ) ;
		df.setMinimumFractionDigits ( 2 ) ; 
		df.setDecimalSeparatorAlwaysShown ( true ) ; 
		return df.format (valeur);
	}
	
	/** Ajoute le produit s'il n'est pas dÃ©jÃ  prÃ©sent dans notre liste. 
	 * @param l : Liste d'objets de type Produit Ã  ajouter.
	 * @return Le nombre de produits effectivement ajoutÃ©. */
	public int addProduits(List<I_Produit> l) {
		if (l == null){
			return 0;
		} else {
			int existants = 0;
			for (int i=0; i < l.size() ; i++){
				if (!existe(l.get(i)) ){
					if (this.addProduit(l.get(i))){
						existants++;
					}
				}
			}
			return existants;	
		}
	}

	/** EnlÃ¨ve le produit du catalogue. 
	 * @param nom : le nom du produit Ã  enlever 
	 * @return Vrai si le produit existe et a Ã©tÃ© supprimÃ©, Faux sinon. */
	@Override
	public boolean removeProduit(String nom) {
		if (nom == null){
			return false;
		} else {
			for (int i=0; i < this.lesProduits.size(); i++){
				if (this.lesProduits.get(i).getNom() == nom){
					this.lesProduits.remove(i);
					return true;
				}
			}
			return false;
		}
	}

	/** Met Ã  jour le stock d'un produit de notre catalogue.
	 * @param nomProduit : nom du produit Ã  mettre Ã  jour.
	 * @param qteAchetee : quantite Ã  ajouter au stock. 
	 * @return Vrai si l'objet appartient au stock et a Ã©tÃ© mis Ã  jour, Faux sinon. */
	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		for (int i=0; i < this.lesProduits.size(); i++){
			if (this.lesProduits.get(i).getNom() == nomProduit){
				if (this.lesProduits.get(i).ajouter(qteAchetee)){
					return true;
				}
			}
		}
		return false;
	}

	/** Met Ã  jour le stock d'un produit de notre catalogue.
	 * @param nomProduit : nom du produit Ã  mettre Ã  jour.
	 * @param qteVendue : quantite Ã  enlever du stock.
	 * @return Vrai si l'objet appartient au stock et a Ã©tÃ© mis Ã  jour, Faux sinon. */
	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		for (int i=0; i < this.lesProduits.size(); i++){
			if (this.lesProduits.get(i).getNom() == nomProduit){
				if(this.lesProduits.get(i).enlever(qteVendue)){
					return true;
				}
			}
		}
		return false;
	}

	/** @return un tableau dont chaque case contient le nom d'un Ã©lÃ©ment du catalogue. */
	@Override
	public String[] getNomProduits() {
		int taille = this.lesProduits.size();
		String[] tab = new String[taille];
		for (int i = 0; i < taille; i++){
			tab[i] = this.lesProduits.get(i).getNom();
		}
		return tab;
	}

	/** Methode d'affichage dans le format demandÃ©. */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i=0; i < this.lesProduits.size(); i++){
			stringBuilder.append(this.lesProduits.get(i).toString());
			stringBuilder.append("\n");
		}
		stringBuilder.append("\n");
		stringBuilder.append("Montant total TTC du stock : " + Catalogue.formater(this.getMontantTotalTTC()) + " €");
		return stringBuilder.toString();
	}

	/** @return la valeur totale des produits du catalogue en stock (en prenant en compte la quantitÃ©). */
	public double getMontantTotalTTC() {
		double somme = 0;
		for (int i = 0; i < this.lesProduits.size(); i ++){
			somme+= this.lesProduits.get(i).getPrixStockTTC();
		}
		return somme;
	}

	/**	Efface tous les objets du catalogue. */
	@Override
	public void clear() {
		this.lesProduits.clear();
	}

	public ArrayList<I_Produit> getLesProduits() {
		return lesProduits;
	}

}