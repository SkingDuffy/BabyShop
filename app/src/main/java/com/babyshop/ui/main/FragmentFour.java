package com.babyshop.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.babyshop.R;
import com.babyshop.ui.presenter.FragmentFourPresenter;

/**
 * Created by admin on 2017/4/10.
 */

public class FragmentFour extends Fragment implements View.OnClickListener {

    private FragmentFourPresenter p = new FragmentFourPresenter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_four, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        view.findViewById(R.id.bt_logout).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_logout:
                p.logout();
                break;
        }
    }
}
