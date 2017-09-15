package net.xem.common;

import java.util.*;

public class utils {
	
	private static ResourceBundle rb = ResourceBundle.getBundle("Config");
	
public static String make_id(){
		
		Calendar c = GregorianCalendar.getInstance();
		
		int YYYY = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minutes = c.get(Calendar.MINUTE);
		int seconds = c.get(Calendar.SECOND);
		
		String MM = "";
		month = month+1;
		if(month < 10){ MM = "0" + month;}else{ MM = "" + month;}
		
		String DD = "";
		if(day < 10){ DD = "0" + day;}else{ DD = "" + day;}
				
		String HH = "";
		if(hour < 10){ HH = "0" + hour;}else{ HH = "" + hour;}
		
		String mm = "";
		if(minutes < 10){ mm = "0" + minutes;}else{ mm = "" + minutes;}
		
		String SS = "";
		if(seconds < 10){ SS = "0" + seconds;}else{ SS = "" + seconds;}
					
		return YYYY+""+MM+""+DD+""+HH+""+mm+""+SS;
		
		
	}
	
public static String make_uuid(){
	String uuid = UUID.randomUUID().toString();
	return uuid;
}

public static String day_time(){
	
	Calendar c = GregorianCalendar.getInstance();
	
	int YYYY = c.get(Calendar.YEAR);
	int month = c.get(Calendar.MONTH);
	int day = c.get(Calendar.DAY_OF_MONTH);
	int hour = c.get(Calendar.HOUR_OF_DAY);
	int minutes = c.get(Calendar.MINUTE);
	int seconds = c.get(Calendar.SECOND);
	
	String MM = "";
	month = month+1;
	if(month < 10){ MM = "0" + month;}else{ MM = "" + month;}
	
	String DD = "";
	if(day < 10){ DD = "0" + day;}else{ DD = "" + day;}
			
	String HH = "";
	if(hour < 10){ HH = "0" + hour;}else{ HH = "" + hour;}
	
	String mm = "";
	if(minutes < 10){ mm = "0" + minutes;}else{ mm = "" + minutes;}
	
	String SS = "";
	if(seconds < 10){ SS = "0" + seconds;}else{ SS = "" + seconds;}
				
	return YYYY+"-"+MM+"-"+DD+" "+HH+":"+mm+":"+SS;		
	
}

public static String get_config(String key){
	
	try {
		return rb.getString(key);	
	} catch (Exception e) {
		return "";
	}
}
	
public static void log(String element, String line) throws Exception{
	
    System.out.println(element + "|" + day_time() + "|" + line);
    
}

public static String get_msg(String code, String detail){

	try {		
		return "{\"error\": [{\"error_code\": \"" + code + "\",\"error_message\": \"" + rb.getString("msg."+code) + "\",\"error_detail\": \"\",\"error_datetime\": \"" + utils.day_time() + "\"}]}";
	} catch (Exception e) {
		return "{\"error\": [{\"error_code\": \"" + code + "\",\"error_message\": \"Error\",\"error_detail\": \"" + detail + "\",\"error_datetime\": \"" + utils.day_time() + "\"}]}";
	}
}

}
