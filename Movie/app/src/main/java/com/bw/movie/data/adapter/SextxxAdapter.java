package com.bw.movie.data.adapter;

import android.support.annotation.Nullable;

import com.bw.movie.R;
import com.bw.movie.data.bean.SextxxBean;
import com.bw.movie.data.utils.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 系统消息的适配器
 */
public class SextxxAdapter extends BaseQuickAdapter<SextxxBean.ResultBean, BaseViewHolder> {
    public SextxxAdapter(int layoutResId, @Nullable List<SextxxBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SextxxBean.ResultBean item) {
        helper.setText(R.id.title, item.getTitle());
        helper.setText(R.id.text, item.getContent());
        long time = item.getPushTime();
        String s = TimeUtils.longToDate(time);
        helper.setText(R.id.time, s);
    }
}
