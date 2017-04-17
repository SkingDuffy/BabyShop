package com.babyshop.ui.biz;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by admin on 2017/4/14.
 */

public class RefreshBiz {

    private int lastPositionItem;
    /**
     * 设置RecyclerView 控件
     * 实现RecyclerView分页加载
     * @param mRecyclerView
     */
    public void setOnRecyclerLoadMoreListener(final RecyclerView mRecyclerView, final OnRecyclerLoadMoreListener l) {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        lastPositionItem == mRecyclerView.getAdapter().getItemCount() - 1) {
                    l.onLoadMore();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                lastPositionItem = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }
        });
    }

    public interface OnRecyclerLoadMoreListener {
        void onLoadMore();
    }


    public void setRefreshColor(SwipeRefreshLayout swipeRefresh){
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }

}
