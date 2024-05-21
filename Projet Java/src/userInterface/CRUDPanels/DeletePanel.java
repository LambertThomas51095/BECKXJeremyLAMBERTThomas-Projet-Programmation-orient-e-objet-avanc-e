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
        searchTextField = new JTextField(10);
        searchButton = new JButton("Rechercher");
        searchButton.addActionListener(buttonListener);
        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);


        informationsPanel = new JPanel();
        informationsPanel.setLayout(new GridLayout(7, 2));
        this.add(informationsPanel, BorderLayout.CENTER);

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
        informationsPanel.add(pseudonymLabel);
        informationsPanel.add(pseudonymInformation);


        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        this.add(buttonPanel, BorderLayout.SOUTH);

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
                try{
                    agent = controller.getAgent(getMatricule());

                    lastNameInformation.setText(agent.getLastname());
                    firstNameInformation.setText(agent.getFirstname());
                    firstNameInformation.setText(agent.getFirstname());
                    birthdateInformation.setText(agent.getBirthdate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    gsmInformation.setText(agent.getPhoneNumber());
                    genderInformation.setText(agent.getGender());
                    if(agent.getIsAlone()){
                        isAloneInformation.setText("Célibataire");
                    }
                    else{
                        isAloneInformation.setText("Marié(e)");
                    }
                    pseudonymInformation.setText(agent.getPseudonym());
                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null, "Une erreur est survenue.\nVeuillez nous excuser.\nErreur : " + exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                if(agent == null){
                    JOptionPane.showMessageDialog(null, "Aucun agent renseigné !", "Avertissement", JOptionPane.WARNING_MESSAGE);
                }
                else{
                    if(JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer l'agent suivant ? : " + agent.getLastname() + " " + agent.getFirstname()) == JOptionPane.YES_OPTION){
                        try{
                            controller.deleteAgent(agent);
                            searchTextField.setText("");
                            lastNameInformation.setText("");
                            firstNameInformation.setText("");
                            birthdateInformation.setText("");
                            gsmInformation.setText("");
                            genderInformation.setText("");
                            isAloneInformation.setText("");
                            pseudonymInformation.setText("");
                            JOptionPane.showMessageDialog(null, "Agent supprimé avec succès !", "Suppression", JOptionPane.INFORMATION_MESSAGE);
                        }catch(Exception exception){
                            JOptionPane.showMessageDialog(null, "L'agent n'a pas été supprimé, une erreur est survenue !\n Erreur : " + exception.getMessage(), "Suppression", JOptionPane.ERROR);
                        }

                    }
                }
            }
        }
    }
}
