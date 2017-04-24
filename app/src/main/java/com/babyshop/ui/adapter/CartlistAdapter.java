package com.babyshop.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babyshop.R;
import com.babyshop.commom.Url;
import com.babyshop.ui.bean.CartGoodsBean;
import com.babyshop.ui.view.IFragmentThree;
import com.babyshop.utils.GlideUtil;

import java.util.List;

/**
 * Created by admin on 2017/4/12.
 */

public class CartlistAdapter extends BaseRecyclerAdapter<CartlistAdapter.MyHolder> {

    Context context;
    List<CartGoodsBean> list;
    IFragmentThree iFragmentThree;

    public CartlistAdapter(Context context, List<CartGoodsBean> list, IFragmentThree iFragmentThree) {
        this.context = context;
        this.list = list;
        this.iFragmentThree = iFragmentThree;
    }

    public void setData(List<CartGoodsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.item_cartlist, parent, false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final CartGoodsBean bean = list.get(position);
        holder.name.setText(bean.name);
        holder.price.setText("¥" + bean.price);
        holder.num.setText("数量：" + bean.num);
        holder.tPrice.setText("¥" + Float.valueOf(bean.price) * Integer.valueOf(bean.num));
        GlideUtil.setUrl(context, Url.IMG + bean.pic.split(",")[0], holder.iv);
        initItemClick(holder, bean);
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iFragmentThree.onClickDelGoodsByCartId(bean.shopCardId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView name, price, num, tPrice;
        ImageView iv;
        LinearLayout rl;

        public MyHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.tv_cart_name);
            this.price = (TextView) view.findViewById(R.id.tv_cart_price);
            this.num = (TextView) view.findViewById(R.id.tv_cart_num);
            this.tPrice = (TextView) view.findViewById(R.id.tv_cart_total_price);
            this.iv = (ImageView) view.findViewById(R.id.iv_img);
            this.rl = (LinearLayout) view.findViewById(R.id.rl_cart_dele);
        }
    }


}
