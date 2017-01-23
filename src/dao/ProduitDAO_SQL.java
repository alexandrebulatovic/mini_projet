package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import metier.I_Produit;
import metier.Produit;

/**
 * Implémentation de {@code I_ProduitDAO} spécifique pour un SGBD utilisant 
 * le langage SQL et implémentant l'API {@code JDBC}.
 * @see I_ProduitDAO
 */

public class ProduitDAO_SQL implements I_ProduitDAO {

	/* ATTRIBUTS */

	private Connection conn;
	private PreparedStatement prepstat;
	private Statement stat;
	private ResultSet rs;

	/** Correspond à l'opérateur d'addition.*/
	private static final char AJOUT = '+';

	/** Correspond à l'opérateur de soustraction. */
	private static final char RETRAIT = '-';

	/* METHODES */

	/** Initialise la connexion à la base de données. */
	public ProduitDAO_SQL()
	{
		this.conn = ConnexionDAO_Oracle.getInstance().getConnexion();
	}

	@Override
	public boolean create(I_Produit p){
		String sql = "INSERT INTO Produits(nom, prixHT, quantite) VALUES (?,?,?)";
		try 
		{
			prepstat = this.conn.prepareStatement(sql);
			prepstat.setString(1, p.getNom());
			prepstat.setDouble(2, p.getPrixUnitaireHT());
			prepstat.setInt(3, p.getQuantite());
			prepstat.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.out.println("erreur creation produit");
			return false;
		}
	}

	@Override
	public I_Produit findByName(String nom) {
		String sql = "SELECT * FROM Produits WHERE nom = ?";

		I_Produit produit;

		try {
			prepstat = this.conn.prepareStatement(sql);
			prepstat.setString(1, nom);
			rs = prepstat.executeQuery(sql);

			if (rs.next())
				produit = new Produit(rs.getString(1), rs.getDouble(2), rs.getInt(3));
			else
				return null;

			return produit;
		} catch (SQLException e) {
			System.out.println("erreur recuperation produit");
			return null;
		}
	}

	@Override
	public List<I_Produit> findAll()
	{
		String sql = "SELECT * FROM Produits";
		List<I_Produit> produits = new ArrayList<I_Produit>();

		try {
			stat = this.conn.createStatement();
			rs = stat.executeQuery(sql);

			while(rs.next())
			{
				Produit p = new Produit(rs.getString(1), rs.getDouble(2), rs.getInt(3));
				produits.add(p);
			}
		}
		catch (SQLException e) {
			System.out.println("erreur recuperation produits");
		}
		return produits;
	}

	@Override
	public boolean addQuantite(String nom, int qte) {
		return majQuantiteProduit(nom, qte, ProduitDAO_SQL.AJOUT);
	}

	@Override
	public boolean removeQuantite(String nom, int qte) {
		return majQuantiteProduit(nom, qte, ProduitDAO_SQL.RETRAIT);
	}

	/**
	 * Met à jour la quantité d'un produit.
	 * @param nom : nom du produit à mettre à jour.
	 * @param qte : valeur à ajouter ou à soustraire à la quantité réelle.
	 * @param operation : opération à effectuer parmi {@code ProduitDAO_SQL.AJOUT} ou 
	 * {@code ProduitDAO_SQL.RETRAIT}.
	 * @return Vrai si la mise à jour a réussi, faux sinon.
	 */
	private boolean majQuantiteProduit(String nom, int qte, char operation) {
		String sql = "UPDATE Produits SET quantite = quantite "+ operation + "? WHERE nom = ?";
		try {
			prepstat = this.conn.prepareStatement(sql);

			prepstat.setInt(1, qte);
			prepstat.setString(2, nom);
			prepstat.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.out.println("erreur maj quantite produit");
			return false;
		}
	}

	@Override
	public boolean delete(String nom) {
		String sql = "DELETE FROM Produits WHERE nom = ?";

		try {
			prepstat = this.conn.prepareStatement(sql);

			prepstat.setString(1, nom);
			prepstat.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.out.println("erreur suppression produit");
			return false;
		}
	}

	@Override
	public void disconnect() {
		ConnexionDAO_Oracle.fermerConnexion();
	}
}