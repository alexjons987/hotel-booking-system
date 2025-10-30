package dao.impl;

import dao.BookingDAO;
import db.Database;
import db.TransactionManager;
import dto.BookingViewDTO;
import models.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements BookingDAO {

    public BookingDAOImpl() {
        String sql = """
            CREATE TABLE IF NOT EXISTS bookings(
            booking_id INT PRIMARY KEY AUTO_INCREMENT,
            room_id INT,
            customer_id INT,
            end DATE,
            CONSTRAINT fk_room
                FOREIGN KEY (room_id) REFERENCES rooms(room_id),
            CONSTRAINT fk_customer
                FOREIGN KEY (customer_id) REFERENCES customers(customer_id))
            """;

        try(Connection conn = Database.getConnection();
            Statement statement = conn.createStatement()){
            statement.execute(sql);
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }

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

    public List<BookingViewDTO> getAllBookings() {
        List<BookingViewDTO> allBookings = new ArrayList<>();
        String sql = """
                SELECT b.booking_id, c.name, r.room_id, r.room_type, b.end FROM customers c
                JOIN bookings b ON c.customer_id = b.customer_id
                JOIN rooms r ON b.room_id = r.room_id;
                """;

        try(Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()) {
                allBookings.add(new BookingViewDTO(
                    rs.getInt("booking_id"),
                    rs.getString("name"),
                    rs.getInt("room_id"),
                    rs.getString("room_type"),
                    rs.getDate("end").toLocalDate()
                ));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return allBookings;
    }
}
