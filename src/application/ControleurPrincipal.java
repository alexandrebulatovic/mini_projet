package application;

import presentation.FenetrePrincipale;
import dao.DAOFactory;
import dao.I_ProduitDAO;
import metier.Catalogue;
import metier.I_Catalogue;

/**
 * Cette classe permet d'initialiser le programme.
 * <P>
 * Elle instancie la fenêtre principale, les sous-contrôleurs, le {@code Catalogue}
 * et le {@code DAO}.
 */

public class ControleurPrincipal {

	/* ATTRIBUTS */

	/** Le {@code Catalogue} en cours d'utilisation. */
	private I_Catalogue catalogue;

	/** Le {@code ProduitDAO} en cours d'utilisation. */
	private I_ProduitDAO dao;

	private ControleurCatalogue controleur_catalogue;
	private ControleurAchatVente controleur_achat_vente;
	private ControleurStocks controleur_stocks;
	/* METHODES */

	/** Lance la fenêtre principale et instancie les autres contrôleurs, le {@code DAO} et 
	 * un {@code Catalogue} commun à tous les contrôleurs.*/
	public ControleurPrincipal() 
	{
		this.catalogue = new Catalogue();
		this.dao = DAOFactory.getInstance().createDAO(DAOFactory.TYPE_SQL);

		this.controleur_catalogue = new ControleurCatalogue(this.catalogue, this.dao);
		this.controleur_stocks = new ControleurStocks(this.catalogue);
		this.controleur_achat_vente = new ControleurAchatVente(this.catalogue, this.dao);

		new FenetrePrincipale(this);
	}

	public ControleurCatalogue getControleurCatalogue() {
		return this.controleur_catalogue;
	}

	public ControleurStocks getControleurStocks() {
		return this.controleur_stocks;
	}

	public ControleurAchatVente getControleurAchatVente() {
		return this.controleur_achat_vente;
	}

	/** Ferme la connexion à la base de données. */
	public void disconnect() {
		this.dao.disconnect();
	}
}