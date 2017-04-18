package com.babyshop.ui.presenter;

import android.text.TextUtils;

import com.babyshop.commom.Constant;
import com.babyshop.commom.Url;
import com.babyshop.ui.bean.GoodsBean;
import com.babyshop.ui.bean.ResultBean;
import com.babyshop.ui.bean.ResultCommBean;
import com.babyshop.ui.bean.TestResultBean;
import com.babyshop.ui.biz.ToLoginBiz;
import com.babyshop.ui.view.ICommView;
import com.babyshop.utils.MyOkHttpUtils;
import com.babyshop.utils.SharedPreferencesUtil;
import com.babyshop.widget.cycleImage.BaseBannerBean;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/4/13.
 */

public class CommPresenter {

    ICommView iCommView;
    SharedPreferencesUtil shared;
    ToLoginBiz toLoginBiz;

    public CommPresenter(ICommView iCommView, SharedPreferencesUtil shared) {
        this.iCommView = iCommView;
        this.shared = shared;
        toLoginBiz = new ToLoginBiz();
    }

    /**
     * 获取商品
     *
     * @param url
     */
    public void getComm(String url) {
        iCommView.showProgress();
        MyOkHttpUtils.get(url, new MyOkHttpUtils.ResultCallback<ResultCommBean>() {
            @Override
            public void onSuccess(ResultCommBean response, int action) {
                iCommView.dismissProgress();
                iCommView.getComm(response.data);
            }

            @Override
            public void onFailure(Exception e) {
                iCommView.dismissProgress();
            }
        });
    }

    /**
     * 加入购物车
     */
    public void putIntoCart(String id, String num) {
        httpPostBiz(id, num);
    }

    /**
     * 加入收藏
     */
    public void collect(String id) {
        httpPostBiz(id, "");
    }

    private void httpPostBiz(String id, String num) {
        if (toLoginBiz.isToLogin(iCommView))
            return;
        String url = "";
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("userid", shared.getUserId());
        if (!TextUtils.isEmpty(num)) {
            params.put("num", num);
            url = Url.ADD_CART;
        } else {
            url = Url.ADD_COLLECT;
        }
        iCommView.showProgress();
        MyOkHttpUtils.post(url, params, new MyOkHttpUtils.ResultCallback<ResultBean>() {
            @Override
            public void onSuccess(ResultBean response, int action) {
                iCommView.dismissProgress();
                iCommView.showToast(response.message);
            }

            @Override
            public void onFailure(Exception e) {
                iCommView.dismissProgress();
                iCommView.showToast(e.toString());
            }
        });
    }


    /**
     * 将数据转换成轮播bean模型
     */
    public List<BaseBannerBean> getBannerList(GoodsBean bean) {
        List<BaseBannerBean> bannerList = new ArrayList<>();
        BaseBannerBean b1 = new BaseBannerBean();
        b1.setUrl(Url.IMG + bean.pic);
        bannerList.add(b1);
        return bannerList;
    }


}
