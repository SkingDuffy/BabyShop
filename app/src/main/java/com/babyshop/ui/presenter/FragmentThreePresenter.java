package com.babyshop.ui.presenter;

import android.support.v4.widget.SwipeRefreshLayout;

import com.babyshop.commom.Url;
import com.babyshop.ui.bean.CartGoodsBean;
import com.babyshop.ui.bean.OrderBean;
import com.babyshop.ui.bean.ResultAddOrder;
import com.babyshop.ui.bean.ResultBean;
import com.babyshop.ui.bean.ResultCartlist;
import com.babyshop.ui.biz.RefreshBiz;
import com.babyshop.ui.view.IFragmentThree;
import com.babyshop.utils.MyOkHttpUtils;
import com.babyshop.utils.SharedPreferencesUtil;

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
        if (cartlist.size() == 0){
            iFragmentThree.showToast("请往购物车里添点东西");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cartlist.size(); i++){
            sb.append(cartlist.get(i).shopCardId);
            if (i != cartlist.size()-1)
                sb.append(",");
        }
        Map<String, String> params = new HashMap<>();
        params.put("userid", shared.getUserId());
        params.put("ids", sb.toString());
        iFragmentThree.showProgress();
        MyOkHttpUtils.post(Url.SETTLEMENT_CART, params, new MyOkHttpUtils.ResultCallback<ResultAddOrder>() {
            @Override
            public void onSuccess(ResultAddOrder response, int action) {
                iFragmentThree.dismissProgress();
                iFragmentThree.stopRefresh();
                iFragmentThree.showToast(response.message);
                if (response.flag){
                    iFragmentThree.onAddOrderSuccess(response.data);
                }
            }

            @Override
            public void onFailure(Exception e) {
                iFragmentThree.dismissProgress();
                iFragmentThree.stopRefresh();
            }
        });
    }

    public void delByCartId(String cartId){
        iFragmentThree.showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("userid", shared.getUserId());
        params.put("id", cartId);
        MyOkHttpUtils.post(Url.DEL_CART, params,new MyOkHttpUtils.ResultCallback<ResultBean>() {
            @Override
            public void onSuccess(ResultBean response, int action) {
                iFragmentThree.dismissProgress();
                iFragmentThree.stopRefresh();
                iFragmentThree.showToast(response.message);
                if (response.flag)
                    iFragmentThree.onDelSuccess();
            }

            @Override
            public void onFailure(Exception e) {
                iFragmentThree.dismissProgress();
                iFragmentThree.stopRefresh();
            }
        });
    }

    /**
     * 计算总数
     */
    public int getTotalNum(List<CartGoodsBean> list){
        int total = 0;
        for (CartGoodsBean bean : list){
            total += Integer.valueOf(bean.num);
        }
        return total;
    }

    /**
     * 计算总价
     */
    public String getTotalPrice(List<CartGoodsBean> list){
        Float total = 0f;
        for (CartGoodsBean bean : list){
            total += Float.valueOf(bean.price) * Integer.valueOf(bean.num);
        }
        return String.format("%.2f", total);
    }

    /**
     * 生成bean模型
     */
    public OrderBean getOrderBean (String orderId, List<CartGoodsBean> list){
        OrderBean orderBean = new OrderBean();
        orderBean.id = orderId;
        orderBean.commodities = list;
        orderBean.totalprice = getTotalPrice(list);
        return orderBean;
    }

}
