package dsa.proyecto.G2;

import dsa.proyecto.G2.orm.dao.DAO;
import dsa.proyecto.G2.models.User;
import dsa.proyecto.G2.util.ConnectionManager;

public class Main {

    public static void main(String[] args) {
        // Probar conexión a la base de datos
        ConnectionManager.testConnection();

        // Insertar un usuario de prueba
        testInsertUser();

        // Aquí inicias tu servidor normalmente (si lo tienes configurado)
        // Por ejemplo:
        // HttpServer server = startServer();
        // System.out.println("Server started at " + BASE_URI);
    }

    private static void testInsertUser() {
        try {
            DAO<User> userDAO = new DAO<>(User.class);
            System.out.println("Probando inserción de usuario de prueba...");
            String query = "INSERT INTO users (nombre, contraseña) VALUES ('test_user', 'test_password')";
            ConnectionManager.getConnection().createStatement().execute(query);
            System.out.println("Usuario de prueba insertado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al insertar el usuario de prueba");
        }
    }
}
