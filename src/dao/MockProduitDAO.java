package dao;

import java.util.ArrayList;
import java.util.List;

import metier.I_Produit;
import metier.Produit;

public class MockProduitDAO implements I_ProduitDAO {

	@Override
	public boolean create(I_Produit p) {
		return true;
	}

	@Override
	public boolean delete(String nom) {
		return true;
	}

	@Override
	public List<I_Produit> findAll() {

		return null;

	}

	@Override
	public I_Produit findByName(String nom) {
		return null;
	}

	@Override
	public boolean addQuantite(String nom, int qte) {
		return true;
	}

	@Override
	public boolean removeQuantite(String nom, int qte) {
		return true;
	}

	@Override
	public void disconnect() {}

}
