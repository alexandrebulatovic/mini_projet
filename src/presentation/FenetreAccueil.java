package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import application.ControleurAccueil;
import useful.FieldsKeyAdapter;

@SuppressWarnings("serial")
public class FenetreAccueil extends JFrame implements ActionListener, Observateur {

	private JButton btAjouter, btSupprimer, btSelectionner;
	private JTextField txtAjouter;
	private JLabel lbNbCatalogues;
	private JComboBox cmbSupprimer, cmbSelectionner;
	private TextArea taDetailCatalogues;
	private ControleurAccueil controleur_accueil;
	FieldsKeyAdapter intKey = new FieldsKeyAdapter("int");
	FieldsKeyAdapter stringKey = new FieldsKeyAdapter("String");

	public FenetreAccueil(ControleurAccueil controleur_accueil) {
		setTitle("Catalogues");
		
		this.controleur_accueil = controleur_accueil;
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		JPanel panInfosCatalogues = new JPanel();
		JPanel panNbCatalogues = new JPanel();
		JPanel panDetailCatalogues = new JPanel();
		JPanel panGestionCatalogues = new JPanel();
		JPanel panAjouter = new JPanel();
		JPanel panSupprimer = new JPanel();
		JPanel panSelectionner = new JPanel();
		panNbCatalogues.setBackground(Color.white);
		panDetailCatalogues.setBackground(Color.white);
		panAjouter.setBackground(Color.gray);
		panSupprimer.setBackground(Color.lightGray);
		panSelectionner.setBackground(Color.gray);
		
		panNbCatalogues.add(new JLabel("Nous avons actuellement : "));
		lbNbCatalogues = new JLabel();
		panNbCatalogues.add(lbNbCatalogues);
		
		taDetailCatalogues = new TextArea();
		taDetailCatalogues.setEditable(false);
		new JScrollPane(taDetailCatalogues);
		taDetailCatalogues.setPreferredSize(new Dimension(300, 100));
		panDetailCatalogues.add(taDetailCatalogues);

		panAjouter.add(new JLabel("Ajouter un catalogue : "));
		txtAjouter = new JTextField(10);
		this.txtAjouter.addKeyListener(stringKey);
		panAjouter.add(txtAjouter);
		btAjouter = new JButton("Ajouter");
		panAjouter.add(btAjouter);

		panSupprimer.add(new JLabel("Supprimer un catalogue : "));
		cmbSupprimer = new JComboBox();
		cmbSupprimer.setPreferredSize(new Dimension(100, 20));
		panSupprimer.add(cmbSupprimer);
		btSupprimer = new JButton("Supprimer");
		panSupprimer.add(btSupprimer);

		panSelectionner.add(new JLabel("Selectionner un catalogue : "));
		cmbSelectionner = new JComboBox();
		cmbSelectionner.setPreferredSize(new Dimension(100, 20));
		panSelectionner.add(cmbSelectionner);
		btSelectionner = new JButton("Selectionner");
		panSelectionner.add(btSelectionner);
		
		panGestionCatalogues.setLayout (new BorderLayout());
		panGestionCatalogues.add(panAjouter, "North");
		panGestionCatalogues.add(panSupprimer);
		panGestionCatalogues.add(panSelectionner, "South");
		
		panInfosCatalogues.setLayout(new BorderLayout());
		panInfosCatalogues.add(panNbCatalogues, "North");
		panInfosCatalogues.add(panDetailCatalogues, "South");
				
		contentPane.add(panInfosCatalogues, "North");
		contentPane.add(panGestionCatalogues, "South");
		pack();

		btAjouter.addActionListener(this);
		btSupprimer.addActionListener(this);
		btSelectionner.addActionListener(this);
	
		String[] tab = this.controleur_accueil.getNomCatalogue();
		String[] tab2 = this.controleur_accueil.getDetailsCatalogue();
		modifierListesCatalogues(tab);
		modifierDetailCatalogues(tab2);
		modifierNbCatalogues(tab.length);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAjouter)
		{
			String texteAjout = txtAjouter.getText();
			if (!texteAjout.equals(""))
			{
				this.controleur_accueil.addCatalogue(texteAjout);
				System.out.println("ajouter le catalogue "+texteAjout);
				txtAjouter.setText(null);
			}
		}
		if (e.getSource() == btSupprimer)
		{
			String texteSupprime = (String)cmbSupprimer.getSelectedItem();
			if (texteSupprime != null)
				this.controleur_accueil.removeCatalogue(texteSupprime);
				System.out.println("supprime catalogue "+texteSupprime);
		}
		if (e.getSource() == btSelectionner)
		{
			String texteSelection = (String)cmbSelectionner.getSelectedItem();
			if (texteSelection != null) 
			{
				System.out.println("selectionne catalogue "+texteSelection);
				this.controleur_accueil.selectCatalogue(texteSelection);
				
				//this.dispose();
			}
		}	
	}

	private void modifierListesCatalogues(String[] nomsCatalogues) {
		cmbSupprimer.removeAllItems();
		cmbSelectionner.removeAllItems();
		if (nomsCatalogues != null)
			for (int i=0 ; i<nomsCatalogues.length; i++)
			{
				cmbSupprimer.addItem(nomsCatalogues[i]);
				cmbSelectionner.addItem(nomsCatalogues[i]);
			}
	}
	
	private void modifierNbCatalogues(int nb)
	{
		lbNbCatalogues.setText(nb + " catalogues");
	}
	
	private void modifierDetailCatalogues(String[] detailCatalogues) {
		taDetailCatalogues.setText("");
		if (detailCatalogues != null) {
			for (int i=0 ; i<detailCatalogues.length; i++)
				taDetailCatalogues.append(detailCatalogues[i]+"\n");
		}
	}

	@Override
	public void mettreAJour() {
		String[] tab = this.controleur_accueil.getNomCatalogue();
		modifierListesCatalogues(tab);
		String[] tab2 = this.controleur_accueil.getDetailsCatalogue();
		modifierDetailCatalogues(tab2);
		modifierNbCatalogues(tab.length);
	}
	
	private void fermerApplication() {
		this.controleur_accueil.disconnect();
		System.out.println("Au revoir");
		System.exit(0);
	}
	
	public void windowClosing(WindowEvent e) {
		this.fermerApplication();
	}
	}

