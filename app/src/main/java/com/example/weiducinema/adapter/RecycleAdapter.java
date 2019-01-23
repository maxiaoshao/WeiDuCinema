package com.example.weiducinema.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.weiducinema.R;
import com.example.weiducinema.bean.PopularBean;

import java.util.ArrayList;
import java.util.List;

/**
 * created by fxb
 * 2019/1/23 14:40
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private List<PopularBean> list = new ArrayList<>();
    private Context mcontext;
    private int postion;

    public RecycleAdapter(Context mcontext) {
        this.mcontext = mcontext;
    }

    public void setList(List<PopularBean> datas) {
        this.list.clear();
        this.list.addAll(datas);
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mcontext, R.layout.ssshuju, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(mcontext).load(list.get(position))
                .into(holder.img);
        // holder.img.setBackgroundResource(mImglist.get(position));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends ViewHolder {
        ImageView img;

        public MyViewHolder(@android.support.annotation.NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgxxx);
        }
    }
}