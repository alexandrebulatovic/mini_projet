package dao;

import metier.I_Produit;

/**
 * Permet d'instancier le {@code ProduitDAO} sélectionné par le développeur.
 * @see I_ProduitDAO
 */
public interface I_DAOFactory {
	
	/** Indique qu'on souhaite un {@code DAO} pour du XML. */
	public static final int TYPE_XML = 0;

	/** Indique qu'on souhaite un {@code DAO} pour un SGBD utilisant 
	 * le langage SQL et implémentant l'API {@code JDBC}. */
	public static final int TYPE_SQL = 1;

	public abstract I_CatalogueDAO createCatalogueDAO();
	
	public abstract I_ProduitDAO createProduitDAO();
	
}
