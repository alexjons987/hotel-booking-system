package controller;

import models.Room;
import services.RoomService;
import util.InputHelper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class RoomController
{
    RoomService rService = new RoomService();
    public void runMenu(Scanner sc){
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
            menuChoice = InputHelper.readInt(sc,0,5);
            switch (menuChoice) {
                case 1 -> AddRoom(sc);
                case 2 -> EditRoom(sc);
                case 3 -> GetRoomList(false);
                case 4 -> GetRoomList(true);
                case 5 -> CheckRoom(sc);
            }
        } while (menuChoice != 0);

    }
    //todo: implement controller methods
    private void AddRoom(Scanner sc)
    {
        double price;
        String room_type;
        System.out.println("Room addition started.");
        System.out.println("Room price?");
        price = InputHelper.readDouble(sc);
        do {
            System.out.println("Room type?");
            room_type = sc.nextLine().trim();
        }while(room_type.isEmpty());
        System.out.println("Room will default to available. Access it via the room editor to occupy it.");
        rService.addRoom(new Room(0, true, BigDecimal.valueOf(price),room_type));
    }
    private void EditRoom(Scanner sc)
    {
        System.out.println("room ID?");
        Room room = rService.getRoomById(InputHelper.readInt(sc));
        if(room == null) { System.out.println("Invalid room ID, returning to menu."); return; }
        System.out.println("Room found: " +
                room.getId() + " | " +
                room.getPrice() + " | " +
                room.is_available() + " | " +
                room.getRoom_type());
        System.out.println("New room price?");
        room.setPrice(BigDecimal.valueOf(InputHelper.readDouble(sc)));
        System.out.println("Availability? 1 = available, any other = unavailable");
        room.setIs_available(InputHelper.readInt(sc,0,1) == 1);
        System.out.println("Room type?");
        room.setRoom_type(sc.nextLine().trim());
        rService.editRoom(room);
    }
    private void GetRoomList(boolean filtered)
    {
        if(filtered)
        {
            System.out.println("Available rooms:");
            PrintRoomList(rService.getRoomsList(true));
        }
        else
        {
            System.out.println("All rooms:");
            PrintRoomList(rService.getRoomsList(false));
        }
    }
    private void CheckRoom(Scanner sc)
    {
        System.out.println("room ID?");
        Room room = rService.getRoomById(InputHelper.readInt(sc));
        if(room == null) { System.out.println("Invalid room ID, returning to menu."); return; }
        System.out.println("Room found: " +
                room.getId() + " | " +
                room.getPrice() + " | " +
                room.is_available() + " | " +
                room.getRoom_type());
    }
    private void PrintRoomList(List<Room> roomsList){
        System.out.println("ID | Price | Available | Type");
        for (Room room : roomsList) {
            System.out.println(room.getId() +" | "+ room.getPrice() +" | "+ room.is_available() +" | "+ room.getRoom_type());
        }
    }
}
