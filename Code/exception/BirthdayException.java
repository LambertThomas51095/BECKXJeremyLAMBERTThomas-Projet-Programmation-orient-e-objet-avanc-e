package exception;

import java.time.LocalDate;

public class BirthdayException extends Exception{
    private LocalDate wrongBirthday;

    public BirthdayException(LocalDate wrongBirthday) {
        this.wrongBirthday = wrongBirthday;
    }

    public LocalDate getWrongBirthday() {
        return wrongBirthday;
    }
}
