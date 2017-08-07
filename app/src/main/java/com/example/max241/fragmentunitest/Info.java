package com.example.max241.fragmentunitest;

import android.content.SyncAdapterType;

public class Info {
	String id="";
	String type="";
	
	public Info()
	{
		
		
	}
	public Info(String id, String type) {
		
		this.id=id;
		this.type=type;
		
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	public String toString() {
		return(id + " " + type);
	}




}
