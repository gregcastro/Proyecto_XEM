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
			response = utils.get_msg("0032", Arrays.toString(e.getStackTrace()).substring(0, 300));
		} finally {
			return response;
		}
	}

	@SuppressWarnings("finally")
	public static String Read(String RigUUID) {

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
				JSONObject obj_rig = new JSONObject();
				obj_rig.put("rig", rig);
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
	public static String read_by_user(String user_email ) {

		String response = "";

		List<?> rows = null;
		List<?> columns = null;

		String rig_list = "";
		try {

			if (utils.get_config("dummy").equals("false")) {
				String params_query[] = { user_email };
				rows = mysql.getQuery(utils.get_config("db.connstr-event"), "CALL sp_rig_list_by_user(?);", params_query);
				JSONObject rig = new JSONObject();
                                
                                for (int i = 0; i < rows.size(); i++) {
					columns = (List<?>) rows.get(i);
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


				}
				JSONObject obj_rig = new JSONObject();
				obj_rig.put("rig", rig);
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
	public static String Create(final MultivaluedMap<String, String> formParams) throws JSONException {
		String a = formParams.toString();
		String response = "";

                a = a.substring(11,a.length() -2);
		a = a.replaceAll("'","\"");
		
                System.out.println("a = " + a);

		List<?> _Rig = null;
		List<?> columns = null;
		JSONObject gpu_info = new JSONObject();
				
		try {
			gpu_info = new JSONObject(a);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		System.out.println(gpu_info['rig_name']);

		String rig_uuid = UUID.randomUUID().toString();
		String rig_name = gpu_info.getString("rig_name");
		String user_email = gpu_info.getString("user_email");
		String rig_lan_ip = gpu_info.getString("rig_lan_ip");
		String rig_claymore_version = gpu_info.getString("rig_claymore_version");
		String rig_time_up = gpu_info.getString("rig_time_up");
		String rig_reset_today = gpu_info.getString("rig_reset_today");
		String rig_claymore_reset_today = gpu_info.getString("rig_claymore_reset_today");
		String rig_gpu_info_eth = gpu_info.getJSONArray("rig_gpu_info_eth").toString();
		String rig_gpu_second_coin = gpu_info.getString("rig_gpu_second_coin");
		String rig_gpu_info_second_coin = gpu_info.getJSONArray("rig_gpu_info_second_coin").toString();
		String location_uuid = gpu_info.getString("location_uuid");
		String rig_start_bat_data = "";
		String uuid ="";
		String rig_reseter_number = "";

		try {

			if (utils.get_config("dummy").equals("false")) {

				String RigExist[] = { user_email, rig_name };
				_Rig = mysql.getQuery(utils.get_config("db.connstr-event"), "CALL sp_rig_read_by_name_and_email(?,?);",
						RigExist);

				if (_Rig.size() >= 1) {

					for (int i = 0; i < _Rig.size(); i++) {
						columns = (List<?>) _Rig.get(i);
						uuid = columns.get(1).toString();
						rig_reseter_number = (columns.get(15)!= null ? columns.get(15).toString() :"0");
					}

					String params[] = { uuid, rig_name, user_email, rig_lan_ip, rig_claymore_version, rig_time_up, rig_reset_today,
							rig_claymore_reset_today, rig_gpu_info_eth, rig_gpu_second_coin, rig_gpu_info_second_coin, rig_start_bat_data, location_uuid,rig_reseter_number };
					mysql.execQuery(utils.get_config("db.connstr-event"), "CALL sp_rig_update(?,?,?,?,?,?,?,?,?,?,?,?,?,?);",
							params);

					JSONObject rig_result = new JSONObject();
					rig_result.put("rig_uuid", rig_uuid);
					rig_result.put("rig_name", rig_name);
					rig_result.put("user_email", user_email);
					rig_result.put("rig_lan_ip", rig_lan_ip);
					rig_result.put("rig_claymore_version", rig_claymore_version);
					rig_result.put("rig_time_up", rig_time_up);
					rig_result.put("rig_reset_today", rig_reset_today);
					rig_result.put("rig_claymore_reset_today", rig_claymore_reset_today);
					rig_result.put("rig_gpu_info_eth", rig_gpu_info_eth);
					rig_result.put("rig_gpu_second_coin", rig_gpu_second_coin);
					rig_result.put("rig_gpu_info_second_coin", rig_gpu_info_second_coin);
					rig_result.put("location_uuid", location_uuid);


					JSONObject _rig = new JSONObject();
					_rig.put("rig", rig_result);
					response = _rig.toString();

				} else {
					String params[] = {  rig_uuid, rig_name, user_email, rig_lan_ip, rig_claymore_version, rig_time_up, rig_reset_today,
							rig_claymore_reset_today, rig_gpu_info_eth, rig_gpu_second_coin, rig_gpu_info_second_coin, rig_start_bat_data, location_uuid };
					mysql.execQuery(utils.get_config("db.connstr-event"), "CALL sp_rig_create(?,?,?,?,?,?,?,?,?,?,?,?,?);",
							params);

					JSONObject rig_result = new JSONObject();
					rig_result.put("rig_uuid", rig_uuid);
					rig_result.put("rig_name", rig_name);
					rig_result.put("user_email", user_email);
					rig_result.put("rig_lan_ip", rig_lan_ip);
					rig_result.put("rig_claymore_version", rig_claymore_version);
					rig_result.put("rig_time_up", rig_time_up);
					rig_result.put("rig_reset_today", rig_reset_today);
					rig_result.put("rig_claymore_reset_today", rig_claymore_reset_today);
					rig_result.put("rig_gpu_info_eth", rig_gpu_info_eth);
					rig_result.put("rig_gpu_second_coin", rig_gpu_second_coin);
					rig_result.put("rig_gpu_info_second_coin", rig_gpu_info_second_coin);
					rig_result.put("location_uuid", location_uuid);
					rig_result.put("rig_start_bat_data", rig_start_bat_data);
					

					JSONObject rig = new JSONObject();
					rig.put("rig", rig_result);
					response = rig.toString();

				}

			} else {
				response = "{\"rig\": [{\"rig_uuid\": \"ASWER123UYT657\",\"rig_datetime_create\": \"2017-01-01\",\"rig_value \": \"Prueba Value\",\"rig_name \": \"Prueba Name\",\"group_uuid \": \"ASWER123UYT657\",\"rig_rig_id \": \"ASWER123UYT657\"}]}";
			}

		} catch (Exception e) {
			e.printStackTrace();
			response = utils.get_msg("0034", Arrays.toString(e.getStackTrace()).substring(0, 300));
		} finally {
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
				mysql.execQuery(utils.get_config("db.connstr-event"), "SPRigDelete(?);", params);
				JSONObject rig_result = new JSONObject();
				rig_result.put("RigUUID", uuid);
				JSONObject rig = new JSONObject();
				rig.put("rig", rig_result);
				response = rig.toString();
			} else {
				response = "{\"group\": [{\"rig_uuid\": \"ASWER123UYT657\"}]}";
			}

		} catch (Exception e) {
			e.printStackTrace();
			response = utils.get_msg("0036", Arrays.toString(e.getStackTrace()).substring(0, 300));
		} finally {
			return response;
		}
	}
}
