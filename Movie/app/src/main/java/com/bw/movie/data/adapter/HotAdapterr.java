package com.bw.movie.data.adapter;
//类注释设置模板


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.data.bean.HotBeen;
import com.bw.movie.data.bean.ReplaceBeen;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * @Description: $description$ 类（或接口）是
 * @Author: yuhua
 * @Date: $date$
 */
public class HotAdapterr extends XRecyclerView.Adapter<HotAdapterr.Holder>{
    List<ReplaceBeen.ResultBean> list;
    Context context;

    public HotAdapterr(List<ReplaceBeen.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_r,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.imageView.setImageURI(list.get(i).getImageUrl());
        holder.textView.setText(list.get(i).getName());
        holder.textView_som.setText(list.get(i).getSummary());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends XRecyclerView.ViewHolder {

        SimpleDraweeView imageView;
        TextView textView,textView_som;
        ImageView simpleDraweeView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.fiml_imgsr);
            textView=itemView.findViewById(R.id.fiml_textsr);
            textView_som=itemView.findViewById(R.id.fiml_text_somer);
            simpleDraweeView=itemView.findViewById(R.id.img_hurtsr);
        }
    }
}
