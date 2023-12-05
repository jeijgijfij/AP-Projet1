package application;

import application.login.gui.LoginPanel;
import application.veterinaire.RedirectionPanel;
import application.veterinaire.bac.gui.*;
import application.veterinaire.peche.CreerPeche;
import application.veterinaire.peche.SupprimerPeche;
import application.veterinaire.peche.ActionsPeche;
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
        getContentPane().add(new ActionsPeche(this));
        revalidate();
        repaint();
    }

    public void showCreateFishing() {
        setSize(new Dimension(450, 350));
        setLocationRelativeTo(null);
        getContentPane().removeAll();
        getContentPane().add(new CreerPeche(this, databaseConnection));
        revalidate();
        repaint();
    }

    public void showDeleteFishing() {
        setSize(new Dimension(450, 350));
        setLocationRelativeTo(null);
        getContentPane().removeAll();
        getContentPane().add(new SupprimerPeche(this, databaseConnection));
        revalidate();
        repaint();
    }

    public void showCreerBac() {
        setSize(new Dimension(1280,720));
        setLocationRelativeTo(null);
        getContentPane().removeAll();
        getContentPane().add(new creerBacPanel(this, databaseConnection));
        revalidate();
        repaint();
    }

    public void showSelectionnerLot() {
        setSize(new Dimension(1280,720));
        setLocationRelativeTo(null);
        getContentPane().removeAll();
        getContentPane().add(new selectionnerLotPanel(this, databaseConnection));
        revalidate();
        repaint();
    }

    public void showSelectionnerTypeBacPanel() {
        setSize(new Dimension(1280,720));
        setLocationRelativeTo(null);
        getContentPane().removeAll();
        getContentPane().add(new selectionnerTypeBacPanel(this, databaseConnection));
        revalidate();
        repaint();
    }

    public void showValidationCreationBacPanel() {
        setSize(new Dimension(1280,720));
        setLocationRelativeTo(null);
        getContentPane().removeAll();
        getContentPane().add(new validationCreationBacPanel(this, databaseConnection));
        revalidate();
        repaint();
    }

    public void showActionsBac() {
        setSize(new Dimension(400, 400));
        setLocationRelativeTo(null);
        getContentPane().removeAll();
        getContentPane().add(new ActionsBac(this));
        revalidate();
        repaint();
    }

    public void showSelectionSuppressionBac() {
        setSize(new Dimension(1280,720));
        setLocationRelativeTo(null);
        getContentPane().removeAll();
        getContentPane().add(new selectionSuppressionBacPanel(this, databaseConnection));
        revalidate();
        repaint();
    }
}
