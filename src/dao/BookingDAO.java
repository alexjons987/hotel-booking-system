package dao;

import models.Booking;

import java.time.LocalDate;

public interface BookingDAO {
    boolean insertBooking(Booking booking);
    boolean markRoomUnavailable(int roomId);
}
