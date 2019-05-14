package com.bw.movie.ui.showfrag;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bw.movie.R;
import com.bw.movie.data.adapter.CommingAdapter;
import com.bw.movie.data.adapter.HotAdapter;
import com.bw.movie.data.adapter.LoopAdapter;
import com.bw.movie.data.adapter.ReplaceAdapter;
import com.bw.movie.data.bean.CommingSoonBeen;
import com.bw.movie.data.bean.HotBeen;
import com.bw.movie.data.bean.ReplaceBeen;
import com.bw.movie.di.contract.MovieContrast;
import com.bw.movie.di.presenter.MoviePersenter;
import com.bw.movie.ui.listActivity.ListActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import recycler.coverflow.RecyclerCoverFlow;

public class FragFilm extends Fragment implements MovieContrast.Views {
    @BindView(R.id.send_recy)
    RecyclerCoverFlow sendRecy;
    Unbinder unbinder;

    HotAdapter hotAdapter;
    @BindView(R.id.img_editi)
    ImageView imgEditi;
    @BindView(R.id.cyc_one)
    RecyclerView cycOne;
    @BindView(R.id.cyc_two)
    RecyclerView cycTwo;
    @BindView(R.id.cyc_three)
    RecyclerView cycThree;
    @BindView(R.id.relay_hot)
    RelativeLayout relayHot;
    @BindView(R.id.relay_replace)
    RelativeLayout relayReplace;
    @BindView(R.id.relay_comming)
    RelativeLayout relayComming;
    private LoopAdapter loopAdapter;
    MovieContrast.Persenters persenters;
    ReplaceAdapter replaceAdapter;
    CommingAdapter commingAdapter;

    Intent intent;
    private final int page = 1, count = 10;

    List<HotBeen.ResultBean> xlist = new ArrayList<>();
    List<ReplaceBeen.ResultBean> plist = new ArrayList<>();
    List<CommingSoonBeen.ResultBean> clist = new ArrayList<>();

    private int imgs[] = {R.mipmap.htjz, R.mipmap.mcgj, R.mipmap.txzs, R.mipmap.wnxs, R.mipmap.wdjdqny, R.mipmap.ws, R.mipmap.xgdmx, R.mipmap.zdn, R.mipmap.zrqk};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_film, null);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loopAdapter = new LoopAdapter(getActivity(), imgs);
        sendRecy.setAdapter(loopAdapter);
        //让轮播图显示中间的图片
        sendRecy.smoothScrollToPosition(imgs.length / 2);
        //自定义接口回调，点击图片使它展示到中间
        loopAdapter.setOnItemClick(new LoopAdapter.OnItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                sendRecy.smoothScrollToPosition(position);
            }
        });

        //recycview布局方向
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);

        //添加方向
        cycOne.setLayoutManager(linearLayoutManager);
        cycTwo.setLayoutManager(linearLayoutManager1);
        cycThree.setLayoutManager(linearLayoutManager2);

        //适配器初始化
        hotAdapter = new HotAdapter(xlist, getActivity());
        replaceAdapter = new ReplaceAdapter(plist, getActivity());
        commingAdapter = new CommingAdapter(clist, getActivity());

        //添加适配器
        cycOne.setAdapter(hotAdapter);
        cycTwo.setAdapter(replaceAdapter);
        cycThree.setAdapter(commingAdapter);

        //p层
        persenters = new MoviePersenter<>(this);
        //sp获取头
        SharedPreferences m = getActivity().getSharedPreferences("m", 0);
        int userid = m.getInt("userid", 0);
        String sessionid = m.getString("sessionid", "");
        //map赋值
        //p层方法
        persenters.toView(userid, sessionid, page, count);
        persenters.toView1(userid, sessionid, page, count);
        persenters.toView2(userid, sessionid, page, count);

        //点击横标跳转
        goon();

    }

    private void goon() {
        relayHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), ListActivity.class);
                intent.putExtra("listld", 0);
                startActivity(intent);

            }
        });

        relayReplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), ListActivity.class);
                intent.putExtra("listld", 1);
                startActivity(intent);
            }
        });

        relayComming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), ListActivity.class);
                intent.putExtra("listld", 2);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void showData(HotBeen hotBeen) {
        List<HotBeen.ResultBean> result = hotBeen.getResult();
        xlist.addAll(result);
        hotAdapter.notifyDataSetChanged();
    }

    @Override
    public void showData1(ReplaceBeen replaceBeen) {
        List<ReplaceBeen.ResultBean> result = replaceBeen.getResult();
        plist.addAll(result);
        replaceAdapter.notifyDataSetChanged();
    }

    @Override
    public void showData2(CommingSoonBeen commingSoonBeen) {
        List<CommingSoonBeen.ResultBean> result = commingSoonBeen.getResult();
        clist.addAll(result);
        commingAdapter.notifyDataSetChanged();
    }
}
