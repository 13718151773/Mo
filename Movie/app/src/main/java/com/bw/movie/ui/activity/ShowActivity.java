package com.bw.movie.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bw.movie.R;
import com.bw.movie.ui.showfrag.FragCinema;
import com.bw.movie.ui.showfrag.FragFilm;
import com.bw.movie.ui.showfrag.FragMine;
import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.ButterKnife;

public class ShowActivity extends AppCompatActivity {

    BottomTabBar mBottomTabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);

        mBottomTabBar = (BottomTabBar) findViewById(R.id.bottom_tab_bar);
        mBottomTabBar.init(getSupportFragmentManager())
                .addTabItem("", R.mipmap.com_icon_film_fault, FragFilm.class)
                .addTabItem("", R.mipmap.com_icon_cinema_default, FragCinema.class)
                .addTabItem("", R.mipmap.com_icon_my_default, FragMine.class);
    }
}
