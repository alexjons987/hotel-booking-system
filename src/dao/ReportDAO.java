package dao;

import models.Customer;

import java.util.List;

public interface ReportDAO {
    List<Customer> getCustomersAndBookingCount();
}
