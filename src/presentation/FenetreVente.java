package presentation;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import application.ControleurCatalogue;

public class FenetreVente extends JFrame implements ActionListener {

	private JButton btVente;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	private ControleurCatalogue cc;

	public FenetreVente(ControleurCatalogue cc,String[] lesProduits) {
		this.cc = cc;
		setTitle("Vente");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btVente = new JButton("Vente");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantité vendue"));
		contentPane.add(txtQuantite);
		contentPane.add(btVente);

		btVente.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String produit = this.combo.getSelectedItem().toString();
		int qte = Integer.parseInt(this.txtQuantite.getText());
		this.cc.vendreStock(produit, qte);
		this.dispose();
	}

}
