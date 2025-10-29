package dao.impl;

import dao.CustomerDAO;
import db.Database;
import models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        String sql = """
                SELECT customer_id, name, email, city FROM customers;
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
                                resultSet.getString("email"),
                                resultSet.getString("city")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public List<Customer> findCustomersByEmail(String searchTerm) {
        List<Customer> foundCustomers = new ArrayList<>();

        String sql = """
                SELECT customer_id, name, email, city
                FROM customers
                WHERE email LIKE ?;
                """;

        try (
                Connection connection = Database.getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(sql);
        ) {
            prepStatement.setString(1, "%" + searchTerm + "%");

            ResultSet resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                foundCustomers.add(
                        new Customer(
                                resultSet.getInt("customer_id"),
                                resultSet.getString("name"),
                                resultSet.getString("email"),
                                resultSet.getString("city")
                        )
                );
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foundCustomers;
    }

    @Override
    public Optional<Customer> updateCustomerCity() {
        return Optional.empty(); // TODO: Implement
    }

    @Override
    public Optional<Customer> deleteCustomer() {
        return Optional.empty(); // TODO: Implement
    }
}
