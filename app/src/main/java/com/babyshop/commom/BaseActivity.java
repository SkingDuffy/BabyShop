package com.babyshop.commom;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.babyshop.R;

/**
 * Created by admin on 2017/4/11.
 */

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog pd;

    /**
     * 设置标题栏
     *
     * @param title
     */
    public void initTitleBar(String title) {
        ((TextView) findViewById(R.id.tv_title)).setText(title);
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 显示进度条
     */
    public void showProgress() {
        showProgress(null);
    }

    public void showProgress(String text) {
        if (TextUtils.isEmpty(text)) {
            text = "Loading.....";
        }
        if (pd == null) {
            pd = new ProgressDialog(this);
            pd.setMessage(text);
            pd.setCanceledOnTouchOutside(false);
        }
        pd.show();
    }

    /**
     * 关闭进度条
     */
    public void dismissProgress() {
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }

    /**
     * Toast
     */
    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
