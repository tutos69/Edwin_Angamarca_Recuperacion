package ec.edu.ups.edwin_angamarca_recuperacion.resources;

import ec.edu.ups.Controlador.BodegaFacade;
import ec.edu.ups.Controlador.ProductoFacade;
import ec.edu.ups.Modelo.Bodega;
import ec.edu.ups.Modelo.Producto;
import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author
 */
@Path("jakartaee9")
public class JakartaEE9Resource {

    @EJB
    private ProductoFacade producto;
    @EJB
    private BodegaFacade bodega;
    
    @GET
    public Response ping() {
        
        Producto p = new Producto(0, "Sal", 12, 12);
        producto.create(p);
        
        Bodega b = new Bodega(0, "Comercial Samanta", "Gualaaceo");
        bodega.create(b);
        
        return Response
                .ok("ping Jakarta EE")
                .build();
    }
}
