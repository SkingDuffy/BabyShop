package com.babyshop.ui.general;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.babyshop.R;
import com.babyshop.commom.BaseActivity;
import com.babyshop.ui.bean.GoodsBean;
import com.babyshop.ui.main.FragmentThree;
import com.babyshop.ui.presenter.CommPresenter;
import com.babyshop.ui.view.ICommView;
import com.babyshop.utils.GlideUtil;
import com.babyshop.utils.SharedPreferencesUtil;
import com.babyshop.widget.cycleImage.BaseBannerBean;
import com.babyshop.widget.cycleImage.ImageCycleView;

/**
 * Created by admin on 2017/4/12.
 */

public class CommodityActivity extends BaseActivity implements ICommView {

    private CommPresenter p;
    private ImageCycleView imageCycleView;
    private TextView tv_name, tv_id, tv_price, tv_descri;
    private String id;
    private Button bt_collect;
    private boolean hasCollect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm);
        initTitleBar("单品详情");
        initParams();
        initView();
    }

    private void initParams() {
        Intent i = getIntent();
        id = i.getStringExtra("id");
        p = new CommPresenter(this, id);
    }

    private void initView() {
        imageCycleView = (ImageCycleView) findViewById(R.id.vp_banner_comm);
        tv_name = (TextView) findViewById(R.id.tv_comm_name);
        tv_id = (TextView) findViewById(R.id.tv_comm_id);
        tv_price = (TextView) findViewById(R.id.tv_comm_price);
        tv_descri = (TextView) findViewById(R.id.tv_comm_description);
        bt_collect = (Button) findViewById(R.id.bt_comm_collect);
    }

    @Override
    protected void onResume() {
        super.onResume();
        p.getComm();
    }

    @Override
    public void getComm(GoodsBean bean) {
        if (SharedPreferencesUtil.getInstance().hasLogin()){
            hasCollect = bean.isCollection;
            setCollectBtnText();
        }
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
        p.putIntoCart("1");
    }

    @Override
    public void onAddCartSuccess() {
        if (FragmentThree.mfragmentThree != null)
            FragmentThree.mfragmentThree.onRefresh();
    }

    /**
     * 收藏
     */
    public void clickCollect(View v) {
        bt_collect.setClickable(false);
        p.collect(hasCollect);
    }

    @Override
    public void onCollectSuccess() {
        bt_collect.setClickable(true);
        hasCollect = !hasCollect;
        setCollectBtnText();
    }

    @Override
    public void onCollectFailure() {
        bt_collect.setClickable(true);
    }

    @Override
    public void toLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void setCollectBtnText(){
        if (hasCollect) {
            bt_collect.setText("取消收藏");
        } else {
            bt_collect.setText("收藏");
        }
    }

}
