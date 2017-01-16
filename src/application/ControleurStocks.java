package application;

import dao.I_ProduitDAO;
import metier.*;

public class ControleurStocks {
	private Catalogue cat;
	
	I_ProduitDAO dao;
	
	public ControleurStocks(Catalogue cat, I_ProduitDAO dao){
		this.cat=cat;
		this.dao=dao;
		}
	public String getStocksCatalogue() {
		 return this.cat.toString();
	}
}
