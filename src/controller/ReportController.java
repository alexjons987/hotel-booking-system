package controller;

import models.Customer;
import services.ReportService;
import util.InputHelper;

import java.util.List;
import java.util.Scanner;

public class ReportController {

    final ReportService reportService = new ReportService();

    public void runMenu(Scanner scanner) {
        int menuChoice;
        do {
            System.out.println("\n- HBS -> Statistics -");
            System.out.println("1. Show bookings per customer");
            System.out.println("2. Show avg. price on booked rooms");
            System.out.println("3. Show available rooms by date");
            System.out.println("4. Show customers with no booking record");
            System.out.println("0. Go back");
            menuChoice = InputHelper.readInt(scanner, 0, 4);

            switch (menuChoice) {
                case 1 -> showBookingsPerCustomer();
                case 2 -> showAvgPriceOnBookedRooms();
                case 3 -> showAvailableRoomsByDate(scanner);
                case 4 -> showCustomersWithNoBookingRecord();
            }
        } while (menuChoice != 0);
    }

    private void showBookingsPerCustomer() {
        System.out.println("- Bookings per customer -");
        List<Customer> customerList = reportService.getCustomersAndBookingCount();
        customerList.forEach(customer -> {
            System.out.printf(
                    "ID: %d | Name: %s | Bookings made: %d%n",
                    customer.getId(),
                    customer.getName(),
                    customer.getBookingsMade()
            );
        });
    }

    private void showAvgPriceOnBookedRooms() {
        System.out.println("- Average price on booked rooms -");
        System.out.println("[NOT IMPLEMENTED]");
    }

    private void showAvailableRoomsByDate(Scanner scanner) {
        System.out.println("- Check available rooms -");
        System.out.println("[NOT IMPLEMENTED]");
    }

    private void showCustomersWithNoBookingRecord() {
        System.out.println("- Customers with no booking record -");
        System.out.println("[NOT IMPLEMENTED]");
    }
}
