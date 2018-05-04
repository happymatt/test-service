package com.jhqc.pxsj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://192.168.31.246:3306/test?characterEncoding=utf8";
		String user = "pxsjuser";
		String passWord = "pxsj_123";
		System.out.println("Connecting to database...");
		Connection conn = DriverManager.getConnection(url,user,passWord);
		System.out.println(conn);
		//STEP 4: Execute a query
		System.out.println("Creating statement...");
		Statement stmt = conn.createStatement();
		String sql = "SELECT * from t_d_shop where shop_id = 2092329530079027";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			System.out.println(rs.getString("shop_name"));
			System.out.println(rs.getString("shop_logo"));
		}
			
		
		conn.close();

	}
}
