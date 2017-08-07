package com.example.max241.fragmentunitest;

import java.util.ArrayList;
import java.util.HashMap;
import android.support.v4.app.Fragment;
import android.view.View;

public interface BrickInterface {

	FragmentCommunicator listener=null;
	String Brickid=null;
	String element=null;
	ArrayList<String> inputEvenType=new ArrayList<String>();;
	ArrayList<String> outputEventType=new ArrayList<String>();;
	ArrayList<Port> inputEventPorts=new ArrayList<Port>();;
	ArrayList<Port> outputEventPorts=new ArrayList<Port>();;
	HashMap<String, String> inputTriggerExample =new HashMap<String, String>();
	HashMap<String, String> outputTriggerExample=new HashMap<String, String>();
	String resourceUrl=null;
	AppView appView=new AppView();
	
	public void init(String id,String element,ArrayList<resourceBinding> data);
	public View generateElement();
	
	public ArrayList<Port> getOutputEventPorts();
	public ArrayList<Port> getInputEventPorts();
	
	
	public AppView getAppView() ;

	public void setAppView(AppView appView) ;

	public void setFragmentListener(FragmentCommunicator listener);

	public String getBrickid() ;
	

	public void setBrickid(String id) ;
	

	public String getElement() ;
	

	public void setElement(String element) ;
	

	public ArrayList<String> getInputEvenType();

	public ArrayList<String> getOutputEvenType();

	public void setInputEvenType(ArrayList<String> InputEvenType);

	public void setOutputEvenType(ArrayList<String> OutputEvenType);

	public void setInputEventPorts(ArrayList<Port> inputEventPorts) ;
	

	public void setOutputEventPorts(ArrayList<Port> outputEventPorts) ;
	


	public HashMap<String, String> getInputTriggerExample() ;



	public void setInputTriggerExample(HashMap<String, String> inputTriggerExample) ;
	

	public HashMap<String, String> getOutputTriggerExample() ;
	


	public void setOutputTriggerExample(HashMap<String, String> outputTriggerExample);
	

	public String getResourceurl();
	


	public void setResourceurl(String resourceurl) ;
	


}
