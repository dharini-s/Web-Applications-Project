package com.battleborn.webapps;

//import java.util.Date;

public class Star {
	
	int id;
	String firstName;
	String lastName;
	String dob;
	String photoURL;
	
	public Star()
	{
		firstName=null;
		lastName=null;
		dob=null;
		photoURL=null;
	}
	
	public void setID(int id)
	{
		this.id=id;
	}
	public void setFirstName(String firstName)
	{
		this.firstName=firstName;
	}
	public void setLastName(String lastName)
	{
		this.lastName=lastName;
	}
	public void setdob(String dob)
	{
		this.dob=dob;
	}
	public void setPhotoURL(String photoURL)
	{
		this.photoURL=photoURL;
	}
	
	public int getID()
	{
		return id;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public String getName()
	{
		return firstName + " " + lastName;
	}
	public String getdob()
	{
		return dob;
	}
	public String setPhotoURL()
	{
		return photoURL;
	}

}
