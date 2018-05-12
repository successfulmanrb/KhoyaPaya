package com.myvision.khoyapaya.number.NumberLevels;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.number.Number;

import java.util.Random;

/**
 * Created by dell-pc on 04/20/2017.
 */

public class No_Sum extends Fragment {
    TextView t1,t3,t5;
    Button b1,b2,b3,b4,b5,b6;
    Random ran=new Random();
    int n,but=6,no1,no2,ans;
    int[] no=new int[but];
    int count=0;
    String space="   ";
    int index_no1,index_no2;
    int ano1,ano2,sum=0;
    // button next for setting values in the button
    public void button_next(int n)
    {
        if(b1.getText().toString().equals(""))
        {
            b1.setText(String.valueOf(n));
        }
        else if(b2.getText().toString().equals(""))
        {
            b2.setText(String.valueOf(n));
        }
        else if(b3.getText().toString().equals(""))
        {
            b3.setText(String.valueOf(n));
        }
        else if(b4.getText().toString().equals(""))
        {
            b4.setText(String.valueOf(n));
        }
        else if(b5.getText().toString().equals(""))
        {
            b5.setText(String.valueOf(n));
        }
        else if(b6.getText().toString().equals(""))
        {
            b6.setText(String.valueOf(n));
        }
    }
    // on button click---intializing values to text view
    public void button_cli(View v) {
        int button_id = v.getId();
        switch (button_id) {
            case R.id.button11: {
                if (t1.getText().toString().equals(space)) {
                    t1.setText(b1.getText().toString());
                } else if (t3.getText().toString().equals("   ")) {
                    t3.setText(b1.getText().toString());
                }
                calc();
                break;
            }
            case R.id.button15: {
                if (t1.getText().toString().equals(space)) {
                    t1.setText(b2.getText().toString());
                } else if (t3.getText().toString().equals(space)) {
                    t3.setText(b2.getText().toString());
                }
                calc();
                break;
            }
            case R.id.button16: {
                if (t1.getText().toString().equals(space)) {
                    t1.setText(b3.getText().toString());
                } else if (t3.getText().toString().equals(space)) {
                    t3.setText(b3.getText().toString());
                }
                calc();
                break;
            }
            case R.id.button17: {
                if (t1.getText().toString().equals(space)) {
                    t1.setText(b4.getText().toString());
                } else if (t3.getText().toString().equals(space)) {
                    t3.setText(b4.getText().toString());
                }
                calc();
                break;
            }
            case R.id.button18: {
                if (t1.getText().toString().equals(space)) {
                    t1.setText(b5.getText().toString());
                } else if (t3.getText().toString().equals(space)) {
                    t3.setText(b5.getText().toString());
                }
                calc();
                break;
            }
            case R.id.button19: {
                if (t1.getText().toString().equals(space)) {
                    t1.setText(b6.getText().toString());
                } else if (t3.getText().toString().equals(space)) {
                    t3.setText(b6.getText().toString());
                }
                calc();
                break;
            }

        }
    }
    // calc for getting right answer or wrong answer
    public void calc()
    {
        if(!t1.getText().equals(space) && !t3.getText().equals(space))
        {
            ano1=Integer.parseInt(t1.getText().toString());
            ano2=Integer.parseInt(t3.getText().toString());
            // sum=ano1+ano2;
            sum=Integer.parseInt(t5.getText().toString());
            if((ano1+ano2)==sum){
                //if((t1.getText().equals(String.valueOf(no1)) && t3.getText().equals(String.valueOf(no2)))) {
                Toast.makeText(getActivity(),"right answer",Toast.LENGTH_SHORT).show();

                if(count<4) {
                    button_set();
                    count++;
                }
                else
                {
                    if (Number.leveltime.getBoolean("is_first_time_number_level_5", true)) {
                         Number.life.edit().putBoolean("opencontrol",true).apply();
                        Number.levellock.edit().putInt("number_level_Isunlock" , 6).apply();
                        //the app is being launched for first time, do something
                        Log.d("TAG", "First time");
                        int cointemp;
                        cointemp= Number.coin.getInt("coin",0)+10;
                        Number.coin.edit().putInt("coin",cointemp).apply();
                        Number.coinv.setText(String.valueOf(Number.coin.getInt("coin",0)));
                        ((Number)getActivity()).oncrose(6);





                        // first time task

                        // record the fact that the app has been started at least once
                        Number.leveltime.edit().putBoolean("is_first_time_number_level_5", false).apply();
                    }
                    else
                    {((Number)getActivity()).oncrose(6);
                        Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                        //second time launch..
                    }
                }
            }
            else {
                Toast.makeText(getActivity(), "wrong answer", Toast.LENGTH_SHORT).show();
                ((Number)getActivity()).onwrong(1);
                ((Number)getActivity()).onretry(5);

            }
        }

    }


    // button set function for intializing values to button
    public void button_set(){
        //Toast.makeText(getActivity(),"button set called",Toast.LENGTH_SHORT).show();
        b1.setText("");
        b2.setText("");
        b3.setText("");
        b4.setText("");
        b5.setText("");
        b6.setText("");
        t1.setText(space);
        t3.setText(space);
        for(int i=0;i<but;i++) {
            n = ran.nextInt(9 - 0) + 0;
            no[i]=n;
            button_next(n);
        }
        index_no1=ran.nextInt(no.length);
        index_no2=ran.nextInt(no.length);
        while (index_no1==index_no2)
            index_no2=ran.nextInt(no.length);
        no1=no[index_no1];
        no2=no[index_no2];
        ans=no1+no2;
        t5.setText(String.valueOf(ans));
       /* for(int i=0;i<but;i++)
        {
            if(i==index_no1 && i==index_no2)
            {
                no[i]=-1;
            }
        }
        int sum=0;
        for(int i=0;i<but;i++)
        {
            if(no[i]!=-1)
            {
                sum=sum+no[i];
            }
        }
        if(sum==ans)
        {
            button_set();
        }*/
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.number_no_sum, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        t1=(TextView)getActivity().findViewById(R.id.textView27);
        t3=(TextView)getActivity().findViewById(R.id.textView29);
        t5=(TextView)getActivity().findViewById(R.id.textView31);
        b1=(Button)getActivity().findViewById(R.id.button11);
        b2=(Button)getActivity().findViewById(R.id.button15);
        b3=(Button)getActivity().findViewById(R.id.button16);
        b4=(Button)getActivity().findViewById(R.id.button17);
        b5=(Button)getActivity().findViewById(R.id.button18);
        b6=(Button)getActivity().findViewById(R.id.button19);
        button_set();
    }
    }

