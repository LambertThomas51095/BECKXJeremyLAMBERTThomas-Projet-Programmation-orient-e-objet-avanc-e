package userInterface.CRUDPanels;

import controller.ApplicationController;
import model.Agent;
import model.Cell;
import model.Will;
import userInterface.CRUDPanels.CreationPanels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreatePanel extends JPanel {
    private JPanel creationPanels, buttonPanel;
    private JButton resetButton, creationButton;
    private Agent agent;
    private Will will;
    private Cell cell;

    public CreatePanel(){
        this.setLayout(new BorderLayout());

        creationPanels = new ProfilPanel();
        this.add(creationPanels, BorderLayout.CENTER);

        ButtonListener buttonListener = new ButtonListener();

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        this.add(buttonPanel, BorderLayout.SOUTH);

        resetButton = new JButton("Réinitialiser");
        resetButton.addActionListener(buttonListener);
        buttonPanel.add(resetButton);

        creationButton = new JButton("Suivant");
        creationButton.addActionListener(buttonListener);
        buttonPanel.add(creationButton);
    }

    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == resetButton){
                CreatePanel.this.remove(creationPanels);
                creationPanels = new ProfilPanel();
                CreatePanel.this.add(creationPanels, BorderLayout.CENTER);

                creationButton.setText("Suivant");

                CreatePanel.this.validate();
                CreatePanel.this.repaint();
            }
            else if(e.getSource() == creationButton){
                if(creationPanels instanceof ProfilPanel){

                    String [] values = ((ProfilPanel) creationPanels).getResult();

                    agent.setLastName(values[0]);
                    agent.setFirstName(values[1]);
                    agent.setBirthdate(LocalDate.parse(values[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    agent.setPhoneNumber(values[3]);
                    agent.setGender(values[4]);
                    if(values[5] == "Célibataire"){
                        agent.setAlone(true);
                    }
                    else{
                        agent.setAlone(false);
                    }
                    agent.setPseudonym(values[6]);

                    CreatePanel.this.remove(creationPanels);
                    creationPanels = new WillPanel();
                    CreatePanel.this.add(creationPanels, BorderLayout.CENTER);

                    CreatePanel.this.validate();
                    CreatePanel.this.repaint();
                }
                else if(creationPanels instanceof WillPanel){

                    String [] values = ((WillPanel) creationPanels).getResult();

                    will.setEpitaph(values[0]);
                    if(values[1] != "autres"){
                        will.setFuneralsType(values[1]);
                    }
                    else{
                        will.setFuneralsType(values[2]);
                    }

                    CreatePanel.this.remove(creationPanels);
                    creationPanels = new CellPanel();
                    CreatePanel.this.add(creationPanels, BorderLayout.CENTER);

                    creationButton.setText("Enregistrer");

                    CreatePanel.this.validate();
                    CreatePanel.this.repaint();
                }
                else{

                    String [] values = ((CellPanel) creationPanels).getResult();

                    agent.setEditorial(will);
                    agent.setAffectation(new ApplicationController().getCell(values[0]));
                    new ApplicationController().addAgent(agent);

                    JPanel confirmation = new JPanel();
                    confirmation.setLayout(new FlowLayout());
                    JLabel labelConfirmation = new JLabel("<html><h1 style=\"color: green; text-align : center;\">Agent créé avec succès !</h1><p style=\"text-align : center;\">L'agent a été ajouté à la base de données.</p><p style=\"text-align : center;\">Merci.</p><html>");
                    confirmation.add(labelConfirmation);
                    CreatePanel.this.removeAll();
                    CreatePanel.this.add(confirmation, BorderLayout.CENTER);

                    CreatePanel.this.validate();
                    CreatePanel.this.repaint();
                }
            }
        }
    }
}
