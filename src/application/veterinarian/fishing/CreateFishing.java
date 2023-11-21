package application.veterinarian.fishing;

import application.Application;
import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateFishing extends JPanel {
    DatabaseConnection databaseConnection;

    public CreateFishing(Application application, DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
        Connection connection = databaseConnection.getConnection();

        setLayout(null);

        JLabel titreLabel = new JLabel("Création pêche");
        titreLabel.setForeground(new Color(186, 85, 211));
        titreLabel.setBounds(167, 24, 155, 25);
        add(titreLabel);

        JLabel bateauLabel = new JLabel("Bateau:");
        bateauLabel.setBounds(43, 85, 46, 14);
        add(bateauLabel);

        JComboBox<String> bateauComboBox = new JComboBox<>();
        bateauComboBox.setBounds(185, 81, 118, 22);
        add(bateauComboBox);

        // Exécuter une requête SQL pour récupérer la liste des bateaux
        try {
            String query = "SELECT id, nom FROM bateau";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Parcourir le résultat et ajouter chaque bateau à la ComboBox
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                bateauComboBox.addItem(id + ": " + nom);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JButton validerButton = new JButton("Valider");
        validerButton.setBounds(158, 171, 89, 23);
        add(validerButton);

        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
                DateFormat.SHORT,
                DateFormat.SHORT);
        Date todaysDate = new Date();
        JLabel dateLabel = new JLabel("Date: " + shortDateFormat.format(todaysDate));

        dateLabel.setBounds(10, 236, 237, 14);
        add(dateLabel);

        // Écouteur d'événements pour le bouton "Valider"
        validerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer l'ID du bateau sélectionné
                String selectedBateau = (String) bateauComboBox.getSelectedItem();
                int idBateau = Integer.parseInt(selectedBateau.split(":")[0].trim());

                // Récupérer la date actuelle
                Date datePeche = new Date();

                // Formatter la date au format souhaité
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = dateFormat.format(datePeche);

                // Exécuter une requête SQL pour insérer une nouvelle pêche
                try {
                    String insertQuery = "INSERT INTO peche (idbateau, datePeche) VALUES (?, ?)";
                    PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                    insertStatement.setInt(1, idBateau);
                    insertStatement.setString(2, formattedDate);
                    insertStatement.executeUpdate();

                    JOptionPane.showMessageDialog(application, "Nouvelle pêche créée avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    application.showRedirectionPanel();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
