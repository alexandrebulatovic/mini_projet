package application;

import dao.I_ProduitDAO;
import metier.*;

/**
 * Cette classe permet d'afficher le contenu du catalogue.
 */

public class ControleurStocks {

	private I_Catalogue catalogue;

	public ControleurStocks(I_Catalogue cat, I_ProduitDAO dao){
		this.catalogue=cat;
	}

	/**
	 * Permet d'afficher le contenu du catalogue.
	 * @return un {@code String} représentant le catalogue.
	 */
	public String getStocksCatalogue() {
		return this.catalogue.toString();
	}
}
