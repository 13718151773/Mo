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
import com.bw.movie.data.adapter.HotAdapterc;
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

/**
 * 我的
 */
public class FragMines extends Fragment implements MovieContrast.Views{


    @BindView(R.id.xccyc_id)
    XRecyclerView xccycId;
    Unbinder unbinder;
    HotAdapterc hotAdapterc;
    List<CommingSoonBeen.ResultBean> clist=new ArrayList<>();
    int page=1,count=10;
    private int userld;
    private String sessionid;
    MovieContrast.Persenters persenters;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_miness, null);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xccycId.setLayoutManager(linearLayoutManager);
        hotAdapterc=new HotAdapterc(clist,getActivity());
        xccycId.setAdapter(hotAdapterc);
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("m",0);
        userld = sharedPreferences.getInt("userld", 0);
//        getInt("userid", 0);
//        String sessionid = m.getString("sessionid", "");
        sessionid = sharedPreferences.getString("sessionid", "");

        xccycId.setPullRefreshEnabled(true);
        xccycId.setLoadingMoreEnabled(true);
        xccycId.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                xccycId.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                persenters.toView2(userld, sessionid,page,count);


                xccycId.refreshComplete();

            }

        });
        persenters=new MoviePersenter<>(this);




        persenters.toView2(userld, sessionid,page,count);

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

    }

    @Override
    public void showData2(CommingSoonBeen commingSoonBeen) {
        List<CommingSoonBeen.ResultBean> result = commingSoonBeen.getResult();
        clist.addAll(result);
        hotAdapterc.notifyDataSetChanged();
    }
}
