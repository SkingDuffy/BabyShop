package com.babyshop.ui.biz;

import com.babyshop.ui.view.BaseIToLogin;
import com.babyshop.utils.SharedPreferencesUtil;

/**
 * Created by admin on 2017/4/17.
 */

public class ToLoginBiz {

    public boolean isToLogin(BaseIToLogin iToLoginView) {
        if (SharedPreferencesUtil.getInstance().hasLogin()) {
            return false;
        } else {
            iToLoginView.toLoginActivity();
            return true;
        }
    }

}
