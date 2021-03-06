package com.example.max241.fragmentunitest.Calories;

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

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class BrickCalories extends Fragment implements BrickInterface {

    TextView textView;
    View v;
    EditText et;
    FragmentCommunicator listener;
    Context context2;

    public void setFragmentListener(FragmentCommunicator listener) {
        this.listener = listener;
    }

    public void inputEventPort(String event)
    {
        textView.setText(event);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context2=getContext();

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calories, container, false);

        textView = (TextView) view.findViewById(R.id.fc_tv);
        //et =(EditText) view.findViewById(R.id.fb_et1);

        textView.setText("BrickCalories");

        cal.setElement((TextView) view.findViewById(R.id.cal_value),context2);

        Log.v("tvvvvvvvvvvvvvvvv:", textView.toString());




        return view;
    }



    String Brickid;
    String element;
    String input[]={"exercise"};
    String output[]={"none"};
    ArrayList<String> inputEventType=new ArrayList<String>();
    ArrayList<String> outputEventType=new ArrayList<String>();
    ArrayList<Port> inputEventPorts=new ArrayList<Port>();
    ArrayList<Port> outputEventPorts=new ArrayList<Port>();;
    HashMap<String, String> inputTriggerExample=new HashMap<String, String>();
    HashMap<String, String> outputTriggerExample=new HashMap<String, String>();
    String resourceUrl;
    AppView appView=new AppView();
    Info InputInfo=new Info(Brickid,input[0]);


    public String getResourceUrl() {
        return resourceUrl;
    }


    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }





    Calories cal=new Calories(appView,InputInfo);



    noService nS=new noService();




    public void init(String id,String element,ArrayList<resourceBinding> data)
    {

        this.Brickid=id;
        this.element=element;


        inputEventPorts.add(cal);     //呼叫此服務並塞入eventport function
        outputEventPorts.add(nS);

        for(int i=0;i<data.size();i++)
        {

            inputEventPorts.get(i).init(data.get(i));
        }



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


    public View generateElement()			//產生native 物件介面與接收參數 ,暫時使用靜態的
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