package metier;
import java.util.ArrayList;
import java.util.List;

public interface I_Catalogue {

	/** On ajoute un objet produit au catalogue s'il n'existe pas déjà. 
	 @param produit : un produit à ajouter 
	 @return Vrai si l'ajout à reussi. */
	public abstract boolean addProduit(I_Produit produit);

	/** Ré-utilise la methode addProduit(Produit) pour ajouter un produit qu'on créé. */
	public abstract boolean addProduit(String nom, double prix, int qte);


	public abstract int addProduits(List<I_Produit> l);
	public abstract boolean removeProduit(String nom);
	public abstract boolean acheterStock(String nomProduit, int qteAchetee);
	public abstract boolean vendreStock(String nomProduit, int qteVendue);
	public abstract String[] getNomProduits();
	public abstract double getMontantTotalTTC();
	public abstract String toString();
	public List<I_Produit> getLesProduits();
	public abstract void clear();

}