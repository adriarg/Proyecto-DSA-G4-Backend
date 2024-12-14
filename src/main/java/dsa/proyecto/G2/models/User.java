package dsa.proyecto.G2.models;

import dsa.proyecto.G2.util.RandomUtils;

public class User {
    private String id;
    private String nombre;
    private String contraseña;

    public User () {
        this.setId(RandomUtils.getId());
    }

    public User(String nombre, String contraseña) {
        this(null,nombre,contraseña);
    }

    public User(String id, String nombre, String contraseña) {
        this();
        if(id!=null) this.setId(id);
        this.nombre = nombre;
        this.contraseña = contraseña;
    }


    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
}

