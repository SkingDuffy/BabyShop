package com.babyshop.ui.adapter;

import android.view.View;

/**
 * 自定义点击监听 for recycler view adapter
 */

public interface OnItemClickListener<T> {
    void onItemClick(View view, T bean);
}
