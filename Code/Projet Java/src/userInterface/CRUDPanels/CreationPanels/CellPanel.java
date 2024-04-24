package userInterface.CRUDPanels.CreationPanels;

import controller.ApplicationController;
import model.Cell;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
        try {
            ArrayList<Cell> cells = new ApplicationController().getAllCells();
            String [] values = {};

            for(int iCell = 0; iCell < cells.size(); iCell++){
                values[iCell] = cells.get(iCell).getName();
            }

            return values;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "test", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    public String [] getResult(){
        String [] values = {(String)cellComboBox.getSelectedItem()};
        return values;
    }
}
