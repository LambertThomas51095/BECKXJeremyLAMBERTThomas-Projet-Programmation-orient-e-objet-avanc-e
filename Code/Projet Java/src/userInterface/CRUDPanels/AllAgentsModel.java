package userInterface.CRUDPanels;

import model.Agent;
import model.Cell;
import model.Will;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;

public class AllAgentsModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Agent> contents;

    public AllAgentsModel(ArrayList<Agent> agents){

    }

    public int getColumnCount(){
        return columnNames.size();
    }
    public int getRowCount(){
        return contents.size();
    }
    public String getColumnName(int column){
        return columnNames.get(column);
    }
    public Object getValueAt(int row, int column){
        Agent agent = contents.get(row);
        switch (column){
            case 0 : return agent.getPersonnalNumber();
            case 1 : return agent.getLastname();
            case 2 : return agent.getFirstname();
            case 3 : return agent.getBirthdate();
            case 4 : return agent.getPhoneNumber();
            case 5 : return agent.getGender();
            case 6 : return agent.getIsAlone();
            case 7 : return agent.getPseudonym();
            case 8 : return agent.getEditorial();
            case 9 : return agent.getAffectation();
            default : return null;
        }
    }
    public Class getColumnClass(int column){
        Class c;
        switch (column){
            case 0 : c = Integer.class;
                    break;
            case 1 : c = String.class;
                    break;
            case 2 : c = String.class;
                    break;
            case 3 : c = LocalDate.class;
                    break;
            case 4 : c = String.class;
                    break;
            case 5 : c = String.class;
                    break;
            case 6 : c = Boolean.class;
                    break;
            case 7 : c = String.class;
                    break;
            case 8 : c = Will.class;
                    break;
            case 9 : c = Cell.class;
                    break;
            default : c = String.class;
        }
        return c;
    }
}
