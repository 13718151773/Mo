package com.bw.movie.data.utils;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 张娜
 * app
 */
public class Myapp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //fresco
        Fresco.initialize(this);

    }
}
