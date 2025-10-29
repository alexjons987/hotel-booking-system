package services;
import dao.impl.RoomDAOImpl;
import models.Room;

public class RoomService
{
    RoomDAOImpl roomDAO = new RoomDAOImpl();
    public void addRoom(Room room) {roomDAO.addRoom(room);}
    public void editRoom(Room room) {roomDAO.EditRoom(room);}
    public void getRoomsList(boolean filterToAvailable) {roomDAO.getRooms(filterToAvailable);}
}
