package services;
import dao.impl.RoomDAOImpl;
import models.Room;

public class RoomService
{
    RoomDAOImpl roomDAO = new RoomDAOImpl();
    public void addRoom(Room room) {roomDAO.addRoom(room);}
    public void getAllRooms() {roomDAO.getAllRooms();}
    public void getAvailableRooms() {roomDAO.getAvailableRooms();}
}
