package com.babyshop.ui.presenter;

import com.babyshop.ui.bean.UserBean;
import com.babyshop.ui.view.ILoginView;
import com.babyshop.utils.MyOkHttpUtils;

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
                    JSONObject dataObj = rawObj.getJSONObject("data");

                    UserBean bean = new UserBean();
                    JSONObject userObj = dataObj.getJSONObject("user");
                    bean.id = userObj.getString("id");
                    bean.name = userObj.getString("name");
                    bean.password = userObj.getString("password");
                    JSONObject accountObj = dataObj.getJSONObject("account");
                    bean.userid = accountObj.getString("userid");
                    bean.balances = accountObj.getDouble("balances");

                    iLogin.loginResponse(bean);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Exception e) {
                iLogin.dismissProgress();
            }
        });
    }

}
