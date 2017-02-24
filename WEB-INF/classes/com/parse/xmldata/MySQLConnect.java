package com.parse.xmldata;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnect 
{
	private static String loginUser = "testuser";
	private static String loginPassword = "testpass";
	private static String loginURL = "jdbc:mysql://localhost:3306/moviedb";
	private static Connection conn;
	
	public static Connection getConn() throws Exception
	{
		if (conn == null || conn.isClosed())
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(loginURL, loginUser, loginPassword);
		}
		
		return conn;
	}
	public static void closeConn() throws Exception
	{
		if (conn != null && !conn.isClosed())
		{
			conn.close();
			conn = null;
		}
	}

}
