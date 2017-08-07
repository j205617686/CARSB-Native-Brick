package com.example.max241.fragmentunitest;

import java.util.ArrayList;

public class AppView {
	
	
	String id="v0001";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	ArrayList<BrickInterface> bricks=new ArrayList<BrickInterface>();
	ArrayList<ForbiddenConnection> FCs=new ArrayList<ForbiddenConnection>();
	ArrayList<EnforcedConnection> ECs=new ArrayList<EnforcedConnection>() ;
	
	public ArrayList<BrickInterface> getBricks() {
		return bricks;
	}
	public void setBricks(ArrayList<BrickInterface> bricks) {
		this.bricks = bricks;
	}
	public ArrayList<ForbiddenConnection> getFCs() {
		return FCs;
	}
	public void setFCs(ArrayList<ForbiddenConnection> fCs) {
		FCs = fCs;
	}
	public ArrayList<EnforcedConnection> getECs() {
		return ECs;
	}
	public void setECs(ArrayList<EnforcedConnection> eCs) {
		ECs = eCs;
	}
	
	
	
	

}
