package application.veterinaire.bac.gui;

import application.Application;
import database.DatabaseConnection;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class validationCreationBacPanel extends JPanel {
	private DatabaseConnection databaseConnection;

	public validationCreationBacPanel(Application application, DatabaseConnection databaseConnection) {
		this.databaseConnection = databaseConnection;
		setLayout(null);

		//Titre de la fenêtre
		JLabel lblNewLabel = new JLabel("CREER UN BAC");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel.setForeground(UIManager.getColor("Button.darkShadow"));
		lblNewLabel.setBounds(10, 11, 202, 50);
		add(lblNewLabel);

		JLabel titre_pannel = new JLabel("VOTRE BAC A BIEN ETE CREE");
		titre_pannel.setFont(new Font("Dialog", Font.BOLD, 15));
		titre_pannel.setForeground(new Color(128, 0, 128));
		titre_pannel.setBounds(436, 11, 391, 56);
		add(titre_pannel);

		//Bouton de validation d'ajout d'un bac
		JButton confirmButton = new JButton("CONFIRMER");
		confirmButton.setBackground(new Color(128, 0, 128));
		confirmButton.setForeground(new Color(255, 255, 255));
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				application.showActionsBac();
			}
		});
		confirmButton.setBounds(502, 600, 260, 50);
		add(confirmButton);

		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
		String strDate = dateFormat.format(date);  

		JLabel Bateau_sel = new JLabel("Bateau sélectionné : "+creerBacPanel.bac_cree.getBateau());
		Bateau_sel.setFont(new Font("Dialog", Font.PLAIN, 25));
		Bateau_sel.setBounds(446, 203, 450, 50);
		add(Bateau_sel);

		JLabel Peche_jour = new JLabel("Pêche du jour sélectionné : "+strDate);
		Peche_jour.setFont(new Font("Dialog", Font.PLAIN, 25));
		Peche_jour.setBounds(446, 280, 507, 50);
		add(Peche_jour);

		JLabel Lot_sel = new JLabel("Lot selectionné : "+creerBacPanel.bac_cree.getLot());
		Lot_sel.setFont(new Font("Dialog", Font.PLAIN, 25));
		Lot_sel.setBounds(446, 341, 450, 50);
		add(Lot_sel);

		JLabel TypeBac_sel = new JLabel("Type de bac selectionné : "+creerBacPanel.bac_cree.getTypeBac());
		TypeBac_sel.setFont(new Font("Dialog", Font.PLAIN, 25));
		TypeBac_sel.setBounds(446, 414, 450, 50);
		add(TypeBac_sel);
	}
}
