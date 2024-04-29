package userInterface.CRUDPanels;

import controller.ApplicationController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class SearchPanel extends JPanel {
    private ApplicationController controller;
    private JTable table;
    private AllAgentsModel model;

    public SearchPanel(){
        this.setLayout(new BorderLayout());
        this.controller = new ApplicationController();
        try {
            model = new AllAgentsModel(controller.getAllAgents());
            table = new JTable(model);

            JScrollPane scrollPane = new JScrollPane(table);
            this.add(scrollPane, BorderLayout.CENTER);

            //Centrer le texte dans les cellules
            DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
            custom.setHorizontalAlignment(JLabel.CENTER);
            for(int i = 0; i < table.getColumnCount(); i++){
                table.getColumnModel().getColumn(i).setCellRenderer(custom);
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
