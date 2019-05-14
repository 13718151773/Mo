package com.bw.movie.data.adapter;
//类注释设置模板


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.data.bean.CommingSoonBeen;
import com.bw.movie.data.bean.ReplaceBeen;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @Description: $description$ 类（或接口）是
 * @Author: yuhua
 * @Date: $date$
 */
public class CommingAdapter extends RecyclerView.Adapter<CommingAdapter.Holder>{
    List<CommingSoonBeen.ResultBean> list;
    Context context;

    public CommingAdapter(List<CommingSoonBeen.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.show_comming,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.imageView.setImageURI(list.get(i).getImageUrl());

        holder.textView.setText(list.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        SimpleDraweeView imageView;
        TextView textView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_comming);
            textView=itemView.findViewById(R.id.text_comming);
        }
    }
}
