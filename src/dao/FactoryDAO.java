package dao;

public class FactoryDAO {

	
	public FactoryDAO() { }
	
	/**
	 * Methode qui génère un DAO correspondant à la technologie employée (SQL, XMl, etc.).
	 * @return un objet implémentant {@code I_ProduitDAO}.
	 * @see I_ProduitDAO
	 */
	public I_ProduitDAO createDAO(){
		return new ProduitDAO_SQL();
	}
	
}
