package com.babyshop.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.babyshop.R;

/**
 * @auther: 祝令峰
 * @create: 2016/12/19 16:47
 * @title: 弹框公共类
 * @description:
 */
public class MyDialog {

    private Context context;

    public MyDialog(Context context) {
        this.context = context;
    }

    public interface onClickEditListener {
        void editText(String msg);
    }

    public void editTextDialog(String text, onClickEditListener rightListener) {
        editTextDialog(text, null, null, rightListener);
    }

    public void editTextDialog(String text, String leftText, String rightText, final onClickEditListener rightListener) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_edittext);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        dialog.setCancelable(true);
        final EditText et = (EditText) dialog.findViewById(R.id.et_msg);
        Button btn_left = (Button) dialog.findViewById(R.id.btn_left);
        Button btn_right = (Button) dialog.findViewById(R.id.btn_right);

        if (!TextUtils.isEmpty(text)){
            et.setText(text);
        }
        if (!TextUtils.isEmpty(leftText)) {
            btn_left.setText(leftText);  //左边按钮命名
        }
        if (!TextUtils.isEmpty(rightText)) {
            btn_right.setText(rightText);    //右边按钮命名
        }

        btn_left.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        if (rightListener != null)
            btn_right.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    rightListener.editText(et.getText().toString().trim());
                    dialog.dismiss();
                }
            });

    }


}
