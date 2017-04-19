package com.babyshop.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.babyshop.R;
import com.babyshop.ui.bean.OrderBean;

import java.util.List;

/**
 * Created by admin on 2017/4/18.
 */

public class OrderlistAdapter extends BaseRecyclerAdapter<OrderlistAdapter.MyHolder> {
    Context context;
    List<OrderBean> list;

    public OrderlistAdapter(Context context, List<OrderBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<OrderBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public OrderlistAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.item_orderlist, parent, false));
    }

    @Override
    public void onBindViewHolder(OrderlistAdapter.MyHolder holder, int position) {
        OrderBean bean = list.get(position);
        holder.tv_id.setText("订单编号：" + bean.id);
        holder.tv_price.setText("¥" + bean.totalprice);
        initItemClick(holder, bean);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView tv_id, tv_price;

        public MyHolder(View itemView) {
            super(itemView);
            tv_id = (TextView) itemView.findViewById(R.id.tv_order_id);
            tv_price = (TextView) itemView.findViewById(R.id.tv_order_price);
        }
    }

}
