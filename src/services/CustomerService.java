package services;

import dao.impl.CustomerDAOImpl;
import models.Customer;

import java.util.List;

public class CustomerService {
    CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    public void addCustomer(Customer customer) {
        customerDAO.addCustomer(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }
}
