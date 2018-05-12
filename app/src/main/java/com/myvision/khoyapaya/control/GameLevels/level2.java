package com.myvision.khoyapaya.control.GameLevels;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.control.Control;

/**
 * Created by Rahul BANSAL on 2/18/2017.
 */
public class level2 extends Fragment {
    LinearLayout l;
    ImageView hsimg ,hsimghappy;
    TextView t;
    Handler handler1;

    Context context;
    Runnable r;


    //Content resolver used as a handle to the system's settings
    private ContentResolver cResolver;

    //Window object, that will store a reference to the current window
    private Window window;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.level2, container, false);
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        handler1 = new Handler();
        //Get the content resolver
        cResolver = getActivity().getContentResolver();

        //Get the current window
        window = getActivity().getWindow();


        hsimg=(ImageView)getActivity().findViewById(R.id.hsimg);
        hsimghappy=(ImageView)getActivity().findViewById(R.id.hsimghappy);
        // coin.edit().putInt("coin" , 10).apply();


        float curBrightnessValue = 0;
        try {
            curBrightnessValue = Settings.System.getInt(
                    getActivity().getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        int screen_brightness = (int) curBrightnessValue;
        //Toast.makeText(Main2Activity.this,String.valueOf(screen_brightness), Toast.LENGTH_SHORT).show();
        if (screen_brightness > 240) {
            //Settings.System.putInt(getActivity().getContentResolver(),Settings.System.SCREEN_BRIGHTNESS,220);

           /* Intent i = new Intent(getActivity(), LevelNo.class);
            if (leveltime.getBoolean("is_first_time_leve2", true)) {
                levellock.edit().putInt("lock" , 3).apply();
                //the app is being launched for first time, do something
                Log.d("TAG", "First time");
                coin.edit().putInt("coin",  + 20).commit();

                // first time task

                // record the fact that the app has been started at least once
                leveltime.edit().putBoolean("is_first_time_leve2", false).commit();
            }
            else
            {
                Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                //second time launch..
            }

            //hsimg.setImageResource(R.drawable.level2happy);
            startActivity(i);

            // l.setBackgroundColor(Color.GREEN);
            // t.setVisibility(View.VISIBLE);
            // hsimg.setImageResource(R.drawable.level2happy);

            //  coinv.setText(String.valueOf(coin.getInt("coin", 0)));

        }*/
        doTheAutoRefresh();

    }else{doTheAutoRefresh();}}
    private void doTheAutoRefresh() {
        r =new Runnable() {
            @Override
            public void run() {
                float curBrightnessValue = 0;
                try {
                    curBrightnessValue = Settings.System.getInt(
                            getActivity().getContentResolver(),
                            Settings.System.SCREEN_BRIGHTNESS);
                } catch (Settings.SettingNotFoundException e) {
                    e.printStackTrace();
                }

                int screen_brightness = (int) curBrightnessValue;
                //Toast.makeText(Main2Activity.this,String.valueOf(screen_brightness), Toast.LENGTH_SHORT).show();
                if (screen_brightness >=250) {

                   // Intent i = new Intent(getActivity(), LevelNo.class);
                  //  handler1.removeCallbacks(r);

                    if (Control.leveltime.getBoolean("is_first_time_level2", true)) {
                        //the app is being launched for first time, do something
                        Control.levellock.edit().putInt("lock" , 3).apply();
                        Log.d("TAG", "First time");
                        int cointemp;
                        cointemp= Control.coin.getInt("coin",0)+10;
                        Control.coin.edit().putInt("coin",cointemp).apply();
                        Control.coinv.setText(String.valueOf(Control.coin.getInt("coin",0)));
                        ((Control)getActivity()).oncrose(3);


                        // first time task

                        // record the fact that the app has been started at least once
                        Control.leveltime.edit().putBoolean("is_first_time_level2", false).apply();
                    }
                    else
                    { ((Control)getActivity()).oncrose(3);
                        Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                        //second time launch..
                    }


                    // coinv.setText(String.valueOf(coin.getInt("coin", 0)));

hsimg.setVisibility(View.GONE);
hsimghappy.setVisibility(View.VISIBLE);
//  startActivity(i);


                    //  l.setBackgroundColor(Color.GREEN);
                    // t.setVisibility(View.VISIBLE);
                }
                // Write code for your refresh logic
                else{doTheAutoRefresh();}
            }
        }; handler1.postDelayed(r,500);

    }

    @Override
    public void onStop() {
        super.onStop();
        handler1.removeCallbacks(r);
    }
}



