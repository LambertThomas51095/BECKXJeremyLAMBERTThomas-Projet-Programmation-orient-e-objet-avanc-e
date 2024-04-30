import model.*;
import exception.*;

import userInterface.*;
import business.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        /*String firstname = Security.cryptingeMethod1("Jeremy");
        String lastname = Security.cryptingeMethod1("Beckx");
        LocalDate birthdate = LocalDate.parse("12/01/2003", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        try {
            Agent a = new Agent(1234, lastname, firstname, birthdate, "0469/374.428", "m", false,new Cell("IESN","broken dreams","081"));
            System.out.println(a);
        }catch (AgentException e){
            System.out.println(e.getMessage());
        }*/
        String firstname = Security.cryptingMethod("Thomas");
        String lastname = Security.cryptingMethod("Lambert");
        System.out.println(firstname);
        System.out.println(lastname);
        System.out.println(Security.decryptingMethod(firstname));
        System.out.println(Security.decryptingMethod(lastname));
        try{
            new AgentManager().getAgentsLanguages("Informatique", LocalDate.parse("2023-11-12", DateTimeFormatter.ofPattern("yyyy-MM-dd"))).stream().forEach(s -> System.out.println(s));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        MainWindow mainWindow = new MainWindow();
    }
}