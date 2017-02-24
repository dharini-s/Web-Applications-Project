package com.battleborn.webapps;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class DeleteItem extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {

	  HttpSession session = request.getSession(true);
	  System.out.println("test");
	  response.setContentType("text/html");
	  PrintWriter out = response.getWriter();
//	  String customeremail = "mcosley@gmail.com";
//	  String customerid = "973020";
	  String movieid = "";

	  try {
		  
		  if (session.getAttribute("customerID") != null && session.getAttribute("customerID") != "" ) {
			  String customerid = session.getAttribute("customerID").toString();
			  
			  movieid = request.getParameter("movie_id_cart"); // Defined in ShoppingCart.jsp

			  System.out.println(movieid);
    
			  String loginUser = "testuser";
			  String loginPasswd = "testpass";
			  String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

			  Class.forName("com.mysql.jdbc.Driver").newInstance();
			  Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
	
			  Statement statement = connection.createStatement();
		  
			  //Test if movie is in cart
			  String query = "Select * from shoppingcarts where movie_id = " + movieid + " and customer_id = " + customerid;
			  //System.out.println(query);
		  
			  ResultSet rs = statement.executeQuery(query);
    
			  if(rs.next()) { // If result is not empty, means the movie is in the cart of this customer.
				  //Update quantity.
				  rs.close();
				  query = "Delete from shoppingcarts where movie_id =  " + movieid;
				  System.out.println(query); //DEBUG
				  statement.executeUpdate(query);
				  rs.close();
				  statement.close();
				  connection.close();
				  ShoppingCart cart = new ShoppingCart();
				  cart.doPost(request, response);
				  String message="The cart is updated.";
				  request.setAttribute("message",message);
				  request.getRequestDispatcher("/ShoppingCart.jsp").forward(request, response);
			  } else {
				  //The result is empty.
				  System.out.println("The movie is not in the cart.");
			  }
			  statement.close();
			  connection.close();
		  } else {
			  String message="Please log in first to update your shopping cart.";
			  request.setAttribute("message",message);
			  request.getRequestDispatcher("/Login.jsp").forward(request,response);
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

