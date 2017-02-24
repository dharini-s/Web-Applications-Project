package com.battleborn.webapps;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.Date;
//import java.time.*;
//import java.time.format.DateTimeFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.*;
import com.battleborn.webapps.CartItem;


public class CheckOut extends HttpServlet {
	
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {

	  response.setContentType("text/html");
	  PrintWriter out = response.getWriter();
	  
	  HttpSession session = request.getSession(true);

//	  String customerid = "973020";
	  String fName = "";
	  String lName = "";
	  String cardid = "";
	  String expiration = "";
	  String message = "";
    
	  try{
		  if (session.getAttribute("customerID") != null && session.getAttribute("customerID") != "" ) {
			  String customerid = session.getAttribute("customerID").toString();
			  	
			  fName = request.getParameter("firstName");
			  lName = request.getParameter("lastName");
			  cardid = request.getParameter("cardid");
			  expiration = request.getParameter("expiration");
    
			  String loginUser = "testuser";
			  String loginPasswd = "testpass";
			  String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

			  Class.forName("com.mysql.jdbc.Driver").newInstance();
			  Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
    
			  String queryCart = "Select * from shoppingcarts where customer_id =" + customerid;
			  System.out.println(queryCart);
		  
			  Statement statement = connection.createStatement();
			  ResultSet rs = statement.executeQuery(queryCart);

        
			  if (rs.next()) {
			  
				  String queryCard = "Select * from creditcards where id = '" + cardid + "' and first_name = '" + fName + "' and last_name = '" + lName + "' and expiration = '" + expiration + "'";
				  ResultSet rsCard = statement.executeQuery(queryCard);
				  System.out.println(queryCard);
			  
				  // If the customer credit card info is correct, add the cart contents to sales. And go to success page.
				  if (rsCard.next()) {
					  int quantity;
					  int movieid;
					  int i;
					  
//				      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//				  	  LocalDate localDate = LocalDate.now();
//				  	  System.out.println(dtf.format(localDate));
				  
					  DateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
					  String localDate = dtf.format(new Date());
					  System.out.println(localDate);
				  
					  String queryCarts = "Select * from shoppingcarts where customer_id =" + customerid;
					  Statement statCarts = connection.createStatement();
					  ResultSet rsCarts = statCarts.executeQuery(queryCarts);
					  System.out.println(queryCarts);
			  			  
					  while(rsCarts.next()) {
						  quantity = rsCarts.getInt("quantity");
						  movieid =  rsCarts.getInt("movie_id");
						  System.out.println(quantity);
						  System.out.println(movieid);
						  for (i = 0; i < quantity; i++) {
							  String queryInsert = "Insert into sales (customer_id, movie_id, sale_date) values(" + customerid + "," + movieid + ", '" + localDate + "')";
							  System.out.println(queryInsert);
							  statement.executeUpdate(queryInsert);
							  System.out.println("Insert Done");
						  }
					  }
				  
					  rsCard.close();
					  rsCarts.close();
					  statCarts.close();
					  statement.close();
					  connection.close();
			  
					  message="Checkout successfully. Thank you for shopping with us!";
					  System.out.println(message);
					  request.setAttribute("message",message);
					  request.getRequestDispatcher("/CheckOutResult.jsp").forward(request, response);

					  System.out.println("After redirect");

				
				  } else { // The customer credit card info is incorrect.
					  rsCard.close();
					  statement.close();
					  connection.close();
			  
//					  message="The input information is incorrect. Please try again.";
//					  request.setAttribute("message",message);
					  request.getRequestDispatcher("/CheckOut.jsp").forward(request, response);
				  }

			  
			  } else {
				  rs.close();
				  statement.close();
				  connection.close();
			  
				  System.out.println("Cart is empty.");
				  message="Your shopping cart is empty. Please add items to your shopping cart before check out.";
				  request.setAttribute("message",message);
				  request.getRequestDispatcher("/CheckOutResult.jsp").forward(request, response);
			  }

		  } else {
			  message="Please log in to check out.";
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

