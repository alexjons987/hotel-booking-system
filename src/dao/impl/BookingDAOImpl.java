package dao.impl;

import dao.BookingDAO;
import db.Database;
import db.TransactionManager;
import models.Booking;

import java.sql.*;

public class BookingDAOImpl implements BookingDAO {

    @Override
    public boolean insertBooking(Booking booking) {
        String sql = "INSERT INTO bookings (room_id, customer_id, end) VALUES (?, ?, ?)";
        try (PreparedStatement statement = TransactionManager.getConnection()
                .prepareStatement(sql)) {

            statement.setInt(1, booking.getRoomID());
            statement.setInt(2, booking.getCustomerID());
            statement.setDate(3, Date.valueOf(booking.getCheckoutDate()));

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean markRoomUnavailable(int roomId) {
        String sql = "UPDATE rooms SET is_available = false WHERE room_id = ? AND is_available = true";
        try (PreparedStatement statement = TransactionManager.getConnection()
                .prepareStatement(sql)) {

            statement.setInt(1, roomId);
            return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            return false;
        }
    }
}
