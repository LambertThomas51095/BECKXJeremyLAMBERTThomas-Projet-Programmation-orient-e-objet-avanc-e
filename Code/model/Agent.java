package model;

import exception.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Agent {
    private Integer personnalNumber;
    private String lastName;
    private String fisrtName;
    private LocalDate birthdate;
    private String phoneNumber;
    private String gender;
    private Boolean isAlone;
    private String pseudonym;
    private Will editorial;
    private Cell affectation;

    public static final String PHONE_PATTERN_1 = "\\d{3}/\\d{2}\\.\\d{2}\\.\\d{2}";
    public static final String PHONE_PATTERN_2 = "\\d{3}/\\d{3}\\.\\d{3}";

    public static final String [] POSSIBLE_GENDER = {"M","F","X"};

    public Agent(Integer personnalNumber, String lastName, String fisrtName, LocalDate birthdate, String phoneNumber, String gender, Boolean isAlone) throws AgentException{
        setPersonnalNumber(personnalNumber);
        setLastName(lastName);
        setFisrtName(fisrtName);
        setBirthdate(birthdate);
        setPhoneNumber(phoneNumber);
        setGender(gender);
        setAlone(isAlone);
    }

    public String toString(){
        return "Tout ok";
    }

    public void setPersonnalNumber(Integer personnalNumber) throws AgentException {
        if(personnalNumber > 0){
            this.personnalNumber = personnalNumber;
        }else{
            throw new AgentException("La valeur du matricule est négative ou nulle");
        }
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setFisrtName(String fisrtName) {
        this.fisrtName = fisrtName;
    }
    public void setBirthdate(LocalDate birthdate) throws AgentException {
        if(LocalDate.now().isAfter(birthdate)) {
            this.birthdate = birthdate;
        }else{
            throw new AgentException("Vous avez entré une mauvaise date de naissance, celle-ci ne peut pas dépassé la date d'aujoudd'hui : "+birthdate);
        }

    }
    public void setPhoneNumber(String phoneNumber) throws AgentException {
        // exemple xxx/xx.xx.xx ou xxx/xxx.xxx => voir constante
        Pattern pattern1 = Pattern.compile(PHONE_PATTERN_1);
        Pattern pattern2 = Pattern.compile(PHONE_PATTERN_2);
        Matcher matcher1 = pattern1.matcher(phoneNumber);
        Matcher matcher2 = pattern2.matcher(phoneNumber);
        if(matcher1.find() || matcher2.find()){
            this.phoneNumber = phoneNumber;
        }else{
            throw new AgentException("Vous avez un entré un autre format de téléphone que ceux demandés :\n"+phoneNumber);
        }

    }
    public void setGender(String gender) throws AgentException{
        if(getPossibleGender().contains(gender)){
            this.gender = gender;
        }else{
            throw new AgentException("Vous avez entré un mauvais genre <<"+gender+">> parmis ceux possible : " +getPossibleGender());
        }

    }
    public void setAlone(Boolean alone) {
        isAlone = alone;
    }
    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }
    public void setEditorial(Will editorial) {
        this.editorial = editorial;
    }
    public void setAffectation(Cell affectation) {
        this.affectation = affectation;
    }

    public Integer getPersonnalNumber() {
        return personnalNumber;
    }
    public String getLastName() {
        return lastName;
    }
    public String getFisrtName() {
        return fisrtName;
    }
    public LocalDate getBirthdate() {
        return birthdate;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getGender() {
        return gender;
    }
    public Boolean getIsAlone() {
        return isAlone;
    }
    public String getPseudonym() {
        return pseudonym;
    }
    public Will getEditorial() {
        return editorial;
    }
    public Cell getAffectation() {
        return affectation;
    }

    public static List getPossibleGender(){
        return Arrays.stream(Agent.POSSIBLE_GENDER).toList();
    }

}
