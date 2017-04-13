package com.babyshop.ui.presenter;

import android.content.Context;
import android.content.Intent;

import com.babyshop.commom.Url;
import com.babyshop.ui.bean.BannerToCycleBean;
import com.babyshop.ui.bean.ResultBannerBean;
import com.babyshop.ui.bean.ResultCommlistBean;
import com.babyshop.ui.bean.ResultSortBean;
import com.babyshop.ui.bean.SortBean;
import com.babyshop.ui.jeneral.CommodityListActivity;
import com.babyshop.ui.view.IFragmentOne;
import com.babyshop.ui.view.IFragmentTwo;
import com.babyshop.utils.LLog;
import com.babyshop.utils.MyOkHttpUtils;

/**
 * Created by admin on 2017/4/11.
 */

public class FragmentTwoPresenter {

    IFragmentTwo iFragmentTwo;

    public FragmentTwoPresenter(IFragmentTwo iFragmentTwo) {
        this.iFragmentTwo = iFragmentTwo;
    }

    /**
     * 获取分类目录
     * @param url
     */
    public void getSortLeft(String url){
        iFragmentTwo.showProgress();
        MyOkHttpUtils.get(url, new MyOkHttpUtils.ResultCallback<ResultSortBean>() {
            @Override
            public void onSuccess(ResultSortBean response, int action) {
                iFragmentTwo.dismissProgress();
                iFragmentTwo.getSortLeft(response.data);
            }

            @Override
            public void onFailure(Exception e) {
                iFragmentTwo.dismissProgress();
            }
        });
    }

    /**
     * 根据分类获取商品列表
     * @param url
     */
    public void getSortRight(String url){
        iFragmentTwo.showProgress();
        MyOkHttpUtils.get(url, new MyOkHttpUtils.ResultCallback<ResultCommlistBean>() {
            @Override
            public void onSuccess(ResultCommlistBean response, int action) {
                iFragmentTwo.dismissProgress();
                iFragmentTwo.getSortRight(response.data);
            }

            @Override
            public void onFailure(Exception e) {
                iFragmentTwo.dismissProgress();
            }
        });
    }

}
