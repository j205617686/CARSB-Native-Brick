package com.example.max241.fragmentunitest.Music;

import com.example.max241.fragmentunitest.AppView;
import com.example.max241.fragmentunitest.Event;
import com.example.max241.fragmentunitest.Info;
import com.example.max241.fragmentunitest.Port;
import com.example.max241.fragmentunitest.Source;

public class Request extends Port {

	Info info = new Info();
	String url;
	AppView view=new AppView();


	public Request()
	{


	}
	public Request(AppView view,Info info)
	{
		this.view=view;
		this.info.setId(info.getId());
		this.info.setType(info.getType());
		
		
	}

	public AppView getAppView(){return view;}

	public void setAppView(AppView view){this.view=view;}

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

	
	
	Event event=new Event();
	Source source =new Source(getInfo().getId(),getInfo().getType());
	
	@Override
	public Event outputEventPort(String obj) {
		
	this.event.reset();
	
	this.event.setType(this.info.getType());
	this.event.setContent(obj);
	this.event.setSource(source);	
		
	
	return(event);
		
		
	}


	
	
	
}
