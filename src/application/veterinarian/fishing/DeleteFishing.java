package application.veterinarian.fishing;

import application.Application;
import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteFishing extends JPanel {
    private JComboBox<String> pecheComboBox;
    private DatabaseConnection databaseConnection;

    public DeleteFishing(Application application, DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;

        setLayout(null);

        JLabel lblNewLabel = new JLabel("Suppression pêche");
        lblNewLabel.setForeground(new Color(186, 85, 211));
        lblNewLabel.setBounds(154, 32, 157, 14);
        add(lblNewLabel);
        pecheComboBox = new JComboBox<>();
        pecheComboBox.setBounds(73, 106, 300, 22);
        add(pecheComboBox);
        JButton supprimerButton = new JButton("Supprimer");
        supprimerButton.setBounds(188, 150, 112, 23);
        add(supprimerButton);
        // Charger la liste des pêches au démarrage de la fenêtre
        chargerListePeche();
        // Écouteur d'événements pour le bouton "Supprimer"
        supprimerButton.addActionListener(e -> supprimerPeche(application));
    }


    // Méthode pour charger la liste des pêches depuis la base de données
    private void chargerListePeche() {
        Connection connection = null;
        try {
            connection = databaseConnection.getConnection();
            String query = "SELECT idbateau, datePeche FROM peche";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            // Remplir la JComboBox avec les pêches
            while (resultSet.next()) {
                int idBateau = resultSet.getInt("idbateau");
                String datePeche = resultSet.getString("datePeche");
                String item = " Bateau:" + idBateau + ", Date " + datePeche;
                pecheComboBox.addItem(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    // Méthode pour supprimer la pêche sélectionnée
    private void supprimerPeche(Application application) {
        Connection connection = null;
        try {
            connection = databaseConnection.getConnection();
            // Récupérer l'ID du bateau de la pêche sélectionnée
            String selectedPeche = (String) pecheComboBox.getSelectedItem();
            String[] parts = selectedPeche.split("\\s+"); // Séparer la chaîne par les espaces
            int idBateau = Integer.parseInt(parts[1].replace("Bateau:", "").replace(",", "").trim()); // Supprimer "Bateau:" et la virgule de la valeur
            // Exécuter la requête SQL pour supprimer la pêche
            String deleteQuery = "DELETE FROM peche WHERE idbateau=?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, idBateau);
            deleteStatement.executeUpdate();
            // Rafraîchir la liste après la suppression
            pecheComboBox.removeAllItems();
            chargerListePeche();
            JOptionPane.showMessageDialog(application, "Pêche supprimée avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
            application.showRedirectionPanel();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
