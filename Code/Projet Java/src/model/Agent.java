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
    private String firstName;
    private LocalDate birthdate;
    private String phoneNumber;
    private String gender;
    private Boolean isAlone;
    private String pseudonym;
    private Will editorial;
    private Cell affectation;

    public static final String PHONE_PATTERN_1 = "^\\d{3}/\\d{2}\\.\\d{2}\\.\\d{2}$";
    public static final String PHONE_PATTERN_2 = "^\\d{3}/\\d{3}\\.\\d{3}$";

    public static final String [] POSSIBLE_GENDER = {"M","F","X"};

    public Agent(Integer personnalNumber, String lastName, String firstName, LocalDate birthdate, String phoneNumber, String gender, Boolean isAlone, String pseudonym, Will editorial, Cell affectation) throws AgentException{
        setPersonnalNumber(personnalNumber);
        setLastName(lastName);
        setFirstName(firstName);
        setBirthdate(birthdate);
        setPhoneNumber(phoneNumber);
        setGender(gender);
        setAlone(isAlone);
        setPseudonym(pseudonym);
        setEditorial(editorial);
        setAffectation(affectation);
    }
    public Agent(Integer personnalNumber, String lastName, String firstName, LocalDate birthdate, String phoneNumber, String gender, Boolean isAlone, Cell affectation) throws AgentException{
        this(personnalNumber,lastName,firstName,birthdate,phoneNumber,gender,isAlone,null,null,affectation);
    }
    public Agent(String lastName, String firstName, LocalDate birthdate, String phoneNumber, String gender, Boolean isAlone,String pseudonym, Cell affectation) throws AgentException{
        this(null,lastName,firstName,birthdate,phoneNumber,gender,isAlone,pseudonym,null,affectation);
    }
    public Agent(String lastName, String firstName, LocalDate birthdate, String phoneNumber, String gender, Boolean isAlone, Cell affectation) throws AgentException{
        this(null,lastName,firstName,birthdate,phoneNumber,gender,isAlone,null,null,affectation);
    }

    @Override
    public String toString() {
       return "personnalNumber : " + personnalNumber +
                "\nlastName : " + lastName +
                "\nfisrtName : " + firstName +
                "\nbirthdate : " + birthdate +
                "\nphoneNumber : " + phoneNumber +
                "\ngender : " + gender +
                "\nisAlone : " + isAlone +
                "\npseudonym : " + pseudonym +
                "\neditorial : " + editorial +
                "\naffectation : \n----\n" + affectation + "\n----";
    }

    public void setPersonnalNumber(Integer personnalNumber) {
        this.personnalNumber = personnalNumber;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setBirthdate(LocalDate birthdate) throws AgentException {
        if(LocalDate.now().isAfter(birthdate) || birthdate.isEqual(LocalDate.now())) {
            this.birthdate = birthdate;
        }else{
            throw new AgentException("Vous avez entré une mauvaise date de naissance, celle-ci ne peut pas dépasser la date d'aujourd'hui : "+LocalDate.now()+" <-> "+birthdate);
        }
    }
    public void setPhoneNumber(String phoneNumber) throws AgentException {
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
        gender = gender.toUpperCase();
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
    public String getFirstName() {
        return firstName;
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
