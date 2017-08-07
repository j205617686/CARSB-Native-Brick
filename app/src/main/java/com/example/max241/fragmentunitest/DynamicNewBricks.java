package com.example.max241.fragmentunitest;

import com.example.max241.fragmentunitest.AppView;
import com.example.max241.fragmentunitest.BrickInterface;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DynamicNewBricks {


    String packageStr;

    AppView view=new AppView();

    public AppView getAppView() {
        return view;
    }

    public void setAppView(AppView view) {
        this.view = view;
    }


    public DynamicNewBricks(String packageStr)
    {
        this.packageStr=packageStr;


    }

    public AppView DynamicNewBricks(String jsonstr) throws ParseException
    {
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(jsonstr);
        JSONObject jsonObject =  (JSONObject) obj;

        JSONArray application = (JSONArray) jsonObject.get("application");
        jsonObject = (JSONObject) application.get(0);

        JSONObject jsonview = (JSONObject) jsonObject.get("view");
        JSONArray jsonbricks = (JSONArray) jsonview.get("bricks");

        System.out.println(jsonbricks.toJSONString());

        AppView view=new AppView();


        ArrayList<BrickInterface> bricksnew=new ArrayList<BrickInterface>();

        for(int i=0;i<jsonbricks.size();i++)
        {
            JSONObject tempbricks = (JSONObject) jsonbricks.get(i);


            String tempbrickId = tempbricks.get("id").toString();

            tempbrickId= tempbrickId.split("_")[1];



            tempbrickId = packageStr + tempbrickId;
            System.out.println(tempbrickId);
			
			/*
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("js");


			Object result ="new "+tempbrickId+"(\""+tempbrickId+"\",\""+tempbrickId+view.getId()+"\")";

			bricks.add((Brick) engine.eval(result.toString()));
			System.out.println(result.toString());
			*/




            ArrayList<resourceBinding> data=new ArrayList<resourceBinding>();
            JSONArray bricksRB = (JSONArray) tempbricks.get("resourceBinding");


            for(int j=0;j<bricksRB.size();j++)
            {
                JSONObject tempjson = (JSONObject)bricksRB.get(j);


                JSONObject tempCSR =  (JSONObject)tempjson.get("componentServiceReplacement");


                resourceBinding tmpdata=new resourceBinding(tempjson.get("defaultResourceUrl").toString(),new ComponentServiceReplacement(tempCSR.get("task").toString(),tempCSR.get("url").toString()), tempjson.get("action").toString(), tempjson.get("type").toString());


                data.add(tmpdata);
            }



            //BrickInterface brickRequest=new BrickInterface(tempbrickId,tempbrickId+view.getId(),data);
            //bricks.add(brickRequest);


            try {
                BrickInterface brick = null;

                //brick = (BrickInterface)Class.forName(tempbrickId).getConstructor(String.class,String.class,ArrayList.class).newInstance(tempbrickId,tempbrickId+view.getId(),data);


                brick = (BrickInterface)Class.forName(tempbrickId).newInstance();



                brick.init(tempbrickId,tempbrickId+view.getId(),data);


                bricksnew.add(brick);


            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }






            data.clear();



        }


        view.setBricks(bricksnew);
        view.setECs(null);
        view.setFCs(null);



        for(int i=0;i<view.getBricks().size();i++)
        {
            view.getBricks().get(i).setAppView(view);
        }

        return view;



    }



}
