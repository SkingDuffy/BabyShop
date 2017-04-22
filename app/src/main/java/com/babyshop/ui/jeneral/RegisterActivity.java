package com.babyshop.ui.jeneral;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.babyshop.R;
import com.babyshop.commom.BaseActivity;
import com.babyshop.ui.presenter.RegisterPresenter;
import com.babyshop.ui.view.IRegisterView;

/**
 * Created by admin on 2017/4/13.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener, IRegisterView {

    private RegisterPresenter p = new RegisterPresenter(this);
    private ImageView dele_name, dele_pwd, dele_pwd2, dele_phone;
    private EditText et_name, et_pwd, et_pwd2, et_phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initTitleBar("注册");
        initView();
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_username);
        et_pwd = (EditText) findViewById(R.id.et_password);
        et_pwd2 = (EditText) findViewById(R.id.et_password2);
        et_phone = (EditText) findViewById(R.id.et_phone);
        dele_name = (ImageView) findViewById(R.id.iv_dele_username);
        dele_pwd = (ImageView) findViewById(R.id.iv_dele_pwd);
        dele_pwd2 = (ImageView) findViewById(R.id.iv_dele_pwd2);
        dele_phone = (ImageView) findViewById(R.id.iv_dele_phone);
        dele_name.setOnClickListener(this);
        dele_pwd.setOnClickListener(this);
        dele_pwd2.setOnClickListener(this);
        dele_phone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_dele_username:
                et_name.setText("");
                break;
            case R.id.iv_dele_pwd:
                et_pwd.setText("");
                break;
            case R.id.iv_dele_pwd2:
                et_pwd.setText("");
                break;
            case R.id.iv_dele_phone:
                et_name.setText("");
                break;
        }
    }

    /**
     * 注册提交
     */
    public void clickRegisterSubmit(View v) {
        String name = et_name.getText().toString().trim();
        String pwd = et_pwd.getText().toString().trim();
        String phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            return;
        }
        p.register(name, pwd, phone);
    }

    @Override
    public void registerSuccess() {
        finish();
    }

}
