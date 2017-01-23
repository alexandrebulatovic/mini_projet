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
 * Cette classe possède aussi des méthodes pour manipuler les données des {@code Produit}.
 * @see I_Produit
 * @see I_Catalogue
 */
public class Catalogue implements I_Catalogue {

	/* ATTRIBUTS */

	private List<I_Produit> lesProduits;

	/* METHODES */

	public Catalogue() {
		this.lesProduits = new ArrayList<I_Produit>();
	}

	@Override
	public boolean addProduit(I_Produit produit) {
		if ( this.existe(produit) || !(this.check(produit)) )
		{
			return false;
		}
		else {

			this.lesProduits.add(produit);
			return true;
		}
	}


	@Override
	public boolean addProduit(String nom, double prixHT, int qte) {

		I_Produit produit = new Produit(nom, prixHT, qte);

		return this.addProduit(produit);
	}

	@Override
	public int addProduits(List<I_Produit> liste) 
	{
		int produits_rajoutes = 0;

		if (liste != null) {

			for (int i=0; i < liste.size() ; i++)
			{
				if (this.addProduit(liste.get(i)))
					produits_rajoutes++;
			}
		}
		return produits_rajoutes;
	}

	@Override
	public boolean removeProduit(String nom) {

		boolean trouve = false;

		if (nom != null)
		{
			for (int i=0; i < this.lesProduits.size() && !trouve ; i++)
			{
				if (this.lesProduits.get(i).getNom().equals(nom)){
					this.lesProduits.remove(i);
					trouve = true;
				}
			}
		}

		return trouve;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) 
	{
		I_Produit produit = getProduit(nomProduit);

		if ( produit != null ) 
		{
			if(getProduit(nomProduit).ajouter(qteAchetee))
				return true;
			else
				return false;
		}
		else 
			return false;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) 
	{
		I_Produit produit = getProduit(nomProduit);

		if ( produit != null ) 
		{
			if(getProduit(nomProduit).enlever(qteVendue))
				return true;
			else
				return false;
		}
		else 
			return false;
	}

	/**
	 * Recherche le produit dans le catalogue.
	 * @param nomProduit : nom du produit à rechercher.
	 * @return le {@code I_Produit} recherché s'il existe, {@code null} sinon.
	 */
	private I_Produit getProduit(String nomProduit) {

		for (int i=0; i < this.lesProduits.size(); i++)
		{
			if (this.lesProduits.get(i).getNom().equals(nomProduit))
				return this.lesProduits.get(i);
		}

		return null;
	}

	@Override
	public String[] getNomProduits() {

		int taille = this.lesProduits.size();

		String[] tabNomProduits = new String[taille];

		for (int i = 0; i < taille; i++){
			tabNomProduits[i] = this.lesProduits.get(i).getNom();
		}
		Arrays.sort(tabNomProduits); // tri du tableau

		return tabNomProduits;
	}

	@Override
	public double getMontantTotalTTC() {

		double somme = 0;

		for (int i = 0; i < this.lesProduits.size(); i ++){
			somme+= this.lesProduits.get(i).getPrixStockTTC();
		}

		return Catalogue.arrondir(somme);
	}

	@Override
	public List<I_Produit> getListeProduits() {
		return lesProduits;
	}

	@Override
	public String toString() {

		StringBuilder stringBuilder = new StringBuilder();

		for (int i=0; i < this.lesProduits.size(); i++){
			stringBuilder.append(this.lesProduits.get(i).toString());
			stringBuilder.append("\n");
		}

		stringBuilder.append("\n");
		stringBuilder.append("Montant total TTC du stock : " + 
				Catalogue.formater(this.getMontantTotalTTC()) + " €");

		return stringBuilder.toString();
	}

	/** Vérifie si le produit existe déjà dans le catalogue.
	 * @param produit : le produit à vérifier.
	 * @return Vrai si un produit existe déjà, Faux sinon.
	 */
	private boolean existe(I_Produit produit ) {

		for (int i=0; i < lesProduits.size(); i++){

			if (lesProduits.get(i).getNom().equals(produit.getNom())){
				return true;
			}
		}
		return false;
	}

	/** Vérifie la cohérence d'un produit.
	 * @param produit : le produit à vérifier.
	 * @return Vrai si un produit est conforme, 
	 * faux s'il est {@code null} ou que ses données sont incohérentes.
	 */
	private boolean check(I_Produit produit){
		if (produit == null || produit.getPrixUnitaireHT()<=0 || produit.getQuantite() < 0){
			return false;
		}else{
			return true;
		}
	}

	/** Arrondie une valeur à deux chiffres après la virgule.
	 * @param valeur : valeur à arrondir. 
	 * @return la valeur arrondie.
	 */
	public static double arrondir(double valeur) {
		BigDecimal bd = new BigDecimal(valeur);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	/** Formate un nombre à virgules dans l'affichage demandé.
	 * @param valeur : {@code Double} à formater. 
	 * @return un {@code String} au bon format. */
	public static String formater(double valeur) {
		DecimalFormat df = new DecimalFormat ( ) ; 
		df.setMaximumFractionDigits ( 2 ) ;
		df.setMinimumFractionDigits ( 2 ) ; 
		df.setDecimalSeparatorAlwaysShown ( true ) ; 
		return df.format(valeur);
	}

	@Override
	public void clear() {
		this.lesProduits.clear();
	}
}