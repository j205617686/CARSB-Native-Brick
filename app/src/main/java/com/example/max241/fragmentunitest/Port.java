package com.example.max241.fragmentunitest;

import java.util.ArrayList;

public class Port {
	
	Info info=new Info();
	String url;
	
	
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	
	public void init(resourceBinding data) {
		
	}
	
	public void inputEventPort(Event event) {
	}
	
	public Event outputEventPort(String obj) {
		Event event=new Event();
		return event;
	}

}
