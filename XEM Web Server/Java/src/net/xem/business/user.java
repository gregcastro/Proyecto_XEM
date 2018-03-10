package net.xem.business;

import java.util.Arrays;
import java.util.List;
import net.xem.common.utils;
import net.xem.connectors.mysql;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Jose Castro
 */
public class user {
    
    public static String list() {
        String response = "";
        List<?> rows = null;
        List<?> columns = null;
        
        String user_list = "";
        
        JSONObject user = new JSONObject();
        JSONObject obj_user = new JSONObject();
        JSONArray list_user = new JSONArray();
        
        try {

            if (utils.get_config("dummy").equals("false")) {
                String params_query[] = {};
                rows = mysql.getQuery(utils.get_config("db.connstr-event"), "CALL sp_user_list();", params_query);

                list_user = new JSONArray();
                int len = rows.size();
                for (int i = 0; i < len; i++) {
                    columns = (List<?>) rows.get(i);
                    user = new JSONObject();

                    user.put("user_id", columns.get(0).toString());
                    user.put("user_email", columns.get(1).toString());

                    list_user.put(user);

                }
                obj_user = new JSONObject();
                obj_user.put("user", list_user);

                user_list = obj_user.toString();
            } else {
//                user_list = "{\"rig\": [{\"rig_id\": \"123456\",\"rig_uuid\": \"ASWER123UYT657\",\"rig_datetime\": \"2017-01-01\",\" rig_firstname \": \"Luis\",\"rig_lastname \": \"BELLO\",\"rig_ country \": \"VENEZUELA\",\"rig_ image \": \"image.jpg\",\"rig_ phone \": \"04142723549\",\"rig_ sex \": \"M\",\"rig_ birthdate \": \"1984-21-01\"}]}";
                user.put("user_id", "123456");
                user.put("user_email", "gcastro@gmail.com");
                list_user.put(user);
                obj_user.put("user", list_user);
                user_list = obj_user.toString();
            }

            response = user_list;

        } catch (Exception e) {
            System.err.println("Error: " + e.toString());
            response = utils.get_msg("0032", Arrays.toString(e.getStackTrace()).substring(0, 300));
        } finally {
            return response;
        }
    }
    
    public static String read(String email) {
        String response = "";
        List<?> rows = null;
        List<?> columns = null;
        String user_list = "";
        try{
            if (utils.get_config("dummy").equals("false")) {
                String params_query[] = {email};
                rows = mysql.getQuery(utils.get_config("db.connstr-event"),"CALL sp_user_read(?);", params_query);
                JSONObject user = new JSONObject();
                JSONArray list_user = new JSONArray();
                int len = rows.size();
                for(int i=0;i<len;i++)
                {
                    columns = (List<?>) rows.get(i);
                    JSONObject users = new JSONObject();
                    users.put("user_id", columns.get(0).toString());
                    users.put("user_email", columns.get(1).toString());
                    users.put("user_password", columns.get(2).toString());
                    users.put("user_start_bat_data", ( columns.get(3) != null ? columns.get(3).toString() : ""  ));
                										
                    list_user.put(users);
                }
                JSONObject obj_user = new JSONObject();
                obj_user.put("user", list_user);
                user_list = obj_user.toString();
            }
            else {
//                user_list = "{\"action\": [{\"action_id\": \"123456\",\"action_uuid\": \"ASWER123UYT657\",\"action_create_datetime\": \"2017-01-01\",\" action_description \": \"Luis\",\"category_uuid \": \"BELLO\",\"team_uuid \": \"VENEZUELA\",,\"action_status \": \"04142723549\"}]}";
                user_list = "{}";
            }

            response = user_list;

        }
        catch (Exception e){
            System.err.println("Error: " + e);
            response = utils.get_msg("0038", e.toString());
        }
        finally
        {
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

        String user_email = objParams.getString("user_email");
        //Deberia llegar hasheada desde el front pero mientras tanto le hare md5 aqui
        String user_password = objParams.getString("user_email");

        //Aplico md5
        user_password = net.xem.common.utils.get_md5(user_password);
        JSONObject user_result = new JSONObject();

        try{
            if (utils.get_config("dummy").equals("false")) {

                String params[] = {user_email, user_password};
                mysql.execQuery(utils.get_config("db.connstr-event"), "CALL sp_user_create(?,?);", params);

                user_result.put("user_email", user_email);
                user_result.put("user_password", user_password);

                response = user_result.toString();

            }
            else {
                user_result.put("user_email", "gcastro@gmail.com");
                user_result.put("user_password", "12345678ABCDEFGH");

                response = user_result.toString();
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

    public static String update(String formParams, String email) throws JSONException {
        String response = "";		
        
        JSONObject objParams = null;
        try {
            objParams = new JSONObject(formParams);
        } catch (JSONException ex) {
            System.err.println("Error al convertir JSON: " + ex);
        }
        
        String user_email = objParams.getString("user_email");
        String user_password = objParams.getString("user_password");
        //Aplico md5
        user_password = net.xem.common.utils.get_md5(user_password);
        JSONObject user_result = new JSONObject();
        
        try{
            if (utils.get_config("dummy").equals("false")) {

                String params[] = {user_email, user_password};
                mysql.execQuery(utils.get_config("db.connstr-event"), "CALL sp_user_update(?,?);", params);

                user_result.put("user_email", user_email);
                user_result.put("user_password", user_password);

                response = user_result.toString();

            }
            else {
                user_result.put("user_email", "gcastro@gmail.com");
                user_result.put("user_password", "12345678ABCDEFGH");

                response = user_result.toString();
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

    public static String delete(String email) {
        String response = "";
        try {
            if (utils.get_config("dummy").equals("false")) {
                String params[] = { email };
                mysql.execQuery(utils.get_config("db.connstr-event"), "CALL sp_user_delete(?);", params);
                JSONObject user_result = new JSONObject();
                user_result.put("user_email", email);
                JSONObject user = new JSONObject();
                user.put("user", user_result);
                response = user.toString();
                
                //Elimino todos los rigs asociados con este usuario
                //Esto deberia arrojar un json que indique todos los rigs que fueron eliminados para unirlo con el responsede aqui
//                net.xem.business.rig.delete_by_user(email);
                
            } else {
                response = "{\"user\": [{\"user_email\": \"prueba@gmail.com\"}]}";
            }

        } catch (Exception e) {
            System.err.println("Error: " + e);
            response = utils.get_msg("0036", e.toString());
        } finally {
            return response;
        }
    }

}
