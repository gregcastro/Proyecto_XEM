package net.xem.ws;

//import javax.ws.rs.Consumes;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;
import org.json.JSONException;


@Path("reset")
@Produces("application/json")
@Consumes("application/json")
public class reset {
	
	@GET
	@Path("/")
	public static String list(){
		String response = "";
		response = net.xem.business.reset.list();
		
		return response;
	}
	
	@GET
	@Path("/{uuid}/")
	public static String read(@PathParam("uuid") String uuid){
	
		String response = "";
		response = net.xem.business.reset.read(uuid);
		
		return response;
	}
        
        @GET
	@Path("/rig_reset/{reset_uuid}/")
	public static String reset_rig(@PathParam("reset_uuid") String uuid){
		String response = "";
                System.out.println("uuid = " + uuid);
		response = net.xem.business.reset.reset_rig(uuid);
		
		return response;
	}
	
	@POST 
	@Path("/")
	public static String create(String formParams) throws JSONException{
		
		String response = "";
		response = net.xem.business.reset.create(formParams);
		
		return response;
	}
        
        @POST 
	@Path("/new_reseter/")
	public static String new_reseter(String formParams) throws JSONException{
                
		String response = "";
		response = net.xem.business.reset.new_reseter(formParams);
		
		return response;
	}
	
	@PUT 
	@Path("/{rig_uuid}/")
	public static String update(@PathParam("rig_uuid") String rig_uuid, String formParams) throws JSONException{
			
            String response = "";
            response = net.xem.business.reset.update(formParams, rig_uuid);

            return response;
	}
	
	@DELETE
	@Path("/{rig_uuid}/")
	public static String delete(@PathParam("rig_uuid") String rig_uuid){
		
            String response = "";
            response = net.xem.business.reset.delete(rig_uuid);

            return response;
	}

}
