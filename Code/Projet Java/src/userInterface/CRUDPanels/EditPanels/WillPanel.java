package userInterface.CRUDPanels.EditPanels;

import model.Will;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class WillPanel extends JPanel implements EditPanel {
    private JLabel epitaphLabel, funeralsTypeLabel, otherFuneralLabel;
    private JTextField epitaphTextField, otherFuneralTextField;
    private JComboBox funeralsTypeComboBox;
    private Will will;

    public WillPanel(Will will){
        this.will = will;

        this.setLayout(new GridLayout(3, 2));

        ComboBoxListener comboBoxListener = new ComboBoxListener();

        funeralsTypeLabel = new JLabel("Type de funérail : ");
        funeralsTypeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        String [] funeralsTypeValues = {"Ne pas enregistrer", "Inhumation", "Crémation", "Autres"};
        funeralsTypeComboBox = new JComboBox(funeralsTypeValues);
        funeralsTypeComboBox.setMaximumRowCount(4);
        funeralsTypeComboBox.setSelectedItem(getFuneralType());
        funeralsTypeComboBox.addItemListener(comboBoxListener);
        this.add(funeralsTypeLabel);
        this.add(funeralsTypeComboBox);

        epitaphLabel = new JLabel("Epitaphe : ");
        epitaphLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        epitaphTextField = new JTextField(will != null ? will.getEpitaph() : "");
        this.add(epitaphLabel);
        this.add(epitaphTextField);

        otherFuneralLabel = new JLabel("Autre type de funérail : ");
        otherFuneralLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        otherFuneralTextField = new JTextField();
        if(will != null && !will.getFuneralsType().equals("Inhumation") && !will.getFuneralsType().equals("Crémation")){
            WillPanel.this.add(otherFuneralLabel);
            WillPanel.this.add(otherFuneralTextField);
            otherFuneralTextField.setText(will != null ? will.getFuneralsType() : "");
        }
    }

    public String getFuneralType(){
        if(will == null){
            System.out.println("test3");
            return "Ne pas enregistrer";
        }
        else if(!will.getFuneralsType().equals("Inhumation") && !will.getFuneralsType().equals("Crémation")){
            System.out.println("test2");
            return "Autres";
        }
        else{
            System.out.println("test1");
            return will.getFuneralsType();
        }
    }

    public String[] getResult(){
        String [] values = {(String)funeralsTypeComboBox.getSelectedItem(), epitaphTextField.getText(), otherFuneralTextField.getText()};
        return values;
    }

    private class ComboBoxListener implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {
            if(funeralsTypeComboBox.getSelectedIndex() == 3 && e.getStateChange() == ItemEvent.SELECTED && WillPanel.this.getComponentCount() == 4){
                WillPanel.this.add(otherFuneralLabel);
                WillPanel.this.add(otherFuneralTextField);
                WillPanel.this.validate();
                WillPanel.this.repaint();
            }
            else if(funeralsTypeComboBox.getSelectedIndex() != 3 && e.getStateChange() == ItemEvent.SELECTED && WillPanel.this.getComponentCount() == 6){
                WillPanel.this.remove(otherFuneralLabel);
                WillPanel.this.remove(otherFuneralTextField);
                WillPanel.this.validate();
                WillPanel.this.repaint();
            }
        }
    }
}
