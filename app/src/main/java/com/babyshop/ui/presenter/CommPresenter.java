package com.babyshop.ui.presenter;

import com.babyshop.ui.bean.ResultCommBean;
import com.babyshop.ui.view.ICommView;
import com.babyshop.utils.MyOkHttpUtils;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by admin on 2017/4/13.
 */

public class CommPresenter {

    ICommView iCommView;

    public CommPresenter(ICommView iCommView) {
        this.iCommView = iCommView;
    }

    public void getComm(String url) {
        iCommView.showProgress();
        MyOkHttpUtils.get(url, new MyOkHttpUtils.ResultCallback<ResultCommBean>() {
            @Override
            public void onSuccess(ResultCommBean response, int action) {
                iCommView.dismissProgress();
                iCommView.getComm(response.data);
            }

            @Override
            public void onFailure(Exception e) {
                iCommView.dismissProgress();
            }
        });
    }

}
