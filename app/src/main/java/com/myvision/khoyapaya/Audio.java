package com.myvision.khoyapaya;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Rahul BANSAL on 4/28/2017.
 */

public class Audio  {
    static MediaPlayer mediaPlayer;
    static Context context;
 public static void start()
   {
    mediaPlayer=MediaPlayer.create(context,R.raw.backa);
       mediaPlayer.start();}


    public void stop()
    {
        //mediaPlayer=MediaPlayer.create(context,R.raw.backa);
        mediaPlayer.stop();
        mediaPlayer.release();
    }

}