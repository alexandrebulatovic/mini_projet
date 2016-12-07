package metier;

import java.util.List;

public class Catalogue implements I_Catalogue {

	private List<Produit> lesProduits;

	public Catalogue() {
		this.lesProduits = null;
	}

	/** On ajoute un objet produit au catalogue s'il n'existe pas déjà. 
	 @param produit : un produit à ajouter 
	 @return Vrai si l'ajout à reussi. */
	@Override
	public boolean addProduit(I_Produit produit) {
		if (this.existe(produit)){
			throw new SecurityException("Produit existe déjà.");
		}
		else {
			this.lesProduits.add(new Produit(produit.getNom(), produit.getPrixUnitaireHT(), produit.getQuantite()));
			return true;
		}
	}

	/** Ré-utilise la methode addProduit(Produit) pour ajouter un produit qu'on créé. */
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
			if (lesProduits.get(i).getNom() == produit.getNom()){
				return true;
			}
		}
		return false;
	}

	/** Ajoute le produit s'il n'est pas déjà présent dans notre liste. 
	 * @param l : Liste d'objets de type Produit à ajouter.
	 * @return Le nombre de produits effectivement ajouté. */
	public int addProduits(List<I_Produit> l) {
		int existants = 0;
		for (int i=0; i < l.size() ; i++){
			if (!existe(l.get(i))){
				existants++;
				this.addProduit(l.get(i));
			}
		}
		return existants;
	}

	/** Enlève le produit du catalogue. 
	 * @param nom : le nom du produit à enlever 
	 * @return Vrai si le produit existe et a été supprimé, Faux sinon. */
	@Override
	public boolean removeProduit(String nom) {
		for (int i=0; i < this.lesProduits.size(); i++){
			if (this.lesProduits.get(i).getNom() == nom){
				this.lesProduits.remove(i);
				return true;
			}
		}
		return false;
	}

	/** Met à jour le stock d'un produit de notre catalogue.
	 * @param nomProduit : nom du produit à mettre à jour.
	 * @param qteAchetee : quantite à ajouter au stock. 
	 * @return Vrai si l'objet appartient au stock et a été mis à jour, Faux sinon. */
	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		for (int i=0; i < this.lesProduits.size(); i++){
			if (this.lesProduits.get(i).getNom() == nomProduit){
				this.lesProduits.get(i).ajouter(qteAchetee);
				return true;
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
				this.lesProduits.get(i).enlever(qteVendue);
				return true;
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
		stringBuilder.append("Montant total TTC du stock : " + this.getMontantTotalTTC() + "€");
		return stringBuilder.toString();
	}

	/** @return la valeur totale des produits du catalogue en stock (en prenant en compte la quantité). */
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

}