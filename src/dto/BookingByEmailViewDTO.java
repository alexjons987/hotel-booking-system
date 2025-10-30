package dto;

import java.time.LocalDate;

public class BookingByEmailViewDTO {
    private final int roomNumber;
    private final String roomType;
    private final String guest;
    private final LocalDate checkoutDate;

    public BookingByEmailViewDTO(int roomNumber, String roomType, String guest, LocalDate checkoutDate) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.guest = guest;
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public String getGuest() {
        return guest;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    @Override
    public String toString() {
        return "BookingByEmailViewDTO {" +
                " roomNumber=" + roomNumber +
                ", roomType='" + roomType + '\'' +
                ", guest='" + guest + '\'' +
                ", checkoutDate=" + checkoutDate +
                " }";
    }
}
