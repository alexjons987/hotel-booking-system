package services;

import dao.impl.CustomerDAOImpl;
import models.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerService {
    CustomerDAOImpl customerDAO = new CustomerDAOImpl();

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
}
