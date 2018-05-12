package com.myvision.khoyapaya.control.GameLevels;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.control.Control;

/**
 * Created by Rahul BANSAL on 2/17/2017.
 */
public class level1 extends Fragment {
    AudioManager audio;
    Dialog d;
    Handler handler;
    int currentVolume,maxVolume ;
   // static int count=1;
    Context context;
    Runnable r1;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.level1, container, false);
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        handler = new Handler();





       audio = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        maxVolume=audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
         Toast.makeText(getActivity(), String.valueOf(currentVolume), Toast.LENGTH_SHORT).show();


        if (currentVolume ==maxVolume)
        {audio.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume-5, 0);
            doTheAutoRefresh();


        }else{doTheAutoRefresh();}


    }


    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)){
            audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
            if(audio.getStreamVolume(AudioManager.STREAM_MUSIC)==maxVolume)
            {


                if (Control.leveltime.getBoolean("is_first_time_level1", true)) {

                    Control.levellock.edit().putInt("lock" , 2).apply();
                    //the app is being launched for first time, do something
                    Log.d("TAG", "First time");
                    int cointemp;
                    cointemp= Control.coin.getInt("coin",0)+10;
                    Control.coin.edit().putInt("coin",cointemp).apply();
                    Control.coinv.setText(String.valueOf(Control.coin.getInt("coin",0)));
                    ((Control)getActivity()).oncrose(2);





                    // first time task

                    // record the fact that the app has been started at least once
                    Control.leveltime.edit().putBoolean("is_first_time_level1", false).apply();
                }
                else
                {((Control)getActivity()).oncrose(2);
                    Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                    //second time launch..
                }


                }
        }
        return true;
    }
    private void doTheAutoRefresh() {
      r1=new Runnable() {
            @Override
            public void run() {

                try {
                    audio = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);


                } catch (Exception e) {
                    e.printStackTrace();
                }

                int   currentVolume1 = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
                //Toast.makeText(Main2Activity.this,String.valueOf(screen_brightness), Toast.LENGTH_SHORT).show();
                if(currentVolume1==maxVolume)
                {




                    if (Control.leveltime.getBoolean("is_first_time_level1", true)) {
                        //the app is being launched for first time, do something
                        Log.d("TAG", "First time");
                        Control.levellock.edit().putInt("lock" , 2).apply();
                        int cointemp;
                        cointemp= Control.coin.getInt("coin",0)+10;
                        Control.coin.edit().putInt("coin",cointemp).apply();
                        Control.coinv.setText(String.valueOf(Control.coin.getInt("coin",0)));
                        ((Control)getActivity()).oncrose(2);

                        // first time task

                        // record the fact that the app has been started at least once
                        Control.leveltime.edit().putBoolean("is_first_time_level1", false).apply();
                    }
                    else
                    { ((Control)getActivity()).oncrose(2);
                        Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                        //second time launch..
                    }


                }
                // Write code for your refresh logic
                else{doTheAutoRefresh();}
            }
        };
        handler.postDelayed(r1,500);


    }


    @Override
    public void onStop() {
        handler.removeCallbacks(r1);
        Log.d("2", "onDestroy: ");
        super.onStop();
    }


}
