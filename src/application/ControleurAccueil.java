package application;

import java.util.List;

import dao.I_CatalogueDAO;
import factory.MainFactory;
import metier.Catalogue;
import metier.I_Magasin;
import metier.I_Catalogue;
import metier.Magasin;
import presentation.FenetreAccueil;

public class ControleurAccueil {

	/** Le {@code Magasin} en cours d'utilisation. */
	private I_Magasin magasin;

	/** Le {@code CatalogueDAO} en cours d'utilisation. */
	private I_CatalogueDAO dao;

	public ControleurAccueil(){
		this.magasin= new Magasin();
		this.dao = MainFactory.createDAO(MainFactory.TYPE_SQL).createCatalogueDAO();
		this.remplirCatalogues();
		FenetreAccueil f = new FenetreAccueil(this);
		this.magasin.attacher(f);
	}

	/**
	 * Ajoute un catalogue au {@code Catalogue}.
	 * @param nom : nom du catalogue.
	 * @param prix : prix hors taxe du catalogue.
	 * @param qte : quantité en stock du catalogue.
	 */
	public void addCatalogue(String nom) 
	{
		Catalogue catalogue = new Catalogue(nom);
		if (this.magasin.addCatalogue(catalogue))
			this.dao.create(catalogue);
	}

	/**
	 * Supprime un catalogue du {@code Catalogue}.
	 * @param nom : nom du catalogue à supprimer.
	 */
	public void removeCatalogue(String nom)
	{
		if (this.magasin.removeCatalogue(nom))
			this.dao.delete(nom);
	}

	/**
	 * Remplit le catalogue à partir des données persistantes.
	 * @see Catalogue
	 */
	public void remplirCatalogues() {
		List<I_Catalogue> listeCatalogues = this.dao.findAll();
		this.magasin.addCatalogues(listeCatalogues);
	}

	/**
	 * Permet d'obtenir un tableau de tous les noms des catalogues
	 * du catalogue.
	 * @return un tableau de {@code String}.
	 */
	public String[] getNomCatalogue(){
		return this.magasin.getNomCatalogues();
	}

	public void selectCatalogue(String nom){
		new ControleurPrincipal(nom);
	}

	public I_Magasin getMagasin(){
		return this.magasin;
	}

	public String[] getDetailsCatalogue() {
		String[] details =  this.magasin.getDetailsCatalogues();
		for (int i=0; i < details.length; i++){
			details[i] = details[i] + " : " + this.dao.findNbProduits(details[i]) + " Produits";
		}
		return details;
	}

	public void disconnect() {
		this.dao.disconnect();
	}


}

