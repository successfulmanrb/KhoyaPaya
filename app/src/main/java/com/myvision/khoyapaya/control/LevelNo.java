package com.myvision.khoyapaya.control;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.materialleanback.MaterialLeanBack;
import com.myvision.khoyapaya.ChoiceActivity;
import com.myvision.khoyapaya.PlayAudio;
import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.Settings;
import com.myvision.khoyapaya.number.Number_Level_Selector;
import com.squareup.picasso.Picasso;



public class LevelNo extends AppCompatActivity {

        MaterialLeanBack materialLeanBack;
Intent svc;
    Intent intent;
    Intent i;
    public static SharedPreferences levellock;
    public static SharedPreferences life;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            top();
            //Fabric.with(this, new Crashlytics());
            setContentView(R.layout.activity_level_no);
            levellock = getSharedPreferences(null, Context.MODE_PRIVATE);
            life = getSharedPreferences(null, Context.MODE_PRIVATE);
            svc= new Intent(LevelNo.this,PlayAudio.class);

             /*   if(!getSharedPreferences(null , MODE_PRIVATE).getBoolean("keyofsound",true))
                    stopService(svc);
            else{startService(svc);}*/
            materialLeanBack = (MaterialLeanBack) findViewById(R.id.materialLeanBack);

            materialLeanBack.setCustomizer(new MaterialLeanBack.Customizer() {
                @Override
                public void customizeTitle(TextView textView) {
                    textView.setTypeface(null, Typeface.BOLD);
                }
            });

            //Picasso.with(getApplicationContext())
            //        .load("http://www.journaldugeek.com/wp-content/blogs.dir/1/files/2015/01/game-of-thrones-saison-5-documentaire.jpg")
            //        .fit().centerCrop()
            //        .into(materialLeanBack.getImageBackground());

            materialLeanBack.setAdapter(new MaterialLeanBack.Adapter<TestViewHolder>() {
                @Override
                public int getLineCount() {
                    return 1;
                }

                @Override
                public int getCellsCount(int line) {
                    return 20;
                }

                @Override
                public TestViewHolder onCreateViewHolder(ViewGroup viewGroup, int line) {
                    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_test, viewGroup, false);
                    return new TestViewHolder(view);
                }

                @Override
                public void onBindViewHolder(TestViewHolder viewHolder, int i) {
                    viewHolder.textView.setText("Level " + (i+1));
                    if(i+1<= levellock.getInt("lock", 0))
                    viewHolder.itemView.setBackground(getResources().getDrawable(R.drawable.lockopen));
                    else
                        viewHolder.itemView.setBackground(getResources().getDrawable(R.drawable.levelimage));
                  /*  String url = "http://www.lorempixel.com/40" + viewHolder.row + "/40" + viewHolder.cell + "/";
                    Picasso.with(viewHolder.imageView.getContext()).load(url).into(viewHolder.imageView);*/

                }

               // @Override
               // public String getTitleForRow(int row) {
                   // return "Line " + row;
             //   }

                @Override
                public boolean hasRowTitle(int row) {
                    return row != 1;
                }


                //region customView
                @Override
                public RecyclerView.ViewHolder getCustomViewForRow(ViewGroup viewgroup, int row) {
                    if (row == 3) {
                        View view = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.header, viewgroup, false);
                        return new RecyclerView.ViewHolder(view) {
                        };
                    } else
                        return null;
                }

                @Override
                public boolean isCustomView(int row) {
                    return row == 3;
                }

                @Override
                public void onBindCustomView(RecyclerView.ViewHolder viewHolder, int row) {
                    super.onBindCustomView(viewHolder, row);
                }

