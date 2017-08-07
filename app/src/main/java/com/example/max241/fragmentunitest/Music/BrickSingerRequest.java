package com.example.max241.fragmentunitest.Music;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InputEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.max241.fragmentunitest.AppView;
import com.example.max241.fragmentunitest.BrickInterface;
import com.example.max241.fragmentunitest.Event;
import com.example.max241.fragmentunitest.FragmentCommunicator;
import com.example.max241.fragmentunitest.Info;
import com.example.max241.fragmentunitest.Port;
import com.example.max241.fragmentunitest.R;
import com.example.max241.fragmentunitest.noService;
import com.example.max241.fragmentunitest.resourceBinding;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class BrickSingerRequest extends Fragment implements BrickInterface {

    FragmentCommunicator listener;
    Context context;


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
        View view = inflater.inflate(R.layout.fragment_a, container, false);            //此處填入此brick使用的layout介面檔






        context = getContext();

        //============================此區為根據介面檔所定義的元件,可自訂或使用預設的========================//

        Button btn = (Button) view.findViewById(R.id.fa_btn);

        final TextView TitleTV=(TextView) view.findViewById(R.id.fa_title);
        TitleTV.setText("BrickSingerRequest");                                          //於此填入Brick名稱

        final EditText et = (EditText) view.findViewById(R.id.fa_et);
        //==================================================================================================//
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (null != listener) {



                    CharSequence text1 = "Send Event";

                    int duration1 = Toast.LENGTH_SHORT;

                    final Toast toast1 = Toast.makeText(context, text1, duration1);

                    toast1.show();


                    String inputStr = et.getText().toString();                  //此區為這個Brick會觸發的event所得到的資訊 這裡預設EditText元件的內容為input String

                    ArrayList<Event> allEvent = new ArrayList<Event>();


                    for (int i = 0; i < getOutputEventPorts().size(); i++) {
                        Event event = new Event();


                        event = getOutputEventPorts().get(i).outputEventPort(inputStr);

                        allEvent.add(event);

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
    String output[]={"singer"};
    ArrayList<String> inputEventType=new ArrayList<String>();
    ArrayList<String> outputEventType=new ArrayList<String>();
    ArrayList<Port> inputEventPorts=new ArrayList<Port>();
    ArrayList<Port> outputEventPorts=new ArrayList<Port>();
    HashMap<String, String> inputTriggerExample =new HashMap<String, String>();
    HashMap<String, String> outputTriggerExample=new HashMap<String, String>();
    String resourceUrl;
    AppView appView=new AppView();



    //==================此區new出會用到的型態成info物件===========================//
    Info inputinfo1 =new Info(Brickid,inputEventType.get(0));
    Info outputinfo1=new Info(Brickid,outputEventType.get(0));
    //============================================================================//



    //=====================new出input/output EventPort物件=========================//
    Request request=new Request(appView,outputinfo1);								  //在第二個參數放上面定義的INFO(自行指定的type)
    noService nS=new noService();

    //=========================================================================//






    public void init(String id,String element,ArrayList<resourceBinding> data)
    {

        this.Brickid=id;
        this.element=element;



        inputEventPorts.add(nS);                                            //在此處塞入inputEventport 物件
        outputEventPorts.add(request);                                      //在此處塞入outputEventport 物件


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

    public ArrayList<Port> getInputEventPorts() {
        return inputEventPorts;
    }

    public void setInputEventPorts(ArrayList<Port> inputEventPorts) {
        this.inputEventPorts = inputEventPorts;
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











