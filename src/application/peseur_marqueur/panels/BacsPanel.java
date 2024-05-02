package application.peseur_marqueur.panels;

import application.Application;
import application.peseur_marqueur.BacsInfo;
import application.peseur_marqueur.LotsInfo;
import application.peseur_marqueur.functions.BacsManager;
import application.peseur_marqueur.functions.LotsManager;
import database.DatabaseConnection;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BacsPanel extends JPanel {
    private DatabaseConnection databaseConnection;
    private DefaultListModel<String> bacsListModel;
    private JList<String> bacsList;

    public BacsPanel(Application application, DatabaseConnection databaseConnection, int index) {
        setLayout(null);

        bacsListModel = new DefaultListModel<>();
        bacsList = new JList<>(bacsListModel);
        JScrollPane scrollPane = new JScrollPane(bacsList);
        scrollPane.setFont(new Font("Dialog", Font.PLAIN, 12));
        scrollPane.setBounds(100, 100, 300, 500);
        add(scrollPane);

        BacsManager bacsManager = new BacsManager(databaseConnection);
        List<BacsInfo> bacs = bacsManager.getAllBacs(index);

        for (BacsInfo bac : bacs) {
            bacsListModel.addElement(bac.getName());
        }

        JLabel bacLabel = new JLabel("Bacs du lot " + index);
        bacLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bacLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        bacLabel.setBounds(160, 50, 180, 25);
        add(bacLabel);

        JLabel infoBateauLabel = new JLabel("");
        infoBateauLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        infoBateauLabel.setBounds(875, 150, 150, 14);
        add(infoBateauLabel);

        JLabel infoDatePecheLabel = new JLabel("");
        infoDatePecheLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        infoDatePecheLabel.setBounds(875, 200, 150, 14);
        add(infoDatePecheLabel);

        JLabel infoPoidsBacLabel = new JLabel("");
        infoPoidsBacLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        infoPoidsBacLabel.setBounds(875, 250, 150, 14);
        add(infoPoidsBacLabel);

        JButton poids1Button = new JButton("Tare 2.5");
        poids1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = bacsList.getSelectedIndex() + 1;

                BacsManager.changeTare(databaseConnection, 1, selectedIndex);
                infoPoidsBacLabel.setText(BacsManager.getTare(databaseConnection, selectedIndex));
            }
        });
        poids1Button.setBounds(600, 450, 190, 30);
        add(poids1Button);

        JButton poids2Button = new JButton("Tare 4");
        poids2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = bacsList.getSelectedIndex() + 1;

                BacsManager.changeTare(databaseConnection, 2, selectedIndex);
                infoPoidsBacLabel.setText(BacsManager.getTare(databaseConnection, selectedIndex));
            }
        });
        poids2Button.setBounds(985, 450, 190, 30);
        add(poids2Button);

        JLabel informationsLabel = new JLabel("Informations");
        informationsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        informationsLabel.setFont(new Font("Dialog", Font.BOLD, 25));
        informationsLabel.setBounds(800, 50, 150, 25);
        add(informationsLabel);

        JLabel datePecheLabel = new JLabel("Date pêche:");
        datePecheLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        datePecheLabel.setBounds(680, 200, 110, 14);
        add(datePecheLabel);

        JLabel bateauLabel = new JLabel("Bateau:");
        bateauLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        bateauLabel.setBounds(680, 150, 46, 14);
        add(bateauLabel);

        JLabel poidsBacLabel = new JLabel("Poids du bac:");
        poidsBacLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
        poidsBacLabel.setBounds(680, 250, 90, 14);
        add(poidsBacLabel);

        JButton retourButton = new JButton("Retour");
        retourButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                application.showLotsPanel();
            }
        });
        retourButton.setBounds(10, 10, 90, 25);
        add(retourButton);

        bacsList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (bacsList.getSelectedIndex() != -1) {
                        int i = bacsList.getSelectedIndex();

                        BacsInfo element = bacs.get(i);

                        infoBateauLabel.setText(element.getBateau());
                        infoDatePecheLabel.setText(element.getDatePeche().toString());
                        infoPoidsBacLabel.setText(BacsManager.getTare(databaseConnection, i + 1));

                        infoBateauLabel.setVisible(true);
                        infoDatePecheLabel.setVisible(true);
                        infoPoidsBacLabel.setVisible(true);
                    } else {

                        infoBateauLabel.setVisible(false);
                        infoDatePecheLabel.setVisible(false);
                        infoPoidsBacLabel.setVisible(false);
                    }
                }
            }
        });
    }
}
