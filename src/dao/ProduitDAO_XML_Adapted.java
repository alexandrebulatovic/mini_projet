package dao;

import java.util.List;

import metier.I_Produit;
import metier.Produit;

/**
 * Adaptation de la classe fournie {@link ProduitDAO_XML} pour 
 * permettre son utilisation avec le programme actuel.
 */
public class ProduitDAO_XML_Adapted implements I_ProduitDAO{

	/** Classe originale avec les méthodes à adapter. */
	private ProduitDAO_XML xml_dao;

	public ProduitDAO_XML_Adapted() {
		this.xml_dao = new ProduitDAO_XML();
	}

	@Override
	public void create(I_Produit p) {

		String nom_produit = p.getNom();

		if (this.xml_dao.lire(nom_produit) == null)
			this.xml_dao.creer(p);
	}

	@Override
	public void delete(String nom) {
		I_Produit produit = new Produit(nom);
		this.xml_dao.supprimer(produit);
	}

	@Override
	public List<I_Produit> findAll() {
		return this.xml_dao.lireTous();
	}

	@Override
	public void update(I_Produit p) {
		this.xml_dao.maj(p);

	}

	@Override
	public I_Produit findByName(String nom) {
		return this.xml_dao.lire(nom);
	}

	@Override
	public void disconnect() {}


}