package com.example.max241.fragmentunitest.Calories;

import android.app.*;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.app.*;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.max241.fragmentunitest.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CaloriesActivity extends FragmentActivity {


    FragmentManager manager;
    AppView view = new AppView();
    EventEngine ee;
    ArrayList<BrickInterface> fragmentlist = new ArrayList<BrickInterface>();
    Thread thread1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ab);



        tToast("Load JSON(onCreate)");

        thread1=new Thread(runnable);

        thread1.start();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());


        manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();

        LinearLayout ll = (LinearLayout) findViewById(R.id.fragmentlayout);
        ll.removeAllViews();
        fragmentlist.clear();


        String jsonstr = "";


        try {
            InputStream is = getResources().getAssets().open("compositeBrickCalories.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonstr = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();

        }

        DynamicNewBricks DNB = new DynamicNewBricks(this.getBaseContext().getPackageName()+".Calories.");



        try {
            view = DNB.DynamicNewBricks(jsonstr);

        } catch (Exception e) {

            e.printStackTrace();
        }


            for (int i = 0; i < view.getBricks().size(); i++) {
                view.getBricks().get(i).setAppView(view);
                //ll.addView(view.getBricks().get(i).generateElement());
                fragmentTransaction.add(R.id.fragmentlayout, (Fragment) view.getBricks().get(i));


            }


            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();





    }



int looptime=0;
    //ArrayList<Port> returnPort=new ArrayList<Port>();


    @Override
    protected void onPause(){
        super.onPause();

        tToast("onPause");
        thread1.interrupt();



    }



    @Override
    protected void onResume(){
        super.onResume();

        tToast("onResume");
        thread1.run();



    }

    @Override
    protected void onStart(){
        super.onStart();


        ee = new EventEngine(view);

        Log.v("SIZZZZZZZZZZZZZZZZZ", view.getBricks().toString());



        for(int i=0;i<view.getBricks().size();i++) {

            view.getBricks().get(i).setFragmentListener(new FragmentCommunicator() {

                @Override
                public void sendEvent(ArrayList<Event> allEvent) {

                    tToast("send Event");

                    for (int i = 0; i < view.getBricks().get(i).getOutputEventPorts().size(); i++)   //將所有event送入eventEngine做比對
                    {


                            ee.eventEngine((ArrayList) manager.getFragments(), allEvent.get(i));
                            looptime++;
                            System.out.println(looptime);



                    }


                }
            });


        }

    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {//捕捉返回鍵
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            ConfirmExit();//按返回鍵，則執行退出確認
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void ConfirmExit(){//退出確認
        AlertDialog.Builder ad=new AlertDialog.Builder(CaloriesActivity.this);
        ad.setTitle("離開");
        ad.setMessage("確定要離開?");
        ad.setPositiveButton("是", new DialogInterface.OnClickListener() {//退出按鈕
            public void onClick(DialogInterface dialog, int i) {
                // TODO Auto-generated method stub
                CaloriesActivity.this.finish();//關閉activity
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        ad.setNegativeButton("否",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                //不退出不用執行任何操作
            }
        });
        ad.show();//示對話框
    }








    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果-->" + val);
        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //
            // TODO: http request.
            //
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value", "请求结果");
            msg.setData(data);
            handler.sendMessage(msg);
        }


    };



    private void tToast(String text1) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast1 = Toast.makeText(context, text1, duration); //建立物件

        toast1.show();
    }



}




