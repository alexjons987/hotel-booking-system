package models;

public class Customer {
    private int id;
    private final String name;
    private String email;
    private String city;
    private int bookingsMade;

    public Customer(String name, String email, String city) {
        this.name = name;
        this.email = email;
        this.city = city;
    }

    public Customer(int id, String name, String email, String city) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
    }

    public Customer(int id, String name, int bookingsMade) {
        this.id = id;
        this.name = name;
        this.bookingsMade = bookingsMade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public int getBookingsMade() {
        return bookingsMade;
    }

    public String toString() {
        return String.format("ID: %d | Name: %s | E-Mail: %s | City: %s", this.id, this.name, this.email, this.city);
    }
}
