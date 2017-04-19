package com.babyshop.ui.presenter;

import com.babyshop.commom.Url;
import com.babyshop.ui.bean.ResultBean;
import com.babyshop.ui.view.IHistoryView;
import com.babyshop.utils.MyOkHttpUtils;
import com.babyshop.utils.SharedPreferencesUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/4/19.
 */

public class HistoryPresenter {

    IHistoryView iHistoryView;

    public HistoryPresenter(IHistoryView iHistoryView) {
        this.iHistoryView = iHistoryView;
    }

    public void delHistory(){
        iHistoryView.showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("userid", SharedPreferencesUtil.getInstance().getUserId());
        MyOkHttpUtils.post(Url.DEL_HISTORY, params,new MyOkHttpUtils.ResultCallback<ResultBean>() {
            @Override
            public void onSuccess(ResultBean response, int action) {
                iHistoryView.dismissProgress();
                iHistoryView.showToast(response.message);
                if (response.flag)
                    iHistoryView.onDelSuccess();
            }

            @Override
            public void onFailure(Exception e) {
                iHistoryView.dismissProgress();
            }
        });
    }

}
