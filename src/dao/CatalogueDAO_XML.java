package dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import metier.Catalogue;
import metier.I_Catalogue;
import metier.I_Produit;
import metier.Produit;

public class CatalogueDAO_XML implements I_CatalogueDAO{

	private String uri = "Catalogues.xml";
	private Document doc;
	
	
	public CatalogueDAO_XML()
	{
		SAXBuilder sdoc = new SAXBuilder();
		try {
			doc = sdoc.build(uri);
		} catch (Exception e) {
			System.out.println("erreur construction arbre JDOM");
		}
	}
	
		
	@Override
	public boolean create(I_Catalogue catalogue) {
		try {
			Element root = doc.getRootElement();
			Element cat = new Element("catalogue");
			cat.setAttribute("nom", catalogue.getNom());
			root.addContent(cat);
			return save();
		} catch (Exception e) {
			System.out.println("erreur creer catalogue");
			return false;
		}
	}

	@Override
	public boolean delete(String nom) {
		try {
			Element root = doc.getRootElement();
			Element cat = (Element)findByName(nom);
			if (cat != null) {
				root.removeContent(cat);
				return save();
			} else
				return false;
		} catch (Exception e) {
			System.out.println("erreur supprimer catalogue");
			return false;
		}
	}

	@Override
	public List<I_Catalogue> findAll() {
		List<I_Catalogue> l = new ArrayList<I_Catalogue>();
		try {
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> lCat = root.getChildren("catalogue");

			for (Element catalogue : lCat) {
				String nomCatalogue = catalogue.getAttributeValue("nom");
				l.add(new Catalogue(nomCatalogue));
			}
		} catch (Exception e) {
			System.out.println("erreur findAll tous les catalogues");
		}
		return l;
	}

	@Override
	public I_Catalogue findByName(String nom) {
		Element root = doc.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> lCat = root.getChildren("catalogue");
		int i = 0;
		while (i < lCat.size() && !lCat.get(i).getAttributeValue("nom").equals(nom))
			i++;
		if (i < lCat.size())
			return (I_Catalogue)lCat.get(i);
		else
			return null;
	}

	@Override
	public List<String> findAllNames() {
		List<String> l = new ArrayList<String>();
		try {
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> lCat = root.getChildren("catalogue");

			for (Element catalogue : lCat) {
				String nomCatalogue = catalogue.getAttributeValue("nom");
				l.add(nomCatalogue);
			}
		} catch (Exception e) {
			System.out.println("erreur findAllNames tous les noms des catalogues");
		}
		return l;
	}



	@Override
	public int findNbProduits(String string) {
		try {
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> lCat = root.getChildren("catalogue");
			int i= 0;
			while (i < lCat.size() && !lCat.get(i).getAttributeValue("nom").equals(string)){
				i++;
			}
			Element e = lCat.get(i);
			List<Element> lProd = e.getChildren("produit");
			return lProd.size();
			
		} catch (Exception e) {
			System.out.println("erreur findNbProduits");
			return 0;
		}
		
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


	@Override
	public void disconnect() {}

}
