package com.babyshop.ui.view;

import com.babyshop.ui.bean.GoodsBean;
import com.babyshop.ui.bean.SortBean;

import java.util.List;

/**
 * Created by admin on 2017/4/11.
 */

public interface IFragmentTwo extends IProgressBase {

    /**
     * 获取分类目录
     * @param response
     */
    void getSortLeft(List<SortBean> response);

    /**
     * 根据分类 获取商品列表
     * @param response
     */
    void getSortRight(List<GoodsBean> response);

}
