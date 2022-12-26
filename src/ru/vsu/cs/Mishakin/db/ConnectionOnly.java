package ru.vsu.cs.Mishakin.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionOnly {
    public static Connection getConnection() throws SQLException {
        String user = "postgres";
        String password = "111";
        String url = "jdbc:postgresql://localhost:1503/Rent";
        System.out.println("successfully connect");
        return DriverManager.getConnection(url, user, password);
    }
}
