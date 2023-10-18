package application;

import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Application extends JFrame {
    private DatabaseConnection databaseConnection;

    public Application() throws SQLException {
        setTitle("Criée");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(400, 400));
        setResizable(false);
        setLocationRelativeTo(null);

        databaseConnection = new DatabaseConnection("jdbc:mysql://localhost", "root", "", "bdd_criee");
    }
}
