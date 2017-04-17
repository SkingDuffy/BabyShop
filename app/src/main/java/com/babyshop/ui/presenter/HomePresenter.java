package com.babyshop.ui.presenter;

import com.babyshop.ui.view.IHomeView;
import com.babyshop.utils.SharedPreferencesUtil;

/**
 * Created by admin on 2017/4/14.
 */

public class HomePresenter {

    IHomeView iHomeView;

    public HomePresenter(IHomeView iHomeView) {
        this.iHomeView = iHomeView;
    }

    public boolean isToLogin() {
        if (SharedPreferencesUtil.getInstance().hasLogin()) {
            return false;
        } else {
            iHomeView.toLoginActivity();
            return true;
        }
    }

}
