package com.example.weiducinema.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.example.weiducinema.adapter.ExAdapter;
import com.example.weiducinema.adapter.PrevueAdapter;
import com.example.weiducinema.adapter.StillsAdapter;
import com.example.weiducinema.app.SpacesItemDecoration;
import com.example.weiducinema.base.WDBaseActivity;
import com.example.weiducinema.base.DataCall;
import com.example.weiducinema.bean.CommentBean;
import com.example.weiducinema.bean.DetailsBean;
import com.example.weiducinema.bean.Result;
import com.example.weiducinema.core.exception.ApiException;
import com.example.weiducinema.custom.WDSelectPicPopupWindow;
import com.example.weiducinema.precener.CommentPrencenter;
import com.example.weiducinema.precener.DetailsPrencenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * created by fxb
 * 2019/1/25 08:47
 */
public class WDDetailsActivity extends WDBaseActivity implements View.OnClickListener{
    ImageView fanhui;
    Button goupiao;
    TextView mover_name,details_txt_details,details_txt_prediction,details_txt_stills,details_txt_review;
    DetailsPrencenter detailsPrencenter;
    SimpleDraweeView movier_sim;
    WDSelectPicPopupWindow menuWindow;
    RadioGroup group;
    JZVideoPlayerStandard jzVideoPlayerStandard;
    private View popview;
    String moverid;
    private int height;
    ImageView guan;
    StillsAdapter adapter2;
    private PopupWindow popupWindow;
    PrevueAdapter adapter;
    ExpandableListView ex;
    ExAdapter adapter4;
    boolean equals;
    ImageView pratticulars_filmreview_while;
    SimpleDraweeView pratticulars_pratticulars_sim,pratticulars_pratticulars_qx_sim;
    TextView pratticulars_pratticulars_lx_txt,pratticulars_pratticulars_dy_txt,pratticulars_pratticulars_sc_txt,pratticulars_pratticulars_cd_txt,jianjie_txt;
    RecyclerView pratticulars_pratticulars_recview,pratticulars_prevue_rec,pratticulars_stagephoto_jz;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        guan = findViewById(R.id.guan);
        details_txt_details = findViewById(R.id.details_txt_details);
        details_txt_prediction = findViewById(R.id.details_txt_prediction);
        details_txt_stills = findViewById(R.id.details_txt_stills);
        details_txt_review = findViewById(R.id.details_txt_review);
        details_txt_review.setOnClickListener(this);
        details_txt_stills.setOnClickListener(this);
        details_txt_prediction.setOnClickListener(this);
        details_txt_details.setOnClickListener(this);
        height = getWindowManager().getDefaultDisplay().getHeight();
        goupiao = findViewById(R.id.goupiao);
        mover_name = findViewById(R.id.movier_name);

        movier_sim = findViewById(R.id.movier_sim);
        detailsPrencenter = new DetailsPrencenter(new Details());
        final Intent intent = getIntent();
        String guans = intent.getStringExtra("guan");
        if (guans==null){

        }else{

            equals = guans.equals("1");
        }
        if (equals){
            guan.setImageResource(R.drawable.com_icon_collection_selected_);
        }else{
            guan.setImageResource(R.drawable.com_icon_collection_default_);
        }
        moverid = intent.getStringExtra("mid");
        detailsPrencenter.reqeust(moverid);
        fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        goupiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(WDDetailsActivity.this,AppropriateActivity.class);
                intent1.putExtra("movieid",li.getId()+"");
                intent1.putExtra("moviename",li.getName());
                intent1.putExtra("imageuri",li.getImageUrl());
                startActivity(intent1);
            }
        });

    }

    @Override
    protected void destoryData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.details_txt_details:
                popview = View.inflate(this, R.layout.film_item_pratticulars_pratticulars, null);
                popupWindow = new PopupWindow(popview, WindowManager.LayoutParams.MATCH_PARENT, height /100*80);
                popupWindow .setOutsideTouchable(true);
                popupWindow .setFocusable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(0));
                popupWindow.showAtLocation(mover_name, Gravity.BOTTOM, 0, 0);
                popupWindow.setAnimationStyle(R.style.popwin_anim_style);
                initDetails(popview);

                break;
            case R.id.details_txt_prediction:
                popview = View.inflate(this, R.layout.film_item_pratticulars_prevue, null);
                popupWindow = new PopupWindow(popview, WindowManager.LayoutParams.MATCH_PARENT, height /100*80);
                popupWindow .setOutsideTouchable(true);
                popupWindow .setFocusable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(0));
                popupWindow.showAtLocation(mover_name, Gravity.BOTTOM, 0, 0);
                popupWindow.setAnimationStyle(R.style.popwin_anim_style);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                       JZVideoPlayer.releaseAllVideos();

                    }
                });

                initPred(popview);
                break;
            case R.id.details_txt_stills:
                popview = View.inflate(this, R.layout.film_item_pratticulars_stagephoto, null);
                popupWindow = new PopupWindow(popview, WindowManager.LayoutParams.MATCH_PARENT, height /100*80);
                popupWindow .setOutsideTouchable(true);
                popupWindow .setFocusable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(0));
                popupWindow.showAtLocation(mover_name, Gravity.BOTTOM, 0, 0);
                popupWindow.setAnimationStyle(R.style.popwin_anim_style);
                initStills(popview);
                break;
            case R.id.details_txt_review:

                popview = View.inflate(this, R.layout.film_item_pratticulars_filmreview, null);
                popupWindow = new PopupWindow(popview, WindowManager.LayoutParams.MATCH_PARENT, height /100*80);
                popupWindow .setOutsideTouchable(true);
                popupWindow .setFocusable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(0));
                popupWindow.showAtLocation(mover_name, Gravity.BOTTOM, 0, 0);
                popupWindow.setAnimationStyle(R.style.popwin_anim_style);
                initReview(popview);
