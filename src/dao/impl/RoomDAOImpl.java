package dao.impl;
import dao.RoomDAO;
import db.Database;
import models.Room;

import java.sql.*;

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
        catch (SQLException e){System.out.println(e.getMessage());}
    }

    @Override
    public void addRoom(Room room) {
        String sql = """ 
                INSERT INTO rooms(is_available, price, room_type)
                VALUES (?,?,?)
                """;
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
    public void getAllRooms() {
        String sql = "SELECT * FROM rooms ORDER BY room_id DESC";
        try(
                Connection con = Database.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rSet = ps.executeQuery()
        )
        {
            while (rSet.next())
            {
                System.out.println(rSet.getString("room_id"+" | "+"is_available"+" | "+"price"+" | "+"room_type"+" |"));
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void getAvailableRooms() {
        String sql = "SELECT * FROM rooms WHERE is_available = TRUE ORDER BY room_id DESC";
        try(
                Connection con = Database.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rSet = ps.executeQuery()
        )
        {
            while (rSet.next())
            {
                System.out.println(rSet.getString("room_id"+" | "+" | "+"price"+" | "+"room_type"+" |"));
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }


}