package com.example.weiducinema.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bw.movie.R;
import com.example.weiducinema.activity.WDShowmoiver;
import com.example.weiducinema.bean.PopularBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * created by fxb
 * 2019/1/24 10:45
 */
public class ShowMoiverAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    Context context;
    List<PopularBean> li;
    public ShowMoiverAdapter(WDShowmoiver WDShowmoiver) {
        this.context = WDShowmoiver;
        li = new ArrayList<>();
    }
    public void setList(List<PopularBean> result) {
        li = result;
    }
    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context,R.layout.show_movier_adapter,null);
        return new ViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull XRecyclerView.ViewHolder viewHolder, final int i) {
        ViewHolder1 viewHolder1 = (ViewHolder1) viewHolder;
        viewHolder1.sim.setImageURI(li.get(i).getImageUrl());
        viewHolder1.name.setText(li.get(i).getName());
        viewHolder1.title.setText("简介："+li.get(i).getSummary());
        viewHolder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.tiao(li.get(i).getId()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return li.size();
    }
    class ViewHolder1 extends RecyclerView.ViewHolder {
        SimpleDraweeView sim;
        TextView name,title;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            sim = itemView.findViewById(R.id.sims);
          name = itemView.findViewById(R.id.name);
          title = itemView.findViewById(R.id.titles);
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
