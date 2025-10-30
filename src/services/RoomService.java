package services;
import dao.impl.RoomDAOImpl;
import models.Room;
import java.util.List;

public class RoomService
{
    RoomDAOImpl roomDAO = new RoomDAOImpl();

    public void addRoom(Room room) {
        roomDAO.addRoom(room);
    }

    public void editRoom(Room room) {
        roomDAO.editRoom(room);
    }

    public List<Room> getRoomsList(boolean filterToAvailable) {
        return roomDAO.getRooms(filterToAvailable);
    }

    public Room getRoomById(int id) {
        return roomDAO.getRoomById(id);
    }
}
