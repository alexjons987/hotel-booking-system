package models;

import java.time.LocalDate;

public class Booking {
    private int id;
    private int roomID;
    private int customerID;
    private LocalDate endOfStay;

    public Booking(int id, int roomID, int customerID, LocalDate endOfStay) {
        this.id = id;
        this.roomID = roomID;
        this.customerID = customerID;
        this.endOfStay = endOfStay;
    }

    public Booking(int roomID, int customerID, LocalDate endOfStay) {
        this.roomID = roomID;
        this.customerID = customerID;
        this.endOfStay = endOfStay;
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

    public LocalDate getEndOfStay() {
        return endOfStay;
    }
}


