package model;

public enum RegularExpression {
    PHONE_NUMBER("^0\\d{2,3}/(\\d{2}\\.\\d{2}\\.\\d{2}|\\d{3}\\.\\d{3})$"),
    DATE_FORMAT("^\\d{2}/\\d{2}/\\d{4}$"),
    ONLY_NUMBER("^\\d+$");

    private String text;

    RegularExpression (String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
