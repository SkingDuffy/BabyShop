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
 * 商品列表适配器（其他模块）
 */
public class CommlistAdapter1 extends RecyclerView.Adapter<CommlistAdapter1.MyViewHolder> {
    Context context;
    List<GoodsBean> commlist;

    public CommlistAdapter1(Context context, List<GoodsBean> commlist) {
        this.context = context;
        this.commlist = commlist;
    }

    public void setData(List<GoodsBean> commlist) {
        this.commlist = commlist;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_commlist1, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GoodsBean bean = commlist.get(position);
        GlideUtil.setUrl(context, Url.IMG + bean.pic, holder.iv);
        holder.tv_name.setText(bean.name);
        holder.tv_price.setText(bean.price);
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
