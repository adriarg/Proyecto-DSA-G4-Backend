package dsa.proyecto.G2.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static String url = "jdbc:mariadb://localhost:3306/proyecto_dsa";
    private static String username = "root";
    private static String password = "";
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al conectar con la base de datos");
            }
        }
        return connection;
    }

    public static void testConnection() {
        try {
            getConnection(); // Solo llama a getConnection() para verificar si se puede establecer la conexión
            System.out.println("Conexión exitosa a la base de datos");
        } catch (Exception e) {
            System.err.println("Error al probar la conexión");
            e.printStackTrace();
        }
    }

}
