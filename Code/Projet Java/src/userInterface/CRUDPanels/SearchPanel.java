package userInterface.CRUDPanels;

import controller.ApplicationController;

import javax.swing.*;

public class SearchPanel extends JPanel {
    private ApplicationController controller;
    private JTable table;
    private AllAgentsModel model;

    public SearchPanel(){
        this.controller = new ApplicationController();
        try {
            model = new AllAgentsModel(controller.getAllAgents());
            table = new JTable(model);

            JScrollPane scrollPane = new JScrollPane(table);
            this.add(scrollPane);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
