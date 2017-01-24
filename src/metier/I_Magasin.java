package metier;

import java.util.List;
import presentation.Observateur;

public interface I_Magasin {
	/** On ajoute un catalogue au catalogue. 
	 @param catalogue : un objet implémentant {@code I_Catalogue} à ajouter 
	 @return Vrai si l'ajout a réussi, faux si le catalogue est incohérent ou existe déjà. */
	public abstract boolean addCatalogue(I_Catalogue catalogue);

	/** Ajoute une liste de catalogues au catalogue. 
	 * @param liste : une {@code List} de catalogues à ajouter.
	 * @return le nombre de catalogues effectivement ajoutés. */
	public abstract int addCatalogues(List<I_Catalogue> liste);

	/** Enlève le catalogue du catalogue. 
	 * @param nom : le nom du catalogue à enlever. 
	 * @return Vrai si le catalogue existe et a été enlevé, Faux sinon. */
	public abstract boolean removeCatalogue(String nom);


	/** @return un tableau de {@code String} trié par ordre alphabétique et
	 * dont chaque case contient le nom d'un catalogue du catalogue. */
	public abstract String[] getNomCatalogues();

	/** @return un objet {@code List} contenant tous les catalogues du catalogue. */
	public List<I_Catalogue> getListeCatalogues();

	/**	Efface tous les catalogues du catalogue. */
	public abstract void clear();

	public abstract void attacher(Observateur o);

	public abstract String[] getDetailsCatalogues();

}
