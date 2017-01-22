package presentation;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import application.ControleurCatalogue;

public class FenetreSuppressionProduit extends JFrame implements ActionListener {

	private JButton btSupprimer;
	private JComboBox<String> combo;
	private ControleurCatalogue controleur_catalogue;
	
	public FenetreSuppressionProduit(ControleurCatalogue controleur_catalogue, String lesProduits[]) {
		this.controleur_catalogue = controleur_catalogue;
		setTitle("Suppression produit");
		setBounds(500, 500, 200, 105);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btSupprimer = new JButton("Supprimer");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(btSupprimer);

		btSupprimer.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String nomProduit = this.combo.getSelectedItem().toString();
		this.controleur_catalogue.removeProduit(nomProduit);
		this.dispose();
	}

}
