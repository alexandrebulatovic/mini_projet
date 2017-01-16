package dao;

public class FactoryDAO {

	
	public FactoryDAO() { }
	
	public I_ProduitDAO createDAO(){
		return new AdaptateurXML();
	}
	
}
