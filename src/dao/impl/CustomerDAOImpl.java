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
    public Customer getCustomerById(int customerId) {
        String sql = """
                SELECT customer_id, name, email, city
                FROM customers
                WHERE customer_id = ? LIMIT 1;
                """;

        try (
                Connection connection = Database.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, customerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Customer(
                        resultSet.getInt("customer_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("city")
                );
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        String sql = """
                SELECT customer_id, name, email, city
                FROM customers;
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
    public int updateCustomerCity(Customer customer, String newCity) {
        String sql = """
                UPDATE customers
                SET city = ?
                WHERE customer_id = ? LIMIT 1;
                """;

        try (
                Connection connection = Database.getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(sql)
        ) {
            prepStatement.setString(1, newCity);
            prepStatement.setInt(2, customer.getId());

            return prepStatement.executeUpdate(); // executeUpdate() when we are using INSERT, UPDATE or DELETE
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            return 0;
        }

        return 0;
    }

    @Override
    public Optional<Customer> deleteCustomer() {
        return Optional.empty(); // TODO: Implement
    }
}
