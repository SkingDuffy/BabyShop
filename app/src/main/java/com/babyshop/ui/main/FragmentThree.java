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

import com.babyshop.R;
import com.babyshop.commom.Url;
import com.babyshop.ui.adapter.CartlistAdapter;
import com.babyshop.ui.bean.CartGoodsBean;
import com.babyshop.ui.jeneral.GenerateOrderActivity;
import com.babyshop.ui.jeneral.LoginActivity;
import com.babyshop.ui.presenter.FragmentThreePresenter;
import com.babyshop.ui.view.IFragmentThree;
import com.babyshop.utils.LLog;
import com.babyshop.utils.SharedPreferencesUtil;
import com.babyshop.widget.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/10.
 */

public class FragmentThree extends Fragment implements IFragmentThree, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private HomeActivity homeInstance;
    private FragmentThreePresenter p = new FragmentThreePresenter(this);
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView rv;
    private CartlistAdapter adapter;
    private List<CartGoodsBean> cartlist = new ArrayList<>();
//    private String userid;
    private SharedPreferencesUtil shared = SharedPreferencesUtil.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeInstance = (HomeActivity) getActivity();
        initView(view);
    }

    private void initView(View view) {
        swipeRefresh = p.initSwipeRefresh(view, R.id.swipe_layout);
        swipeRefresh.setOnRefreshListener(this);
        rv = (RecyclerView) view.findViewById(R.id.rv_cartlist);
        rv.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        rv.setNestedScrollingEnabled(false);
        adapter = new CartlistAdapter(getActivity(), cartlist);
        rv.setAdapter(adapter);
        if (shared.hasLogin()){
            onRefresh();
        } else {
            startActivityForResult(new Intent(getActivity(), LoginActivity.class), 0);
        }
        view.findViewById(R.id.bt_settlement).setOnClickListener(this);
    }

    @Override
    public void onRefresh() {
        String url = Url.CART_LIST + "?userid=" + shared.getUserId();
        p.getCommList(url);
    }

    @Override
    public void getCartList(List<CartGoodsBean> cartlist) {
        adapter.setData(cartlist);
    }

    @Override
    public void stopRefresh() {
        swipeRefresh.setRefreshing(false);
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_settlement:
                startActivity(new Intent(getActivity(), GenerateOrderActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == getActivity().RESULT_OK){
            onRefresh();
        }
    }
}
