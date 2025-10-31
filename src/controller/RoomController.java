package controller;

import models.Room;
import services.RoomService;
import util.InputHelper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class RoomController {
    RoomService rService = new RoomService();

    public void runMenu(Scanner sc) {
        int menuChoice;
        do {
            System.out.println("""
                    Room Management System started.
                    1: Add room
                    2: Edit room
                    3: Get list of all rooms
                    4: Get list of available rooms
                    5: Check specific room
                    0: Return to previous menu
                    """);
            menuChoice = InputHelper.readInt(sc, 0, 5);
            switch (menuChoice) {
                case 1 -> addRoom(sc);
                case 2 -> editRoom(sc);
                case 3 -> getRoomList(false);
                case 4 -> getRoomList(true);
                case 5 -> checkRoom(roomIdLookUp(sc));
            }
        } while (menuChoice != 0);

    }

    private void addRoom(Scanner sc) {
        double price;
        String roomType;
        System.out.println("Room addition started.");
        System.out.println("Room price?");
        price = InputHelper.readDouble(sc);
        do {
            System.out.println("Room type?");
            roomType = sc.nextLine().trim();
        } while (roomType.isEmpty());
        System.out.println("Room will default to available. Access it via the room editor to occupy it.");
        rService.addRoom(new Room(0, true, BigDecimal.valueOf(price), roomType));
    }

    private void editRoom(Scanner sc) {
        Room room = roomIdLookUp(sc);
        checkRoom(room);
        System.out.println("New room price?");
        BigDecimal dec = BigDecimal.valueOf(InputHelper.readDouble(sc));
        System.out.println("Availability? 1 = available, any other = unavailable");
        boolean avlb = InputHelper.readInt(sc, 0, 1) == 1;
        System.out.println("Room type?");
        String type = sc.nextLine().trim();
        Room newRoom = new Room(room.getId(), avlb, dec, type);
        rService.editRoom(newRoom);
    }

    private void getRoomList(boolean filtered) {
        if (filtered) {
            System.out.println("Available rooms:");
            printRoomList(rService.getRoomsList(true));
        }
        else {
            System.out.println("All rooms:");
            printRoomList(rService.getRoomsList(false));
        }
    }

    private void checkRoom(Room room) {
        if (room == null) {
            System.out.println("Invalid room ID, returning to menu.");
            return;
        }
        System.out.println("Room found: " +
                room.getId() + " | " +
                room.getPrice() + " | " +
                room.isAvailable() + " | " +
                room.getRoomType());
    }

    private Room roomIdLookUp(Scanner sc) {
        Room room;
        System.out.println("room ID?");
        room = rService.getRoomById(InputHelper.readInt(sc));
        return room;
    }

    private void printRoomList(List<Room> roomsList) {
        System.out.println("ID | Price | Available | Type");
        for (Room room : roomsList) {
            System.out.println(room.getId() + " | " + room.getPrice() + " | " + room.isAvailable() + " | " + room.getRoomType());
        }
    }

}
