package com.babyshop.ui.jeneral;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.babyshop.R;
import com.babyshop.commom.BaseActivity;
import com.babyshop.commom.Url;
import com.babyshop.ui.adapter.BaseRecyclerAdapter;
import com.babyshop.ui.adapter.CommlistAdapter0;
import com.babyshop.ui.adapter.CommlistAdapter1;
import com.babyshop.ui.adapter.OnItemClickListener;
import com.babyshop.ui.bean.GoodsBean;
import com.babyshop.ui.biz.RefreshBiz;
import com.babyshop.ui.presenter.CommListPresenter;
import com.babyshop.ui.view.ICommlistView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/11.
 */

public class CommodityListActivity extends BaseActivity implements ICommlistView, SwipeRefreshLayout.OnRefreshListener, RefreshBiz.OnRecyclerLoadMoreListener {

    private CommListPresenter p = new CommListPresenter(this);
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView rv;
    private BaseRecyclerAdapter adapter;
    private List<GoodsBean> commlist = new ArrayList<>();
    //当前页
    private int start = 1;
    //商品列表 type:0 限时抢购，1 促销快报，2 新品上架，3 热卖单品，4 推荐品牌
    private int type = 0;
    //每页获取的商品数量
    private final int SIZE = 8;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commlist);
        initIntent();
        initView();
    }

    /**
     * 初始化type，设置标题
     */
    private void initIntent() {
        type = getIntent().getIntExtra("type", 0);
        String title = "";
        switch (type){
            case 0: title = "限时抢购";
                break;
            case 1: title = "促销快报";
                break;
            case 2: title = "新品上架";
                break;
            case 3: title = "热卖单品";
                break;
            case 4: title = "推荐品牌";
                break;
        }
        initTitleBar(title);
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv_commlist);
        rv.setLayoutManager(new LinearLayoutManager(this));
        if (type == 0){
            adapter = new CommlistAdapter0(this, commlist);
        } else {
            adapter = new CommlistAdapter1(this, commlist);
        }
        rv.setAdapter(adapter);
        onRefresh();
        adapter.setOnItemClickListener(new OnItemClickListener<GoodsBean>() {
            @Override
            public void onItemClick(View view, GoodsBean bean) {
                startActivity(new Intent(CommodityListActivity.this, CommodityActivity.class)
                        .putExtra("id", bean.id));
            }
        });
        p.setLoadMore(rv, this);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        p.setRefreshColor(swipeRefresh);
        swipeRefresh.setOnRefreshListener(this);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        start = 1;
        String url = Url.HOME_LIST +
                "?type=" + type +
                "&start=" + start +
                "&size=" + SIZE;
        p.getCommList(url);
    }

    @Override
    public void getCommList(List<GoodsBean> commlist) {
        //将网络获取的数据添加到缓存列表中
        if (start == 1){
            this.commlist = commlist;
        } else {
            this.commlist.addAll(commlist);
        }
        if (type == 0){
            ((CommlistAdapter0)adapter).setData(this.commlist);
        } else {
            ((CommlistAdapter1)adapter).setData(this.commlist);
        }
    }

    @Override
    public void stopRefresh() {
        swipeRefresh.setRefreshing(false);
    }

    /**
     * 分页加载RecyclerView
     */
    @Override
    public void recyclerLoadMore() {
        String url = Url.HOME_LIST +
                "?type=" + type +
                "&start=" + (++start) +
                "&size=" + SIZE;
        p.getCommList(url);
    }
}
