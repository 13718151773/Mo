package com.bw.movie.data.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.data.bean.TuijYyBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * 推荐影院
 * 张娜
 */
public class TuijYyAdapter extends BaseQuickAdapter<TuijYyBean.ResultBean, BaseViewHolder> {

    //接口回调
    GuanZhuCallBack guanZhuCallBack;

    public void setonGuan(GuanZhuCallBack guanZhuCallBack) {
        this.guanZhuCallBack = guanZhuCallBack;
    }

    public interface GuanZhuCallBack {
        void oncall(int id);

        void oncall2(int id);
    }


    public TuijYyAdapter(int layoutResId, @Nullable List<TuijYyBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final TuijYyBean.ResultBean item) {
        SimpleDraweeView img1 = helper.getView(R.id.img);
        img1.setImageURI(Uri.parse(item.getLogo()));

        helper.setText(R.id.name, item.getName());
        helper.setText(R.id.addre, item.getAddress());
        helper.setText(R.id.mi, item.getDistance() + "");

        final ImageView img2 = helper.getView(R.id.img2);
        switch (item.getFollowCinema()) {
            case 1:
                //已关注
                img2.setImageResource(R.mipmap.com_icon_collection_selected);
                break;
            case 2:
                //未关注
                img2.setImageResource(R.mipmap.com_icon_collection_default);
                break;
        }

        //点击关注
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = item.getId();
                if (item.getFollowCinema() == 2) {
                    //不关注 要
                    //关注
                    img2.setImageResource(R.mipmap.com_icon_collection_selected);
                    guanZhuCallBack.oncall(id);
                } else if (item.getFollowCinema() == 1) {
                    //关注 要
                    //不关注
                    img2.setImageResource(R.mipmap.com_icon_collection_default);
                    guanZhuCallBack.oncall2(id);
                }
            }
        });


    }
}
