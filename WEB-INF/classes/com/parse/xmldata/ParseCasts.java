package com.parse.xmldata;

//import com.battleborn.webapps.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class ParseCasts implements ParseData
{
	Document dom;
	ArrayList<StarsInMovies> starsinmovies = new ArrayList<StarsInMovies>(); //from  xml file
//	HashMap<String, Integer> stageNametoStarID = new HashMap<String, Integer>();
//	ArrayList<Star> starsFromDB = new ArrayList<Star>();//from MySQL
	
	
	int count = 0;

	public static Integer getKeyByValue (HashMap<Integer, String> map, String value)
	{
		for(Entry<Integer, String> entry: map.entrySet()){
			if (Objects.equals(value, entry.getValue()))
			{
				return entry.getKey();
			}
		}
		return null;
	}

	public void parseXmlFile(String file)
	{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			
			try {
				
				DocumentBuilder db = dbf.newDocumentBuilder();
				dom = db.parse(file);
//				System.out.println("After document has been parsed");
				this.parseDocument();
				

			}catch(ParserConfigurationException pce) {
				pce.printStackTrace();
			}catch(SAXException se) {
				se.printStackTrace();
			}catch(IOException ioe) {
				ioe.printStackTrace();
			}
	}
	
	public void parseDocument()
	{
		StarDetails starData = new StarDetails();
		
		HashMap<Integer, String> dbStarHm = new HashMap<Integer, String>();
				
		try{
			dbStarHm = starData.getStarIDMap();
		}		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		MovieDetails movieData = new MovieDetails();
		
		HashMap<Integer, String> dbMovieHm = new HashMap<Integer, String>();
				
		try{
			dbMovieHm = movieData.getMovieIDMap();
		}		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		Element docEle = dom.getDocumentElement();
		
		NodeList casts = docEle.getChildNodes(); //root element
		if (casts != null && casts.getLength()>0) 
		{

	        for (int i = 0; i < casts.getLength(); i++) 
	        {
	        	NodeList dirfilms = casts.item(i).getChildNodes();
	        	if (dirfilms != null && dirfilms.getLength()>0) 
	    		{

	    	        for (int j = 0; j < dirfilms.getLength(); j++) 
	    	        {
	    	        	NodeList filmc = dirfilms.item(j).getChildNodes();

	    	        	for (int k = 0; k < filmc.getLength(); k++) 
		    	        {
		    	        	// Store the starid and movieid  into arraylist starsinmovies.
			        		StarsInMovies starinmovie = new StarsInMovies();

	    	        		if(filmc.item(k).getNodeType() == Node.ELEMENT_NODE)
	    	        		{
		    	        		Element m = (Element) filmc.item(k);
		    	        		
		    	        		// Gettting title and getting starid and movieid
		    	        		if (m.getElementsByTagName("t") != null&& m!=null &&m.getElementsByTagName("t").item(0)!=null)
		    	        		{
		    	        			String title = m.getElementsByTagName("t").item(0).getTextContent();
		    	        			
		    	        			// Compare the title and get the movieid from db.
		    	        			if(dbMovieHm.containsValue(title.toUpperCase()))
		    						{
		    	        				if (this.getKeyByValue(dbMovieHm,title) != null) {
			    	        				starinmovie.setMovieId(this.getKeyByValue(dbMovieHm,title));
		    	        				}
		    						}
		    	        			
		    	        			// Getting starid
									if (m.getElementsByTagName("a") != null&& m!=null &&m.getElementsByTagName("a").item(0)!=null) 
									{
										String stagename = m.getElementsByTagName("a").item(0).getTextContent();
										
			    	        			// Compare the stagename and get the starid from db.
			    	        			if(dbStarHm.containsValue(stagename.toUpperCase()))
			    						{
			    	        				if (this.getKeyByValue(dbStarHm,stagename) != null) {
				    	        				starinmovie.setStarId(this.getKeyByValue(dbStarHm,stagename));
			    	        				}
			    						}
										
			    	        			starsinmovies.add(starinmovie);
										count ++;
									}
		    	        		}
								
	    	        		}
	    	        		
		    	        }
	    	        	
	    	        	
	    	        }
	    		}
	        }
		}
//		System.out.println("Count is: " + starsinmovies.size());
		
		try // insert the stars ArrayList in batches
		{
			long tStart = System.currentTimeMillis();

			starData.insertStarsInMovies(starsinmovies);

			long tEnd = System.currentTimeMillis();
			long tDelta = tEnd - tStart;
			double elapsedSeconds = tDelta / 1000.0;
			System.out.println("Seconds taken for inserting into stars_in_movies:" + elapsedSeconds);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            
		
	}
	
	
	public void printData()
	{
		
	}
}
