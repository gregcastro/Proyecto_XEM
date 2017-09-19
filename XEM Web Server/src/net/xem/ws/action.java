package net.xem.ws;
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

@Path("action")
@Produces("application/json")
public class action {
	
        @GET
	@Path("/")
	public static String list(){
	
		String response = "";
		response = net.xem.business.action.list();		
		return response;
	}
	
        @GET
	@Path("/{uuid}/")
	public static String read(@PathParam("uuid") String uuid){
	
		String response = "";
		response = net.xem.business.action.read(uuid);
		
		return response;
	}
	
        @GET
	@Path("/by_rig/{rig_uuid}/")
	public static String read_by_rig(@PathParam("rig_uuid") String rig_uuid){
                System.out.println("action/by_rig/{rig_uuid}");
		String response = "";
		response = net.xem.business.action.read_by_rig(rig_uuid);
		
		return response;
	}
	
        @GET
	@Path("reset/by_rig/{rig_uuid}/")
	public static String reset_by_rig(@PathParam("rig_uuid") String rig_uuid){
	
		String response = "";
		response = net.xem.business.action.reset_by_rig(rig_uuid);
		
		return response;
	}
        
	@POST 
	@Path("/")
        @Consumes("application/json")
//	public static String create(final MultivaluedMap<String, String> formParams){
	public static String create(String formParams) throws JSONException{
		String response = "";
		response = net.xem.business.action.create(formParams);
		
		return response;
	}
	
	@PUT 
	@Path("/{uuid}/")
	public static String update(@PathParam("uuid") String uuid, final MultivaluedMap<String, String> formParams){
			
		String response = "";
		response = net.xem.business.action.update(formParams,uuid);
		
		return response;
	}
	
	@DELETE
	@Path("/{uuid}/")
	public static String delete(@PathParam("uuid") String uuid){
		
		String response = "";
		response = net.xem.business.action.delete(uuid);
		
		return response;
	}
        
        @GET
        @Path("/prueba_gcastro")
        public static String prueba_get_method(){
	
		String response = "";
		response = net.xem.business.action.prueba_get_method();		
		return response;
	}
        
        
        
        
}