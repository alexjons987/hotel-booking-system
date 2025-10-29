package dao.impl;
import dao.RoomDAO;
import db.Database;
import models.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RoomDAOImpl implements RoomDAO
{
    public RoomDAOImpl(){
        String sql = """
                CREATE TABLE IF NOT EXISTS rooms (
                room_id INT PRIMARY KEY AUTO_INCREMENT,
                is_available BOOLEAN NOT NULL,
                price DECIMAL(10,2) NOT NULL,
                room_type TEXT NOT NULL
                );
                """;
        try (Statement statement = Database.getConnection().createStatement()) {statement.execute(sql);}
        catch (SQLException e){System.out.println(e.getMessage());}
    }

    @Override
    public void addRoom(Room room) {
        String sql = """ 
                INSERT INTO rooms(is_available, price, room_type)
                VALUES (?,?,?)
                """;
        try(PreparedStatement pStat = Database.getConnection().prepareStatement(sql))
        {
            pStat.setBoolean(1, room.is_available());
            pStat.setBigDecimal(2,room.getPrice());
            pStat.setString(3, room.getRoom_type());
            pStat.executeUpdate();
        }
        catch (SQLException e){System.out.println("SQLException: " + e.getMessage());}
    }
}
