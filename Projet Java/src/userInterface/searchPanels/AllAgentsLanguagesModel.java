package userInterface.searchPanels;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllAgentsLanguagesModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<ArrayList<String>> contents;

    public AllAgentsLanguagesModel(ArrayList<ArrayList<String>> contents){
        columnNames = new ArrayList<>();
        columnNames.add("Nom");
        columnNames.add("Prénom");
        columnNames.add("Langues");
        setContents(contents);
    }

    public void setContents(ArrayList<ArrayList<String>> contents){
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
        ArrayList<String> content = contents.get(row);
        switch (column){
            case 0 : return content.get(0);
            case 1 : return content.get(1);
            case 2 : return content.get(2);
            default : return null;
        }
    }
    public Class getColumnClass(int column){
        return String.class;
    }
}
