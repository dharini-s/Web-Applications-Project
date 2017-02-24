<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.battleborn.webapps.*"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="java.sql.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.http.*"%>
<%@page import="java.util.*"%>
<%@page import="com.battleborn.webapps.Table"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>
	<meta charset="UTF-8">
    <title>Fabflix Dashboard</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	

    <!-- Custom CSS -->
    <link href="/Fabflix/css/sb-admin.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="/Fabflix/css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/Fabflix/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
	<style type="text/css">
		th, td {
    		padding: 2px;
    		font-size: 100%;
		}
		hr {
    		border: 0;
    		height: 1px;
/*     		background-image: linear-gradient(to right, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.75), rgba(0, 0, 0, 0)); */
/* 			Comment this background-image out to make hr hidden. */
		}
		body {
/*         	background-image: url("https://www.planwallpaper.com/static/images/desktop-background14.jpg");
 */         background-color: white;
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
  			margin: 50px 0px 0px 220px;
  			width:1000px;
 			/* background-color: #eeeeee; */
  			/* border: 1px solid white; */
  			opacity: 0.75;
  			filter: alpha(opacity=60); /* For IE8 and earlier */
		}
		 
 		.form-control {
    		width:1000px;
		}
  			 
	</style>
</head>
<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/Fabflix/Dashboard.jsp">Dashboard</a>
            </div>
            
            <!-- Top Menu Items -->
           <ul class="nav navbar-right top-nav">
                 <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Admin<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="/Fabflix/EmployeeLogin.jsp"><i class="fa fa-fw fa-user"></i> Sign in</a>
                        </li>
                        <li>
                            <a href="/Fabflix/servlet/EmployeeLogout"><i class="fa fa-fw fa-power-off"></i> Sign Out</a>
                        </li>
                    </ul>
                </li>
            </ul>
            
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li class="active">
                        <a href="/Fabflix/InsertStar.jsp"><i class="fa fa-fw fa-dashboard"></i> Insert Star</a>
                    </li>
                    <li class="active">
                        <a href="/Fabflix/InsertMovie.jsp"><i class="fa fa-fw fa-dashboard"></i> Insert Movie</a>
                    </li>
                    <li>
                        <a href="/Fabflix/Metadatda.jsp"><i class="fa fa-fw fa-bar-chart-o"></i> Metadata</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>

    </div>
    <!-- /#wrapper -->
    
	
	
<div class="transbox"> 

<div id="page-wrapper">

	<div class="container-fluid">

	<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Metadata</h1>
			</div>
		</div>
		
<% 
	HttpSession session = request.getSession(true);
    try {
            			  
		if (session.getAttribute("employeeEmail") != null && session.getAttribute("employeeEmail") != "" ) {

			String loginUser = "testuser";
			String loginPasswd = "testpass";
			String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
            		
			Statement statement = connection.createStatement();
            				  
			String query = "select table_name, table_type from information_schema.tables where table_schema='moviedb' order by table_name";
			System.out.println(query); //DEBUG

			ResultSet rs = statement.executeQuery(query);

			ArrayList<Table> table = new ArrayList<Table>();
%>			 
        	<div class="row">
        	<div class="col-lg-6">
            	<h2>tables</h2>
            	<div class="table-responsive">
                	<table class="table table-bordered table-hover table-striped">
                    	<thead>
                        	<tr>
                            	<th>table_name</th>
                            	<th>table_type</th>
                       	 	</tr>
                    	</thead>
                    	<tbody>
<%                    	
			while (rs.next()) {
				String tableName = rs.getString("table_name");
				String tableType = rs.getString("table_type");
				Table tablesindb = new Table();
				tablesindb.setName(tableName);
				tablesindb.setType(tableType);
				table.add(tablesindb);
				
%>
                             <tr>
                                 <td><%=rs.getString(1)%></td>
                                 <td><%=rs.getString(2)%></td>
                             </tr>
<%
			}
%>
                        	</tbody>
                     	</table>
                 	</div>
              	</div>
          	</div>
<%
			
			rs.close();
			
			if (table != null) {
				for (int i = 0; i< table.size(); i++) {
%>
                <!-- /.row -->

                	<div class="row">
                    	<div class="col-lg-6">
                        	<h2><%=table.get(i).getName()%></h2>
                        	<div class="table-responsive">
                            	<table class="table table-bordered table-hover table-striped">
                                	<thead>
                                    	<tr>
                                        	<th>column_name</th>
                                        	<th>data_type</th>
                                        	<th>column_key</th>
                                   	 	</tr>
                                	</thead>
                                	<tbody>
<%
					query = "select column_name, data_type, column_key from information_schema.columns where table_schema='moviedb' and table_name ='" + table.get(i).getName() +"'";
					rs = statement.executeQuery(query);
					while (rs.next()) {		
%>
                                    	<tr>
                                        	<td><%=rs.getString(1)%></td>
                                        	<td><%=rs.getString(2)%></td>
                                        	<td><%=rs.getString(3)%></td>
                                    	</tr>
<%
					}
%>
                                	</tbody>
                            	</table>
                        	</div>
                   		</div>
                	</div>
                	<!-- /.row -->
<%			
					}
				} else {
					System.out.println("table is empty.");
				}
		  	} else {
			  String message="Please log in first to view the metadata.";
			  request.setAttribute("message",message);
			  request.getRequestDispatcher("/EmployeeLogin.jsp").forward(request,response);
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
%>


</div>
</div>
</body>
</html>

