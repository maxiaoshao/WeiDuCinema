package com.example.weiducinema.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.example.weiducinema.activity.WDDetailsActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * created by fxb
 * 2019/1/26 14:04
 */
public class StillsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> li;
    public StillsAdapter(WDDetailsActivity wdDetailsActivity) {
        this.context = wdDetailsActivity;
        li = new ArrayList<>();
    }
    public void setData(List<String> posterList) {
        li = posterList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context,R.layout.stills_adapter,null);
        return new ViewHodler1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
    ViewHodler1 viewHolder1 = (ViewHodler1) viewHolder;
    viewHolder1.imageView.setImageURI(li.get(i));
    }

    @Override
    public int getItemCount() {
        return li.size();
    }
    class ViewHodler1 extends RecyclerView.ViewHolder {
        SimpleDraweeView imageView;
        public ViewHodler1(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.im);
        }
    }


}
