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
        String [] values = new String[cells.size()];

        for(int iCell = 0; iCell < cells.size(); iCell++){
            System.out.println(cells.get(iCell).getName());
            values[iCell] = cells.get(iCell).getName();
        }

        return values;
    }

    public String [] getResult(){
        String [] values = {(String)cellComboBox.getSelectedItem()};
        return values;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }
}
