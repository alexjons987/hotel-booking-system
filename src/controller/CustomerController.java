package controller;

import models.Customer;
import services.CustomerService;
import util.InputHelper;

import java.util.List;
import java.util.Scanner;

public class CustomerController {

    CustomerService customerService = new CustomerService();

    public void runMenu(Scanner scanner) {
        int menuChoice;
        do {
            System.out.println("\n- HBS -> Manage customers -");
            System.out.println("1. Add new customer");
            System.out.println("2. Show all customers");
            System.out.println("3. Search customer by e-mail");
            System.out.println("4. Update customer city");
            System.out.println("5. Remove customer");
            System.out.println("0. Go back");
            menuChoice = InputHelper.readInt(scanner, 0, 5);

            switch (menuChoice) {
                case 1 -> addNewCustomer(scanner);
                case 2 -> showAllCustomers();
                case 3 -> findCustomerByEmail(scanner);
                case 4 -> updateCustomerCity(scanner);
                case 5 -> removeCustomer(scanner);
            }
        } while (menuChoice != 0);
    }

    private void addNewCustomer(Scanner scanner) {
        System.out.println("- Add new customer -");
        System.out.print("Enter new customer name: ");
        String newCustomerName = scanner.nextLine().trim();

        System.out.print("Enter new customer email: ");
        String newCustomerEmail = scanner.nextLine().trim(); // TODO: Add e-mail string verification

        System.out.print("Enter new customer city: ");
        String newCustomerCity = scanner.nextLine().trim();

        Customer customer = new Customer(newCustomerName, newCustomerEmail, newCustomerCity);

        customerService.addCustomer(customer);
    }

    private void showAllCustomers() {
        System.out.println("- Show all customers -");
        List<Customer> customers = customerService.getAllCustomers();
        customers.forEach(System.out::println);
    }

    private void findCustomerByEmail(Scanner scanner) {
        System.out.println("- Search customer by e-mail -");
        System.out.println("[NOT IMPLEMENTED]"); // TODO: Implement
    }

    private void updateCustomerCity(Scanner scanner) {
        System.out.println("- Update customer city -");
        System.out.println("[NOT IMPLEMENTED]"); // TODO: Implement
    }

    private void removeCustomer(Scanner scanner) {
        System.out.println("- Remove customer -");
        System.out.println("[NOT IMPLEMENTED]"); // TODO: Implement
    }
}
