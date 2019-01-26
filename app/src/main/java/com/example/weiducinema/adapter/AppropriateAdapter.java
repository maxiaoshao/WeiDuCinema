package com.example.weiducinema.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.example.weiducinema.activity.AppropriateActivity;
import com.example.weiducinema.bean.QueryBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * created by fxb
 * 2019/1/26 16:26
 */
public class AppropriateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<QueryBean> li;
    Context context;
    public AppropriateAdapter(AppropriateActivity appropriateActivity) {
        this.context = appropriateActivity;
        li = new ArrayList<>();
    }
    public void setData(List<QueryBean> data) {
        li = data;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.appropriate_adapter,null);
        return new ViewHelow1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHelow1 viewHelow1 = (ViewHelow1) viewHolder;
        viewHelow1.sim.setImageURI(li.get(i).getLogo());
        viewHelow1.name.setText(li.get(i).getName());
        viewHelow1.jie.setText(li.get(i).getAddress());
        viewHelow1.ju.setText(li.get(i).getDistance()+"");
    }

    @Override
    public int getItemCount() {
        return li.size();
    }
    class ViewHelow1 extends RecyclerView.ViewHolder {
        SimpleDraweeView sim;
        TextView name,jie,ju;
        public ViewHelow1(@NonNull View itemView) {
            super(itemView);
            sim = itemView.findViewById(R.id.sim);
            name = itemView.findViewById(R.id.name);
            jie = itemView.findViewById(R.id.jie);
            ju = itemView.findViewById(R.id.ju);
        }
    }
    public interface onItemClick{
        void tiao(String json);
    }
    private onItemClick onItemClick;
    public void setOnItemClick(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

}
