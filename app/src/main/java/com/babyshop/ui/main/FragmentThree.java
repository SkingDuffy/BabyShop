package com.babyshop.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.babyshop.R;
import com.babyshop.ui.adapter.CartlistAdapter;
import com.babyshop.ui.adapter.OnItemClickListener;
import com.babyshop.ui.bean.CartGoodsBean;
import com.babyshop.ui.bean.OrderBean;
import com.babyshop.ui.jeneral.CommodityActivity;
import com.babyshop.ui.jeneral.GenerateOrderActivity;
import com.babyshop.ui.presenter.FragmentThreePresenter;
import com.babyshop.ui.view.IFragmentThree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/10.
 */

public class FragmentThree extends Fragment implements IFragmentThree, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    public static FragmentThree mfragmentThree = null;
    private HomeActivity homeInstance;
    private FragmentThreePresenter p = new FragmentThreePresenter(this);
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView rv;
    private CartlistAdapter adapter;
    private List<CartGoodsBean> cartlist = new ArrayList<>();
    private TextView tv_num, tv_price, tv_score;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mfragmentThree = this;
        homeInstance = (HomeActivity) getActivity();
        initView(view);
    }

    private void initView(View view) {
        tv_num = (TextView) view.findViewById(R.id.tv_total_num_cart);
        tv_price = (TextView) view.findViewById(R.id.tv_total_price_cart);
        tv_score = (TextView) view.findViewById(R.id.tv_score_cart);
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        p.setRefreshColor(swipeRefresh);
        swipeRefresh.setOnRefreshListener(this);
        rv = (RecyclerView) view.findViewById(R.id.rv_cartlist);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setNestedScrollingEnabled(false);
        adapter = new CartlistAdapter(getActivity(), cartlist, this);
        rv.setAdapter(adapter);
        onRefresh();
        adapter.setOnItemClickListener(new OnItemClickListener<CartGoodsBean>() {
            @Override
            public void onItemClick(View view, CartGoodsBean bean) {
                startActivity(new Intent(getActivity(), CommodityActivity.class)
                        .putExtra("id", bean.id));
            }
        });
        view.findViewById(R.id.bt_settlement).setOnClickListener(this);
    }

    @Override
    public void onRefresh() {
        p.getCartList();
    }

    @Override
    public void getCartList(List<CartGoodsBean> cartlist) {
        if (cartlist.size() == 0 || cartlist == null){
            this.cartlist.clear();
            setDefaultText();
        } else {
            this.cartlist = cartlist;
            setListText(cartlist);
        }
        adapter.setData(this.cartlist);
    }

    @Override
    public void stopRefresh() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onAddOrderSuccess(String orderId) {
        OrderBean orderBean = p.getOrderBean(orderId, cartlist);
        startActivity(new Intent(getActivity(), GenerateOrderActivity.class)
                .putExtra("orderBean", orderBean));
        cartlist.clear();
        adapter.setData(cartlist);
        setDefaultText();
    }

    private void setDefaultText(){
        tv_num.setText("--");
        tv_price.setText("--");
        tv_score.setText("--");
    }

    private void setListText(List<CartGoodsBean> cartlist){
        tv_num.setText("" + p.getTotalNum(cartlist));
        tv_price.setText("¥" + p.getTotalPrice(cartlist));
        tv_score.setText("18");
    }

    @Override
    public void onClickDelGoodsByCartId(String cartId) {
        p.delByCartId(cartId);
    }

    @Override
    public void onDelSuccess() {
        onRefresh();
    }

    @Override
    public void showProgress() {
        homeInstance.showProgress();
    }

    @Override
    public void dismissProgress() {
        homeInstance.dismissProgress();
    }

    @Override
    public void showToast(String msg) {
        homeInstance.showToast(msg);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_settlement:
                p.settlement(cartlist);
                break;
        }
    }


}
