package application;

import application.login.gui.LoginPanel;
import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Application extends JFrame {
    private DatabaseConnection databaseConnection;

    public Application() throws SQLException {
        setTitle("La Criée");
        setIconImage(new ImageIcon("res/images/criee_app.png").getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(960, 540));
        setResizable(false);
        setLocationRelativeTo(null);

        databaseConnection = new DatabaseConnection("jdbc:mysql://localhost", "root", "", "bdd_criee");

        showLoginPanel();
    }

    public void showLoginPanel() {
        getContentPane().removeAll();
        getContentPane().add(new LoginPanel(this, databaseConnection));
        revalidate();
        repaint();
    }


}
