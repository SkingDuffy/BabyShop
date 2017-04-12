package com.babyshop.ui.presenter;

import android.content.Context;
import android.content.Intent;

import com.babyshop.commom.Url;
import com.babyshop.ui.jeneral.CommodityListActivity;
import com.babyshop.ui.view.IFragmentOne;
import com.babyshop.ui.bean.ResultBannerBean;
import com.babyshop.ui.bean.BannerToCycleBean;
import com.babyshop.utils.LLog;
import com.babyshop.utils.MyOkHttpUtils;

/**
 * Created by admin on 2017/4/11.
 */

public class FragmentOnePresenter {
    final String TAG = "FragmentOnePresenter";

    IFragmentOne iFragmentOne;

    public FragmentOnePresenter(IFragmentOne iFragmentOne) {
        this.iFragmentOne = iFragmentOne;
    }

    public void getBannerRes(String url){
        iFragmentOne.showProgress();
        MyOkHttpUtils.get(url, new MyOkHttpUtils.ResultCallback<ResultBannerBean>() {
            @Override
            public void onSuccess(ResultBannerBean response, int action) {
                iFragmentOne.dismissProgress();
                LLog.e(response.data.toString());
                for (int i = 0; i < response.data.size(); i++){
                    BannerToCycleBean b = response.data.get(i);
                    b.setUrl(Url.IMG + b.pic);
                }
                LLog.e(response.data.toString());
                iFragmentOne.setBannerRes(response.data);
            }

            @Override
            public void onFailure(Exception e) {
                iFragmentOne.dismissProgress();
            }
        });

    }


    public void startActivity(Context c, int item){
        c.startActivity(new Intent(c, CommodityListActivity.class).putExtra("item", item));
    }

}
