package application.veterinaire.bac.functional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import application.veterinaire.bac.functional.classes.Bateau;
import application.veterinaire.bac.functional.classes.Lot;
import application.veterinaire.bac.gui.creerBacPanel;
import database.DatabaseConnection;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class creerBac {
		
		//On cree la liste des instances de bateaux et on la renvoie
		public static List<Bateau> bateau_liste(DatabaseConnection databaseConnection) {
			Connection con = databaseConnection.getConnection();
			List<Bateau> bateau_liste = Arrays.asList();
			List<Bateau> bateau_l = new ArrayList<>(bateau_liste);
			
			try{
				java.sql.Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT nom, immatriculation FROM bateau;");
				
				while (rs.next()) {
					Bateau bateau = new Bateau(rs.getString("nom"),rs.getString("immatriculation"));
					bateau_l.add(bateau);
					}
				
			}catch (SQLException e ){
				JOptionPane.showMessageDialog(null, "Problème d'actualisation de la table'."+e, "Erreur", JOptionPane.INFORMATION_MESSAGE);
			}
			return  bateau_l;
		}
		
		//On cree la liste des bateaux affiches dans les tableaux et on la renvoie
		public static DefaultListModel<String> Jliste_Bateau(DatabaseConnection databaseConnection){
			DefaultListModel<String> Jliste_Bateau = new DefaultListModel<>();
			List<Bateau>bateau_liste = bateau_liste(databaseConnection);
			Connection con = databaseConnection.getConnection();
			
			for (Bateau bateau : bateau_liste) {
				Jliste_Bateau.addElement(bateau.getNom());
			}
			return Jliste_Bateau;
		}
		
		//On cree la liste des tableaux affiches dans les bateaux et on la renvoie
		public static List<String> liste_Lot(DatabaseConnection databaseConnection) {
			Connection con = databaseConnection.getConnection();
			List<String> liste_l = Arrays.asList();
			List<String> liste_lot = new ArrayList<>(liste_l);
			
			try{
				java.sql.Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT id FROM lot;");
				
				while (rs.next()) {
					liste_lot.add(rs.getString("id"));
					}
				
			}catch (SQLException e ){
				JOptionPane.showMessageDialog(null, "Problème d'actualisation de la table'."+e, "Erreur", JOptionPane.INFORMATION_MESSAGE);
			}
			return  liste_lot;
		}
		//On créé un listModel a partir de la liste des lots et on le renvoie
		public static DefaultListModel<String> Jliste_Lot(DatabaseConnection databaseConnection){
			DefaultListModel<String> Jliste_Lot = new DefaultListModel<>();
			List<String>lot_liste = liste_Lot(databaseConnection);
			Connection con = databaseConnection.getConnection();
			
			for (String lot : lot_liste) {
				Jliste_Lot.addElement(lot);
			}
			return Jliste_Lot;
		}
		//On récupere les informations des lots
		public static List<Lot> liste_info_lot(DatabaseConnection databaseConnection, String id) {
			Connection con = databaseConnection.getConnection();
			List<Lot> liste_i = Arrays.asList();
			List<Lot> liste_info = new ArrayList<>(liste_i);
			
			try{
				java.sql.Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT lot.id,"
						+ "	bateau.nom,"
						+ "	lot.datePeche,"
						+ "	espece.nom,"
						+ "	taille.specification,"
						+ "	presentation.libelle,"
						+ "	qualite.libelle,"
						+ "	poidsBrutLot"
						+ " FROM lot"
						+ " JOIN bateau ON lot.idbateau = bateau.id"
						+ " JOIN espece ON lot.idespece = espece.id"
						+ " JOIN taille ON lot.idtaille = taille.id"
						+ " JOIN presentation ON lot.idpresentation = presentation.id"
						+ " JOIN qualite ON lot.idqualite = qualite.id"
						+ " WHERE lot.id="+id+";");
				while (rs.next()) {
					Lot lot = new Lot(
							rs.getString("lot.id"),
							rs.getString("bateau.nom"),
							rs.getString("lot.datePeche"),
							rs.getString("espece.nom"),
							rs.getString("taille.specification"),
							rs.getString("presentation.libelle"),
							rs.getString("qualite.libelle"),
							rs.getString("poidsBrutLot"));
					liste_info.add(lot);
					}
				
			}catch (SQLException e ){
				JOptionPane.showMessageDialog(null, "Problème d'actualisation de la table'."+e, "Erreur", JOptionPane.INFORMATION_MESSAGE);
			}
			return  liste_info;
		}
		
		//On créé la liste des type de bac et on la renvoie
		public static List<String> liste_type_bac(DatabaseConnection databaseConnection) {
			Connection con = databaseConnection.getConnection();
			List<String> liste_t = Arrays.asList();
			List<String> liste_type_bac = new ArrayList<>(liste_t);
			try{
				java.sql.Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT id FROM typebac;");	
				while (rs.next()) {
					liste_type_bac.add(rs.getString("id"));
					}
			}catch (SQLException e ){
				JOptionPane.showMessageDialog(null, "Problème d'actualisation de la table'."+e, "Erreur", JOptionPane.INFORMATION_MESSAGE);
			}
			return  liste_type_bac;
		}
		
		//On créé le bac via les informations sélectionnées
		public static void validation(DatabaseConnection databaseConnection) {
			Connection con = databaseConnection.getConnection();
			Date date = Calendar.getInstance().getTime();  
		    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
		    String strDate = dateFormat.format(date);
			try{
				java.sql.Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("INSERT INTO bac VALUES('"
				+ creerBacPanel.bac_cree.getBateau()+","
				+strDate+","
				+ creerBacPanel.bac_cree.getLot()+","
				+ creerBacPanel.bac_cree.getTypeBac()+"')");
				
			}catch (SQLException e ){
				JOptionPane.showMessageDialog(null, "Problème d'actualisation de la table'."+e, "Erreur", JOptionPane.INFORMATION_MESSAGE);
			}
		}
}
