package application.veterinaire.bac.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import application.Application;
import application.veterinaire.bac.functional.creerBac;
import database.DatabaseConnection;

public class selectionnerTypeBacPanel extends JPanel {
	private DatabaseConnection databaseConnection;

	public selectionnerTypeBacPanel(Application application, DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
		setLayout(null);

		//Titre de la fenêtre
		JLabel lblNewLabel = new JLabel("CREER UN BAC");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel.setForeground(UIManager.getColor("Button.darkShadow"));
		lblNewLabel.setBounds(10, 11, 202, 50);
		add(lblNewLabel);
		
		JLabel Tare = new JLabel("TARE : 1");
		Tare.setFont(new Font("Dialog", Font.BOLD, 25));
		Tare.setForeground(UIManager.getColor("Button.darkShadow"));
		Tare.setBounds(609, 261, 202, 50);
		add(Tare);
		
		//On créé la comboBox qui permettra de choisir le type de bac
		JComboBox cb = new JComboBox();
		cb.setBounds(486, 270, 82, 44);
		for(String typebac : creerBac.liste_type_bac(databaseConnection)) {
            cb.addItem(typebac);
		}
		cb.addActionListener(new ActionListener() {    
			     @Override
			     public void actionPerformed(ActionEvent e) {
			        Tare.setText("TARE : " +cb.getSelectedItem().toString());    
			     }});
		add(cb);
		
		//Bouton de validation d'ajout d'un type bac
		JButton confirmButton = new JButton("VALIDER");
		confirmButton.setBackground(new Color(128, 0, 128));
		confirmButton.setForeground(new Color(255, 255, 255));
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedItem = cb.getSelectedItem().toString();
				creerBacPanel.bac_cree.setTypeBac(selectedItem);
				application.showValidationCreationBacPanel();
			}
		});
		confirmButton.setBounds(502, 600, 260, 50);
		add(confirmButton);
				
		//Bouton de retour au menu precedent
		JButton backButton = new JButton("RETOUR");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				application.showSelectionnerLot();
			}
		});
		backButton.setForeground(new Color(255, 255, 255));
		backButton.setBackground(new Color(204, 0, 0));
		backButton.setBounds(1111, 17, 127, 44);
		add(backButton);
				
		//Element de mise en page
		JLabel titre_pannel = new JLabel("SELECTIONNEZ LE TYPE DE BAC");
		titre_pannel.setFont(new Font("Dialog", Font.BOLD, 15));
		titre_pannel.setForeground(new Color(128, 0, 128));
		titre_pannel.setBounds(436, 11, 391, 56);
		add(titre_pannel);
	}
}
