package com.babyshop.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babyshop.R;
import com.babyshop.commom.Url;
import com.babyshop.ui.bean.CartGoodsBean;
import com.babyshop.ui.bean.OrderBean;
import com.babyshop.utils.GlideUtil;

/**
 * Created by admin on 2017/4/18.
 */

public class OrderAdapter extends BaseRecyclerAdapter {
    Context context;
    OrderBean orderBean;

    public OrderAdapter(Context context, OrderBean orderBean) {
        this.context = context;
        this.orderBean = orderBean;
    }

    public void setData(OrderBean orderBean){
        this.orderBean = orderBean;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1){
            return new MyHolder1(LayoutInflater.from(context).inflate(R.layout.item_order_header, parent, false));
        } else {
            return new MyHolder2(LayoutInflater.from(context).inflate(R.layout.item_cartlist, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == 1){
            MyHolder1 holder1 = (MyHolder1) holder;
            holder1.tv_id.setText("订单编号：" + orderBean.id);
            holder1.tv_price.setText("¥" + orderBean.totalprice);
        } else {
            MyHolder2 holder2 = (MyHolder2) holder;
            CartGoodsBean bean = orderBean.commodities.get(position);
            holder2.name.setText(bean.name);
            holder2.price.setText("¥" + bean.price);
            GlideUtil.setUrl(context, Url.IMG + bean.pic, holder2.iv);
            holder2.rl.setVisibility(View.GONE);
            initItemClick(holder, bean);
        }

    }

    @Override
    public int getItemCount() {
        return orderBean.commodities.size() + 1;
    }


    class MyHolder1 extends RecyclerView.ViewHolder{
        TextView tv_id, tv_price;

        public MyHolder1(View itemView) {
            super(itemView);
            tv_id = (TextView) itemView.findViewById(R.id.tv_order_id);
            tv_price = (TextView) itemView.findViewById(R.id.tv_order_price);
        }
    }

    class MyHolder2 extends RecyclerView.ViewHolder{
        TextView name, price;
        ImageView iv;
        LinearLayout rl;

        public MyHolder2(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.tv_cart_name);
            this.price = (TextView) view.findViewById(R.id.tv_cart_price);
            this.iv = (ImageView) view.findViewById(R.id.iv_img);
            this.rl = (LinearLayout) view.findViewById(R.id.rl_cart_dele);
        }
    }

}
