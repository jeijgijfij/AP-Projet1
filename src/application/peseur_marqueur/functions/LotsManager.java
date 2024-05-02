package application.peseur_marqueur.functions;

import application.peseur_marqueur.LotsInfo;
import database.DatabaseConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LotsManager {
    private DatabaseConnection databaseConnection;

    public LotsManager(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public List<LotsInfo> getAllLots() {
        Connection connection = databaseConnection.getConnection();
        List<LotsInfo> lotsList = new ArrayList<>();

        try {
            String req = "SELECT id FROM lot";
            PreparedStatement statement = connection.prepareStatement(req);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int lotID = resultSet.getInt("id");

                LotsInfo lot = new LotsInfo("Lot " + lotID, lotID);
                lotsList.add(lot);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lotsList;
    }

    public static void changeState(DatabaseConnection databaseConnection, int selectedIndex) {
        Connection connection = databaseConnection.getConnection();

        if (selectedIndex != 0) {
            String req = "UPDATE lot SET validationLot = NOT validationLot WHERE idLot = ?";

            try {
                PreparedStatement statement = connection.prepareStatement(req);
                statement.setInt(1, selectedIndex);
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "L'état du lot " + selectedIndex + " vient d'être changé avec succès.");
                } else {
                    JOptionPane.showMessageDialog(null, "Échec du changement d'état du lot " + selectedIndex);
                }
                statement.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'exécution de la requête SQL : " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Aucun lot sélectionné.");
        }
    }

    public static String getNbBac(DatabaseConnection databaseConnection, int index) {
        Connection connection = databaseConnection.getConnection();
        String nombreDeBacs = null;

        try {
            String req = "SELECT COUNT(*) AS nombre_de_bacs FROM bac WHERE idLot = ?";
            PreparedStatement statement = connection.prepareStatement(req);
            statement.setInt(1, index);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                nombreDeBacs = resultSet.getString("nombre_de_bacs");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreDeBacs;
    }

    public static String getState(DatabaseConnection databaseConnection, int index) {
        Connection connection = databaseConnection.getConnection();
        String state = null;

        try {
            String req = "SELECT validationLot FROM lot WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(req);
            statement.setInt(1, index);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                boolean validationLot = resultSet.getBoolean("validationLot");
                state = (validationLot) ? "Validé" : "Non validé";
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return state;
    }
}
