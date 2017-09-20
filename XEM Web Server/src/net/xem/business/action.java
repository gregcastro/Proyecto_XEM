package net.xem.business;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MultivaluedMap;
import net.xem.common.utils;
import net.xem.connectors.mysql;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class action {
	@SuppressWarnings("finally")
	public static String list() {
		String response = "";
		List<?> rows = null;
		List<?> columns = null;
		String action_list = "";
		try{
			if (utils.get_config("dummy").equals("false")) {
				String params_query[] = {};
				rows = mysql.getQuery
						(utils.get_config("db.connstr-event"),
								"CALL sp_action_list();", params_query);
				
				JSONArray list_action = new JSONArray();
				int len = rows.size();
				for(int i = 0; i < len; i++) 
				{
					columns = (List<?>) rows.get(i);
					JSONObject actions = new JSONObject();
					actions.put("action_id", columns.get(0).toString());
					actions.put("action_uuid", columns.get(1).toString());
					actions.put("action_change_claymore_version", columns.get(2).toString());
					actions.put("action_change_start_bat", columns.get(3).toString());
					actions.put("action_download_claymore_version", columns.get(4).toString());
					actions.put("action_restart_claymore", columns.get(5).toString());
                                        //Este no estaba, preguntar si hace falta o no
                                        //actions.put("action_reset_rig", columns.get(6).toString());
                                        actions.put("rig_uuid", columns.get(7).toString());										
					list_action.put(actions);
				}
				JSONObject obj_action = new JSONObject();
				obj_action.put("action", list_action);				
				action_list = obj_action.toString();
			}
			else {
				action_list = "{\"action\": [{\"action_id\": \"123456\",\"action_uuid\": \"ASWER123UYT657\",\"action_create_datetime\": \"2017-01-01\",\" action_description \": \"Luis\",\"category_uuid \": \"BELLO\",\"team_uuid \": \"VENEZUELA\",,\"action_status \": \"04142723549\"}]}";
			}
			response = action_list;
		}
		catch (Exception e){
			e.printStackTrace();
			response = utils.get_msg("0037", Arrays.toString(e.getStackTrace()).substring(0,300));
		}
		finally
		{
			return response;
		}
	}
	
	@SuppressWarnings("finally")
	public static String read(String action_uuid) {
		String response = "";
		List<?> rows = null;
		List<?> columns = null;
		String action_list = "";
		try{
			if (utils.get_config("dummy").equals("false")) {
				String params_query[] = {action_uuid};
				rows = mysql.getQuery
						(utils.get_config("db.connstr-event"),"CALL sp_action_read(?);", params_query);
				JSONObject action= new JSONObject();
				JSONArray list_action = new JSONArray();
				int len = rows.size();
				for(int i=0;i<len;i++)
				{
					columns = (List<?>) rows.get(i);
					JSONObject actions = new JSONObject();
					actions.put("action_id", columns.get(0).toString());
					actions.put("action_uuid", columns.get(1).toString());
					actions.put("action_change_claymore_version", columns.get(2).toString());
					actions.put("action_change_start_bat", columns.get(3).toString());
					actions.put("action_download_claymore_version", columns.get(4).toString());
					actions.put("action_restart_claymore", columns.get(5).toString());
					actions.put("rig_uuid", columns.get(6).toString());										
					list_action.put(actions);

				}
				JSONObject obj_action = new JSONObject();
				obj_action.put("action", action);
				action_list = obj_action.toString();
			}
			else {
				action_list = "{\"action\": [{\"action_id\": \"123456\",\"action_uuid\": \"ASWER123UYT657\",\"action_create_datetime\": \"2017-01-01\",\" action_description \": \"Luis\",\"category_uuid \": \"BELLO\",\"team_uuid \": \"VENEZUELA\",,\"action_status \": \"04142723549\"}]}";
			}

			response = action_list;

		}
		catch (Exception e){
			e.printStackTrace();
			response = utils.get_msg("0038", Arrays.toString(e.getStackTrace()).substring(0,300));
		}
		finally
		{
			return response;
		}
	}
	
        @SuppressWarnings("finally")
	public static String read_by_rig(String rig_uuid) {
            String response = "";
            List<?> rows = null;
            List<?> columns = null;
            String action_list = "";

//                System.out.println("rig_uuid = " + rig_uuid);

            try{
                if (utils.get_config("dummy").equals("false")) {
                    String params_query[] = {rig_uuid};
                    rows = mysql.getQuery
                                    (utils.get_config("db.connstr-event"),"CALL sp_action_read_by_rig(?);", params_query);
                    JSONObject main_action= new JSONObject();
                    JSONObject actions= new JSONObject();
                    JSONObject properties= new JSONObject();
                    int len = rows.size();
                    for(int i=0;i<len;i++)
                    {
                            columns = (List<?>) rows.get(i);
                            actions.put("action_change_claymore_version", columns.get(2).toString());
                            actions.put("action_restart_claymore", columns.get(5).toString());
                            actions.put("action_change_start_bat", columns.get(3).toString());
                            actions.put("action_download_claymore_version", columns.get(4).toString());
                            actions.put("action_reset_rig", columns.get(11).toString());
                            
                            properties.put("rig_claymore_version", columns.get(9).toString());
                            properties.put("user_start_bat_data", (columns.get(10) == null ? "" : columns.get(10).toString() ) );

                            main_action.put("action", actions);
                            main_action.put("properties", properties);

                    }
                    action_list = main_action.toString();
                }
                else {
                    action_list = "{\"action\": [{\"action_id\": \"123456\",\"action_uuid\": \"ASWER123UYT657\",\"action_create_datetime\": \"2017-01-01\",\" action_description \": \"Luis\",\"category_uuid \": \"BELLO\",\"team_uuid \": \"VENEZUELA\",,\"action_status \": \"04142723549\"}]}";
                }

                response = action_list;

            }
            catch (Exception e){
                e.printStackTrace();
                response = utils.get_msg("0038", Arrays.toString(e.getStackTrace()).substring(0,300));
            }
            finally
            {		
                    return response;		
            }
	}
        
	@SuppressWarnings("finally")
	public static String reset_by_rig(String rig_uuid) {
		String response = "";
		List<?> rows = null;
		List<?> columns = null;
		String action_list = "";
		try{
			if (utils.get_config("dummy").equals("false")) {
				String params_query[] = {rig_uuid};
				rows = mysql.getQuery
						(utils.get_config("db.connstr-event"),"CALL sp_action_reset_by_rig(?);", params_query);
				
				JSONObject actions= new JSONObject();
				int len = rows.size();
				for(int i=0;i<len;i++)
				{
					columns = (List<?>) rows.get(i);
					actions.put("action_reset_rig", columns.get(5).toString());
					actions.put("rig_reseter_number", columns.get(6).toString());

				}

				action_list = actions.toString();
			}
			else {
				action_list = "{\"action\": [{\"action_id\": \"123456\",\"action_uuid\": \"ASWER123UYT657\",\"action_create_datetime\": \"2017-01-01\",\" action_description \": \"Luis\",\"category_uuid \": \"BELLO\",\"team_uuid \": \"VENEZUELA\",,\"action_status \": \"04142723549\"}]}";
			}

			response = action_list;

		}
		catch (Exception e){
			e.printStackTrace();
			response = utils.get_msg("0038", Arrays.toString(e.getStackTrace()).substring(0,300));
		}
		finally
		{		
			return response;		
		}
	}
	
	@SuppressWarnings("finally")
//	public static String create(final MultivaluedMap<String, String> formParams) {
	public static String create(String formParams) throws JSONException {
            JSONObject objParams = null;
            try {
                objParams = new JSONObject(formParams);
            } catch (JSONException ex) {
                System.err.println("Error: al convertir JSON" + ex);
            }
            
                String response ="";
		String uuid = UUID.randomUUID().toString();
		String action_change_claymore_version = Integer.toString(objParams.getInt("action_change_claymore_version"));
                String action_change_start_bat = Integer.toString(objParams.getInt("action_change_start_bat"));
                String action_download_claymore_version = Integer.toString(objParams.getInt("action_download_claymore_version"));
                String action_restart_claymore = Integer.toString(objParams.getInt("action_restart_claymore"));
                String action_reset_rig = null;
		String rig_uuid = objParams.getString("rig_uuid").replaceAll("\\[|\\]", "");
	
		try{

                    if (utils.get_config("dummy").equals("false")) {
                        
                        //Evaluar si primero verifico si no existe un accion con el mismo rig_uuid, para que no hayan action duplicados para el mismo rig
                        //Evaluarlo porque se supone q esto solo se llame si se esta creando por primera vez este rig_uuid
                        
                        String params[] = {uuid,action_change_claymore_version,action_change_start_bat, action_download_claymore_version, action_restart_claymore, action_reset_rig, rig_uuid};
                        mysql.execQuery(utils.get_config("db.connstr-event"), "CALL sp_action_create(?,?,?,?,?,?,?);", params);;

                        JSONObject action_result= new JSONObject();
                        action_result.put("action_uuid", uuid);				
                        action_result.put("action_change_claymore_version", action_change_claymore_version);
                        action_result.put("action_change_start_bat", action_change_start_bat);
                        action_result.put("action_download_claymore_version", action_download_claymore_version);
                        action_result.put("action_restart_claymore", action_restart_claymore);
                        action_result.put("action_reset_rig", action_reset_rig);
                        action_result.put("rig_uuid", rig_uuid);				
                        response = action_result.toString();

                    }
                    else {
                            response = "{\"action\": [{\"action_id\": \"123456\",\"action_uuid\": \"ASWER123UYT657\",\"action_create_datetime\": \"2017-01-01\",\" action_description \": \"Luis\",\"category_uuid \": \"BELLO\",\"team_uuid \": \"VENEZUELA\",,\"action_status \": \"04142723549\"}]}";
                    }
		}
		catch (Exception e){
			e.printStackTrace();
			response = utils.get_msg("0039", Arrays.toString(e.getStackTrace()).substring(0,300));
		}
		finally
		{
			return response;
		}
	}
	
	@SuppressWarnings("finally")
	public static String update(final MultivaluedMap<String, String> formParams, String uuid) {

		String response = "";		
		String action_change_claymore_version = formParams.get("action_change_claymore_version").toString().replaceAll("\\[|\\]", "");
		String action_change_start_bat = formParams.get("action_change_start_bat").toString().replaceAll("\\[|\\]", "");
		String action_download_claymore_version = formParams.get("action_download_claymore_version").toString().replaceAll("\\[|\\]", "");
		String action_restart_claymore = formParams.get("action_restart_claymore").toString().replaceAll("\\[|\\]", "");
		String rig_uuid = formParams.get("rig_uuid").toString().replaceAll("\\[|\\]", "");
	
		
		try{

			if (utils.get_config("dummy").equals("false")) {
				String params[] = {uuid,action_change_claymore_version,action_change_start_bat, action_download_claymore_version, action_restart_claymore, rig_uuid};
				mysql.execQuery(utils.get_config("db.connstr-event"), "CALL sp_action_update(?,?,?,?,?,?);", params);

				JSONObject action_result= new JSONObject();
				action_result.put("action_uuid", uuid);				
				action_result.put("action_change_claymore_version", action_change_claymore_version);
				action_result.put("action_change_start_bat", action_change_start_bat);
				action_result.put("action_download_claymore_version", action_download_claymore_version);
				action_result.put("action_restart_claymore", action_restart_claymore);
				action_result.put("rig_uuid", rig_uuid);

				JSONObject action = new JSONObject();
				action.put("action", action_result);
				response = action.toString();
				
			}
			else {
				response = "{\"action\": [{\"action_id\": \"123456\",\"action_uuid\": \"ASWER123UYT657\",\"action_create_datetime\": \"2017-01-01\",\" action_description \": \"Luis\",\"category_uuid \": \"BELLO\",\"team_uuid \": \"VENEZUELA\",,\"action_status \": \"04142723549\"}]}";
			}
		}
		catch (Exception e){
			e.printStackTrace();
			response = utils.get_msg("0040", Arrays.toString(e.getStackTrace()).substring(0,300));
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
				mysql.execQuery(utils.get_config("db.connstr-event"), "CALL sp_action_delete(?);", params);
				
				JSONObject action_result= new JSONObject();
				action_result.put("question_uuid", uuid);
				JSONObject action = new JSONObject();
				action.put("action", action_result);

				response = action.toString();
				
			}
			else {
				response = "{\"action\": [{\"action_uuid\": \"ASWER123UYT657\"}]}";
			}

		}
		catch (Exception e){
			e.printStackTrace();
			response = utils.get_msg("0041", Arrays.toString(e.getStackTrace()).substring(0,300));
		}
		finally
		{
			return response;
		}
	}

        
        @SuppressWarnings("finally")
	public static String prueba_get_method() {
		String response = "";
		List<?> rows = null;
		List<?> columns = null;
		String action_list = "";
		try{
			if (utils.get_config("dummy").equals("false")) {
				String params_query[] = {};
				rows = mysql.getQuery
						(utils.get_config("db.connstr-event"),
								"CALL sp_action_list();", params_query);
				
				JSONArray list_action = new JSONArray();
				int len = rows.size();
                                System.out.println(rows.get(0));
                                System.out.println("len = " + len);
				for(int i = 0; i < len; i++) 
				{
					columns = (List<?>) rows.get(i);
					JSONObject actions = new JSONObject();
					actions.put("action_id", columns.get(0).toString());
					actions.put("action_uuid", columns.get(1).toString());
					actions.put("action_change_claymore_version", columns.get(2).toString());
					actions.put("action_change_start_bat", columns.get(3).toString());
					actions.put("action_download_claymore_version", columns.get(4).toString());
					actions.put("action_restart_claymore", columns.get(5).toString());
                                        //Este no estaba, preguntar si hace falta o no
                                        //actions.put("action_reset_rig", columns.get(6).toString());
                                        actions.put("rig_uuid", columns.get(7).toString());										
					list_action.put(actions);
				}
				JSONObject obj_action = new JSONObject();
				obj_action.put("action", list_action);				
				action_list = obj_action.toString();
			}
			else {
				action_list = "{\"action\": [{\"action_id\": \"123456\",\"action_uuid\": \"ASWER123UYT657\",\"action_create_datetime\": \"2017-01-01\",\" action_description \": \"Luis\",\"category_uuid \": \"BELLO\",\"team_uuid \": \"VENEZUELA\",,\"action_status \": \"04142723549\"}]}";
			}
			response = action_list;
		}
		catch (Exception e){
			e.printStackTrace();
			response = utils.get_msg("0037", Arrays.toString(e.getStackTrace()).substring(0,300));
		}
		finally
		{
			return response;
		}
	}
        
        
}


