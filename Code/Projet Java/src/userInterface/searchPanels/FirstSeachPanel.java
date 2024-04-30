package userInterface.searchPanels;

import controller.ApplicationController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FirstSeachPanel extends JPanel {
    private ApplicationController controller;
    private JPanel searchPanel, valuesPanel, buttonPanel;
    private JLabel cellLabel, birthdateLabel;
    private JTextField cellTextField, birthdateTextField;
    private JButton searchButton;
    private JTable table;
    private AllAgentsLanguagesModel model;

    public FirstSeachPanel(){
        this.controller = new ApplicationController();

        this.setLayout(new BorderLayout());

        ButtonListener buttonListener = new ButtonListener();

        searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(2, 1));
        this.add(searchPanel, BorderLayout.NORTH);

        valuesPanel = new JPanel();
        valuesPanel.setLayout(new GridLayout(2, 2));

        cellLabel = new JLabel("Nom de la cellule : ");
        cellLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        cellTextField = new JTextField(10);
        birthdateLabel = new JLabel("Date de naissance maximale : ");
        birthdateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        birthdateTextField = new JTextField(10);
        valuesPanel.add(cellLabel);
        valuesPanel.add(cellTextField);
        valuesPanel.add(birthdateLabel);
        valuesPanel.add(birthdateTextField);

        searchPanel.add(valuesPanel);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        searchButton = new JButton("Rechercher");
        searchButton.addActionListener(buttonListener);
        buttonPanel.add(searchButton);

        searchPanel.add(buttonPanel);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            try{
                model = new AllAgentsLanguagesModel(controller.getAgentsLanguages(cellTextField.getText(), LocalDate.parse(birthdateTextField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
                table = new JTable(model);

                JScrollPane scrollPane = new JScrollPane(table);
                FirstSeachPanel.this.add(scrollPane, BorderLayout.CENTER);

                //Centrer le texte dans les cellules
                DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
                custom.setHorizontalAlignment(JLabel.CENTER);
                for(int i = 0; i < table.getColumnCount(); i++){
                    table.getColumnModel().getColumn(i).setCellRenderer(custom);
                }

                FirstSeachPanel.this.validate();
                FirstSeachPanel.this.repaint();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null, "Le code de la mission est incorrect !", "Mission inconnue", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
