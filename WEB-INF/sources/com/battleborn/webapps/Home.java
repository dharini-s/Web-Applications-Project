package com.battleborn.webapps;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Home extends HttpServlet {

	public String ReturnServletInfo() {
		return "Servlet connects to MySQL to verify the details of user.";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		  HttpSession session = request.getSession(true);
		  System.out.println("test");
		  response.setContentType("text/html");
		  PrintWriter out = response.getWriter();
//		  String customeremail = "mcosley@gmail.com";
//		  String customerid = "973020";
		  String movieid = "";
		  String newQuantity = "";

		  try {
			  
			  if (session.getAttribute("customerID") != null && session.getAttribute("customerID") != "" ) {
				  String customerid = session.getAttribute("customerID").toString();    
			  
				  System.out.println(movieid);
				  System.out.println(newQuantity);
	    
				  String loginUser = "testuser";
				  String loginPasswd = "testpass";
				  String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

				  Class.forName("com.mysql.jdbc.Driver").newInstance();
				  Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
		
				  Statement statement = connection.createStatement();

				  String query = "select name from genres;";
				  ArrayList<String> genres = new ArrayList<String>();
			  
				  ResultSet rs = statement.executeQuery(query);
				  
				  while (rs.next()) {
						genres.add(rs.getString(1));/////index is 1??
						System.out.println(rs.getString(1));
				  }

				  request.setAttribute("genres", genres);

				  request.getRequestDispatcher("/MainPage.jsp").forward(request, response);
				
						//session.setAttribute("flag",flag);
					System.out.println("Logged in successfully");			
					if(rs!=null)
						rs.close();
					if(statement!=null)
						statement.close();
					out.close();	
					
			} else {

				String message ="";
				message="Please log in first.";
				request.setAttribute("message",message);
				request.getRequestDispatcher("/Login.jsp").forward(request, response);


				out.close();
			}
		} 
		catch (SQLException ex) {
			while (ex != null) {
				System.out.println ("SQL Exception:  " + ex.getMessage ());
				ex = ex.getNextException ();
			}  // end while
		}  // end catch SQLException

		catch(java.lang.Exception ex)
		{
			out.println("<HTML>" +
			"<HEAD><TITLE>" +
			"MovieDB: Error" +
			"</TITLE></HEAD>\n<BODY>" +
			"<P>SQL error in doPost: " +
			ex.getMessage() + "</P></BODY></HTML>");
			return;
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
