package presentation;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import metier.Catalogue;
import application.*;



public class FenetrePrincipale extends JFrame implements ActionListener,
WindowListener {

	private JButton btAfficher;
	private JButton btNouveauProduit;
	private JButton btSupprimerProduit;
	//	private JButton btNouvelleCategorie;
	//	private JButton btSupprimerCategorie;
	private JButton btAchat;
	private JButton btVente;
	private JButton btQuitter;


	private ControleurCatalogue controleur_catalogue;
	private ControleurStocks controleur_stock;
	private ControleurPrincipal controleur_principal;
	private ControleurAchatVente controleur_achats_ventes;

	public FenetrePrincipale() {

		this.controleur_principal = new ControleurPrincipal();

		this.controleur_catalogue = controleur_principal.getControleurCatalogue();
		this.controleur_stock = controleur_principal.getControleurStocks();
		this.controleur_achats_ventes = controleur_principal.getControleurAchatVente();

		setTitle("exercice Produits");
		setBounds(500, 500, 320, 250);
		JPanel panAffichage = new JPanel();
		JPanel panNouveauSupprimerProduit = new JPanel();
		//		JPanel panNouveauSupprimerCategorie = new JPanel();
		JPanel panAchatVente = new JPanel();
		JPanel panQuitter = new JPanel();
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAfficher = new JButton("Quantit�s en stock");
		btNouveauProduit = new JButton("Nouveau Produit");
		btSupprimerProduit = new JButton("Supprimer Produit");
		//		btNouvelleCategorie = new JButton("Nouvelle Categorie");
		//		btSupprimerCategorie = new JButton("Supprimer Categorie");
		btAchat = new JButton("Achat Produits");
		btVente = new JButton("Vente Produits");
		btQuitter = new JButton("Quitter");
		panAffichage.add(btAfficher);
		panNouveauSupprimerProduit.add(btNouveauProduit); 
		panNouveauSupprimerProduit.add(btSupprimerProduit);
		//		panNouveauSupprimerCategorie.add(btNouvelleCategorie); 
		//		panNouveauSupprimerCategorie.add(btSupprimerCategorie);
		panAchatVente.add(btAchat); 
		panAchatVente.add(btVente);  
		panQuitter.add(btQuitter);

		contentPane.add(panAffichage);
		//		contentPane.add(panNouveauSupprimerCategorie);
		contentPane.add(panNouveauSupprimerProduit);
		contentPane.add(panAchatVente);
		contentPane.add(panQuitter);

		btAfficher.addActionListener(this);
		btNouveauProduit.addActionListener(this);
		btSupprimerProduit.addActionListener(this);
		//		btNouvelleCategorie.addActionListener(this);
		//		btSupprimerCategorie.addActionListener(this);
		btAchat.addActionListener(this);
		btVente.addActionListener(this);
		btQuitter.addActionListener(this);

		addWindowListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		/* M�me chose pour tabCategories (partie 4) */ 		
		//		String[] tabCategories = new String[] {"Bio", "Luxe" };

		if (e.getSource() == btAfficher)
			new FenetreAffichage(this.controleur_stock.getStocksCatalogue());
		if (e.getSource() == btNouveauProduit)
			//			new FenetreNouveauProduit(tabCategories);
			new FenetreNouveauProduit(this.controleur_catalogue);
		if (e.getSource() == btSupprimerProduit)
			new FenetreSuppressionProduit(this.controleur_catalogue,this.controleur_catalogue.getNomProduits());
		//		if (e.getSource() == btNouvelleCategorie)
		//			new FenetreNouvelleCategorie();
		//		if (e.getSource() == btSupprimerCategorie)
		//			new FenetreSuppressionCategorie(tabCategories);
		if (e.getSource() == btAchat)
			new FenetreAchat(this.controleur_achats_ventes,this.controleur_catalogue.getNomProduits());
		if (e.getSource() == btVente)
			new FenetreVente(this.controleur_achats_ventes,this.controleur_catalogue.getNomProduits());
		if (e.getSource() == btQuitter){
			fermerApplication();
		}	
	}


	public void windowClosing(WindowEvent arg0) {
		fermerApplication();
	}
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}

	private void fermerApplication() {
		this.controleur_principal.disconnect();
		System.out.println("Au revoir");
		System.exit(0);
	}
}