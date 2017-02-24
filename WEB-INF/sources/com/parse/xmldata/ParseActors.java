package com.parse.xmldata;

//import com.battleborn.webapps.*;
import com.mysql.jdbc.StringUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

public class ParseActors implements ParseData
{
	Document dom;
	ArrayList<Star> stars = new ArrayList<Star>(); //from actors.xml file
	
	int count = 0;
	
	public boolean isInteger(String input)
	{
		try
		{
			Integer.parseInt(input);
			return true;
		}
		catch(NumberFormatException e)
		{
			System.out.println("String is not numeric.");
			return false;
		}
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
		HashSet<String> dbStarHs = new HashSet<String>();
		
		StarDetails starData = new StarDetails();
		
		try{
			dbStarHs = starData.getStarDetails();
		}		
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		Element docEle = dom.getDocumentElement();
		
		// Get root element "actors" -- <actors>
		NodeList actors = docEle.getChildNodes();
		
		if (actors != null && actors.getLength()>0) 
		{
			// Traverse each child of root "actors.item(i)" -- <actor>
	        for (int i = 0; i < actors.getLength(); i++) 
	        {
	        	// To check whether each child of root is an element node (leaf node), contains elements like <stagename> and <dob>
	        	if(actors.item(i).getNodeType() == Node.ELEMENT_NODE)
	        	{
	        		// The child is an element node, get the star stagename and dob from the text.
	        		Element leafElement = (Element) actors.item(i);
	        		
        			// Store the stagename and dob get from xml file into arraylist star.
	        		Star star = new Star();
	        		
	        		// Since this is the leaf element, it should only contain text, no more nodes below it. Only use item(0) of this leaf element.
	        		// Get stage name
	        		if (leafElement.getElementsByTagName("stagename") != null && leafElement!=null && leafElement.getElementsByTagName("stagename").item(0)!=null)
	        		{
	        			String stagename = leafElement.getElementsByTagName("stagename").item(0).getTextContent();
	        			
//						System.out.println("Stage name is: " + stagename); // DEBUG
						
						// Compare the stagename with the star name currently in the database
						if(!dbStarHs.contains(stagename.toUpperCase()))
						{
							dbStarHs.add(stagename.toUpperCase());
		        			// Store the stagename get from xml file into arraylist star.
							String name[] = stagename.split(" ");
							
							if(name.length>0) {
								star.setFirstName(name[0].toUpperCase());
							}
							if(name.length>1) {
								star.setLastName(name[1].toUpperCase());
							}
							else {
								star.setLastName("");
							}

//							System.out.println("Name is: " + star.getFirstName() + " " + star.getLastName());
							
			        		// Get dob (date of birth)
			        		if (leafElement.getElementsByTagName("dob") != null && leafElement!=null && leafElement.getElementsByTagName("dob").item(0)!=null)
			        		{
			        			String dateOfBirth = leafElement.getElementsByTagName("dob").item(0).getTextContent();
			        			
//								System.out.println("DoB is: " + dateOfBirth); // DEBUG
								
								// Check if the date of birth (only contains year, so it's pure digits) is legal
								// Normally it will be like "1970", but it is like "19XX" and "*" in some cases in the xml file
						        String dob = null;
						        
						        if (this.isInteger(dateOfBirth) && dateOfBirth != null && dateOfBirth != "") {
						        	dob = dateOfBirth + "/01/01";
						        } else {
						        	dob = null;
						        }

			        			// Store the dob get from xml file into arraylist star.						
								star.setdob(dob);
//								System.out.println("Dob is: " + star.getdob());
			        		}
			        		
			        		stars.add(star);
			        		count ++;
						} else {
							System.out.println("The star already exists in the database.");
						}
	        		}
	        	}
	        }
		}
		
//		System.out.println("Count is: " + stars.size());
		
		try // insert the stars ArrayList in batches
		{
			long tStart = System.currentTimeMillis();
			
			starData.insertStars(stars);
			
			long tEnd = System.currentTimeMillis();
			long tDelta = tEnd - tStart;
			double elapsedSeconds = tDelta / 1000.0;
			System.out.println("Seconds taken for inserting into stars:" + elapsedSeconds);
			
			//starDetails.insertGenres(genres);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
	}
}
