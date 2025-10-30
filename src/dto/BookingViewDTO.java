package dto;

import java.time.LocalDate;

public class BookingViewDTO {
    private final int bookingID;
    private final String guestName;
    private final int roomNumber;
    private final String roomType;
    private final LocalDate checkoutDate;

    public BookingViewDTO(int bookingID, String guestName, int roomNumber, String roomType, LocalDate checkoutDate) {
        this.bookingID = bookingID;
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.checkoutDate = checkoutDate;
    }

    public int getBookingID() {
        return bookingID;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    @Override
    public String toString() {
        return "BookingViewDTO {" +
                " bookingID=" + bookingID +
                ", guest='" + guestName + '\'' +
                ", roomNumber=" + roomNumber +
                ", roomType='" + roomType + '\'' +
                ", checkoutDate=" + checkoutDate +
                " }";
    }
}
