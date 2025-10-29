package util;

import java.util.Scanner;

public class InputHelper {

    public static int readInt(Scanner sc) {
        while (true) {
            System.out.print("> ");
            String userInput = sc.nextLine().trim();
            try {
                return Integer.parseInt(userInput);
            } catch (NumberFormatException ignored) {
                System.out.println("Please enter an integer.");
            }
        }
    }

    public static int readInt(Scanner sc, int min, int max) {
        while (true) {
            System.out.print("> ");
            String userInput = sc.nextLine().trim();
            try {
                int choice = Integer.parseInt(userInput);
                if (choice >= min && choice <= max)
                    return choice;
            } catch (NumberFormatException ignored) {}
            System.out.printf("Please enter an allowed number (%d - %d).%n", min, max);
        }
    }
}
