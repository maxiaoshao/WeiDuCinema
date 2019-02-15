package com.example.weiducinema.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.example.weiducinema.activity.cinema.CinemaChooseActivity;
import com.example.weiducinema.bean.FilmTimeBean;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by 邵新轩
 * 影院影片排期时间
 */

public class CinemaTimeAdapter extends RecyclerView.Adapter<CinemaTimeAdapter.MyHolder> {
    Context context;
    List<FilmTimeBean> mList = new ArrayList<>();
    public CinemaTimeAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cinema_time,viewGroup,false);
        MyHolder  holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
       final FilmTimeBean filmTimeBean = mList.get(i);
       myHolder.title.setText(filmTimeBean.getScreeningHall());
       myHolder.begintime.setText(filmTimeBean.getBeginTime());
       myHolder.overtime.setText(filmTimeBean.getEndTime());
       SpannableString spannableString = changTVsize(filmTimeBean.getPrice()+"");
       myHolder.element.setText(spannableString);
       myHolder.img_next.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               onItemClick.byValue(filmTimeBean.getScreeningHall(),filmTimeBean.getSeatsTotal(),filmTimeBean.getId()+"");
           }
       });
       //myHolder.begintime.setText(mList.get(i).);
        /**
         * 跳转到选座页面
         */
//       myHolder.itemView.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               Intent intent=new Intent(context, ChooseAseatActivity.class);
//               context.startActivity(intent);
//           }
//       });
    }
    public static SpannableString changTVsize(String value) {
        SpannableString spannableString = new SpannableString(value);
        if (value.contains(".")) {
            spannableString.setSpan(new RelativeSizeSpan(0.6f), value.indexOf("."), value.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return spannableString;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public void addAll(List<FilmTimeBean> result1) {
        if (result1!=null){
            mList.addAll(result1);
        }

    }

    public void clien() {
     mList.clear();///
    }

    public class MyHolder extends RecyclerView.ViewHolder {
      private TextView   title;
      private TextView   begintime;
      private TextView   overtime;
      private TextView   element;
      private TextView   angle;
      private ImageView  img_next;
        public MyHolder(@NonNull View itemView) {
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
        void byValue(String name,int price,String paiid);
    }
    private onItemClick onItemClick;
    public void setOnItemClick(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
}
