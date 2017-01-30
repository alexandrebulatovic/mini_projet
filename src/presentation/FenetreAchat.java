package presentation;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import application.ControleurAchatVente;
import application.ControleurCatalogue;
import useful.FieldsKeyAdapter;



public class FenetreAchat extends JFrame implements ActionListener {

	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	private ControleurAchatVente cav;
	FieldsKeyAdapter intKey = new FieldsKeyAdapter("int");
	FieldsKeyAdapter stringKey = new FieldsKeyAdapter("String");
	public FenetreAchat(ControleurAchatVente cav, String[] lesProduits) {

		this.cav = cav;
		setTitle("Achat");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAchat = new JButton("Achat");
		txtQuantite = new JTextField(5);
		this.txtQuantite.addKeyListener(intKey);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantité achetée"));
		contentPane.add(txtQuantite);
		contentPane.add(btAchat);

		btAchat.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String produit = this.combo.getSelectedItem().toString();
		int qte = Integer.parseInt(this.txtQuantite.getText());
		this.cav.acheterStock(produit, qte);
		this.dispose();
	}

}
