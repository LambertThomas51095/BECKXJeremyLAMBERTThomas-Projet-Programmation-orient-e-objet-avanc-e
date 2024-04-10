package userInterface.CRUDPanels;

import userInterface.CRUDPanels.CreationPanels.*;

import javax.swing.*;
import java.awt.*;

public class CreatePanel extends JPanel {
    private JPanel creationPanels;
    private JPanel buttonPanel;
    private JButton resetButton, creationButton;

    public CreatePanel(){
        this.setLayout(new BorderLayout());

        creationPanels = new ProfilPanel();
        this.add(creationPanels, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        this.add(buttonPanel, BorderLayout.SOUTH);

        resetButton = new JButton("Réinitialiser");
        buttonPanel.add(resetButton);

        creationButton = new JButton("Suivant");
        buttonPanel.add(creationButton);
    }
}
