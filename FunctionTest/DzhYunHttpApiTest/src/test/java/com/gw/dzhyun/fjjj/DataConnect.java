package com.gw.dzhyun.fjjj;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnect {

	private static Connection conn;

	public DataConnect() {

	}

	public static Connection getConnect() {
		try {
			if (null == conn) {
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://10.15.98.122:33061/dzh_stock";
				//String url = "jdbc:mysql://10.15.107.180:3306/litaojun";
				Class.forName(driver);
				conn = DriverManager.getConnection(url, "dzhread", "111111");
				//conn = DriverManager.getConnection(url, "litaojun", "litaojun");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}