package com.babyshop.ui.jeneral;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.babyshop.R;
import com.babyshop.commom.BaseActivity;
import com.babyshop.commom.Url;
import com.babyshop.ui.bean.GoodsBean;
import com.babyshop.ui.presenter.CommListPresenter;
import com.babyshop.ui.view.ICommlistView;
import com.babyshop.utils.LLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/11.
 */

public class CommodityListActivity extends BaseActivity implements ICommlistView, SwipeRefreshLayout.OnRefreshListener {

    CommListPresenter p = new CommListPresenter(this);

    SwipeRefreshLayout swipeRefresh;
    RecyclerView rv;
    HomeAdapter adapter;
    List<GoodsBean> commlist = new ArrayList<>();
    //当前页
    private int start = 1;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_commlist);
        LLog.e("------- initTitleBar");
        initTitleBar("限时抢购");
        LLog.e("------- initView");
        initView();
    }

    private void initView() {

        rv = (RecyclerView) findViewById(R.id.rv_commlist);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HomeAdapter(commlist);
        rv.setAdapter(adapter);
        onRefresh();
        setRecyclerMore(rv);

//        swipeRefresh.setRefreshing(true);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        swipeRefresh.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        start = 1;
        String url = Url.HOME_LIST + "?type=" + getIntent().getIntExtra("item", 0)
                + "&start=" + start + "&size=5";
        p.getCommList(url);
    }

    @Override
    public void getCommList(List<GoodsBean> commlist) {
        //将网络获取的数据添加到缓存列表中
        this.commlist.addAll(commlist);
        adapter.addData(this.commlist);
    }

    @Override
    public void stopRefresh() {
        swipeRefresh.setRefreshing(false);
    }

//    @Override
//    public void onClick(View view) {
//
//        switch (view.getId()){
//            case R.id.iv_back:
//                finish();
//            break;
//        }
//    }


    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
        List<GoodsBean> commlist;

        public HomeAdapter(List<GoodsBean> commlist) {
            this.commlist = commlist;
        }

        public void addData(List<GoodsBean> commlist) {
            this.commlist = commlist;
            notifyItemRangeChanged(this.commlist.size(), commlist.size());
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    CommodityListActivity.this).inflate(R.layout.item_commlist, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(commlist.get(position).name);
        }

        @Override
        public int getItemCount() {
            return commlist.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.tv_name);
            }
        }
    }


    private int lastPositionItem;
    /**
     * 设置RecyclerView 控件
     * 实现RecyclerView分页加载
     *
     * @param mRecyclerView
     */
    public void setRecyclerMore(RecyclerView mRecyclerView) {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        lastPositionItem == adapter.getItemCount() - 1) {
                    loadRecyclerMore();
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


    /**
     * 分页加载RecyclerView
     */
    public void loadRecyclerMore() {
        String url = Url.HOME_LIST + "?type=" + getIntent().getIntExtra("item", 0)
                + "&start=" + (start++) + "&size=5";
        LLog.e("-----" + url);
        p.getCommList(url);
    }

}
