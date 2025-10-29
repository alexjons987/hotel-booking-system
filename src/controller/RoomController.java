package controller;

import services.RoomService;
import util.InputHelper;

import java.util.Scanner;

public class RoomController
{
    RoomService rService = new RoomService();
    public void runMenu(Scanner sc){
        int menuChoice = 0;
        do {
            System.out.println("""
                    Room Management System started.
                    1: Add room
                    2: Edit room
                    3: Get list of rooms
                    4: Check specific room
                    0: Return to previous menu
                    """);
            menuChoice = InputHelper.readInt(sc,0,4);
            switch (menuChoice) {
                case 1 -> AddRoom();
                case 2 -> EditRoom();
                case 3 -> GetRoomList();
                case 4 -> CheckRoom();
            }
        } while (menuChoice != 0);

    }
    //todo: implement controller methods
    private void AddRoom()
    {
        
    }
    private void EditRoom()
    {

    }
    private void GetRoomList()
    {

    }
    private void CheckRoom()
    {

    }
}
