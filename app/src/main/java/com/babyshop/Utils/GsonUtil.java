package com.babyshop.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by: hy
 */
public class GsonUtil {
    private static GsonUtil mInstance;
    private static Gson mGson;

    private GsonUtil() {
        if (null == mGson)
            mGson = new Gson();
    }

    public static GsonUtil getInstance() {
        if (null == mInstance) {
            synchronized (GsonUtil.class) {
                mInstance = new GsonUtil();
            }
        }
        return mInstance;
    }

    public static Gson gson() {
        if (null != mGson) {
            getInstance();
        }
        return mGson;
    }

    /**
     * 解析数据
     *
     * @param json       json 数据
     * @param modelClass Model
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T parserJson(String json, Class<T> modelClass) throws Exception {
        return mGson.fromJson(json, modelClass);
    }

    /**
     * 解析数据
     *
     * @param json    json 数据
     * @param typeOfT Model
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T parserJson(String json, Type typeOfT) throws Exception {
        return mGson.fromJson(json, typeOfT);
    }

    /**
     * javabean to json
     *
     * @param o  Object
     * @return String
     *
     */
    public String bean2Json(Object o){
        return mGson.toJson(o);
    }

}
