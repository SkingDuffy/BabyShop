package com.babyshop.ui.view;

import com.babyshop.ui.bean.OrderBean;

import java.util.List;

/**
 * Created by admin on 2017/4/18.
 */

public interface IOrderlistView extends BaseIProgress {

    void getOrderList(List<OrderBean> orderList);

    void stopRefresh();

}
