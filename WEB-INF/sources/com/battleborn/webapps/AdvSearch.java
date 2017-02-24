package com.battleborn.webapps;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdvSearch extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			response.setContentType("text/html");
			String title = "";
			String year="";
			String director = "";
			String fName = "";
			String lName = "";
			String genre = "";
			String sortOrder = "";
			
			int page = 1;
			int recordsPerPage = 5;
			
			if(request.getParameter("page") != null)
				page = Integer.parseInt(request.getParameter("page"));
			if(request.getParameter("recordsPerPage")!=null)
				recordsPerPage=Integer.parseInt(request.getParameter("recordsPerPage"));
			
			sortOrder = request.getParameter("sortOrder");
			System.out.println("Page number: "+page);
			if (sortOrder == null)
			{
				sortOrder = "";
			}
			System.out.println("Page number: "+page);
			System.out.println("Sort order: "+sortOrder);
			title = request.getParameter("title");
			if(title==null)
				System.out.println("title is null");
			else if(title == "")
				System.out.println("title is empty string");
				
			year = request.getParameter("year");
			fName = request.getParameter("fName");
			lName = request.getParameter("lName");
			director = request.getParameter("director");
			genre = request.getParameter("genre");
			sortOrder = request.getParameter("sortOrder");
			
			System.out.println("Genre in adv search" +genre);
			
			List<Movie> queryresults = new ArrayList<Movie>(); 
			ArrayList<ArrayList<String>> genres = new ArrayList<ArrayList<String>>(); 
			ArrayList<ArrayList<Star>> stars = new ArrayList<ArrayList<Star>>(); 
			System.out.println("search1");

			try {
				System.out.println("In try of mainpage");
				QueryInformation qi = new QueryInformation();
				queryresults = qi.queryInformation(title, year, director, fName, lName, genre,(page-1)*recordsPerPage,recordsPerPage,sortOrder);
				genres=qi.getGenres(queryresults);
				stars =qi.getStars(queryresults);
				int noOfRecords = qi.getnoOfRecords();
				System.out.println("No. of recs"+noOfRecords);
				int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
				
				System.out.println("In AdvSearch");
				
				request.setAttribute("movielist", queryresults);
				request.setAttribute("noOfPages", noOfPages);
				request.setAttribute("currentPage", page);
				request.setAttribute("fName", fName);
				request.setAttribute("lName", lName);
				request.setAttribute("year", year);
				request.setAttribute("director", director);
				request.setAttribute("title", title);
				request.setAttribute("genre",genre);
				request.setAttribute("genres", genres);
				request.setAttribute("stars", stars);
				request.setAttribute("sortOrder", sortOrder);
				request.setAttribute("recordsPerPage", recordsPerPage);

				RequestDispatcher view = request.getRequestDispatcher("/AdvSearchResults.jsp");
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
