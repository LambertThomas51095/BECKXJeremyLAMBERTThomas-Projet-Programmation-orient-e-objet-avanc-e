package userInterface;

import javax.swing.*;
import java.awt.*;

public class HelpWindow extends JFrame {
    private Container frameContainer;
    private JPanel panel;
    private JLabel label;
    public HelpWindow(){
        super("\"Pas vu, pas pris\" spy agency - Contacts");
        setBounds(350, 300, 650, 250);
        this.setResizable(false);

        frameContainer = this.getContentPane();

        panel = new JPanel();

        label = new JLabel("<html><h1 style=\"color: #007bff; text-align : center;\">Besoin d'aide ?</h1><p style=\"font-size: 20px;\" text-align : center;>En cas d'urgence, vous pouvez nous appeler au</p><p style=\"font-size: 24px; font-weight: bold; color: #28a745;\" text-align : center;>0471/74.09.15</p><p style=\"font-size: 16px;\" text-align : center;>Nos agents sont disponibles 24/7 pour vous assister.</p><html>");
        label.setLayout(new FlowLayout());
        panel.setBackground(Color.WHITE);

        panel.add(label);

        frameContainer.add(panel);

        setVisible(true);
    }
}
