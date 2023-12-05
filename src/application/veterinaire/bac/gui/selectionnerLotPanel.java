package application.veterinaire.bac.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import application.Application;
import database.DatabaseConnection;
import application.veterinaire.bac.functional.creerBac;

public class selectionnerLotPanel extends JPanel {
	private DatabaseConnection databaseConnection;

	public selectionnerLotPanel(Application application, DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
		setLayout(null);
		
		//Titre de la fenetre
		JLabel lblNewLabel = new JLabel("CREER UN BAC");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel.setForeground(UIManager.getColor("Button.darkShadow"));
		lblNewLabel.setBounds(10, 11, 202, 50);
		add(lblNewLabel);
		
		//Element de mise en page
				JLabel titre_pannel = new JLabel("SELECTIONNEZ LE LOT ASSOCIE");
				titre_pannel.setFont(new Font("Dialog", Font.BOLD, 15));
				titre_pannel.setForeground(new Color(128, 0, 128));
				titre_pannel.setBounds(456, 11, 391, 56);
				add(titre_pannel);
				
				//On definit une taille de fenetre non changeable par l'utilisateur
				
				//Informations du lot
				JLabel Id_lot = new JLabel("Identifiant du lot : "+"");
				Id_lot.setFont(new Font("Dialog", Font.PLAIN, 25));
				Id_lot.setBounds(650, 107, 450, 50);
				add(Id_lot);
				
				JLabel Bateau_sel = new JLabel("Bateau sélectionné : "+"");
				Bateau_sel.setFont(new Font("Dialog", Font.PLAIN, 25));
				Bateau_sel.setBounds(650, 154, 450, 50);
				add(Bateau_sel);
				
				JLabel Peche_jour = new JLabel("Pêche du jour sélectionnée : "+"");
				Peche_jour.setFont(new Font("Dialog", Font.PLAIN, 25));
				Peche_jour.setBounds(650, 215, 507, 50);
				add(Peche_jour);
				
				JLabel Espece_poisson = new JLabel("Espèce de Poisson : "+"");
				Espece_poisson.setFont(new Font("Dialog", Font.PLAIN, 25));
				Espece_poisson.setBounds(650, 276, 450, 50);
				add(Espece_poisson);
				
				JLabel Taille = new JLabel("Taille du lot : "+"");
				Taille.setFont(new Font("Dialog", Font.PLAIN, 25));
				Taille.setBounds(650, 459, 450, 50);
				add(Taille);
				
				JLabel Presentation = new JLabel("Presentation du lot : "+"");
				Presentation.setFont(new Font("Dialog", Font.PLAIN, 25));
				Presentation.setBounds(650, 520, 450, 50);
				add(Presentation);
				
				JLabel Qualite = new JLabel("Qualité du lot :"+"");
				Qualite.setFont(new Font("Dialog", Font.PLAIN, 25));
				Qualite.setBounds(650, 398, 450, 50);
				add(Qualite);
				
				JLabel Poids = new JLabel("Poids du lot :"+"");
				Poids.setFont(new Font("Dialog", Font.PLAIN, 25));
				Poids.setBounds(650, 337, 450, 50);
				add(Poids);
		
		//Liste des lots dans l'interface
		DefaultListModel JlistModel = creerBac.Jliste_Lot(databaseConnection);
		JList<String> list = new JList(JlistModel);
		list.setBounds(283, 107, 284, 463);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setVisibleRowCount(-1);
		list.addListSelectionListener(new ListSelectionListener() {
			     public void valueChanged(ListSelectionEvent arg0) {
			    	 Id_lot.setText("Identifiant du lot : " +creerBac.liste_info_lot(databaseConnection,
			    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getId());   
			    	 Bateau_sel.setText("Bateau sélectionné : " +creerBac.liste_info_lot(databaseConnection,
					    	 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getBateau()); 
			    	 Peche_jour.setText("Pêche du jour sélectionnée : " +creerBac.liste_info_lot(databaseConnection,
			    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getDatePeche());
			    	 Espece_poisson.setText("Espèce de Poisson : " +creerBac.liste_info_lot(databaseConnection,
			    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getEspece());
			    	 Presentation.setText("Taille du lot : " +creerBac.liste_info_lot(databaseConnection,
			    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getTaille());
			    	 Qualite.setText("Qualité du lot : " +creerBac.liste_info_lot(databaseConnection,
			    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getQualite());
			    	 Presentation.setText("Presentation du lot : " +creerBac.liste_info_lot(databaseConnection,
			    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getPresentation());
			    	 Poids.setText("Poids du lot : " +creerBac.liste_info_lot(databaseConnection,
			    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getPoids());
			     }});
		add(list);
		
		//Bouton de validation d'ajout d'un lot
		JButton confirmButton = new JButton("VALIDER");
		confirmButton.setBackground(new Color(128, 0, 128));
		confirmButton.setForeground(new Color(255, 255, 255));
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//On recupère la valeur selectionnée
				String selectedListValue = list.getSelectedValue();
				creerBacPanel.bac_cree.setLot(selectedListValue);
				application.showSelectionnerTypeBacPanel();
			}
		});
		confirmButton.setBounds(502, 600, 260, 50);
		add(confirmButton);
		
		//Bouton de retour au menu precedent
		JButton backButton = new JButton("RETOUR");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				application.showCreerBac();
			}
		});
		backButton.setForeground(new Color(255, 255, 255));
		backButton.setBackground(new Color(204, 0, 0));
		backButton.setBounds(1111, 17, 127, 44);
		add(backButton);
		
	}

}
