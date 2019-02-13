package com.example.weiducinema.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.example.weiducinema.bean.SystemMassage;
import com.example.weiducinema.uitls.DateUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * date:2019/1/26 20:56
 * author:邵新轩
 * function:系统消息
 */
public class SysAdapter extends XRecyclerView.Adapter<SysAdapter.MyHolder>{

    private Context context;
    private List<SystemMassage>list = new ArrayList<>();
    private OnClickListen onClickListen;



    public SysAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = View.inflate(context, R.layout.activity_system_massage_item, null);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int i) {
        final SystemMassage systemMassage = list.get(i);

        holder.title.setText(systemMassage.getTitle());
        holder.boby.setText(systemMassage.getContent());
        try {

            String current = DateUtils.dateFormat(new Date(System.currentTimeMillis()), DateUtils.DATE_PATTERN);
            String oldTime = DateUtils.dateFormat(new Date(systemMassage.getPushTime()), DateUtils.DATE_PATTERN);
            if (current.equals(oldTime)){
                holder.time.setText(DateUtils.dateFormat(new Date(systemMassage.getPushTime()), DateUtils.HOUR_PATTERN));
            }else {
                holder.time.setText(DateUtils.dateFormat(new Date(systemMassage.getPushTime()), DateUtils.HOUR_PATTERN));
            }

            if (systemMassage.getStatus() == 1){
                holder.massage.setVisibility(View.GONE);//隐藏
            }else {
                holder.massage.setVisibility(View.VISIBLE);//隐藏
            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = systemMassage.getId();
                    onClickListen.Onclick(id);
                }
            });

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void adAll(List<SystemMassage> massages) {
        list.addAll(massages);
    }

    public void clearList() {
        list.clear();

    }

    class MyHolder extends XRecyclerView.ViewHolder {

        TextView  title,boby,time,massage;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.massage_time);
            title = itemView.findViewById(R.id.sys_text);
            boby = itemView.findViewById(R.id.boby_text);
            massage = itemView.findViewById(R.id.circle);
        }
    }


    public interface OnClickListen{
        void Onclick(int id);
    }
    public void setOnClickListen(OnClickListen onClickListen) {
        this.onClickListen = onClickListen;
    }
}
