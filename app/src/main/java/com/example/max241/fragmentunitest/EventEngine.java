package com.example.max241.fragmentunitest;


import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class EventEngine {



    ArrayList<ForbiddenConnection> FCs=new ArrayList<ForbiddenConnection>();
    ArrayList<EnforcedConnection> ECs=new ArrayList<EnforcedConnection>();


    public EventEngine(AppView view)
    {
        this.FCs=view.getFCs();
        this.ECs=view.getECs();

    }




    public void eventEngine(ArrayList<BrickInterface> bricks,Event event)
    {

        /*
        List<BrickInterface> bricks=new ArrayList<BrickInterface>();

        for(int i=0;i<fragmentbricks.size();i++)
        {

            bricks.add((BrickInterface) fragmentbricks.get(i));

        }
*/
       // ArrayList<Port> returnPort=new ArrayList<Port>();
       // System.out.println("inputEventPorts's Size:"+bricks.get(0).getInputEventPorts().size());

        System.out.println("brick size:"+bricks.size());

        for(int i=0;i<bricks.size();i++)
        {
            System.out.println("brick"+(i+1)+":");
            for(int j=0;j<bricks.get(i).getInputEventPorts().size();j++)
            {
                System.out.println("--------------------------------------------");
                System.out.println("i:"+i+",j:"+j+" type="+bricks.get(i).getInputEventPorts().get(j).getInfo().getType());
                System.out.println("i:"+i+",j:"+j+" type="+event.getType());
                System.out.println("--------------------------------------------");

                if(bricks.get(i).getInputEventPorts().get(j).getInfo().getType()==event.getType())
                {

                    if(forbiddenChecker(bricks.get(i).getInputEventPorts().get(j),event))		//forbiddenChecker
                    {

                        bricks.get(i).getInputEventPorts().get(j).inputEventPort(event);

                    }

                    break;
                }

                else if(enforcedChecker(bricks.get(i).getInputEventPorts().get(j),event))		//enforcedChecker
                {

                    bricks.get(i).getInputEventPorts().get(j).inputEventPort(event);
                    break;
                }



            }



        }


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




    public boolean forbiddenChecker(Port port,Event event)
    {
        String sourceBrick=event.getSource().getBrick();
        String sourceType=event.getSource().getEventType();

        String destinationBrick = port.getInfo().getId();
        String destinationType = port.getInfo().getType();
        boolean check =true;

        ArrayList<ForbiddenConnection> FCs=new ArrayList<ForbiddenConnection>();

        this.FCs=FCs;

        for(int i=0;i<getFCs().size();i++)
        {
            if(FCs.get(i).getSource().getBrick()==sourceBrick && FCs.get(i).getSource().getEventType()==sourceType)
            {
                if(FCs.get(i).getDestination().getBrick()==destinationBrick && FCs.get(i).getDestination().getEventType()==destinationType)
                {

                    check = false;

                }
            }
        }

        return check;
    }



    public boolean enforcedChecker(Port port,Event event)
    {

        String sourceBrick=event.getSource().getBrick();
        String sourceType=event.getSource().getEventType();

        String destinationBrick = port.getInfo().getId();
        String destinationType = port.getInfo().getType();

        boolean check =false;


        ArrayList<EnforcedConnection> ECs=new ArrayList<EnforcedConnection>();

        this.ECs=ECs;

        for(int i=0;i<getECs().size();i++)
        {
            if(ECs.get(i).getSource().getBrick()==sourceBrick && ECs.get(i).getSource().getEventType()==sourceType)
            {
                if(ECs.get(i).getDestination().getBrick()==destinationBrick && ECs.get(i).getDestination().getEventType()==destinationType)
                {

                    check = true;

                }
            }
        }


        return check;
    }



}
