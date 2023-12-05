package application.veterinaire.bac.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import application.Application;
import application.veterinaire.bac.functional.SupprimerBac;
import database.DatabaseConnection;

public class selectionSuppressionBacPanel extends JPanel {
	private static DatabaseConnection databaseConnection;

	public selectionSuppressionBacPanel(Application application, DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
		setLayout(null);
		
		//Informations du lot
		JLabel Id_bac = new JLabel("Identifiant du bac : "+"");
		Id_bac.setFont(new Font("Dialog", Font.PLAIN, 25));
		Id_bac.setBounds(650, 187, 450, 50);
		add(Id_bac);
		
		JLabel Bateau_sel = new JLabel("Bateau sélectionné : "+"");
		Bateau_sel.setFont(new Font("Dialog", Font.PLAIN, 25));
		Bateau_sel.setBounds(650, 248, 450, 50);
		add(Bateau_sel);
		
		JLabel Peche_jour = new JLabel("Pêche du jour sélectionnée : "+"");
		Peche_jour.setFont(new Font("Dialog", Font.PLAIN, 25));
		Peche_jour.setBounds(650, 309, 507, 50);
		add(Peche_jour);
		
		JLabel Id_Lot = new JLabel("Identifiant du lot associé : "+"");
		Id_Lot.setFont(new Font("Dialog", Font.PLAIN, 25));
		Id_Lot.setBounds(650, 370, 450, 50);
		add(Id_Lot);
		
		JLabel IdTypeBac = new JLabel("Type du bac : "+"");
		IdTypeBac.setFont(new Font("Dialog", Font.PLAIN, 25));
		IdTypeBac.setBounds(650, 431, 450, 50);
		add(IdTypeBac);
		
		//Titre de la fenetre
		JLabel lblNewLabel = new JLabel("SUPPRIMER UN BAC");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel.setForeground(UIManager.getColor("Button.darkShadow"));
		lblNewLabel.setBounds(10, 11, 260, 50);
		add(lblNewLabel);
		
		//Liste des bateaux dans l'interface
		DefaultListModel JlistModel = SupprimerBac.Jliste_Bac(databaseConnection);
		JList<String> list = new JList(JlistModel);
		
		list.setBounds(232, 104, 391, 471);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setVisibleRowCount(-1);
		list.addListSelectionListener(new ListSelectionListener() {
		     public void valueChanged(ListSelectionEvent arg0) {
		    	 Id_bac.setText("Identifiant du bac : " +SupprimerBac.liste_info_bac(databaseConnection,
		    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getId());   
		    	 Bateau_sel.setText("Bateau : " +SupprimerBac.liste_info_bac(databaseConnection,
				    	 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getBateau()); 
		    	 Peche_jour.setText("Pêche du jour : " +SupprimerBac.liste_info_bac(databaseConnection,
		    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getDatePeche());
		    	 Id_Lot.setText("Identifiant du lot associé : " +SupprimerBac.liste_info_bac(databaseConnection,
		    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getLot());
		    	 IdTypeBac.setText("Type de bac : " +SupprimerBac.liste_info_bac(databaseConnection,
		    			 list.getSelectedValue().toString()).get(Integer.parseInt(list.getSelectedValue().toString())-1).getTypeBac());
		     }});
		add(list);
		
		//Bouton de validation d'ajout d'un bateau
		JButton confirmButton = new JButton("VALIDER");
		confirmButton.setBackground(new Color(128, 0, 128));
		confirmButton.setForeground(new Color(255, 255, 255));
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//On recupère la valeur selectionnée
				String selectedListValue = list.getSelectedValue();
				int confirm = JOptionPane.showConfirmDialog(
						null,
						"Etes-vous sûr de vouloir supprimer ce bac ?",
						"Confirmation",
		                JOptionPane.YES_NO_OPTION,
		                JOptionPane.QUESTION_MESSAGE, null );
				if (confirm==0) {
					SupprimerBac.Supprimer_bac(databaseConnection, selectedListValue);
				}
				application.showActionsBac();
			}
		});
		confirmButton.setBounds(502, 600, 260, 50);
		add(confirmButton);
		
		//Bouton de retour au menu precedent
		JButton backButton = new JButton("RETOUR");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				application.showActionsBac();
			}
		});
		backButton.setForeground(new Color(255, 255, 255));
		backButton.setBackground(new Color(204, 0, 0));
		backButton.setBounds(1111, 17, 127, 44);
		add(backButton);
		
		//Element de mise en page
		JLabel lblNewLabel_1 = new JLabel("SELECTIONNEZ LE BAC A SUPPRIMER");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_1.setForeground(new Color(128, 0, 128));
		lblNewLabel_1.setBounds(436, 11, 391, 56);
		add(lblNewLabel_1);
	}
}
