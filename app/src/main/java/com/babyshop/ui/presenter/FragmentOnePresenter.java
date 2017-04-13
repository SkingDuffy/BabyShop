package com.babyshop.ui.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

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

    /**
     * 获取首页轮播资源
     * @param url
     */
    public void getBannerData(String url){
        iFragmentOne.showProgress();
        MyOkHttpUtils.get(url, new MyOkHttpUtils.ResultCallback<ResultBannerBean>() {
            @Override
            public void onSuccess(ResultBannerBean response, int action) {
                iFragmentOne.dismissProgress();
                for (int i = 0; i < response.data.size(); i++){
                    BannerToCycleBean b = response.data.get(i);
                    b.setUrl(Url.IMG + b.pic);
                }
                iFragmentOne.setBannerRes(response.data);
            }

            @Override
            public void onFailure(Exception e) {
                iFragmentOne.dismissProgress();
            }
        });

    }


    /**
     * 跳转到商品列表
     */
    public void startActivity(Context context, int type){
        context.startActivity(new Intent(context, CommodityListActivity.class).putExtra("type", type));
    }

}
