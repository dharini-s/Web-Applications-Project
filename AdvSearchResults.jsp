<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.battleborn.webapps.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Fabflix</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<style type="text/css">
		th, td {
    		padding: 2px;
    		font-size: 110%;
		}
		hr {
    		border: 0;
    		height: 1px;
    		background-image: linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0));
		}
		body {
        	background-image: url("https://www.planwallpaper.com/static/images/desktop-background14.jpg");
        	background-size: cover;
        	background-repeat:no-repeat;
        	padding:0;
  			margin:0;
  			
  			-moz-background-size: cover;
			-webkit-background-size: cover;
			background-size: cover;
			background-position: top center !important;
			background-repeat: no-repeat !important;
			background-attachment: fixed;
		}

		div.transbox {
  			margin: 180px 60px 180px 60px;
 			background-color: #eeeeee;
  			border: 1px solid white;
  			opacity: 0.75;
  			filter: alpha(opacity=60); /* For IE8 and earlier */
		}
		
		.dropdown-submenu {
    position: relative;
}

.dropdown-submenu .dropdown-menu {
    top: 0;
    left: 100%;
    margin-top: -1px;
}
  			
	</style>
</head>


<body>
<% if (session.getAttribute("customerID") == null|| session.getAttribute("customerID") == "" ) 
	{
    response.sendRedirect("/Fabflix/LoginError.jsp"); // Not logged in, redirect to login page.
}
%>

	<nav class="navbar navbar-inverse navbar-fixed-top">
  		<div class="container-fluid">
    		<div class="navbar-header">
      			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        			<span class="icon-bar"></span>
        			<span class="icon-bar"></span>
        			<span class="icon-bar"></span> 
      			</button>
      			<a class="navbar-brand" href="#"><b>Fabflix</b></a>
   			 </div>
    		<div class="collapse navbar-collapse" id="myNavbar">
      			<ul class="nav navbar-nav">
				<form action="/Fabflix/servlet/MainPage" method="get" class="navbar-form navbar-left">
      				<div class="input-group">
        				<input type="text" name="search" class="form-control" id="search" placeholder="Search">
        				<input type="hidden" name="sortOrder" value=""/>
				 		<input type="hidden" name="page" value="1"/> 
        				<div class="input-group-btn">
          					<button class="btn btn-default" type="submit">
            					<i class="glyphicon glyphicon-search"></i>
          					</button>
        				</div>
      				</div>
    			</form>
      			</ul>
      			
       			<ul id="multicol-menu" class="nav navbar-nav navbar-right">
      				<form action="/Fabflix/servlet/Home" method="get"></form>
      				<li class="active"><a href="/Fabflix/servlet/Home">Home</a></li>    				
 			 		<li><a href="/Fabflix/advancedSearch.jsp">Advanced Search</a></li>
        			<li><a href="/Fabflix/servlet/ShoppingCart"><span></span>My Cart</a></li>
        			<li><a href="/Fabflix/servlet/CheckOut"><span></span>Checkout</a></li>
        			
        			<li class="dropdown">
        				<a class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>Sign in<span class="caret"></span></a>
        				<ul class="dropdown-menu">
        					<li><a href="/Fabflix/Login.jsp">Sign in</a></li>
          					<li><a href="/Fabflix/servlet/Logout">Sign out</a></li>
        				</ul>
      				</li>
      			</ul>
      		
    		</div>
 	 	</div>
	</nav>
	

<div class="transbox">
<div class="container">

