package dao.impl;

import dao.ReportDAO;
import db.Database;
import models.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReportDAOImpl implements ReportDAO {

    @Override
    public List<Customer> getCustomersAndBookingCount() {
        List<Customer> customers = new ArrayList<>();

        String sql = """
                SELECT customers.customer_id, customers.name, COUNT(bookings.booking_id) as total_bookings
                FROM customers
                LEFT JOIN
                	bookings
                    ON bookings.customer_id = customers.customer_id
                GROUP BY customers.customer_id, customers.name
                ORDER BY total_bookings DESC;
                """;

        try (
                Connection connection = Database.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getInt("customer_id"),
                                resultSet.getString("name"),
                                resultSet.getInt("total_bookings")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }
}
