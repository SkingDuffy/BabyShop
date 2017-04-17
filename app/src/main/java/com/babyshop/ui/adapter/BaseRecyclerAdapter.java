package com.babyshop.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * TODO - 暂未用到，待完善
 */

public abstract class BaseRecyclerAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements View.OnClickListener {


    protected OnItemClickListener onItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    protected void initItemClick(VH holder, Object bean){
        holder.itemView.setTag(bean);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null){
            onItemClickListener.onItemClick(view, view.getTag());
        }
    }


}
