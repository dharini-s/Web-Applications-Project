package com.parse.xmldata;

//import com.battleborn.webapps.*;
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

public class ParseMovies implements ParseData {
	Document dom;
	ArrayList<Movie> movies = new ArrayList<Movie>();

	HashSet<String> set = new HashSet<String>();
	
	HashMap<String, Integer> Title_to_Genre = new HashMap<String, Integer>();
	HashMap<Integer, ArrayList<Integer>> MovieID_GenreID = new HashMap<Integer, ArrayList<Integer>>();
	HashMap<String, Integer> fid_movieid = new HashMap<String, Integer>();
	ArrayList<String> fids = new ArrayList<String>();
	ArrayList<String> genres = new ArrayList<String>();

	int count = 0;

	public void parseXmlFile(String file) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			dom = db.parse(file);
//			System.out.println("After document has been parsed");
			this.parseDocument();

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void parseDocument() 
	{
		Element docEle = dom.getDocumentElement();
		
		MovieDetails movieDetails = new MovieDetails();
		try 
		{
			set = movieDetails.getMovieDetails();

		} catch (SQLException e) {
			System.out.println("Error in getting movie details from MovieDetails.java");
			e.printStackTrace();
		}

		NodeList directorFilms = docEle.getElementsByTagName("directorfilms");
		for(int i=0;i<directorFilms.getLength();i++) /*At directorfilms element*/
		{
			Node filmsNode = directorFilms.item(i);
			NodeList director= null;
			
			String titleMovie = null;//to be inserted into movie object
			String directorMovie = null;
			Integer yearMovie = null;
			if(filmsNode.getNodeType() == Node.ELEMENT_NODE) /*checking if filmsnode can be cast to Element*/
			{
				Element filmsElement = (Element) filmsNode;
				NodeList films = filmsElement.getElementsByTagName("film");
				
				NodeList directorList=filmsElement.getElementsByTagName("dirname");/*For getting the director from the same level as titles*/
				Element dirName = (Element) directorList.item(0);
				if(dirName!=null &&dirName.getChildNodes()!=null)
				{
					 director = dirName.getChildNodes();
				
					if(director!=null && director.item(0)!=null)
					{
//						System.out.println("Director : " + director.item(0).getTextContent());
						directorMovie=director.item(0).getTextContent();
						//movie.setDirector(director.item(0).getTextContent());
					}
				} else {
					System.out.println("Invalid director name <dirname>.");
				}
                
                /*Getting title and year*/
				for(int j=0;j<films.getLength();j++)
				{
					Node filmNode = films.item(j);
					if(filmNode.getNodeType() == Node.ELEMENT_NODE)
					{
						   
						Element filmElement = (Element)filmNode;
						NodeList filmList = filmElement.getElementsByTagName("t"); /*For getting the titles*/
						Movie movie = new Movie();
						if(filmList!=null&&filmList .item(0)!=null)
						{
//							System.out.println("Title : " + filmList.item(0).getTextContent());
							titleMovie=filmList.item(0).getTextContent();
							if(!set.contains(titleMovie.toUpperCase()))
							{
								set.add(titleMovie.toUpperCase());
								NodeList yearList = filmElement.getElementsByTagName("year"); /*For getting the titles*/
								if(filmList!=null&&filmList .item(0)!=null)
								{
									try
									{
										Integer.parseInt(yearList.item(0).getTextContent());
									}
									catch(NumberFormatException e)
									{
										System.out.println("Invalid year value <year>.");
										continue;
									}
									yearMovie = Integer.parseInt(yearList.item(0).getTextContent());
									//
//									System.out.println("Year : " + yearList.item(0).getTextContent());
									

								}
//								System.out.println("HashSet size: " + set.size());;
								if(directorMovie!=null && titleMovie!=null && yearMovie!=null)
								{
									movie.setYear(yearMovie);
									movie.setDirector(directorMovie);
									movie.setTitle(titleMovie);
									set.add(titleMovie.toUpperCase());
									movies.add(movie);
								}

							} else {
								System.out.println("Duplicated movie title <t>.");
							}
							//movie.setTitle(filmList.item(0).getTextContent());
							count++;
						}

						///get genres here
						NodeList cats = filmElement.getElementsByTagName("cats"); //m instead of cats?
						if(cats.getLength()>0&&cats!=null)
						{
							Element cat = (Element) cats.item(0);

							Node singleGenre = cat.getElementsByTagName("cat").item(0);// cat element
							if (singleGenre!= null) 
							{
								genres.add(cat.getElementsByTagName("cat").item(0).getTextContent());
							}

						}
						
					}
					
				}
			}
		}
		try // insert the movies ArrayList in batches
		{
			long tStart = System.currentTimeMillis();

			movieDetails.insertMovies(movies);
			
			long tEnd = System.currentTimeMillis();
			long tDelta = tEnd - tStart;
			double elapsedSeconds = tDelta / 1000.0;
			System.out.println("Seconds taken for inserting into movies:" + elapsedSeconds);
			
			long tStart1 = System.currentTimeMillis();

			movieDetails.insertGenres(genres);
			
			long tEnd1 = System.currentTimeMillis();
			long tDelta1 = tEnd1 - tStart1;
			double elapsedSeconds1 = tDelta1 / 1000.0;
			System.out.println("Seconds taken for inserting into genres:" + elapsedSeconds1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
//		System.out.println("Count is: " + count);
	}
}
