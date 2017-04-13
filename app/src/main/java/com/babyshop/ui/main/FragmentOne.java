package com.babyshop.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.babyshop.R;
import com.babyshop.commom.Url;
import com.babyshop.ui.presenter.FragmentOnePresenter;
import com.babyshop.ui.view.IFragmentOne;
import com.babyshop.ui.bean.BannerToCycleBean;
import com.babyshop.utils.GlideUtil;
import com.babyshop.widget.cycleImage.ImageCycleView;
import com.bumptech.glide.Glide;

import java.util.List;

public class FragmentOne extends Fragment implements IFragmentOne, View.OnClickListener {
    private HomeActivity mInstance;
    private FragmentOnePresenter mPresenter = new FragmentOnePresenter(this);
    private ImageCycleView vp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mInstance = (HomeActivity) getActivity();
        initView(view);
    }

    private void initView(View view) {
        // set banner
        vp = (ImageCycleView) view.findViewById(R.id.vp_banner_home);
        mPresenter.getBannerRes(Url.HOME_BANNER);
        // set item click listener
        int[] itemIds = new int[]{R.id.rl_item_0, R.id.rl_item_1, R.id.rl_item_2, R.id.rl_item_3, R.id.rl_item_4};
        for (int i = 0; i < itemIds.length; i++){
            view.findViewById(itemIds[i]).setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View view) {
        int type = 0;
        switch (view.getId()){
            case R.id.rl_item_0: type = 0;
                break;
            case R.id.rl_item_1: type = 1;
                break;
            case R.id.rl_item_2: type = 2;
                break;
            case R.id.rl_item_3: type = 3;
                break;
            case R.id.rl_item_4: type = 4;
                break;
        }
        mPresenter.startActivity(getActivity(), type);
    }

    @Override
    public void setBannerRes(List<BannerToCycleBean> response) {
        vp.setImageResources(response, new ImageCycleView.ImageCycleViewListener<BannerToCycleBean>() {
            @Override
            public void displayImage(String imageURL, ImageView imageView) {
                GlideUtil.setRes(getActivity(), imageURL, imageView);
            }

            @Override
            public void onImageClick(BannerToCycleBean dataBean, int position, View imageView) {

            }
        });
    }

    @Override
    public void showProgress() {
        mInstance.showProgress();
    }

    @Override
    public void dismissProgress() {
        mInstance.dismissPorgress();
    }

}
