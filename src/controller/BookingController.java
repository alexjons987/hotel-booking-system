package controller;

import dto.BookingByEmailViewDTO;
import dto.BookingViewDTO;
import models.Booking;
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
            System.out.println("2. See all bookings");
            System.out.println("3. See booking by email");
            System.out.println("4. Cancel booking");
            System.out.println("0. Go back");
            int choice = InputHelper.readInt(scanner, 0, 4);

            switch(choice) {
                case 1 -> bookRoom(scanner);
                case 2 -> getAllBookings();
                case 3 -> getBookingsByCustomerEmail(scanner);
                case 4 -> cancelBooking(scanner);
                case 0 -> { return; }
                default -> System.out.println("Invalid choice, try again!");
            }
        }
    }

    public void bookRoom(Scanner scanner) {
        // TODO: Present a list of available rooms (from getAvailableRooms())
        System.out.println("Select desired room by its number : ");
        int room_id = InputHelper.readInt(scanner);

        // TODO: A list of customers (from getAllCustomers())
        System.out.println("Add customer id: ");
        int customer_id = InputHelper.readInt(scanner);

        LocalDate checkoutDate = InputHelper.readDate(scanner, "Add checkout date (YYYY-MM-DD): ");

        boolean success = service.bookRoom(new Booking(room_id, customer_id, checkoutDate));
        if(success) {
            System.out.println("Your booking is confirmed");
        } else {
            System.out.println("Booking failed. Please try again.");
        }
    }

    public void getAllBookings() {
        for (BookingViewDTO b : service.getAllBookings()) {
            System.out.println(b);
        }
    }
    
    public void getBookingsByCustomerEmail(Scanner scanner) {
        System.out.println("Add email address to find booking:");
        System.out.print("> ");
        String input = scanner.nextLine().trim();

        for(BookingByEmailViewDTO b : service.getBookingByEmail(input)) {
            System.out.println(b);
        }
    }

    public void cancelBooking(Scanner scanner) {
        System.out.println("Booked rooms: ");
        getAllBookings();
        System.out.println();
        System.out.println("Add booking ID to cancel: ");
        int bookingID = InputHelper.readInt(scanner);

        String result = service.cancelBooking(bookingID) ? "Booking is cancelled" : "Cancelling failed";
        System.out.println(result);
    }
}
