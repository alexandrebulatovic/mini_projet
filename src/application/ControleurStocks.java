package application;

import dao.I_ProduitDAO;
import metier.*;

/**
 * Correspond au scenario "Connaitre l'etat des stocks".
 */
public class ControleurStocks {
	private I_Catalogue catalogue;

	I_ProduitDAO dao;

	public ControleurStocks(I_Catalogue cat, I_ProduitDAO dao){
		this.catalogue=cat;
		this.dao=dao;
	}
	public String getStocksCatalogue() {
		return this.catalogue.toString();
	}
}
