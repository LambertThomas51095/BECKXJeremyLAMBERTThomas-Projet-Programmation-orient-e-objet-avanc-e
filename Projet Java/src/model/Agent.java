package model;

import exception.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Agent {
    private Integer personnalNumber;
    private String lastname;
    private static Integer LAST_NAME_LENGTH = 25;
    private String firstname;
    private static Integer FIRST_NAME_LENGTH = 20;
    private LocalDate birthdate;
    private String phoneNumber;
    private static Integer PHONE_NUMBER_LENGTH = 13;
    private String gender;
    private static Integer GENDER_LENGTH = 1;
    private Boolean isAlone;
    private String pseudonym;
    private static Integer PSEUDONYM_LENGTH = 25;
    private Will editorial;
    private Cell affectation;

    public static final String [] POSSIBLE_GENDER = {"M","F","X"};

    public Agent(Integer personnalNumber, String lastname, String firstname, LocalDate birthdate, String phoneNumber, String gender, Boolean isAlone, String pseudonym, Will editorial, Cell affectation) throws AgentException{
        setPersonnalNumber(personnalNumber);
        setLastname(lastname);
        setFirstname(firstname);
        setBirthdate(birthdate);
        setPhoneNumber(phoneNumber);
        setGender(gender);
        setAlone(isAlone);
        setPseudonym(pseudonym);
        setEditorial(editorial);
        setAffectation(affectation);
    }
    public Agent(Integer personnalNumber, String lastname, String firstname, LocalDate birthdate, String phoneNumber, String gender, Boolean isAlone, Cell affectation) throws AgentException{
        this(personnalNumber,lastname,firstname,birthdate,phoneNumber,gender,isAlone,null,null,affectation);
    }
    public Agent(String lastname, String firstname, LocalDate birthdate, String phoneNumber, String gender,String pseudonym) throws AgentException{
        this(null,lastname,firstname,birthdate,phoneNumber,gender,null,pseudonym,null,null);
    }

    @Override
    public String toString() {
       return "personnalNumber : " + personnalNumber +
                "\nlastname : " + lastname +
                "\nfisrtName : " + firstname +
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
    public void setLastname(String lastname) throws AgentException {
        if(lastname.length() <= LAST_NAME_LENGTH){
            Pattern pattern = Pattern.compile(RegularExpression.NO_SPACE_PATTERN.toString());
            Matcher matcher = pattern.matcher(lastname);
            if(!matcher.find()){
                this.lastname = lastname;
            }else{
                throw new AgentException("Vous devez entrer obligatoirement un nom !");
            }
        }
        else{
            throw new AgentException("Le nom entré est trop long !\nMaximum " + LAST_NAME_LENGTH + " charactères");
        }
    }
    public void setFirstname(String firstname) throws AgentException{
        if(firstname.length() <= FIRST_NAME_LENGTH){
            Pattern pattern = Pattern.compile(RegularExpression.NO_SPACE_PATTERN.toString());
            Matcher matcher = pattern.matcher(firstname);
            if(!matcher.find()){
                this.firstname = firstname;
            }else{
                throw new AgentException("Vous devez entrer obligatoirement un prénom !");
            }
        }
        else{
            throw new AgentException("Le prénom entré est trop long !\nMaximum " + FIRST_NAME_LENGTH + " charactères");
        }
    }
    public void setBirthdate(LocalDate birthdate) throws AgentException {
        if(LocalDate.now().isAfter(birthdate) || birthdate.isEqual(LocalDate.now())) {
            this.birthdate = birthdate;
        }else{
            throw new AgentException("Vous avez entré une mauvaise date de naissance, celle-ci ne peut pas dépasser la date d'aujourd'hui : "+LocalDate.now()+" <-> "+birthdate);
        }
    }
    public void setPhoneNumber(String phoneNumber) throws AgentException {
        if(phoneNumber.length() <= PHONE_NUMBER_LENGTH){
            Pattern pattern = Pattern.compile(RegularExpression.PHONE_NUMBER.toString());
            Matcher matcher = pattern.matcher(phoneNumber);
            if(matcher.find()){
                this.phoneNumber = phoneNumber;
            }else{
                throw new AgentException("Vous avez un entré un autre format de téléphone que ceux demandés :\n"+phoneNumber);
            }
        }
        else{
            throw new AgentException("Le numéro de téléphone entré est trop long !\nMaximum " + PHONE_NUMBER_LENGTH + " charactères");
        }
    }
    public void setGender(String gender) throws AgentException{
        if(gender.length() <= GENDER_LENGTH){
            gender = gender.toUpperCase();
            if(getPossibleGender().contains(gender)){
                this.gender = gender;
            }else{
                throw new AgentException("Vous avez entré un mauvais genre <<"+gender+">> parmis ceux possible : " +getPossibleGender());
            }
        }
        else{
            throw new AgentException("Le genre entré est trop long !\nMaximum " + GENDER_LENGTH + " charactères");
        }

    }
    public void setAlone(Boolean alone) {
        isAlone = alone;
    }
    public void setPseudonym(String pseudonym) throws AgentException{
        if(pseudonym == null || pseudonym.length() <= PSEUDONYM_LENGTH){
            this.pseudonym = pseudonym;
        }
        else{
            throw new AgentException("Le pseudonym entré est trop long !\nMaximum " + PSEUDONYM_LENGTH + " charactères");
        }
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
    public String getLastname() {
        return lastname;
    }
    public String getFirstname() {
        return firstname;
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
        return Arrays.stream(Agent.POSSIBLE_GENDER).collect(Collectors.toList());
    }

}
