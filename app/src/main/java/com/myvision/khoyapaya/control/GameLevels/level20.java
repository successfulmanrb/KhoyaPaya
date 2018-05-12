package com.myvision.khoyapaya.control.GameLevels;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myvision.khoyapaya.BuildConfig;
import com.myvision.khoyapaya.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Rahul BANSAL on 2/19/2017.
 */
public class level20 extends Fragment {

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.control_khoyapaya_lost_level, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String androidOS = Build.VERSION.RELEASE;

        PackageInfo pInfo = null;
        try {
            pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        assert pInfo != null;
        String version = pInfo.versionName;
       //String versionName = BuildConfig.VERSION_NAME;
        if(getActivity().getSharedPreferences(null, MODE_PRIVATE).getBoolean("khoyapaya_touch", false))
        {
            Toast.makeText(getActivity(),version,Toast.LENGTH_SHORT).show();
            getActivity().getSharedPreferences(null, MODE_PRIVATE).edit().putBoolean("khoyapaya_touch", false).apply();

        }




    }

}
