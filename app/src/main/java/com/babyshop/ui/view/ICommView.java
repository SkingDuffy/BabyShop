package com.babyshop.ui.view;

import com.babyshop.ui.bean.GoodsBean;

/**
 * Created by admin on 2017/4/13.
 */

public interface ICommView extends BaseIProgress, BaseIToast {

    void getComm(GoodsBean bean);

    void toLoginActivity();

}
