package application;

import metier.*;

public class ControleurStocks {
	private Catalogue cat;
	public ControleurStocks(Catalogue cat){
		this.cat=cat;
		}
	public String getStocksCatalogue() {
		 return this.cat.toString();
	}
}
