package com.babyshop.ui.presenter;

import com.babyshop.ui.biz.ToLoginBiz;
import com.babyshop.ui.view.IHomeView;

/**
 * Created by admin on 2017/4/14.
 */

public class HomePresenter {

    IHomeView iHomeView;
    ToLoginBiz toLoginBiz;

    public HomePresenter(IHomeView iHomeView) {
        this.iHomeView = iHomeView;
        toLoginBiz = new ToLoginBiz();
    }

    public boolean isToLogin() {
        return toLoginBiz.isToLogin(iHomeView);
    }

}
