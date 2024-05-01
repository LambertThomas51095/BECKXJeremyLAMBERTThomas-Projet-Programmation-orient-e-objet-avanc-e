import model.*;
import exception.*;

import userInterface.*;
import business.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        /*
        String firstname = Security.cryptingMethod("Thomas");
        String lastname = Security.cryptingMethod("Lambert");
        System.out.println(firstname);
        System.out.println(lastname);
        System.out.println(Security.decryptingMethod(firstname));
        System.out.println(Security.decryptingMethod(lastname));
        try{
            new AgentManager().getContacts(1).stream().forEach(s -> System.out.println(s));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        String dateText = "02/02/2024";
        Pattern pattern = Pattern.compile(RegularExpression.DATE_FORMAT.toString());
        Matcher matcher = pattern.matcher(dateText);
        if(matcher.find()) {
            LocalDate date = LocalDate.parse(dateText, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if(!date.isAfter(LocalDate.now())){
                System.out.println("ok");
            }else{
                System.out.println("plus grand que ajd");
            }

        }*/

        MainWindow mainWindow = new MainWindow();
    }
}