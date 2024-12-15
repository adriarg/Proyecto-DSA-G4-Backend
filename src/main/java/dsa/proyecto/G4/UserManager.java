package dsa.proyecto.G4;

import dsa.proyecto.G4.models.User;

import java.util.List;

public interface UserManager {
    User getUsuarioPorId(String id);
    User getUsuarioPorNombre(String nombre);
    User addUsuario(User usuario);
    public User addUsuario(String id,String nombre,String contraseña);
    public User addUsuario(String nombre, String contraseña);
    List<User> getUsuarios();

    User updateUser(String id,User u);

    boolean removeUsuario(String id);

    int countUsers();
}
