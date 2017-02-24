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

import com.battleborn.webapps.Movie;

public class SingleStar extends HttpServlet {
	public static boolean flag;

	public String ReturnServletInfo() {
		return "Servlet returns details of one movie.";
	}

	  public void doGet(HttpServletRequest request,
              HttpServletResponse response) throws ServletException, IOException {

		  response.setContentType("text/html");
		  PrintWriter out = response.getWriter();
		  
		  String fName = "";
		  String lName = "";
		  String starname = "";
		  java.sql.Date dob = java.sql.Date.valueOf("2100-01-01");;
		  String starid = "";
		  String photourl = "";
		  ArrayList <Movie> movies = new ArrayList<Movie>(); 

		  try {
			  starid = request.getParameter("starid");
			  if (starid != null) {
		
				 String loginUser = "testuser";
				 String loginPasswd = "testpass";
				 String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

				 Class.forName("com.mysql.jdbc.Driver").newInstance();
				 Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
		
				 String queryStarPerId ="select * from stars where id = '" + starid + "'";	
				 Statement selectStarPerId = connection.createStatement();
				 ResultSet resultStarPerId = selectStarPerId.executeQuery(queryStarPerId);

				 if (resultStarPerId.next()) {
			
					 fName = resultStarPerId.getString("first_name");
					 lName = resultStarPerId.getString("last_name");
					 starname = fName + " " + lName;
					 photourl = resultStarPerId.getString("photo_url");
					 dob = resultStarPerId.getDate("dob");
					 
				 }
		
		
				 String queryMovies ="SELECT * FROM movies where id in ( SELECT movie_id from stars_in_movies where star_id = " + starid + ")";	
				 Statement selectMovies = connection.createStatement();
				 ResultSet resultMovies = selectMovies.executeQuery(queryMovies);
				 System.out.println(queryMovies);
		
				 while (resultMovies.next()) {
					 Movie movie = new Movie();
					 int movieId = resultMovies.getInt("id");
					 String title = resultMovies.getString("title");
					 int year = resultMovies.getInt("year");
					 movie.setID(movieId); 
					 movie.setTitle(title);
					 movie.setYear(year);
					 movies.add(movie);
				 }
				 
				 request.setAttribute("starid",starid);
				 request.setAttribute("starname", starname);
				 request.setAttribute("dob", dob);
				 request.setAttribute("photourl", photourl);
				 request.setAttribute("movies", movies); 
				 request.getRequestDispatcher("/SingleStar.jsp").forward(request, response);

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
