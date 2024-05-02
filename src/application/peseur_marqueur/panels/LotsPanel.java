package application.peseur_marqueur.panels;

import application.Application;
import application.peseur_marqueur.LotsInfo;
import application.peseur_marqueur.functions.LotsManager;
import database.DatabaseConnection;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.List;

public class LotsPanel extends JPanel {
    private DatabaseConnection databaseConnection;
    private DefaultListModel<String> lotsListModel;
    private JList<String> lotsList;

    public LotsPanel(Application application, DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;

        setBounds(100, 100, 450, 300);
        setSize(new Dimension(1280, 720));

        setLayout(null);

        lotsListModel = new DefaultListModel<>();
        lotsList = new JList<>(lotsListModel);
        JScrollPane scrollPane = new JScrollPane(lotsList);
        scrollPane.setFont(new Font("Dialog", Font.PLAIN, 12));
        scrollPane.setBounds(100, 100, 300, 500);
        add(scrollPane);

        LotsManager lotsManager = new LotsManager(databaseConnection);
        List<LotsInfo> lots = lotsManager.getAllLots();

        for (LotsInfo lot : lots) {
            lotsListModel.addElement(lot.getName());
        }

        JLabel lotsLabel = new JLabel("Lots");
        lotsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lotsLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        lotsLabel.setBounds(175, 50, 150, 25);
        add(lotsLabel);

        JLabel infoNomLabel = new JLabel("");
        infoNomLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        infoNomLabel.setBounds(875, 150, 150, 14);
        add(infoNomLabel);

        JLabel infoNbBacLabel = new JLabel("");
        infoNbBacLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        infoNbBacLabel.setBounds(875, 200, 150, 14);
        add(infoNbBacLabel);

        JLabel infoEtatLabel = new JLabel("");
        infoEtatLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        infoEtatLabel.setBounds(875, 250, 150, 14);
        add(infoEtatLabel);

        JButton validerLotButton = new JButton("Changer l'état du lot");
        validerLotButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = lotsList.getSelectedIndex() + 1;

                LotsManager.changeState(databaseConnection, selectedIndex);
                infoEtatLabel.setText(LotsManager.getState(databaseConnection, selectedIndex));
            }
        });
        validerLotButton.setBounds(600, 450, 190, 30);
        add(validerLotButton);

        JButton voirBacsButton = new JButton("Voir les bacs");
        voirBacsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = lotsList.getSelectedIndex() + 1;
                if (selectedIndex != 0) {
                    application.showBacsPanel(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(null, "Aucun lot sélectionné.");
                }
            }
        });
        voirBacsButton.setBounds(985, 450, 190, 30);
        add(voirBacsButton);

        JLabel informationsLabel = new JLabel("Informations");
        informationsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        informationsLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        informationsLabel.setBounds(800, 50, 150, 25);
        add(informationsLabel);

        JLabel nbBacLabel = new JLabel("Nombre de bacs:");
        nbBacLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        nbBacLabel.setBounds(680, 200, 110, 14);
        add(nbBacLabel);

        JLabel nomBacLabel = new JLabel("Nom:");
        nomBacLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        nomBacLabel.setBounds(680, 150, 46, 14);
        add(nomBacLabel);

        JLabel etatLotLabel = new JLabel("Etat du lot:");
        etatLotLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        etatLotLabel.setBounds(680, 250, 74, 14);
        add(etatLotLabel);

        JSeparator separator = new JSeparator();
        separator.setBounds(50, 30, 400, 600);
        add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(600, 30, 575, 270);
        add(separator_1);

        lotsList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (lotsList.getSelectedIndex() != -1) {
                        int index = lotsList.getSelectedIndex();

                        infoNomLabel.setText(lotsListModel.getElementAt(index));
                        infoNbBacLabel.setText(LotsManager.getNbBac(databaseConnection, index + 1));
                        infoEtatLabel.setText(LotsManager.getState(databaseConnection, index + 1));

                        infoNomLabel.setVisible(true);
                        infoNbBacLabel.setVisible(true);
                        infoEtatLabel.setVisible(true);
                    } else {

                        infoNomLabel.setVisible(false);
                        infoNbBacLabel.setVisible(false);
                        infoEtatLabel.setVisible(false);
                    }
                }
            }
        });
    }
}
