package models;

public class Customer {
    private int id;
    private final String name;
    private final String email;
    private final String city;

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

    public String toString() {
        return String.format("ID: %d | Name: %s | E-Mail: %s | City: %s", this.id, this.name, this.email, this.city);
    }
}
