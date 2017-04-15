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

    public boolean hasLogin() {
        if (SharedPreferencesUtil.getInstance().hasLogin()) {
            return true;
        } else {
            iHomeView.toLoginActivity();
            return false;
        }
    }

}
