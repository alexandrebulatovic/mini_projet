package dao;

import java.util.List;

import metier.I_Produit;
import metier.Produit;

public class AdaptateurXML implements I_ProduitDAO{

	private ProduitDAO_XML dao;

	public AdaptateurXML() {
		this.dao = new ProduitDAO_XML();
	}

	@Override
	public void create(I_Produit p) {
		this.dao.creer(p);

	}

	@Override
	public void delete(String nom) {
		I_Produit produit = new Produit(nom);
		this.dao.supprimer(produit);
	}

	@Override
	public List<I_Produit> read() {
		return this.dao.lireTous();
	}

	@Override
	public void updateQuantité(String nom, int value) {
		
	}

	@Override
	public void updateProduit(I_Produit p) {
		this.dao.maj(p);

	}




}
