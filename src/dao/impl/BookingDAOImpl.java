package dao.impl;

import dao.BookingDAO;
import db.Database;
import models.Booking;

import java.sql.*;

public class BookingDAOImpl implements BookingDAO {

    @Override
    public boolean bookRoom(Booking booking) {
        String sql = "INSERT INTO bookings (room_id, customer_id, end) VALUES (?, ?, ?)";

        try(Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, booking.getRoomID());
            statement.setInt(2, booking.getCustomerID());
            // Convert to java.sql.Date
            statement.setDate(3, Date.valueOf(booking.getCheckoutDate()));

            int affectedRows = statement.executeUpdate();
            boolean isSuccess = affectedRows > 0;
            if(isSuccess) {
                // TODO: update room table (is_available false)
                // probably a transaction?
            }

            return isSuccess;
        } catch (SQLException e) {
            System.err.println("The booking failed: " + e.getMessage());
            return false;
        }
    }

}
