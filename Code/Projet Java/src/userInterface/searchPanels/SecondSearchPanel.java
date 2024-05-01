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
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecondSearchPanel extends JPanel {
    private ApplicationController controller;
    private JPanel searchPanel, valuesPanel, buttonPanel;
    private JLabel agentNameLabel, agentFirstNameLabel, agentMatriculeLabel;
    private JTextField agentNameTextField, agentFirstNameTextField, agentMatriculeTextField;
    private JButton searchButton;
    private JTable table;
    private AllAgentMissionsModel model;

    public SecondSearchPanel(){
        this.controller = new ApplicationController();

        this.setLayout(new BorderLayout());

        ButtonListener buttonListener = new ButtonListener();

        searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(2, 1));
        this.add(searchPanel, BorderLayout.NORTH);

        valuesPanel = new JPanel();
        valuesPanel.setLayout(new GridLayout(3, 2));

        agentNameLabel = new JLabel("Nom de l'agent : ");
        agentNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        agentNameTextField = new JTextField(10);
        agentFirstNameLabel = new JLabel("Prénom de l'agent : ");
        agentFirstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        agentFirstNameTextField = new JTextField(10);
        agentMatriculeLabel = new JLabel("Matricule de l'agent (optionnel) : ");
        agentMatriculeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        agentMatriculeTextField = new JTextField(10);
        valuesPanel.add(agentNameLabel);
        valuesPanel.add(agentNameTextField);
        valuesPanel.add(agentFirstNameLabel);
        valuesPanel.add(agentFirstNameTextField);
        valuesPanel.add(agentMatriculeLabel);
        valuesPanel.add(agentMatriculeTextField);


        searchPanel.add(valuesPanel);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        searchButton = new JButton("Rechercher");
        searchButton.addActionListener(buttonListener);
        buttonPanel.add(searchButton);

        searchPanel.add(buttonPanel);
    }

    public Integer getMatricule() throws ConnectionException, AccessException{
        String matriculeText = agentMatriculeTextField.getText();
        Pattern pattern = Pattern.compile(RegularExpression.ONLY_NUMBER.toString());
        Matcher matcher = pattern.matcher(matriculeText);
        if(matcher.find()){
            Integer matricule = Integer.parseInt(matriculeText);
            if(controller.getAllPersonnalNumbers().contains(matricule)){
                return matricule;
            }
        }
        return null;
    }
    public Boolean validateInformations(Integer matricule) throws ConnectionException, AccessException{
        if(matricule == null){
            ArrayList<String> names = new ArrayList<>();
            names.add(agentNameTextField.getText());
            names.add(agentFirstNameTextField.getText());
            if(!controller.getAllAgentsName().contains(names)){ // si nom prenom good ?
                return false;
            }
        }
        return true;
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                // verif matricule et nom prénom si null ou existe pas
                Integer matricule = getMatricule();
                if(validateInformations(matricule)){
                    ArrayList<ArrayList<String>> agentMissions = controller.getAgentMissions(agentNameTextField.getText(), agentFirstNameTextField.getText(), matricule);
                    if(agentMissions.size() > 0){
                        model = new AllAgentMissionsModel(agentMissions);
                        table = new JTable(model);

                        JScrollPane scrollPane = new JScrollPane(table);
                        SecondSearchPanel.this.add(scrollPane, BorderLayout.CENTER);

                        //Centrer le texte dans les cellules
                        DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
                        custom.setHorizontalAlignment(JLabel.CENTER);
                        for(int i = 0; i < table.getColumnCount(); i++){
                            table.getColumnModel().getColumn(i).setCellRenderer(custom);
                        }
                    }else{
                        // reset le tableau si possible ?... => cas où un agent n'a pas de mission
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Au moins une des données entrées est incorrectes !\n(Veuillez privilégier le matricule)", "Données incorrecte", JOptionPane.ERROR_MESSAGE);
                }

                SecondSearchPanel.this.validate();
                SecondSearchPanel.this.repaint();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null, "Une erreur avec la base de donnée est survenue.\nVeuillez nous excuser.", "Erreur de base de données", JOptionPane.ERROR_MESSAGE);
                System.out.println(exception.getMessage());
            }
        }
    }
}
