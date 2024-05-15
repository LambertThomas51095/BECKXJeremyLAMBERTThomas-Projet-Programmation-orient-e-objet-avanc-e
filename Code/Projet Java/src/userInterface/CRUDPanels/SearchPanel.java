package userInterface.CRUDPanels;

import controller.ApplicationController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchPanel extends JPanel {
    private ApplicationController controller;
    private JTable table;
    private AllAgentsModel model;
    private JScrollPane scrollPane;
    private JPanel buttonPanel;
    private JButton deleteButton;
    private ListSelectionModel listSelect;

    public SearchPanel(){
        this.setLayout(new BorderLayout());
        this.controller = new ApplicationController();
        ButtonListener buttonListener = new ButtonListener();

        try {
            model = new AllAgentsModel(controller.getAllAgents());
            table = new JTable(model);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            scrollPane = new JScrollPane(table);
            this.add(scrollPane, BorderLayout.CENTER);

            //Centrer le texte dans les cellules
            DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
            custom.setHorizontalAlignment(JLabel.CENTER);
            for(Integer i = 0; i < table.getColumnCount(); i++){
                table.getColumnModel().getColumn(i).setCellRenderer(custom);
            }

            buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            this.add(buttonPanel, BorderLayout.SOUTH);

            deleteButton = new JButton("Supprimer l'agent");
            deleteButton.addActionListener(buttonListener);
            buttonPanel.add(deleteButton);

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Une erreur est survenue.\nVeuillez nous excuser.\nErreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            listSelect = table.getSelectionModel();
            Integer selectedLine = listSelect.getMinSelectionIndex();
            try {
                controller.deleteAgent(controller.getAgent((Integer)table.getValueAt(selectedLine, 0)));
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null, "Une erreur est survenue.\nVeuillez nous excuser.\nErreur : " + exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            listSelect.clearSelection();
            try{
                SearchPanel.this.remove(scrollPane);
                table = new JTable(new AllAgentsModel(controller.getAllAgents()));
                scrollPane = new JScrollPane(table);
                SearchPanel.this.add(scrollPane, BorderLayout.CENTER);

                //Centrer le texte dans les cellules
                DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
                custom.setHorizontalAlignment(JLabel.CENTER);
                for(Integer i = 0; i < table.getColumnCount(); i++){
                    table.getColumnModel().getColumn(i).setCellRenderer(custom);
                }
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null, "Une erreur est survenue.\nVeuillez nous excuser.\nErreur : " + exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            SearchPanel.this.validate();
            SearchPanel.this.repaint();
        }
    }
}
