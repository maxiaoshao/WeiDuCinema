package com.example.weiducinema.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.weiducinema.R;
import com.example.weiducinema.ShowActivity;
import com.example.weiducinema.core.base.BaseActivity;

import java.util.ArrayList;

import io.reactivex.annotations.NonNull;

/**
 * created by 命运的尘 on 2019/1/22 17:09
 */
public class ScountActivity extends BaseActivity {

    ViewPager pagers;
    ArrayList<Integer> list1;
    ArrayList<View> lsit2;
    LinearLayout linear;
    private int mCurrentIndex = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_double;
    }

    @Override
    protected void initView() {
        linear = findViewById(R.id.linear);
        pagers = findViewById(R.id.pager);
        list1 = new ArrayList<>();
        lsit2 = new ArrayList<>();
        list1.add(R.drawable.bootpage1);
        list1.add(R.drawable.bootpage2);
        list1.add(R.drawable.bootpage3);
        list1.add(R.drawable.bootpage4);
        String text[] = {"荡涤你的心灵", "看遍人生百态", "净化你的灵魂", "带你开启一段美好的电影之旅"};

        for (int i = 0; i < list1.size()-1; i++) {
            View view = View.inflate(getBaseContext(), R.layout.bootpage_item, null);
            ImageView imageView = view.findViewById(R.id.introd_image);
            //添加图片
            imageView.setImageResource(list1.get(i));
            TextView textViewTop = view.findViewById(R.id.introd_textTop);
            textViewTop.setText("一场电影");
            TextView textViewButtom = view.findViewById(R.id.introd_textBottom);
            //添加文字
            textViewButtom.setText(text[i]);
            lsit2.add(view);
        }
        //获取最后一张布局
        View viewclick = View.inflate(getBaseContext(), R.layout.bootpage_item_click, null);

        ImageView imageView = viewclick.findViewById(R.id.introd_image);
        //添加图片
        imageView.setImageResource(list1.get(3));

        TextView textViewTop = viewclick.findViewById(R.id.introd_textTop);

        TextView textViewButtom = viewclick.findViewById(R.id.introd_textBottom);
        //添加文字
        textViewTop.setText("八维移动通信学院作品");

        textViewButtom.setText(text[3]);
        //将布局添加到集合
        lsit2.add(viewclick);

        pagers.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return lsit2.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {

                View view1 = lsit2.get(position);

                container.addView(view1);

                return view1;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });

        for (int i = 0; i < lsit2.size(); i++) {

            ImageView dot = new ImageView(this);
            if (i == mCurrentIndex) {
                dot.setImageResource(R.drawable.rbtn_t);//设置当前页的圆点
            } else {
                dot.setImageResource(R.drawable.rbtn_f);//其余页的圆点
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout
                    .LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i > 0) {
                params.leftMargin = 10;//设置圆点边距
            }
            dot.setLayoutParams(params);
            linear.addView(dot);//将圆点添加到容器中
        }

        pagers.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mCurrentIndex = i;
                for (int position = 0; position < linear.getChildCount(); position++) {
                    ImageView imageView = (ImageView) linear.getChildAt(position);
                    if (position == i) {
                        imageView.setImageResource(R.drawable.rbtn_t);
                    } else {
                        imageView.setImageResource(R.drawable.rbtn_f);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScountActivity.this, ShowActivity.class);
                startActivity(intent);
                finish();
            }
        });

        saveData();
    }
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("movie", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("flag", true);

        editor.commit();
    }

    @Override
    protected void destoryData() {

    }
}
