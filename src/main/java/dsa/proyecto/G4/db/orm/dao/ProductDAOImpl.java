package dsa.proyecto.G4.db.orm.dao;

import dsa.proyecto.G4.db.orm.FactorySession;
import dsa.proyecto.G4.db.orm.Session;
import dsa.proyecto.G4.models.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProductDAOImpl implements IProductDAO {

    final static Logger logger = Logger.getLogger(ProductDAOImpl.class.getName());

    @Override
    public int addProduct(String id, String nombre, double precio) {
        Session session = null;
        try {
            session = FactorySession.openSession();
            Product product = new Product(id, nombre, precio);
            session.save(product);
        } catch (Exception e) {
            logger.severe("Error adding product: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return 0;
    }

    @Override
    public Product getProduct(String id) {
        Session session = null;
        Product product = null;
        try {
            session = FactorySession.openSession();
            product = (Product) session.get(Product.class, "id", id);
        } catch (Exception e) {
            logger.severe("Error getting product: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return product;
    }

    @Override
    public void updateProduct(String id, String nombre, double precio) throws SQLException {
        Session session = null;
        try {
            session = FactorySession.openSession();
            Product product = (Product) session.get(Product.class, "id", id);
            if (product != null) {
                product.setNombre(nombre);
                product.setPrecio(precio);
                session.update(product);
                logger.info("Successfully updated product with id: " + id);
            } else {
                logger.warning("Product with id " + id + " not found");
                throw new SQLException("Product not found");
            }
        } catch (SQLException e) {
            logger.severe("Couldn't update product: " + e.getMessage());
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void deleteProduct(String id) throws SQLException {
        Product product = this.getProduct(id);
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.delete(product);
        } catch (Exception e) {
            logger.severe("Error deleting product: " + e.getMessage());
            throw new SQLException("Error deleting product", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Product> getProducts() {
        Session session = null;
        List<Product> products = null;
        try {
            session = FactorySession.openSession();
            // CORRECCIÓN DEL ERROR DE CONVERSIÓN
            products = session.findAll(Product.class)
                    .stream()
                    .map(obj -> (Product) obj)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.severe("Error getting products: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return products;
    }
}
