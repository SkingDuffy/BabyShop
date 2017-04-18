package com.babyshop.ui.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.babyshop.commom.Url;
import com.babyshop.ui.bean.CartGoodsBean;
import com.babyshop.ui.bean.ResultCartlist;
import com.babyshop.ui.biz.RefreshBiz;
import com.babyshop.ui.view.IFragmentThree;
import com.babyshop.utils.GsonUtil;
import com.babyshop.utils.LLog;
import com.babyshop.utils.MyOkHttpUtils;
import com.babyshop.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/4/14.
 */

public class FragmentThreePresenter {

    private IFragmentThree iFragmentThree;
    private RefreshBiz refreshBiz;
    private SharedPreferencesUtil shared = SharedPreferencesUtil.getInstance();

    public FragmentThreePresenter(IFragmentThree iFragmentThree) {
        this.iFragmentThree = iFragmentThree;
        this.refreshBiz = new RefreshBiz();
    }

    public void setRefreshColor(SwipeRefreshLayout swipeRefresh){
        refreshBiz.setRefreshColor(swipeRefresh);
    }

    public void getCartList(){
        iFragmentThree.showProgress();
        String url = Url.CART_LIST + "?userid=" + shared.getUserId();
        MyOkHttpUtils.get(url, new MyOkHttpUtils.ResultCallback<ResultCartlist>() {
            @Override
            public void onSuccess(ResultCartlist response, int action) {
                iFragmentThree.dismissProgress();
                iFragmentThree.stopRefresh();
                iFragmentThree.getCartList(response.data);
            }

            @Override
            public void onFailure(Exception e) {
                iFragmentThree.dismissProgress();
                iFragmentThree.stopRefresh();
            }
        });
    }

    public void settlement(List<CartGoodsBean> cartlist){
        List<ParamsSubBean> paramsList = new ArrayList<>();
        for (CartGoodsBean bean : cartlist){
            paramsList.add(new ParamsSubBean(bean.id, bean.num, bean.price));
        }
        ParamsBean paramsBean = new ParamsBean(shared.getUserId(), paramsList);
        String paramsBody = GsonUtil.getInstance().bean2Json(paramsBean);
        LLog.e("---------- paramsBody = " + paramsBody);

//        Map<String, String> params = new HashMap<>();
//        params.put("userid", shared.getUserId());
//        params.put("commodities", commodities);
        iFragmentThree.showProgress();
        MyOkHttpUtils.postString(Url.SETTLEMENT_CART, paramsBody, new MyOkHttpUtils.ResultCallback<ResultCartlist>() {
            @Override
            public void onSuccess(ResultCartlist response, int action) {
                iFragmentThree.dismissProgress();
                iFragmentThree.stopRefresh();
                LLog.e("----------- onSuccess ==========");
                iFragmentThree.toGenerateOrderActivity();
            }

            @Override
            public void onFailure(Exception e) {
                iFragmentThree.dismissProgress();
                iFragmentThree.stopRefresh();
            }
        });
    }

    class ParamsBean {
        String userid;
        List<ParamsSubBean> commodities;

        public ParamsBean(String userid, List<ParamsSubBean> commodities) {
            this.userid = userid;
            this.commodities = commodities;
        }
    }

    class ParamsSubBean {
        String id;
        String num;
        String price;

        public ParamsSubBean(String id, String num, String price) {
            this.id = id;
            this.num = num;
            this.price = price;
        }
    }

}
