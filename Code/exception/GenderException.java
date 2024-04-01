package exception;

public class GenderException extends Exception{
    private String wrongGender;

    public GenderException(String wrongGender) {
        this.wrongGender = wrongGender;
    }

    public String getWrongGender() {
        return wrongGender;
    }
}
