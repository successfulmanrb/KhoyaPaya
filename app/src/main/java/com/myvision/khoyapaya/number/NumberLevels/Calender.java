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

import static com.myvision.khoyapaya.control.Control.coin;
import static com.myvision.khoyapaya.control.Control.coinv;

/**
 * Created by Rahul BANSAL on 4/5/2017.
 */
public class Calender extends Fragment {
    TextView ans;

    public void button_press(View v)
    {
        int id=v.getId();
        switch (id)
        {
            case R.id.button20: {
                int n=Integer.parseInt(ans.getText().toString());
                if(n==0)
                {

                }
                else{
                    ans.setText(""+(n-1));
                }
                break;
            }
            case R.id.button21: {
                int n=Integer.parseInt(ans.getText().toString());
                ans.setText(""+(n+1));
                break;
            }
            case R.id.button22: {
                if(Integer.parseInt(ans.getText().toString()) == 12)
                { Toast.makeText(getActivity(),"right answer",Toast.LENGTH_SHORT).show();
                    if (Number.leveltime.getBoolean("is_first_time_number_level_2", true)) {

                        Number.levellock.edit().putInt("number_level_Isunlock" , 3).apply();
                        //the app is being launched for first time, do something
                        Log.d("TAG", "First time");
                        int cointemp;
                        cointemp= Number.coin.getInt("coin",0)+10;
                        Number.coin.edit().putInt("coin",cointemp).apply();
                        Number.coinv.setText(String.valueOf(Number.coin.getInt("coin",0)));
                        ((Number)getActivity()).oncrose(3);





                        // first time task

                        // record the fact that the app has been started at least once
                        Number.leveltime.edit().putBoolean("is_first_time_number_level_2", false).apply();
                    }
                    else
                    {((Number)getActivity()).oncrose(3);
                        Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                        //second time launch..
                    }


                }
                else {
                    Toast.makeText(getActivity(), "wrong answer", Toast.LENGTH_SHORT).show();
                    ((Number)getActivity()).onwrong(1);
                    ((Number)getActivity()).onretry(2);
                }
                break;
            }
        }
    }


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.number_calendar, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        ans=(TextView)getActivity().findViewById(R.id.textView37);
        //ans.setText("0");

    }

}
