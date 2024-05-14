package userInterface.CRUDPanels;

import controller.ApplicationController;
import model.Agent;
import model.Cell;
import model.Will;
import userInterface.CRUDPanels.EditPanels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EditPanel extends JPanel {
    private ApplicationController controller;
    private JPanel editPanels, searchPanel, buttonPanel;
    private JLabel searchLabel;
    private JTextField searchTextField;
    private JButton searchButton, resetButton, editButton;
    private Agent agent;
    private Will will;
    private Will tempWill;
    private boolean destroyWill;

    public EditPanel(){
        this.destroyWill = false;
        this.controller = new ApplicationController();

        ButtonListener buttonListener = new ButtonListener();

        this.setLayout(new BorderLayout());

        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());

        searchLabel = new JLabel("Matricule de l'agent : ");
        searchLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        searchTextField = new JTextField(10);
        searchButton = new JButton("Rechercher");
        searchButton.addActionListener(buttonListener);
        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);

        editPanels = searchPanel;
        this.add(editPanels, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        this.add(buttonPanel, BorderLayout.SOUTH);

        resetButton = new JButton("Réinitialiser");
        resetButton.addActionListener(buttonListener);
        buttonPanel.add(resetButton);

        editButton = new JButton("Suivant");
        editButton.addActionListener(buttonListener);
        buttonPanel.add(editButton);
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
                    EditPanel.this.remove(editPanels);
                    editPanels = new ProfilPanel(agent);
                    EditPanel.this.add(editPanels, BorderLayout.CENTER);

                    EditPanel.this.validate();
                    EditPanel.this.repaint();
                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de la recherche de l'agent.\nVeuillez nous excuser.\nErreur : " + exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(e.getSource() == resetButton){
                EditPanel.this.remove(editPanels);
                editPanels = searchPanel;
                EditPanel.this.add(editPanels, BorderLayout.CENTER);

                editButton.setText("Suivant");

                EditPanel.this.validate();
                EditPanel.this.repaint();
            }
            else if(e.getSource() == editButton){
                if(editPanels instanceof ProfilPanel){
                    try{

                    String [] values = ((ProfilPanel) editPanels).getResult();

                        agent.setLastname(values[0]);
                        agent.setFirstname(values[1]);
                        agent.setBirthdate(LocalDate.parse(values[2]));
                        agent.setPhoneNumber(values[3]);
                        agent.setGender(values[4]);
                        if(values[5] == "Célibataire"){
                            agent.setAlone(true);
                        }
                        else{
                            agent.setAlone(false);
                        }
                        agent.setPseudonym(values[6]);

                        EditPanel.this.remove(editPanels);
                        tempWill = agent.getEditorial();
                        editPanels = new WillPanel(tempWill);
                        EditPanel.this.add(editPanels, BorderLayout.CENTER);
                    }catch(Exception exception){
                        JOptionPane.showMessageDialog(null, "Une erreur est survenue.\nVeuillez nous excuser.\nErreur : " + exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    EditPanel.this.validate();
                    EditPanel.this.repaint();
                }
                else if(editPanels instanceof WillPanel){
                    try{
                        String [] values = ((WillPanel) editPanels).getResult();
                        if(!values[0].equals("Ne pas enregistrer")){
                            will = new Will(values[1]);
                            if(!values[0].equals("autres")){
                               will.setFuneralsType(values[0]);
                            }
                            else{
                                will.setFuneralsType(values[2]);
                            }
                            agent.setEditorial(will);
                        }
                        else{
                            if (agent.getEditorial() != null) {
                                destroyWill = true;
                            }
                            agent.setEditorial(null);
                        }

                        EditPanel.this.remove(editPanels);

                        editPanels = new CellPanel(controller.getAllCells(), agent.getAffectation());
                        EditPanel.this.add(editPanels, BorderLayout.CENTER);

                        editButton.setText("Enregistrer");

                        EditPanel.this.validate();
                        EditPanel.this.repaint();
                    }catch(Exception exception){
                        JOptionPane.showMessageDialog(null, "Une erreur est survenue.\nVeuillez nous excuser.\nErreur : " + exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }

                }
                else{

                    String [] values = ((CellPanel) editPanels).getResult();

                    ArrayList<Cell> cells = ((CellPanel) editPanels).getCells();
                    Integer iCell = 0;
                    while(cells.get(iCell).getName() != values[0]){
                        iCell++;
                    }
                    agent.setAffectation(cells.get(iCell));
                    try{
                        controller.modifyAgent(agent);

                        JPanel confirmation = new JPanel();
                        confirmation.setLayout(new FlowLayout());
                        JLabel labelConfirmation = new JLabel("<html><h1 style=\"color: green; text-align : center;\">Agent modifié avec succès !</h1><p style=\"text-align : center;\">L'agent a été modifié dans la base de données.</p><p style=\"text-align : center;\">Merci.</p><html>");
                        confirmation.add(labelConfirmation);
                        EditPanel.this.removeAll();
                        EditPanel.this.add(confirmation, BorderLayout.CENTER);

                        EditPanel.this.validate();
                        EditPanel.this.repaint();

                        try{
                            if (destroyWill) {
                                controller.deleteWill(tempWill);
                            }
                        }
                        catch(Exception exception){
                            JOptionPane.showMessageDialog(null, "Une erreur est survenue.\nVeuillez nous excuser.\nErreur : " + exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }catch(Exception exception){
                        JOptionPane.showMessageDialog(null, "Une erreur est survenue.\nVeuillez nous excuser.\nErreur : " + exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        }
    }
}
