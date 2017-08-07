package com.example.max241.fragmentunitest;

public class resourceBinding {
	
	
	
	String defaultResourceUrl;
	ComponentServiceReplacement CSR =new ComponentServiceReplacement();
	String action;
	String type;
	
	public resourceBinding(String defaultResourceUrl,ComponentServiceReplacement CSR ,String action,String type)
	{
		this.defaultResourceUrl=defaultResourceUrl;
		this.CSR=CSR;
		this.action=action;
		this.type=type;
	}
	
	
	
	public String getDefaultResourceUrl() {
		return defaultResourceUrl;
	}
	public void setDefaultResourceUrl(String defaultResourceUrl) {
		this.defaultResourceUrl = defaultResourceUrl;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public ComponentServiceReplacement getCSR() {
		return CSR;
	}
	public void setCSR(ComponentServiceReplacement cSR) {
		CSR = cSR;
	}

}
