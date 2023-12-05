package application.veterinaire;

import application.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedirectionPanel extends JPanel {
    public RedirectionPanel(Application application) {
        setLayout(null);

        JLabel title = new JLabel("Tableau de bord");
        title.setFont(new Font("Dialog", Font.BOLD, 25));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(50, 35, 300, 30);
        add(title);

        JButton fisheriesManagement = new JButton("Gestion des pêches");
        fisheriesManagement.setFont(new Font("Dialog", Font.PLAIN, 12));
        fisheriesManagement.setBounds(125, 100, 150, 30);
        fisheriesManagement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                application.showFishingActions();
            }
        });
        add(fisheriesManagement);

        JButton batchManagement = new JButton("Gestion des lots");
        batchManagement.setFont(new Font("Dialog", Font.PLAIN, 12));
        batchManagement.setBounds(125, 150, 150, 30);
        batchManagement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        add(batchManagement);

        JButton binManagement = new JButton("Gestion des bacs");
        binManagement.setFont(new Font("Dialog", Font.PLAIN, 12));
        binManagement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                application.showActionsBac();
            }
        });
        binManagement.setBounds(125, 200, 150, 30);
        add(binManagement);
    }
}
