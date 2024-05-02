package application.peseur_marqueur.functions;

import application.peseur_marqueur.BacsInfo;
import database.DatabaseConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BacsManager {
    private DatabaseConnection databaseConnection;

    public BacsManager(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public List<BacsInfo> getAllBacs(int index) {
        Connection connection = databaseConnection.getConnection();
        List<BacsInfo> bacsList = new ArrayList<>();

        try {
            String req = "SELECT bac.id, bateau.nom, bac.datePeche, typebac.tare FROM bac JOIN bateau ON bac.idBateau = bateau.id JOIN typebac ON bac.idTypeBac = typebac.id WHERE bac.id = ?";
            PreparedStatement statement = connection.prepareStatement(req);
            statement.setInt(1, index);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int bacID = resultSet.getInt("id");
                String name = "Bac " + bacID;
                String bateauNom = resultSet.getString("nom");
                Date datePeche = resultSet.getDate("datePeche");
                float tare = resultSet.getFloat("tare");

                BacsInfo bac = new BacsInfo(name, bateauNom, datePeche, tare, bacID);
                bacsList.add(bac);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bacsList;
    }

    public static void changeTare(DatabaseConnection databaseConnection, int value, int index) {
        Connection connection = databaseConnection.getConnection();

        if (index != 0) {
            try {
                String req = "UPDATE bac SET idTypeBac = ? WHERE id = ?";
                PreparedStatement statement = connection.prepareStatement(req);
                statement.setInt(1, value);
                statement.setInt(2, index);
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "La tare du bac " + index + " vient d'être changée avec succès.");
                } else {
                    JOptionPane.showMessageDialog(null, "Échec du changement de la tare");
                }
                statement.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'exécution de la requête SQL : " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Aucun bac sélectionné.");
        }
    }

    public static String getTare(DatabaseConnection databaseConnection, int index) {
        Connection connection = databaseConnection.getConnection();
        String tare = null;

        try {
            String req = "SELECT typebac.tare FROM bac JOIN typebac ON bac.idTypeBac = typebac.id WHERE bac.id = ?";
            PreparedStatement statement = connection.prepareStatement(req);
            statement.setInt(1, index);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                float tareValue = resultSet.getFloat("tare");
                tare = String.valueOf(tareValue);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tare;
    }
}
