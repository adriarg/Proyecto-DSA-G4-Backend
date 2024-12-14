package dsa.proyecto.G2;

import dsa.proyecto.G2.models.Product;

import java.util.List;

public interface ProductManager {
    // Obtener un producto por ID
    Product getProductById(String id);

    // Agregar un nuevo producto
    void addProduct(Product product);

    // Obtener todos los productos
    List<Product> getAllProducts();

    // Eliminar un producto por ID
    boolean removeProduct(String id);
}
