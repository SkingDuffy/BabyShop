package com.babyshop.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by admin on 2017/4/15.
 */

public abstract class MyRecyclerAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(getItemData(position));
        holder.itemView.setOnClickListener(this);
    }

    public abstract Object getItemData(int position);

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null){
            onItemClickListener.onItemClick(view, view.getTag());
        }
    }

    private OnItemClickListener onItemClickListener = null;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    /**
     * 自定义点击监听
     */
    public interface OnItemClickListener<T>{
        void onItemClick(View view, T bean);
    }

}
