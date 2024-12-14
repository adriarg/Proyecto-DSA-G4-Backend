package dsa.proyecto.G2;

import dsa.proyecto.G2.models.User;

import java.util.List;

public interface UserManager {
    User getUsuarioPorId(String id);
    User getUsuarioPorNombre(String nombre);
    User addUsuario(User usuario);
    public User addUsuario(String id,String nombre,String contraseña);
    public User addUsuario(String nombre, String contraseña);
    List<User> getUsuarios();
    boolean removeUsuario(String id);
}
