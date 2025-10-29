package controller;

import services.BookingService;

public class BookingController {
    BookingService service = new BookingService();

//    public void runCourseMenu() {
//        while(true) {
//            System.out.println("===Bookings===");
//            System.out.println("1. Add a new booking");
//            System.out.println("0. Exit");
//            int choice = input.getInt("Enter menu option: ");
//
//            switch(choice) {
//                case 1 -> addCourse();
//                case 0 -> {
//                    System.out.println("Exiting...");
//                    return;
//                }
//                default -> System.out.println("Invalid choice, try again!");
//            }
//        }
//    }
//
//    public void addCourse() {
//        String courseName = input.getString("Add name of the course: ");
//        int credits = input.getInt("Add credits: ");
//
//        service.addBooking(courseName, credits);
//        System.out.println("Booking is done");
//    }
}
