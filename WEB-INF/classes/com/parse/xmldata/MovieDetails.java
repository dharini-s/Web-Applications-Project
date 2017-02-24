package com.parse.xmldata;

//import com.battleborn.webapps.*;
import java.sql.*;                              // Enable SQL processing
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class MovieDetails 
{
	int count = 0;
	public HashSet<String> getMovieDetails() throws SQLException
	{
		HashSet<String> set = new HashSet<String>();
		//MySQLConnect conn = new MySQLConnect();
		try{
			Connection conn = MySQLConnect.getConn();
			String queryMovies ="SELECT title FROM movies";	
			PreparedStatement selectMovies = conn.prepareStatement(queryMovies);
			ResultSet resultMovies = selectMovies.executeQuery(queryMovies);
			//System.out.println(queryStars);
			
			while (resultMovies.next()) 
			{
				
				String title = resultMovies.getString(1);
				set.add(title.toUpperCase());
				
			}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		try 
		{
			MySQLConnect.closeConn();
		} 
		catch (Exception e) 
		{
			System.out.println("An error occurred in querying movie details!");
			e.printStackTrace();
		}
		return set;
	}

	
	public HashMap<Integer, String> getMovieIDMap() throws SQLException
	{
		// Build a hash set to store star name currently existing in the moviedb
		HashMap<Integer, String> dbMovieHm = new HashMap<Integer, String>();
		
		try{
			Connection conn = MySQLConnect.getConn();
			String queryMovies ="SELECT id, title FROM movies";	
			PreparedStatement selectMovies = conn.prepareStatement(queryMovies);
			ResultSet resultMovies = selectMovies.executeQuery(queryMovies);
			
			while (resultMovies.next()) 
			{
				int movieid = resultMovies.getInt(1);
				String title = resultMovies.getString(2).toUpperCase();				
				dbMovieHm.put(movieid, title);			
			}
			
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		try 
		{
			MySQLConnect.closeConn();
		} 
		catch (Exception e) 
		{
			System.out.println("An error occurred in querying star details!");
			e.printStackTrace();
		}
		return dbMovieHm;
	}
	
	
	public void insertMovies(ArrayList<Movie> movies) throws SQLException 
	{
		Connection conn;
		try 
		{
			conn = MySQLConnect.getConn();

			conn.setAutoCommit(false);
			String stmt = "INSERT INTO movies (title,year,director) values (?, ?, ?);";
			PreparedStatement ps = conn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
			System.out.println("ArrayList size is: "+movies.size());
			for (Movie m : movies) 
			{
			
					if(m.getTitle()!=null)
					{
						ps.setString(1, m.getTitle());
						ps.setInt(2, m.getYear());
						ps.setString(3, m.getDirector());
						ps.addBatch();
						count++;
						
					}
					else
					{
						System.out.println("Title of the movie was null");
					}
					
			}
			ps.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
			System.out.println("In MovieDetails insertMovies. Count is: " + count);
			
			MySQLConnect.closeConn();
		}
		catch (Exception e) {
			System.out.println("Error in inserting movie data");
			e.printStackTrace();
		}
	}
	public void insertGenres(ArrayList<String> genres) throws SQLException 
	{
		Connection conn;
		try 
		{
			conn = MySQLConnect.getConn();

			conn.setAutoCommit(false);
			String stmt = "INSERT INTO genres (name) SELECT * FROM (SELECT ?) AS tmp WHERE NOT EXISTS ( SELECT name FROM genres WHERE name = ? ) LIMIT 1";
			PreparedStatement ps = conn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
			for (String g : genres) 
			{
				if(g.length()>30)
				{
					String newG = g.substring(0,30);
					ps.setString(1, newG);
					ps.setString(2, newG);
				}
				else
				{
					ps.setString(1, g);
					ps.setString(2, g);
				}
				ps.addBatch();

			}
			ps.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
			MySQLConnect.closeConn();
		}
		catch (Exception e) {
			System.out.println("Error in inserting movie data");
			e.printStackTrace();
		}
	}

}
