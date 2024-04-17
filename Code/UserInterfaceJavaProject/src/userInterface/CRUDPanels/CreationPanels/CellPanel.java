package userInterface.CRUDPanels.CreationPanels;

import javax.swing.*;
import java.awt.*;

public class CellPanel extends JPanel implements CreationPanel{
    private JLabel cellLabel;
    private JComboBox cellComboBox;

    public CellPanel(){
        this.setLayout(new GridLayout(1, 2));

        cellLabel = new JLabel("Cellules : ");
        cellLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        cellComboBox = new JComboBox(getCellValues());
        cellComboBox.setMaximumRowCount(3);
        this.add(cellLabel);
        this.add(cellComboBox);
    }

    public String[] getCellValues(){
        String [] values = {"", ""};
        return values;
    }

    public String [] getResult(){
        String [] values = {(String)cellComboBox.getSelectedItem()};
        return values;
    }
}
