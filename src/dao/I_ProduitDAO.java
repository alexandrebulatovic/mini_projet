package dao;

import java.util.List;

import metier.Produit;

public interface I_ProduitDAO {
	public abstract void create(Produit p);
	public abstract void delete(String nom);
	public abstract List<Produit> read();
}
