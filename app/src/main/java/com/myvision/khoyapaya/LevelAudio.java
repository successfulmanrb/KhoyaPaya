package com.myvision.khoyapaya;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Rahul BANSAL on 4/28/2017.
 */

public class LevelAudio extends Service {
    private static final String LOGCAT = null;
    MediaPlayer objPlayer;
    private int length,shareopreference_length;
    public void onCreate(){
        super.onCreate();
        Log.d(LOGCAT, "Service Started!");
        objPlayer = MediaPlayer.create(this, R.raw.levelsound);
        objPlayer.setLooping(true); // Set looping
        objPlayer.setVolume(100,100);
        //int resID=getResources().getIdentifier(backa, "raw", getPackageName());

    }

    public int onStartCommand(Intent intent, int flags, int startId){
        shareopreference_length=getSharedPreferences(null,MODE_PRIVATE).getInt("length_of_soundlevel",0);
        Log.d(LOGCAT, "start length "+shareopreference_length);
        objPlayer.seekTo(shareopreference_length);
        objPlayer.start();
        Log.d(LOGCAT, "Media Player started!");
        if(!objPlayer.isLooping()){
            Log.d(LOGCAT, "Problem in Playing Audio");
        }
        return 1;
    }

    public void onStop(){
        objPlayer.stop();
        objPlayer.release();
    }

    public void onPause(){
        objPlayer.stop();
        objPlayer.release();
    }

    public void onDestroy(){
        Log.d(LOGCAT, "out destroy");
        if(objPlayer!=null)
        {length=objPlayer.getCurrentPosition();
            getSharedPreferences(null,MODE_PRIVATE).edit().putInt("length_of_soundlevel",length).apply();
            Log.d(LOGCAT, "in destroy "+length);
            objPlayer.stop();
            objPlayer.release();}
    }

    @Override
    public IBinder onBind(Intent objIndent) {
        return null;
    }
}
