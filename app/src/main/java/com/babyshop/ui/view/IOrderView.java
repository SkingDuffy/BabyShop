package com.babyshop.ui.view;

import com.babyshop.ui.bean.CartGoodsBean;
import com.babyshop.ui.bean.OrderBean;

import java.util.List;

/**
 * Created by admin on 2017/4/18.
 */

public interface IOrderView extends BaseIProgress {

    void getOrderList(List<CartGoodsBean> orderList);

}
