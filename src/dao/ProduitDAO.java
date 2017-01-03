package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import metier.Produit;

public class ProduitDAO {
	
	private ConnexionDAO conn;

	private PreparedStatement pst;
	
	public ProduitDAO()
	{}

	public void create(Produit p){
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
		
		this.conn.fermerConnexion();
	}

	public void remove(String nom) {
		this.conn = ConnexionDAO.getInstance();
		String sql = "DELETE FROM Produits WHERE nom = ?";
		try {
			pst = conn.getConnexion().prepareStatement(sql);
			pst.setString(1, nom);
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.conn.fermerConnexion();
		
	}
}
