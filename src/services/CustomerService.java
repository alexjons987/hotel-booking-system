package services;

import dao.impl.CustomerDAOImpl;
import models.Customer;

public class CustomerService {
    CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    public void addCustomer(Customer customer) {
        customerDAO.addCustomer(customer);
    }
}
