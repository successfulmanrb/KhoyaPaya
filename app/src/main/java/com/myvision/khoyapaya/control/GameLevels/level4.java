package com.myvision.khoyapaya.control.GameLevels;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.control.Control;

/**
 * Created by Rahul BANSAL on 2/18/2017.
 */
public class level4 extends Fragment {
ImageView neede;
    ImageView neede2;
    TextView level4tv;
     Handler handler1;
    Runnable r;
    boolean a;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.level4, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        neede=(ImageView)getActivity().findViewById(R.id.neede1);
        handler1 = new Handler();

        //neede2=(ImageView)getActivity().findViewById(R.id.neede2);
        level4tv=(TextView)getActivity().findViewById(R.id.level4tv);
        a=isConnected(getActivity());
        if(a) {
            //neede.setVisibility(View.GONE);
           // neede2.setVisibility(View.VISIBLE);
            neede.setImageResource(R.drawable.level4neede2);
            level4tv.setText("Power Activated ");

            //Intent i = new Intent(getActivity(), LevelNo.class);
            if (Control.leveltime.getBoolean("is_first_time_level4", true)) {
                Control.levellock.edit().putInt("lock", 5).apply();
                //the app is being launched for first time, do something
                Log.d("TAG", "First time");
                int cointemp;
                cointemp= Control.coin.getInt("coin",0)+10;
                Control.coin.edit().putInt("coin",cointemp).apply();
                Control.coinv.setText(String.valueOf(Control.coin.getInt("coin",0)));
                ((Control)getActivity()).oncrose(5);

                //Toast.makeText(getActivity(),"connected " , Toast.LENGTH_SHORT).show();


                // first time task

                // record the fact that the app has been started at least once
                Control.leveltime.edit().putBoolean("is_first_time_level4", false).apply();
                //startActivity(i);
            } else {((Control)getActivity()).oncrose(5);
                Toast.makeText(getActivity(), "Coin provided 1st time only", Toast.LENGTH_SHORT).show();
                //second time launch..
            }

            //hsimg.setImageResource(R.drawable.level2happy);

        }else{ doTheAutoRefresh();}
    }

    private void doTheAutoRefresh() { r =new Runnable() {
        @Override
        public void run() {
            a=isConnected(getActivity());
            if(a) {//neede.setVisibility(View.GONE);
               // neede2.setVisibility(View.VISIBLE);
                neede.setImageResource(R.drawable.level4neede2);
                level4tv.setText("Power Activated ");
                level4tv.setTextColor(Color.GREEN);
                Control.coinv.setText(String.valueOf(Control.coin.getInt("coin",0)));
                ((Control)getActivity()).oncrose(5);

                //Toast.makeText(getActivity(),"connected " , Toast.LENGTH_SHORT).show();
            }
            else{doTheAutoRefresh();}

        }
    }; handler1.postDelayed(r,500);

    }

    @Override
    public void onStop() {
        super.onStop();
        handler1.removeCallbacks(r);
    }


    public static boolean isConnected(Context context) {
            Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        assert intent != null;
        int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            return plugged == BatteryManager.BATTERY_PLUGGED_AC || plugged == BatteryManager.BATTERY_PLUGGED_USB;

    }
}
