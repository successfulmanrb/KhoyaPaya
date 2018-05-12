package com.myvision.khoyapaya.control.GameLevels;

import android.app.Fragment;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.control.Control;

/**
 * Created by Rahul BANSAL on 3/10/2017.
 */
public class level8 extends Fragment {
    int unlock=0;
    ImageView jail , home , thief , prisnor;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.level8 , container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        jail=(ImageView)getActivity().findViewById(R.id.jail);
        home=(ImageView)getActivity().findViewById(R.id.house);
        thief=(ImageView)getActivity().findViewById(R.id.thief);
        prisnor=(ImageView)getActivity().findViewById(R.id.prisoner);

        if(unlock==1)
            Toast.makeText(getActivity(),"unlock",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getActivity(),"lock screen ",Toast.LENGTH_SHORT).show();
        KeyguardManager myKM = (KeyguardManager) getActivity().getSystemService(Context.KEYGUARD_SERVICE);
        if( myKM.inKeyguardRestrictedInputMode()) {
            unlock=1;
        } else {
            //it is not locked
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        if(unlock==1)
        {
            Toast.makeText(getActivity(),"unlock",Toast.LENGTH_SHORT).show();
            thief.setVisibility(View.GONE);
            home.setVisibility(View.GONE);
            jail.setVisibility(View.VISIBLE);
            prisnor.setVisibility(View.VISIBLE);

            if (Control.leveltime.getBoolean("is_first_time_level8", true)) {

                //the app is being launched for first time, do something
                Control.levellock.edit().putInt("lock" , 9).apply();
                Log.d("TAG", "First time");
                int cointemp;
                cointemp= Control.coin.getInt("coin",0)+10;
                Control.coin.edit().putInt("coin",cointemp).apply();
                Control.coinv.setText(String.valueOf(Control.coin.getInt("coin",0)));
                ((Control)getActivity()).oncrose(9);

                //Toast.makeText(getActivity(),"connected " , Toast.LENGTH_SHORT).show();


                // first time task

                // record the fact that the app has been started at least once
                Control.leveltime.edit().putBoolean("is_first_time_level8", false).apply();

            }
            else
            {((Control)getActivity()).oncrose(9);
                Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                //second time launch..
            }

        }
        else
            Toast.makeText(getActivity(),"lock screen ",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPause() {
        super.onPause();
        PowerManager powerManager = (PowerManager) getActivity().getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            isScreenOn = powerManager.isInteractive();
        } else {
            isScreenOn = powerManager.isScreenOn();
        }

        if (!isScreenOn) {
            unlock=1;
            Toast.makeText(getActivity(),"act pauss",Toast.LENGTH_SHORT).show();
            // The screen has been locked
            // do stuff...
        }
    }
}
