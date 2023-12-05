package application.veterinaire.bac.functional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import application.veterinaire.bac.functional.classes.Bac;
import database.DatabaseConnection;

public class SupprimerBac {
	public static List<String> liste_bac(DatabaseConnection databaseConnection) {
		Connection con = databaseConnection.getConnection();
		List<String> liste_b = Arrays.asList();
		List<String> liste_bac = new ArrayList<>(liste_b);
		
		try{
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT id FROM bac;");

			while (rs.next()) {
				liste_bac.add(rs.getString("id"));
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Problème d'actualisation de la table'."+e, "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		return  liste_bac;
	}

	public static DefaultListModel<String> Jliste_Bac(DatabaseConnection databaseConnection){
		DefaultListModel<String> Jliste_bac = new DefaultListModel<>();
		List<String>bac_liste = liste_bac(databaseConnection);
		Connection con = databaseConnection.getConnection();
		
		for (String lot : bac_liste) {
			Jliste_bac.addElement(lot);
		}
		return Jliste_bac;
	}

	public static void Supprimer_bac(DatabaseConnection databaseConnection, String idbac) {
		Connection con = databaseConnection.getConnection();
		try {
			String query = "DELETE FROM bac WHERE id = ?";
			try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
				preparedStatement.setString(1, idbac);
				int rowsAffected = preparedStatement.executeUpdate();

				if (rowsAffected > 0) {
					JOptionPane.showMessageDialog(null, "Bac supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Aucune ligne correspondante trouvée.", "Avertissement", JOptionPane.WARNING_MESSAGE);
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Problème d'actualisation de la table : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static List<Bac> liste_info_bac(DatabaseConnection databaseConnection, String id) {
		Connection con = databaseConnection.getConnection();
		List<Bac> liste_i = Arrays.asList();
		List<Bac> liste_info = new ArrayList<>(liste_i);
		
		try{
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT bac.id,"
					+ "	bateau.nom,"
					+ "	bac.datePeche,"
					+ "	lot.id,"
					+ "	bac.idTypeBac"
					+ " FROM bac"
					+ " JOIN bateau ON bac.idbateau = bateau.id"
					+ " JOIN lot ON bac.idlot = lot.id"
					+ " WHERE lot.id="+id+";");
			while (rs.next()) {
				Bac bac = new Bac(
						rs.getString("bateau.nom"),
						rs.getString("lot.id"),
						rs.getString("bac.idTypeBac")
						);
						bac.setId(id);
						bac.setDatePeche(rs.getString("bac.datePeche"));
				liste_info.add(bac);
				}
			
		}catch (SQLException e ){
			JOptionPane.showMessageDialog(null, "Problème d'actualisation de la table'."+e, "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		return  liste_info;
	}
}
