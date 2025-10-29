package controller;

import models.Customer;
import services.CustomerService;
import util.InputHelper;

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
                case 1:
                    addNewCustomer(scanner);
                    break;
                case 2:
                    System.out.println("- Show all customers -");

                    break;
                case 3:
                    System.out.println("- Search customer by e-mail -");

                    break;
                case 4:
                    System.out.println("- Update customer city -");

                    break;
                case 5:
                    System.out.println("- Remove customer -");
                    break;

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
}
