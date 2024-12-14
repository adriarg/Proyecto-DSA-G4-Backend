package dsa.proyecto.G2;

import dsa.proyecto.G2.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class UserManagerImpl implements UserManager {
    private static UserManagerImpl instance;
    private Map<String, User> usuarios;

    private UserManagerImpl() {
        this.usuarios = new HashMap<>();
    }

    public static UserManagerImpl getInstance() {
        if (instance == null) {
            instance = new UserManagerImpl();
        }
        return instance;
    }

    @Override
    public User getUsuarioPorId(String id) {
        return usuarios.get(id);
    }

    @Override
    public User getUsuarioPorNombre(String nombre) {
        return usuarios.values().stream()
                .filter(u -> u.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addUsuario(User usuario) {
        usuarios.put(usuario.getId(), usuario);
    }

    // Nuevo método para obtener todos los usuarios
    public List<User> getUsuarios() {
        return new ArrayList<>(usuarios.values());
    }
}
