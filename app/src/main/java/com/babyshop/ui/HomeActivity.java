package com.babyshop.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.babyshop.R;

/**
 * Description:
 * <p> Created by Lucky Feng on 2017/4/6 </p>
 */

public class HomeActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
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
        fragments = new Fragment[] {fragmentOne, fragmentTwo, fragmentThree, fragmentFour};
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_home, fragmentOne)
                .add(R.id.fl_home, fragmentTwo)
                .hide(fragmentTwo)
                .show(fragmentOne)
                .commit();
        ((RadioGroup)findViewById(R.id.rg_home)).setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch(checkedId){
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
     *  show fragment by fragIndex
     */
    private void showFragment(int fragIndex) {
        if (currentTabIndex != fragIndex) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.hide(fragments[currentTabIndex]);
            if (!fragments[fragIndex].isAdded()){
                ft.add(R.id.fl_home, fragments[fragIndex]);
            }
            ft.show(fragments[fragIndex]).commit();
        }
        currentTabIndex = fragIndex;
    }
}
