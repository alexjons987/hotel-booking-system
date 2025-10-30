package services;

import dao.impl.ReportDAOImpl;
import models.Customer;

import java.util.List;

public class ReportService {
    final ReportDAOImpl reportDAO = new ReportDAOImpl();

    public List<Customer> getCustomersAndBookingCount() {
        return reportDAO.getCustomersAndBookingCount();
    }
}
