package com.parse.xmldata;

//import com.battleborn.webapps.*;
import java.sql.*;                              // Enable SQL processing
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import java.util.HashSet;

public class StarDetails 
{
	public HashSet<String> getStarDetails() throws SQLException
	{
		// Build a hash set to store star name currently existing in the moviedb
		HashSet<String> dbStarsHs = new HashSet<String>();
		
		try{
			Connection conn = MySQLConnect.getConn();
			String queryStars ="SELECT id, first_name, last_name FROM stars";	
			PreparedStatement selectStars = conn.prepareStatement(queryStars);
			ResultSet resultStars = selectStars.executeQuery(queryStars);
			
			while (resultStars.next()) 
			{
				String name = resultStars.getString(2).toUpperCase() + " " + resultStars.getString(3).toUpperCase();				
				dbStarsHs.add(name);			
			}
			
			for (String s : dbStarsHs) {
			    System.out.println(s);
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
		return dbStarsHs;
	}
	
	public HashMap<Integer, String> getStarIDMap() throws SQLException
	{
		// Build a hash set to store star name currently existing in the moviedb
		HashMap<Integer, String> dbStarsHm = new HashMap<Integer, String>();
		
		try{
			Connection conn = MySQLConnect.getConn();
			String queryStars ="SELECT id, first_name, last_name FROM stars";	
			PreparedStatement selectStars = conn.prepareStatement(queryStars);
			ResultSet resultStars = selectStars.executeQuery(queryStars);
			
			while (resultStars.next()) 
			{
				int starid = resultStars.getInt(1);
				String name = resultStars.getString(2).toUpperCase() + " " + resultStars.getString(3).toUpperCase();				
				dbStarsHm.put(starid, name);			
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
		return dbStarsHm;
	}
	
	
	public void insertStars(ArrayList<Star> stars) throws SQLException 
	{
		Connection conn;
		try 
		{
			conn = MySQLConnect.getConn();
			System.out.println("In insertStars of StarDetails");
			conn.setAutoCommit(false);
			String stmt = "INSERT INTO stars (first_name, last_name, dob) VALUES (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
		
			for (Star s : stars) 
			{
//				for(int i=0;i<100;i++)
//				{
					if(s.getLastName()!=null)
					{
						//System.out.println(s.getFirstName());
						ps.setString(1, s.getFirstName());
						ps.setString(2, s.getLastName());
						ps.setString(3, s.getdob());
						ps.addBatch();
					}
					else
					{
						System.out.println("The last name of the star was null");
					}
					ps.executeBatch();
					conn.commit();
//				}
			}
			ps.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
			MySQLConnect.closeConn();
		}
		catch (Exception e) {
			System.out.println("Error in inserting star data");
			e.printStackTrace();
		}
	}
	
	
	public void insertStarsInMovies(ArrayList<StarsInMovies> starsinmovies) throws SQLException 
	{
		Connection conn;
		try 
		{
			conn = MySQLConnect.getConn();
			System.out.println("In insertStarsInMovies of StarDetails");
			Statement statement = conn.createStatement();
			statement.execute("SET FOREIGN_KEY_CHECKS=0");
			
			conn.setAutoCommit(false);
			String stmt = "INSERT INTO stars_in_movies (star_id, movie_id) VALUES (?, ?)";
			PreparedStatement ps = conn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
		
			for (StarsInMovies s : starsinmovies) 
			{
				ps.setInt(1, s.getStarId());
				ps.setInt(2, s.getMovieId());
				ps.addBatch();
			}
			ps.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
			
			statement.execute("SET FOREIGN_KEY_CHECKS=1");
			statement.close();
			
			MySQLConnect.closeConn();
		}
		catch (Exception e) {
			System.out.println("Error in inserting stars in movies data");
			e.printStackTrace();
		}
	}
	
}	

