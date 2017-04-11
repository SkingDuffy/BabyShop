package com.babyshop.commom;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

/**
 * Created by admin on 2017/4/11.
 */

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog pd;

    /**
     * 显示进度条
     */
    public void showProgress() {
        showProgress(null);
    }
    public void showProgress(String text) {
        pd = new ProgressDialog(this);
        if (TextUtils.isEmpty(text)){
            text = "加载中...";
        }
        pd.setMessage(text);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
    }

    /**
     * 关闭进度条
     */
    public void dismissPorgress() {
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }

}
