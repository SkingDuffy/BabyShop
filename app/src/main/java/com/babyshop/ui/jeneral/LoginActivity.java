package com.babyshop.ui.jeneral;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.babyshop.R;
import com.babyshop.commom.BaseActivity;
import com.babyshop.commom.Url;
import com.babyshop.ui.bean.UserBean;
import com.babyshop.ui.presenter.LoginPresenter;
import com.babyshop.ui.view.ILoginView;
import com.babyshop.utils.SharedPreferencesUtil;

/**
 * Created by admin on 2017/4/13.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, TextWatcher, ILoginView{

    private LoginPresenter p = new LoginPresenter(this);
    private ImageView iv_name, iv_pwd, dele_name, dele_pwd;
    private EditText et_name, et_pwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_username);
        et_pwd = (EditText) findViewById(R.id.et_password);
        String u_name = SharedPreferencesUtil.getInstance().getUserName();
        if (!TextUtils.isEmpty(u_name)){
            et_name.setText(u_name);
            et_pwd.setFocusable(true);
            et_pwd.requestFocus();
        }
        iv_name = (ImageView) findViewById(R.id.iv_username);
        iv_pwd = (ImageView) findViewById(R.id.iv_password);
        dele_name = (ImageView) findViewById(R.id.iv_dele_username);
        dele_pwd = (ImageView) findViewById(R.id.iv_dele_pwd);
        et_name.addTextChangedListener(this);
        et_pwd.addTextChangedListener(this);
        iv_name.setOnClickListener(this);
        iv_pwd.setOnClickListener(this);
        dele_name.setOnClickListener(this);
        dele_pwd.setOnClickListener(this);
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
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (et_name.length() > 0) {
            iv_name.setImageResource(R.mipmap.icon_avatar_enable);
            dele_name.setVisibility(View.VISIBLE);
        } else {
            iv_name.setImageResource(R.mipmap.icon_avatar_disable);
            dele_name.setVisibility(View.GONE);
        }
        if (et_pwd.length() > 0) {
            iv_pwd.setImageResource(R.mipmap.icon_lock_enable);
            dele_pwd.setVisibility(View.VISIBLE);
        } else {
            iv_pwd.setImageResource(R.mipmap.icon_lock_disable);
            dele_pwd.setVisibility(View.GONE);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

    /**
     * 登陆事件
     */
    public void clickLogin(View v){
        String name = et_name.getText().toString().trim();
        String pwd = et_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            et_name.setFocusable(true);
            et_name.requestFocus();
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            et_pwd.setFocusable(true);
            et_pwd.requestFocus();
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        p.login(Url.LOGIN + "?name=" + name + "&password=" + pwd);
    }

    /**
     * 注册事件
     */
    public void clickRegister(View v){

    }

    @Override
    public void loginSuccess(UserBean userBean) {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void registerResponse(String url) {

    }

}
