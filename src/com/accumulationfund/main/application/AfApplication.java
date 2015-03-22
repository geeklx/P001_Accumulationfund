package com.accumulationfund.main.application;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

public class AfApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		// 百度地图初始化
		SDKInitializer.initialize(getApplicationContext());
	}
}
