package dsa.proyecto.G4.db.orm.dao;

import dsa.proyecto.G4.models.Product;
import java.sql.SQLException;
import java.util.List;

public interface IProductDAO {

    // Obtener todos los productos
    List<Product> getProducts();

    // Obtener un producto por su ID
    Product getProduct(String id);

    // Agregar un nuevo producto
    int addProduct(String id, String nombre, double precio);

    // Actualizar la información de un producto
    void updateProduct(String id, String nombre, double precio) throws SQLException;

    // Eliminar un producto por su ID
    void deleteProduct(String id) throws SQLException;
}
