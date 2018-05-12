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

import java.util.Random;

/**
 * Created by Rahul BANSAL on 4/5/2017.
 */


public class Operations extends Fragment {
    TextView selct_one,st1,st2,st3;
    String select[]={"Tap the biggest value","Tap the smallest value"};
    int count=1,max_count=6;
    int ranvalue,ranvalue1=0,ranvalue2=0,ranvalue3=0;
    int setVisible=1;
    boolean ans1,ans2,ans3;
    String stmt[]={"3-2","9-5","2+3","36/6","3+5","63/7","5*2","4*5"};
    Random ran=new Random();
    public void st_cli(View v){
        int id=v.getId();
        switch (id)
        {
            case R.id.textView33: {
                if (ans1){
                    Toast.makeText(getActivity(),"right answer",Toast.LENGTH_SHORT).show();
                    if(count<=max_count) {
                        set_text();
                    }
                }
                else {
                    Toast.makeText(getActivity(),"wrong answer",Toast.LENGTH_SHORT).show();
                    ((Number)getActivity()).onwrong(1);
                    ((Number)getActivity()).onretry(10);
                }
                break;
            }
            case R.id.textView34: {
                if (ans2){
                    Toast.makeText(getActivity(),"right answer",Toast.LENGTH_SHORT).show();
                    if(count<=max_count) {
                        set_text();
                    }
                }
                else {
                    Toast.makeText(getActivity(),"wrong answer",Toast.LENGTH_SHORT).show();
                    ((Number)getActivity()).onwrong(1);
                    ((Number)getActivity()).onretry(10);
                }
                break;
            }
            case R.id.textView35: {
                if (ans3){
                    Toast.makeText(getActivity(),"right answer",Toast.LENGTH_SHORT).show();
                    if(count<=max_count) {
                        set_text();
                    }
                }
                else {
                    Toast.makeText(getActivity(),"wrong answer",Toast.LENGTH_SHORT).show();
                    ((Number)getActivity()).onwrong(1);
                    ((Number)getActivity()).onretry(10);
                }
                break;
            }
        }
        if(count>max_count)
            if (Number.leveltime.getBoolean("is_first_time_number_level_10", true)) {

                Number.levellock.edit().putInt("number_level_Isunlock" , 11).apply();
                //the app is being launched for first time, do something
                Log.d("TAG", "First time");
                int cointemp;
                cointemp= Number.coin.getInt("coin",0)+10;
                Number.coin.edit().putInt("coin",cointemp).apply();
                Number.coinv.setText(String.valueOf(Number.coin.getInt("coin",0)));
                ((Number)getActivity()).oncrose(11);





                // first time task

                // record the fact that the app has been started at least once
                Number.leveltime.edit().putBoolean("is_first_time_number_level_10", false).apply();
            }
            else
            {((Number)getActivity()).oncrose(11);
                Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                //second time launch..
            }

    }
    public void set_text(){
        Toast.makeText(getActivity(),"value of count "+count,Toast.LENGTH_SHORT).show();
        if(count<4) {
            st3.setVisibility(View.INVISIBLE);
            setVisible=0;
        }
        else{
            st3.setVisibility(View.VISIBLE);
            setVisible=1;
        }
        ranvalue1=ran.nextInt(stmt.length);
        st1.setText(stmt[ranvalue1]);
        ranvalue2=ran.nextInt(stmt.length);
        while (ranvalue1==ranvalue2)
        {
            ranvalue2=ran.nextInt(stmt.length);
        }
        st2.setText(stmt[ranvalue2]);
        if(setVisible==1)
        {
            ranvalue3=ran.nextInt(stmt.length);
            while (ranvalue1==ranvalue3 || ranvalue2==ranvalue3)
            {
                ranvalue3=ran.nextInt(stmt.length);
            }
            st3.setText(stmt[ranvalue3]);
        }
        ranvalue=ran.nextInt(select.length);
        selct_one.setText(select[ranvalue]);
        if(ranvalue==0)
        {
            if(ranvalue1>ranvalue2)
            {
                if(ranvalue1>ranvalue3)
                {
                    ans1=true;
                }
                else
                {
                    ans3=true;
                }
            }
            else{
                if(ranvalue2>ranvalue3){
                    ans2=true;
                }
                else{
                    ans3=true;
                }
            }

        }
        else if(ranvalue==1)
        {
            if(setVisible==0)
            {
                if(ranvalue1<ranvalue2)
                {
                    ans1=true;
                }
                else
                {
                    ans2=true;
                }
            }
            else {
                if (ranvalue1 < ranvalue2 && ranvalue1 < ranvalue3) {
                    ans1 = true;
                } else if (ranvalue2 < ranvalue1 && ranvalue2 < ranvalue3) {
                    ans2 = true;
                } else {
                    ans3 = true;
                }
            }
        }
        count=count+1;
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.number_operations, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        selct_one=(TextView)getActivity().findViewById(R.id.textView32);
        st1=(TextView)getActivity().findViewById(R.id.textView33);
        st2=(TextView)getActivity().findViewById(R.id.textView34);
        st3=(TextView)getActivity().findViewById(R.id.textView35);
        set_text();



    }

}
