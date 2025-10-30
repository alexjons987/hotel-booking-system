package services;

import dao.impl.BookingDAOImpl;
import db.TransactionManager;
import dto.BookingViewDTO;
import models.Booking;

import java.util.List;

public class BookingService {
    BookingDAOImpl dao = new BookingDAOImpl();

    public boolean bookRoom(Booking booking) {
        try {
            TransactionManager.begin();

            boolean booked = dao.insertBooking(booking);

            if (!booked) {
                TransactionManager.rollback();
                return false;
            }

            boolean updated = dao.markRoomUnavailable(booking.getRoomID());

            if (!updated) {
                TransactionManager.rollback();
                return false;
            }

            TransactionManager.commit();
            return true;

        } catch (Exception e) {
            TransactionManager.rollback();
            return false;
        }
    }

    public List<BookingViewDTO> getAllBookings() {
        return dao.getAllBookings();
    }
}
