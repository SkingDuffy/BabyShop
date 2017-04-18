package com.babyshop.ui.jeneral;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.babyshop.R;
import com.babyshop.commom.BaseActivity;
import com.babyshop.commom.Url;
import com.babyshop.ui.adapter.OrderlistAdapter;
import com.babyshop.ui.bean.OrderBean;
import com.babyshop.ui.presenter.OrderListPresenter;
import com.babyshop.ui.view.IOrderlistView;
import com.babyshop.utils.SharedPreferencesUtil;

import java.util.List;

/**
 * Created by admin on 2017/4/18.
 */

public class OrderListActivity extends BaseActivity implements IOrderlistView, SwipeRefreshLayout.OnRefreshListener {

    private OrderListPresenter p = new OrderListPresenter(this);
    private RecyclerView rv;
    private OrderlistAdapter adapter;
    private SwipeRefreshLayout sr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commlist);
        initTitleBar("订单列表");
        initView();
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv_commlist);
        rv.setLayoutManager(new LinearLayoutManager(this));
        sr = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        p.setRefreshColor(sr);
        sr.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        p.getCommList(Url.MY_ORDER + "?userid=" + SharedPreferencesUtil.getInstance().getUserId());
    }

    @Override
    public void getOrderList(List<OrderBean> orderList) {
        adapter = new OrderlistAdapter(this, orderList);
        rv.setAdapter(adapter);
    }

    @Override
    public void stopRefresh() {
        sr.setRefreshing(false);
    }

}