//                pratticulars_filmreview_while = popview.findViewById(R.id.pratticulars_filmreview_while);
//                pratticulars_prevue_rec.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(WDDetailsActivity.this,"写评论",Toast.LENGTH_LONG).show();
//                    }
//                });
                break;
        }
    }

    private void initReview(View popview) {


        ex = popview.findViewById(R.id.ex);
        CommentPrencenter commentPrencenter = new CommentPrencenter(new Coment());
        adapter4 = new ExAdapter(this);
        commentPrencenter.reqeust(li.getId()+"","1","10");
        ex.setAdapter(adapter4);

    }

    private void initStills(View popview) {
        detailsPrencenter = new DetailsPrencenter(new Details4());
        detailsPrencenter.reqeust(moverid);
        adapter2 = new StillsAdapter(this);
        pratticulars_stagephoto_jz = popview.findViewById(R.id.pratticulars_stagephoto_jz);
        pratticulars_stagephoto_jz.setLayoutManager(new GridLayoutManager(this,2));
        pratticulars_stagephoto_jz.addItemDecoration(new SpacesItemDecoration(10));
        pratticulars_stagephoto_jz.setAdapter(adapter2);
    }

    private void initPred(View popview) {
        detailsPrencenter = new DetailsPrencenter(new Details3());
        detailsPrencenter.reqeust(moverid);
        pratticulars_prevue_rec = popview.findViewById(R.id.pratticulars_prevue_rec);
        adapter = new PrevueAdapter(this);
        pratticulars_prevue_rec.setLayoutManager(new LinearLayoutManager(this));
        pratticulars_prevue_rec.addItemDecoration(new SpacesItemDecoration(10));
        pratticulars_prevue_rec.setAdapter(adapter);

    }

    private void initDetails(View popview) {

        detailsPrencenter = new DetailsPrencenter(new Details2());
        detailsPrencenter.reqeust(moverid);
        pratticulars_pratticulars_sim = popview.findViewById(R.id.pratticulars_pratticulars_sim);
        pratticulars_pratticulars_cd_txt = popview.findViewById(R.id.pratticulars_pratticulars_cd_txt);
        pratticulars_pratticulars_qx_sim = popview.findViewById(R.id.pratticulars_pratticulars_qx_sim);
        pratticulars_pratticulars_lx_txt = popview.findViewById(R.id.pratticulars_pratticulars_lx_txt);
        pratticulars_pratticulars_dy_txt = popview.findViewById(R.id.pratticulars_pratticulars_dy_txt);
        pratticulars_pratticulars_sc_txt = popview.findViewById(R.id.pratticulars_pratticulars_sc_txt);
        jianjie_txt = popview.findViewById(R.id.jianjie_txt);


    }
        DetailsBean li;

    private class Details implements DataCall<Result<DetailsBean>> {


        @Override
        public void success(Result<DetailsBean> data) {
            li = data.getResult();
            movier_sim.setImageURI(data.getResult().getImageUrl());
            mover_name.setText(data.getResult().getName());

            pratticulars_pratticulars_sc_txt.setText(data.getResult().getDuration());

        }

        @Override
        public void fail(ApiException e) {

        }
    }

    private View.OnClickListener itemsOnClick = new View.OnClickListener() {


        @Override
        public void onClick(View v) {

        }
    };

    private class Details2 implements DataCall<Result<DetailsBean>> {
        @Override
        public void success(Result<DetailsBean> data) {
            pratticulars_pratticulars_sim.setImageURI(data.getResult().getImageUrl());
            pratticulars_pratticulars_cd_txt.setText(data.getResult().getPlaceOrigin());
            pratticulars_pratticulars_lx_txt.setText(data.getResult().getMovieTypes());
            pratticulars_pratticulars_dy_txt.setText(data.getResult().getDirector());
            jianjie_txt.setText(data.getResult().getSummary());
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    private class Details3 implements DataCall<Result<DetailsBean>> {
        @Override
        public void success(Result<DetailsBean> data) {
            adapter.setList(data.getResult().getShortFilmList());
            adapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    private class Details4 implements DataCall<Result<DetailsBean>> {
        @Override
        public void success(Result<DetailsBean> data) {
            adapter2.setData(data.getResult().getPosterList());
            adapter2.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    private class Coment implements DataCall<Result<List<CommentBean>>> {


        @Override
        public void success(Result<List<CommentBean>> data) {
            adapter4.setData(data.getResult());
            adapter4.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
