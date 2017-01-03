package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnexionDAO 
{

	private static ConnexionDAO instance_connexion = null;

	private Connection connexion;


	private ConnexionDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			this.connexion = DriverManager.getConnection(
					"jdbc:oracle:thin:@162.38.222.149:1521:IUT","maurya","0205006473Y");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}

	}

	public static ConnexionDAO getInstance()
	{			
		if (instance_connexion == null)
		{ 	
			instance_connexion = new ConnexionDAO();	
		}
		return instance_connexion;
	}


	/**
	 * Ferme la connexion.
	 * @return Vrai si la fermeture a réussie, faux sinon.
	 */
	public boolean fermerConnexion()
	{
		boolean fermeture_connexion;
		try {
			this.getConnexion().close();
			fermeture_connexion = true;
			this.instance_connexion = null;
		} catch (SQLException e) {
			e.printStackTrace();
			fermeture_connexion = false;
		}

		return fermeture_connexion;
	}

	public Connection getConnexion() {
		return connexion;
	}


}
