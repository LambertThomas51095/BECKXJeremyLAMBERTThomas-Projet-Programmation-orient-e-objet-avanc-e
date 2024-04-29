package userInterface.CRUDPanels;

import controller.ApplicationController;
import model.*;
import userInterface.CRUDPanels.CreationPanels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CreatePanel extends JPanel {
    private ApplicationController controller;
    private JPanel creationPanels, buttonPanel;
    private JButton resetButton, creationButton;
    private Agent agent;
    private Will will;
    private Cell cell;

    public CreatePanel(){
        this.controller = new ApplicationController();

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
                    System.out.println(values.length);

                    try{
                        agent = new Agent(values[0],values[1],LocalDate.parse(values[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")),values[3],values[4],values[6]);
                        if(values[5] == "Célibataire"){
                            agent.setAlone(true);
                        }
                        else{
                            agent.setAlone(false);
                        }

                        CreatePanel.this.remove(creationPanels);
                        creationPanels = new WillPanel();
                        CreatePanel.this.add(creationPanels, BorderLayout.CENTER);
                    }catch(Exception exception){
                        // reset  ?
                        CreatePanel.this.remove(creationPanels);
                        creationPanels = new ProfilPanel();
                        CreatePanel.this.add(creationPanels, BorderLayout.CENTER);
                        creationButton.setText("Suivant");

                        System.out.println(exception.getMessage());
                    }
                    CreatePanel.this.validate();
                    CreatePanel.this.repaint();
                }
                else if(creationPanels instanceof WillPanel){

                    String [] values = ((WillPanel) creationPanels).getResult();
                    System.out.println(values.length);
                    will = new Will(values[0]);
                    if(!values[1].equals("autres")){
                        will.setFuneralsType(values[1]);
                    }
                    else{
                        will.setFuneralsType(values[2]);
                    }

                    CreatePanel.this.remove(creationPanels);
                    try{
                        creationPanels = new CellPanel(controller.getAllCells());
                        CreatePanel.this.add(creationPanels, BorderLayout.CENTER);

                        creationButton.setText("Enregistrer");

                        CreatePanel.this.validate();
                        CreatePanel.this.repaint();
                    }catch(Exception exception){
                        // gérer exception
                        JOptionPane.showMessageDialog(null, exception.getMessage());
                    }

                }
                else{

                    String [] values = ((CellPanel) creationPanels).getResult();
                    System.out.println(values.length);

                    agent.setEditorial(will);

                    ArrayList<Cell> cells = ((CellPanel) creationPanels).getCells();
                    Integer iCell = 0;
                    while(cells.get(iCell).getName() != values[0]){
                        iCell++;
                    }
                    agent.setAffectation(cells.get(iCell));
                    try{
                        controller.addAgent(agent);

                        JPanel confirmation = new JPanel();
                        confirmation.setLayout(new FlowLayout());
                        JLabel labelConfirmation = new JLabel("<html><h1 style=\"color: green; text-align : center;\">Agent créé avec succès !</h1><p style=\"text-align : center;\">L'agent a été ajouté à la base de données.</p><p style=\"text-align : center;\">Merci.</p><html>");
                        confirmation.add(labelConfirmation);
                        CreatePanel.this.removeAll();
                        CreatePanel.this.add(confirmation, BorderLayout.CENTER);

                        CreatePanel.this.validate();
                        CreatePanel.this.repaint();
                    }catch(Exception exception){
                        System.out.println(exception.getMessage());
                    }

                }
            }
        }
    }
}
