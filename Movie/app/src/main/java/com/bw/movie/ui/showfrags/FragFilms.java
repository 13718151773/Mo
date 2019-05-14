package com.bw.movie.ui.showfrags;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.data.adapter.HotAdaptert;
import com.bw.movie.data.bean.CommingSoonBeen;
import com.bw.movie.data.bean.HotBeen;
import com.bw.movie.data.bean.ReplaceBeen;
import com.bw.movie.di.contract.MovieContrast;
import com.bw.movie.di.presenter.MoviePersenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragFilms extends Fragment implements MovieContrast.Views {
    List<HotBeen.ResultBean> hlist = new ArrayList<>();
    HotAdaptert hotAdaptert;
    @BindView(R.id.xfcyc_id)
    XRecyclerView xfcycId;
    Unbinder unbinder;

    MovieContrast.Persenters persenters;
    int page=1;
    final int count=10;
    private int userld;
    private String sessionid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_films, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xfcycId.setLayoutManager(linearLayoutManager);
        hotAdaptert=new HotAdaptert(hlist,getActivity());
        xfcycId.setAdapter(hotAdaptert);
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("m",0);
        userld = sharedPreferences.getInt("userld", 0);
//        getInt("userid", 0);
//        String sessionid = m.getString("sessionid", "");
        sessionid = sharedPreferences.getString("sessionid", "");

        xfcycId.setPullRefreshEnabled(true);
        xfcycId.setLoadingMoreEnabled(true);
        xfcycId.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                xfcycId.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                persenters.toView(userld, sessionid,page,count);


                xfcycId.refreshComplete();

            }

        });
        persenters=new MoviePersenter<>(this);




        persenters.toView(userld, sessionid,page,count);

    }

    @Override
    public void showData(HotBeen hotBeen) {
        List<HotBeen.ResultBean> result = hotBeen.getResult();
        hlist.addAll(result);
        hotAdaptert.notifyDataSetChanged();
    }

    @Override
    public void showData1(ReplaceBeen replaceBeen) {

    }

    @Override
    public void showData2(CommingSoonBeen commingSoonBeen) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
