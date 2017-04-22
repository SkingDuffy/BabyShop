package com.babyshop.ui.jeneral;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.babyshop.R;
import com.babyshop.commom.BaseActivity;
import com.babyshop.ui.bean.OrderBean;

/**
 * Created by admin on 2017/4/15.
 */

public class GenerateOrderActivity extends BaseActivity {

    private OrderBean orderBean;
    private TextView tv_id, tv_price;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_order);
        initPre();
        initView();
    }

    private void initPre() {
        initTitleBar("提交成功");
        orderBean = (OrderBean) getIntent().getSerializableExtra("orderBean");
    }

    private void initView() {
        tv_id = (TextView) findViewById(R.id.tv_order_id);
        tv_price = (TextView) findViewById(R.id.tv_order_price);
        tv_id.setText("订单编号：" + orderBean.id);
        tv_price.setText("应付金额：¥" + orderBean.totalprice);
    }

    public void clickToShopping(View view){
        finish();
    }

    public void clickToOrder(View view){
        startActivity(new Intent(this, OrderActivity.class)
                .putExtra("orderBean", orderBean));
    }

}
