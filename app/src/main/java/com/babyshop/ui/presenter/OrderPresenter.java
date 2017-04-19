package com.babyshop.ui.presenter;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.babyshop.commom.Url;
import com.babyshop.ui.bean.ResultCartlist;
import com.babyshop.ui.bean.ResultOrderBean;
import com.babyshop.ui.biz.RefreshBiz;
import com.babyshop.ui.view.IOrderView;
import com.babyshop.ui.view.IOrderlistView;
import com.babyshop.utils.MyOkHttpUtils;
import com.babyshop.utils.SharedPreferencesUtil;

/**
 * Created by admin on 2017/4/11.
 */

public class OrderPresenter {

    IOrderView iOrderView;
    RefreshBiz refreshBiz;

    public OrderPresenter(IOrderView iOrderView) {
        this.iOrderView = iOrderView;
        refreshBiz = new RefreshBiz();
    }

    public void getOrder(){

    }

    public void setRefreshColor(SwipeRefreshLayout swipeRefresh){
        refreshBiz.setRefreshColor(swipeRefresh);
    }

}
