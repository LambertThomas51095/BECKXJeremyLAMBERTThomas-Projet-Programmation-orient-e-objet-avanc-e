package model;

public class Cell {
    private String name;
    private String address;
    private String phoneNumber;

    public Cell(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }
}
