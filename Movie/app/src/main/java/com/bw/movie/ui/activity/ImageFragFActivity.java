package com.bw.movie.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;

import com.bw.movie.R;
import com.bw.movie.data.urld.Urils;
import com.bw.movie.ui.imgfrag.FragGo;
import com.bw.movie.ui.imgfrag.FragHurt;
import com.bw.movie.ui.imgfrag.Fragmove;
import com.bw.movie.ui.imgfrag.Fragsour;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ImageFragFActivity extends AppCompatActivity {
    private ViewPager pager;
    private RadioButton groupo,groupt,groupe,groupf;
    List<Fragment> vlist=new ArrayList<>();
    Timer timer;
     int i;
     Handler handler=new Handler(){
         @Override
         public void handleMessage(Message msg) {
             super.handleMessage(msg);
             switch (msg.what){
                 case 200:
                     i--;
                     if (i<0){
                         timer.cancel();
                         urlIsok();

                     }
             }
         }
     };

    private void urlIsok() {
        Urils urils=new Urils();
        boolean networkConnected = urils.isNetworkConnected(this);
        if (networkConnected){
            startActivity(new Intent(ImageFragFActivity.this, LoginActivity.class));
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            finish();
        }else{
            startActivity(new Intent(ImageFragFActivity.this, NoInterActivity.class));
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        pager=findViewById(R.id.pager);
        groupo=findViewById(R.id.rbut_one);
        groupt=findViewById(R.id.rbut_two);

        groupe=findViewById(R.id.rbut_three);

        groupf=findViewById(R.id.rbut_four);


        vlist.add(new FragHurt());
        vlist.add(new Fragsour());
        vlist.add(new Fragmove());
        vlist.add(new FragGo());

        pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return vlist.get(i);
            }

            @Override
            public int getCount() {
                return vlist.size();
            }
        });


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        groupo.setChecked(true);
                        break;
                    case 1:
                        groupt.setChecked(true);
                        break;
                    case 2:
                        groupe.setChecked(true);
                        break;
                    case 3:
                        groupf.setChecked(true);
                        times();
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                SharedPreferences sp = getSharedPreferences("m", 0);
                SharedPreferences.Editor edit = sp.edit();
                edit.putBoolean("sp",true);
                edit.commit();
            }
        });




    }

    private void times() {
        i=2;
        timer=new Timer();

        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(200);
            }
        };

        timer.schedule(timerTask,1,1000);
    }
}
