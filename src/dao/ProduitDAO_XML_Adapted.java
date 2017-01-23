package dao;

import java.util.List;

import metier.I_Produit;
import metier.Produit;

/**
 * Adaptation de la classe fournie {@link ProduitDAO_XML} pour 
 * permettre son utilisation avec le programme actuel.
 */
public class ProduitDAO_XML_Adapted implements I_ProduitDAO{

	/** Correspond à l'opérateur de soustraction. */
	private static final char RETRAIT = '-';

	/** Correspond à l'opérateur d'addition.*/
	private static final char AJOUT = '+';

	/** Classe originale avec les méthodes à adapter. */
	private ProduitDAO_XML xml_dao;

	public ProduitDAO_XML_Adapted() {
		this.xml_dao = new ProduitDAO_XML();
	}

	@Override
	public boolean create(I_Produit p) {
		return this.xml_dao.creer(p);
	}

	@Override
	public boolean delete(String nom) {
		I_Produit produit = xml_dao.lire(nom);

		return this.xml_dao.supprimer(produit);
	}

	@Override
	public List<I_Produit> findAll() {
		return this.xml_dao.lireTous();
	}

	@Override
	public boolean addQuantite(String nom, int qte) {
		return majQuantiteProduit(nom, qte, ProduitDAO_XML_Adapted.AJOUT);
	}

	@Override
	public boolean removeQuantite(String nom, int qte) {
		return majQuantiteProduit(nom, qte, ProduitDAO_XML_Adapted.RETRAIT);
	}

	/**
	 * Met à jour la quantité d'un produit.
	 * @param nom : nom du produit à mettre à jour.
	 * @param qte : valeur à ajouter ou à soustraire à la quantité réelle.
	 * @param operation : opération à effectuer parmi {@code ProduitDAO_XML_Adapted.AJOUT} ou 
	 * {@code ProduitDAO_XML_Adapted.RETRAIT}.
	 * @return Vrai si la mise à jour a réussi, faux sinon.
	 */
	private boolean majQuantiteProduit(String nom, int qte, char operation) {

		I_Produit produitActuel = xml_dao.lire(nom);

		if (produitActuel != null)
		{
			double prixUnitaireHT = produitActuel.getPrixUnitaireHT();
			int nouvelleQuantite = produitActuel.getQuantite() + operation + qte;

			I_Produit produit = new Produit(nom, prixUnitaireHT, nouvelleQuantite);

			if (xml_dao.maj(produit))
				return true;
			else
				return false;
		}
		else
			return false;
	}

	@Override
	public I_Produit findByName(String nom) {
		return this.xml_dao.lire(nom);
	}

	@Override
	public void disconnect() {}
}