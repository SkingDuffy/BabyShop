package com.babyshop.ui.view;

import com.babyshop.ui.bean.BannerToCycleBean;

import java.util.List;

/**
 * Created by admin on 2017/4/11.
 */

public interface IFragmentOne {

    void setBannerRes(List<BannerToCycleBean> response);

    void showProgress();
    void dismissProgress();

}
