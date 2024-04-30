package userInterface.searchPanels;

import controller.ApplicationController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ThirdSearchPanel extends JPanel {
    private ApplicationController controller;
    private JPanel searchPanel;
    private JLabel searchLabel;
    private JTextField searchTextField;
    private JButton searchButton;
    private JTable table;
    private AllContactsModel model;

    public ThirdSearchPanel(){
        this.controller = new ApplicationController();

        this.setLayout(new BorderLayout());

        ButtonListener buttonListener = new ButtonListener();

        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        this.add(searchPanel, BorderLayout.NORTH);

        searchLabel = new JLabel("Code de la mission : ");
        searchLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        searchTextField = new JTextField(10);
        searchButton = new JButton("Rechercher");
        searchButton.addActionListener(buttonListener);
        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            try{
                model = new AllContactsModel(controller.getContacts(Integer.parseInt(searchTextField.getText())));
                table = new JTable(model);

                JScrollPane scrollPane = new JScrollPane(table);
                ThirdSearchPanel.this.add(scrollPane, BorderLayout.CENTER);

                //Centrer le texte dans les cellules
                DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
                custom.setHorizontalAlignment(JLabel.CENTER);
                for(int i = 0; i < table.getColumnCount(); i++){
                    table.getColumnModel().getColumn(i).setCellRenderer(custom);
                }

                ThirdSearchPanel.this.validate();
                ThirdSearchPanel.this.repaint();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null, "Le code de la mission est incorrect !", "Mission inconnue", JOptionPane.ERROR);
            }
        }
    }
}
