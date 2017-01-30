package factory;

import dao.CatalogueDAO_XML;
import dao.I_CatalogueDAO;
import dao.I_ProduitDAO;
import dao.ProduitDAO_XML;

/**
 * Permet d'instancier le {@code ProduitDAO} sélectionné par le développeur.
 * @see I_ProduitDAO
 */
public class XMLFactory implements I_DAOFactory{

	/* ATTRIBUTS */
	private static XMLFactory INSTANCE;

	/* METHODES */
	protected XMLFactory() {}

	public static synchronized I_DAOFactory getInstance() {
		if (INSTANCE == null)
			INSTANCE = new XMLFactory();
		return INSTANCE;
	}

	@Override
	public I_CatalogueDAO createCatalogueDAO() {
		return new CatalogueDAO_XML();
	}

	@Override
	public I_ProduitDAO createProduitDAO() {
		return new ProduitDAO_XML();
	}
}
