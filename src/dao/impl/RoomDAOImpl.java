package dao.impl;
import dao.RoomDAO;
import db.Database;
import models.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO
{
    public RoomDAOImpl(){
        String sql = """
                CREATE TABLE IF NOT EXISTS rooms (
                room_id INT PRIMARY KEY AUTO_INCREMENT,
                is_available BOOLEAN NOT NULL,
                price DECIMAL(10,2) NOT NULL,
                room_type TEXT NOT NULL
                )
                """;
        try (
                Connection con = Database.getConnection();
                Statement st = con.createStatement()
        )
        {
            st.execute(sql);
        }
        catch (SQLException e){System.out.println("SQLException: " + e.getMessage());}
    }

    @Override
    public void addRoom(Room room) {
        String sql = "INSERT INTO rooms(is_available, price, room_type) VALUES (?,?,?)";
        try(
                Connection con = Database.getConnection();
                PreparedStatement pStat = con.prepareStatement(sql)
        )
        {
            pStat.setBoolean(1, room.is_available());
            pStat.setBigDecimal(2,room.getPrice());
            pStat.setString(3, room.getRoom_type());
            pStat.executeUpdate();
        }
        catch (SQLException e){System.out.println("SQLException: " + e.getMessage());}
    }

    @Override
    public List<Room> getRooms(Boolean filterToAvailable) {
        String sql = "SELECT * FROM rooms ORDER BY room_id ASC";
        if(filterToAvailable){sql = "SELECT * FROM rooms WHERE is_available = TRUE ORDER BY room_id ASC";}
        List<Room> rooms = new ArrayList<>();
        try(
                Connection con = Database.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rSet = ps.executeQuery()
        )
        {
            while (rSet.next())
            {
                rooms.add(new Room(
                        rSet.getInt(1),
                        rSet.getBoolean(2),
                        rSet.getBigDecimal(3),
                        rSet.getString(4)));
            }
        }
        catch (SQLException e) {System.out.println("SQLException: " + e.getMessage());}
        return rooms;
    }

    public Room getRoomById(int id) {
        String sql = "SELECT * FROM rooms WHERE room_id = ?";
        Room retRoom;
        try(
                Connection con = Database.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rSet = ps.executeQuery()
        )
        {
            if(rSet.next()) {
                retRoom = new Room(
                        rSet.getInt(1),
                        rSet.getBoolean(2),
                        rSet.getBigDecimal(3),
                        rSet.getString(4));
                return retRoom;
            }
        }
        catch (SQLException e) {System.out.println("SQLException: " + e.getMessage());}
        return null;
    }

    public void EditRoom(Room room) {
        String sql = """
                UPDATE rooms
                SET is_available = ?, price = ?, room_type = ?
                WHERE room_id = ?
                """;
        try(
                Connection con = Database.getConnection();
                PreparedStatement pStat = con.prepareStatement(sql)
        )
        {
            pStat.setBoolean(1, room.is_available());
            pStat.setBigDecimal(2,room.getPrice());
            pStat.setString(3, room.getRoom_type());
            pStat.setInt(4, room.getId());
            pStat.executeUpdate();
        }
        catch (SQLException e){System.out.println("SQLException: " + e.getMessage());}
    }

}