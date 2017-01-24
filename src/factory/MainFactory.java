package factory;

public class MainFactory {
	/* ATTRIBUTS */
	private static MainFactory INSTANCE;

	/** Indique qu'on souhaite un {@code DAO} pour du XML. */
	public static final int TYPE_XML = 0;

	/** Indique qu'on souhaite un {@code DAO} pour un SGBD utilisant 
	 * le langage SQL et implémentant l'API {@code JDBC}. */
	public static final int TYPE_SQL = 1;

	/* METHODES */
	protected MainFactory() {}


	public static I_DAOFactory createDAO(int dao_type){
		switch (dao_type){
		case TYPE_XML:
			return new XMLFactory();
		case TYPE_SQL:
			return new OracleFactory();
		default:
			throw new IllegalArgumentException("le type choisi n'existe pas");
		}
	}


}
