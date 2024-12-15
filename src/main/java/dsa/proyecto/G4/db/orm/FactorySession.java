package dsa.proyecto.G4.db.orm;

import dsa.proyecto.G4.db.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactorySession {

    public static Session openSession() {
        Connection conn = getConnection();
        return new SessionImpl(conn);
    }

    public static Connection getConnection() {
        String db = DBUtils.getDb();
        String host = DBUtils.getDbHost();
        String port = DBUtils.getDbPort();
        String user = DBUtils.getDbUser();
        String pass = DBUtils.getDbPasswd();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://" + host + ":" + port + "/" +
                    db + "?user=" + user + "&password=" + pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
