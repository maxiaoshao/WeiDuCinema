package com.example.weiducinema.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

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
                intent1.putExtra("llname",li.getName());
                intent1.putExtra("daoyan",li.getDirector());
                intent1.putExtra("shichang",li.getDuration());
                intent1.putExtra("leixing",li.getMovieTypes());
                intent1.putExtra("chandi",li.getPlaceOrigin());
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
                SimpleDraweeView pratticulars_filmreview_xs4 = popview.findViewById(R.id.pratticulars_pratticulars_qx_sim);
                pratticulars_filmreview_xs4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
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
                SimpleDraweeView pratticulars_filmreview_xs3 = popview.findViewById(R.id.pratticulars_prevue_xs);
                pratticulars_filmreview_xs3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
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
                SimpleDraweeView pratticulars_filmreview_xs2 = popview.findViewById(R.id.pratticulars_stagephoto_xs);
                pratticulars_filmreview_xs2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
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
                SimpleDraweeView pratticulars_filmreview_xs = popview.findViewById(R.id.pratticulars_filmreview_xs);
                pratticulars_filmreview_xs.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                initReview(popview);

                break;
        }
    }

    private void initReview(final View popview) {


        pratticulars_filmreview_while = popview.findViewById(R.id.pratticulars_filmreview_while);
        pratticulars_filmreview_while.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View inflate1 = View.inflate(WDDetailsActivity.this, R.layout.addpicture_item, null);
                //   Dialog 弹框
                final Dialog bottomDialog = new Dialog(WDDetailsActivity.this, R.style.BottomDialog);
                bottomDialog.setContentView(inflate1);
                ViewGroup.LayoutParams layoutParamsthreefilmreview = inflate1.getLayoutParams();
                layoutParamsthreefilmreview.width = getResources().getDisplayMetrics().widthPixels;
                inflate1.setLayoutParams(layoutParamsthreefilmreview);
                bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
                bottomDialog.setCanceledOnTouchOutside(true);
                bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
                bottomDialog.show();
                View viewById = inflate1.findViewById(R.id.fa);
                viewById.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomDialog.dismiss();
                    }
                });

            }
        });


        ex = popview.findViewById(R.id.ex);
        ex.setGroupIndicator(null);
        CommentPrencenter commentPrencenter = new CommentPrencenter(new Coment());
        adapter4 = new ExAdapter(this);
        commentPrencenter.reqeust(li.getId()+"","1","10");
        ex.setAdapter(adapter4);


    }

    private void initStills(View popview) {

        adapter2 = new StillsAdapter(this);
        adapter2.setData(li.getPosterList());
        pratticulars_stagephoto_jz = popview.findViewById(R.id.pratticulars_stagephoto_jz);
        pratticulars_stagephoto_jz.setLayoutManager(new GridLayoutManager(this,2));
        pratticulars_stagephoto_jz.addItemDecoration(new SpacesItemDecoration(10));
        pratticulars_stagephoto_jz.setAdapter(adapter2);
    }

    private void initPred(View popview) {

        pratticulars_prevue_rec = popview.findViewById(R.id.pratticulars_prevue_rec);
        adapter = new PrevueAdapter(this);
        adapter.setList(li.getShortFilmList());
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
