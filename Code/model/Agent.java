package model;

import exception.*;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Agent {
    private Integer personnalNumber;
    private String lastName;
    private String fisrtName;
    private LocalDate birthday;
    private String phoneNumber;
    private String gender;
    private Boolean isAlone;
    private String pseudonym;
    private Integer editorial;
    private String affectation;

    public final String PHONE_PATTERN_1 = "\\d{3}/\\d{2}\\.\\d{2}\\.\\d{2}";
    public final String PHONE_PATTERN_2 = "\\d{3}/\\d{3}\\.\\d{3}";

    public Agent(Integer personnalNumber, String lastName, String fisrtName, LocalDate birthday, String phoneNumber, String gender, Boolean isAlone, String pseudonym, Integer editorial, String affectation) throws GenderException, BirthdayException, PersonnalNumberException, PhoneNumberException{
        setPersonnalNumber(personnalNumber);
        setLastName(lastName);
        setFisrtName(fisrtName);
        setBirthday(birthday);
        setPhoneNumber(phoneNumber);
        setGender(gender);
        setAlone(isAlone);
        setPseudonym(pseudonym);
        setEditorial(editorial);
        setAffectation(affectation);
    }

    public void setPersonnalNumber(Integer personnalNumber) throws PersonnalNumberException {
        if(personnalNumber > 0){
            this.personnalNumber = personnalNumber;
        }else{
            throw new PersonnalNumberException(personnalNumber,"Négatif");
        }
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setFisrtName(String fisrtName) {
        this.fisrtName = fisrtName;
    }
    public void setBirthday(LocalDate birthday) throws BirthdayException {
        if(LocalDate.now().isAfter(birthday)) {
            this.birthday = birthday;
        }else{
            throw new BirthdayException(birthday);
        }

    }
    public void setPhoneNumber(String phoneNumber) throws PhoneNumberException {
        // exemple xxx/xx.xx.xx ou xxx/xxx.xxx => voir constante
        Pattern pattern1 = Pattern.compile(PHONE_PATTERN_1);
        Pattern pattern2 = Pattern.compile(PHONE_PATTERN_2);
        Matcher matcher1 = pattern1.matcher(phoneNumber);
        Matcher matcher2 = pattern2.matcher(phoneNumber);
        if(matcher1.find() || matcher2.find()){
            this.phoneNumber = phoneNumber;
        }else{
            throw new PhoneNumberException(phoneNumber);
        }

    }
    public void setGender(String gender) throws GenderException{
        if(gender.equals("H") || gender.equals("F") || gender.equals("M")){
            this.gender = gender;
        }else{
            throw new GenderException(gender);
        }

    }
    public void setAlone(Boolean alone) {
        isAlone = alone;
    }
    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }
    public void setEditorial(Integer editorial) {
        this.editorial = editorial;
    }
    public void setAffectation(String affectation) {
        this.affectation = affectation;
    }
}
