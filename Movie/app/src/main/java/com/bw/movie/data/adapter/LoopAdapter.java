package com.bw.movie.data.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;

public class LoopAdapter extends RecyclerView.Adapter<LoopAdapter.ViewHolder> implements View.OnClickListener {
    private Context mContext;

    private int img[];

    public LoopAdapter(Context mContext, int[] img) {
        this.mContext = mContext;
        this.img = img;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.loop_movie, null);
        ViewHolder viewholer = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewholer;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        Glide.with(mContext).load(img[position]).into(holder.simpleDraweeView);
    }

    @Override
    public void onClick(View view) {
        if(onItemClick!=null){
            onItemClick.onItemClick(view,(int)view.getTag());
        }
    }


    public interface OnItemClick{
        void onItemClick(View view, int position);
    }
    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public int getItemCount() {
        return img.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView simpleDraweeView;
        public ViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView=itemView.findViewById(R.id.simp_cinema_flow);
        }
    }
}
