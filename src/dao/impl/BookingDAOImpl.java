package dao.impl;

import dao.BookingDAO;
import db.Database;
import db.TransactionManager;
import dto.BookingByEmailViewDTO;
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

    @Override
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
                    rs.getString("roomType"),
                    rs.getDate("end").toLocalDate()
                ));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return allBookings;
    }

    public List<BookingByEmailViewDTO> getBookingByEmail(String email) {
        List<BookingByEmailViewDTO> bookingByEmail = new ArrayList<>();

        String sql = """
                SELECT r.room_id, r.room_type, c.name, b.end FROM rooms r
                JOIN bookings b ON r.room_id = b.room_id
                JOIN customers c ON b.customer_id = c.customer_id
                WHERE c.email = ?;
                """;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    bookingByEmail.add(
                            new BookingByEmailViewDTO(
                                    rs.getInt("room_id"),
                                    rs.getString("room_type"),
                                    rs.getString("name"),
                                    rs.getDate("end").toLocalDate()
                            )
                    );
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookingByEmail;
    }

    @Override
    public Integer getRoomIdByBookingId(int bookingID) {
        String sql = """
                SELECT room_id FROM bookings
                WHERE booking_id = ?;
                """;

        try(PreparedStatement statement = TransactionManager
                .getConnection()
                .prepareStatement(sql)) {
            statement.setInt(1, bookingID);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                return rs.getInt("room_id");
            }

        } catch(SQLException e) {
            return null;
        }
        return null;
    }

    @Override
    public boolean deleteBooking(int bookingID) {
        String sql = "DELETE FROM bookings WHERE booking_id = ?";
        try (PreparedStatement statement = TransactionManager.getConnection()
                .prepareStatement(sql)) {

            statement.setInt(1, bookingID);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean markRoomAvailable(int roomId) {
        String sql = "UPDATE rooms SET is_available = true WHERE room_id = ? AND is_available = false";
        try (PreparedStatement statement = TransactionManager.getConnection()
                .prepareStatement(sql)) {

            statement.setInt(1, roomId);
            return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            return false;
        }
    }
}
