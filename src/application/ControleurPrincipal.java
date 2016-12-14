package application;
import metier.*;
import presentation.*;

public class ControleurPrincipal {


	private Catalogue catalogue;
	private controleurProduits controller_produits;
	private ControleurAchatsVentes controller_av;
	private controleurStocks controller_stocks;


	/** Constructeur principal du programme avec un catalogue commun et les contrôleurs associés. */
	public ControleurPrincipal() {
		this.catalogue = new Catalogue();
		this.controller_produits = new controleurProduits(this.catalogue);
		this.controller_av = new ControleurAchatsVentes(this.catalogue, "Achat"); // rajouter "Achat" ou "Vente" en parametre
		this.controller_stocks = new controleurStocks(this.catalogue);;
	}





	public static void main(String[] args) {
		new ControleurPrincipal();
		new FenetrePrincipale();
	}

}
