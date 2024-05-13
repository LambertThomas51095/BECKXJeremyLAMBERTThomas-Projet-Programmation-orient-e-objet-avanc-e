import model.*;
import exception.*;

import  business.Security;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;


public class TestClass {
private Agent agent1;

    @BeforeEach
    public void setUp(){
        try{
            agent1 = new Agent(1234,"beckx","jeremy", LocalDate.of(2003,01,12),"0123/45.67.89","m",false,new Cell("iesn","broekn dreams","081/12.12.12"));
        }catch(AgentException e){
            System.out.println(e.getMessage());
        }
    }

    // Birthdate test
    @Test
    public void testBirthdateTomorrow(){
        assertThrows(AgentException.class,()-> agent1.setBirthdate(LocalDate.of(2025,12,12)));
    }
    @Test
    public void testBirthdateToday(){
        try{
            LocalDate date = LocalDate.now();
            agent1.setBirthdate(date);
            assertEquals(date,agent1.getBirthdate());
        }catch(AgentException e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testBirthdateYesterday(){
        try{
            LocalDate date = LocalDate.now().minusDays(1);
            agent1.setBirthdate(date);
            assertEquals(date,agent1.getBirthdate());
        }catch(AgentException e){
            System.out.println(e.getMessage());
        }
    }

    // Gender tests
    @Test
    public void testGenderM(){
        try{
            agent1.setGender("M");
            assertEquals("M",agent1.getGender());
        }catch(AgentException e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testGenderF(){
        try{
            agent1.setGender("F");
            assertEquals("F",agent1.getGender());
        }catch(AgentException e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testGenderX(){
        try{
            agent1.setGender("X");
            assertEquals("X",agent1.getGender());
        }catch(AgentException e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testGenderm(){
        try{
            agent1.setGender("m");
            assertEquals("M",agent1.getGender());
        }catch(AgentException e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testGenderf(){
        try{
            agent1.setGender("f");
            assertEquals("F",agent1.getGender());
        }catch(AgentException e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testGenderx(){
        try{
            agent1.setGender("x");
            assertEquals("X",agent1.getGender());
        }catch(AgentException e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testGenderA(){
        assertThrows(AgentException.class, () -> agent1.setGender("A"));
    }
    @Test
    public void testGendera(){
        assertThrows(AgentException.class, () -> agent1.setGender("a"));
    }

    // PhoneNumber tests
    @Test
    public void testPhoneNumberPattern1(){
        try{
            agent1.setPhoneNumber("0469/53.26.98");
            assertEquals("0469/53.26.98",agent1.getPhoneNumber());
        }catch(AgentException e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testPhoneNumberPattern2(){
        try{
            agent1.setPhoneNumber("0469/532.698");
            assertEquals("0469/532.698",agent1.getPhoneNumber());
        }catch(AgentException e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testPhoneNumberRandom1(){
        assertThrows(AgentException.class, () -> agent1.setPhoneNumber("469/532698"));
    }
    @Test
    public void testPhoneNumberRandom2(){
        assertThrows(AgentException.class, () -> agent1.setPhoneNumber("469532698"));
    }
    @Test
    public void testPhoneNumberRandom3(){
        assertThrows(AgentException.class, () -> agent1.setPhoneNumber("469/53.26.984"));
    }
    @Test
    public void testPhoneNumberRandom4(){
        assertThrows(AgentException.class, () -> agent1.setPhoneNumber("4469/53.26.98"));
    }

    // Security test
    @Test
    public void testCryptingName(){
        assertEquals("ࡋ࠸ࡃࡄ࠼ࠧ",Security.cryptingMethod("Thomas"));
    }
    @Test
    public void testDecryptingName(){
        assertEquals("Thomas",Security.decryptingMethod("ࡋ࠸ࡃࡄ࠼ࠧ"));
    }
}
