package com.parse.xmldata;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;

public class Main 
{
	public static void main(String args[]) throws SQLException
	{
		/*call the xml parsers,
		 * what about the objects?
		 * check and push to DB
		 * 
		 */																																																																																																																																																		
//		String movies = "mains243.xml";
//		String casts = "casts124.xml";
		Scanner scan = new Scanner(System.in);
		
//		String actors = "actors63.xml";
		String movies = "";
		String casts = "";		
		String actors = "";
		
		System.out.println("Please enter the name of the main xml file with full path to parse: ");
		movies = scan.nextLine();
		System.out.println("Please enter the name of the actors xml file with full path to parse: ");
		actors = scan.nextLine();
		System.out.println("Please enter the name of the casts xml file with full path to parse: ");
		casts = scan.nextLine();
		
		
		ParseMovies parseMovies = new ParseMovies();
		parseMovies.parseXmlFile(movies);

		ParseActors parseActors = new ParseActors();
		parseActors.parseXmlFile(actors);
		
		ParseCasts parseCasts = new ParseCasts();
		parseCasts.parseXmlFile(casts);
		
		

	}

}
