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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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


public class BrickPersonalInfo extends Fragment implements BrickInterface {

    FragmentCommunicator listener;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context=getContext();

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personalinfo, container, false);

        Button btn = (Button) view.findViewById(R.id.fa_btn);
        //final TextView textView = (TextView) view.findViewById(R.id.fa);
        //final EditText ismale = (EditText) view.findViewById(R.id.et4);

        final RadioButton RB1=(RadioButton)view.findViewById(R.id.radioButton2);
        final RadioButton RB2=(RadioButton)view.findViewById(R.id.radioButton1);

        final RadioGroup RG=(RadioGroup)view.findViewById(R.id.radioGroup);
        final TextView tv=(TextView)view.findViewById(R.id.textv);



        final RadioGroup.OnCheckedChangeListener changeradio = new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                // 透過id來辨認不同的radiobutton
                switch (checkedId) {
                    case R.id.radioButton2:
                        tv.setText("true");
                        break;
                    case R.id.radioButton1:
                        tv.setText("false");
                        break;
                    default:
                        break;



                }
            }
        };











        final EditText age = (EditText) view.findViewById(R.id.fa_et);
        final EditText weight = (EditText) view.findViewById(R.id.fa_et2);


        final TextView TitleTV=(TextView) view.findViewById(R.id.fc_tv);

        TitleTV.setText("BrickPersonalInfo");

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {

                    RG.setOnCheckedChangeListener(changeradio);
                    CharSequence text1 = "BrickPI send event";      //設定顯示的訊息

                    int duration1 = Toast.LENGTH_SHORT;   //設定訊息停留長短

                    Toast toast1 = Toast.makeText(context, text1, duration1); //建立物件

                    toast1.show();

                    String inputStr = tv.getText().toString()+" "+age.getText().toString()+" "+weight.getText().toString();


                    System.out.println("inputttttttttttttttttttt:"+inputStr);
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

    String input[]={"none"};
    String output[]={"personalInfo"};
    ArrayList<String> inputEventType=new ArrayList<String>();
    ArrayList<String> outputEventType=new ArrayList<String>();
    ArrayList<Port> inputEventPorts=new ArrayList<Port>();;
    ArrayList<Port> outputEventPorts=new ArrayList<Port>();;
    HashMap<String, String> inputTriggerExample =new HashMap<String, String>();
    HashMap<String, String> outputTriggerExample=new HashMap<String, String>();
    String resourceUrl;
    AppView appView=new AppView();
   Info outputinfo1=new Info(Brickid,output[0]);

    //----------------------------------------------------------------------------//

    noService nS=new noService();
    PersonalInfo PI=new PersonalInfo(appView,outputinfo1);

   //------------------------------------------------------------------------------//



    public void setView(AppView view)
    {

        this.appView=view;


    }




    public void init(String id,String element,ArrayList<resourceBinding> data)
    {

        this.Brickid=id;
        this.element=element;



        inputEventPorts.add(nS);     //呼叫此服務並塞入eventport function
        outputEventPorts.add(PI);


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
