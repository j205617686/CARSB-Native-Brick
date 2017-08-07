package com.example.max241.fragmentunitest.Calories;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.max241.fragmentunitest.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Calories extends Port {

    Info info  = new Info();
    AppView view=new AppView();
    String url;
    String joperacompositeservice;
    TextView tv;
    Context context;

    public Calories(AppView view,Info info)
    {
        this.view=view;
        this.info.setId(info.getId());
        this.info.setType(info.getType());


    }

    public void setElement(TextView tv,Context context)
    {
        this.tv=tv;
        this.context=context;

    }

    @Override
    public void init(resourceBinding data) {

        //Info infotmp  = new Info("1","");

       // this.info=infotmp;


        //this.info=info;
        this.url=data.getDefaultResourceUrl();
        this.joperacompositeservice="";
        //this.init(data);

        System.out.println("dataType:"+data.getType());
        System.out.println("infoType:"+this.info.getType());
        if(data.getType().equals(this.info.getType()))
        {


            this.joperacompositeservice="";
        }

    }

    @Override
    public void inputEventPort(Event event) {


        System.out.println("Do calories inputEventPort");
        HttpConnect HC=new HttpConnect();

        String url;
        String jsonStr;
        String result="";

            String[] parameter=event.getContent().split(" ");

            String sexual=parameter[0];
            String Age=parameter[1];
            String weight=parameter[2];
            String HR=parameter[3];
            String T=parameter[4];


            //String url2 = this.url+"?"+URLEncoder.encode(event.getContent(), "UTF-8");
            url = this.url+"?"+"HR="+HR+"&Age="+Age+"&T="+T+"&W="+weight+"&sexual="+sexual;


            //url="http://140.121.197.132:8080/CalServlet/calories.do?HR=120&Age=23&T=2&W=52&sexual=true";



            System.out.println(url);

            System.out.print("Event Response:" + HC.getData(url));

            CharSequence text1 = "Event Response";      //設定顯示的訊息

            int duration1 = Toast.LENGTH_SHORT;   //設定訊息停留長短

            final Toast toast1 = Toast.makeText(context, text1, duration1); //建立物件
            toast1.show();



            try {

                jsonStr=HC.getData(url);

                JSONParser parser = new JSONParser();

                Object obj = parser.parse(jsonStr);
                JSONObject jsonObject =  (JSONObject) obj;


                JSONObject output=(JSONObject) jsonObject.get("output");
                Double calories=(Double) output.get("calories");


                result=calories.toString();


            }catch (Exception e)
            {
                e.printStackTrace();

            }




            tv.setText(result);




    }






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



    public String getJoperacompositeservice() {
        return joperacompositeservice;
    }



    public void setJoperacompositeservice(String joperacompositeservice) {
        this.joperacompositeservice = joperacompositeservice;
    }






}
