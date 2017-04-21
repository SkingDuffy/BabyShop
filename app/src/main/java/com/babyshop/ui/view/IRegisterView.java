package com.babyshop.ui.view;

/**
 * Created by admin on 2017/4/13.
 */

public interface IRegisterView extends BaseIProgress, BaseIToast {

    void registerSuccess();

    void showProgress(String text);

}
