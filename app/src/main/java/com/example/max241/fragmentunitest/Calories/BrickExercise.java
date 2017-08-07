package com.example.max241.fragmentunitest.Calories;

/**
 * Created by max241 on 2015/10/9.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.max241.fragmentunitest.music.Request;


import com.example.max241.fragmentunitest.AppView;
import com.example.max241.fragmentunitest.BrickInterface;
import com.example.max241.fragmentunitest.Event;
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


public class BrickExercise extends Fragment implements BrickInterface {

    FragmentCommunicator listener;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context=getContext();

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);

        Button btn = (Button) view.findViewById(R.id.fa_btn);
        //final TextView textView = (TextView) view.findViewById(R.id.fa);
        final EditText HeartRate = (EditText) view.findViewById(R.id.fb_et);
        final EditText Time = (EditText) view.findViewById(R.id.fb_et2);

        final TextView TitleTV=(TextView) view.findViewById(R.id.fc_tv);
        final TextView past= (TextView) view.findViewById(R.id.past);
        ex1.setElement((Button) view.findViewById(R.id.fa_btn),(TextView) view.findViewById(R.id.past),context);
        TitleTV.setText("BrickExercise   ");

        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (null != listener) {

                    CharSequence text1 = "BrickEX send event";      //設定顯示的訊息

                    int duration1 = Toast.LENGTH_SHORT;   //設定訊息停留長短

                    Toast toast1 = Toast.makeText(context, text1, duration1); //建立物件

                    toast1.show();

                    String inputStr = past.getText().toString()+" "+HeartRate.getText().toString() +" "+Time.getText().toString() ;

                    ArrayList<Event> allEvent = new ArrayList<Event>();


                    for (int i = 0; i < getOutputEventPorts().size(); i++) {
                        Event event = new Event();


                        event = getOutputEventPorts().get(i).outputEventPort(inputStr);

                        allEvent.add(event);

                        //eventEngine.eventEngine(view.getBricks(),event);


                    }


                    listener.sendEvent(allEvent);
                }

            }
        });
        return view;
    }

    public void setFragmentListener(FragmentCommunicator listener) {
        this.listener = listener;
    }


    String Brickid;
    String element;
    String input[]={"personalInfo"};
    String output[]={"exercise"};
    ArrayList<String> inputEventType=new ArrayList<String>();
    ArrayList<String> outputEventType=new ArrayList<String>();
    ArrayList<Port> inputEventPorts=new ArrayList<Port>();;
    ArrayList<Port> outputEventPorts=new ArrayList<Port>();;
    HashMap<String, String> inputTriggerExample =new HashMap<String, String>();
    HashMap<String, String> outputTriggerExample=new HashMap<String, String>();
    String resourceUrl;
    AppView appView=new AppView();
    Info outinfo=new Info(Brickid,output[0]);
    Info inInfo=new Info(Brickid,input[0]);

    //----------------------------------------------------------------------------//

    noService nS=new noService();
    Exercise ex1=new Exercise(appView,inInfo);
    Exercise ex2=new Exercise(appView,outinfo);

    //----------------------------------------------------------------------------//



    public void setView(AppView view)
    {

        this.appView=view;


    }




    public void init(String id,String element,ArrayList<resourceBinding> data)
    {

        this.Brickid=id;
        this.element=element;



        inputEventPorts.add(ex1);     //呼叫此服務並塞入eventport function
        outputEventPorts.add(ex2);


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

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
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

        return(getView());

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
