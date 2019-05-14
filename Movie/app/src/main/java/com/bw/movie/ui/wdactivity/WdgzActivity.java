package com.bw.movie.ui.wdactivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;

import com.bw.movie.R;
import com.bw.movie.data.bean.MyGzYpBean;
import com.bw.movie.di.contract.WdgzContract;
import com.bw.movie.di.presenter.WdgzPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的关注
 * 张娜
 */
public class WdgzActivity extends AppCompatActivity implements WdgzContract.View {


    @BindView(R.id.radio1)
    RadioButton radio1;
    @BindView(R.id.radio2)
    RadioButton radio2;
    @BindView(R.id.recy)
    RecyclerView recy;
    private WdgzPresenter presenter;
    private int userid;
    private String sessionid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdgz);
        ButterKnife.bind(this);

        presenter = new WdgzPresenter();
        presenter.attachView(this);

        SharedPreferences m = getSharedPreferences("m", 0);
        userid = m.getInt("userid", 0);
        sessionid = m.getString("sessionid", "");

    }

    @OnClick({R.id.radio1, R.id.radio2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.radio1:

                //电影
                radio1.setTextColor(Color.WHITE);
                radio2.setTextColor(Color.BLACK);
                presenter.requestData(userid,sessionid,1,20);

                break;
            case R.id.radio2:

                //影院
                radio2.setTextColor(Color.WHITE);
                radio1.setTextColor(Color.BLACK);

                break;
        }
    }

    @Override
    public void showData(MyGzYpBean bean) {
        List<MyGzYpBean.ResultBean> result = bean.getResult();

        LinearLayoutManager manager=new LinearLayoutManager(this);
        recy.setLayoutManager(manager);

    }
}
