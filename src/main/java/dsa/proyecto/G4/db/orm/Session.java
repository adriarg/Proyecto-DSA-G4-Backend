package dsa.proyecto.G4.db.orm;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface Session {

    // CRUD básico
    void save(Object entity);  // Crear
    void close();  // Cerrar la sesión

    // Recuperar objetos por clave primaria
    Object get(Class<?> theClass, String pk, Object value);  // Leer

    // Actualizar un objeto
    void update(Object object) throws SQLException;  // Actualizar

    // Eliminar un objeto
    void delete(Object object) throws SQLException;  // Eliminar

    // Consultas de recuperación de datos
    List<Object> findAll(Class<?> theClass);  // Recuperar todos los objetos de la clase
    List<Object> findAll(Class<?> theClass, HashMap<String, String> params) throws SQLException;  // Recuperar objetos con filtros
    List<Object> getList(Class<?> theClass, String key, Object value);  // Recuperar lista filtrada
    List<Object> query(String query, Class<?> theClass, HashMap<String, Object> params);  // Consulta SQL personalizada
}
