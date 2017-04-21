package com.babyshop.ui.adapter;

/**
 * Created by admin on 2017/4/12.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.babyshop.R;
import com.babyshop.commom.Url;
import com.babyshop.ui.bean.GoodsBean;
import com.babyshop.utils.GlideUtil;

import java.util.List;

/**
 * 商品列表适配器（限时抢购）
 */
public class CommlistAdapter0 extends BaseRecyclerAdapter<CommlistAdapter0.MyViewHolder> {
    Context context;
    List<GoodsBean> commlist;

    public CommlistAdapter0(Context context, List<GoodsBean> commlist) {
        this.context = context;
        this.commlist = commlist;
    }

    public void setData(List<GoodsBean> commlist) {
        this.commlist = commlist;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_commlist0, parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GoodsBean bean = commlist.get(position);
        GlideUtil.setUrl(context, Url.IMG + bean.pic.split(",")[0], holder.iv);
        holder.tv_name.setText(bean.name);
        holder.tv_price.setText("¥" + bean.price);
        initItemClick(holder, bean);
    }

    @Override
    public int getItemCount() {
        return commlist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv_name, tv_price;

        public MyViewHolder(View view) {
            super(view);
            iv = (ImageView) view.findViewById(R.id.iv_img);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_price = (TextView) view.findViewById(R.id.tv_price);
        }
    }
}
