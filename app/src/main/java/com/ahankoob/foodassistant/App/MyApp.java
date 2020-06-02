package com.ahankoob.foodassistant.App;

import android.app.Application;

import com.dbflow5.config.FlowManager;

public class MyApp extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		FlowManager.init(this);
	}
}
