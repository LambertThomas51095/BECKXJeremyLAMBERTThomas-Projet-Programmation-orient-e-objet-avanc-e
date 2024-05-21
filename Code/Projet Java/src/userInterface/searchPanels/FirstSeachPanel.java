package userInterface.searchPanels;

import controller.ApplicationController;
import model.Cell;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FirstSeachPanel extends JPanel {
    private ApplicationController controller;
    private JPanel searchPanel, valuesPanel, buttonPanel;
    private JLabel cellLabel, birthdateLabel;
    private JComboBox cellComboBox;
    private JSpinner dateSpinner;
    private JSpinner.DateEditor editor;
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
        cellComboBox = new JComboBox(getCellNames());
        birthdateLabel = new JLabel("Date de naissance maximale : ");
        birthdateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        Date today = new Date();
        dateSpinner = new JSpinner(new SpinnerDateModel(today, null, today, Calendar.MONTH));
        editor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(editor);

        valuesPanel.add(cellLabel);
        valuesPanel.add(cellComboBox);
        valuesPanel.add(birthdateLabel);
        valuesPanel.add(dateSpinner);

        searchPanel.add(valuesPanel);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        searchButton = new JButton("Rechercher");
        searchButton.addActionListener(buttonListener);
        buttonPanel.add(searchButton);

        searchPanel.add(buttonPanel);
    }

    public String[] getCellNames(){
        try{
            ArrayList<Cell> cells = controller.getAllCells();
            String [] cellNames = new String[cells.size()];
            for(Integer iCell = 0; iCell < cells.size(); iCell++){
                cellNames[iCell] = cells.get(iCell).getName();
            }

            return cellNames;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                JFormattedTextField dateText = ((JSpinner.DefaultEditor)editor).getTextField();
                LocalDate date = LocalDate.parse(dateText.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                model = new AllAgentsLanguagesModel(controller.getAgentsLanguages((String)cellComboBox.getSelectedItem(), date));
                table = new JTable(model);
                if(FirstSeachPanel.this.getComponentCount() == 2){
                    FirstSeachPanel.this.remove(1);
                }
                JScrollPane scrollPane = new JScrollPane(table);
                FirstSeachPanel.this.add(scrollPane, BorderLayout.CENTER);

                //Centrer le texte dans les cellules
                DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
                custom.setHorizontalAlignment(JLabel.CENTER);
                for(Integer i = 0; i < table.getColumnCount(); i++){
                    table.getColumnModel().getColumn(i).setCellRenderer(custom);
                }

                FirstSeachPanel.this.validate();
                FirstSeachPanel.this.repaint();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null, "Une erreur avec la base de donnée est survenue.\nVeuillez nous excuser.\nErreur : " + exception.getMessage(), "Erreur de base de données", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
