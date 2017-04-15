package com.babyshop.ui;

import android.app.Application;

import com.babyshop.utils.SharedPreferencesUtil;

import org.xutils.x;


/**
 * Created by admin on 2017/4/11.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // init XUtils
//        x.Ext.init(this);

        SharedPreferencesUtil.init(this);

    }
}
