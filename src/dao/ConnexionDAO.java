package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/** Représente une connexion à une base de données Oracle. */

public class ConnexionDAO 
{

	/* CONSTANTES */
	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String ADRESSE = "jdbc:oracle:thin:@162.38.222.149:1521:IUT";
	private static final String USER = "maurya";
	private static final String PASSWORD = "0205006473Y";

	/* FIELDS */
	private static ConnexionDAO INSTANCE_CONNEXION = null;
	private static Connection connexion;

	/* METHODES */
	private ConnexionDAO() 
	{
		try {
			Class.forName(DRIVER);
			this.connexion = DriverManager.getConnection(ADRESSE, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Erreur : "+e.getMessage());
		} catch (ClassNotFoundException e){
			System.out.println("Le driver n'a pas été trouvé par Java.");
		}
	}


	public Connection getConnexion() {
		return connexion;
	}

	/** Retourne l'instance en cours d'une {@code ConnexionDAO} ou 
	 * alors crée une nouvelle instance si nécessaire.
	 * @return un {@link ConnexionDAO}
	 */
	public synchronized static ConnexionDAO getInstance()
	{			
		if (INSTANCE_CONNEXION == null)
			INSTANCE_CONNEXION = new ConnexionDAO();

		return INSTANCE_CONNEXION;
	}


	/** Ferme la connexion à la base de données.
	 * @return Vrai si la fermeture a réussie, faux sinon. */
	public static boolean fermerConnexion()
	{
		boolean fermeture_connexion;

		try {
			connexion.close();
			fermeture_connexion = true;
			INSTANCE_CONNEXION = null;

		} catch (SQLException e) {

			System.out.println("Erreur : "+e.getMessage());
			fermeture_connexion = false;
		}

		return fermeture_connexion;
	}




}
