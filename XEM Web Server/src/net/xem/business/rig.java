package net.xem.business;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.ws.rs.core.MultivaluedMap;
import net.xem.common.utils;
import net.xem.connectors.mysql;
import java.io.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class rig {

    @SuppressWarnings("finally")
    public static String List() {
            String response = "";
            List<?> rows = null;
            List<?> columns = null;

            String rig_list = "";

            try {
                
                    if (utils.get_config("dummy").equals("false")) {
                            String params_query[] = {};
                            rows = mysql.getQuery(utils.get_config("db.connstr-event"), "CALL sp_rig_list();", params_query);
                            
                            JSONArray list_rig = new JSONArray();
                            int len = rows.size();
                            for (int i = 0; i < len; i++) {
                                    columns = (List<?>) rows.get(i);
                                    JSONObject rig = new JSONObject();

                                    rig.put("rig_id", columns.get(0).toString());
                                    rig.put("rig_uuid", columns.get(1).toString());
                                    rig.put("rig_create_datetime", columns.get(2).toString());
                                    rig.put("rig_name", (columns.get(3)!= null ? columns.get(3).toString() :"NONAME"));				
                                    rig.put("user_email", columns.get(4).toString());
                                    rig.put("rig_lan_ip", columns.get(5).toString());
                                    rig.put("rig_claymore_version", columns.get(6).toString());
                                    rig.put("rig_time_up", columns.get(7).toString());
                                    rig.put("rig_reset_today", columns.get(8).toString());
                                    rig.put("rig_claymore_reset_today", columns.get(9).toString());
                                    rig.put("rig_gpu_info_eth", columns.get(10).toString());
                                    rig.put("rig_gpu_second_coin", columns.get(11).toString());
                                    rig.put("rig_gpu_info_second_coin", columns.get(12).toString());
                                    rig.put("rig_start_bat_data", (columns.get(13)!= null ? columns.get(13).toString() :"NULL"));
                                    rig.put("location_uuid", (columns.get(14)!= null ? columns.get(14).toString() :"NULL"));
                                    rig.put("rig_reseter_number", (columns.get(15)!= null ? columns.get(15).toString() :"NULL"));		


                                    list_rig.put(rig);

                            }
                            JSONObject obj_rig = new JSONObject();
                            obj_rig.put("rig", list_rig);

                            rig_list = obj_rig.toString();                            
                    } else {
                            rig_list = "{\"rig\": [{\"rig_id\": \"123456\",\"rig_uuid\": \"ASWER123UYT657\",\"rig_datetime\": \"2017-01-01\",\" rig_firstname \": \"Luis\",\"rig_lastname \": \"BELLO\",\"rig_ country \": \"VENEZUELA\",\"rig_ image \": \"image.jpg\",\"rig_ phone \": \"04142723549\",\"rig_ sex \": \"M\",\"rig_ birthdate \": \"1984-21-01\"}]}";
                    }

                    response = rig_list;

            } catch (Exception e) {
                    e.printStackTrace();
                    response = utils.get_msg("0032", e.getCause().toString());
            } finally {
                    return response;
            }
    }

    @SuppressWarnings("finally")
    public static String Read(String RigUUID) {
            
            System.out.println("RigUUID = " + RigUUID);
            String response = "";

            List<?> rows = null;
            List<?> columns = null;

            String rig_list = "";

            try {

                    if (utils.get_config("dummy").equals("false")) {
                            String params_query[] = { RigUUID };
                            rows = mysql.getQuery(utils.get_config("db.connstr-event"), "CALL sp_rig_read(?);", params_query);

                            JSONObject rig = new JSONObject();
                            for (int i = 0; i < rows.size(); i++) {
                                    columns = (List<?>) rows.get(i);
                                    // Todo: add rig_id
                                    rig.put("rig_id", columns.get(0).toString());
                                    rig.put("rig_uuid", columns.get(1).toString());
                                    rig.put("rig_create_datetime", columns.get(2).toString());
                                    rig.put("rig_name", (columns.get(3)!= null ? columns.get(3).toString() :"NONAME"));				
                                    rig.put("user_email", columns.get(4).toString());
                                    rig.put("rig_lan_ip", columns.get(5).toString());
                                    rig.put("rig_claymore_version", columns.get(6).toString());
                                    rig.put("rig_time_up", columns.get(7).toString());
                                    rig.put("rig_reset_today", columns.get(8).toString());
                                    rig.put("rig_claymore_reset_today", columns.get(9).toString());
                                    rig.put("rig_gpu_info_eth", columns.get(10).toString());
                                    rig.put("rig_gpu_second_coin", columns.get(11).toString());
                                    rig.put("rig_gpu_info_second_coin", columns.get(12).toString());
                                    rig.put("rig_start_bat_data", (columns.get(13)!= null ? columns.get(13).toString() :"NULL"));
                                    rig.put("location_uuid", (columns.get(14)!= null ? columns.get(14).toString() :"NULL"));
                                    rig.put("rig_reseter_number", (columns.get(15)!= null ? columns.get(15).toString() :"NULL"));	



                            }
//				JSONObject obj_rig = new JSONObject();
//				obj_rig.put("rig", rig);
//				rig_list = obj_rig.toString();
                            rig_list = rig.toString();
                    } else {
                            rig_list = "{\"rig\": [{\"rig_id\": \"123456\",\"rig_uuid\": \"ASWER123UYT657\",\"rig_datetime\": \"2017-01-01\",\"rig_type\": \"EMAIL\",\"rig_status\": \"SENT\",\"rig_from\": \"JANIO\",\"rig_to\": \"JOSE\"},{\"rig_text\": \"HI\"}]}";
                    }

                    response = rig_list;

            } catch (Exception e) {
                    e.printStackTrace();
                    response = utils.get_msg("0033", Arrays.toString(e.getStackTrace()).substring(0, 300));
            } finally {
                    return response;
            }
    }

    @SuppressWarnings("finally")
    public static String read_by_user(String user_email ) {

            String response = "";

            List<?> rows = null;
            List<?> columns = null;

            String rig_list = "";
            try {

                    if (utils.get_config("dummy").equals("false")) {
                            String params_query[] = { user_email };
                            rows = mysql.getQuery(utils.get_config("db.connstr-event"), "CALL sp_rig_list_by_user(?);", params_query);
                            int len = rows.size();
                            JSONArray list_rig = new JSONArray();
                            for (int i = 0; i < len; i++) {
                                columns = (List<?>) rows.get(i);
                                JSONObject rig = new JSONObject();
                                rig.put("rig_id", columns.get(0).toString());
                                rig.put("rig_uuid", columns.get(1).toString());
                                rig.put("rig_create_datetime", columns.get(2).toString());
                                rig.put("rig_name", (columns.get(3)!= null ? columns.get(3).toString() :""));				
                                rig.put("user_email", columns.get(4).toString());
                                rig.put("rig_lan_ip", columns.get(5).toString());
                                rig.put("rig_claymore_version", columns.get(6).toString());
                                rig.put("rig_time_up", columns.get(7).toString());
                                rig.put("rig_reset_today", columns.get(8).toString());
                                rig.put("rig_claymore_reset_today", columns.get(9).toString());
                                rig.put("rig_gpu_info_eth", columns.get(10).toString());
                                rig.put("rig_gpu_second_coin", columns.get(11).toString());
                                rig.put("rig_gpu_info_second_coin", columns.get(12).toString());
                                rig.put("rig_start_bat_data", (columns.get(13)!= null ? columns.get(13).toString() :""));
                                rig.put("location_uuid", (columns.get(14)!= null ? columns.get(14).toString() :""));
                                rig.put("rig_reseter_number", (columns.get(15)!= null ? columns.get(15).toString() :""));	

                                list_rig.put(rig);

                            }
                            JSONObject obj_rig = new JSONObject();
                            obj_rig.put("rig", list_rig);
                            rig_list = obj_rig.toString();
                    } else {
                            rig_list = "{\"rig\": [{\"rig_id\": \"123456\",\"rig_uuid\": \"ASWER123UYT657\",\"rig_datetime\": \"2017-01-01\",\"rig_type\": \"EMAIL\",\"rig_status\": \"SENT\",\"rig_from\": \"JANIO\",\"rig_to\": \"JOSE\"},{\"rig_text\": \"HI\"}]}";
                    }

                    response = rig_list;

            } catch (Exception e) {
                    e.printStackTrace();
                    response = utils.get_msg("0033", Arrays.toString(e.getStackTrace()).substring(0, 300));
            } finally {
                    return response;
            }
    }

    @SuppressWarnings("finally")
    public static String Create(String formParams) throws JSONException {
        
        String a = formParams;
        String response = "";

        List<?> _Rig = null;
        List<?> columns = null;
        JSONObject gpu_info = new JSONObject();

        try {
            gpu_info = new JSONObject(a);
            String aux = gpu_info.getString("gpu_info");
            gpu_info = new JSONObject(aux);        
        } catch (JSONException e) {
            System.err.println("Error: " + e.toString());
        }  
        try {
            String rig_uuid = gpu_info.getString("rig_uuid");
            String rig_name = gpu_info.getString("rig_name");
            String user_email = gpu_info.getString("rig_email");
            String rig_lan_ip = gpu_info.getString("rig_lan_ip");
            String rig_claymore_version = gpu_info.getString("rig_claymore_version");
            String rig_time_up = gpu_info.getString("rig_time_up");
            String rig_gpu_info_eth = gpu_info.getJSONArray("rig_gpu_info_eth").toString();
            String rig_gpu_second_coin = gpu_info.getString("rig_gpu_second_coin");
            String rig_gpu_info_second_coin = gpu_info.getJSONArray("rig_gpu_info_second_coin").toString();

            String rig_start_bat_data = "";
            String uuid ="";
            String rig_reseter_number = "";


            if (utils.get_config("dummy").equals("false")) {
                
                //Si el RIG ya existe lo actualizo, sino lo creo
                String RigExist[] = { user_email, rig_name, rig_lan_ip };
                _Rig = mysql.getQuery(utils.get_config("db.connstr-event"), "CALL sp_rig_read_by_name_email_ip(?,?,?);", RigExist);
                
                if (_Rig.size() >= 1) {
                    System.out.println("SP_RIG_UPDATE");
                    for (int i = 0; i < _Rig.size(); i++) {
                        columns = (List<?>) _Rig.get(i);
                        uuid = columns.get(1).toString();
                        rig_reseter_number = (columns.get(15)!= null ? columns.get(15).toString() :"0");
                    }

                    String params[] = { uuid, rig_name, user_email, rig_lan_ip, rig_claymore_version, rig_time_up,
                                                            rig_gpu_info_eth, rig_gpu_second_coin, rig_gpu_info_second_coin, 
                                                            rig_start_bat_data, rig_reseter_number };

                    mysql.execQuery(utils.get_config("db.connstr-event"), "CALL sp_rig_update(?,?,?,?,?,?,?,?,?,?,?);", params);


                    JSONObject rig_result = new JSONObject();
                    rig_result.put("rig_uuid", rig_uuid);
                    rig_result.put("rig_name", rig_name);
                    rig_result.put("user_email", user_email);
                    rig_result.put("rig_lan_ip", rig_lan_ip);
                    rig_result.put("rig_claymore_version", rig_claymore_version);
                    rig_result.put("rig_time_up", rig_time_up);
                    rig_result.put("rig_gpu_info_eth", rig_gpu_info_eth);
                    rig_result.put("rig_gpu_second_coin", rig_gpu_second_coin);
                    rig_result.put("rig_gpu_info_second_coin", rig_gpu_info_second_coin);
                    rig_result.put("rig_start_bat_data", rig_start_bat_data);
                    
                    JSONObject _rig = new JSONObject();
                    _rig.put("rig", rig_result);
                    response = _rig.toString();

                } else {
                    System.out.println("SP_RIG_CREATE");
                    
                    String param[] = {  rig_uuid };
                    _Rig = mysql.getQuery(utils.get_config("db.connstr-event"), "CALL sp_rig_read(?);", param);

                    if (_Rig.size() > 0) {
                        //Es posible que este if no haga falta, porque en el codigo de python me encargo de verificar en el mismo momento si el uuid existe o no
                        JSONObject rig_result = new JSONObject();
                        rig_result.put("Codigo", 003);
                        rig_result.put("ERROR", "El rig_uuid: " + rig_uuid + " ya existe.");
                        response = rig_result.toString();
                    } else {
                        rig_reseter_number = "0";
                        String params[] = {  rig_uuid, rig_name, user_email, rig_lan_ip, rig_claymore_version, rig_time_up,
                                             rig_gpu_info_eth, rig_gpu_second_coin, rig_gpu_info_second_coin, rig_start_bat_data, rig_reseter_number };
                        mysql.execQuery(utils.get_config("db.connstr-event"), "CALL sp_rig_create(?,?,?,?,?,?,?,?,?,?,?);",
                                        params);
                        JSONObject rig_result = new JSONObject();
                        rig_result.put("rig_uuid", rig_uuid);
                        rig_result.put("rig_name", rig_name);
                        rig_result.put("user_email", user_email);
                        rig_result.put("rig_lan_ip", rig_lan_ip);
                        rig_result.put("rig_claymore_version", rig_claymore_version);
                        rig_result.put("rig_time_up", rig_time_up);
                        rig_result.put("rig_gpu_info_eth", rig_gpu_info_eth);
                        rig_result.put("rig_gpu_second_coin", rig_gpu_second_coin);
                        rig_result.put("rig_gpu_info_second_coin", rig_gpu_info_second_coin);
                        rig_result.put("rig_start_bat_data", rig_start_bat_data);
                        rig_result.put("rig_reseter_number", rig_reseter_number);

                        //Creo que esto de guardar un json dentro de otro json esta malo.. no hace falta! 
                        JSONObject rig = new JSONObject();
                        rig.put("rig", rig_result);
                        response = rig.toString();

                        //Creacion de accion asociada al rig_uuid
                        JSONObject actionObj = new JSONObject();
                        actionObj.put("action_change_claymore_version", 0);
                        actionObj.put("action_change_start_bat", 0);
                        actionObj.put("action_download_claymore_version", 0);
                        actionObj.put("action_restart_claymore", 0);
                        actionObj.put("action_reset_rig", "");
                        actionObj.put("rig_uuid", rig_uuid);
                        net.xem.business.action.create(actionObj.toString());

                    }
                }

            } else {
                    response = "{\"rig\": [{\"rig_uuid\": \"ASWER123UYT657\",\"rig_datetime_create\": \"2017-01-01\",\"rig_value \": \"Prueba Value\",\"rig_name \": \"Prueba Name\",\"group_uuid \": \"ASWER123UYT657\",\"rig_rig_id \": \"ASWER123UYT657\"}]}";
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.print(e);
            response = utils.get_msg("0034", Arrays.toString(e.getStackTrace()).substring(0, 300));
        } finally {
            System.out.println("response = " + response);
            return response;
        }
    }

    @SuppressWarnings("finally")
    public static String Update(final MultivaluedMap<String, String> formParams, String uuid) {

            String response = "";

            String Email = formParams.get("Email").toString().replaceAll("\\[|\\]", "");
            String Rigname = formParams.get("Rigname").toString().replaceAll("\\[|\\]", "");
            String LanIp = formParams.get("LanIp").toString().replaceAll("\\[|\\]", "");
            String ClaymoreVersion = formParams.get("ClaymoreVersion").toString().replaceAll("\\[|\\]", "");
            String TimeUp = formParams.get("TimeUp").toString().replaceAll("\\[|\\]", "");
            String ResetToday = formParams.get("ResetToday").toString().replaceAll("\\[|\\]", "");
            String ClaymoreRigResetToday = formParams.get("ClaymoreRigResetToday").toString().replaceAll("\\[|\\]", "");
            String GPUInfoETH = formParams.get("GPUInfoETH").toString().replaceAll("\\[|\\]", "");
            String SecondCoin = formParams.get("SecondCoin").toString().replaceAll("\\[|\\]", "");
            String GPUInfoSecondCoin = formParams.get("GPUInfoSecondCoin").toString().replaceAll("\\[|\\]", "");
            String rig_start_bat_data = formParams.get("rig_start_bat_data").toString().replaceAll("\\[|\\]", "");		
            String LocationUUID = formParams.get("LocationUUID").toString().replaceAll("\\[|\\]", "");
            String rig_reseter_number = formParams.get("rig_reseter_number").toString().replaceAll("\\[|\\]", "");

            try {

                    if (utils.get_config("dummy").equals("false")) {
                            String params[] = { uuid, Email, Rigname, LanIp, ClaymoreVersion, TimeUp, ResetToday,
                                            ClaymoreRigResetToday, GPUInfoETH, SecondCoin, GPUInfoSecondCoin, rig_start_bat_data,LocationUUID, rig_reseter_number };
                            mysql.execQuery(utils.get_config("db.connstr-event"), "CALL SPRigUpdate(?,?,?,?,?,?,?,?,?,?,?,?,?,?);",
                                            params);

                            JSONObject rig_result = new JSONObject();
                            rig_result.put("RigUUID", uuid);
                            rig_result.put("Email", Email);
                            rig_result.put("Rigname", Rigname);
                            rig_result.put("LanIp", LanIp);
                            rig_result.put("ClaymoreVersion", ClaymoreVersion);
                            rig_result.put("TimeUp", TimeUp);
                            rig_result.put("ResetToday", ResetToday);
                            rig_result.put("ClaymoreRigResetToday", ClaymoreRigResetToday);
                            rig_result.put("GPUInfoETH", GPUInfoETH);
                            rig_result.put("SecondCoin", SecondCoin);
                            rig_result.put("GPUInfoSecondCoin", GPUInfoSecondCoin);
                            rig_result.put("LocationUUID", LocationUUID);

                            JSONObject rig = new JSONObject();
                            rig.put("rig", rig_result);
                            response = rig.toString();
                    } else {
                            response = "{\"rig\": [{\"rig_uuid\": \"ASWER123UYT657\",\"rig_datetime_create\": \"2017-01-01\",\"rig_value \": \"Prueba Value\",\"rig_name \": \"Prueba Name\",\"group_uuid \": \"ASWER123UYT657\",\"rig_rig_id \": \"ASWER123UYT657\"}]}";
                    }
            } catch (Exception e) {
                    e.printStackTrace();
                    response = utils.get_msg("0035", Arrays.toString(e.getStackTrace()).substring(0, 300));
            } finally {
                    return response;
            }
    }

    @SuppressWarnings("finally")
    public static String Delete(String uuid) {
        String response = "";

        try {

            if (utils.get_config("dummy").equals("false")) {
                String params[] = { uuid };
                mysql.execQuery(utils.get_config("db.connstr-event"), "CALL sp_rig_delete(?);", params);
                
                JSONObject rig_result = new JSONObject();
                rig_result.put("rig_uuid", uuid);
                
                JSONObject rig = new JSONObject();
                rig.put("rig", rig_result);
                response = rig.toString();

                //Delete action associated with rig
//                mysql.execQuery(utils.get_config("db.connstr-event"), "CALL sp_action_delete_by_rig(?);", params);

                //Delete reset associated with rig
//                mysql.execQuery(utils.get_config("db.connstr-event"), "CALL sp_reset_delete(?);", params);

                //Delete location associated with rig
//                mysql.execQuery(utils.get_config("db.connstr-event"), "CALL sp_location_rig_delete(?);", params);

            } else {
                response = "{\"rig\": [{\"rig_uuid\": \"ASWER123UYT657\"}]}";
            }

        } catch (Exception e) {
            e.printStackTrace();
            response = utils.get_msg("0036", e.toString());
        } finally {
            return response;
        }
    }


    @SuppressWarnings("finally")
    public static String delete_by_user(String user_email) {

            String rig_list = read_by_user(user_email);
                        
            JSONObject rig_list_json = new JSONObject();
            try {
                rig_list_json = new JSONObject(rig_list);
            } catch (JSONException ex) {
                System.err.println("Error al convertir JSON: " + ex);
            }
                        
            try {
                JSONArray rig_array = rig_list_json.getJSONArray("rig");
                
                int N = rig_array.length();
                for (int i = 0; i < N; i++) {
                    //Llamo ala funcion de Delete para borrar todos los Rigs asociados y a su vez, todos los action, reset y locations asociados
                    Delete(rig_array.getJSONObject(i).getString("rig_uuid"));
                }  
            } catch (Exception e) {
                System.err.println("Error al obtener JSONArray: " + e);
            }
            return "";
            
	}

    
    public static String update_rigs_start_bat(String user_uuid, String user_start_bat_data) throws JSONException {
        String response = "";
        JSONObject rig_result = new JSONObject();
        List<?> rows = null;
        List<?> columns = null;
        try{
            if (utils.get_config("dummy").equals("false")) {
                
                //Cambio todos los rig_start_bat_data en RIG por el del usuario
                String params[] = {user_start_bat_data, user_uuid};
                mysql.execQuery(utils.get_config("db.connstr-event"), "UPDATE `xem_tbl_rig`\n" +
                                                                      "SET"
                                                                    + "`rig_start_bat_data` = ?"
                                                                    + "WHERE `user_email` = ?", params);

                rig_result.put("user_email", user_uuid);
                rig_result.put("user_start_bat_data", user_start_bat_data);

                
                //Busco todos los rigs_uuid pertenecientes al user
                String params_query[] = {user_uuid};
                rows = mysql.getQuery(utils.get_config("db.connstr-event"), "SELECT `rig_uuid` FROM `xem_tbl_rig` WHERE `user_email` = ?;", params_query);
                int len = rows.size();
                
                //Por cada rig_uuid le cambio la accion de "action_change_start_bat" a TRUE
                for (int i = 0; i < len; i++) {
                    columns = (List<?>) rows.get(i);
                                       
                    String params_action[] = { columns.get(0).toString() };
                    mysql.execQuery(utils.get_config("db.connstr-event"), "UPDATE `xem_tbl_action` SET `action_change_start_bat` = 1 WHERE `rig_uuid` = ?", params_action);

                }
                response = rig_result.toString();

            }
            else {
                rig_result.put("user_email", "gcastro@gmail.com");
                rig_result.put("user_start_bat_data", "");

                response = rig_result.toString();
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
        
        
        
        
}
