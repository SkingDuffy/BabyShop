package com.babyshop.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.babyshop.commom.Constant;

public class SharedPreferencesUtil {

    private static SharedPreferencesUtil spUtil = null;
    private static SharedPreferences sp = null;

    private SharedPreferencesUtil(Context ctx) {
        sp = ctx.getSharedPreferences("babyShop", ctx.MODE_PRIVATE);
    }

    public static synchronized void init(Context cxt){
        if(spUtil == null){
            spUtil = new SharedPreferencesUtil(cxt);
        }
    }

    /**
     * get instance of PreferenceManager
     */
    public synchronized static SharedPreferencesUtil getInstance() {
        if (spUtil == null) {
            throw new RuntimeException("please init first!");
        }
        return spUtil;
    }

    public void clear() {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    public void remove(String key) {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }

    public int getInt(final String key, final int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public void putInt(final String key, final int value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void putInt(final String key, final Integer value) {
        if (null != value) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(key, value);
            editor.commit();
        }
    }

    public long getLong(final String key, final long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    public void putLong(final String key, final long value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public void putLong(final String key, final Long value) {
        if (null != value) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putLong(key, value);
            editor.commit();
        }
    }

    public float getFloat(final String key, final float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    public void putFloat(final String key, final float value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public void putFloat(final String key, final Float value) {
        if (null != value) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putFloat(key, value);
            editor.commit();
        }
    }

    public String getString(final String key, final String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public void putString(final String key, final String value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public boolean getBoolean(final String key, final boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public void putBoolean(final String key, final boolean value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void putBoolean(final String key, final Boolean value) {
        if (null != value) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean(key, value);
            editor.commit();
        }
    }

/*---------------------------以下常用字段---------------------------*/

    /**
     * 判断用户是否登陆
     */
    public boolean hasLogin(){
        return getBoolean(Constant.HAS_LOGIN, false);
    }

    /**
     * use id
     */
    public String getUserId(){
        return getString(Constant.U_ID, "");
    }

    /**
     * use name
     */
    public String getUserName(){
        return getString(Constant.U_NAME, "");
    }


}
