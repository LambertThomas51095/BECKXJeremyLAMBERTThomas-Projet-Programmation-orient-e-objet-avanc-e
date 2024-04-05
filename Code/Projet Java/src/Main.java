import model.*;
import exception.*;
import userInterface.*;


import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        MainWindow mainWindow = new MainWindow();

        /*try{
            Agent a1 = new Agent(1234,"Beckx","Jérémy",LocalDate.of(2003,1,12),"111/22.33.44","l",false);
            System.out.println(a1);
        }catch(GenderException genderException){
            System.out.println("Attention <" + genderException.getWrongGender() + "> n'est pas un genre possible parmis "+ Agent.getPossibleGender());
        }catch(BirthdayException birthdayException){
            System.out.println("Attention votre année de naissance ne peut pas dépasser la date du jour : "+LocalDate.now()+".\nVous avez introduit "+birthdayException.getWrongBirthday());
        }catch(PersonnalNumberException personnalNumberException){
            System.out.println("Attention le matricule n'est pas correct pour raison : "+personnalNumberException.getMessage()+".\nVous avez introduit : "+personnalNumberException.getWrongPersonnalNumber());
        }catch(PhoneNumberException phoneNumberException){
            System.out.println("Attention le numéro de gsm introduit : "+phoneNumberException.getWrongPhoneNumber()+" ne correpsond pas à un des modèles demandés :\n"+Agent.PHONE_PATTERN_1+"\n"+Agent.PHONE_PATTERN_2);
        }*/
    }
}