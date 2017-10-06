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
import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;


@Path("rig")
@Produces("application/json")
public class rig {
	
	@GET
	@Path("/")
	public static String list(){
            
		String response = "";
		response = net.xem.business.rig.List();
		
		return response;
	}
	
	@GET
	@Path("/{uuid}/")
	public static String read(@PathParam("uuid") String uuid){
	
		String response = "";
		response = net.xem.business.rig.Read(uuid);
		
		return response;
	}
	
        @GET
	@Path("ReadByUser/{uuid}/")
	public static String ReadByUser(@PathParam("uuid") String uuid){
		String response = "";
		response = net.xem.business.rig.read_by_user(uuid);
		                
		return response;
	}
	
	
	@POST 
	@Path("/")
        @Consumes("application/json")
        public static String create(String formParams) throws JSONException{
		String response = "";
		response = net.xem.business.rig.Create(formParams);
		   
		return response;
	}
	
	@PUT 
	@Path("/{uuid}/")
	public static String update(@PathParam("uuid") String uuid, final MultivaluedMap<String, String> formParams){
			
		String response = "";
		response = net.xem.business.rig.Update(formParams,uuid);
		
		return response;
	}
        
        @PUT 
	@Path("/update_rigs_start_bat/{user_uuid}")
	public static String update_rigs_start_bat(@PathParam("user_uuid") String user_uuid) throws JSONException{
                
            //Busco el start_bat_data del usuario 
            String user = net.xem.business.user.read(user_uuid);
            JSONObject obj_user = new JSONObject(user);
            JSONArray aux = obj_user.getJSONArray("user");
            obj_user = (JSONObject) aux.get(0);
            
            String response = "";
            response = net.xem.business.rig.update_rigs_start_bat(user_uuid, obj_user.getString("user_start_bat_data"));

            return response;
	}
	
	@DELETE
	@Path("/{uuid}/")
	public static String delete(@PathParam("uuid") String uuid){
		
		String response = "";
		response = net.xem.business.rig.Delete(uuid);
		
		return response;
	}

}
