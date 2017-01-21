package application;
import dao.FactoryDAO;
import dao.I_ProduitDAO;
import dao.ProduitDAO_SQL;
import metier.*;
import presentation.*;

public class ControleurPrincipal {


	private Catalogue catalogue;

	private ControleurCatalogue controleur_catalogue;
	private ControleurStocks controleur_stock;
	private ControleurAchatVente controleur_achats_ventes;

	private I_ProduitDAO dao;

	private FactoryDAO factory;


	/** Constructeur principal du programme avec un catalogue commun et les contrôleurs associés. */
	public ControleurPrincipal() {
		this.catalogue = new Catalogue();
		this.factory = new FactoryDAO();
		this.dao = factory.createDAO();
		this.controleur_catalogue = new ControleurCatalogue(this.catalogue, this.dao);
		this.controleur_stock = new ControleurStocks(this.catalogue, this.dao);
		this.controleur_achats_ventes = new ControleurAchatVente(this.catalogue, this.dao);
	}

	public ControleurCatalogue getControleurCatalogue() {
		return this.controleur_catalogue;
	}

	public ControleurStocks getControleurStock() {
		return this.controleur_stock;
	}

	public ControleurAchatVente getControleurAchatsVentes() {
		return this.controleur_achats_ventes;
	}


	public static void main(String[] args) {
		ControleurPrincipal c = new ControleurPrincipal();
		FenetrePrincipale f = new FenetrePrincipale();
	}

	public void disconnect() {
		if (this.dao instanceof ProduitDAO_SQL)
			((ProduitDAO_SQL)this.dao).disconnect();
	}

}
