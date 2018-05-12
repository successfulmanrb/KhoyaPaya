package com.myvision.khoyapaya.control.GameLevels;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
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
public class level14 extends Fragment {

    BroadcastReceiver receiver;
    IntentFilter intentFilter;
    private MusicIntentReceiver myReceiver;
    private static final String TAG = "Control";

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.level14, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myReceiver = new MusicIntentReceiver();
            }


    // Intent i = new Intent(getActivity(), LevelNo.class);

    //hsimg.setImageResource(R.drawable.level2happy);
    // startActivity(i);


    @Override
    public void onResume() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        getActivity().registerReceiver(myReceiver, filter);
        super.onResume();
    }

    class MusicIntentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                int state = intent.getIntExtra("state", -1);
                switch (state) {
                    case 0:
                        Log.d(TAG, "Headset is unplugged");
                        break;
                    case 1:{getActivity().unregisterReceiver(myReceiver);
                        Log.d(TAG, "Headset is plugged");
                        if (Control.leveltime.getBoolean("is_first_time_level14", true)) {
                            Control.levellock.edit().putInt("lock", 15).apply();
                            //the app is being launched for first time, do something
                            Log.d("TAG", "First time");
                            int cointemp;
                            cointemp = Control.coin.getInt("coin", 0) + 10;
                            Control.coin.edit().putInt("coin", cointemp).apply();
                            Control.coinv.setText(String.valueOf(Control.coin.getInt("coin", 0)));
                            ((Control) getActivity()).oncrose(15);


                            // first time task

                            // record the fact that the app has been started at least once
                            Control.leveltime.edit().putBoolean("is_first_time_level14", false).apply();
                        } else {
                            ((Control) getActivity()).oncrose(15);

                            Toast.makeText(getActivity(), "Coin provided 1st time only", Toast.LENGTH_SHORT).show();
                            //second time launch..
                        }
                    }
                        break;
                    default:
                        Log.d(TAG, "I have no idea what the headset state is");
                }
            }
        }
    }

    @Override
    public void onPause() {
       // getActivity().unregisterReceiver(myReceiver);
        super.onPause();
    }
}
