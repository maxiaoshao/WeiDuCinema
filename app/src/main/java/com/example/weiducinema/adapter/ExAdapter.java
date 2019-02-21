package com.example.weiducinema.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.example.weiducinema.activity.WDDetailsActivity;
import com.example.weiducinema.bean.CommentBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * created by fxb
 * 2019/1/26 16:26
 */
public class ExAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<CommentBean> li;
    Context context;

    public ExAdapter(WDDetailsActivity appropriateActivity) {
        this.context = appropriateActivity;
        li = new ArrayList<>();
    }

    public void setData(List<CommentBean> data) {
        li = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.pupop_ping, null);
        return new ViewHelow1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ViewHelow1 viewHelow1 = (ViewHelow1) viewHolder;
        viewHelow1.sim.setImageURI(li.get(i).getCommentHeadPic());
        viewHelow1.name.setText(li.get(i).getCommentUserName());
        viewHelow1.jie.setText(li.get(i).getCommentContent());
        viewHelow1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.tiao(li.get(i).getCommentUserName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return li.size();
    }

    class ViewHelow1 extends RecyclerView.ViewHolder {
        SimpleDraweeView sim;
        TextView name, jie;

        public ViewHelow1(@NonNull View itemView) {
            super(itemView);
            sim = itemView.findViewById(R.id.ping_sim);
            name = itemView.findViewById(R.id.ping_name);
            jie = itemView.findViewById(R.id.tt);
        }
    }

    public interface onItemClick {
        void tiao(String json);
    }

    private onItemClick onItemClick;

    public void setOnItemClick(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

}
