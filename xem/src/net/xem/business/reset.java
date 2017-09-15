package net.xem.business;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.ws.rs.core.MultivaluedMap;
import net.xem.common.utils;
import net.xem.connectors.mysql;

import org.json.JSONArray;
import org.json.JSONObject;


public class reset {

	@SuppressWarnings("finally")
	public static String list() {

		String response = "";
		List<?> rows = null;
		List<?> columns = null;

		String reset_list = "";

		try{

			if (utils.get_config("dummy").equals("false")) {
				String params_query[] = {};
				rows = mysql.getQuery
						(utils.get_config("db.connstr-event"),
								"CALL sp_reset_list();", params_query);
				
				JSONArray list_reset= new JSONArray();
				int len = rows.size();
				for(int i = 0; i < len; i++) 
				{
					columns = (List<?>) rows.get(i);
					JSONObject reset= new JSONObject();

					reset.put("reset_id", columns.get(0).toString());
					reset.put("reset_uuid", columns.get(1).toString());
					reset.put("reset_create_datetime", columns.get(2).toString());
					reset.put("user_uuid", columns.get(3).toString());
					reset.put("rig_uuid", columns.get(4).toString());
					list_reset.put(reset);

				}
				JSONObject obj_reset = new JSONObject();
				obj_reset.put("reset", list_reset);
				
				reset_list = obj_reset.toString();

			}
			else {
				reset_list = "{\"reset\": [{\"reset_id\": \"123456\",\"reset_uuid\": \"ASWER123UYT657\",\"reset_datetime\": \"2017-01-01\",\" reset_firstname \": \"Luis\",\"reset_lastname \": \"BELLO\",\"reset_ country \": \"VENEZUELA\",\"reset_ image \": \"image.jpg\",\"reset_ phone \": \"04142723549\",\"reset_ sex \": \"M\",\"reset_ birthdate \": \"1984-21-01\"}]}";
			}

			response = reset_list;

		}
		catch (Exception e){
			e.printStackTrace();
			response = utils.get_msg("0018", Arrays.toString(e.getStackTrace()).substring(0,300));
		}
		finally
		{
			return response;
		}
	}

	@SuppressWarnings("finally")
	public static String read(String reset_uuid) {

		String response = "";

		List<?> rows = null;
		List<?> columns = null;

		String reset_list = "";

		try{


			if (utils.get_config("dummy").equals("false")) {

				String params_query[] = {reset_uuid};
				rows = mysql.getQuery
						(utils.get_config("db.connstr-event"), 
								"CALL sp_reset_read(?);", params_query);

				JSONArray list_reset= new JSONArray();
				int len = rows.size();
				for(int i = 0; i < len; i++) 
				{
					columns = (List<?>) rows.get(i);
					JSONObject reset= new JSONObject();

					reset.put("reset_id", columns.get(0).toString());
					reset.put("reset_uuid", columns.get(1).toString());
					reset.put("reset_create_datetime", columns.get(2).toString());
					reset.put("user_uuid", columns.get(3).toString());
					reset.put("rig_uuid", columns.get(4).toString());
					list_reset.put(reset);

				}
				JSONObject obj_reset = new JSONObject();
				obj_reset.put("reset", list_reset);
				
				reset_list = obj_reset.toString();
			}
			else {
				reset_list = "{\"reset\": [{\"reset_id\": \"123456\",\"reset_uuid\": \"ASWER123UYT657\",\"reset_datetime\": \"2017-01-01\",\"reset_type\": \"EMAIL\",\"reset_status\": \"SENT\",\"reset_from\": \"JANIO\",\"reset_to\": \"JOSE\"},{\"reset_text\": \"HI\"}]}";
			}

			response = reset_list;

		}
		catch (Exception e){
			e.printStackTrace();
			response = utils.get_msg("0033", Arrays.toString(e.getStackTrace()).substring(0,300));
		}
		finally
		{
			return response;
		}
	}

