package com.example.max241.fragmentunitest.Music;

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
import com.example.max241.fragmentunitest.AppView;
import com.example.max241.fragmentunitest.BrickInterface;
import com.example.max241.fragmentunitest.DynamicNewBricks;
import com.example.max241.fragmentunitest.Event;
import com.example.max241.fragmentunitest.EventEngine;
import com.example.max241.fragmentunitest.FragmentCommunicator;
import com.example.max241.fragmentunitest.R;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MusicActivity extends FragmentActivity {


    FragmentManager manager;
    AppView view = new AppView();
    EventEngine ee;
    ArrayList<BrickInterface> fragmentlist = new ArrayList<BrickInterface>();
    Thread thread1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ab);



        Context context = getApplication();

        CharSequence text1 = "Load JSON";      

        int duration1 = Toast.LENGTH_SHORT;   

        Toast toast1 = Toast.makeText(context, text1, duration1); 

        toast1.show();


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
            InputStream is = getResources().getAssets().open("compositeBrickSimple.json");					//此處填寫JSON檔名稱
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonstr = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();

        }


        DynamicNewBricks DNB = new DynamicNewBricks(this.getBaseContext().getPackageName()+".Music.");		//此處填寫此CompositeBrick的名稱



        try {
            view = DNB.DynamicNewBricks(jsonstr);

        } catch (Exception e) {

            e.printStackTrace();
        }


        for (int i = 0; i < view.getBricks().size(); i++) {
            view.getBricks().get(i).setAppView(view);

            fragmentTransaction.add(R.id.fragmentlayout, (Fragment) view.getBricks().get(i));


        }


        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }





int looptime=0;
    @Override
    protected void onStart(){
        super.onStart();

        Context context = getApplication();

        CharSequence text1 = "Send Event";     

        int duration1 = Toast.LENGTH_SHORT;   


        final Toast toast1 = Toast.makeText(context, text1, duration1); 


        ee = new EventEngine(view);

       



        for(int i=0;i<view.getBricks().size();i++) {

            view.getBricks().get(i).setFragmentListener(new FragmentCommunicator() {

                @Override
                public void sendEvent(ArrayList<Event> allEvent) {

                    toast1.show();


                    for (int i = 0; i < view.getBricks().get(i).getOutputEventPorts().size(); i++)   
                    {
                            ee.eventEngine((ArrayList) manager.getFragments(), allEvent.get(i));

                    }
                    looptime++;
                    System.out.println(looptime);

                }
            });


        }

    }



@Override
protected void onPause(){
    super.onPause();


    thread1.interrupt();




}


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            ConfirmExit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void ConfirmExit(){
        AlertDialog.Builder ad=new AlertDialog.Builder(MusicActivity.this);
        ad.setTitle("離開");
        ad.setMessage("確定要離開?");
        ad.setPositiveButton("是", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
               
               MusicActivity.this.finish();
                thread1.interrupt();

            }
        });
        ad.setNegativeButton("否",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                
            }
        });
        ad.show();
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
            
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value", "请求结果");
            msg.setData(data);
            handler.sendMessage(msg);
        }


    };







}




