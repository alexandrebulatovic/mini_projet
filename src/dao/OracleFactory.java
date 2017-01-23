package dao;


/**
 * Permet d'instancier le {@code ProduitDAO} sélectionné par le développeur.
 * @see I_ProduitDAO
 */
public class OracleFactory implements I_DAOFactory {

	/* ATTRIBUTS */
	private static OracleFactory INSTANCE;



	/* METHODES */
	protected OracleFactory() {}

	/**
	 * Méthode qui génère un {@code ProduitDAO} correspondant à la technologie employée (SQL, XML, etc.).
	 * @param dao_type le type de {@code ProduitDAO} à instancier, au choix 
	 * {@code DAOFactory.TYPE_XML} ou {@code DAOFactory.TYPE_SQL}.
	 * @return un objet implémentant la classe {@code I_ProduitDAO} permettant la persistance des données.
	 * @exception IllegalArgumentException si le type n'est pas renseigné.
	 * @see I_ProduitDAO
	 */


	public static synchronized I_DAOFactory getInstance() {
		if (INSTANCE == null)
			INSTANCE = new OracleFactory();
		return INSTANCE;
	}

	@Override
	public I_CatalogueDAO createCatalogueDAO() {
		return new CatalogueDAO_Oracle();
	}

	@Override
	public I_ProduitDAO createProduitDAO() {
		return new ProduitDAO_Oracle();
	}
}
