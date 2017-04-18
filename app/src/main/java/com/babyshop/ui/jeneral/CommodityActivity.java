package com.babyshop.ui.jeneral;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.babyshop.R;
import com.babyshop.commom.BaseActivity;
import com.babyshop.commom.Constant;
import com.babyshop.commom.Url;
import com.babyshop.ui.bean.GoodsBean;
import com.babyshop.ui.presenter.CommPresenter;
import com.babyshop.ui.view.ICommView;
import com.babyshop.utils.GlideUtil;
import com.babyshop.utils.SharedPreferencesUtil;
import com.babyshop.widget.cycleImage.BaseBannerBean;
import com.babyshop.widget.cycleImage.ImageCycleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/12.
 */

public class CommodityActivity extends BaseActivity implements ICommView {

    private CommPresenter p;
    private ImageCycleView imageCycleView;
    private TextView tv_name, tv_id, tv_price, tv_descri;
    private String id, userid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm);
        initTitleBar("单品详情");
        initParams();
        initView();
    }

    private void initParams() {
        SharedPreferencesUtil shared = SharedPreferencesUtil.getInstance();
        p = new CommPresenter(this, shared);

        Intent i = getIntent();
        id = i.getStringExtra("id");
        userid = shared.getString(Constant.U_ID, "");
    }

    private void initView() {
        imageCycleView = (ImageCycleView) findViewById(R.id.vp_banner_comm);
        tv_name = (TextView) findViewById(R.id.tv_comm_name);
        tv_id = (TextView) findViewById(R.id.tv_comm_id);
        tv_price = (TextView) findViewById(R.id.tv_comm_price);
        tv_descri = (TextView) findViewById(R.id.tv_comm_description);
        String suffix = TextUtils.isEmpty(userid) ? "?id=" + id : "?id=" + id + "&userid=" + userid;
        p.getComm(Url.COMMODITY + suffix);
    }

    @Override
    public void getComm(GoodsBean bean) {
        tv_name.setText(bean.name);
        tv_id.setText("商品编号：" + bean.id);
        tv_price.setText("¥" + bean.price);
        tv_descri.setText(bean.describe);
        imageCycleView.setImageResources(p.getBannerList(bean), new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void displayImage(String imageURL, ImageView imageView) {
                GlideUtil.setUrl(CommodityActivity.this, imageURL, imageView);
            }

            @Override
            public void onImageClick(BaseBannerBean dataBean, int position, View imageView) {
            }
        });
    }

    /**
     * 加入购物车
     */
    public void clickPutIntoCart(View v) {
        p.putIntoCart(id, "1");
    }

    /**
     * 收藏
     */
    public void clickCollect(View v) {
        p.collect(id);
    }

    @Override
    public void toLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
    }

}
