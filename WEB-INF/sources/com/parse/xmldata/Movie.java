package com.parse.xmldata;

public class Movie {

	int id;
	String title;
	int year;
	String director;
	String bannerUrl;
	String trailerUrl;
	
	public Movie() 
	{
		title = null;
		director = null;
		bannerUrl = null;
		trailerUrl = null;
		
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public void setYear(int year){
		this.year = year;
	}
	
	public void setDirector(String director){
		this.director = director;
	}
	
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	
	public void setTrailerUrl(String trailerUrl){
		this.trailerUrl = trailerUrl;
	}
	
	
	public String getTitle() {
		return title;
	}
	
	public int getID() {
		return id;
	}
	
	public int getYear() {
		return year;
	}
	
	public String getDirector() {
		return director;
	}
	
	public String getBannerUrl() {
		return bannerUrl;
	}
	
	public String getTrailerUrl() {
		return trailerUrl;
	}

}

