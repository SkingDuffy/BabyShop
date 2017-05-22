package com.babyshop.ui.general;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.babyshop.R;
import com.babyshop.commom.BaseActivity;
import com.babyshop.ui.adapter.BaseRecyclerAdapter;
import com.babyshop.ui.adapter.CommlistAdapter0;
import com.babyshop.ui.adapter.CommlistAdapter1;
import com.babyshop.ui.adapter.OnItemClickListener;
import com.babyshop.ui.bean.GoodsBean;
import com.babyshop.ui.biz.RefreshBiz;
import com.babyshop.ui.presenter.CommListPresenter;
import com.babyshop.ui.presenter.HistoryPresenter;
import com.babyshop.ui.view.ICommlistView;
import com.babyshop.ui.view.IHistoryView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/11.
 */

public class CommodityListActivity extends BaseActivity implements ICommlistView, IHistoryView, SwipeRefreshLayout.OnRefreshListener, RefreshBiz.OnRecyclerLoadMoreListener {

    private CommListPresenter p = new CommListPresenter(this);
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView rv;
    private BaseRecyclerAdapter adapter;
    private List<GoodsBean> commlist = new ArrayList<>();
    //当前页
    private int start = 1;
    //商品列表 type:0 限时抢购，1 促销快报，2 新品上架，3 热卖单品，4 推荐品牌，，5 浏览历史，6 收藏列表
    private int type = 0;
    //每页获取的商品数量
    private final int SIZE = 8;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commlist);
        initPre();
        initView();
    }

    /**
     * 初始化type，设置标题
     */
    private void initPre() {
        type = getIntent().getIntExtra("type", 0);
        initTitleBar(p.initTitle(type));
        if (type == 5){
            final HistoryPresenter pp = new HistoryPresenter(this);
            TextView tv_right = (TextView) findViewById(R.id.tv_title_right);
            tv_right.setVisibility(View.VISIBLE);
            tv_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pp.delHistory();
                }
            });
        }
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv_commlist);
        rv.setLayoutManager(new LinearLayoutManager(this));
        if (type == 0) {
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
        p.setOnRecyclerLoadMoreListener(rv, this);
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
        p.getCommList(type, start, SIZE);
    }

    @Override
    public void getCommList(List<GoodsBean> commlist) {
        //将网络获取的数据添加到缓存列表中
        if (start == 1) {
            this.commlist = commlist;
        } else {
            this.commlist.addAll(commlist);
        }
        if (type == 0) {
            ((CommlistAdapter0) adapter).setData(this.commlist);
        } else {
            ((CommlistAdapter1) adapter).setData(this.commlist);
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
    public void onLoadMore() {
        p.getCommList(type, start, SIZE);
    }

    /**
     * 删除浏览历史
     */
    @Override
    public void onDelSuccess() {
        onRefresh();
    }
}
