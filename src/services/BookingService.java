package services;

import dao.impl.BookingDAOImpl;
import db.TransactionManager;
import dto.BookingByEmailViewDTO;
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

    public List<BookingByEmailViewDTO> getBookingByEmail(String email) {
        return dao.getBookingByEmail(email);
    }

    public boolean cancelBooking(int bookingID) {
        try {
            TransactionManager.begin();

           Integer roomID = dao.getRoomIdByBookingId(bookingID);

            if (roomID == null) {
                TransactionManager.rollback();
                return false;
            }

            boolean deleted = dao.deleteBooking(bookingID);
            boolean updated = dao.markRoomAvailable(roomID);

            if(deleted && updated) {
                TransactionManager.commit();
                return true;
            }else {
                TransactionManager.rollback();
                return false;
            }

        } catch (Exception e) {
            TransactionManager.rollback();
            return false;
        }
    }
}
