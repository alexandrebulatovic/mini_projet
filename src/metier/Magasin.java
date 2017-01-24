package metier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import presentation.Observateur;

public class Magasin implements I_Magasin {
	/* ATTRIBUTS */

	private List<I_Catalogue> lesCatalogues;
	private List<Observateur> auditeurs = new ArrayList<Observateur>();

	/* METHODES */

	public Magasin() {
		this.lesCatalogues = new ArrayList<I_Catalogue>();
	}

	@Override
	public boolean addCatalogue(I_Catalogue catalogue) {
		if ( this.existe(catalogue) )
		{
			return false;
		}
		else {

			this.lesCatalogues.add(catalogue);
			this.avertir();
			return true;
		}
	}

	private boolean existe(I_Catalogue catalogue) {

		for (int i=0; i < lesCatalogues.size(); i++){

			if (lesCatalogues.get(i).getNom().equals(catalogue.getNom())){
				return true;
			}
		}
		return false;
	}

	@Override
	public int addCatalogues(List<I_Catalogue> catalogues) 
	{
		int catalogues_rajoutes = 0;

		if (catalogues != null) {

			for (int i=0; i < catalogues.size() ; i++)
			{
				if (this.addCatalogue(catalogues.get(i)))
					catalogues_rajoutes++;
			}
		}
		this.avertir();
		return catalogues_rajoutes;
	}

	@Override
	public boolean removeCatalogue(String nom) {

		boolean trouve = false;

		if (nom != null)
		{
			for (int i=0; i < this.lesCatalogues.size() && !trouve ; i++)
			{
				if (this.lesCatalogues.get(i).getNom().equals(nom)){
					this.lesCatalogues.remove(i);
					trouve = true;
				}
			}
		}
		this.avertir();
		return trouve;
	}

	@Override
	public String[] getNomCatalogues() {

		int taille = this.lesCatalogues.size();
		System.out.println(taille);
		String[] tabNomCatalogues = new String[taille];
		for (int i = 0; i < taille; i++){
			tabNomCatalogues[i] = this.lesCatalogues.get(i).getNom();
		}
		Arrays.sort(tabNomCatalogues); // tri du tableau

		return tabNomCatalogues;
	}

	@Override
	public List<I_Catalogue> getListeCatalogues() {
		return lesCatalogues;
	}

	@Override
	public void clear() {
		this.lesCatalogues.clear();
	}

	public void attacher(Observateur o){
		this.auditeurs.add(o);
	}

	private void avertir(){
		for(int i=0; i<this.auditeurs.size();i++){
			this.auditeurs.get(i).mettreAJour();
		}
	}

	public String[] getDetailsCatalogues(){
		String[] details = new String[this.lesCatalogues.size()];
		for (int i=0; i < this.lesCatalogues.size(); i++){
			details[i]= this.lesCatalogues.get(i).getNom();
		}
		return details;
	}
}
