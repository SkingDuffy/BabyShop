package com.babyshop.ui.general;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.babyshop.R;
import com.babyshop.commom.BaseActivity;
import com.babyshop.ui.adapter.OnItemClickListener;
import com.babyshop.ui.adapter.OrderlistAdapter;
import com.babyshop.ui.bean.OrderBean;
import com.babyshop.ui.presenter.OrderListPresenter;
import com.babyshop.ui.view.IOrderlistView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/18.
 */

public class OrderListActivity extends BaseActivity implements IOrderlistView, SwipeRefreshLayout.OnRefreshListener {

    private OrderListPresenter p = new OrderListPresenter(this);
    private List<OrderBean> list = new ArrayList<>();
    private RecyclerView rv;
    private OrderlistAdapter adapter;
    private SwipeRefreshLayout sr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commlist);
        initPre();
        initView();
    }

    private void initPre() {
        initTitleBar("订单列表");
        TextView tv_right = (TextView) findViewById(R.id.tv_title_right);
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.delOrderList();
            }
        });
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv_commlist);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new OrderlistAdapter(this, list);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener<OrderBean>() {
            @Override
            public void onItemClick(View view, OrderBean bean) {
                startActivity(new Intent(OrderListActivity.this, OrderActivity.class)
                        .putExtra("orderBean", bean));
            }
        });
        sr = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        p.setRefreshColor(sr);
        sr.setOnRefreshListener(this);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        p.getOrderList();
    }

    @Override
    public void getOrderList(List<OrderBean> orderList) {
        adapter.setData(list = orderList);
    }

    @Override
    public void onDelSuccess() {
        onRefresh();
    }

    @Override
    public void stopRefresh() {
        sr.setRefreshing(false);
    }

}
