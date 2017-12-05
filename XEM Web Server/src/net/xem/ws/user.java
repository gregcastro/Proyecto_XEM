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
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

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
    
    //Este no se usa, es si se quiere agregar a traves de un JSON
    @POST 
    @Path("/create")
    public static String create(String formParams) throws JSONException{
        String response = "";
        response = net.xem.business.user.create(formParams);

        return response;
    }
    
    @POST 
    @Path("/")
    @Consumes("application/x-www-form-urlencoded")
    public static Response create2(@FormParam("email") String email, @FormParam("password") String pass, @FormParam("confirm-password") String confirmPass ) throws JSONException{
        String response = "";
        
        //Validar si las contrasenas coinciden
        if( !pass.equals(confirmPass) ) {
            JSONObject response_error = new JSONObject();
            //Parametrizar esto
            response_error.put("Error", "Passwords doesn't match");
            response_error.put("Code", "put_code_here");
//            return response_error.toString();
        }
        
        JSONObject formParams = new JSONObject();
        formParams.put("user_email", email);
        formParams.put("user_password", pass);
        
        response = net.xem.business.user.create(formParams.toString());
        return Response.ok().entity(response).header("Access-Control-Allow-Origin", "*")
			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
			.allow("OPTIONS").build();
        
        
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