<h3 align="center">Advanced Search Results</h3>
	<%
		List<Movie> movies = (List<Movie>) request.getAttribute("movielist");
		int currentPage = (Integer)request.getAttribute("page");
		int noOfPages = (Integer)request.getAttribute("noOfPages");
		int recordsPerPage = (Integer) request.getAttribute("recordsPerPage");
		//String search = (String)request.getAttribute("searchStr");
		String sortOrder = (String) request.getAttribute("sortOrder");
		
		String fName = (String)request.getAttribute("fName");
		String title = (String)request.getAttribute("title");
		String director = (String)request.getAttribute("director");
		String year = (String)request.getAttribute("year");
		String lName = (String)request.getAttribute("lName");
		String genre = (String)request.getAttribute("genre");
		ArrayList<ArrayList<String>> genres = (ArrayList<ArrayList<String>>)request.getAttribute("genres"); 
		ArrayList<ArrayList<Star>> stars =( ArrayList<ArrayList<Star>>)request.getAttribute("stars");
		String titleSort = "";
		String yearSort = "";

		if (sortOrder == null || sortOrder == "") {
			titleSort = "TitleAsc";
			yearSort = "YearAsc";
		} else {
			if (sortOrder.equals("TitleAsc")) {
				titleSort = "TitleDesc";
				yearSort = "YearAsc";
			} else if (sortOrder.equals("TitleDesc")) {
				titleSort = "TitleAsc";
				yearSort = "YearAsc";
			} else if (sortOrder.equals("YearAsc")) {
				titleSort = "TitleAsc";
				yearSort = "YearDesc";
			} else {
				titleSort = "TitleAsc";
				yearSort = "YearAsc";
			}
		}
		
		
		if (!movies.isEmpty()) {
			if (movies.size() > 0) {
				int i=0;
				
%>
	<hr>
<%
				for (Movie movie : movies) {
%>

<div align="center">
<TABLE ALIGN="CENTER">
			<col width="200">
			<col width="200">
			<col width="300">
			<TR BGCOLOR="#FFAD00">
			
	<TR>
				<% 		   
				   int s = 6;
				   if (!stars.isEmpty() && stars!=null) {
					   s = stars.get(i).size() + s - 1;
				   }
				   if (!genres.isEmpty() && genres!=null) {
					   s = genres.get(i).size() + s - 1;
				   }
				%>

				<TD rowspan = "<%=s%>"><img src="<%=movie.getBannerUrl()%>" alt="<%=movie.getTitle()%>" height="140" width="95"></TD>
				<TD><b>Title:</b></TD>
				<TD style="padding-top:0px; padding-bottom:0px;"><a href="/Fabflix/servlet/SingleMovie?movieid=<%=movie.getID()%>"><b><%=movie.getTitle()%></b></a></TD>
				
			</TR>

			<TR>
				<TD><b>Year:</b></TD>
				<TD><b><%=movie.getYear()%></b></TD>
			</TR>

			<TR>
				<TD><b>Director:</b></TD>
				<TD><b><%=movie.getDirector()%></b></TD>
			</TR>

			<TR>
				<TD><b>Movie ID:</b></TD>
				<TD><b><%=movie.getID()%></b></TD>
			</TR>

			<TR>
				<% 
				   int gsize = 0;
				   if (!genres.isEmpty() && genres!=null) {
					   if(genres.get(i).size()> 0) {
					   		gsize = genres.get(i).size();
				%>	   
					   <TD rowspan = "<%=gsize%>" valign="top"><b>Genres:</b></TD>
					   <TD style="padding-bottom:0px;"><a href="/Fabflix/servlet/AdvSearch?title=&year=&fName=&lName=&director=&genre=<%=genres.get(i).get(0)%>&page=1"><b><%=genres.get(i).get(0)%></b></a></TD>
				<%   							
					   } else {
			    		%>
		    				   <TD><b>Genres:</b></TD>
							   <TD></TD>
		    			<%							   
					   }
					} else {
    			%>
    				   <TD><b>Genres:</b></TD>
					   <TD></TD>
    			<%		
    				}
	    		%>
			</TR>


				<% if (!genres.isEmpty() && genres!=null) {
						if (genres.get(i).size() > 1) {
					   		for(int k = 1; k < genres.get(i).size(); k++) {
				%>	   
								<TR>
					   			<TD style="padding-top:0px; padding-bottom:0px;"><a href="/Fabflix/servlet/AdvSearch?title=&year=&fName=&lName=&director=&genre=<%=genres.get(i).get(k)%>&page=1"><b><%=genres.get(i).get(k)%></b></a></TD>
					   			</TR>
				<%   							
    						}
						}
					}
	    		%>
	    		
			<TR>
				<% 
				   int size = 0;
				   if (!stars.isEmpty() && stars!=null) {
					   if(stars.get(i).size()> 0) {
					   		size = stars.get(i).size();
				%>	   
					   		<TD rowspan = "<%=size%>" valign="top"><b>Stars:</b></TD>
					   		<TD style="padding-bottom:0px;"><a href="/Fabflix/servlet/SingleStar?starid=<%=stars.get(i).get(0).getID()%>"><b><%=stars.get(i).get(0).getName()%></b></a></TD>
				<%   							
					   } else {
			    		%>
		    				   <TD><b>Stars:</b></TD>
							   <TD></TD>
		    			<%						   
					   }
					} else {
    			%>
    				   <TD><b>Stars:</b></TD>
					   <TD></TD>
    			<%		
    				}
	    		%>
			</TR>


				<% if (!stars.isEmpty() && stars!=null) {
						if (stars.get(i).size() > 1) {
					   		for(int j = 1; j < stars.get(i).size(); j++) {
				%>	   
								<TR>
					   			<TD style="padding-top:0px; padding-bottom:0px;"><a href="/Fabflix/servlet/SingleStar?starid=<%=stars.get(i).get(j).getID()%>"><b><%=stars.get(i).get(j).getName()%></b></a></TD>
					   			</TR>
				<%   							
    						}
						}
					}
	    		%>
	    		
	    	<TR>
				<TD>

					<form target="_self" action="/Fabflix/servlet/AddToCart" id = 'cartForm' method="POST">
						<input type="submit" id ="addtocart" class="btn btn-success" style="padding:4px;" value="Add to Cart" onclick="alert('Movie added to your cart successfully!')">
						<input type="hidden" name=movie_id_cart value=<%=movie.getID()%>>
					</form>

				</TD>
				<TD><b>Trailer:</b></TD>
				<TD><a href="<%=movie.getTrailerUrl()%>"><b>Click here</b></a><b> to watch the movie trailer</b></TD>
			</TR>
										
		</TABLE>
		<hr>
		
	<%i++;
						}
					
						}
						}
	else {%>
	<p>Search returned zero results.</p>
					<%}%>
					<div align="center">
					<c:if test="${currentPage gt 1}">
		<td><a href="/Fabflix/servlet/AdvSearch?title=${title}&year=${year}&fName=${fName}&lName=${lName}&director=${director}&genre=${genre}&sortOrder=${sortOrder}&recordsPerPage=${recordsPerPage}&page=${currentPage - 1}">
		<button>Previous</button>
		</a>
	</c:if>
					
			<table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
						<a href="/Fabflix/servlet/AdvSearch?title=${title}&year=${year}&fName=${fName}&lName=${lName}&director=${director}&genre=${genre}&sortOrder=${sortOrder}&recordsPerPage=${recordsPerPage}&page=${i}">${i}</a>>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
		
	
				<c:if test="${currentPage le noOfPages}">
		<td><a href="/Fabflix/servlet/AdvSearch?title=${title}&year=${year}&fName=${fName}&lName=${lName}&director=${director}&genre=${genre}&sortOrder=${sortOrder}&recordsPerPage=${recordsPerPage}&page=${currentPage + 1}">
		<button class="btn btn-info">Next</button>
		</a>
	</c:if>
	<br> Sort By: <a
			href="/Fabflix/servlet/AdvSearch?title=${title}&year=${year}&fName=${fName}&lName=${lName}&director=${director}&genre=${genre}&sortOrder=TitleAsc&recordsPerPage=${recordsPerPage}&page=${currentPage}">Title
			<img
			src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-arrow-up-b-128.png"
			class="order_arrow" width=20 />
		</a> | <a
			href="/Fabflix/servlet/AdvSearch?title=${title}&year=${year}&fName=${fName}&lName=${lName}&director=${director}&genre=${genre}&sortOrder=TitleDesc&recordsPerPage=${recordsPerPage}&page=${currentPage}">Title
			<img
			src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-arrow-down-b-128.png"
			class="order_arrow" width=20 />
		</a> | <a
			href="/Fabflix/servlet/AdvSearch?title=${title}&year=${year}&fName=${fName}&lName=${lName}&director=${director}&genre=${genre}&sortOrder=YearAsc&recordsPerPage=${recordsPerPage}&page=${currentPage}">Year
			<img
			src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-arrow-up-b-128.png"
			class="order_arrow" width=20 />
		</a> | <a
			href="/Fabflix/servlet/AdvSearch?title=${title}&year=${year}&fName=${fName}&lName=${lName}&director=${director}&genre=${genre}&sortOrder=YearDesc&recordsPerPage=${recordsPerPage}&page=${currentPage}">Year
			<img
			src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-arrow-down-b-128.png"
			class="order_arrow" width=20 />
		</a> | <br>
		<p>
			Results Per Page: <a
				href="/Fabflix/servlet/AdvSearch?title=${title}&year=${year}&fName=${fName}&lName=${lName}&director=${director}&genre=${genre}&sortOrder=TitleDesc&recordsPerPage=5&page=${currentPage}">5</a>
			<a
				href="/Fabflix/servlet/AdvSearch?title=${title}&year=${year}&fName=${fName}&lName=${lName}&director=${director}&genre=${genre}&sortOrder=TitleDesc&recordsPerPage=10&page=${currentPage}">10</a>
			<a
				href="/Fabflix/servlet/AdvSearch?title=${title}&year=${year}&fName=${fName}&lName=${lName}&director=${director}&genre=${genre}&sortOrder=TitleDesc&recordsPerPage=15&page=${currentPage}">15</a>
			<a
				href="/Fabflix/servlet/AdvSearch?title=${title}&year=${year}&fName=${fName}&lName=${lName}&director=${director}&genre=${genre}&sortOrder=TitleDesc&recordsPerPage=20&page=${currentPage}">20</a>

		</p>
	</div>
</div>
</div>
</body>
</html>
