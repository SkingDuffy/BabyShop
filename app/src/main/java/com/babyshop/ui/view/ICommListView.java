package com.babyshop.ui.view;

import com.babyshop.ui.bean.GoodsBean;

import java.util.List;

/**
 * Created by admin on 2017/4/11.
 */

public interface ICommlistView {

    /**
     * 获取商品列表
     * @param commlist
     */
    void getCommList(List<GoodsBean> commlist);

    /**
     * 停止刷新动画
     */
    void stopRefresh();

}
