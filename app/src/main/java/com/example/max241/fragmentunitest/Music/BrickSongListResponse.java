package com.example.max241.fragmentunitest.Music;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.max241.fragmentunitest.AppView;
import com.example.max241.fragmentunitest.BrickInterface;
import com.example.max241.fragmentunitest.FragmentCommunicator;
import com.example.max241.fragmentunitest.Info;
import com.example.max241.fragmentunitest.Port;
import com.example.max241.fragmentunitest.R;
import com.example.max241.fragmentunitest.noService;
import com.example.max241.fragmentunitest.resourceBinding;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import  org.json.simple.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class BrickSongListResponse extends Fragment implements BrickInterface {


    FragmentCommunicator listener;
    Context context2;

    public void setFragmentListener(FragmentCommunicator listener) {
        this.listener = listener;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String jsonstr = "";


        try {
            InputStream is = getResources().getAssets().open("BrickSingerRequest.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonstr = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();

        }




        try {


            JSONParser parser = new JSONParser();

            Object obj = parser.parse(jsonstr);
            JSONObject jsonObject = (JSONObject) obj;

            JSONObject brick =(JSONObject) jsonObject.get("brick");

            Brickid=brick.get("id").toString();

            JSONArray inputEvent=(JSONArray)brick.get("inputEvent");
            JSONArray outputEvent=(JSONArray)brick.get("outputEvent");

            for(int i=0;i<inputEvent.size();i++)
            {
                JSONObject eventType=(JSONObject) inputEvent.get(i);



                inputEventType.add(eventType.get("eventType").toString());


            }

            for(int i=0;i<outputEvent.size();i++)
            {
                JSONObject eventType=(JSONObject) outputEvent.get(i);
                outputEventType.add(eventType.get("eventType").toString());


            }








        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }	//讀json檔 並得到json字串


















    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);				//此處填入此brick使用的layout介面檔

		this.context2=getContext();
		
		//============================此區為根據介面檔所定義的元件,可自訂或使用預設的========================//
        TextView textView = (TextView) view.findViewById(R.id.fb_et2);
		textView.setText("BrickAlbumResponse");


        response.setElement((EditText) view.findViewById(R.id.editText1),context2);			//response這個inputEventPort會更改到介面來產生結果 ,所以必須傳入元件ID
		//===================================================================================================//



        return view;
    }



    String Brickid="";
    String element="";
    String input[]={"singer"};
    String output[]={"none"};
    ArrayList<String> inputEventType=new ArrayList<String>();
    ArrayList<String> outputEventType=new ArrayList<String>();
    ArrayList<Port> inputEventPorts=new ArrayList<Port>();
    ArrayList<Port> outputEventPorts=new ArrayList<Port>();;
    HashMap<String, String> inputTriggerExample=new HashMap<String, String>();
    HashMap<String, String> outputTriggerExample=new HashMap<String, String>();
    String resourceUrl="";
    AppView appView=new AppView();

    //==================此區new出會用到的型態成info物件===================//
    Info inputInfo1=new Info(Brickid,inputEventType.get(0));
    //===================================================================//

    //=====================new出input/output EventPort物件=====================//
    Response response=new Response(appView,inputInfo1);
    noService nS=new noService();

     //=====================================================================//

    public void init(String id,String element,ArrayList<resourceBinding> data)
    {

        this.Brickid=id;
        this.element=element;


        inputEventPorts.add(response);                  //在此處塞入inputEventport 物件
        outputEventPorts.add(nS);			            //在此處塞入outputEventport 物件

        for(int i=0;i<data.size();i++)
        {
            inputEventPorts.get(i).init(data.get(i));
        }



    }




	
	
	
	
	
	
	
	
	
    public String getResourceUrl() {
        return resourceUrl;
    }


    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getBrickid() {
        return Brickid;
    }


    public void setBrickid(String brickid) {
        Brickid = brickid;
    }


    public AppView getAppView() {
        return appView;
    }

    public void setAppView(AppView appView) {
        this.appView = appView;
    }


    public String getElement() {
        return element;
    }


    public void setElement(String element) {
        this.element = element;
    }


    public ArrayList<String> getInputEvenType(){return inputEventType;}

    public ArrayList<String> getOutputEvenType(){return outputEventType;}



    public void setInputEvenType(ArrayList<String> inputEvenType)
    {
        this.inputEventType=inputEvenType;
    }

    public void setOutputEvenType(ArrayList<String> outputEvenType)
    {
        this.outputEventType=outputEvenType;
    }


    public ArrayList<Port> getInputEventPorts() {
        return inputEventPorts;
    }


    public void setInputEventPorts(ArrayList<Port> inputEventPorts) {
        this.inputEventPorts = inputEventPorts;
    }


    public ArrayList<Port> getOutputEventPorts() {
        return outputEventPorts;
    }


    public void setOutputEventPorts(ArrayList<Port> outputEventPorts) {
        this.outputEventPorts = outputEventPorts;
    }


    public HashMap<String, String> getInputTriggerExample() {
        return inputTriggerExample;
    }


    public void setInputTriggerExample(HashMap<String, String> inputTriggerExample) {
        this.inputTriggerExample = inputTriggerExample;
    }


    public HashMap<String, String> getOutputTriggerExample() {
        return outputTriggerExample;
    }


    public void setOutputTriggerExample(HashMap<String, String> outputTriggerExample) {
        this.outputTriggerExample = outputTriggerExample;
    }


    public String getResourceurl() {
        return resourceUrl;
    }


    public void setResourceurl(String resourceurl) {
        this.resourceUrl = resourceurl;
    }


    public View generateElement()			
    {

        return (getView());

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity a;

        if (context instanceof Activity){
            a=(Activity) context;
        }

    }

}