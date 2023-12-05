package application.veterinaire.bac.gui;

import application.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionsBac extends JPanel {
    public ActionsBac(Application application) {
        setLayout(null);

        JLabel title = new JLabel("Gestion des bacs");
        title.setFont(new Font("Dialog", Font.BOLD, 25));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(50, 35, 300, 30);
        add(title);

        JButton createFishing = new JButton("Créer un bac");
        createFishing.setFont(new Font("Dialog", Font.PLAIN, 12));
        createFishing.setBounds(100, 100, 200, 30);
        createFishing.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                application.showCreerBac();
            }
        });
        add(createFishing);

        JButton deleteFishing = new JButton("Supprimer un bac");
        deleteFishing.setFont(new Font("Dialog", Font.PLAIN, 12));
        deleteFishing.setBounds(100, 150, 200, 30);
        deleteFishing.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                application.showSelectionSuppressionBac();
            }
        });
        add(deleteFishing);

        JButton editFishing = new JButton("Modifier un bac");
        editFishing.setFont(new Font("Dialog", Font.PLAIN, 12));
        editFishing.setBounds(100, 200, 200, 30);
        editFishing.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        add(editFishing);

        JButton backButton = new JButton("Retour");
        backButton.setBounds(155, 300, 90, 25);
        backButton.setFont(new Font("Dialog", Font.PLAIN, 12));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                application.showRedirectionPanel();
            }
        });
        add(backButton);
    }
}
