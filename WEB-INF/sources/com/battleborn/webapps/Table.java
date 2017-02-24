package com.battleborn.webapps;

public class Table {


	String tableName;
	String tableType;
	
	public Table() 
	{
		tableName = null;
		tableType = null;
	}
	
	public void setName(String tableName) {
		this.tableName = tableName;
	}
	
	public void setType(String tableType) {
		this.tableType = tableType;
	}

	public String getName() {
		return tableName;
	}
	
	public String getType() {
		return tableType;
	}
	
}

