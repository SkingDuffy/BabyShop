package com.babyshop.ui.presenter;

import com.babyshop.commom.Url;
import com.babyshop.ui.bean.ResultBean;
import com.babyshop.ui.view.IRegisterView;
import com.babyshop.utils.GsonUtil;
import com.babyshop.utils.MyOkHttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/4/13.
 */

public class RegisterPresenter {

    IRegisterView iRegisterView;

    public RegisterPresenter(IRegisterView iLogin) {
        this.iRegisterView = iLogin;
    }

    public void register(String name, String pwd, String phone) {
        iRegisterView.showProgress();
        Map<String, String> params = new HashMap<>();
        params.put("user", GsonUtil.getInstance().bean2Json(new UserInfo(name, pwd, phone)));
        MyOkHttpUtils.post(Url.REGIST, params, new MyOkHttpUtils.ResultCallback<ResultBean>() {
            @Override
            public void onSuccess(ResultBean response, int action) {
                iRegisterView.dismissProgress();
                if (response.flag)
                    iRegisterView.registerSuccess();
                iRegisterView.showToast(response.message);
            }

            @Override
            public void onFailure(Exception e) {
                iRegisterView.dismissProgress();
                iRegisterView.showToast(e.toString());
            }
        });
    }

    class UserInfo{
        String name;
        String password;
        String phone;

        public UserInfo(String name, String password, String phone) {
            this.name = name;
            this.password = password;
            this.phone = phone;
        }
    }

}
