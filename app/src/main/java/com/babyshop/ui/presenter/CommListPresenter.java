package com.babyshop.ui.presenter;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.babyshop.ui.bean.ResultCommlistBean;
import com.babyshop.ui.biz.RefreshBiz;
import com.babyshop.ui.view.ICommlistView;
import com.babyshop.utils.MyOkHttpUtils;

/**
 * Created by admin on 2017/4/11.
 */

public class CommListPresenter {

    ICommlistView iCommListView;
    RefreshBiz refreshBiz;

    public CommListPresenter(ICommlistView iCommListView) {
        this.iCommListView = iCommListView;
        refreshBiz = new RefreshBiz();
    }

    public void getCommList(String url){
        iCommListView.showProgress();
        MyOkHttpUtils.get(url, new MyOkHttpUtils.ResultCallback<ResultCommlistBean>() {
            @Override
            public void onSuccess(ResultCommlistBean response, int action) {
                iCommListView.dismissProgress();
                iCommListView.stopRefresh();
                iCommListView.getCommList(response.data);
            }

            @Override
            public void onFailure(Exception e) {
                iCommListView.dismissProgress();
                iCommListView.stopRefresh();
            }
        });
    }

    public void setRefreshColor(SwipeRefreshLayout swipeRefresh){
        refreshBiz.setRefreshColor(swipeRefresh);
    }

    public void setLoadMore(RecyclerView mRecyclerView, RefreshBiz.OnRecyclerLoadMoreListener l){
        refreshBiz.setRecyclerMore(mRecyclerView, l);
    }

}
