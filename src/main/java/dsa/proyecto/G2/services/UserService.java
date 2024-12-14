package dsa.proyecto.G2.services;

import dsa.proyecto.G2.models.User;
import dsa.proyecto.G2.orm.dao.DAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/usuarios", description = "Endpoint to User Service")
@Path("/usuarios")
public class UserService {

    private DAO<User> userDAO;

    public UserService() {
        this.userDAO = new DAO<User>(User.class) {};
    }

    @GET
    @ApiOperation(value = "Obtener todos los usuarios", notes = "Devuelve una lista de todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = User.class, responseContainer = "List"),
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarios() {
        try {
            List<User> usuarios = userDAO.selectAll();
            return Response.status(200).entity(usuarios).build();
        } catch (Exception e) {
            return Response.status(500).entity("Error al obtener usuarios").build();
        }
    }

    @POST
    @ApiOperation(value = "Crear un nuevo usuario", notes = "Crea un nuevo usuario con la información proporcionada")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuario creado con éxito", response = User.class),
            @ApiResponse(code = 400, message = "El nombre de usuario ya existe"),
            @ApiResponse(code = 500, message = "Error al crear usuario")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newUsuario(User usuario) {
        try {
            // Insertar el nuevo usuario
            userDAO.insert(usuario);
            return Response.status(201).entity(usuario).build();
        } catch (Exception e) {
            // Inspeccionar la causa del error
            if (e.getCause() != null && e.getCause().getMessage().contains("Duplicate entry")) {
                // Error por nombre de usuario duplicado
                return Response.status(400).entity("El nombre de usuario ya existe").build();
            }
            e.printStackTrace();
            return Response.status(500).entity("Error al crear usuario").build();
        }
    }


    @POST
    @Path("/login")
    @ApiOperation(value = "Iniciar sesión", notes = "Valida las credenciales del usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login exitoso"),
            @ApiResponse(code = 401, message = "Credenciales incorrectas")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User credentials) {
        try {
            List<User> usuarios = userDAO.selectAll();
            for (User user : usuarios) {
                if (user.getNombre().equals(credentials.getNombre()) &&
                        user.getContraseña().equals(credentials.getContraseña())) {
                    return Response.status(200).entity(user).build();
                }
            }
            return Response.status(401).entity("Credenciales incorrectas").build();
        } catch (Exception e) {
            return Response.status(500).entity("Error al iniciar sesión").build();
        }
    }
}
