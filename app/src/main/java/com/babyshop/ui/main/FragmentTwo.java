package com.babyshop.ui.main;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.babyshop.R;
import com.babyshop.commom.Url;
import com.babyshop.ui.adapter.SortLeftAdapter;
import com.babyshop.ui.adapter.SortRightAdapter;
import com.babyshop.ui.bean.GoodsBean;
import com.babyshop.ui.bean.SortBean;
import com.babyshop.ui.jeneral.CommodityActivity;
import com.babyshop.ui.presenter.FragmentTwoPresenter;
import com.babyshop.ui.view.IFragmentTwo;
import com.babyshop.utils.LLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/10.
 */

public class FragmentTwo extends Fragment implements IFragmentTwo {

    private FragmentTwoPresenter p = new FragmentTwoPresenter(this);
    private RecyclerView rv_left, rv_right;
    private SortLeftAdapter leftAdapter;
    private SortRightAdapter rightAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        rv_left = (RecyclerView) view.findViewById(R.id.rv_sort_left);
        rv_right = (RecyclerView) view.findViewById(R.id.rv_sort_right);
        // 左边目录列表
        rv_left.setLayoutManager(new LinearLayoutManager(getActivity()));
        leftAdapter = new SortLeftAdapter(getActivity(), new ArrayList<SortBean>());
        rv_left.setAdapter(leftAdapter);
        p.getSortLeft(Url.CATEGORY);
        // 右边商品列表
        rv_right.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rightAdapter = new SortRightAdapter(getActivity(), new ArrayList<GoodsBean>());
        rv_right.setAdapter(rightAdapter);
        // 切换目录
        leftAdapter.setOnItemClickListener(new SortLeftAdapter.OnItemClickListener<SortBean>() {
            @Override
            public void onItemClick(View view, SortBean bean) {
//                Toast.makeText(getActivity(), bean.name, Toast.LENGTH_SHORT).show();
                p.getSortRight(Url.QUERY_LIST + "?categoryid=" + bean.pid);
            }
        });
        // 右边商品点击事件
        rightAdapter.setOnItemClickListener(new SortRightAdapter.OnItemClickListener<GoodsBean>() {
            @Override
            public void onItemClick(View view, GoodsBean bean) {
                startActivity(new Intent(getActivity(), CommodityActivity.class)
                        .putExtra("id", bean.id));
            }
        });
    }

    @Override
    public void getSortLeft(List<SortBean> response) {
        leftAdapter.setData(response);
    }

    @Override
    public void getSortRight(List<GoodsBean> response) {
        rightAdapter.setData(response);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }
}
