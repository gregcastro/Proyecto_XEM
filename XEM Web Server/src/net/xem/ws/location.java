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
import org.json.JSONException;

@Path("location")
@Produces("application/json")
@Consumes("application/json")
public class location {
    
    @GET
    @Path("/{user_email}/")
    public static String list(@PathParam("user_email") String user_email) throws JSONException{
        String response = "";
        response = net.xem.business.location.list(user_email);		
        return response;
    }
    
    @GET
    @Path("/by_rig/{rig_uuid}/")
    public static String read(@PathParam("rig_uuid") String rig_uuid) throws JSONException{

            String response = "";
            System.out.println("rig_uuid = " + rig_uuid);
            response = net.xem.business.location.read(rig_uuid);

            return response;
    }
    
    @POST 
    @Path("/")
    public static String create(String formParams) throws JSONException{
        String response = "";
        response = net.xem.business.location.create(formParams);

        return response;
    }
    
    @PUT 
    @Path("/{rig_uuid}/")
    public static String update(@PathParam("rig_uuid") String rig_uuid, String formParams) throws JSONException{

            String response = "";
            response = net.xem.business.location.update(formParams,rig_uuid);

            return response;
    }
    
    @DELETE
    @Path("/{rig_uuid}/")
    public static String delete(@PathParam("rig_uuid") String rig_uuid){
        String response = "";
        response = net.xem.business.location.delete(rig_uuid);

        return response;
    }
    
}
