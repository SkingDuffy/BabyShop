package com.babyshop.ui.view;

import com.babyshop.ui.bean.GoodsBean;

import java.util.List;

/**
 * Created by admin on 2017/4/11.
 */

public interface IHistoryView extends BaseIProgress, BaseIToast {

    /**
     * 删除所有浏览记录
     */
    void onDelSuccess();

}
