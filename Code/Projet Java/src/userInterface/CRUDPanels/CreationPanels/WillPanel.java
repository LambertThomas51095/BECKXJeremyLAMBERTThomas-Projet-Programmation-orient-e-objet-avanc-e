package userInterface.CRUDPanels.CreationPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class WillPanel extends JPanel implements CreationPanel{
    private JLabel epitaphLabel, funeralsTypeLabel, otherFuneralLabel;
    private JTextField epitaphTextField, otherFuneralTextField;
    private JComboBox funeralsTypeComboBox;

    public WillPanel(){
        this.setLayout(new GridLayout(3, 2));

        ComboBoxListener comboBoxListener = new ComboBoxListener();

        funeralsTypeLabel = new JLabel("Type de funérail : ");
        funeralsTypeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        String [] funeralsTypeValues = {"Ne pas enregistrer", "Inhumation", "Crémation", "autres"};
        funeralsTypeComboBox = new JComboBox(funeralsTypeValues);
        funeralsTypeComboBox.setMaximumRowCount(4);
        funeralsTypeComboBox.addItemListener(comboBoxListener);
        this.add(funeralsTypeLabel);
        this.add(funeralsTypeComboBox);

        otherFuneralLabel = new JLabel("Autre type de funérail : ");
        otherFuneralLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        otherFuneralTextField = new JTextField();

        epitaphLabel = new JLabel("Epitaphe : ");
        epitaphLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        epitaphTextField = new JTextField();
        this.add(epitaphLabel);
        this.add(epitaphTextField);
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
