package com.myvision.khoyapaya.control.GameLevels;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.control.Control;

/**
 * Created by Rahul BANSAL on 2/19/2017.
 */
public class level6 extends Fragment {

    private ViewGroup mainLayout;
    private ImageView image;
    ImageView swtchoff;
    ImageView closelamp;
    private int xDelta;
    private int yDelta;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.level6, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mainLayout = (RelativeLayout) getActivity().findViewById(R.id.main);
        image = (ImageView) getActivity().findViewById(R.id.openlamp);
        closelamp = (ImageView) getActivity().findViewById(R.id.closelamp);
        swtchoff = (ImageView) getActivity().findViewById(R.id.swtchoff);




        image.setOnTouchListener(onTouchListener());





        swtchoff.setOnClickListener(onclickswtch());
    }

    private View.OnClickListener onclickswtch() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(getActivity(), LevelNo.class);

                swtchoff.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.level6switchoff));
                image.setVisibility(View.GONE);
                closelamp.setVisibility(View.VISIBLE);

                if (Control.leveltime.getBoolean("is_first_time_level6", true)) {

                    //the app is being launched for first time, do something
                    Control.levellock.edit().putInt("lock" , 7).apply();
                    Log.d("TAG", "First time");
                    int cointemp;
                    cointemp= Control.coin.getInt("coin",0)+10;
                    Control.coin.edit().putInt("coin",cointemp).apply();
                    Control.coinv.setText(String.valueOf(Control.coin.getInt("coin",0)));
                    ((Control)getActivity()).oncrose(7);

                    //Toast.makeText(getActivity(),"connected " , Toast.LENGTH_SHORT).show();


                // first time task

                // record the fact that the app has been started at least once
                    Control.leveltime.edit().putBoolean("is_first_time_level6", false).apply();

            }
            else
            {((Control)getActivity()).oncrose(7);
                Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                //second time launch..
            }
               // startActivity(i);
            //ImageView ivTest = new ImageView(getActivity());


            }

        };
    }



    private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();

                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)
                                view.getLayoutParams();

                        xDelta = x - lParams.leftMargin;
                        yDelta = y - lParams.topMargin;
                        break;

                    case MotionEvent.ACTION_UP:
                        //Toast.makeText(getActivity(),"thanks for new location!", Toast.LENGTH_SHORT).show();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                      //  layoutParams.rightMargin = 0;
                       // layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                        break;
                }
                mainLayout.invalidate();
                return true;
            }
        };
    }

}
