package userInterface.CRUDPanels;

import controller.ApplicationController;

import javax.swing.*;

public class SearchPanel extends JPanel {
    private JTable table;
    private AllAgentsModel model;

    public SearchPanel(){
        model = new AllAgentsModel(new ApplicationController().getAllAgents());
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
    }
}
