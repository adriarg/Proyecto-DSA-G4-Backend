package dsa.proyecto.G4;

import dsa.proyecto.G4.models.User;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class UserManagerImpl implements UserManager {
    private static UserManagerImpl instance;
    private List<User> usuarios;
    final static Logger logger = Logger.getLogger(String.valueOf(UserManagerImpl.class));

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
    public User addUsuario(User usuario) {
        usuarios.add(usuario);

        return usuario;
    }

    public User addUsuario(String id,String nombre,String contraseña){
        return this.addUsuario(new User(id, nombre,contraseña));
    }
    public User addUsuario(String nombre, String contraseña){
        return this.addUsuario(null,nombre,contraseña);
    }

    @Override
    public List<User> getUsuarios() {
        return new LinkedList<>(usuarios);
    }

    @Override
    public User updateUser(String id,User u){
        User u1 = this.getUsuarioPorId(id);
        if(u!=null){
           removeUsuario(id);
            u1.setNombre(u.getNombre());
            u1.setContraseña(u.getContraseña());
            addUsuario(u1);

        }else {
            logger.warning("not found"+u);
        }
        return u1;
    }

    @Override
    public boolean removeUsuario(String id) {
        return usuarios.removeIf(usuario -> usuario.getId().equals(id));
    }

    @Override
    public int countUsers(){
        return usuarios.size();
    }
}
