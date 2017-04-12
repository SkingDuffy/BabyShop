package com.babyshop.ui.view;

import com.babyshop.ui.bean.GoodsBean;

import java.util.List;

/**
 * Created by admin on 2017/4/11.
 */

public interface ICommlistView {

    void getCommList(List<GoodsBean> commlist);

    void stopRefresh();

}
