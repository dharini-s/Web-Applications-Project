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

import com.battleborn.webapps.Star;

public class SingleMovie extends HttpServlet {

	public String ReturnServletInfo() {
		return "Servlet returns details of one movie.";
	}

	  public void doGet(HttpServletRequest request,
              HttpServletResponse response) throws ServletException, IOException {

		  response.setContentType("text/html");
		  PrintWriter out = response.getWriter();
		  
		  String title = "";
		  int year = 0;
		  String director = "";
		  String bannerUrl = "";
		  String trailer = "";
		  String price = "$15.99";
		  ArrayList <String> genres = new ArrayList<String>(); 
		  ArrayList <Star> stars = new ArrayList<Star>(); 
		  
		  
		  try {
			  
			  String movieid = request.getParameter("movieid");
			  if (movieid != null) {
		
				 String loginUser = "testuser";
				 String loginPasswd = "testpass";
				 String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

				 Class.forName("com.mysql.jdbc.Driver").newInstance();
				 Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
		
				 String queryMoviePerId ="select * from movies where id = '" + movieid + "'";	
				 Statement selectMoviePerId = connection.createStatement();
				 ResultSet resultMoviePerId = selectMoviePerId.executeQuery(queryMoviePerId);

				 if (resultMoviePerId.next()) {
			
					 title = resultMoviePerId.getString(2);
					 year = resultMoviePerId.getInt(3);
					 director = resultMoviePerId.getString(4);
					 bannerUrl = resultMoviePerId.getString(5);
					 trailer = resultMoviePerId.getString(6);
				 }
		
				 String queryGenre ="SELECT name FROM genres where id in ( SELECT genre_id from genres_in_movies where movie_id = " + movieid + ")";	
				 Statement selectGenre = connection.createStatement();
				 ResultSet resultGenre = selectGenre.executeQuery(queryGenre);
				 System.out.println(queryGenre);

				 while (resultGenre.next()) {
					 genres.add(resultGenre.getString(1));
					 System.out.println(resultGenre.getString(1));
				 }
		
				 String queryStars ="SELECT * FROM stars where id in ( SELECT star_id from stars_in_movies where movie_id = " + movieid + ")";	
				 Statement selectStars = connection.createStatement();
				 ResultSet resultStars = selectStars.executeQuery(queryStars);
				 System.out.println(queryStars);
		
				 while (resultStars.next()) {
					 Star star = new Star();
					 int starId = resultStars.getInt("id");
					 String fName = resultStars.getString(2);
					 String lName = resultStars.getString(3);
					 star.setID(starId);
					 star.setFirstName(fName);
					 star.setLastName(lName);
					 stars.add(star);
				 }
				 
				 request.setAttribute("movieid",movieid);
				 request.setAttribute("genres", genres);
				 request.setAttribute("title", title);
				 request.setAttribute("year", year);
				 request.setAttribute("director", director);
				 request.setAttribute("bannerUrl", bannerUrl);
				 request.setAttribute("trailer", trailer);
				 request.setAttribute("price", price);	 
				 request.setAttribute("stars", stars);	 
				 request.getRequestDispatcher("/SingleMovie.jsp").forward(request, response);
				 

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
