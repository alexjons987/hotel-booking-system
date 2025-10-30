import controller.BookingController;
import controller.CustomerController;
import controller.RoomController;
import controller.ReportController;
import util.InputHelper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingController bookingController = new BookingController();
        CustomerController customerController = new CustomerController();
        ReportController reportController = new ReportController();
        RoomController roomController = new RoomController();

        int menuChoice;
        do {
            System.out.println("\n- HBS -");
            System.out.println("1. Manage customers");
            System.out.println("2. Manage rooms");
            System.out.println("3. Manage bookings");
            System.out.println("4. View hotel statistics");
            System.out.println("0. Exit");
            menuChoice = InputHelper.readInt(scanner, 0, 4);

            switch (menuChoice) {
                case 1:
                    customerController.runMenu(scanner);
                    break;
                case 2:
                    roomController.runMenu(scanner);
                    break;
                case 3:
                    bookingController.runBookingMenu(scanner);
                    break;
                case 4:
                    reportController.runMenu(scanner);
                    break;

            }
        } while (menuChoice != 0);
        System.out.println("Goodbye!");
    }
}
