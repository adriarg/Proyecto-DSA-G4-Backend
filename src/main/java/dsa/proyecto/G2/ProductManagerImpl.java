package dsa.proyecto.G2;

import dsa.proyecto.G2.models.Product;

import java.util.LinkedList;
import java.util.List;

public class ProductManagerImpl implements ProductManager {
    private static ProductManagerImpl instance;
    private List<Product> productos;

    // Constructor privado para Singleton
    private ProductManagerImpl() {
        this.productos = new LinkedList<>();
    }

    // Obtener instancia Singleton
    public static ProductManagerImpl getInstance() {
        if (instance == null) {
            instance = new ProductManagerImpl();
        }
        return instance;
    }

    @Override
    public Product getProductById(String id) {
        for (Product producto : productos) {
            if (producto.getId().equals(id)) {
                return producto;
            }
        }
        return null;
    }

    @Override
    public void addProduct(Product product) {
        productos.add(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return new LinkedList<>(productos);
    }

    @Override
    public boolean removeProduct(String id) {
        return productos.removeIf(producto -> producto.getId().equals(id));
    }
}
