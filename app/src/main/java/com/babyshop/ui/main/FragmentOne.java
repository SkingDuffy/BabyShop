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
        int[] itemIds = new int[]{R.id.rl_item_1, R.id.rl_item_2, R.id.rl_item_3, R.id.rl_item_4, R.id.rl_item_5};
        for (int i = 0; i < itemIds.length; i++){
            view.findViewById(itemIds[i]).setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_item_1:
                mPresenter.startActivity(getActivity(), 0);
                break;

        }
    }

    @Override
    public void setBannerRes(List<BannerToCycleBean> response) {
        vp.setImageResources(response, new ImageCycleView.ImageCycleViewListener<BannerToCycleBean>() {
            @Override
            public void displayImage(String imageURL, ImageView imageView) {
                Glide.with(getActivity())
                        .load(imageURL)
                        .placeholder(R.mipmap.img_holder)
                        .error(R.mipmap.img_error)
                        .into(imageView);
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
