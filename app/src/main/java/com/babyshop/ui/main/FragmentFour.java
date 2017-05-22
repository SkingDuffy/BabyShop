package com.babyshop.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.babyshop.R;
import com.babyshop.ui.general.AddressActivity;
import com.babyshop.ui.general.CommodityListActivity;
import com.babyshop.ui.general.LoginActivity;
import com.babyshop.ui.general.OrderListActivity;
import com.babyshop.ui.presenter.FragmentFourPresenter;
import com.babyshop.ui.view.IFragmentFour;
import com.babyshop.utils.SharedPreferencesUtil;

/**
 * Created by admin on 2017/4/10.
 */

public class FragmentFour extends Fragment implements View.OnClickListener, IFragmentFour {

    private FragmentFourPresenter p = new FragmentFourPresenter(this);
    private ImageView iv_avatar;
    private TextView tv_name;
    private Button bt_logout;
    private SharedPreferencesUtil shared = SharedPreferencesUtil.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_four, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initInfo();
    }

    private void initView(View view) {
        iv_avatar = (ImageView) view.findViewById(R.id.iv_avatar);
        tv_name = (TextView) view.findViewById(R.id.tv_user_name);
        bt_logout = (Button) view.findViewById(R.id.bt_logout);
        iv_avatar.setOnClickListener(this);
        tv_name.setOnClickListener(this);
        bt_logout.setOnClickListener(this);
        int[] ids = {R.id.rl_item_address, R.id.rl_item_history, R.id.rl_item_order, R.id.rl_item_collect};
        for (int id : ids){
            view.findViewById(id).setOnClickListener(this);
        }
    }

    private void initInfo() {
        if (shared.hasLogin()){
            loginSuccess();
        } else {
            logoutSuccess();
        }
    }

    @Override
    public void onClick(View view) {
        if (p.isToLogin())
            return;
        switch (view.getId()) {
            case R.id.rl_item_address:
                startActivity(new Intent(getActivity(), AddressActivity.class));
                break;
            case R.id.rl_item_history:
                startActivity(new Intent(getActivity(), CommodityListActivity.class).putExtra("type", 5));
                break;
            case R.id.rl_item_collect:
                startActivity(new Intent(getActivity(), CommodityListActivity.class).putExtra("type", 6));
                break;
            case R.id.rl_item_order:
                startActivity(new Intent(getActivity(), OrderListActivity.class));
            break;
            case R.id.bt_logout:
                p.logout();
                break;
        }
    }

    @Override
    public void toLoginActivity() {
        startActivityForResult(new Intent(getActivity(), LoginActivity.class), 0x1);
    }

    @Override
    public void logoutSuccess() {
        iv_avatar.setImageResource(R.mipmap.avatar_default);
        tv_name.setText("点击登录");
        bt_logout.setVisibility(View.GONE);
    }

    @Override
    public void loginSuccess() {
        iv_avatar.setImageResource(R.mipmap.avatar_img);
        tv_name.setText(shared.getUserName());
        bt_logout.setVisibility(View.VISIBLE);
    }

}
