package com.example.weiducinema.base;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.example.weiducinema.app.WifiUtils;
import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import crossoverone.statuslib.StatusUtil;

public abstract class WDBaseFragment extends Fragment {
	public Gson mGson = new Gson();
	public SharedPreferences mShare = WDApplication.getShare();

	private Unbinder unbinder;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(getLayoutId(), container, false);
		unbinder = ButterKnife.bind(this, view);
		initView(view);

		// 每次ViewPager要展示该页面时，均会调用该方法获取显示的View
		long time = System.currentTimeMillis();



		setStatusColor();
		setSystemInvadeBlack();
			return view;

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}
	protected void setStatusColor() {
		StatusUtil.setUseStatusBarColor(getActivity(), Color.parseColor("#00000000"));
	}

	protected void setSystemInvadeBlack() {
		// 第二个参数是是否沉浸,第三个参数是状态栏字体是否为黑色。
		StatusUtil.setSystemStatus(getActivity(), true, true);
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart(getClass().getName());
	}


	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd(getClass().getName());
	}
	/**
	 * 设置页面名字 用于友盟统计
	 */
	public abstract String getPageName();
	/**
	 * 设置layoutId
	 * @return
	 */
	protected abstract int getLayoutId();

	/**
	 * 初始化视图
	 * @param view
	 */
	protected abstract void initView(View view);
}
