package dao;

import java.util.List;

import metier.I_Catalogue;


public interface I_CatalogueDAO {
	/** Crée un nouveau catalogue dans la base de données.
	 * @param p : objet {@code I_Catalogue} à ajouter. */
	public abstract boolean create(I_Catalogue p);

	/** Supprime un catalogue de la base de données.
	 * @param nom : nom du catalogue à supprimer. */
	public abstract boolean delete(String nom);

	/** Génère une liste de tous les catalogues stockés dans la base de données.
	 * @return un objet {@code List} contenant tous les catalogues. */
	public abstract List<I_Catalogue> findAll();

	/** Cherche le catalogue demandé dans la base de données.
	 * @param nom : nom du catalogue à générer.
	 * @return un objet {@code I_Catalogue} correspondant à un catalogue existant. */
	public abstract I_Catalogue findByName(String nom);

	/** Génère une liste de tous les noms des catalogues stockés dans la base de données.
	 *  @return un objet {@code List} contenant tous les nom des catalogues. */
	public abstract List<String> findAllNames();


	/** Se déconnecte de la base de données. */
	public abstract void disconnect();

	public abstract int findNbProduits(String string);
}
