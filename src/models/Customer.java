package models;

public class Customer {
    private final int id;
    private final String name;
    private final String email;
    private final String city;
    private final int bookingsMade;

    public Customer(String name, String email, String city) {
        this.id = -1;
        this.name = name;
        this.email = email;
        this.city = city;
        this.bookingsMade = -1;
    }

    public Customer(int id, String name, String email, String city) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.bookingsMade = -1;
    }

    public Customer(int id, String name, int bookingsMade) {
        this.id = id;
        this.name = name;
        this.email = null;
        this.city = null;
        this.bookingsMade = bookingsMade;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getCity() {
        return this.city;
    }

    public int getBookingsMade() {
        return this.bookingsMade;
    }

    public String toString() {
        return String.format("ID: %d | Name: %s | E-Mail: %s | City: %s", this.id, this.name, this.email, this.city);
    }
}
