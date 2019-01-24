package com.example.weiducinema.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.example.weiducinema.bean.PopularBean;
import com.facebook.drawee.view.SimpleDraweeView;



import java.util.ArrayList;
import java.util.List;

/**
 * created by fxb
 * 2019/1/23 19:03
 */
public class Recycle1Adapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<PopularBean> li;

    public Recycle1Adapter(FragmentActivity activity) {
        this.context = activity;
        li = new ArrayList<>();
    }


    public void setList(List<PopularBean> result) {
        li = result;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.popul_adapter,null);
        return new ViewHolder1(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder1 viewHolder1 = (ViewHolder1) viewHolder;
        viewHolder1.sim.setImageURI(li.get(i).getImageUrl());
        viewHolder1.t1.setText(li.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return li.size();
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        SimpleDraweeView sim;
        TextView t1;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            sim = itemView.findViewById(R.id.sims);
            t1 = itemView.findViewById(R.id.t1);
        }
    }
}
