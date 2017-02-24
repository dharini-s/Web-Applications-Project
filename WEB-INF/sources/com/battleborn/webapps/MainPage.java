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

public class MainPage extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			response.setContentType("text/html");

			HttpSession session = request.getSession();
			
			
			int page = 1;
			int recordsPerPage = 5;
			
			String search;
			search = request.getParameter("search");
			if(request.getParameter("recordsPerPage")!=null)
				recordsPerPage=Integer.parseInt(request.getParameter("recordsPerPage"));
			
			String sortOrder = request.getParameter("sortOrder");
			//		
			if (sortOrder == null)
			{
				sortOrder = "";
			}
			System.out.println(search);
			
			if(request.getParameter("page") != null)
				page = Integer.parseInt(request.getParameter("page"));
			System.out.println("Page number: "+page);
			System.out.println("Sort order: "+sortOrder);
			
			List<Movie> queryresults = new ArrayList<Movie>(); 
			ArrayList<ArrayList<String>> genres = new ArrayList<ArrayList<String>>(); 
			ArrayList<ArrayList<Star>> stars = new ArrayList<ArrayList<Star>>(); 
			try {
				
				System.out.println("In try of mainpage");
				QueryInformation qi = new QueryInformation();
				
				System.out.println("Called constructor of query info");
				queryresults = qi.queryInformation(search, "", "", "", "", "", (page-1)*recordsPerPage,recordsPerPage, sortOrder);
				genres=qi.getGenres(queryresults);
				stars =qi.getStars(queryresults);
				
				//System.out.println(qi.get(0),qi.get(1),qi.get(2),qi.get(3),qi.get(4));
				int noOfRecords = qi.getnoOfRecords();
				System.out.println("No. of recs"+noOfRecords);
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				
				System.out.println("After calling qi()");
				//System.out.println(queryresults.get(1)+"this is the title");
				request.setAttribute("movielist", queryresults);
				request.setAttribute("noOfPages", noOfPages);
				request.setAttribute("currentPage", page);
				//request.setAttribute("searchString", search);
				request.setAttribute("sortOrder", sortOrder);
				request.setAttribute("search", search);
				request.setAttribute("genres", genres);
				request.setAttribute("stars", stars);
				request.setAttribute("recordsPerPage", recordsPerPage);
				System.out.println("page before request:"+page);
				
				RequestDispatcher view = request.getRequestDispatcher("/SearchResults.jsp");
				view.forward(request, response);
					
				
				
			
			} catch (SQLException ex) {
				///
			}
		} catch (java.lang.Exception ex) {
			out.println("<HTML>" + "<HEAD><TITLE>" + "MovieDB: Error" + "</TITLE></HEAD>\n<BODY>"
					+ "<P>SQL error in doGet: " + ex.getMessage() + "</P></BODY></HTML>");
			return;
		}
	}
}
