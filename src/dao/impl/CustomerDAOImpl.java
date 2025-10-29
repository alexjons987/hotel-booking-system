package dao.impl;

import dao.CustomerDAO;
import db.Database;
import models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDAOImpl implements CustomerDAO {

    public CustomerDAOImpl() {
        String sql = """
                CREATE TABLE IF NOT EXISTS customers (
                	customer_id INT PRIMARY KEY AUTO_INCREMENT,
                	name TEXT,
                    email TEXT,
                    city TEXT
                );
                """;
        try (
                Connection connection = Database.getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCustomer(Customer customer) {
        String sql = """
                INSERT INTO customers(name, email, city)
                    VALUES  (?, ?, ?);
                """;

        try (
                Connection connection = Database.getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(sql)
        ) {
            prepStatement.setString(1, customer.getName());
            prepStatement.setString(2, customer.getEmail());
            prepStatement.setString(3, customer.getCity());

            prepStatement.executeUpdate(); // executeUpdate() when we are using INSERT, UPDATE or DELETE
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
