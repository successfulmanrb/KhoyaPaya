package com.myvision.khoyapaya.control.GameLevels;

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
import com.myvision.khoyapaya.control.Control;

/**
 * Created by Rahul BANSAL on 2/19/2017.
 */
public class level18 extends Fragment {
Button bminus , bplus , bcoat;
    TextView  tvno;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.level18, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bminus=(Button)getActivity().findViewById(R.id.bminus);
        bplus=(Button)getActivity().findViewById(R.id.bplus);
        bcoat=(Button)getActivity().findViewById(R.id.bcoat);
        bcoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvno.getText().toString().equals("2nd"))
                {  Toast.makeText(getActivity(),"correct",Toast.LENGTH_SHORT).show();
                    if (Control.leveltime.getBoolean("is_first_time_level18", true)) {
                        Control.levellock.edit().putInt("lock" , 19).apply();
                        //the app is being launched for first time, do something
                        Log.d("TAG", "First time");
                        int cointemp;
                        cointemp= Control.coin.getInt("coin",0)+10;
                        Control.coin.edit().putInt("coin",cointemp).apply();
                        Control.coinv.setText(String.valueOf(Control.coin.getInt("coin",0)));
                        ((Control)getActivity()).oncrose(19);


                        // first time task

                        // record the fact that the app has been started at least once
                        Control.leveltime.edit().putBoolean("is_first_time_level18", false).apply();
                    }
                    else
                    {
                        ((Control)getActivity()).oncrose(19);

                        //Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                        //second time launch..
                    }}
                else
                {Toast.makeText(getActivity(),"wrong",Toast.LENGTH_SHORT).show();}
            }
        });
        tvno=(TextView)getActivity().findViewById(R.id.tvno);
        bminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* int n=Integer.parseInt(tvno.getText().toString());
                if(n==0)
                {}
                else{tvno.setText(""+(n-1));


                }*/
                if(tvno.getText().toString().equals("0"))
                {}
                else{//int n=Integer.parseInt(tvno.getText().toString());
                    if(tvno.getText().toString().equals("1st"))
                tvno.setText("0");
                    if(tvno.getText().toString().equals("2nd"))
                        tvno.setText("1st");
                    if(tvno.getText().toString().equals("3rd"))
                        tvno.setText("2nd");
                    if(tvno.getText().toString().equals("4th"))
                        tvno.setText("3rd");}



            }
        });
        bplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // int n=Integer.parseInt(tvno.getText().toString());
                if(tvno.getText().toString().equals("0"))
                    tvno.setText("1st");
                else{ if(tvno.getText().toString().equals("1st"))
                    tvno.setText("2nd");
else{
                if(tvno.getText().toString().equals("2nd"))
                    tvno.setText("3rd");
else {
                    if (tvno.getText().toString().equals("3rd"))
                        tvno.setText("4th");


                } }}}
        });




    }

}
