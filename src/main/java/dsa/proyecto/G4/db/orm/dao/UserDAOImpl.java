package dsa.proyecto.G4.db.orm.dao;

import dsa.proyecto.G4.db.orm.FactorySession;
import dsa.proyecto.G4.db.orm.Session;
import dsa.proyecto.G4.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class UserDAOImpl implements IUserDAO {

    final static Logger logger = Logger.getLogger(UserDAOImpl.class.getName());

    @Override
    public int addUser(String id, String nombre, String contraseña) {
        Session session = null;
        try {
            session = FactorySession.openSession();
            User user = new User(id, nombre, contraseña);
            session.save(user);
        } catch (Exception e) {
            logger.severe("Error adding user: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return 0;
    }

    @Override
    public User getUser(String id) {
        Session session = null;
        User user = null;
        try {
            session = FactorySession.openSession();
            user = (User) session.get(User.class, "id", id);
        } catch (Exception e) {
            logger.severe("Error getting user: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public User getUserByName(String nombre) {
        Session session = null;
        User user = null;
        try {
            session = FactorySession.openSession();
            user = (User) session.get(User.class, "nombre", nombre);
        } catch (Exception e) {
            logger.severe("Error getting user by name: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public void deleteUser(String id) throws SQLException {
        User user = this.getUser(id);
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.delete(user);
        } catch (Exception e) {
            logger.severe("Error deleting user: " + e.getMessage());
            throw new SQLException("Error deleting user", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void updateUser(String id, String nombre, String contraseña) throws SQLException {
        Session session = null;
        try {
            session = FactorySession.openSession();
            User user = (User) session.get(User.class, "id", id);
            if (user != null) {
                user.setNombre(nombre);
                user.setContraseña(contraseña);
                session.update(user);
                logger.info("Successfully updated user with id: " + id);
            } else {
                logger.warning("User with id " + id + " not found");
                throw new SQLException("User not found");
            }
        } catch (SQLException e) {
            logger.severe("Couldn't update user: " + e.getMessage());
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getUsers() {
        Session session = null;
        List<User> users = null;
        try {
            session = FactorySession.openSession();
            // CORRECCIÓN DEL ERROR DE CONVERSIÓN
            users = session.findAll(User.class)
                    .stream()
                    .map(obj -> (User) obj)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.severe("Error getting users: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return users;
    }
}
