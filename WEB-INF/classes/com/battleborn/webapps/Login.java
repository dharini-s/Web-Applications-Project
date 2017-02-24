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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
	public static int flag;

	public String ReturnServletInfo() {
		return "Servlet connects to MySQL to verify the details of user.";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		PrintWriter out = response.getWriter();
		
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		System.out.println("gRecaptchaResponse=" + gRecaptchaResponse);
		// Verify CAPTCHA.
		boolean valid = VerifyUtils.verify(gRecaptchaResponse);
		if (!valid) {
		    //errorString = "Captcha invalid!";
		    out.println("<HTML>" +
				"<HEAD><TITLE>" +
				"MovieDB: Error" +
				"</TITLE></HEAD>\n<BODY>" +
				"<P>Recaptcha WRONG!!!! </P></BODY></HTML>");
		    return;
		}
		
		// boolean st = false;
		String loginUser = "testuser";
		String loginPasswd = "testpass";
		String loginUrl = "jdbc:mysql://localhost:3306/moviedb";
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		PreparedStatement stmt = null;
		// Connection conn = null;
		request.getParameter("message");

		response.setContentType("text/html");

		String id = "";
		String password = "";
		String customerID = "";
		// flag = -1;
		response.setContentType("text/html");
		System.out.println("In try block of genres");

		try {

			// Integer id=Integer.parseInt(request.getParameter("id"));
			id = request.getParameter("id");
			password = request.getParameter("password");

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
			String query = "select * from customers where email = ? and password = ? ;";

			stmt = conn.prepareStatement(query);
			stmt.setString(1, id);
			stmt.setString(2, password);

			HttpSession session = request.getSession(true);
			session.getAttribute("flag");

			rs = stmt.executeQuery();
			flag = rs.next() ? 1 : 0;

			session.setAttribute("flag", flag);

			if (flag == 1) {

				customerID = rs.getString("id");
				
				session.setAttribute("customerID", customerID);

				query = "select name from genres;";
				ArrayList<String> genres = new ArrayList<String>();

				rs = stmt.executeQuery(query);

				while (rs.next()) {
					genres.add(rs.getString(1));///// index is 1??
					System.out.println(rs.getString(1));
				}

				request.setAttribute("genres", genres);
				request.getRequestDispatcher("/MainPage.jsp").forward(request, response);

				// session.setAttribute("flag",flag);
				System.out.println("Logged in successfully");
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				out.close();

			} else {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				String message = "";
				System.out.println("Log in unsuccessful!");
				if (session.getAttribute(customerID) == "" || session.getAttribute(customerID) == null)
					message = "Please enter a valid username/password combination.";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/Login.jsp").forward(request, response);

				if (conn != null)
					conn.close();
				out.close();
			}
		} catch (SQLException ex) {
			while (ex != null) {
				System.out.println("SQL Exception:  " + ex.getMessage());
				ex = ex.getNextException();
			} // end while
		} // end catch SQLException

		catch (java.lang.Exception ex) {
			out.println("<HTML>" + "<HEAD><TITLE>" + "MovieDB: Error" + "</TITLE></HEAD>\n<BODY>"
					+ "<P>SQL error in doPost: " + ex.getMessage() + "</P></BODY></HTML>");
			return;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
