package exception;

public class PhoneNumberException extends Exception{
    private String wrongPhoneNumber;

    public PhoneNumberException(String wrongPhoneNumber) {
        this.wrongPhoneNumber = wrongPhoneNumber;
    }

    public String getWrongPhoneNumber() {
        return wrongPhoneNumber;
    }
}
