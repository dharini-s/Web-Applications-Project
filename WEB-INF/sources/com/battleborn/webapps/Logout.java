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

public class Logout extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		session.invalidate();
		String message="You have logged out successfully.";
		request.setAttribute("message",message);
		//out.print("You are successfully logged out!");
		request.getRequestDispatcher("/Login.jsp").forward(request, response);

		out.close();
	}
}
