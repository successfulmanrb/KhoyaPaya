package com.myvision.khoyapaya;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import java.util.Locale;

public class Settings extends Activity {
ImageView levelv,gamev;
    Intent svc , svlevel;
    Intent intent;
    Button blang;
    public static  SharedPreferences sound_of_on;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sound_of_on=getSharedPreferences(null,MODE_PRIVATE);
        levelv=(ImageView) findViewById(R.id.iv_level);
        gamev=(ImageView) findViewById(R.id.iv_game);
        gamev.setImageResource(R.drawable.volume_on);
        levelv.setImageResource(R.drawable.volume_on);
        gamev.setTag("on");
        levelv.setTag("on");
        blang=(Button)findViewById(R.id.blang);
        blang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("hi");
            }
        });
        //sw.setTextOn("On"); // displayed text of the Switch whenever it is in checked or on state
      //  sw.setTextOff("Off"); // displayed text of the Switch whenever it is in unchecked i.e. off sta
        svc=new Intent(this, PlayAudio.class);
        svlevel=new Intent(this, LevelAudio.class);
        if(getIntent().getBooleanExtra("sound_onlang",false)){
            if(getSharedPreferences(null , MODE_PRIVATE).getBoolean("keyofsound",true))
            { startService(svc);
                }
        }
        if(!getSharedPreferences(null , MODE_PRIVATE).getBoolean("keyofsound",true))
        { stopService(svc);
        gamev.setImageResource(R.drawable.volume_close);
        gamev.setTag("close");}


        if(!getSharedPreferences(null , MODE_PRIVATE).getBoolean("keyofsoundlevel",true))
        {  stopService(svlevel);
        levelv.setImageResource(R.drawable.volume_close);
        levelv.setTag("close");}
        gamev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gamev.getTag().equals("close"))
                {
                    svc=new Intent(Settings.this, PlayAudio.class);
                    startService(svc);
                    gamev.setImageResource(R.drawable.volume_on);
                    gamev.setTag("on");
                    sound_of_on.edit().putBoolean("keyofsound",true).apply();
                }
                else{
                    svc=new Intent(Settings.this, PlayAudio.class);
                    stopService(svc);
                    gamev.setImageResource(R.drawable.volume_close);
                    gamev.setTag("close");
                    getSharedPreferences(null , MODE_PRIVATE).edit().putBoolean("keyofsound",false).apply();
                }

            }
        });
        levelv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(levelv.getTag().equals("close"))
                { levelv.setImageResource(R.drawable.volume_on);
                    levelv.setTag("on");
                    getSharedPreferences(null , MODE_PRIVATE).edit().putBoolean("keyofsoundlevel",true).apply();
                }
                else
                {  levelv.setImageResource(R.drawable.volume_close);
                   levelv.setTag("close");
                    getSharedPreferences(null , MODE_PRIVATE).edit().putBoolean("keyofsoundlevel",false).apply();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       intent=new Intent(Settings.this,Home.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        getSharedPreferences(null , MODE_PRIVATE).edit().putString("locate",lang).apply();
        Intent refresh = new Intent(this, Settings.class);
        refresh.putExtra("sound_onlang",true);
        startActivity(refresh);
        overridePendingTransition(0,0);
        finish();

    }
    @Override
    protected void onPause() {
        super.onPause();
        if(intent==null)
            stopService(svc);
        //Toast.makeText(Home.this, "pause", Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        if(getSharedPreferences(null , MODE_PRIVATE).getBoolean("keyofsound",true))
        {

            startService(svc);}
        //Toast.makeText(Home.this, "onRestart", Toast.LENGTH_SHORT).show();
    }
    public  void  language_func(View v){
        setLocale(v.getTag().toString());
    }
}
