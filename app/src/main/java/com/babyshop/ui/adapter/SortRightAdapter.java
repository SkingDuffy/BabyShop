package com.babyshop.ui.adapter;

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
 * Created by admin on 2017/4/12.
 */

public class SortRightAdapter extends BaseRecyclerAdapter<SortRightAdapter.MyHolder> {
    Context context;
    List<GoodsBean> list;

    public SortRightAdapter(Context context, List<GoodsBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<GoodsBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.item_sort_right, parent, false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        GoodsBean bean = list.get(position);
        holder.name.setText(bean.name);
        holder.price.setText("¥" + bean.price);
        GlideUtil.setUrl(context, Url.IMG + bean.pic.split(",")[0], holder.iv);
        initItemClick(holder, bean);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView name, price;
        ImageView iv;
        public MyHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.tv_sort_name);
            this.price = (TextView) view.findViewById(R.id.tv_sort_price);
            this.iv = (ImageView) view.findViewById(R.id.iv_sort_img);
        }
    }


}
