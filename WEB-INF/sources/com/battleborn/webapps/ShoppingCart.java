package com.battleborn.webapps;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import com.battleborn.webapps.CartItem;


public class ShoppingCart extends HttpServlet {
	
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {

	  response.setContentType("text/html");
	  PrintWriter out = response.getWriter();
	  
	  HttpSession session = request.getSession(true);
//	  String customerid = session.getAttribute("customerID").toString();    


//	  String customerid = "973020";
    
	  try{
		  if (session.getAttribute("customerID") != null && session.getAttribute("customerID") != "" ) {
			  String customerid = session.getAttribute("customerID").toString();    
     
			  String loginUser = "testuser";
			  String loginPasswd = "testpass";
			  String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

			  Class.forName("com.mysql.jdbc.Driver").newInstance();
			  Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
	
			  Statement statement = connection.createStatement();
    
			  String query = "Select movies.id, movies.title, shoppingcarts.quantity FROM movies, shoppingcarts where movies.id = shoppingcarts.movie_id and shoppingcarts.customer_id = " + customerid; 
			  System.out.println(query);
    
			  ResultSet rs = statement.executeQuery(query);
    
			  ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
    
			  while (rs.next()) {
				  String title = rs.getString("title");
				  int quantity = rs.getInt("quantity");
				  int itemid =  rs.getInt("id");
				  CartItem movieincart = new CartItem();
				  movieincart.setTitle(title);
				  movieincart.setQuantity(quantity);
				  movieincart.setID(itemid);
				  cartItems.add(movieincart);
			  } 
			  statement.close();
			  connection.close();

		  //Test
			  for (int i=0; i < cartItems.size(); i++) {
				  System.out.println(cartItems.get(i).getTitle());
			  }
		  
			  request.setAttribute("cartItems", cartItems); // Create session named "cartItems" and bound object cartItems to session movies.
			  request.getRequestDispatcher("/ShoppingCart.jsp").forward(request,response);
	
		  } else {
			  String message="Please log in first to view your shopping cart.";
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

