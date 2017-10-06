package net.xem.ws;
/**
 *
 * @author Jose Castro
 */
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MultivaluedMap;
import org.json.JSONException;

@Path("user")
@Produces("application/json")
@Consumes("application/json")
public class user {
    
    @GET
    @Path("/")
    public static String list(){
        String response = "";
        response = net.xem.business.user.list();		
        return response;
    }
    
    @GET
    @Path("/{email}/")
    public static String read(@PathParam("email") String email){

            String response = "";
            response = net.xem.business.user.read(email);

            return response;
    }
    
    @POST 
    @Path("/")
    public static String create(String formParams) throws JSONException{
        String response = "";
        response = net.xem.business.user.create(formParams);

        return response;
    }
    
    @PUT 
    @Path("/{email}/")
    public static String update(@PathParam("email") String email, String formParams) throws JSONException{

            String response = "";
            response = net.xem.business.user.update(formParams,email);

            return response;
    }
   
    @DELETE
    @Path("/{email}/")
    public static String delete(@PathParam("email") String email){

            String response = "";
            response = net.xem.business.user.delete(email);

            return response;
    }
    
    
    
    
}
