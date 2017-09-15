package net.xem.connectors;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class http {
	
	public static String invokeURL(String url, String method, String output, String[] headers) throws Exception{
		
		String response = "";
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(method);
		
		for(String header : headers){
			if(!header.equals(""))
				conn.addRequestProperty(header.split("=")[0], header.split("=")[1]);
		}
		
		conn.setConnectTimeout(30000);
		
		if(output!=""){			
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream())); 
			out.write(output);
			out.flush();
			out.close();
		}			
		
		BufferedReader in = null;
		
		if(conn.getResponseCode() >= 400){
			in = new BufferedReader( new InputStreamReader( conn.getErrorStream() ) );
		}else{
			in = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );
		}
		
		String remoteresponse;
		
		while ( (remoteresponse = in.readLine()) != null ) {
			response += remoteresponse;
		}
		
		in.close();
		return response;
		
	}

}