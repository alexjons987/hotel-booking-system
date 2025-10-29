package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/hotel_db";
    static final String DB_USER_NAME = System.getenv("MYSQL_USER");
    static final String DB_USER_PASSWORD = System.getenv("MYSQL_PASSWORD");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASSWORD);
    }
}
