package application;

import application.login.gui.LoginPanel;
import application.veterinarian.RedirectionPanel;
import application.veterinarian.fishing.CreateFishing;
import application.veterinarian.fishing.DeleteFishing;
import application.veterinarian.fishing.FishingActions;
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

    public void showRedirectionPanel() {
        setSize(new Dimension(400, 400));
        setLocationRelativeTo(null);
        getContentPane().removeAll();
        getContentPane().add(new RedirectionPanel(this));
        revalidate();
        repaint();
    }

    public void showFishingActions() {
        setSize(new Dimension(400, 400));
        setLocationRelativeTo(null);
        getContentPane().removeAll();
        getContentPane().add(new FishingActions(this));
        revalidate();
        repaint();
    }

    public void showCreateFishing() {
        setSize(new Dimension(450, 350));
        setLocationRelativeTo(null);
        getContentPane().removeAll();
        getContentPane().add(new CreateFishing(this, databaseConnection));
        revalidate();
        repaint();
    }

    public void showDeleteFishing() {
        setSize(new Dimension(450, 350));
        setLocationRelativeTo(null);
        getContentPane().removeAll();
        getContentPane().add(new DeleteFishing(this, databaseConnection));
        revalidate();
        repaint();
    }
}
