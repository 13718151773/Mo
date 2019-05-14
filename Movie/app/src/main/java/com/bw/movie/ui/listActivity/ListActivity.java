package com.bw.movie.ui.listActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.ui.showfrags.FragCinemas;
import com.bw.movie.ui.showfrags.FragFilms;
import com.bw.movie.ui.showfrags.FragMines;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends AppCompatActivity {


    @BindView(R.id.lGroup)
    RadioGroup lGroup;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    List<Fragment> flist = new ArrayList<>();
    @BindView(R.id.lradio)
    RadioButton lradio;
    @BindView(R.id.lradit)
    RadioButton lradit;
    @BindView(R.id.lradie)
    RadioButton lradie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        //获取跳转过来的数据
        Intent intent = getIntent();
        int listld = intent.getIntExtra("listld", 0);

        flist.add(new FragFilms());
        flist.add(new FragCinemas());
        flist.add(new FragMines());

        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return flist.get(i);
            }

            @Override
            public int getCount() {
                return flist.size();
            }
        });


        switch (listld) {
            case 0:
                lradio.setChecked(true);
                mViewPager.setCurrentItem(0);
                break;
            case 1:
                lradit.setChecked(true);
                mViewPager.setCurrentItem(1);
                break;
            case 2:
                lradie.setChecked(true);
                mViewPager.setCurrentItem(2);
                break;

        }

        lGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.lradio:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.lradit:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.lradie:
                        mViewPager.setCurrentItem(2);
                        break;
                }
            }
        });


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                lGroup.getChildAt(lGroup.getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }
}