	@SuppressWarnings("finally")
	public static String create(final MultivaluedMap<String, String> formParams) {

		String response = "";

		String uuid = UUID.randomUUID().toString();
		String user_uuid = formParams.get("user_uuid").toString().replaceAll("\\[|\\]", "");
		String rig_uuid = formParams.get("rig_uuid").toString().replaceAll("\\[|\\]", "");
		
		try{

			if (utils.get_config("dummy").equals("false")) {
				String params[] = {uuid,user_uuid,rig_uuid};
				mysql.execQuery(utils.get_config("db.connstr-event"), "CALL sp_reset_create(?,?,?);", params);
				
				JSONObject reset_result= new JSONObject();
				reset_result.put("reset_uuid", uuid);
				reset_result.put("user_uuid", user_uuid);
				reset_result.put("rig_uuid", rig_uuid);

				
				JSONObject reset = new JSONObject();
				reset.put("reset", reset_result);				
				response = reset.toString();
			}
			else {
				response = "{\"reset\": [{\"reset_uuid\": \"ASWER123UYT657\",\"reset_datetime_create\": \"2017-01-01\",\"reset_value \": \"Prueba Value\",\"reset_name \": \"Prueba Name\",\"group_uuid \": \"ASWER123UYT657\",\"reset_reset_id \": \"ASWER123UYT657\"}]}";
			}

		}
		catch (Exception e){
			e.printStackTrace();
			response = utils.get_msg("0034", Arrays.toString(e.getStackTrace()).substring(0,300));
		}
		finally
		{
			return response;
		}
	}

	@SuppressWarnings("finally")
	public static String update(final MultivaluedMap<String, String> formParams, String uuid) {

		String response = "";
		
		String user_uuid = formParams.get("user_uuid").toString().replaceAll("\\[|\\]", "");
		String rig_uuid = formParams.get("rig_uuid").toString().replaceAll("\\[|\\]", "");

		try{

			if (utils.get_config("dummy").equals("false")) {

				String params[] = {uuid,user_uuid,rig_uuid};
				mysql.execQuery(utils.get_config("db.connstr-event"), "CALL sp_reset_update(?,?,?);", params);	
				
				
				JSONObject reset_result= new JSONObject();
				reset_result.put("reset_uuid", uuid);
				reset_result.put("user_uuid", user_uuid);
				reset_result.put("rig_uuid", rig_uuid);
				
				JSONObject reset = new JSONObject();
				reset.put("reset", reset_result);				
				response = reset.toString();
			}
			else {
				response = "{\"reset\": [{\"reset_uuid\": \"ASWER123UYT657\",\"reset_datetime_create\": \"2017-01-01\",\"reset_value \": \"Prueba Value\",\"reset_name \": \"Prueba Name\",\"group_uuid \": \"ASWER123UYT657\",\"reset_reset_id \": \"ASWER123UYT657\"}]}";
			}

		}
		catch (Exception e){
			e.printStackTrace();
			response = utils.get_msg("0035", Arrays.toString(e.getStackTrace()).substring(0,300));
		}
		finally
		{
			return response;
		}
	}

	@SuppressWarnings("finally")
	public static String delete(String uuid) {

		String response = "";


		try{

			if (utils.get_config("dummy").equals("false")) {
				String params[] = {uuid};
				mysql.execQuery(utils.get_config("db.connstr-event"), "CALL sp_reset_delete(?);", params);				
				JSONObject reset_result= new JSONObject();
				reset_result.put("reset_uuid", uuid);
				JSONObject reset = new JSONObject();
				reset.put("reset", reset_result);
				response = reset.toString();
			}
			else {
				response = "{\"group\": [{\"reset_uuid\": \"ASWER123UYT657\"}]}";
			}

		}
		catch (Exception e){
			e.printStackTrace();
			response = utils.get_msg("0036", Arrays.toString(e.getStackTrace()).substring(0,300));
		}
		finally
		{
			return response;
		}
	}
}
