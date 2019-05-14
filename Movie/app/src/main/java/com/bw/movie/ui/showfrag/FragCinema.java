package com.bw.movie.ui.showfrag;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.data.adapter.TuijYyAdapter;
import com.bw.movie.data.bean.GzBean;
import com.bw.movie.data.bean.QxGzBean;
import com.bw.movie.data.bean.TuijYyBean;
import com.bw.movie.di.contract.FragCinemaContract;
import com.bw.movie.di.presenter.FragCinemaPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 影院
 */
public class FragCinema extends Fragment implements FragCinemaContract.View {
    @BindView(R.id.radio1)
    RadioButton radio1;
    @BindView(R.id.radio2)
    RadioButton radio2;
    Unbinder unbinder;
    @BindView(R.id.recy)
    RecyclerView recy;
    private FragCinemaPresenter presenter;
    private int userid;
    private String sessionid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_cinema, null);
        unbinder = ButterKnife.bind(this, view);

        SharedPreferences m = getActivity().getSharedPreferences("m", 0);
        userid = m.getInt("userid", 0);
        sessionid = m.getString("sessionid", "");

        presenter = new FragCinemaPresenter();
        presenter.attachView(this);

        //
        presenter.requestData(userid, sessionid, 1, 20);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.radio1, R.id.radio2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.radio1:

                //推荐影院
                radio1.setTextColor(Color.WHITE);
                radio2.setTextColor(Color.BLACK);
                presenter.requestData(userid, sessionid, 1, 20);

                break;
            case R.id.radio2:

                //附近影院
                radio2.setTextColor(Color.WHITE);
                radio1.setTextColor(Color.BLACK);
                //经度 纬度
                //presenter.requestData2(userid, sessionid, "", "", 1, 20);

                break;
        }
    }

    @Override
    public void showData(TuijYyBean bean) {
        List<TuijYyBean.ResultBean> result = bean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recy.setLayoutManager(manager);
        TuijYyAdapter adapter = new TuijYyAdapter(R.layout.tuijyy_iteam, result);
        recy.setAdapter(adapter);


        adapter.setonGuan(new TuijYyAdapter.GuanZhuCallBack() {
            @Override
            public void oncall(int id) {
                //Todo 关注
                presenter.requestData3(userid, sessionid, id);
            }

            @Override
            public void oncall2(int id) {
                //取消关注
                presenter.requestData4(userid, sessionid, id);
            }
        });
    }

    //关注
    @Override
    public void showData2(GzBean bean) {
        String message = bean.getMessage();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    //不再关注
    @Override
    public void showData3(QxGzBean bean) {
        String message = bean.getMessage();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }


}
