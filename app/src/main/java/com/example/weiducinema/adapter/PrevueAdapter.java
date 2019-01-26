package com.example.weiducinema.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.example.weiducinema.activity.WDDetailsActivity;
import com.example.weiducinema.bean.DetailsBean;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;
import retrofit2.http.Url;

/**
 * created by fxb
 * 2019/1/26 11:22
 */
public class PrevueAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<DetailsBean.ShortFilmListBean> li;
    Context context;
    public PrevueAdapter(WDDetailsActivity wdDetailsActivityClass) {
        this.context = wdDetailsActivityClass;
        li = new ArrayList<>();
    }
    public void setList(List<DetailsBean.ShortFilmListBean> posterList) {
        li = posterList;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.prevue_adapter,null);

        return new ViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder1 viewHolder1 = (ViewHolder1) viewHolder;
        String videoUrl = li.get(i).getVideoUrl();
        String imageUrl = li.get(i).getImageUrl();
//        viewHolder1.jzVideoPlayerStandard.setUp(li.get(i).getVideoUrl(),JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL);
//        Uri uri = Uri.parse(li.get(i).getImageUrl());
//        viewHolder1.jzVideoPlayerStandard.thumbImageView.setImageURI(uri);
        viewHolder1.jzVideoPlayerStandard.setUp(videoUrl, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL);
        Glide.with(context).load(imageUrl)
                .into(viewHolder1.jzVideoPlayerStandard.thumbImageView);
    }

    @Override
    public int getItemCount() {
        return li.size();
    }
    class ViewHolder1 extends RecyclerView.ViewHolder {
        JZVideoPlayerStandard jzVideoPlayerStandard;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            jzVideoPlayerStandard = itemView.findViewById(R.id.videoplayer);
        }
    }


}
