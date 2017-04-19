package com.babyshop.ui.jeneral;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.babyshop.R;
import com.babyshop.commom.BaseActivity;
import com.babyshop.ui.adapter.OnItemClickListener;
import com.babyshop.ui.adapter.OrderAdapter;
import com.babyshop.ui.adapter.OrderlistAdapter;
import com.babyshop.ui.bean.CartGoodsBean;
import com.babyshop.ui.bean.OrderBean;
import com.babyshop.ui.presenter.OrderListPresenter;
import com.babyshop.ui.presenter.OrderPresenter;
import com.babyshop.ui.view.IOrderView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/18.
 */

public class OrderActivity extends BaseActivity implements IOrderView, SwipeRefreshLayout.OnRefreshListener {

    private OrderPresenter p = new OrderPresenter(this);
    private OrderBean orderBean;
    private RecyclerView rv;
    private OrderAdapter adapter;
    private SwipeRefreshLayout sr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commlist);
        initPre();
        initView();
    }

    private void initPre(){
        initTitleBar("订单详情");
        orderBean = (OrderBean) getIntent().getSerializableExtra("orderBean");
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv_commlist);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new OrderAdapter(this, orderBean);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener<CartGoodsBean>() {
            @Override
            public void onItemClick(View view, CartGoodsBean bean) {
                startActivity(new Intent(OrderActivity.this, CommodityActivity.class)
                        .putExtra("id", bean.id));
            }
        });
        sr = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        p.setRefreshColor(sr);
        sr.setOnRefreshListener(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        adapter.setData(orderBean);
        sr.setRefreshing(false);
    }

    @Override
    public void getOrderList(List<CartGoodsBean> orderList) {

    }

}
