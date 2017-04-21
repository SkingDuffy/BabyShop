package com.babyshop.ui.view;

import com.babyshop.ui.bean.UserBean;

/**
 * Created by admin on 2017/4/13.
 */

public interface ILoginView extends BaseIProgress, BaseIToast {

    void loginSuccess(UserBean userBean);

    void showProgress(String text);

}
