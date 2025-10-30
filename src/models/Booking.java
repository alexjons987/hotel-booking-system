package models;

import java.time.LocalDate;

public class Booking {
    private final int id;
    private final int roomID;
    private final int customerID;
    private final LocalDate checkoutDate;

    public Booking(int id, int roomID, int customerID, LocalDate checkoutDate) {
        this.id = id;
        this.roomID = roomID;
        this.customerID = customerID;
        this.checkoutDate = checkoutDate;
    }

    public Booking(int roomID, int customerID, LocalDate checkoutDate) {
        this.id = -1; // id not assigned by DB yet
        this.roomID = roomID;
        this.customerID = customerID;
        this.checkoutDate = checkoutDate;
    }

    public int getId() {
        return id;
    }

    public int getRoomID() {
        return roomID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }
}


