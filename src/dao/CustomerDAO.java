package dao;

import models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    void addCustomer(Customer customer);
    List<Customer> getAllCustomers();
    List<Customer> findCustomerByEmail();
    Optional<Customer> updateCustomerCity();
    Optional<Customer> deleteCustomer();
}
