package com.babyshop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import com.babyshop.R;
import com.babyshop.Utils.SharedPreferencesUtil;
import com.babyshop.commom.Constant;
import com.babyshop.widget.cycleImage.ImageCycleView;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 是否第一次登陆
        SharedPreferencesUtil sharedUtil = SharedPreferencesUtil.getInstance(this);
        boolean isFirst = sharedUtil.getBoolean(Constant.FIRST_LOGIN, true);
        if (isFirst){
            // 进入欢迎页
            sharedUtil.putBoolean(Constant.FIRST_LOGIN, false);
            setContentView(R.layout.activity_welcome);
            initViewPager();
        } else {
            // 进入启动页
            setContentView(R.layout.activity_splash);
            initSplash();
        }

    }

    /**
     * 欢迎页轮播
     */
    private void initViewPager(){
        List<Object> imageList = new ArrayList<>();
        imageList.add(R.mipmap.splash_1);
        imageList.add(R.mipmap.splash_2);
        imageList.add(R.mipmap.splash_3);
        imageList.add(R.mipmap.splash_4);
        ImageCycleView vp = (ImageCycleView) findViewById(R.id.vp);
        final Button bt = (Button) findViewById(R.id.bt_enter);
        vp.setImageResources(imageList, new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void displayImage(Object imageRes, ImageView imageView) {
                imageView.setImageResource((Integer) imageRes);
            }

            @Override
            public void onImageClick(int position, View imageView) {

            }

            @Override
            public void onPageSelected(int index) {
                if (index == 3){
                    bt.setVisibility(View.VISIBLE);
                } else {
                    bt.setVisibility(View.GONE);
                }
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
            }
        });
    }

    /**
     * 启动页
     */
    private void initSplash(){
        View view = findViewById(R.id.view_splash);
        Animation anim = new AlphaAnimation(0.4f, 1.0f);
        anim.setDuration(800);
        view.startAnimation(anim);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {

                }
                startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
                finish();
            }
        }).start();

    }

}
