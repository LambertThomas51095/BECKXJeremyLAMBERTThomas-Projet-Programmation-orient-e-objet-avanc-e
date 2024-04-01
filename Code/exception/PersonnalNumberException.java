package exception;

public class PersonnalNumberException extends Exception{
    private Integer wrongPersonnalNumber;

    public PersonnalNumberException(Integer wrongPersonnalNumber, String reason) {
        super(reason);
        this.wrongPersonnalNumber = wrongPersonnalNumber;
    }
}
