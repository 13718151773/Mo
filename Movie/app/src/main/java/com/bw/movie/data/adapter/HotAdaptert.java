package com.bw.movie.data.adapter;
//类注释设置模板


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.data.bean.HotBeen;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * @Description: $description$ 类（或接口）是
 * @Author: yuhua
 * @Date: $date$
 */
public class HotAdaptert extends XRecyclerView.Adapter<HotAdaptert.Holder>{
    List<HotBeen.ResultBean> list;
    Context context;

    public HotAdaptert(List<HotBeen.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_f,null);
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
            imageView=itemView.findViewById(R.id.fiml_imgs);
            textView=itemView.findViewById(R.id.fiml_texts);
            textView_som=itemView.findViewById(R.id.fiml_text_some);
            simpleDraweeView=itemView.findViewById(R.id.img_hurts);
        }
    }
}
