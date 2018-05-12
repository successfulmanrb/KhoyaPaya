package com.myvision.khoyapaya.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;

import com.myvision.khoyapaya.BasicLayout;
import com.myvision.khoyapaya.R;

public class Splashsnow extends Activity {
    private final int SPLASH_DISPLAY_LENGTH = 8000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashsnow);
        findViewById(R.id.mButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatchDialog matchDialog = new MatchDialog();
                //getSupportFragmentManager().beginTransaction().add(matchDialog, "matchDialog").commit();
            }
        });
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Splashsnow.this,BasicLayout.class);
                Splashsnow.this.startActivity(mainIntent);
                Splashsnow.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }


}
