package com.bw.movie.ui.showfrags;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.data.adapter.HotAdapterr;
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

public class FragCinemas extends Fragment implements MovieContrast.Views {

    @BindView(R.id.xrcyc_id)
    XRecyclerView xrcycId;
    Unbinder unbinder;

    HotAdapterr hotAdapterr;
    int page = 1, count = 10;
    MovieContrast.Persenters persenters;
    private int userld;
    private String sessionid;
    List<ReplaceBeen.ResultBean> rlist = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_cinemas, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrcycId.setLayoutManager(linearLayoutManager);
        hotAdapterr = new HotAdapterr(rlist, getActivity());
        xrcycId.setAdapter(hotAdapterr);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("m", 0);
        userld = sharedPreferences.getInt("userld", 0);
//        getInt("userid", 0);
//        String sessionid = m.getString("sessionid", "");
        sessionid = sharedPreferences.getString("sessionid", "");

        xrcycId.setPullRefreshEnabled(true);
        xrcycId.setLoadingMoreEnabled(true);
        xrcycId.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                xrcycId.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                persenters.toView1(userld, sessionid, page, count);

                Log.e("wawas", page + "");
                xrcycId.refreshComplete();

            }

        });
        persenters = new MoviePersenter<>(this);


        persenters.toView1(userld, sessionid, page, count);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showData(HotBeen hotBeen) {

    }

    @Override
    public void showData1(ReplaceBeen replaceBeen) {
        List<ReplaceBeen.ResultBean> result = replaceBeen.getResult();
        rlist.addAll(result);
        hotAdapterr.notifyDataSetChanged();
    }

    @Override
    public void showData2(CommingSoonBeen commingSoonBeen) {

    }
}
