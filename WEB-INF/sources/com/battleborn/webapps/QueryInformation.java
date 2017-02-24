package com.battleborn.webapps;


import java.sql.*;                              // Enable SQL processing
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import com.battleborn.webapps.Movie;


public class QueryInformation {
    /**
     * @param 
     * @return
     */
		int noOfRecords;
		public int getnoOfRecords()
		{
			return noOfRecords;
		}
		public ArrayList<ArrayList<Star>> getStars(List<Movie> m) throws Exception
		{
			String loginUser = "testuser";
        String loginPasswd = "testpass";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";
        ArrayList<ArrayList<Star>> starsPerMovie = new ArrayList<ArrayList<Star>>();
        try{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        
        Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
        
        if (!m.isEmpty()||m!=null) 
        {
			if (m.size() > 0) 
			{
				
				for (Movie movie : m) 
				{
					ArrayList<Star> stars = new ArrayList<Star>();
					int movieID = movie.getID();
					
					System.out.println("movie ID in genre function" + movieID);
					
					String queryStars ="SELECT * FROM stars where id in ( SELECT star_id from stars_in_movies where movie_id = " + movieID + ")";	
					Statement selectStars = connection.createStatement();
					ResultSet resultStars = selectStars.executeQuery(queryStars);
					System.out.println(queryStars);
	
					while (resultStars.next()) 
					{
						Star star = new Star();
						int starId = resultStars.getInt("id");
						String fName = resultStars.getString(2);
						String lName = resultStars.getString(3);
						star.setID(starId);
						star.setFirstName(fName);
						star.setLastName(lName);
						stars.add(star);
					}
					starsPerMovie.add(stars);	
				}
				
			}
			
        	}
        }
        catch(SQLException e)
        {
        	//
        }
			return starsPerMovie;
}
		public ArrayList<ArrayList<String>> getGenres(List<Movie> m) throws Exception
		{
			String loginUser = "testuser";
	        String loginPasswd = "testpass";
	        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";
	        ArrayList<ArrayList<String>> genresPerMovie = new ArrayList<ArrayList<String>>();
	        try{
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        
	        Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
	        
	        if (!m.isEmpty()||m!=null) 
	        {
				if (m.size() > 0) 
				{
					
					for (Movie movie : m) 
					{
						ArrayList<String> genres = new ArrayList<String>();
						int movieID = movie.getID();
						String queryGenre ="SELECT name FROM genres where id in ( SELECT genre_id from genres_in_movies where movie_id = " + movieID + ")";	
						Statement selectGenre = connection.createStatement();
						ResultSet resultGenre = selectGenre.executeQuery(queryGenre);
						System.out.println(queryGenre);
						
						while (resultGenre.next()) 
						{
							genres.add(resultGenre.getString(1));
							System.out.println(resultGenre.getString(1));
						}
						genresPerMovie.add(genres);
					}
					
				}
				
	        }
	        }
	        catch(SQLException e)
	        {
	        	/////
	        }
	        return genresPerMovie;
}
    public List<Movie> queryInformation(String title, String year, String director, String starFirstName, String starLastName, String genre, int offset, int noOfRecords, String sortOrder) throws Exception 
    {
    	List<Movie>  details = new ArrayList<Movie>();
        String loginUser = "testuser";
        String loginPasswd = "testpass";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        
        Connection connection = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);
        
        String orderClause = "";
        
        System.out.println("Sort order: "+sortOrder);
        
