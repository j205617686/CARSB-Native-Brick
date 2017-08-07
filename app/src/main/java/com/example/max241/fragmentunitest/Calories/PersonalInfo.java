package com.example.max241.fragmentunitest.Calories;

import com.example.max241.fragmentunitest.AppView;
import com.example.max241.fragmentunitest.Event;
import com.example.max241.fragmentunitest.Info;
import com.example.max241.fragmentunitest.Port;
import com.example.max241.fragmentunitest.Source;

/**
 * Created by max241 on 2015/10/9.
 */
public class PersonalInfo extends Port {

    Info info=new Info();
    //Info inputinfo = new Info("1","none");
    //Info outputinfo = new Info("2","personalinfo");
    String url;
    AppView view=new AppView();

    public PersonalInfo(AppView view,Info info)
    {
        this.view=view;
        this.info=info;


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



    Event event=new Event();
    Source source =new Source(getInfo().getId(),getInfo().getType());

    @Override
    public Event outputEventPort(String obj) {

        this.event.reset();

        this.event.setType(getInfo().getType());
        this.event.setContent(obj);
        this.event.setSource(source);


        return(event);


    }



}
