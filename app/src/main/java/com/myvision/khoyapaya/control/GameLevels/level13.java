package com.myvision.khoyapaya.control.GameLevels;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.control.Control;

/**
 * Created by Rahul BANSAL on 2/19/2017.
 */
public class level13 extends Fragment {

    BroadcastReceiver receiver;
    IntentFilter intentFilter;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.level13, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

         intentFilter = new
                IntentFilter("android.intent.action.AIRPLANE_MODE");


         receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("AirplaneMode", "Service state changed");
                boolean isAirplaneMode = isAirplaneModeOn(context);
                if(isAirplaneMode)
                { Toast.makeText(getActivity(),"on",Toast.LENGTH_SHORT).show();
                    if (Control.leveltime.getBoolean("is_first_time_level13", true)) {
                        Control.levellock.edit().putInt("lock" , 14).apply();
                        //the app is being launched for first time, do something
                        Log.d("TAG", "First time");
                        int cointemp;
                        cointemp= Control.coin.getInt("coin",0)+10;
                        Control.coin.edit().putInt("coin",cointemp).apply();
                        Control.coinv.setText(String.valueOf(Control.coin.getInt("coin",0)));
                        ((Control)getActivity()).oncrose(14);


                        // first time task

                        // record the fact that the app has been started at least once
                        Control.leveltime.edit().putBoolean("is_first_time_level13", false).apply();
                    }
                    else
                    {
                        ((Control)getActivity()).oncrose(14);

                        Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                        //second time launch..
                    }
                }
                else
                    Toast.makeText(getActivity(),"close",Toast.LENGTH_SHORT).show();

            }
        };




        // Intent i = new Intent(getActivity(), LevelNo.class);

                                //hsimg.setImageResource(R.drawable.level2happy);
                               // startActivity(i);


                            }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(receiver, intentFilter);
    }

    private static boolean isAirplaneModeOn(Context context) {

        return Settings.System.getInt(context.getContentResolver(),
                Settings.System.AIRPLANE_MODE_ON, 0) != 0;

    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);

    }
}
