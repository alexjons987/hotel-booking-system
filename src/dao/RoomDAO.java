package dao;

import models.Room;

import java.util.List;

public interface RoomDAO
{
    void addRoom(Room room);
    List<Room> getRooms(Boolean filterToAvailable);
    Room getRoomById(int id);
    void editRoom(Room room);
}
