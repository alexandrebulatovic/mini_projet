package application;
import metier.*;
import presentation.*;

public class ControleurPrincipal {


	private Catalogue catalogue;
	private ControleurCatalogue controller_catalogue;
	private ControleurStocks controller_stocks;


	/** Constructeur principal du programme avec un catalogue commun et les contrôleurs associés. */
	public ControleurPrincipal() {
		this.catalogue = new Catalogue();
		this.controller_catalogue = new ControleurCatalogue(this.catalogue);
		this.controller_stocks = new ControleurStocks(this.catalogue);
	}

	public ControleurCatalogue getControleurCatalogue() {
		return this.controller_catalogue;
	}

	public ControleurStocks getControleurStocks() {
		return this.controller_stocks;
	}

	public static void main(String[] args) {
		ControleurPrincipal c = new ControleurPrincipal();
		FenetrePrincipale f = new FenetrePrincipale();
	}
}
