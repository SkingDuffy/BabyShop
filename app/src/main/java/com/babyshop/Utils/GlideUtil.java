package com.babyshop.utils;

import android.content.Context;
import android.widget.ImageView;

import com.babyshop.R;
import com.babyshop.commom.Url;
import com.bumptech.glide.Glide;

/**
 * Created by admin on 2017/4/13.
 */

public class GlideUtil {

    /**
     * 加载网络图片
     * @param context
     * @param url
     * @param iv
     */
    public static void setUrl(Context context, String url, ImageView iv){
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.img_holder)
                .error(R.mipmap.img_error)
                .into(iv);
    }

}
