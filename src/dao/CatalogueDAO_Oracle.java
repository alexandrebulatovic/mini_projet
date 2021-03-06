package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import metier.Catalogue;
import metier.I_Catalogue;

/**
 * Implémentation de {@code I_CatalogueDAO} spécifique pour un SGBD utilisant 
 * le langage SQL et implémentant l'API {@code JDBC}.
 * @see I_CatalogueDAO
 */

public class CatalogueDAO_Oracle implements I_CatalogueDAO{

	/* ATTRIBUTS */

	private Connection conn;
	private PreparedStatement prepstat;
	private Statement stat;
	private ResultSet rs;

	public CatalogueDAO_Oracle()
	{
		this.conn = ConnexionDAO_Oracle.getInstance().getConnexion();
	}

	@Override
	public boolean create(I_Catalogue catalogue){
		String sql = "INSERT INTO Catalogues(nom) VALUES (?)";
		try {
			prepstat = this.conn.prepareStatement(sql);
			prepstat.setString(1, catalogue.getNom());
			prepstat.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("erreur creation catalogue");
			return false;
		}
	}

	@Override
	public List<I_Catalogue> findAll()
	{
		String sql = "SELECT * FROM Catalogues";
		List<I_Catalogue> catalogue = new ArrayList<I_Catalogue>();
		try {
			stat = this.conn.createStatement();
			rs = stat.executeQuery(sql);
	
			while(rs.next())
			{
				I_Catalogue p = new Catalogue(rs.getString(2));
				catalogue.add(p);
			}
		}
		catch (SQLException e) {
			System.out.println("erreur recuperation catalogues");
		}
		return catalogue;
	}

	@Override
	public List<String> findAllNames()
	{
		String sql = "SELECT nom FROM Catalogues";
		List<String> catalogues = new ArrayList<String>();
		try {
			stat = this.conn.createStatement();
			rs = stat.executeQuery(sql);
	
			while(rs.next())
			{
				catalogues.add(rs.getString(2));
			}
		}
		catch (SQLException e) {
			System.out.println("erreur recuperation nom des catalogues");
		}
		return catalogues;
	}

	@Override
	public I_Catalogue findByName(String nom) {
		String sql = "SELECT * FROM Catalogues WHERE nom = ?";
		I_Catalogue catalogue;
		try {
			prepstat = this.conn.prepareStatement(sql);
			prepstat.setString(1, nom);
			rs = prepstat.executeQuery(sql);
	
			if (rs.next())
				catalogue = new Catalogue(rs.getString(2));
			else
				return null;
	
			return catalogue;
		} catch (SQLException e) {
			System.out.println("erreur recuperation catalogue");
			return null;
		}
	}

	public int findNbProduits(String catalogue){
		String sql = "SELECT COUNT(*) FROM Produits WHERE nom_catalogue = ?";
		try {
			prepstat = this.conn.prepareStatement(sql);
			prepstat.setString(1, catalogue);
			rs = prepstat.executeQuery();
			rs.next();
			return rs.getInt(1);
			
		} catch (SQLException e) {
			System.out.println("erreur recuperation nbproduits");
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	
	@Override
	public boolean delete(String nom) {
		String sql = "DELETE FROM Catalogues WHERE nom = ?";
		try {
			prepstat = this.conn.prepareStatement(sql);
			prepstat.setString(1, nom);
			prepstat.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("erreur suppression catalogue");
			return false;
		}
	}

	public void disconnect() {
		ConnexionDAO_Oracle.fermerConnexion();

	}


}
