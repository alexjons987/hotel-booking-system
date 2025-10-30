package services;

import dao.impl.CustomerDAOImpl;
import db.TransactionManager;
import models.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CustomerService {
    final CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    public void addCustomer(Customer customer) {
        customerDAO.addCustomer(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    public List<Customer> findCustomersByEmail(String searchTerm) {
        return customerDAO.findCustomersByEmail(searchTerm);
    }

    public Optional<Customer> updateCustomerCity(int customerId, String newCity) {
        Customer customer = customerDAO.getCustomerById(customerId);

        if (customerDAO.updateCustomerCity(customer, newCity) > 0) {
            return Optional.of(customer);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Customer> deleteCustomer(int customerId) {
        Optional<Customer> opt;

        // Get customer
        Customer customer = customerDAO.getCustomerById(customerId);

        try {
            TransactionManager.begin();

            // Mark rooms booked by customer as available
            if (!customerDAO.removeCustomerBookings(customer)) {
                TransactionManager.rollback();
                return Optional.empty();
            }

            // Delete customer
            if (customerDAO.deleteCustomer(customer) > 0) {
                TransactionManager.commit();
                return Optional.of(customer);
            } else {
                TransactionManager.rollback();
                return Optional.empty();
            }
        } catch (SQLException e) {
            TransactionManager.rollback();
            return Optional.empty();
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }
}
