package com.babyshop.utils;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.internal.$Gson$Types;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Description : OkHttp网络连接封装工具类
 */
public class MyOkHttpUtils {

    private OkHttpClient.Builder mOkHttpClient;
    private Handler mHandler;
    private static MyOkHttpUtils mInstance;
    private static final String IMGUR_CLIENT_ID = "9199fde  f135c122";
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String REQEST_TAG = "requestTag";
    private static final String RESPONCE_TAG = "responceTag";
    private static final String REQEST_STR = "请求参数： ";
    private static final String HEADER_STR = "hearder： ";
    private static final String RESPONCE_STR = "响应参数： ";
    public static final int REFERSH = 1; //刷新
    public static final int LOADMORE = 2; //加载更多
    public static final int OTHER = 3; //其他
    public Call call;

    public MyOkHttpUtils() {
        /**
         * 构建OkHttpClient 
         */
        mOkHttpClient = new OkHttpClient.Builder();
        /**
         * 设置连接的超时时间 
         */
        mOkHttpClient.connectTimeout(10, TimeUnit.SECONDS);
        /**
         * 设置响应的超时时间
         */
        mOkHttpClient.writeTimeout(10, TimeUnit.SECONDS);
        /**
         * 请求的超时时间
         */
        mOkHttpClient.readTimeout(30, TimeUnit.SECONDS);
//        /**
//         * 允许使用Cookie
//         */
//        mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
        /**
         * 获取主线程的handler
         */
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * 通过单例模式构造对象
     *
     * @return OkHttpUtils
     */
    private synchronized static MyOkHttpUtils getmInstance() {
        if (mInstance == null) {
            mInstance = new MyOkHttpUtils();
        }
        return mInstance;
    }

    /**
     * 构造Get请求
     *
     * @param url      请求的url
     * @param callback 结果回调的方法
     */
    private void getRequest(String url, final ResultCallback callback, int acation) {
        LLog.e( REQEST_STR + url);
        final Request.Builder request = new Request.Builder();
        setHeader(request);
        deliveryResult(callback, request.url(url).build(), acation);
    }

    /**
     * 构造post 请求
     *
     * @param url      请求的url
     * @param callback 结果回调的方法
     * @param params   请求参数
     */
    private void postRequest(String url, final ResultCallback callback, List<Param> params, int action) {
        Request request = buildPostRequest(url, params);
        deliveryResult(callback, request, action);
    }

    /**
     * 构造postString 请求
     *
     * @param url      请求的url
     * @param callback 结果回调的方法
     * @param json     请求参数
     */
    private void postStringRequest(String url, final ResultCallback callback, String json, int action) {
        Request request = buildPostStringRequest(url, json);
        deliveryResult(callback, request, action);
    }

    /**
     * 构造postFile 请求
     *
     * @param url      请求的url
     * @param callback 结果回调的方法
     * @param filePath 文件路径
     */
    private void postFileRequest(String url, final ResultCallback callback, String filePath, int action) {
        Request request = buildPostFileRequest(url, filePath);
        deliveryResult(callback, request, action);
    }

    /**
     * 处理请求结果的回调
     *
     * @param callback
     * @param request
     */
    private void deliveryResult(final ResultCallback callback, Request request, final int action) {
        call = mOkHttpClient.build().newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailCallback(callback, e);
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                try {
                    String str = response.body().string();
                    LLog.e(RESPONCE_STR + str);
                    if (callback.mType == String.class) {
                        sendSuccessCallBack(callback, str, action);
                    } else {
                        Object object = GsonUtil.getInstance().parserJson(str, callback.mType);
                        sendSuccessCallBack(callback, object, action);
                    }
                } catch (final Exception e) {
                    LLog.e("convert json failure" + e.getMessage());
                    sendFailCallback(callback, e);
                }
            }
        });
    }

    /**
     * 取消当前请求
     */
    public void cancelReqest() {
        if (call != null) {
            if (!call.isCanceled()) {
                call.cancel();
            }
        }
    }

    /**
     * 发送失败的回调
     *
     * @param callback
     * @param e
     */
    private void sendFailCallback(final ResultCallback callback, final Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onFailure(e);
                }
            }
        });
    }

    /**
     * 发送成功的调
     *
     * @param callback
     * @param obj
     */
    private void sendSuccessCallBack(final ResultCallback callback, final Object obj, final int action) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess(obj, action);
                }
            }
        });
    }

    /**
     * 构造post请求
     *
     * @param url    请求url
     * @param params 请求的参数
     * @return 返回 Request
     */
    private Request buildPostRequest(String url, List<Param> params) {
        FormBody.Builder formBody = new FormBody.Builder();
        StringBuffer sb = new StringBuffer(url + "?");
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                Param param = params.get(i);
                formBody.add(param.key, param.value);
                if (i != params.size() - 1) {
                    sb.append(param.key + "=" + param.value + "&");
                } else {
                    sb.append(param.key + "=" + param.value);
                }
            }
        }
        Request.Builder post = new Request.Builder().url(url).post(formBody.build());
        setHeader(post);
        LLog.e(REQEST_STR + sb.toString());
        return post.build();
    }

    /**
     * 构造post请求
     *
     * @param url    请求url
     * @param params 请求的参数
     * @return 返回 Request
     */
    private Request buildGetRequest(String url, List<Param> params) {
        FormBody.Builder formBody = new FormBody.Builder();
        StringBuffer sb = new StringBuffer(url + "?");
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                Param param = params.get(i);
                formBody.add(param.key, param.value);
                if (i != params.size() - 1) {
                    sb.append(param.key + "=" + param.value + "&");
                } else {
                    sb.append(param.key + "=" + param.value);
                }
            }
        }
        Request.Builder get = new Request.Builder().url(url).get();
        get.put(formBody.build());
        setHeader(get);
        LLog.e(REQEST_STR + sb.toString());
        return get.build();
    }

    private void setHeader(Request.Builder post) {
//        String uid = App.spUtil().getString("uid");
//        String uid = App.aCache().getAsString("uid");
//
//        String mac = DeviceHelper.getMAC(Url.context());
        post.addHeader("Content-Type", "application/json");
//        LLog.e( HEADER_STR + "uid(" + uid + ");token-(" + mac + ")");
    }

    /**
     * 构造postString请求
     *
     * @param url  请求url
     * @param json 请求的参数
     * @return 返回 Request
     */
    private Request buildPostStringRequest(String url, String json) {
        LLog.e(REQEST_STR + url + json);
        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder request = new Request.Builder();
        setHeader(request);
        return request.url(url).post(body).build();
    }

    /**
     * 构造postFile请求
     *
     * @param url      请求url
     * @param filePath 文件路径
     * @return 返回 Request
     */
    private Request buildPostFileRequest(String url, String filePath) {
        File file = new File(filePath);
        MultipartBody.Builder requestBody = new MultipartBody.Builder();
        requestBody.setType(MultipartBody.FORM);
        RequestBody fileBody = RequestBody.create(MEDIA_TYPE_PNG, file);
        requestBody.addFormDataPart("imgFile", file.getName(), fileBody);
        Request.Builder request = new Request.Builder();
        request.header("Authorization", "Client-ID " + IMGUR_CLIENT_ID);
        setHeader(request);
        return request.url(url).post(requestBody.build()).build();
    }

    /**********************对外接口************************/

    /**
     * get请求
     *
     * @param url      请求url
     * @param callback 请求回调
     */
    public static void get(String url, ResultCallback callback) {
        getmInstance().getRequest(url, callback, 0);
    }

    /**
     * post请求
     *
     * @param url      请求url
     * @param callback 请求回调
     * @param params   请求参数
     */
    public static void post(String url, List<Param> params, final ResultCallback callback) {
        getmInstance().postRequest(url, callback, params, 0);
    }

    /**
     * postString请求
     *
     * @param url      请求url
     * @param callback 请求回调
     * @param json     请求json
     */
    public static void postString(String url, String json, int action, final ResultCallback callback) {
        getmInstance().postStringRequest(url, callback, json, action);
    }

    /**
     * postFile请求
     *
     * @param url      请求url
     * @param callback 请求回调
     * @param filePath 请求file
     */
    public static void postFile(String url, String filePath, int action, final ResultCallback callback) {
        getmInstance().postFileRequest(url, callback, filePath, action);

    }


    /**
     * http请求回调类,回调方法在UI线程中执行
     *
     * @param <T>
     */
    public static abstract class ResultCallback<T> {

        Type mType;

        public ResultCallback() {
            mType = getSuperclassTypeParameter(getClass());
        }

        static Type getSuperclassTypeParameter(Class<?> subclass) {
            Type superclass = subclass.getGenericSuperclass();//返回父类的类型
            if (superclass instanceof Class) {
                throw new RuntimeException("Missing type parameter.");
            }
            ParameterizedType parameterized = (ParameterizedType) superclass;
            return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
        }

        /**
         * 请求成功回调
         *
         * @param response
         */
        public abstract void onSuccess(T response, int action);

        /**
         * 请求失败回调
         *
         * @param e
         */
        public abstract void onFailure(Exception e);
    }

    /**
     * post请求参数类
     */
    public static class Param {

        String key;//请求的参数
        String value;//参数的值

        public Param() {
        }

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }

    }


}  