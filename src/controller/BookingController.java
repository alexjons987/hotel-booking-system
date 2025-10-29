package controller;

import services.BookingService;
import util.InputHelper;

import java.time.LocalDate;
import java.util.Scanner;

public class BookingController {
    BookingService service = new BookingService();

    public void runBookingMenu(Scanner scanner) {
        while(true) {
            System.out.println("===Bookings===");
            System.out.println("1. Add a new booking");
            System.out.println("0. Go back");
            int choice = InputHelper.readInt(scanner, 0, 1);

            switch(choice) {
                case 1 -> bookRoom(scanner);
                // case 2 -> getAllBookings();
                // case 3 -> cancelBooking();
                case 0 -> { return; }
                default -> System.out.println("Invalid choice, try again!");
            }
        }
    }

    public void bookRoom(Scanner scanner) {
        // TODO: Present a list of available rooms (from getAvailableRooms())
        System.out.println("Select the desired room by its number : ");
        int room_id = InputHelper.readInt(scanner);

        // TODO: A list of customers (from getAllCustomers())
        System.out.println("Add customer id: ");
        int customer_id = InputHelper.readInt(scanner);

        LocalDate checkoutDate = InputHelper.readDate(scanner, "Add checkout date (YYYY-MM-DD): ");

        boolean success = service.bookRoom(room_id, customer_id, checkoutDate);
        if(success) {
            System.out.println("Your booking is confirmed");
        } else {
            System.out.println("Booking failed. Please try again.");
        }
    }
}
