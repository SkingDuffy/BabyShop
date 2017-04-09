package com.babyshop.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.babyshop.R;
import com.babyshop.Utils.SharedPreferencesUtil;
import com.babyshop.commom.Constant;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    private Context mContext;
    private List<Integer> imageList = new ArrayList<>();
    private ImageView[] mImageViews;
    private LinearLayout mGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        // 是否第一次登陆
        SharedPreferencesUtil sharedUtil = SharedPreferencesUtil.getInstance(this);
//        boolean isFirst = sharedUtil.getBoolean(Constant.FIRST_LOGIN, true);
        if (true) {
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
    private void initViewPager() {
        imageList.add(R.mipmap.splash_2);
        imageList.add(R.mipmap.splash_3);
        imageList.add(R.mipmap.splash_4);
        ViewPager vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(new VpAdapter(imageList));
        final Button bt = (Button) findViewById(R.id.bt_enter);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
            }
        });
        setBannerIndicator();
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int index) {
                mImageViews[index].setBackgroundResource(R.mipmap.icon_banner_select);
                for (int i = 0; i < mImageViews.length; i++) {
                    if (index != i) {
                        mImageViews[i].setBackgroundResource(R.mipmap.icon_banner_unselect);
                    }
                }
                if (index == imageList.size() - 1) {
                    bt.setVisibility(View.VISIBLE);
                } else {
                    bt.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 启动页
     */
    private void initSplash() {
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

    /**
     * 设置轮播指示点
     */
    private void setBannerIndicator() {
        mGroup = (LinearLayout) findViewById(R.id.ll_indicator);
        mImageViews = new ImageView[imageList.size()];
        float mScale = getResources().getDisplayMetrics().density;
        for (int i = 0; i < imageList.size(); i++) {
            ImageView mImageView = new ImageView(mContext);
            int imagePadding = (int) (mScale * 5);
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setMargins(imagePadding, 0, imagePadding, 0);
            mImageView.setLayoutParams(layout);
            mImageView.setPadding(imagePadding, imagePadding, imagePadding, imagePadding);
            mImageViews[i] = mImageView;

            if (i == 0) {
                mImageViews[i].setBackgroundResource(R.mipmap.icon_banner_unselect);
            } else {
                mImageViews[i].setBackgroundResource(R.mipmap.icon_banner_select);
            }
            mGroup.addView(mImageViews[i]);
        }
    }

    class VpAdapter extends PagerAdapter {
        List<Integer> list;

        public VpAdapter(List<Integer> list) {
            this.list = list;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(list.get(position));
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ImageView view = (ImageView) object;
            container.removeView(view);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
