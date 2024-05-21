package userInterface.searchPanels;

import controller.ApplicationController;
import exception.AccessException;
import exception.ConnectionException;
import model.RegularExpression;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    public Integer getMissionCode() throws ConnectionException, AccessException {
        String missionCodeText = searchTextField.getText();
        Pattern pattern = Pattern.compile(RegularExpression.ONLY_NUMBER.toString());
        Matcher matcher = pattern.matcher(missionCodeText);
        if(matcher.find()){
            Integer missionCode = Integer.parseInt(missionCodeText);
            if(controller.getAllMissionsCode().contains(missionCode)){
                return missionCode;
            }
        }
        return null;
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Integer missionCode = getMissionCode();
                if(missionCode != null){
                    ArrayList<String> contacts = controller.getContacts(missionCode);
                    if(contacts.size() > 0){
                        model = new AllContactsModel(contacts);
                        table = new JTable(model);

                        if(ThirdSearchPanel.this.getComponentCount() == 2){
                            ThirdSearchPanel.this.remove(1);
                        }
                        JScrollPane scrollPane = new JScrollPane(table);
                        ThirdSearchPanel.this.add(scrollPane, BorderLayout.CENTER);

                        //Centrer le texte dans les cellules
                        DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
                        custom.setHorizontalAlignment(JLabel.CENTER);
                        for(Integer i = 0; i < table.getColumnCount(); i++){
                            table.getColumnModel().getColumn(i).setCellRenderer(custom);
                        }
                    }else{
                        if(ThirdSearchPanel.this.getComponentCount() == 2){
                            ThirdSearchPanel.this.remove(1);
                        }
                        JOptionPane.showMessageDialog(null, "Aucun contact n'est attribué à cette mission", "Aucun contact attribué", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    if(ThirdSearchPanel.this.getComponentCount() == 2){
                        ThirdSearchPanel.this.remove(1);
                    }
                    JOptionPane.showMessageDialog(null, "Le code de mission entrée n'existe pas", "Donnée incorrecte", JOptionPane.ERROR_MESSAGE);
                }

                ThirdSearchPanel.this.validate();
                ThirdSearchPanel.this.repaint();
            }
            catch(Exception exception){
                JOptionPane.showMessageDialog(null, "Une erreur avec la base de donnée est survenue.\nVeuillez nous excuser.\nErreur : " + exception.getMessage(), "Erreur de base de données", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
