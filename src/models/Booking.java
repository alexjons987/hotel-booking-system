package models;

import java.time.LocalDate;

public class Booking {
    private int id;
    private int roomID;
    private int customerID;
    private LocalDate checkoutDate;

    public Booking(int id, int roomID, int customerID, LocalDate checkoutDate) {
        this.id = id;
        this.roomID = roomID;
        this.customerID = customerID;
        this.checkoutDate = checkoutDate;
    }

    public Booking(int roomID, int customerID, LocalDate checkoutDate) {
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


