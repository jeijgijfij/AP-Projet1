package application.veterinarian;

import application.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class redirectionPanel extends JPanel {
    public redirectionPanel(Application application) {
        // SIZE 400*400

        setLayout(null);

        JButton fisheriesManagement = new JButton("Gestion des pêches");
        fisheriesManagement.setFont(new Font("Dialog", Font.PLAIN, 12));
        fisheriesManagement.setBounds(125, 100, 150, 30);
        fisheriesManagement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

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

            }
        });
        binManagement.setBounds(125, 200, 150, 30);
        add(binManagement);
    }
}
