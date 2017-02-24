 package com.battleborn.webapps;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.sql.CallableStatement;


public class InsertMovie extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {

	  HttpSession session = request.getSession(true);
	  System.out.println("test");
	  response.setContentType("text/html");
	  PrintWriter out = response.getWriter();
	  String message = "";
      String addmoviemsg = "";
      String linkstarmsg = "";
      String linkgenremsg = "";
	  String title = "";
	  String year = "2000";
	  String yearstr = "";
	  	  
	  String director = "";
	  String starName = "";
	  String genre = "";
	  String bannerurl = "";
	  String trailerurl = "";
	  String starFirstName = "";
	  String starLastName = "";
	  String query = "";

	  try {
		  
		  if (session.getAttribute("employeeEmail") != null && session.getAttribute("employeeEmail") != "" ) {

			  String loginUser = "testuser";
			  String loginPasswd = "testpass";
			  String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

			  Class.forName("com.mysql.jdbc.Driver").newInstance();
			  Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
	
			  Statement statement = connection.createStatement();
			  
			  title = request.getParameter("movietitle");
			  yearstr = request.getParameter("movieyear");
			  
			  year = (yearstr == null || yearstr == "") ? "2000" : yearstr;
			  
			  director = request.getParameter("director");
			  starName = request.getParameter("starname");
			  genre = request.getParameter("moviegenre");
			  bannerurl = request.getParameter("bannerurl");
			  trailerurl = request.getParameter("trailerurl");

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
		          message = "The input star name is invalid. Please input a valid name. E.g. Schwarzenegger or Arnold Schwarzenegger.";
		          request.setAttribute("message",message);
				  request.getRequestDispatcher("/InsertMovie.jsp").forward(request,response);
		      }
		      
		      if (title != null && title != "") {
		    	  
		    	  query = "call add_movie ('" + title + "' , " + year + ", '" + director + "' , '" + bannerurl + "' , '" + trailerurl + "' , '" + starFirstName + "' , '" + starLastName + "' , '"+ genre + "' , @msg_addmovie, @msg_linkstar, @msg_linkgenre)";
		    	  System.out.println(query); //DEBUG
		    	  statement.executeQuery(query);
			  
		    	  query = "select @msg_addmovie";
		    	  ResultSet rs = statement.executeQuery(query);
			  
		    	  while (rs.next()) {
		    		  addmoviemsg = rs.getString(1);
		    		  System.out.println(addmoviemsg);
		    	  }

		    	  rs.close();
			  
		    	  query = "select @msg_linkstar";
		    	  rs = statement.executeQuery(query);
			  
		    	  while (rs.next()) {
		    		  linkstarmsg = rs.getString(1);
		    		  System.out.println(linkstarmsg);
		    	  }

		    	  query = "select @msg_linkgenre";
		    	  rs = statement.executeQuery(query);
			  
		    	  while (rs.next()) {
		    		  linkgenremsg = rs.getString(1);
		    		  System.out.println(linkgenremsg);
		    	  }

			  
		    	  rs.close();
		    	  statement.close();
		    	  connection.close();
		    	  
				  request.setAttribute("addmoviemsg",addmoviemsg);
		          request.setAttribute("linkstarmsg",linkstarmsg);
		          request.setAttribute("linkgenremsg",linkgenremsg);
		          request.setAttribute("message",message);
				  request.getRequestDispatcher("/InsertMovie.jsp").forward(request,response);
			  
		      } else {
		    	  message = "Title cannot be empty. Please input title for the movie.";
				  request.setAttribute("message",message);
				  request.getRequestDispatcher("/InsertMovie.jsp").forward(request,response);
		      }

		  } else {
			  message="Please log in first to insert movie.";
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

