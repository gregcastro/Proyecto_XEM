package net.xem.ws;

//import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MultivaluedMap;


@Path("reset")
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
	
	@POST 
	@Path("/")
	public static String create(final MultivaluedMap<String, String> formParams){
		
		String response = "";
		response = net.xem.business.reset.create(formParams);
		
		return response;
	}
	
	@PUT 
	@Path("/{uuid}/")
	public static String update(@PathParam("uuid") String uuid, final MultivaluedMap<String, String> formParams){
			
		String response = "";
		response = net.xem.business.reset.update(formParams,uuid);
		
		return response;
	}
	
	@DELETE
	@Path("/{uuid}/")
	public static String delete(@PathParam("uuid") String uuid){
		
		String response = "";
		response = net.xem.business.reset.delete(uuid);
		
		return response;
	}

}
