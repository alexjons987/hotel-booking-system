package dao;

import models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    void addCustomer(Customer customer);

    Customer getCustomerById(int customerId);

    List<Customer> getAllCustomers();

    List<Customer> findCustomersByEmail(String searchTerm);

    int updateCustomerCity(Customer customer, String newCity);

    Optional<Customer> deleteCustomer();
}
