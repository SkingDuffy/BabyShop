package com.babyshop.ui.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioGroup;

import com.babyshop.R;
import com.babyshop.commom.BaseActivity;
import com.babyshop.ui.jeneral.LoginActivity;
import com.babyshop.ui.presenter.HomePresenter;
import com.babyshop.ui.view.IHomeView;

public class HomeActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, IHomeView {
    private HomePresenter p = new HomePresenter(this);
    private Fragment[] fragments;
    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;
    private FragmentFour fragmentFour;
    private int currentTabIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();

    }

    private void initView() {
        fragmentOne = new FragmentOne();        // init all fragments
        fragmentTwo = new FragmentTwo();
        fragmentThree = new FragmentThree();
        fragmentFour = new FragmentFour();
        fragments = new Fragment[]{fragmentOne, fragmentTwo, fragmentThree, fragmentFour};
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_home, fragmentOne)
                .add(R.id.fl_home, fragmentTwo)
                .hide(fragmentTwo)
                .show(fragmentOne)
                .commit();
        showFragment(0);    //状态栏颜色
        ((RadioGroup) findViewById(R.id.rg_home)).setOnCheckedChangeListener(this);
        findViewById(R.id.rb_cart).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        return p.isToLogin();
                }
                return false;
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                showFragment(0);
                break;
            case R.id.rb_sort:
                showFragment(1);
                break;
            case R.id.rb_cart:
                showFragment(2);
                break;
            case R.id.rb_mine:
                showFragment(3);
                break;
        }
    }

    /**
     * show fragment by fragIndex
     */
    private void showFragment(int fragIndex) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (fragIndex == 0) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            } else {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }

        if (currentTabIndex != fragIndex) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.hide(fragments[currentTabIndex]);
            if (!fragments[fragIndex].isAdded()) {
                ft.add(R.id.fl_home, fragments[fragIndex]);
            }
            ft.show(fragments[fragIndex]).commit();
        }
        currentTabIndex = fragIndex;
    }

    @Override
    public void toLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
    }

}
