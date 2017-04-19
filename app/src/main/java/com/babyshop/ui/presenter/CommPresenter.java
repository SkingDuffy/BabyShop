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
    ToLoginBiz toLoginBiz;
    String id, userid;

    public CommPresenter(ICommView iCommView, String id) {
        this.iCommView = iCommView;
        this.id = id;
        toLoginBiz = new ToLoginBiz();
        SharedPreferencesUtil shared = SharedPreferencesUtil.getInstance();
        userid = shared.getUserId();
    }

    /**
     * 获取商品
     */
    public void getComm() {
        String suffix = TextUtils.isEmpty(userid) ? "?id=" + id : "?id=" + id + "&userid=" + userid;
        iCommView.showProgress();
        MyOkHttpUtils.get(Url.COMMODITY + suffix, new MyOkHttpUtils.ResultCallback<ResultCommBean>() {
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
    public void putIntoCart(String num) {
        HashMap<String, String> map = new HashMap<>();
        map.put("num", num);
        httpPostBiz(1, map);
    }

    /**
     * 加入收藏
     */
    public void collect(boolean hasCollect) {
        if (!hasCollect) {
            httpPostBiz(2, null);     //收藏
        } else {
            httpPostBiz(3, null);     //取消
        }

    }

    private void httpPostBiz(int type, Map<String, String> map) {
        if (toLoginBiz.isToLogin(iCommView))
            return;
        String url = "";
        switch (type) {
            case 1:
                url = Url.ADD_CART;
                break;
            case 2:
                url = Url.ADD_COLLECT;
                break;
            case 3:
                url = Url.DEL_COLLECT;
                break;
        }
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("userid", userid);
        if (map != null)
            params.putAll(map);
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
