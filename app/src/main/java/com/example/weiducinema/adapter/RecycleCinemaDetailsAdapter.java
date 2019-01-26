package com.example.weiducinema.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.example.weiducinema.bean.PopularBean;
import com.example.weiducinema.bean.ScheduleBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * created by fxb
 * 2019/1/25 14:09
 */
public class RecycleCinemaDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<ScheduleBean> li;

    public RecycleCinemaDetailsAdapter(FragmentActivity activity) {
        this.context = activity;
        li = new ArrayList<>();
    }


    public void setList(List<ScheduleBean> result) {
        li.addAll(result);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.muma,null);
        return new ViewHolder1(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder1 viewHolder1 = (ViewHolder1) viewHolder;
        viewHolder1.sim.setImageURI(li.get(i).getImageUrl());
        mByValue.Cid(li.get(i).getId());
    }

    @Override
    public int getItemCount() {
        return li.size();
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        SimpleDraweeView sim;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            sim = itemView.findViewById(R.id.simm);
        }
    }
    public interface ByValue{
        void Cid(int id);
    }
    private ByValue mByValue;

    public void setByvalue(ByValue oByValue) {
        mByValue = oByValue;
    }
}
