package com.babyshop.ui.presenter;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.babyshop.commom.Url;
import com.babyshop.ui.bean.ResultCommlistBean;
import com.babyshop.ui.biz.RefreshBiz;
import com.babyshop.ui.view.ICommlistView;
import com.babyshop.utils.MyOkHttpUtils;
import com.babyshop.utils.SharedPreferencesUtil;

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

    public void getCommList(int type, int start, int size){
        String url = "";
        if (type == 5 || type == 6) {
            String suffix = "?userid=" + SharedPreferencesUtil.getInstance().getUserId() +
                    "&start=" + start + "&size=" + size;
            if (type == 5) {
                url = Url.MY_HISTORY + suffix;
            } else if (type == 6) {
                url = Url.MY_COLLECT + suffix;
            }
        } else {
            url = Url.HOME_LIST +
                    "?type=" + type +
                    "&start=" + start +
                    "&size=" + size;
        }
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

    public void setOnRecyclerLoadMoreListener(RecyclerView mRecyclerView, RefreshBiz.OnRecyclerLoadMoreListener l){
        refreshBiz.setOnRecyclerLoadMoreListener(mRecyclerView, l);
    }

    public String initTitle(int type){
        String title = "";
        switch (type) {
            case 0:
                title = "限时抢购";
                break;
            case 1:
                title = "促销快报";
                break;
            case 2:
                title = "新品上架";
                break;
            case 3:
                title = "热卖单品";
                break;
            case 4:
                title = "推荐品牌";
                break;
            case 5:
                title = "最近浏览";
                break;
            case 6:
                title = "我的收藏";
                break;
        }
        return title;
    }

}
