package dao;

import dto.BookingViewDTO;
import models.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingDAO {
    boolean insertBooking(Booking booking);
    boolean markRoomUnavailable(int roomId);
    List<BookingViewDTO> getAllBookings();
    boolean deleteBooking(int bookingID);
    boolean markRoomAvailable(int roomId);
    Integer getRoomIdByBookingId(int bookingID);

}
