package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {

    private static final String URL = "jdbc:mysql://localhost:3306/db_minpro4_satwa";
    private static final String USER = "root";
    private static final String PASSWORD = ""; //

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println(">> Woopss, gagal konek ke database Bosku: " + e.getMessage());
            return null;
        }
    }
}