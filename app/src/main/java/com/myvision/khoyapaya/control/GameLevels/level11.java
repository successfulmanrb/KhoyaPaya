package com.myvision.khoyapaya.control.GameLevels;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.control.Control;

/**
 * Created by Rahul BANSAL on 3/10/2017.
 */
public class level11 extends Fragment {
    GestureDetector gestureDetector;
    ImageView door , classroom;
    boolean tapped;
    int count=0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.level11, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        door=(ImageView)getActivity().findViewById(R.id.door11);
        //classroom=(ImageView)getActivity().findViewById(R.id.classroom);
        gestureDetector = new GestureDetector(getActivity(),new GestureListener());
        door.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                return gestureDetector.onTouchEvent(event);
            }

        });


    }
    public class GestureListener extends
            GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {

            return true;
        }
/*
        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Toast.makeText(getActivity(), "doubletapevent", Toast.LENGTH_SHORT).show();
            return super.onDoubleTapEvent(e);
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Toast.makeText(getActivity(), "showpress", Toast.LENGTH_SHORT).show();
            super.onShowPress(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Toast.makeText(getActivity(), "singlecon", Toast.LENGTH_SHORT).show();
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onContextClick(MotionEvent e) {
            Toast.makeText(getActivity(), "oncontext", Toast.LENGTH_SHORT).show();
            return super.onContextClick(e);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Toast.makeText(getActivity(), "singletapup", Toast.LENGTH_SHORT).show();
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Toast.makeText(getActivity(), "longpress", Toast.LENGTH_SHORT).show();
            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Toast.makeText(getActivity(), "scroll", Toast.LENGTH_SHORT).show();
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Toast.makeText(getActivity(), "flick", Toast.LENGTH_SHORT).show();
            return super.onFling(e1, e2, velocityX, velocityY);
        }
*/
        // event when double tap occurs
        @Override
        public boolean onDoubleTap(MotionEvent e) {
             count++;
            tapped = !tapped;

            if (count==3) {
               // door.setVisibility(View.GONE);
                //classroom.setVisibility(View.VISIBLE);
                door.setImageResource(R.drawable.classroom);
                if (Control.leveltime.getBoolean("is_first_time_level11", true)) {
                    //the app is being launched for first time, do something
                    Control.levellock.edit().putInt("lock", 12).apply();
                    Log.d("TAG", "First time");
                    int cointemp;
                    cointemp = Control.coin.getInt("coin", 0) + 10;
                    Control.coin.edit().putInt("coin", cointemp).apply();
                    Control.coinv.setText(String.valueOf(Control.coin.getInt("coin", 0)));
                    ((Control) getActivity()).oncrose(12);

                    //((Control)getActivity()).oncrose(8);


                    // first time task

                    // record the fact that the app has been started at least once
                    Control.leveltime.edit().putBoolean("is_first_time_level11", false).apply();
                } else {
                    Toast.makeText(getActivity(), "Coin provided 1st time only", Toast.LENGTH_SHORT).show();
                    //second time launch..
                    ((Control) getActivity()).oncrose(12);
                }



            } else {



            }

            return true;
        }
    }
}
