package userInterface.searchPanels;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllContactsModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<String> contents;

    public AllContactsModel(ArrayList<String> contents){
        columnNames = new ArrayList<>();
        columnNames.add("Contacts");
        setContents(contents);
    }

    public void setContents(ArrayList<String> contents){
        this.contents = contents;
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
        String content = contents.get(row);
        switch (column){
            case 0 : return content;
            default : return null;
        }
    }
    public Class getColumnClass(int column){
        return String.class;
    }
}