                //endregion

            });

            materialLeanBack.setOnItemClickListener(new MaterialLeanBack.OnItemClickListener() {
                @Override
                public void onTitleClicked(int row, String text) {
                    Toast.makeText(getApplicationContext(), "onTitleClicked " + row + " " + text, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onItemClicked(int row, int column) {
                    intent=new Intent(LevelNo.this, Control.class);
                    if(life.getBoolean("life", true)){
                        if(life.getBoolean("opencontrol",false)){

                    if(column==1)
                    {
                        //Toast.makeText(getApplicationContext(), "onItemClicked  " + column, Toast.LENGTH_SHORT).show();
                        intent=new Intent(LevelNo.this, Control.class);
                        intent.putExtra("level" , 1);
                        startActivity(intent);
                    }else{levelselector(column);}

/*
                    if(column==2 )
                    { if(1==1){
                        Toast.makeText(getApplicationContext(), "onItemClicked  " +levellock.getInt("lock",0), Toast.LENGTH_SHORT).show();
                       intent=new Intent(LevelNo.this, Control.class);
                        intent.putExtra("level" , 2);
                        startActivity(intent);} else{ Toast.makeText(LevelNo.this , "locked 2" , Toast.LENGTH_SHORT).show();}
                    }

                    if(column==3 )
                    {//Toast.makeText(getApplicationContext(), "onItemClicked  " + column, Toast.LENGTH_SHORT).show();
                           if(column<=levellock.getInt("lock",0)){
                        Intent i=new Intent(LevelNo.this, Control.class);
                        i.putExtra("level" , 3);
                        startActivity(i);
                    }
                    else{ Toast.makeText(LevelNo.this , "locked 3" , Toast.LENGTH_SHORT).show();}}
                    if(column==4)
                    {//Toast.makeText(getApplicationContext(), "onItemClicked  " + column, Toast.LENGTH_SHORT).show();
                        if (column <= levellock.getInt("lock", 0)) {


                        Intent i=new Intent(LevelNo.this, Control.class);
                        i.putExtra("level" , 4);
                        startActivity(i);
                    }
                    else{ Toast.makeText(LevelNo.this , "locked 4" , Toast.LENGTH_SHORT).show();}}
                    if(column==5)
                    {//Toast.makeText(getApplicationContext(), "onItemClicked  " + column, Toast.LENGTH_SHORT).show();
if(column<=levellock.getInt("lock",0)){
                        Intent i=new Intent(LevelNo.this, Control.class);
                        i.putExtra("level" , 5);
                        startActivity(i);
                    }else{ Toast.makeText(LevelNo.this , "locked 5" , Toast.LENGTH_SHORT).show();}}

                    if(column==6)
                    {//Toast.makeText(getApplicationContext(), "onItemClicked  " + column, Toast.LENGTH_SHORT).show();
                               if(column<=levellock.getInt("lock",0)){
                        Intent i=new Intent(LevelNo.this, Control.class);
                        i.putExtra("level" , 6);
                        startActivity(i);
                    }else{ Toast.makeText(LevelNo.this , "locked 6" , Toast.LENGTH_SHORT).show();}}
                    if(column==7)
                    {//Toast.makeText(getApplicationContext(), "onItemClicked  " + column, Toast.LENGTH_SHORT).show();
                               if(column<=levellock.getInt("lock",0)){
                        Intent i=new Intent(LevelNo.this, Control.class);
                        i.putExtra("level" , 7);
                        startActivity(i);
                    }else{ Toast.makeText(LevelNo.this , "locked 7" , Toast.LENGTH_SHORT).show();}}
                        if(column==8)
                        {//Toast.makeText(getApplicationContext(), "onItemClicked  " + column, Toast.LENGTH_SHORT).show();
                            if(column<=levellock.getInt("lock",0)){
                                Intent i=new Intent(LevelNo.this, Control.class);
                                i.putExtra("level" , 8);
                                startActivity(i);
                            }else{ Toast.makeText(LevelNo.this , "locked 8" , Toast.LENGTH_SHORT).show();}}
                        if(column==9)
                        {//Toast.makeText(getApplicationContext(), "onItemClicked  " + column, Toast.LENGTH_SHORT).show();
                            if(column<=levellock.getInt("lock",0)){
                                Intent i=new Intent(LevelNo.this, Control.class);
                                i.putExtra("level" , 9);
                                startActivity(i);
                            }else{ Toast.makeText(LevelNo.this , "locked 9" , Toast.LENGTH_SHORT).show();}}
                        if(column==10)
                        {//Toast.makeText(getApplicationContext(), "onItemClicked  " + column, Toast.LENGTH_SHORT).show();
                            if(column<=levellock.getInt("lock",0)){
                                Intent i=new Intent(LevelNo.this, Control.class);
                                i.putExtra("level" , 10);
                                startActivity(i);
                            }else{ Toast.makeText(LevelNo.this , "locked 10" , Toast.LENGTH_SHORT).show();}}
                        if(column==11)
                        {//Toast.makeText(getApplicationContext(), "onItemClicked  " + column, Toast.LENGTH_SHORT).show();
                            if(column<=levellock.getInt("lock",0)){
                                Intent i=new Intent(LevelNo.this, Control.class);
                                i.putExtra("level" , 11);
                                startActivity(i);
                            }else{ Toast.makeText(LevelNo.this , "locked 11" , Toast.LENGTH_SHORT).show();}}
                        if(column==12)
                        {//Toast.makeText(getApplicationContext(), "onItemClicked  " + column, Toast.LENGTH_SHORT).show();
                            if(column<=levellock.getInt("lock",0)){
                                Intent i=new Intent(LevelNo.this, Control.class);
                                i.putExtra("level" , 12);
                                startActivity(i);
                            }else{ Toast.makeText(LevelNo.this , "locked 12" , Toast.LENGTH_SHORT).show();}}
                        if(column==13)
                        {//Toast.makeText(getApplicationContext(), "onItemClicked  " + column, Toast.LENGTH_SHORT).show();
                            if(column<=levellock.getInt("lock",0)){
                                Intent i=new Intent(LevelNo.this, Control.class);
                                i.putExtra("level" , 13);
                                startActivity(i);
                            }else{ Toast.makeText(LevelNo.this , "locked 13" , Toast.LENGTH_SHORT).show();}}
                        if(column==14)
                        {//Toast.makeText(getApplicationContext(), "onItemClicked  " + column, Toast.LENGTH_SHORT).show();
                            if(column<=levellock.getInt("lock",0)){
                                Intent i=new Intent(LevelNo.this, Control.class);
                                i.putExtra("level" , 14);
                                startActivity(i);
                            }else{ Toast.makeText(LevelNo.this , "locked 14" , Toast.LENGTH_SHORT).show();}}
                        if(column==15)
                        {//Toast.makeText(getApplicationContext(), "onItemClicked  " + column, Toast.LENGTH_SHORT).show();
                            if(column<=levellock.getInt("lock",0)){
                                Intent i=new Intent(LevelNo.this, Control.class);
                                i.putExtra("level" , 15);
                                startActivity(i);
                            }else{ Toast.makeText(LevelNo.this , "locked 15" , Toast.LENGTH_SHORT).show();}}

                        if(column==16)
                        {//Toast.makeText(getApplicationContext(), "onItemClicked  " + column, Toast.LENGTH_SHORT).show();
                            if(column<=levellock.getInt("lock",0)){
                                Intent i=new Intent(LevelNo.this, Control.class);
                                i.putExtra("level" , 16);
                                startActivity(i);
                            }else{ Toast.makeText(LevelNo.this , "locked 16" , Toast.LENGTH_SHORT).show();}}

                        if(column==17)
                        {//Toast.makeText(getApplicationContext(), "onItemClicked  " + column, Toast.LENGTH_SHORT).show();
                            if(column<=levellock.getInt("lock",0)){
                                Intent i=new Intent(LevelNo.this, Control.class);
                                i.putExtra("level" , 17);
                                startActivity(i);
                            }else{ Toast.makeText(LevelNo.this , "locked 17" , Toast.LENGTH_SHORT).show();}}*/

                    }else {Toast.makeText(LevelNo.this , "clear 5 number level 1st" , Toast.LENGTH_SHORT).show();}

                        //Toast.makeText(getApplicationContext(), "onItemClicked " + row + " " + column, Toast.LENGTH_SHORT).show();
                }else {Toast.makeText(LevelNo.this , "wait for life to fulfill" , Toast.LENGTH_SHORT).show();}}


            });

           /* toolbar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    materialLeanBack.smoothScrollTo(5, 6);
                }
            });*/

        }
    private void levelselector(int column) {
        if(column<=levellock.getInt("number_level_Isunlock",0)){
            Toast.makeText(getApplicationContext(), "onItemClicked  " +levellock.getInt("number_level_Isunlock",0), Toast.LENGTH_SHORT).show();

            intent.putExtra("level" ,column);
            startActivity(intent);} else{ Toast.makeText(LevelNo.this , "locked "+column , Toast.LENGTH_SHORT).show();}
    }

    private void top() {
        if (Build.VERSION.SDK_INT >= 21) {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

        }
        if (Build.VERSION.SDK_INT <21) {
            //  customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    @Override
        protected void onPostCreate(Bundle savedInstanceState) {
            super.onPostCreate(savedInstanceState);
//            mDrawerToggle.syncState();
        }
    @Override
    protected void onRestart() {
        super.onRestart();

        if(getSharedPreferences(null , MODE_PRIVATE).getBoolean("keyofsound",true))
            startService(svc);
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(intent==null && i==null)
            stopService(svc);
       Toast.makeText(LevelNo.this, "pause", Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onBackPressed() {
        i=new Intent(LevelNo.this, ChoiceActivity.class);
        i.putExtra("intentfromlevels",true);
        startActivity(i);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    }
