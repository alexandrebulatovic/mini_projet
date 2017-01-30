package dao;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import metier.I_Produit;
import metier.Produit;

import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

/**
 * Classe fournie et non modifiée permettant l'utilisation 
 * de fichiers XML pour la persistance des données.
 */
public class ProduitDAO_XML implements I_ProduitDAO{
	private String uri = "Catalogues.xml"; // fichier a placer dans le dossier du mini projet
	private Document doc;

	/** Correspond à l'opérateur d'addition.*/
	private static final char AJOUT = '+';

	/** Correspond à l'opérateur de soustraction. */
	private static final char RETRAIT = '-';

	public ProduitDAO_XML() {
		SAXBuilder sdoc = new SAXBuilder();
		try {
			doc = sdoc.build(uri);
		} catch (Exception e) {
			System.out.println("erreur construction arbre JDOM");
		}
	}

	@Override
	public boolean create(I_Produit produit, String catalogue) {
		try {
			Element root = doc.getRootElement();
			List<Element> lCat = root.getChildren("catalogue");
			int i = getIndexCatalogue(catalogue, lCat);
			Element cat = lCat.get(i);
			Element prod = new Element("produit");
			Element nom = new Element("nom");
			prod.addContent(nom.setText(String.valueOf(produit.getNom())));
			Element prix = new Element("prixHT");
			prod.addContent(prix.setText(String.valueOf(produit.getPrixUnitaireHT())));
			Element qte = new Element("quantite");
			prod.addContent(qte.setText(String.valueOf(produit.getQuantite())));
			cat.addContent(prod);
			return save();
		} catch (Exception e) {
			System.out.println("erreur creer produit");
			return false;
		}
	}

	//TODO
	@Override
	public boolean delete(String nom,String catalogue) {
		try {
			Element root = doc.getRootElement();
			List<Element> lCat = root.getChildren("catalogue");
			Element prod = find(nom,catalogue);
			if (prod != null) {
				int i = getIndexCatalogue(catalogue, lCat);
				lCat.get(i).removeContent(prod);
				return save();
			} else
			return false;
		} catch (Exception e) {
			System.out.println("erreur supprimer produit");
			return false;
		}
	}
	@Override
	public I_Produit findByName(String nom) {
		Element root = doc.getRootElement();
		List<Element> lProd = root.getChildren("produit");
		int a = getIndexProduit(nom, lProd);
		if (a < lProd.size())
			return (I_Produit)lProd.get(a);
		else
			return null;
	}

	@Override
	public List<I_Produit> findAll(String catalogue) {
		List<I_Produit> l = new ArrayList<I_Produit>();
		try {
			Element root = doc.getRootElement();
			List<Element> lCat = root.getChildren("catalogue");
			int i = getIndexCatalogue(catalogue, lCat);
			Element cat = lCat.get(i);
			List<Element> lProd = cat.getChildren("produit");
	
			for (Element prod : lProd) {
				String nomP = prod.getChild("nom").getText();
				Double prix = Double.parseDouble(prod.getChild("prixHT").getText());
				int qte = Integer.parseInt(prod.getChild("quantite").getText());
				l.add(new Produit(nomP, prix, qte));
			}
		} catch (Exception e) {
			System.out.println("erreur lireTous tous les produits");
		}
		return l;
	}

	@Override
	public boolean addQuantite(String nom,String catalogue, int quantite) {
		return UpdateQuantiteProduit(nom,catalogue, quantite, ProduitDAO_XML.AJOUT);
	}

	@Override
	public boolean removeQuantite(String nom,String catalogue, int quantite) {
		return UpdateQuantiteProduit(nom,catalogue, quantite, ProduitDAO_XML.RETRAIT);
	}

	private int getIndexProduit(String nom, List<Element> lProd) {
		int a = 0;
		while (a < lProd.size() && !lProd.get(a).getChild("nom").getText().equals(nom))
			a++;
		return a;
	}

	private int getIndexCatalogue(String catalogue, List<Element> lCat) {
		int i = 0;
		while (i < lCat.size() && !lCat.get(i).getChild("nomCatalogue").getText().equals(catalogue))
			i++;
		return i;
	}

	private Element find(String nom,String catalogue){
		Element root = doc.getRootElement();
	
		List<Element> lCat = root.getChildren("catalogue");
		int i = getIndexCatalogue(catalogue, lCat);
		Element cat = lCat.get(i);
		List<Element> lProd = cat.getChildren("produit");
		int a = getIndexProduit(nom, lProd);
		if (a < lProd.size())
			return lProd.get(a);
		else
			return null;
	}

	private boolean save() {
		System.out.println("Sauvegarde");
		XMLOutputter out = new XMLOutputter();
		try {
			out.output(doc, new PrintWriter(uri));
			return true;
		} catch (Exception e) {
			System.out.println("erreur sauvegarde dans fichier XML");
			return false;
		}
	}

	private boolean UpdateQuantiteProduit(String nom,String catalogue, int qte, char operation) {
		try {
			Element root = doc.getRootElement();
			Element prod = find(nom,catalogue);
			if (prod != null) {
				int quantite = Integer.parseInt(prod.getChild("quantite").getText());
				if(operation == '+'){
					prod.getChild("quantite").setText(Integer.toString(quantite+qte));
				}else{
					prod.getChild("quantite").setText(Integer.toString(quantite-qte));
				}
				return save();
			}
			return false;
		} catch (Exception e) {
			System.out.println("erreur maj produit");
			return false;
		}

	}
}
