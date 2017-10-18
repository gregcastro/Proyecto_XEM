package net.xem.business;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import net.xem.common.utils;
import net.xem.connectors.mysql;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Jose Castro
 */
public class location {
    
    public static String list(String user_email) throws JSONException {
      
        String response ="";
        List<?> rows = null;
        List<?> columns = null;
                
        String location_list = "";
        
        JSONObject location = new JSONObject();
        JSONObject obj_location = new JSONObject();
        JSONArray list_location = new JSONArray();
        
        try {
            if (utils.get_config("dummy").equals("false")) {
                String params_query[] = {user_email};
                rows = mysql.getQuery(utils.get_config("db.connstr-event"), "CALL sp_location_rig_list(?);", params_query);

                list_location = new JSONArray();
                int len = rows.size();
                for (int i = 0; i < len; i++) {
                    columns = (List<?>) rows.get(i);
                    location = new JSONObject();

                    location.put("location_id", columns.get(0).toString());
                    location.put("location_name", columns.get(1).toString());
                    location.put("user_email", columns.get(2).toString());
                    location.put("rig_uuid", columns.get(3).toString());

                    list_location.put(location);

                }
                obj_location = new JSONObject();
                obj_location.put("user", list_location);

                location_list = obj_location.toString();
            } else {
                location.put("location_id", "1");
                location.put("location_name", "cafetal");
                location.put("user_email", "gcastro@gmail.com");
                location.put("rig_uuid", "12345678ADFGGDDG");
                list_location.put(location);
                obj_location.put("location", list_location);
                location_list = obj_location.toString();
            }

            response = location_list;

        } catch (Exception e) {
            System.err.println("Error: " + e.toString());
            response = utils.get_msg("0032", e.toString() );
        } finally {
            return response;
        }
        
        
        
        
    }
    
    public static String read(String rig_uuid) throws JSONException {
      
        String response ="";
        List<?> rows = null;
        List<?> columns = null;
                
        String location_list = "";
        
        JSONObject location = new JSONObject();
        JSONObject obj_location = new JSONObject();
        JSONArray list_location = new JSONArray();
        
        try {
            if (utils.get_config("dummy").equals("false")) {
                String params_query[] = {rig_uuid};
                rows = mysql.getQuery(utils.get_config("db.connstr-event"), "CALL sp_location_rig_read(?);", params_query);

                list_location = new JSONArray();
                int len = rows.size();
                for (int i = 0; i < len; i++) {
                    columns = (List<?>) rows.get(i);
                    location = new JSONObject();

                    location.put("location_id", columns.get(0).toString());
                    location.put("location_name", columns.get(1).toString());
                    location.put("user_email", columns.get(2).toString());
                    location.put("rig_uuid", columns.get(3).toString());

                    list_location.put(location);

                }
                obj_location = new JSONObject();
                obj_location.put("user", list_location);

                location_list = obj_location.toString();
            } else {
                location.put("location_id", "1");
                location.put("location_name", "cafetal");
                location.put("user_email", "gcastro@gmail.com");
                location.put("rig_uuid", "12345678ADFGGDDG");
                list_location.put(location);
                obj_location.put("location", list_location);
                location_list = obj_location.toString();
            }

            response = location_list;

        } catch (Exception e) {
            System.err.println("Error: " + e.toString());
            response = utils.get_msg("0032", e.toString() );
        } finally {
            return response;
        }
        
        
        
        
    }

    public static String create(String formParams) throws JSONException {
        
        JSONObject objParams = null;
        try {
            objParams = new JSONObject(formParams);
        } catch (JSONException ex) {
            System.err.println("Error al convertir JSON: " + ex);
        }

        String response ="";
        String location_uuid = UUID.randomUUID().toString();
        String location_name = objParams.getString("location_name");
        String user_email = objParams.getString("user_email");
        JSONObject location_result = new JSONObject();
        
        try{
            if (utils.get_config("dummy").equals("false")) {

                String params[] = {location_uuid, location_name, user_email};
                mysql.execQuery(utils.get_config("db.connstr-event"), "CALL sp_location_rig_create(?,?,?);", params);
                
                location_result.put("location_uuid", location_uuid);
                location_result.put("location_name", location_name);
                location_result.put("user_email", user_email);

                response = location_result.toString();
            }
            else {
                location_result.put("location_uuid", "uuid_prueba");
                location_result.put("location_name", "lugar de prueba");
                location_result.put("user_email", "gcastro@gmail.com");

                response = location_result.toString();
            }
        }
        catch (Exception e){
            System.err.println("Error: " + e);
            response = utils.get_msg("0039", e.toString());
        }
        finally {
            return response;
        }
        
    }

    public static String update(String formParams, String rig_uuid) throws JSONException {
        String response = "";		
        
        JSONObject objParams = null;
        try {
            objParams = new JSONObject(formParams);
        } catch (JSONException ex) {
            System.err.println("Error al convertir JSON: " + ex);
        }
        
        String user_email = objParams.getString("user_email");
        String location_name = objParams.getString("location_name");
        JSONObject location_result = new JSONObject();
        
        try{
            if (utils.get_config("dummy").equals("false")) {

                String params[] = {location_name, user_email, rig_uuid};
                mysql.execQuery(utils.get_config("db.connstr-event"), "CALL sp_location_rig_update(?,?,?);", params);
                
                location_result.put("location_name", location_name);
                location_result.put("user_email", user_email);
                location_result.put("rig_uuid", rig_uuid);

                response = location_result.toString();
            }
            else {
                location_result.put("location_name", "lugar de prueba");
                location_result.put("user_email", "gcastro@gmail.com");
                location_result.put("rig_uuid", "12345678ABCDEFGH");

                response = location_result.toString();
            }
        }
        catch (Exception e){
            System.err.println("Error: " + e);
            response = utils.get_msg("0039", e.toString());
        }
        finally {
            return response;
        }
    }

    public static String delete(String rig_uuid) {
        String response = "";
        try {
            if (utils.get_config("dummy").equals("false")) {
                String params[] = { rig_uuid };
                mysql.execQuery(utils.get_config("db.connstr-event"), "CALL sp_location_rig_delete(?);", params);
                
                JSONObject location_result = new JSONObject();
                location_result.put("rig_uuid", rig_uuid);
                
                JSONObject location = new JSONObject();
                location.put("location", location_result);
                response = location.toString();
            } else {
                response = "{\"location\": [{\"rig_uuid\": \"bjdbadnae3546424bjb24\"}]}";
            }

        } catch (Exception e) {
            System.err.println("Error: " + e);
            response = utils.get_msg("0036", e.toString());
        } finally {
            return response;
        }
    }

    
    
}
