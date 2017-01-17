package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import metier.I_Produit;
import metier.Produit;

public class ProduitDAO_SQL implements I_ProduitDAO {

	private ConnexionDAO conn;

	private PreparedStatement pst;

	
	public ProduitDAO_SQL()
	{}

	public void create(I_Produit p){
		this.conn = ConnexionDAO.getInstance();
		String sql = "INSERT INTO Produits(nom, prixHT, quantite) VALUES (?,?,?)";
		try {
			pst = conn.getConnexion().prepareStatement(sql);
			pst.setString(1, p.getNom());
			pst.setDouble(2, p.getPrixUnitaireHT());
			pst.setInt(3, p.getQuantite());
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<I_Produit> findAll(){
		this.conn = ConnexionDAO.getInstance();
		String sql = "SELECT * FROM Produits";
		Statement state;
		ResultSet result = null;
		List<I_Produit> produits = new ArrayList<I_Produit>();
		try {
			state = conn.connexion.createStatement();
			result = state.executeQuery(sql);
			while(result.next()){
				Produit p = new Produit(result.getString(1),result.getDouble(2),result.getInt(3));
				produits.add(p);
			}
			result.close();
			state.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return produits;
	}

	public void updateProduit(I_Produit p){
		this.conn = ConnexionDAO.getInstance();
		String sql = "UPDATE Produits SET quantite = quantite + ? WHERE nom = ?";
		try {
			pst = conn.getConnexion().prepareStatement(sql);
			pst.setInt(1, p.getQuantite());
			pst.setString(2, p.getNom());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String nom) {
		this.conn = ConnexionDAO.getInstance();
		String sql = "DELETE FROM Produits WHERE nom = ?";
		try {
			pst = conn.getConnexion().prepareStatement(sql);
			pst.setString(1, nom);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public I_Produit findByName(String nom) {
		this.conn = ConnexionDAO.getInstance();
		String sql = "SELECT FROM Produits WHERE nom = ?";
		Statement state = null;

		I_Produit p = null;
		try {
			pst = conn.getConnexion().prepareStatement(sql);
			pst.setString(1, nom);
			ResultSet result = state.executeQuery(sql);
			if (result.next())
				p = new Produit(result.getString(1),result.getDouble(2),result.getInt(3));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	/** Ferme la connexion à la base de données. */
	public void disconnect() {
		this.conn.fermerConnexion();
	}


}

