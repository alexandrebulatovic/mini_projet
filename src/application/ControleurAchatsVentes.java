package application;

import presentation.*;
import metier.*;

public class ControleurAchatsVentes {
	private FenetreAchat fa;
	private FenetreVente fv;
	
	private Catalogue cat;
	
	public ControleurAchatsVentes(Catalogue cat,String str){
		this.cat=cat;	
		if(str.equals("Achat")){
			fa = new FenetreAchat(this.cat.getNomProduits());
		}else if(str.equals("Vente")){
			fv = new FenetreVente(this.cat.getNomProduits());
		}
	}
	
}
