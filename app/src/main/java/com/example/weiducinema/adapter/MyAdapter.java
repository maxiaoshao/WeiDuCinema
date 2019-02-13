package com.example.weiducinema.adapter;
 
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
 
import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.example.weiducinema.bean.TicketBean;

import java.util.ArrayList;
import java.util.List;
 
/**
 * Created by 。 on 2018/12/14.
 */
 
public class MyAdapter extends RecyclerView.Adapter{
    private List<TicketBean> list = new ArrayList<>();
    private Context context;
    private ClickListener clickListener;
    private LongClickListener longClickListener;
    public void addList(List<TicketBean> u){
        if(u!=null){
            list.addAll(u);
        }
    }
    public MyAdapter( Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wait_money, parent, false);
            return new MyViewHolder(view);
        }else if(viewType==1){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.finish_layout, parent, false);
            return new ViewHolder2(view);
        }

       return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = holder.getItemViewType();
        switch (itemViewType){
            case 0:
                MyViewHolder myViewHolder = (MyViewHolder) holder;
                final TicketBean ticketBean = list.get(position);
                myViewHolder.one_dingdan.setText("订单号:"+ticketBean.getOrderId());
                myViewHolder.one_film.setText("影院:"+ticketBean.getCinemaName());
                myViewHolder.one_count.setText("数量:"+ticketBean.getAmount()+"张");
                myViewHolder.one_money.setText("金额:"+ticketBean.getPrice()+"元");
                myViewHolder.one_yingting.setText("影厅:"+ticketBean.getScreeningHall());
                myViewHolder.one_time.setText("时间:"+ticketBean.getBeginTime());
                myViewHolder.one_title.setText(ticketBean.getMovieName());
                myViewHolder.one_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClick.tiao(ticketBean.getOrderId()+"");
                    }
                });
                break;
            case 1:
                ViewHolder2 holder2 = (ViewHolder2) holder;
                TicketBean ticketBean2 = list.get(position);
                holder2.two_dingdan.setText("订单号:"+ticketBean2.getOrderId());
                holder2.two_film.setText("影院:"+ticketBean2.getCinemaName());
                holder2.two_count.setText("数量:"+ticketBean2.getAmount()+"张");
                holder2.two_money.setText("金额:"+ticketBean2.getPrice()+"元");
                holder2.two_yingting.setText("影厅:"+ticketBean2.getScreeningHall());
                holder2.two_time.setText("时间:"+ticketBean2.getBeginTime());
                holder2.two_title.setText(ticketBean2.getMovieName());
                break;
        }
    }
    public TicketBean getBean(int position){
        TicketBean user = list.get(position);
        return user;
    }

    @Override
    public int getItemViewType(int position) {
        int status = list.get(position).getStatus();

        if(status==1){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clearIteam() {
        list.clear();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        private final TextView one_title;
        private final TextView one_dingdan;
        private final TextView one_yingting;
        private final TextView one_film;
        private final TextView one_time;
        private final TextView one_count;
        private final TextView one_money;
        private final TextView one_button;

        public MyViewHolder(View itemView) {
            super(itemView);
            one_title = itemView.findViewById(R.id.one_title);
            one_dingdan = itemView.findViewById(R.id.one_dingdan);
            one_film = itemView.findViewById(R.id.one_film);
            one_yingting = itemView.findViewById(R.id.one_yingting);
            one_time = itemView.findViewById(R.id.one_time);
            one_count = itemView.findViewById(R.id.one_count);
            one_money = itemView.findViewById(R.id.one_money);
            one_button = itemView.findViewById(R.id.one_button);

        }
    }
    class ViewHolder2 extends RecyclerView.ViewHolder{

        private final TextView two_title;
        private final TextView two_dingdan;
        private final TextView two_yingting;
        private final TextView two_film;
        private final TextView two_time;
        private final TextView two_count;
        private final TextView two_money;
        public ViewHolder2(View itemView) {
            super(itemView);
            two_title = itemView.findViewById(R.id.two_title);
            two_dingdan = itemView.findViewById(R.id.two_dingdan);
            two_film = itemView.findViewById(R.id.two_film);
            two_yingting = itemView.findViewById(R.id.two_yingting);
            two_time = itemView.findViewById(R.id.two_time);
            two_count = itemView.findViewById(R.id.two_count);
            two_money = itemView.findViewById(R.id.two_money);
        }
    }
    public interface ClickListener{
        void onItmeClickListener(View view, int position);
    }
    public void setOnItmeClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }
    public interface LongClickListener{
        void onLongItmeClickListener(View view, int position);
    }
    public void setOnLongItmeClickListener(LongClickListener longClickListener){
        this.longClickListener = longClickListener;
    }
    public interface onItemClick{
        void tiao(String json);
    }
    private onItemClick onItemClick;
    public void setOnItemClick(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

}
