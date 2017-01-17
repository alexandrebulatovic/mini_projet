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

public class ProduitDAO_SQL implements I_ProduitDAO {

	private Connection conn;
	private PreparedStatement prepstat;
	private Statement stat;
	private ResultSet rs;

	/** Initialise une connexion à une base de données. */
	public ProduitDAO_SQL()
	{
		this.conn = ConnexionDAO.getInstance().getConnexion();
	}

	public void create(I_Produit p){
		String sql = "INSERT INTO Produits(nom, prixHT, quantite) VALUES (?,?,?)";
		try {
			prepstat = this.conn.prepareStatement(sql);
			prepstat.setString(1, p.getNom());
			prepstat.setDouble(2, p.getPrixUnitaireHT());
			prepstat.setInt(3, p.getQuantite());
			prepstat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<I_Produit> findAll(){
		String sql = "SELECT * FROM Produits";
		List<I_Produit> produits = new ArrayList<I_Produit>();
		try {
			stat = this.conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				Produit p = new Produit(rs.getString(1),rs.getDouble(2),rs.getInt(3));
				produits.add(p);
			}
			rs.close();
			stat.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return produits;
	}

	public void updateProduit(I_Produit p){
		String sql = "UPDATE Produits SET quantite = quantite + ? WHERE nom = ?";
		try {
			prepstat = this.conn.prepareStatement(sql);
			prepstat.setInt(1, p.getQuantite());
			prepstat.setString(2, p.getNom());
			prepstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String nom) {
		String sql = "DELETE FROM Produits WHERE nom = ?";
		try {
			prepstat = this.conn.prepareStatement(sql);
			prepstat.setString(1, nom);
			prepstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public I_Produit findByName(String nom) {
		String sql = "SELECT FROM Produits WHERE nom = ?";

		I_Produit p = null;

		try {
			prepstat = this.conn.prepareStatement(sql);
			prepstat.setString(1, nom);
			ResultSet result = prepstat.executeQuery(sql);
			if (result.next())
				p = new Produit(result.getString(1),result.getDouble(2),result.getInt(3));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	/** Ferme la connexion à la base de données. */
	public void disconnect() {
		ConnexionDAO.fermerConnexion();
	}


}

