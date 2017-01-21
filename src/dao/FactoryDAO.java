package dao;

public class FactoryDAO {

	
	public FactoryDAO() { }
	
	/**
	 * Methode qui génère un DAO correspondant à la technologie employée (SQL, XML, etc.).
	 * @return un objet implémentant {@code I_ProduitDAO} permettant la persistance des données.
	 * @see I_ProduitDAO
	 */
	public I_ProduitDAO createDAO(){
		return new AdaptateurXML();
	}
	
}
