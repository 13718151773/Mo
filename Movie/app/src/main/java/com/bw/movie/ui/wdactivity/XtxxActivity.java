package com.bw.movie.ui.wdactivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.data.adapter.SextxxAdapter;
import com.bw.movie.data.bean.SextxxBean;
import com.bw.movie.data.bean.UpdateReadMesBean;
import com.bw.movie.data.bean.WdBean;
import com.bw.movie.di.contract.SextxxContract;
import com.bw.movie.di.presenter.SextxxPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 系统消息
 * 张娜
 */
public class XtxxActivity extends AppCompatActivity implements SextxxContract.View {

    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.message)
    LinearLayout message;
    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.back)
    ImageView back;
    private SextxxPresenter presenter;
    private int userid;
    private String sessionid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xtxx);
        ButterKnife.bind(this);

        SharedPreferences m = getSharedPreferences("m", 0);
        userid = m.getInt("userid", 0);
        sessionid = m.getString("sessionid", "");

        presenter = new SextxxPresenter();
        presenter.attachView(this);
        presenter.requestData(userid, sessionid, 1, 20);
        presenter.requestData3(userid,sessionid);


    }

    @Override
    public void showData(SextxxBean bean) {

        final List<SextxxBean.ResultBean> result = bean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recy.setLayoutManager(manager);
        SextxxAdapter adapter = new SextxxAdapter(R.layout.sextxx_iteam, result);
        recy.setAdapter(adapter);
        //修改状态
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int id = result.get(position).getId();
                presenter.requestData2(userid, sessionid, id);
            }
        });

    }

    //未读数量
    @Override
    public void showData2(WdBean bean) {
        int count = bean.getCount();
        num.setText(count + "");
    }

    //修改状态
    @Override
    public void showData3(UpdateReadMesBean bean) {
        String message = bean.getMessage();
        if (message.equals("状态改变成功")) {
            presenter.requestData3(userid, sessionid);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

    }


}
