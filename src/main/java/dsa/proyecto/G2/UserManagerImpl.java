package dsa.proyecto.G2;

import dsa.proyecto.G2.models.User;

import java.util.LinkedList;
import java.util.List;

public class UserManagerImpl implements UserManager {
    private static UserManagerImpl instance;
    private List<User> usuarios;

    private UserManagerImpl() {
        this.usuarios = new LinkedList<>();
    }

    public static UserManagerImpl getInstance() {
        if (instance == null) {
            instance = new UserManagerImpl();
        }
        return instance;
    }

    @Override
    public User getUsuarioPorId(String id) {
        for (User usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public User getUsuarioPorNombre(String nombre) {
        for (User usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public void addUsuario(User usuario) {
        usuarios.add(usuario);
    }

    @Override
    public List<User> getUsuarios() {
        return new LinkedList<>(usuarios);
    }

    @Override
    public boolean removeUsuario(String id) {
        return usuarios.removeIf(usuario -> usuario.getId().equals(id));
    }
}
