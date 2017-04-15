package com.babyshop.ui.presenter;

import com.babyshop.commom.Constant;
import com.babyshop.ui.bean.UserBean;
import com.babyshop.ui.view.ILoginView;
import com.babyshop.utils.MyOkHttpUtils;
import com.babyshop.utils.SharedPreferencesUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by admin on 2017/4/13.
 */

public class LoginPresenter {

    ILoginView iLogin;

    public LoginPresenter(ILoginView iLogin) {
        this.iLogin = iLogin;
    }

    public void login(String url) {
        iLogin.showProgress("正在登陆");
        MyOkHttpUtils.get(url, new MyOkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response, int action) {
                iLogin.dismissProgress();
                try {
                    JSONObject rawObj = new JSONObject(response);
                    if (!rawObj.getBoolean("flag")) {
                        //登陆失败
                        iLogin.showToast(rawObj.getString("message"));
                    } else {
                        //登陆成功
                        JSONObject userObj = rawObj.getJSONObject("data");

                        UserBean bean = new UserBean();
                        bean.id = userObj.getString("id");
                        bean.name = userObj.getString("name");
                        bean.password = userObj.getString("password");
                        bean.phone = userObj.getString("phone");

                        SharedPreferencesUtil shared = SharedPreferencesUtil.getInstance();
                        shared.putString(Constant.U_ID, bean.id);
                        shared.putString(Constant.U_NAME, bean.name);
                        shared.putString(Constant.U_PHONE, bean.phone);
                        shared.putBoolean(Constant.HAS_LOGIN, true);

                        iLogin.showToast(rawObj.getString("message"));
                        iLogin.loginSuccess(bean);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Exception e) {
                iLogin.dismissProgress();
                iLogin.showToast(e.toString());
            }
        });
    }

}
