package com.myvision.khoyapaya.number.NumberLevels;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.number.Number;

/**
 * Created by dell-pc on 04/21/2017.
 */

public class Prime extends Fragment {
    TextView opt1,opt2,opt3,opt4;

    //ans = (ImageView) getActivity().findViewById(R.id.imageView3);
    //opt1 = (ImageView) getActivity().findViewById(R.id.imageView4);
    public void textcli(View v)
    {
        int id=v.getId();
        switch (id)
        {
            case R.id.textView4:
            {
                Toast.makeText(getActivity(),"right answer",Toast.LENGTH_SHORT).show();
                if (Number.leveltime.getBoolean("is_first_time_number_level_7", true)) {

                    Number.levellock.edit().putInt("number_level_Isunlock" , 8).apply();
                    //the app is being launched for first time, do something
                    Log.d("TAG", "First time");
                    int cointemp;
                    cointemp= Number.coin.getInt("coin",0)+10;
                    Number.coin.edit().putInt("coin",cointemp).apply();
                    Number.coinv.setText(String.valueOf(Number.coin.getInt("coin",0)));
                    ((Number)getActivity()).oncrose(8);





                    // first time task

                    // record the fact that the app has been started at least once
                    Number.leveltime.edit().putBoolean("is_first_time_number_level_7", false).apply();
                }
                else
                {((Number)getActivity()).oncrose(8);
                    Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                    //second time launch..
                }
            }
                break;
            default: {
                Toast.makeText(getActivity(), "wrong answer", Toast.LENGTH_SHORT).show();
                ((Number)getActivity()).onwrong(1);
                ((Number)getActivity()).onretry(7);
            }
        }
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.number_prime, container, false);
    }
    //ans = (ImageView) getActivity().findViewById(R.id.imageView3);
    //opt1 = (ImageView) getActivity().findViewById(R.id.imageView4);
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        opt1=(TextView)getActivity().findViewById(R.id.textView4);
        opt2=(TextView)getActivity().findViewById(R.id.textView5);
        opt3=(TextView)getActivity().findViewById(R.id.textView6);
        opt4=(TextView)getActivity().findViewById(R.id.textView7);

    }
}
