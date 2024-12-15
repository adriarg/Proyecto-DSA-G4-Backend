package dsa.proyecto.G4.services;

import dsa.proyecto.G4.ProductManager;
import dsa.proyecto.G4.ProductManagerImpl;
import dsa.proyecto.G4.models.Product;
import dsa.proyecto.G4.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/productos", description = "Endpoint to Product Service")
@Path("/productos")
public class ProductService {

    private ProductManager productManager;

    public ProductService() {
        this.productManager = ProductManagerImpl.getInstance();

        // Datos de ejemplo
        if (productManager.getProductById("1") == null) {
            this.productManager.addProduct(new Product("1", "Producto1", 10.0));
            this.productManager.addProduct(new Product("2", "Producto2", 25.0));
        }
    }
    @GET
    @ApiOperation(value = "Obtener todos los productos", notes = "Devuelve una lista de todos los productos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = User.class, responseContainer = "List"),
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        List<Product> productos = this.productManager.getAllProducts();
        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(productos) {};
        return Response.status(200).entity(entity).build();
    }

    @POST
    @ApiOperation(value = "Crear un nuevo producto", notes = "Crea un nuevo producto con la información proporcionada")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Product.class),
            @ApiResponse(code = 500, message = "Error de validación")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newProduct(Product product) {
        if (product.getNombre() == null || product.getPrecio() == null) {
            return Response.status(500).entity("Error de validación").build();
        }
        this.productManager.addProduct(product);
        return Response.status(201).entity(product).build();
    }

    @GET
    @Path("/{id}")
    @ApiOperation(value = "Obtener un producto por ID", notes = "Devuelve el producto con el ID especificado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Product.class),
            @ApiResponse(code = 404, message = "Producto no encontrado")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") String id) {
        Product product = this.productManager.getProductById(id);
        if (product == null) return Response.status(404).build();
        return Response.status(200).entity(product).build();
    }

    @GET
    @Path("/{id}/precio")
    @ApiOperation(value = "Obtener el precio de un producto", notes = "Devuelve el precio del producto con el ID especificado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Double.class),
            @ApiResponse(code = 404, message = "Producto no encontrado")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductPrice(@PathParam("id") String id) {
        Product product = this.productManager.getProductById(id);
        if (product == null) return Response.status(404).build();
        return Response.status(200).entity(product.getPrecio()).build();
    }
}
