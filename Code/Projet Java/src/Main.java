import model.*;
import exception.*;

import userInterface.*;
import business.Security;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        /*String firstname = Security.cryptingeMethod1("Jeremy");
        String lastname = Security.cryptingeMethod1("Beckx");
        LocalDate birthdate = LocalDate.parse("12/01/2003", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        try {
            Agent a = new Agent(1234, lastname, firstname, birthdate, "469/374.428", "m", false,new Cell("IESN","broken dreams","081"));
            System.out.println(a);
        }catch (AgentException e){
            System.out.println(e.getMessage());
        }*/

        MainWindow mainWindow = new MainWindow();
    }
}