package com.example.max241.fragmentunitest.Calories;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.max241.fragmentunitest.AppView;
import com.example.max241.fragmentunitest.Event;
import com.example.max241.fragmentunitest.Info;
import com.example.max241.fragmentunitest.Port;
import com.example.max241.fragmentunitest.Source;

/**
 * Created by max241 on 2015/10/9.
 */
public class Exercise extends Port {


    Info info = new Info();
    String url;
    AppView view = new AppView();
    Button btn;
  TextView tv;
Context context;
    public Exercise(AppView view,Info info) {

        this.view = view;
        this.info=info;

    }

    public void setElement(Button btn,TextView tv,Context ct)
    {
        this.btn=btn;
        this.tv=tv;
        this.context=ct;

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


    Event event = new Event();
    Source source = new Source(getInfo().getId(), getInfo().getType());

    @Override
    public Event outputEventPort(String obj) {

        this.event.reset();

        this.event.setType(this.info.getType());
        this.event.setContent(obj);
        this.event.setSource(source);


        return (event);


    }

    public void inputEventPort(Event event) {

        System.out.println("Do exercise inputEventPort");
        CharSequence text1 = "BrickEX get event";      //設定顯示的訊息

        int duration1 = Toast.LENGTH_SHORT;   //設定訊息停留長短

        Toast toast1 = Toast.makeText(context, text1, duration1); //建立物件

        toast1.show();


        tv.setText(event.getContent());
        btn.callOnClick();



    }
}