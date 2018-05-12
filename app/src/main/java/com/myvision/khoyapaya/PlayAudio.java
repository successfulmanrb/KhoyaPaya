package com.myvision.khoyapaya;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import com.myvision.khoyapaya.R;

public class PlayAudio extends Service{
	private static final String LOGCAT = "homesound";
	MediaPlayer objPlayer;
	boolean pause_resume=false;
	private int length,shareopreference_length;


	public void onCreate(){
		super.onCreate();
		Log.d(LOGCAT, "Service Started!");
		objPlayer = MediaPlayer.create(this, R.raw.backa);
		objPlayer.setLooping(true); // Set looping
		objPlayer.setVolume(100,100);
		//int resID=getResources().getIdentifier(backa, "raw", getPackageName());

	}

	public int onStartCommand(Intent intent, int flags, int startId){
		//for continue sound after stop
		shareopreference_length=getSharedPreferences(null,MODE_PRIVATE).getInt("length_of_sound",0);
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
		Log.d(LOGCAT, "out stop");
		if(objPlayer!=null)
		{objPlayer.stop();
			Log.d(LOGCAT, "in stop");
		objPlayer.release();}
	}

	public void onPause(){
		Log.d(LOGCAT, "out pause");
		if(objPlayer!=null)
		{objPlayer.stop();
			Log.d(LOGCAT, "in pause");
		objPlayer.release();}
	}

	public void onDestroy(){
		Log.d(LOGCAT, "out destroy");
		if(objPlayer!=null)
		{length=objPlayer.getCurrentPosition();
			getSharedPreferences(null,MODE_PRIVATE).edit().putInt("length_of_sound",length).apply();

 			Log.d(LOGCAT, "in destroy "+length);
			objPlayer.stop();
		objPlayer.release();}
	}

	@Override
	public IBinder onBind(Intent objIndent) {
		return null;
	}
}
