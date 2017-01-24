package factory;

import dao.I_CatalogueDAO;
import dao.I_ProduitDAO;

/**
 * Permet d'instancier le {@code ProduitDAO} sélectionné par le développeur.
 * @see I_ProduitDAO
 */
public interface I_DAOFactory {

	public abstract I_CatalogueDAO createCatalogueDAO();

	public abstract I_ProduitDAO createProduitDAO();

}
