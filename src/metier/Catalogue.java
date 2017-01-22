package metier;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Conteneur pour les {@code Produit}.
 * <P>
 * Contient des méthodes de manipulation des données de chaque {@code Produit}.
 * @see I_Produit
 */
public class Catalogue implements I_Catalogue {

	private List<I_Produit> lesProduits;

	public Catalogue() {
		this.lesProduits = new ArrayList<I_Produit>();
	}


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


	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		Produit produit = new Produit(nom, prix, qte);
		return this.addProduit(produit);
	}

	/** Methode personnelle pour simplifier les tests sur l'existence des produits.
	 * @param produit : le produit à comparer avec la liste
	 * @return Vrai si un produit existe déjà, Faux sinon
	 */
	public boolean existe(I_Produit produit ) {
		for (int i=0; i < lesProduits.size(); i++){
			if (lesProduits.get(i).getNom().equals(produit.getNom())){
				return true;
			}
		}
		return false;
	}

	/** Methode personnelle pour vérifier les paramètres d'un produit.
	 * @param produit : le produit à vérifier
	 * @return Vrai si un produit est conforme
	 */
	public boolean check(I_Produit produit){
		if(produit == null || produit.getPrixUnitaireHT()<=0 || produit.getQuantite() < 0){
			return false;
		}else{
			return true;
		}
	}

	/** Methode pour arrondir à deux chiffres apres la virgule.
	 * @param valeur : valeur qu'on veut arrondir. 
	 * @return double : Valeur arrondie */
	public static double arrondir(double valeur) {
		BigDecimal bd = new BigDecimal(valeur);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	/** Methode pour formater les nombres avec deux chiffres apres la virgule.
	 * @param valeur : valeur qu'on veut arrondir. 
	 * @return string : valeur arrondie */
	public static String formater(double valeur) {
		DecimalFormat df = new DecimalFormat ( ) ; 
		df.setMaximumFractionDigits ( 2 ) ;
		df.setMinimumFractionDigits ( 2 ) ; 
		df.setDecimalSeparatorAlwaysShown ( true ) ; 
		return df.format(valeur);
	}

	/** Ajoute le produit s'il n'est pas déjà  présent dans notre liste. 
	 * @param l : Liste d'objets de type Produit à ajouter.
	 * @return Le nombre de produits effectivement ajouté. */
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

	/** Enlève le produit du catalogue. 
	 * @param nom : le nom du produit à enlever 
	 * @return Vrai si le produit existe et a été supprimé, Faux sinon. */
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

	/** Met à jour le stock d'un produit de notre catalogue.
	 * @param nomProduit : nom du produit à mettre à jour.
	 * @param qteAchetee : quantite à ajouter au stock. 
	 * @return Vrai si l'objet appartient au stock et a été mis à jour, Faux sinon. */
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

	/** Met à jour le stock d'un produit de notre catalogue.
	 * @param nomProduit : nom du produit à mettre à jour.
	 * @param qteVendue : quantite à enlever du stock.
	 * @return Vrai si l'objet appartient au stock et a été mis à jour, Faux sinon. */
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

	/** @return un tableau dont chaque case contient le nom d'un élément du catalogue. */
	@Override
	public String[] getNomProduits() {
		int taille = this.lesProduits.size();
		String[] tab = new String[taille];
		for (int i = 0; i < taille; i++){
			tab[i] = this.lesProduits.get(i).getNom();
		}
		Arrays.sort(tab);
		return tab;
	}

	/** Methode d'affichage dans le format demandé. */
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

	/** @return la valeur totale des produits du catalogue en stock (en prenant en compte la quantité). */
	public double getMontantTotalTTC() {
		double somme = 0;
		for (int i = 0; i < this.lesProduits.size(); i ++){
			somme+= this.lesProduits.get(i).getPrixStockTTC();
		}
		return Catalogue.arrondir(somme);
	}

	/**	Efface tous les objets du catalogue. */
	@Override
	public void clear() {
		this.lesProduits.clear();
	}

	/** @return la Liste des produits du catalogue. */
	public List<I_Produit> getLesProduits() {
		return lesProduits;
	}

}