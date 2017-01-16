package dao;

import java.util.List;

import metier.I_Produit;
import metier.Produit;

public interface I_ProduitDAO {
	public abstract void create(I_Produit p);
	public abstract void delete(String nom);
	public abstract List<I_Produit> read();
	public abstract void updateQuantité(String nom,int value);
	public abstract void updateProduit(I_Produit p);
}
