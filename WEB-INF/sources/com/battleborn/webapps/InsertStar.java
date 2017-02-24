 package com.battleborn.webapps;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class InsertStar extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {

	  HttpSession session = request.getSession(true);
	  System.out.println("test");
	  response.setContentType("text/html");
	  PrintWriter out = response.getWriter();
	  String message = "";


	  try {
		  
		  if (session.getAttribute("employeeEmail") != null && session.getAttribute("employeeEmail") != "" ) {

			  String loginUser = "testuser";
			  String loginPasswd = "testpass";
			  String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

			  Class.forName("com.mysql.jdbc.Driver").newInstance();
			  Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
	
			  Statement statement = connection.createStatement();

			  String starid = null;
			  String starName = "";
			  String starDoB = "";
			  String starPhotoUrl = "";
			  String starFirstName = "";
			  String starLastName = "";
			 
			  starName = request.getParameter("starName");
			  starDoB = request.getParameter("starDoB");
			  starPhotoUrl = request.getParameter("starPhotoUrl");

			 
		      String[] tokens = starName.split(" ");

		      if (tokens.length == 1)
		      {
		          // The input name cannot be split by whitespace.
		          starFirstName = "";
		          starLastName = starName;
		      } else if (tokens.length == 2)
		      {
		          starFirstName = tokens[0];
		          starLastName = tokens[1];
		      } else
		      {
		          message = "The input is invalid. Please input a valid name. E.g. Schwarzenegger or Arnold Schwarzenegger.";
		          request.setAttribute("message",message);
				  request.getRequestDispatcher("/InsertStar.jsp").forward(request,response);
		      }
		        
		      
		      String query = "Insert into stars values(" + starid + ", '" + starFirstName + "' , '" + starLastName + "' , '" + starDoB + "' , '" + starPhotoUrl + "')";
			  System.out.println(query); //DEBUG

		        
			  statement.executeUpdate(query);
			  
//			  statement.close();
			  connection.close();
	          message = "A star has been successfully added to the database.";
	          request.setAttribute("message",message);
			  request.getRequestDispatcher("/InsertStar.jsp").forward(request,response);
		  } else {
			  message="Please log in first to insert star.";
			  request.setAttribute("message",message);
			  request.getRequestDispatcher("/EmployeeLogin.jsp").forward(request,response);
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
  
  /** Handle GET and POST requests identically. */
  public void doPost(HttpServletRequest request,
                 HttpServletResponse response)
  throws ServletException, IOException {
  	doGet(request, response);
  }

}

