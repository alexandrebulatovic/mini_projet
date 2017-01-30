	package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Représente une connexion à une base de données Oracle. */

public class ConnexionDAO_Oracle
{

	/* ATTRIBUTS */

	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String ADRESSE = "jdbc:oracle:thin:@162.38.222.149:1521:IUT";
	private static final String USER = "maurya";
	private static final String PASSWORD = "0205006473Y";

	private static ConnexionDAO_Oracle INSTANCE;

	private static Connection connexion;

	/* METHODES */

	/**
	 * Initialise un objet {@code Connection} pour la base de données Oracle.
	 */
	protected ConnexionDAO_Oracle() 
	{
		try {
			Class.forName(DRIVER);
			connexion = DriverManager.getConnection(ADRESSE, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Erreur : "+e.getMessage());
		} catch (ClassNotFoundException e){
			System.out.println("Le driver n'a pas été trouvé par Java.");
		}
	}

	public static synchronized ConnexionDAO_Oracle getInstance()
	{			
		if (INSTANCE == null)
			INSTANCE = new ConnexionDAO_Oracle();
		return INSTANCE;
	}

	public Connection getConnexion() {
		return connexion;
	}

	/** Ferme la connexion à la base de données.
	 * @return Vrai si la fermeture a réussie, faux sinon. */
	public static boolean fermerConnexion()
	{
		boolean fermeture_connexion;

		try 
		{
			connexion.close();
			fermeture_connexion = true;
			INSTANCE = null;

		} catch (SQLException e) {

			System.out.println("Erreur : "+e.getMessage());
			fermeture_connexion = false;
		}

		return fermeture_connexion;
	}




}
