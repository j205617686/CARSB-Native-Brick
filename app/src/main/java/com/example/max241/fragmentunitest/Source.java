package com.example.max241.fragmentunitest;

public class Source {
	
	
	String brick;
	String eventType;
	
	
	public Source()
	{
		
		
	}
	
	public Source(String brick,String eventType)
	{
		
		this.brick=brick;
		this.eventType=eventType;
		
	}
	
	public String getBrick() {
		return brick;
	}
	public void setBrick(String brick) {
		this.brick = brick;
	}
	
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	

}
