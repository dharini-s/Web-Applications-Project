package com.battleborn.webapps;

public class CartItem {

	int id;
	String title;
	int quantity;
	String price;

	// Add item with default quantity 1.
	public CartItem() 
	{	
		title = null;
		quantity  = 1;
		price = "$15.99";
	}
	
	// Add item with user defined quantity.
	public CartItem(int newQuantity) 
	{	
		title = null;
		quantity  = newQuantity;
		price = "$15.99";
	}

	public void setID(int id){
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setQuantity(int newQuantity) {
		this.quantity = newQuantity;
	}
	
	public void setPrice(String price){
		this.price = price;
	}

	public int getID() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public String getPrice() {
		return price;
	}

}

