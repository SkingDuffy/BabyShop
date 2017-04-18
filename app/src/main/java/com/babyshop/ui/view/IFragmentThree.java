package com.babyshop.ui.view;

import com.babyshop.ui.bean.CartGoodsBean;

import java.util.List;

/**
 * Created by admin on 2017/4/11.
 */

public interface IFragmentThree extends BaseIProgress {

    /**
     * 获取商品列表
     */
    void getCartList(List<CartGoodsBean> cartlist);

    /**
     * 停止刷新动画
     */
    void stopRefresh();

    void toGenerateOrderActivity();

}
