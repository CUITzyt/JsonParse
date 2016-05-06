package com.szy.activity;

import java.io.File;

import android.app.Application;
import android.util.Log;

public class MyApp extends Application {
	private static MyApp instance;
	
	/* 返回Application的实例  */
	public static MyApp getContext() {
		return instance;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		Log.i("zyt","application is created!");
	}
	
}
