package com.babyshop.ui;

import android.app.Application;

import org.xutils.x;


/**
 * Created by admin on 2017/4/11.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
