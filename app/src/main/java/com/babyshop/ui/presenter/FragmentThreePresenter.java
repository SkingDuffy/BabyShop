package com.babyshop.ui.presenter;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.babyshop.ui.bean.ResultCartlist;
import com.babyshop.ui.biz.RefreshBiz;
import com.babyshop.ui.view.IFragmentThree;
import com.babyshop.utils.MyOkHttpUtils;
import com.babyshop.utils.SharedPreferencesUtil;

/**
 * Created by admin on 2017/4/14.
 */

public class FragmentThreePresenter {

    IFragmentThree iFragmentThree;

    RefreshBiz refreshBiz;

    public FragmentThreePresenter(IFragmentThree iFragmentThree) {
        this.iFragmentThree = iFragmentThree;
        this.refreshBiz = new RefreshBiz();
    }

    public void getCommList(String url){
        iFragmentThree.showProgress();
        MyOkHttpUtils.get(url, new MyOkHttpUtils.ResultCallback<ResultCartlist>() {
            @Override
            public void onSuccess(ResultCartlist response, int action) {
                iFragmentThree.dismissProgress();
                iFragmentThree.stopRefresh();
                iFragmentThree.getCartList(response.data);
            }

            @Override
            public void onFailure(Exception e) {
                iFragmentThree.dismissProgress();
                iFragmentThree.stopRefresh();
            }
        });
    }

    public SwipeRefreshLayout initSwipeRefresh(View view, int res){
        return new RefreshBiz().init(view, res);
    }

    public void setLoadMore(RecyclerView mRecyclerView, RefreshBiz.LoadRecyclerMoreListener l){
        refreshBiz.setRecyclerMore(mRecyclerView, l);
    }

}
