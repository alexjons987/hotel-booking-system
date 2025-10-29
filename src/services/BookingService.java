package services;

import dao.impl.BookingDAOImpl;
import models.Booking;

import java.time.LocalDate;

public class BookingService {
    BookingDAOImpl dao = new BookingDAOImpl();

    public boolean bookRoom(int room_id, int customer_id, LocalDate checkoutDate) {
        // TODO: validate room is available and that customer exists

        Booking booking = new Booking(room_id, customer_id, checkoutDate);
        return dao.bookRoom(booking);

        // TODO: set room as unavailable
    }
}
