package application.veterinaire.bac.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import application.Application;
import application.veterinaire.bac.functional.classes.Bac;
import application.veterinaire.bac.functional.creerBac;
import database.DatabaseConnection;

import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;

public class creerBacPanel extends JPanel {
	private JTextField textField;
	private String Bateau;
	private DatabaseConnection databaseConnection;
	//Contient les valeurs selectionnées pour la creation de bac
	public static Bac bac_cree = new Bac("","","");

	public creerBacPanel(Application application, DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
		setLayout(null);
		
		//Titre de la fenetre
		JLabel lblNewLabel = new JLabel("CREER UN BAC");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel.setForeground(UIManager.getColor("Button.darkShadow"));
		lblNewLabel.setBounds(10, 11, 202, 50);
		add(lblNewLabel);
		
		//Liste des bateaux dans l'interface
		DefaultListModel JlistModel = creerBac.Jliste_Bateau(databaseConnection);
		JList<String> list = new JList(JlistModel);
		
		list.setBounds(436, 105, 391, 471);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setVisibleRowCount(-1);
		add(list);
		
		//Bouton de validation d'ajout d'un bateau
		JButton confirmButton = new JButton("VALIDER");
		confirmButton.setBackground(new Color(128, 0, 128));
		confirmButton.setForeground(new Color(255, 255, 255));
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//On recupère la valeur selectionnée
				String selectedListValue = list.getSelectedValue();
				bac_cree.setBateau(selectedListValue);
				application.showSelectionnerLot();
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
		JLabel lblNewLabel_1 = new JLabel("SELECTIONNEZ LA PECHE DU JOUR D'UN BATEAU");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_1.setForeground(new Color(128, 0, 128));
		lblNewLabel_1.setBounds(436, 11, 391, 56);
		add(lblNewLabel_1);
		
		//On affiche la peche du jour
		//En recuperant la date systeme
	    Date date = Calendar.getInstance().getTime();  
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
	    String strDate = dateFormat.format(date);  
	    //Puis en l'affichant dans dans label 
		JLabel lblNewLabel_2 = new JLabel("Pêche du jour : "+strDate);
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(446, 78, 188, 14);
		add(lblNewLabel_2);
	}
}
