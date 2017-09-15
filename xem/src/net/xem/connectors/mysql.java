package net.xem.connectors;

import java.sql.*;
import java.util.*;

public class mysql {
	
	public static List<?> getQuery(String connstr, String query, String params[]) throws Exception{
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prep_st = null;
		
		List<?> list = null;
        
		try {
			
			con = DriverManager.getConnection(connstr);
			prep_st = con.prepareStatement(query);
			
			for(int i=1; i <= params.length; i++){
				prep_st.setString(i, params[i-1]);
			}
			
			rs = prep_st.executeQuery();
	        list = resultSetToArrayList(rs);
	        
		}finally{
			
			if(rs!=null)
				rs.close();
			
			if(prep_st!=null)
				prep_st.close();
				
			if(con!=null)
				con.close();
			
		}
		
		return list;
		
	}
	
	public static void execQuery(String connstr, String query, String params[]) throws Exception{
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = null;
		PreparedStatement prep_st = null;
		
		try {
			
			con = DriverManager.getConnection(connstr);
			prep_st = con.prepareStatement(query);
			
			for(int i=1; i <= params.length; i++){
				prep_st.setString(i, params[i-1]);
			}
			
			prep_st.execute();
	        
		}finally{
			
			if(prep_st!=null)
				prep_st.close();
			
			if(con!=null)
				con.close();
			
		}
		
	}
	
	public static List<ArrayList<Object>> resultSetToArrayList(ResultSet rs) throws Exception{
		
		  ResultSetMetaData md = rs.getMetaData();
		  int columns = md.getColumnCount();
		  ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
		  
		  while (rs.next()){
			  
			 ArrayList<Object> row = new ArrayList<Object>(columns);
		     
			 for(int i=1; i<=columns; ++i){
				 
		     	 row.add(rs.getObject(i));
		     	 
		     }
			 
		     list.add(row);
		     
		  }

		 return list;
	}
	
}