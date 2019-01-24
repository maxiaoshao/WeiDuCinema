package com.example.weiducinema.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.weiducinema.app.WifiUtils;
import com.google.gson.Gson;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
	public Gson mGson = new Gson();
	public SharedPreferences mShare = WDApplication.getShare();

	private Unbinder unbinder;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {


		// 每次ViewPager要展示该页面时，均会调用该方法获取显示的View
		long time = System.currentTimeMillis();
		int type = WifiUtils.getInstance(getActivity()).getNetype();
		if (type == -1) {
			Toast.makeText(getActivity(), "没有网络", Toast.LENGTH_LONG).show();
		}

			View view = inflater.inflate(getLayoutId(), container, false);
			unbinder = ButterKnife.bind(this, view);
			initView(view);
			return view;

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}

	//	@Override
//	public void onResume() {
//		super.onResume();
//		if (!MTStringUtils.isEmpty(getPageName()))
//			MobclickAgent.onPageStart(getPageName()); // 统计页面
//	}
//
//	@Override
//	public void onPause() {
//		super.onPause();
//		if (!MTStringUtils.isEmpty(getPageName()))
//			MobclickAgent.onPageEnd(getPageName());// 统计页面
//	}

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
