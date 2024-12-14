package dsa.proyecto.G2;

import dsa.proyecto.G2.models.User;

import java.util.List;

public interface UserManager {
    User getUsuarioPorId(String id);
    User getUsuarioPorNombre(String nombre);
    void addUsuario(User usuario);
    List<User> getUsuarios();
    boolean removeUsuario(String id);
}
