package userInterface.CRUDPanels.CreationPanels;

import exception.AgentException;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfilPanel extends JPanel implements CreationPanel{
    private JLabel lastNameLabel, firstNameLabel, birthdateLabel, gsmLabel, genderLabel, pseudonymLabel;
    private JTextField lastNameTextField, firstNameTextField, birthdateTextField, gsmTextField, pseudonymTextField;
    private JComboBox genderComboBox;
    private JRadioButton isAloneRadioButton, isMarriedRadioButton;
    private ButtonGroup isAloneRadioButtonGroup;

    public static final String NO_SPACE_PATTERN = "^\\d{3}/\\d{2}\\.\\d{2}\\.\\d{2}$";

    public ProfilPanel(){
        this.setLayout(new GridLayout(7, 2));

        lastNameLabel = new JLabel("Nom : ");
        lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lastNameTextField = new JTextField();
        this.add(lastNameLabel);
        this.add(lastNameTextField);

        firstNameLabel = new JLabel("Prénom : ");
        firstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        firstNameTextField = new JTextField();
        this.add(firstNameLabel);
        this.add(firstNameTextField);

        birthdateLabel = new JLabel("Date de naissance : ");
        birthdateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        birthdateTextField = new JTextField();
        this.add(birthdateLabel);
        this.add(birthdateTextField);

        gsmLabel = new JLabel("Numéro de téléphone : ");
        gsmLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gsmTextField = new JTextField();
        this.add(gsmLabel);
        this.add(gsmTextField);

        genderLabel = new JLabel("Genre : ");
        genderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        String[] genderValues = {"Masculin", "Féminin", "Autres"};
        genderComboBox = new JComboBox(genderValues);
        genderComboBox.setMaximumRowCount(3);
        this.add(genderLabel);
        this.add(genderComboBox);

        isAloneRadioButton = new JRadioButton("Célibataire", false);
        isAloneRadioButton.setHorizontalAlignment(SwingConstants.RIGHT);
        isMarriedRadioButton = new JRadioButton("Marié(e)", false);
        this.add(isAloneRadioButton);
        this.add(isMarriedRadioButton);
        isAloneRadioButtonGroup = new ButtonGroup();
        isAloneRadioButtonGroup.add(isAloneRadioButton);
        isAloneRadioButtonGroup.add(isMarriedRadioButton);

        pseudonymLabel = new JLabel("Pseudonyme : ");
        pseudonymLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        pseudonymTextField = new JTextField();
        this.add(pseudonymLabel);
        this.add(pseudonymTextField);
    }

    public String isAlone(){
        if(isAloneRadioButton.isSelected()){
            return isAloneRadioButton.getText();
        }
        else if (isMarriedRadioButton.isSelected()){
            return isMarriedRadioButton.getText();
        }
        else{
            return null;
        }
    }

    public String getGender(){
        String gender = (String)genderComboBox.getSelectedItem();
        switch(gender){
            case "Masculin" : return "M";
            case "Féminin" : return "F";
            default : return "X";
        }
    }

    public String getPseudonym() {
        String pseudonym = pseudonymTextField.getText();
        Pattern pattern = Pattern.compile(NO_SPACE_PATTERN);
        Matcher matcher = pattern.matcher(pseudonym);
        if(matcher.find()){
            return pseudonym;
        }else{
            return null;
        }
    }

    public String[] getResult(){
        String [] values = {lastNameTextField.getText(), firstNameTextField.getText(), birthdateTextField.getText(), gsmTextField.getText(), getGender(), isAlone(), getPseudonym()};
        return values;
    }
}
