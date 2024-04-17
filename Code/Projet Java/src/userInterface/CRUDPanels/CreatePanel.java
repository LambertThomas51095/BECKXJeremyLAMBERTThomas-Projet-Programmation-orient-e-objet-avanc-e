package userInterface.CRUDPanels;

import userInterface.CRUDPanels.CreationPanels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatePanel extends JPanel {
    private JPanel creationPanels;
    private JPanel buttonPanel;
    private JButton resetButton, creationButton;

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

                    for(String value : ((ProfilPanel) creationPanels).getResult()){
                        JOptionPane.showMessageDialog(null, value);
                    }

                    CreatePanel.this.remove(creationPanels);
                    creationPanels = new WillPanel();
                    CreatePanel.this.add(creationPanels, BorderLayout.CENTER);

                    CreatePanel.this.validate();
                    CreatePanel.this.repaint();
                }
                else if(creationPanels instanceof WillPanel){

                    for(String value : ((WillPanel) creationPanels).getResult()){
                        JOptionPane.showMessageDialog(null, value);
                    }

                    CreatePanel.this.remove(creationPanels);
                    creationPanels = new CellPanel();
                    CreatePanel.this.add(creationPanels, BorderLayout.CENTER);

                    creationButton.setText("Enregistrer");

                    CreatePanel.this.validate();
                    CreatePanel.this.repaint();
                }
                else{

                    for(String value : ((CellPanel) creationPanels).getResult()){
                        JOptionPane.showMessageDialog(null, value);
                    }

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
