package application;

import dao.I_ProduitDAO;
import metier.I_Catalogue;

/**
 * Cette classe se charge de modifier les stocks des produits.
 * 
 * @see #acheterStock(String, int)
 * @see #vendreStock(String, int)
 */

public class ControleurAchatVente {

	/* ATTRIBUTS */

	private I_Catalogue catalogue;

	private I_ProduitDAO dao;

	/* METHODES */

	public ControleurAchatVente(I_Catalogue catalogue, I_ProduitDAO dao){
		this.catalogue = catalogue;
		this.dao = dao;
	}

	/**
	 * Met à jour le stock d'un produit.
	 * @param nom : nom du produit à mettre à jour.
	 * @param qteAchetee : quantité à rajouter au stock.
	 * @return Vrai si la mise à jour du stock a réussi, faux sinon.
	 */
	public boolean acheterStock(String nom, int qteAchetee){

		if (this.catalogue.acheterStock(nom, qteAchetee) && this.dao.addQuantite(nom, qteAchetee))
			return true;
		else
			return false;
	}

	/**
	 * Met à jour le stock d'un produit.
	 * @param nom : nom du produit à mettre à jour.
	 * @param qteVendue : quantité à enlever du stock.
	 * @return Vrai si la mise à jour du stock a réussi, faux sinon.
	 */
	public boolean vendreStock(String nom, int qteVendue){

		if (this.catalogue.vendreStock(nom, qteVendue) && this.dao.removeQuantite(nom, qteVendue))
			return true;
		else
			return false;
	}
}