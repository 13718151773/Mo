package com.bw.movie.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    int i;
    Timer time;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
                switch (msg.what){
                    case 200:
                        i--;
                        if (i<0){
                            time.cancel();
                            //跳转
                            SharedPreferences m = getSharedPreferences("m", 0);
                            boolean sp = m.getBoolean("sp", false);
                            if (sp){
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            }else{
                                startActivity(new Intent(MainActivity.this, ImageFragFActivity.class));
                            }
                            intogo();
                            finish();
                        }
                }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //倒计时
        countown();
    }


    //跳转动画（渐变）
    public void intogo() {
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    private void countown() {
        i=3;

        time=new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(200);
            }
        };

        time.schedule(timerTask,1,1000);
    }

}
