package com.myvision.khoyapaya.control.GameLevels;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.control.Control;

/**
 * Created by Rahul BANSAL on 2/19/2017.
 */
public class level3 extends Fragment {
Button b;
    EditText e;
   // TextView coinv;




    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.level3, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getBatteryPercentage();
        e=(EditText)getActivity().findViewById(R.id.edtv);
        b=(Button)getActivity().findViewById(R.id.bttv);

    }

    void getBatteryPercentage( ) {
        BroadcastReceiver batteryreceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                context.unregisterReceiver(this);
                int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int tempLevel = 0;
                if(currentLevel > 0 && scale > 0) {
                    //currentLevel=((currentLevel*100)/100);
                    final int finalCurrentLevel = currentLevel;
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String r;
                            r=e.getText().toString();
                            if( r.equals(String.valueOf(finalCurrentLevel)))
                            {
                               // Intent i = new Intent(getActivity(), LevelNo.class);
                                if (Control.leveltime.getBoolean("is_first_time_level3", true)) {
                                    Control.levellock.edit().putInt("lock" , 4).apply();
                                    //the app is being launched for first time, do something
                                    Log.d("TAG", "First time");
                                    int cointemp;
                                    cointemp= Control.coin.getInt("coin",0)+10;
                                    Control.coin.edit().putInt("coin",cointemp).apply();
                                    Control.coinv.setText(String.valueOf(Control.coin.getInt("coin",0)));
                                    ((Control)getActivity()).oncrose(4);


                                    // first time task

                                    // record the fact that the app has been started at least once
                                    Control.leveltime.edit().putBoolean("is_first_time_level3", false).apply();
                                }
                                else
                                {
                                    ((Control)getActivity()).oncrose(4);

                                    Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                                    //second time launch..
                                }

                                //hsimg.setImageResource(R.drawable.level2happy);
                               // startActivity(i);


                            }

                        }
                    });

                }
            }
        };
        IntentFilter batteryFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
       getActivity().registerReceiver(batteryreceiver, batteryFilter);
    }

}
