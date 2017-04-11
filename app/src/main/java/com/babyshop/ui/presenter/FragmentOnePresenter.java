package com.babyshop.ui.presenter;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

import com.babyshop.commom.Url;
import com.babyshop.ui.view.IFragmentOne;
import com.babyshop.ui.bean.BannerResultBean;
import com.babyshop.ui.bean.BannerToCycleBean;
import com.babyshop.utils.LLog;
import com.babyshop.utils.MyOkHttpUtils;

import org.xutils.HttpManager;
import org.xutils.common.Callback;
import org.xutils.http.HttpManagerImpl;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by admin on 2017/4/11.
 */

public class FragmentOnePresenter {
    final String TAG = "FragmentOnePresenter";

    IFragmentOne iFragmentOne;

    public FragmentOnePresenter(IFragmentOne iFragmentOne) {
        this.iFragmentOne = iFragmentOne;
    }

    public void getBannerRes(){
        iFragmentOne.showProgress();
        MyOkHttpUtils.get(Url.HOME_BANNER, new MyOkHttpUtils.ResultCallback<BannerResultBean>() {
            @Override
            public void onSuccess(BannerResultBean response, int action) {
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
//        c.startActivity(new Intent(c, cls).putExtra("item", item));
    }

}
