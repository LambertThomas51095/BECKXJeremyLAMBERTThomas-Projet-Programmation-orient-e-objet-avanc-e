package userInterface.CRUDPanels;

import controller.ApplicationController;
import model.Agent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class DeletePanel extends JPanel {
    private ApplicationController controller;
    private JPanel searchPanel, informationsPanel, buttonPanel;
    private JLabel searchLabel, lastNameLabel, lastNameInformation, firstNameLabel, firstNameInformation, birthdateLabel, birthdateInformation, gsmLabel, gsmInformation, genderLabel, genderInformation, isAloneLabel, isAloneInformation, pseudonymLabel, pseudonymInformation;
    private JTextField searchTextField;
    private JButton searchButton, deleteButton;
    private Agent agent;

    public DeletePanel(){
        this.controller = new ApplicationController();

        this.setLayout(new BorderLayout());

        ButtonListener buttonListener = new ButtonListener();

        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        this.add(searchPanel, BorderLayout.NORTH);

        searchLabel = new JLabel("Matricule de l'agent : ");
        searchLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        searchTextField = new JTextField();
        searchButton = new JButton("Rechercher");
        searchButton.addActionListener(buttonListener);
        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);


        informationsPanel = new JPanel();
        informationsPanel.setLayout(new GridLayout(7, 2));
        this.add(informationsPanel);

        lastNameLabel = new JLabel("Nom : ");
        lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lastNameInformation = new JLabel("");
        informationsPanel.add(lastNameLabel);
        informationsPanel.add(lastNameInformation);

        firstNameLabel = new JLabel("Prénom : ");
        firstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        firstNameInformation = new JLabel("");
        informationsPanel.add(firstNameLabel);
        informationsPanel.add(firstNameInformation);

        birthdateLabel = new JLabel("Date de naissance : ");
        birthdateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        birthdateInformation = new JLabel("");
        informationsPanel.add(birthdateLabel);
        informationsPanel.add(birthdateInformation);

        gsmLabel = new JLabel("Numéro de téléphone : ");
        gsmLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gsmInformation = new JLabel("");
        informationsPanel.add(gsmLabel);
        informationsPanel.add(gsmInformation);

        genderLabel = new JLabel("Genre : ");
        genderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        genderInformation = new JLabel("");
        informationsPanel.add(genderLabel);
        informationsPanel.add(genderInformation);

        isAloneLabel = new JLabel("Statut : ");
        isAloneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        isAloneInformation = new JLabel("");
        informationsPanel.add(isAloneLabel);
        informationsPanel.add(isAloneInformation);

        pseudonymLabel = new JLabel("Pseudonyme : ");
        pseudonymLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        pseudonymInformation = new JLabel("");
        this.add(pseudonymLabel);
        this.add(pseudonymInformation);


        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        this.add(buttonPanel);

        deleteButton = new JButton("Supprimer");
        deleteButton.addActionListener(buttonListener);
        buttonPanel.add(deleteButton);
    }

    public Integer getMatricule(){
        return Integer.parseInt(searchTextField.getText());
    }

    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == searchButton){
                agent = controller.getAgent(getMatricule());

                lastNameInformation.setText(agent.getLastName());
                firstNameInformation.setText(agent.getFirstName());
                firstNameInformation.setText(agent.getFirstName());
                birthdateInformation.setText(agent.getBirthdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                gsmInformation.setText(agent.getPhoneNumber());
                genderInformation.setText(agent.getGender());
                if(agent.getIsAlone()){
                    isAloneInformation.setText("Célibataire");
                }
                else{
                    isAloneInformation.setText("Marié(e)");
                }
                pseudonymInformation.setText(agent.getPseudonym());
            }
            else {
                if(agent == null){
                    JOptionPane.showMessageDialog(null, "Aucun agent renseigné !", "Avertissement", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    if(JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer l'agent suivant ? : " + agent.getLastName() + " " + agent.getFirstName()) == JOptionPane.YES_OPTION){
                        controller.deleteAgent(agent);
                        searchTextField.setText("");
                        lastNameInformation.setText("");
                        firstNameInformation.setText("");
                        birthdateInformation.setText("");
                        gsmInformation.setText("");
                        genderInformation.setText("");
                        isAloneInformation.setText("");
                        pseudonymInformation.setText("");
                        JOptionPane.showConfirmDialog(null, "Agent supprimé avec succès !", "Suppression", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }
}
