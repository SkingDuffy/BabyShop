package com.babyshop.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.babyshop.R;
import com.babyshop.ui.bean.SortBean;

import java.util.List;

/**
 * Created by admin on 2017/4/12.
 */

public class SortLeftAdapter extends RecyclerView.Adapter<SortLeftAdapter.MyHolder> implements View.OnClickListener {
    private Context context;
    private List<SortBean> list;
    /**
     * 缓存上次点击的itemView
     */
    private View cacheView = null;

    public SortLeftAdapter(Context context, List<SortBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<SortBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sort_left, parent, false);
        MyHolder vh = new MyHolder(view);
        // 为创建的itemView注册点击事件
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        SortBean bean = list.get(position);
        holder.tv_sort.setText(bean.name);
        // 将数据保存到itemView的tag中，以便回调使用
        holder.itemView.setTag(bean);
        // 设置默认选中首个目录
        if (position == 0){
            cacheView = holder.itemView;
            onItemClickListener.onItemClick(holder.itemView, bean);
            cacheView.setBackgroundResource(R.color.bg_base);
            holder.itemView.setBackgroundResource(R.color.white);
            cacheView = holder.itemView;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null){
            if ( cacheView != view){
                onItemClickListener.onItemClick(view, view.getTag());
                if (cacheView != null)
                    cacheView.setBackgroundResource(R.color.bg_base);
                view.setBackgroundResource(R.color.white);
                cacheView = view;
            }
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView tv_sort;
        public MyHolder(View view) {
            super(view);
            this.tv_sort = (TextView) view.findViewById(R.id.tv_sort_left);
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
