package com.example.max241.fragmentunitest;

import java.util.ArrayList;
import java.util.HashMap;

public class Event {
	
	private String type;
	private String content;
	private Source source=new Source();;
	private String trigger;
	
	public String getType() {
		return type;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getTrigger() {
		return trigger;
	}
	
	public Source getSource() {
		return source;
	}
	
	public void setType( String type ) {
		this.type = type;
	}
	
	public void setContent( String content ) {
		//content = content.replaceAll("\\\"", "\"");
		
		this.content = content;
	}
	
	public void setTrigger( String trigger ) {
		this.trigger = trigger;
	}
	
	public void setSource( Source source ) {
		this.source = source;
	}
	
	public void reset() {
		this.type = null;
		this.content = null;
		this.trigger = null;
		this.source=null;
	}
	
	public String toString() {
		return type + ':' + content;
	}
}
