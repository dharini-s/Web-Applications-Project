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

public class EmployeeLogin extends HttpServlet {

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

		String password = "";
		String employeeEmail = "";
		response.setContentType("text/html");

		try {

			// Integer id=Integer.parseInt(request.getParameter("id"));
			employeeEmail = request.getParameter("email");
			password = request.getParameter("password");

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
			String query = "select * from employees where email = ? and password = ? ;";

			stmt = conn.prepareStatement(query);
			stmt.setString(1, employeeEmail);
			stmt.setString(2, password);

			HttpSession session = request.getSession(true);

			rs = stmt.executeQuery();

			if (rs.next()) {

				session.setAttribute("employeeEmail", employeeEmail);

				request.getRequestDispatcher("/Dashboard.jsp").forward(request, response);

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
				if (session.getAttribute(employeeEmail) == "" || session.getAttribute(employeeEmail) == null)
					message = "Please enter a valid username/password combination.";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/EmployeeLogin.jsp").forward(request, response);

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
