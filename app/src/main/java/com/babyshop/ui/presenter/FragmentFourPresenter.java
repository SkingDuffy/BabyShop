package com.babyshop.ui.presenter;

import com.babyshop.commom.Constant;
import com.babyshop.ui.biz.ToLoginBiz;
import com.babyshop.ui.view.IFragmentFour;
import com.babyshop.utils.SharedPreferencesUtil;

/**
 * Created by admin on 2017/4/15.
 */

public class FragmentFourPresenter {

    IFragmentFour iFragmentFour;
    ToLoginBiz toLoginBiz;

    public FragmentFourPresenter(IFragmentFour iFragmentFour) {
        this.iFragmentFour = iFragmentFour;
        toLoginBiz = new ToLoginBiz();
    }



    public void logout(){
        SharedPreferencesUtil shared = SharedPreferencesUtil.getInstance();
        String u_name = shared.getUserName();
        shared.clear();
        shared.putBoolean(Constant.HAS_LOGIN, false);
        shared.putString(Constant.U_NAME, u_name);
        shared.putBoolean(Constant.FIRST_LOGIN, false);
        iFragmentFour.logoutSuccess();
    }

    public boolean isToLogin(){
        return toLoginBiz.isToLogin(iFragmentFour);
    }

}
