package application.login.gui;

import application.Application;
import application.login.functional.LoginManager;
import database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginPanel extends JPanel {
    private DatabaseConnection databaseConnection;
    private LoginManager loginManager;

    public LoginPanel(Application application, DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
        loginManager = new LoginManager(databaseConnection);

        setLayout(null);

        JLabel connectionLabel = new JLabel("Connexion");
        connectionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        connectionLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        connectionLabel.setBounds(165, 50, 150, 25);
        add(connectionLabel);

        BackgroundPanel logoPanel = new BackgroundPanel("res/images/criee_logo.png");
        logoPanel.setBounds(480, 0, 480, 540);
        add(logoPanel);

        JLabel usernameLabel = new JLabel("Nom d'utilisateur");
        usernameLabel.setFont(new Font("Dialog", Font.ITALIC, 11));
        usernameLabel.setBounds(90, 150, 85, 15);
        add(usernameLabel);
        JTextField usernameField = new JTextField(50);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 12));
        usernameField.setBounds(90, 165, 300, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Mot de passe");
        passwordLabel.setFont(new Font("Dialog", Font.ITALIC, 11));
        passwordLabel.setBounds(90, 235, 85, 15);
        add(passwordLabel);
        JPasswordField passwordField = new JPasswordField(50);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 12));
        passwordField.setBounds(90, 250, 300, 25);
        JCheckBox displayPassword = new JCheckBox("Afficher le mot de passe");
        displayPassword.setFont(new Font("Dialog", Font.PLAIN, 11));
        displayPassword.setBounds(87, 280, 145, 15);
        displayPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox checkBox = (JCheckBox) e.getSource();
                passwordField.setEchoChar(checkBox.isSelected() ? '\0' : '•');
            }
        });
        add(passwordField);
        add(displayPassword);

        JButton loginButton = new JButton("Se connecter");
        loginButton.setFont(new Font("Dialog", Font.PLAIN, 12));
        loginButton.setBounds(165, 350, 150, 30);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = usernameField.getText();
                String enteredPassword = new String(passwordField.getPassword());
                int role = 0;

                if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(application, "Veuillez remplir tous les champs.");
                    return;
                }

                try {
                    role = loginManager.authenticateUser(databaseConnection, enteredUsername, enteredPassword);

                    switch (role) {
                        case 0:
                            JOptionPane.showMessageDialog(application, "Les identifiants saisis sont invalides", "Erreur d'authentification", JOptionPane.ERROR_MESSAGE);
                            break;
                        case 1:
                            application.showRedirectionPanel();
                            break;
                        case 2:
                            // Appeler le panel Peseur Marqueur
                            break;
                        case 3:
                            // Appeler le panel Directeur des ventes
                            break;
                    }
                } catch (SQLException exception) {
                    JOptionPane.showMessageDialog(application, "Erreur avec la base de données", "Erreur d'authentification", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(loginButton);
    }
}
