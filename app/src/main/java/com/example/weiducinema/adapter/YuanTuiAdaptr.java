package com.example.weiducinema.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.example.weiducinema.bean.YuantuiBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * created by fxb
 * 2019/1/24 19:55
 */
public class YuanTuiAdaptr  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<YuantuiBean> li;
    public YuanTuiAdaptr(FragmentActivity activity) {
        this.context = activity;
        li = new ArrayList<>();
    }
    public void setData(List<YuantuiBean> result) {
        li = result;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context,R.layout.frag_cinema_layout_adapter,null);
        return new ViewHelow1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ViewHelow1 viewHelow1 = (ViewHelow1) viewHolder;
        viewHelow1.sim.setImageURI(li.get(i).getLogo());
        viewHelow1.name.setText(li.get(i).getName());
        viewHelow1.jie.setText(li.get(i).getAddress());
        viewHelow1.ju.setText(li.get(i).getFollowCinema()+" KM");
        viewHelow1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.tiao(li.get(i).getId()+"",li.get(i).getLogo(),li.get(i).getName()
                ,li.get(i).getAddress()
                );
            }
        });
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
        void tiao(String cinameId,String imgPic,String cinameName,String address);
    }
    private onItemClick onItemClick;
    public void setOnItemClick(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

}
