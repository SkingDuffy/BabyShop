package com.babyshop.ui.view;

import com.babyshop.ui.bean.UserBean;

/**
 * Created by admin on 2017/4/13.
 */

public interface ILoginView extends IProgressBase {

    void loginResponse(UserBean userBean);

    void registerResponse(String url);

    void showProgress(String text);

}
