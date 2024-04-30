package userInterface.searchPanels;

import controller.ApplicationController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public static final String NO_SPACE_PATTERN = "^ *$";

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

    public Integer getMatricule() {
        String matricule = agentMatriculeTextField.getText();
        Pattern pattern = Pattern.compile(NO_SPACE_PATTERN);
        Matcher matcher = pattern.matcher(matricule);
        if(matcher.find()){
            return null;
        }else{
            return Integer.parseInt(matricule);
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            try{
                model = new AllAgentMissionsModel(controller.getAgentMissions(agentNameTextField.getText(), agentFirstNameTextField.getText(), getMatricule()));
                table = new JTable(model);

                JScrollPane scrollPane = new JScrollPane(table);
                SecondSearchPanel.this.add(scrollPane, BorderLayout.CENTER);

                //Centrer le texte dans les cellules
                DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
                custom.setHorizontalAlignment(JLabel.CENTER);
                for(int i = 0; i < table.getColumnCount(); i++){
                    table.getColumnModel().getColumn(i).setCellRenderer(custom);
                }

                SecondSearchPanel.this.validate();
                SecondSearchPanel.this.repaint();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null, "Le code de la mission est incorrect !", "Mission inconnue", JOptionPane.ERROR);
            }
        }
    }
}
