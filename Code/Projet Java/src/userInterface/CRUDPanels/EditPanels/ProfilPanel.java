package userInterface.CRUDPanels.EditPanels;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.zinternaltools.DateVetoPolicyMinimumMaximumDate;
import model.Agent;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfilPanel extends JPanel implements EditPanel {
    private JLabel lastNameLabel, firstNameLabel, birthdateLabel, gsmLabel, genderLabel, pseudonymLabel;
    private JTextField lastNameTextField, firstNameTextField, gsmTextField, pseudonymTextField;
    private DatePicker datePicker;
    private JComboBox genderComboBox;
    private JRadioButton isAloneRadioButton, isMarriedRadioButton;
    private ButtonGroup isAloneRadioButtonGroup;
    private Agent agent;

    public static final String NO_SPACE_PATTERN = "^ *$";

    public ProfilPanel(Agent agent) {
        this.agent = agent;
        this.setLayout(new GridLayout(7, 2));

        lastNameLabel = new JLabel("Nom : ");
        lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lastNameTextField = new JTextField(agent.getLastname());
        this.add(lastNameLabel);
        this.add(lastNameTextField);

        firstNameLabel = new JLabel("Prénom : ");
        firstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        firstNameTextField = new JTextField(agent.getFirstname());
        this.add(firstNameLabel);
        this.add(firstNameTextField);

        birthdateLabel = new JLabel("Date de naissance : ");
        birthdateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        datePicker = new DatePicker();
        DatePickerSettings datePickerSetting = new DatePickerSettings();
        datePicker.setSettings(datePickerSetting);
        datePicker.getComponentDateTextField().setEnabled(false);
        datePickerSetting.setVetoPolicy(new DateVetoPolicyMinimumMaximumDate(LocalDate.MIN,LocalDate.now()));
        this.add(birthdateLabel);

        gsmLabel = new JLabel("Numéro de téléphone : ");
        gsmLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        gsmTextField = new JTextField(agent.getPhoneNumber());
        this.add(gsmLabel);
        this.add(gsmTextField);

        genderLabel = new JLabel("Genre : ");
        genderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        String[] genderValues = {"Masculin", "Féminin", "Autres"};
        genderComboBox = new JComboBox(genderValues);
        genderComboBox.setMaximumRowCount(3);
        genderComboBox.setSelectedItem(displayGender());
        this.add(genderLabel);
        this.add(genderComboBox);


        if (agent.getIsAlone()) {
            isAloneRadioButton = new JRadioButton("Célibataire", true);
            isMarriedRadioButton = new JRadioButton("Marié(e)", false);
        } else {
            isAloneRadioButton = new JRadioButton("Célibataire", false);
            isMarriedRadioButton = new JRadioButton("Marié(e)", true);
        }
        isAloneRadioButton.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(isAloneRadioButton);
        this.add(isMarriedRadioButton);
        isAloneRadioButtonGroup = new ButtonGroup();
        isAloneRadioButtonGroup.add(isAloneRadioButton);
        isAloneRadioButtonGroup.add(isMarriedRadioButton);

        pseudonymLabel = new JLabel("Pseudonyme : ");
        pseudonymLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        pseudonymTextField = new JTextField(agent.getPseudonym() != null ? agent.getPseudonym() : "");
        this.add(pseudonymLabel);
        this.add(pseudonymTextField);
    }

    public String displayGender() {
        String gender = agent.getGender();
        switch (gender) {
            case "M":
                return "Masculin";
            case "F":
                return "Féminin";
            default:
                return "Autres";
        }
    }

    public String isAlone() {
        if (isAloneRadioButton.isSelected()) {
            return isAloneRadioButton.getText();
        } else if (isMarriedRadioButton.isSelected()) {
            return isMarriedRadioButton.getText();
        } else {
            return null;
        }
    }

    public String getGender() {
        String gender = (String) genderComboBox.getSelectedItem();
        switch (gender) {
            case "Masculin":
                return "M";
            case "Féminin":
                return "F";
            default:
                return "X";
        }
    }

    public String getPseudonym() {
        String pseudonym = pseudonymTextField.getText();
        Pattern pattern = Pattern.compile(NO_SPACE_PATTERN);
        Matcher matcher = pattern.matcher(pseudonym);
        if (matcher.find()) {
            return null;
        } else {
            return pseudonym;
        }
    }

    public String[] getResult() {
        String[] values = {lastNameTextField.getText(), firstNameTextField.getText(), datePicker.getText(), gsmTextField.getText(), getGender(), isAlone(), getPseudonym()};
        return values;
    }
}