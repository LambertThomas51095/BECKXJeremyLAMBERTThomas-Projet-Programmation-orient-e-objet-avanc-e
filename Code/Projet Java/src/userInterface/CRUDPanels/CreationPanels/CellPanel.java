package userInterface.CRUDPanels.CreationPanels;

import controller.ApplicationController;
import exception.AccessException;
import exception.ConnectionException;
import model.Cell;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CellPanel extends JPanel implements CreationPanel{
    private ApplicationController controller;
    private JLabel cellLabel;
    private JComboBox cellComboBox;
    private ArrayList<Cell> cells;

    public CellPanel(ArrayList<Cell> cells) throws ConnectionException, AccessException{
        this.cells = cells;
        this.controller = new ApplicationController();
        this.setLayout(new GridLayout(1, 2));

        cellLabel = new JLabel("Cellules : ");
        cellLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        cellComboBox = new JComboBox(getCellValues());
        cellComboBox.setMaximumRowCount(3);
        this.add(cellLabel);
        this.add(cellComboBox);
    }

    public String[] getCellValues() throws ConnectionException, AccessException {
        //ArrayList<Cell> cells = controller.getAllCells();
        String [] values = {};

        for(int iCell = 0; iCell < cells.size(); iCell++){
            values[iCell] = cells.get(iCell).getName();
        }

        return values;
        /*
        try {
            ArrayList<Cell> cells = controller.getAllCells();
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
        */
    }

    public String [] getResult(){
        String [] values = {(String)cellComboBox.getSelectedItem()};
        return values;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }
}
