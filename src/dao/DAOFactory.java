package dao;


/**
 * Permet d'instancier le {@code ProduitDAO} sélectionné par le développeur.
 * @see I_ProduitDAO
 */
public class DAOFactory {

	/* ATTRIBUTS */
	private static DAOFactory INSTANCE;

	/** Indique qu'on souhaite un {@code DAO} pour du XML. */
	public static final int TYPE_XML = 0;

	/** Indique qu'on souhaite un {@code DAO} pour un SGBD utilisant 
	 * le langage SQL et implémentant l'API {@code JDBC}. */
	public static final int TYPE_SQL = 1;

	/** Indique qu'on souhaite un {@code DAO} de Mock pour les tests. */
	public static final int TYPE_MOCK = 2;

	/* METHODES */
	protected DAOFactory() {}

	/**
	 * Méthode qui génère un {@code ProduitDAO} correspondant à la technologie employée (SQL, XML, etc.).
	 * @param dao_type le type de {@code ProduitDAO} à instancier, au choix 
	 * {@code DAOFactory.TYPE_XML} ou {@code DAOFactory.TYPE_SQL}.
	 * @return un objet implémentant la classe {@code I_ProduitDAO} permettant la persistance des données.
	 * @exception IllegalArgumentException si le type n'est pas renseigné.
	 * @see I_ProduitDAO
	 */
	public I_ProduitDAO createDAO(int dao_type){
		switch (dao_type){
		case TYPE_XML:
			return new ProduitDAO_XML_Adapted();
		case TYPE_SQL:
			return new ProduitDAO_SQL();
		case TYPE_MOCK:
			return new MockProduitDAO();
		default:
			throw new IllegalArgumentException("le type choisi n'existe pas");
		}
	}

	public static synchronized DAOFactory getInstance() {
		if (INSTANCE == null)
			INSTANCE = new DAOFactory();
		return INSTANCE;
	}
}
