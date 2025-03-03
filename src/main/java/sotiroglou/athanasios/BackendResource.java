package sotiroglou.athanasios;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
public class BackendResource {

    @GET
    @Path("/success")
    @Produces(MediaType.TEXT_PLAIN)
    public Response success() {
        return Response.ok("Success 200!").build();
    }

    @GET
    @Path("/bad-request")
    @Produces(MediaType.TEXT_PLAIN)
    public Response badRequest() {
        return Response.status(400).entity("Bad Request 400!").build();
    }

    @GET
    @Path("/server-error")
    @Produces(MediaType.TEXT_PLAIN)
    public Response serverError() {
        return Response.status(500).entity("Internal Server Error 500!").build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

}
