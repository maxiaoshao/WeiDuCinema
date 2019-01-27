package com.example.weiducinema.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.example.weiducinema.bean.QueryPaiBean;
import com.example.weiducinema.bean.ScheduleBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * created by fxb
 * 2019/1/25 14:09
 */
public class QueryPaiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<QueryPaiBean> li;

    public QueryPaiAdapter(FragmentActivity activity) {
        this.context = activity;
        li = new ArrayList<>();
    }


    public void setList(List<QueryPaiBean> result) {
        li=result;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_cinema_time,null);
        return new ViewHolder1(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ViewHolder1 viewHolder1 = (ViewHolder1) viewHolder;
        viewHolder1.title.setText(li.get(i).getScreeningHall());
        viewHolder1.begintime.setText(li.get(i).getBeginTime());
        viewHolder1.overtime.setText(li.get(i).getEndTime());
        viewHolder1.element.setText(li.get(i).getSeatsTotal()+"");
        viewHolder1.img_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClick.byValue(li.get(i).getScreeningHall(),li.get(i)
                        .getSeatsTotal());
            }
        });
    }

    @Override
    public int getItemCount() {
        return li.size();
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView   begintime;
        private TextView   overtime;
        private TextView   element;
        private TextView   angle;
        private ImageView img_next;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.txt_title);
            begintime=itemView.findViewById(R.id.txt_begin);
            overtime=itemView.findViewById(R.id.txt_end);
            element=itemView.findViewById(R.id.txt_element);
//            angle=itemView.findViewById(R.id.txt_angle);
            img_next=itemView.findViewById(R.id.img_next);
        }
    }
    public interface onItemClick{
        void byValue(String name,int price);
    }
    private onItemClick mOnItemClick;
    public void setOnItemClick(onItemClick onItemClick) {
        mOnItemClick = onItemClick;
    }
}
