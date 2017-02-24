package com.parse.xmldata;


public interface ParseData 
{
	//parse the xml file and get the dom object
	public void parseXmlFile(String s);
	
	//get each employee element and create a Employee object
	public void parseDocument();
	

}