        System.out.println("year is:"+year);
        try
        {
        	if(!genre.isEmpty())
        	{
        		
        		if (sortOrder == null) {
        		orderClause = " title";
        		}
        		else if (sortOrder.equals("YearAsc")) {
        			orderClause = " year";
        		} 
        		else if (sortOrder.equals("YearDesc")) {
        			orderClause = " year desc";
        		}
        		else if (sortOrder.equals("TitleAsc")) {
        			orderClause = " title";
        		} 
        		else if (sortOrder.equals("TitleDesc")) {
        			orderClause = " title desc ";
        			
        		} 
    		
            String queryMoviePerGenres = "Select * from movies inner join genres_in_movies on movies.id = genres_in_movies.movie_id inner join genres on genres_in_movies.genre_id = genres.id where genres.name = '" + genre + "' order by "+orderClause+" limit "+offset+ ", " + noOfRecords;
            
            System.out.println(queryMoviePerGenres);
            System.out.println();

            Statement selectMoviePerGenres = connection.createStatement();
            ResultSet resultMoviePerGenres = selectMoviePerGenres.executeQuery(queryMoviePerGenres);
            
            // print table's contents, field by field
            while (resultMoviePerGenres.next()) {
            	
            	int id1 = resultMoviePerGenres.getInt(1);
				String title1 = resultMoviePerGenres.getString(2);
				int year1 = resultMoviePerGenres.getInt(3);
				String director1 = resultMoviePerGenres.getString(4);
				String bannerUrl = resultMoviePerGenres.getString(5);
				String trailerUrl = resultMoviePerGenres.getString(6);
                Movie movie = new Movie();
				movie.setID(id1);
				movie.setTitle(title1);
				movie.setDirector(director1);
				movie.setYear(year1);
				movie.setBannerUrl(bannerUrl);
				movie.setTrailerUrl(trailerUrl);
				details.add(movie);

            } 
            resultMoviePerGenres = selectMoviePerGenres.executeQuery("SELECT FOUND_ROWS()");
            if(resultMoviePerGenres.next())
				this.noOfRecords = resultMoviePerGenres.getInt(1);

            if (selectMoviePerGenres != null) {
                selectMoviePerGenres.close();
            }

            if (resultMoviePerGenres != null) {
                resultMoviePerGenres.close();
            }
            return details;
        } 
        else {
                
            System.out.println("No input genre.");
            System.out.println();
        }

        Boolean inputFlag = (title.isEmpty() && year.isEmpty() && director.isEmpty() && starFirstName.isEmpty() && starLastName.isEmpty());
        System.out.println("After genre check");//flag must be false
        
        if(!inputFlag)
        		System.out.println("input flag is false");
        /* 1. Get movies from the title, matching substring */
        if (!inputFlag) {
        	if (sortOrder == null || sortOrder == "") {
        		orderClause = " title";
        		}
        		else if (sortOrder.equals("YearAsc")) {
        			orderClause = " year";
        		} 
        		else if (sortOrder.equals("YearDesc")) {
        			orderClause = " year desc";
        		}
        		else if (sortOrder.equals("TitleAsc")) {
        			orderClause = " title";
        		} 
        		else if (sortOrder.equals("TitleDesc")) {
        			orderClause = " title desc";
        			
        		} 
            // Define statement strings for each input.
            String stmtHead = "Select * from movies ";
            String stmtTitle = "title like '" + title + "%'";
            String stmtYear = "year = " + year;
            String stmtDirector = "director like '" + director + "%'";

            String stmtInnerJoin = "inner join stars_in_movies on movies.id = stars_in_movies.movie_id inner join stars on stars_in_movies.star_id = stars.id ";

            String stmtStarFirstName = "stars.first_name like '" + starFirstName + "%'";

            String stmtStarLastName = "stars.last_name like '" + starLastName + "%'";

            // Boolean andFlag1 = (title != null && year.isEmpty() && director.isEmpty() && starFirstName.isEmpty() && starLastName.isEmpty());
            // Boolean andFlag2 = (year != null && director.isEmpty() && starFirstName.isEmpty() && starLastName.isEmpty());

            String queryMovie = stmtHead + ((starFirstName.isEmpty() && starLastName.isEmpty()) ? "" : stmtInnerJoin)
                                    + "where " + (title.isEmpty() ? "" : stmtTitle)
                                    + (year.isEmpty() ? "" : (title.isEmpty() ? stmtYear : (" and " + stmtYear)))
                                    + (director.isEmpty() ? "" : ((title.isEmpty() && year.isEmpty()) ? stmtDirector : (" and " + stmtDirector)))
                                    + (starFirstName.isEmpty() ? "" : ((title.isEmpty() && year.isEmpty() && director.isEmpty()) ? stmtStarFirstName : (" and " + stmtStarFirstName)))
                                    + (starLastName.isEmpty() ? "" : ((title.isEmpty() && year.isEmpty() && director.isEmpty() && starFirstName.isEmpty()) ? stmtStarLastName : (" and " + stmtStarLastName)))+" order by "+orderClause+" limit "+offset+ ", " + noOfRecords;

            System.out.println("Query Movie in QI:"+queryMovie);
            System.out.println();

            Statement selectMovie = connection.createStatement();
            ResultSet resultMovie = selectMovie.executeQuery(queryMovie);

            // print table's contents, field by field
            while (resultMovie.next()) {
            	
            	int id1 = resultMovie.getInt(1);
				String title1 = resultMovie.getString(2);
				int year1 = resultMovie.getInt(3);
				String director1 = resultMovie.getString(4);
				String bannerUrl = resultMovie.getString(5);
				String trailerUrl = resultMovie.getString(6);
                Movie movie = new Movie();
				movie.setID(id1);
				System.out.println(movie.getID());
				movie.setTitle(title1);
				System.out.println(movie.getTitle());
				movie.setDirector(director1);
				movie.setYear(year1);
				movie.setBannerUrl(bannerUrl);
				movie.setTrailerUrl(trailerUrl);
				details.add(movie);
                

            } 
            resultMovie = selectMovie.executeQuery("SELECT FOUND_ROWS()");
            if(resultMovie.next())
				this.noOfRecords = resultMovie.getInt(1);
            
            if (selectMovie != null) {
                selectMovie.close();
            }

            if (resultMovie != null) {
                resultMovie.close();
            }
            System.out.println("Returned something");
            return details;
            
        } else {
                
            System.out.println("No input.");
            System.out.println();
        }


        if (connection != null) {
            connection.close();
        }
        System.out.println("Finished queryInfo");
        //details.add("No results");
        
        }
        catch(Exception e) {
        	System.out.println(e.getMessage());
        }
        return details;//=()? : ;

    }
}
