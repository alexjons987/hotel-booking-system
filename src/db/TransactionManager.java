package db;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

    private static final ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    public static void begin() throws SQLException {
        Connection conn = Database.getConnection();
        conn.setAutoCommit(false);
        connectionHolder.set(conn);
    }

    public static void commit() {
        Connection conn = connectionHolder.get();
        try {
            if (conn != null) {
                conn.commit();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    public static void rollback() {
        Connection conn = connectionHolder.get();
        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = connectionHolder.get();
        if (conn == null) {
            // no transaction started — return a normal connection
            return Database.getConnection();
        }
        return conn;
    }

    private static void closeConnection() {
        Connection conn = connectionHolder.get();
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ignored) {
        } finally {
            connectionHolder.remove();
        }
    }
}

