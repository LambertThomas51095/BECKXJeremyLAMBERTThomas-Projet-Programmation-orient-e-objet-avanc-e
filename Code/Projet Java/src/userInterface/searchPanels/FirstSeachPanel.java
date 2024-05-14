package userInterface.searchPanels;

import controller.ApplicationController;
import exception.AccessException;
import exception.ConnectionException;
import model.RegularExpression;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstSeachPanel extends JPanel {
    private ApplicationController controller;
    private JPanel searchPanel, valuesPanel, buttonPanel;
    private JLabel cellLabel, birthdateLabel;
    private JTextField cellTextField;
    private JButton searchButton;
    private JTable table;
    private AllAgentsLanguagesModel model;
    private JSpinner dateSpinner;

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
        Date today = new Date();
        dateSpinner = new JSpinner(new SpinnerDateModel(today, null, today, Calendar.MONTH));
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(editor);

        valuesPanel.add(cellLabel);
        valuesPanel.add(cellTextField);
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

    public String validateCellName() throws ConnectionException, AccessException {
        String cellName = cellTextField.getText();
        if(controller.getAllCells().stream().anyMatch(cell -> cell.getName().equals(cellName))){
            return cellName;
        }
        return null;
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Date bufferDate = (Date)dateSpinner.getValue();
                LocalDate date = LocalDate.of(bufferDate.getYear()+1900,bufferDate.getMonth()+1,bufferDate.getDate());
                System.out.println(date);
                String cellName = validateCellName();
                if(cellName != null){
                    model = new AllAgentsLanguagesModel(controller.getAgentsLanguages(cellName, date));
                    table = new JTable(model);
                    if(FirstSeachPanel.this.getComponentCount() == 2){
                        FirstSeachPanel.this.remove(1);
                    }
                    JScrollPane scrollPane = new JScrollPane(table);
                    FirstSeachPanel.this.add(scrollPane, BorderLayout.CENTER);

                    //Centrer le texte dans les cellules
                    DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
                    custom.setHorizontalAlignment(JLabel.CENTER);
                    for(int i = 0; i < table.getColumnCount(); i++){
                        table.getColumnModel().getColumn(i).setCellRenderer(custom);
                    }
                }else{
                    if(FirstSeachPanel.this.getComponentCount() == 2){
                        FirstSeachPanel.this.remove(1);
                    }
                    JOptionPane.showMessageDialog(null, "Une ou plusieurs données entrées sont érronées !", "Données incorrectes", JOptionPane.ERROR_MESSAGE);
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
